package lv.sh.resources.backoffice;

import lv.sh.dto.Device;
import lv.sh.service.device.DeviceServiceImpl;
import lv.sh.service.device.IDeviceService;

import javax.ws.rs.*;
import java.util.List;

@Path("device")
public class DeviceResource {

    @GET
    @Path("all")
    @Produces("application/json")
    public List<Device> getAll(){
        IDeviceService deviceService=new DeviceServiceImpl();
        return deviceService.getAllDevices();
    }

    @POST
    @Path("add")
    @Produces("application/json")
    public String post(Device device){
        IDeviceService deviceService=new DeviceServiceImpl();
        deviceService.addDevice(device);
        return "DONE";
    }
}
