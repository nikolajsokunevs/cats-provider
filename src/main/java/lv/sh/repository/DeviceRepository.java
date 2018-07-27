package lv.sh.repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lv.sh.dto.Device;
import lv.sh.config.ApplicationProperties;
import lv.sh.dto.Room;
import lv.sh.repository.codecs.DeviceCodec;
import lv.sh.repository.codecs.RoomCodec;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;


import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

import static lv.sh.config.ApplicationProperties.getString;

public class DeviceRepository {

    public static DeviceRepository instance;

    private static String HOST = getString(ApplicationProperties.ApplicationProperty.DB_HOST);
    private static String PORT = getString(ApplicationProperties.ApplicationProperty.DB_PORT);
    private static String DB_NAME = getString(ApplicationProperties.ApplicationProperty.DB_NAME);

    private static String USER = getString(ApplicationProperties.ApplicationProperty.DB_USER);
    private static String PASSWORD = getString(ApplicationProperties.ApplicationProperty.DB_PASSWORD);

    private MongoDatabase db;

    private CodecRegistry codecRegistry;

    private DeviceRepository() {

        Codec<Document> defaultDocumentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
        DeviceCodec deviceCodec = new DeviceCodec(defaultDocumentCodec);
        RoomCodec roomCodec = new RoomCodec(defaultDocumentCodec);
        codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromCodecs(deviceCodec), CodecRegistries.fromCodecs(roomCodec));

        MongoClientURI uri = new MongoClientURI(getConnectionURL());
        MongoClient mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase(DB_NAME).withCodecRegistry(codecRegistry);
    }

    public static DeviceRepository getInstance() {
        if (instance != null) return instance;
        instance = new DeviceRepository();
        return instance;
    }

    private static String getConnectionURL() {
        String url = format("mongodb://%s:%s@%s:%s/%s", USER, PASSWORD, HOST, PORT, DB_NAME);
        return url;
    }

    public void insertDevice(Device device) {
        MongoCollection<Device> collection = db.getCollection("device", Device.class);
        collection.insertOne(device);
    }

    public void insertRoom(Room room) {
        MongoCollection<Room> collection = db.getCollection("room", Room.class);
        collection.insertOne(room);
    }

    public void updateRoom(Room room, String id) {
        MongoCollection<Room> collection = db.getCollection("room", Room.class);
        collection.deleteOne(Filters.eq("_id", id));
        collection.insertOne(room);
    }

    public List<Device> getAllDevices() {
        MongoCollection<Device> collection = db.getCollection("device", Device.class);
        List<Device> foundDocument = collection.find().into(new ArrayList<Device>());
        return foundDocument;
    }

    public List<Room> getAllRooms() {
        MongoCollection<Room> collection = db.getCollection("room", Room.class);
        List<Room> foundDocument = collection.find().into(new ArrayList<Room>());
        return foundDocument;
    }
}
