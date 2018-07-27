package lv.sh.service.room;

import lv.sh.dto.Room;
import lv.sh.repository.DeviceRepository;

import java.util.List;

public class RoomServiceImpl implements IRoomService {

    private DeviceRepository deviceRepository = DeviceRepository.getInstance();

    @Override
    public Room addRoom(Room room) {
        deviceRepository.insertRoom(room);
        return room;
    }

    @Override
    public List<Room> getAllRooms() {
        return deviceRepository.getAllRooms();
    }

    @Override
    public Room updateRoom(Room room, String id) {
        deviceRepository.updateRoom(room, id);
        return room;
    }
}
