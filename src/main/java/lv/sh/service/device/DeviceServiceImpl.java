package lv.sh.service.device;

import lv.sh.dto.Device;
import lv.sh.repository.DeviceRepository;

import java.util.List;

public class DeviceServiceImpl implements IDeviceService {

    private DeviceRepository deviceRepository=DeviceRepository.getInstance();

    @Override
    public Device addDevice(Device device) {
        deviceRepository.insertDevice(device);
        return null;
    }

    @Override
    public List<Device> getAllDevices() {
        return deviceRepository.getAllDevices();
    }
}
