package lv.sh.service.device;

import lv.sh.dto.Device;

import java.util.List;

public interface IDeviceService {

    /**
     * Creates new device entity in DB.
     * @param device
     * @return
     */
    public Device addDevice(Device device);
    public List<Device> getAllDevices();
}
