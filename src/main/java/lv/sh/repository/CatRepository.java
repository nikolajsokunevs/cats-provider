package lv.sh.repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lv.sh.dto.Cat;
import lv.sh.config.ApplicationProperties;
import lv.sh.dto.Cat_;
import lv.sh.repository.codecs.CatCodec;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;


import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

import static lv.sh.config.ApplicationProperties.getString;

public class CatRepository {

    public static CatRepository instance;

    private static String HOST = getString(ApplicationProperties.ApplicationProperty.DB_HOST);
    private static String PORT = getString(ApplicationProperties.ApplicationProperty.DB_PORT);
    private static String DB_NAME = getString(ApplicationProperties.ApplicationProperty.DB_NAME);

    private static String USER = getString(ApplicationProperties.ApplicationProperty.DB_USER);
    private static String PASSWORD = getString(ApplicationProperties.ApplicationProperty.DB_PASSWORD);

    private MongoDatabase db;

    private CodecRegistry codecRegistry;

    private CatRepository() {

        Codec<Document> defaultDocumentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
        CatCodec catCodec = new CatCodec(defaultDocumentCodec);
        codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromCodecs(catCodec));

        MongoClientURI uri = new MongoClientURI(getConnectionURL());
        MongoClient mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase(DB_NAME).withCodecRegistry(codecRegistry);
    }

    public static CatRepository getInstance() {
        if (instance != null) return instance;
        instance = new CatRepository();
        return instance;
    }

    private static String getConnectionURL() {
        String url = format("mongodb://%s:%s@%s:%s/%s", USER, PASSWORD, HOST, PORT, DB_NAME);
        return url;
    }

    public void insertCat(Cat cat) {
        MongoCollection<Cat> collection = db.getCollection("cat", Cat.class);
        collection.insertOne(cat);
    }

    public List<Cat_> getAllCats() {
        MongoCollection<Cat> collection = db.getCollection("cat", Cat.class);
        List<Cat> foundDocument = collection.find().into(new ArrayList<Cat>());
        return convertCatToCat_(foundDocument);
    }

    private List<Cat_> convertCatToCat_(List<Cat> cats){
        List<Cat_> response = new ArrayList<>();
        for(Cat current:cats){
            Cat_ currentR=new Cat_(current);
            if (current.getAncestors()!=null){
                response.addAll(convertCatToCat_(current.getAncestors()));
                List<String> ancestorsForCurrent=new ArrayList<>();
                for (Cat ancestor:current.getAncestors()) {
                    ancestorsForCurrent.add((ancestor.getId()));
                }
                currentR.setAncestors(ancestorsForCurrent);
            }
            response.add(currentR);
        }
        return response;
    }

}
