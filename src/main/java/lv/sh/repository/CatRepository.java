package lv.sh.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lv.sh.dto.Cat;
import lv.sh.config.ApplicationProperties;
import lv.sh.repository.codecs.CatCodec;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;


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

    public List<Cat> getAllCats() {
        MongoCollection<Cat> collection = db.getCollection("cat", Cat.class);
        return collection.find().into(new ArrayList<>());
    }

    public Cat getCatById(String id){
        MongoCollection<Cat> collection = db.getCollection("cat", Cat.class);
        BasicDBObject searchObject = new BasicDBObject();
        searchObject.put("_id", id);
        List<Cat> cats=collection.find(searchObject).into(new ArrayList<>());
        if (cats.size()>0) return cats.get(0);
        return new Cat();
    }

    public void deleteCat(String id){
        MongoCollection<Cat> collection = db.getCollection("cat", Cat.class);
        BasicDBObject searchObject = new BasicDBObject();
        searchObject.put("_id", id);
        collection.deleteOne(searchObject);
    }

    public void updateCat(String id, Cat cat){
        deleteCat(id);
        cat.setId(id);
        insertCat(cat);
    }
}
