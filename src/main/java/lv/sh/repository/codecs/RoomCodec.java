package lv.sh.repository.codecs;

import lv.sh.dto.Room;
import org.bson.*;
import org.bson.codecs.*;
import org.bson.types.ObjectId;

import java.util.List;

public class RoomCodec implements CollectibleCodec<Room> {

    private Codec<Document> documentCodec;

    public RoomCodec() {
        this.documentCodec = new DocumentCodec();
    }

    public RoomCodec(Codec<Document> codec) {
        this.documentCodec = codec;
    }

    @Override
    public void encode(BsonWriter writer, Room value,
                       EncoderContext encoderContext) {
        Document document = roomToDocument(value);
        documentCodec.encode(writer, document, encoderContext);
    }

    public Document roomToDocument(Room room) {
        Document document = new Document();
        String id = room.getId();
        String roomName = room.getRoomName();
        String status = room.getStatus();
        Boolean on = room.isOn();
        Double percentage = room.getPercentage();
        List<String> relatedDevices=room.getRelatedDevices();

        if (null != id) document.put("_id", id);
        if (null != roomName) document.put("room_name", roomName);
        if (null != status) document.put("status", status);
        if (null != on) document.put("on", on);
        if (null != percentage) document.put("percentage", percentage);
        if (null != relatedDevices) document.put("related_devices", relatedDevices);
        return document;
    }


    @Override
    public Class<Room> getEncoderClass() {
        return Room.class;
    }

    @Override
    public Room decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        System.out.println("document " + document);
        Room device = documentToRoom(document);
        return device;
    }

    public Room documentToRoom(Document document) {
        Room room = new Room();
        room.setId(document.getString("_id"));
        room.setRoomName(document.getString("room_name"));
        room.setOn(document.getBoolean("on"));
        room.setPercentage(document.getDouble("percentage"));
        room.setRelatedDevices((List<String>) document.get("related_devices"));
        return room;
    }

    @Override
    public Room generateIdIfAbsentFromDocument(Room document) {
        Room deviceWithId = document;
        if (deviceWithId.getId() == null) deviceWithId.setId(new ObjectId().toString());
        return deviceWithId;
    }

    @Override
    public boolean documentHasId(Room document) {
        return null == document.getId();
    }

    @Override
    public BsonValue getDocumentId(Room document) {
        if (!documentHasId(document)) {
            throw new IllegalStateException("The document does not contain an _id");
        }

        return new BsonString(document.getId());
    }
}