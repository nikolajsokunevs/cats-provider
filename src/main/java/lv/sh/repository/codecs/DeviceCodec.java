package lv.sh.repository.codecs;

import lv.sh.dto.Device;
import org.bson.*;
import org.bson.codecs.*;
import org.bson.types.ObjectId;

import java.util.List;

public class DeviceCodec implements CollectibleCodec<Device> {

    private Codec<Document> documentCodec;

    public DeviceCodec() {
        this.documentCodec = new DocumentCodec();
    }

    public DeviceCodec(Codec<Document> codec) {
        this.documentCodec = codec;
    }

    @Override
    public void encode(BsonWriter writer, Device value,
                       EncoderContext encoderContext) {
        Document document = deviceToDocument(value);
        documentCodec.encode(writer, document, encoderContext);
    }

    public Document deviceToDocument(Device device) {
        Document document = new Document();
        String id = device.getId();
        String deviceName = device.getDeviceName();
        String status = device.getStatus();
        Boolean on = device.isOn();
        Double percentage = device.getPercentage();
        List<String> relatedDevices=device.getRelatedDevices();

        if (null != id) document.put("_id", id);
        if (null != deviceName) document.put("device_name", deviceName);
        if (null != status) document.put("status", status);
        if (null != on) document.put("on", on);
        if (null != percentage) document.put("percentage", percentage);
        if (null != relatedDevices) document.put("related_devices", relatedDevices);
        return document;
    }


    @Override
    public Class<Device> getEncoderClass() {
        return Device.class;
    }

    @Override
    public Device decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        System.out.println("document " + document);
        Device device = documentToDevice(document);
        return device;
    }

    public Device documentToDevice(Document document) {
        Device device = new Device();
        device.setId(document.getString("_id"));
        device.setDeviceName(document.getString("device_name"));
        device.setOn(document.getBoolean("on"));
        device.setPercentage(document.getDouble("percentage"));
        device.setRelatedDevices((List<String>) document.get("related_devices"));
        return device;
    }

    @Override
    public Device generateIdIfAbsentFromDocument(Device document) {
        Device deviceWithId = document;
        if (deviceWithId.getId() == null) deviceWithId.setId(new ObjectId().toString());
        return deviceWithId;
    }

    @Override
    public boolean documentHasId(Device document) {
        return null == document.getId();
    }

    @Override
    public BsonValue getDocumentId(Device document) {
        if (!documentHasId(document)) {
            throw new IllegalStateException("The document does not contain an _id");
        }

        return new BsonString(document.getId());
    }
}