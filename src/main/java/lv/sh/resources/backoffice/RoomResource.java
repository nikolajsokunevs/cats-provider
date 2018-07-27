package lv.sh.resources.backoffice;

import lv.sh.dto.Room;
import lv.sh.service.room.IRoomService;
import lv.sh.service.room.RoomServiceImpl;

import javax.ws.rs.*;
import java.util.List;

@Path("room")
public class RoomResource {

    @GET
    @Path("all")
    @Produces("application/json")
    public List<Room> getAll(){
        IRoomService roomService=new RoomServiceImpl();
        return roomService.getAllRooms();
    }

    @POST
    @Path("add")
    @Produces("application/json")
    public Room post(Room room){
        IRoomService roomService=new RoomServiceImpl();
        roomService.addRoom(room);
        return room;
    }

    @PUT
    @Path("update/{param}")
    @Produces("application/json")
    public Room put(Room room, @PathParam("param") String id){
        IRoomService roomService=new RoomServiceImpl();
        roomService.updateRoom(room, id);
        return room;
    }
}
