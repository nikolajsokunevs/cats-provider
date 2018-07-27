package lv.sh.dto;

import java.util.List;

public class Room {

    private String id;
    private String roomName;
    private String status;
    private boolean on;
    private double percentage;
    private List<String> relatedDevices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public List<String> getRelatedDevices() {
        return relatedDevices;
    }

    public void setRelatedDevices(List<String> relatedDevices) {
        this.relatedDevices = relatedDevices;
    }
}
