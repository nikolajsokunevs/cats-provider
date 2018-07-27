package lv.sh.service.room;

import lv.sh.dto.Room;

import java.util.List;

public interface IRoomService {

    /**
     * Creates new room entity in DB.
     * @param room
     * @return
     */
    public Room addRoom(Room room);

    public List<Room> getAllRooms();

    public Room updateRoom(Room room, String id);
}
