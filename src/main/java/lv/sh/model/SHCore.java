package lv.sh.model;

import lv.sh.dto.Device;
import lv.sh.repository.DeviceRepository;

import java.util.List;

public class SHCore {

    private static List<Device> devices=null;

    public static List<Device> getDevices(){
        if (devices==null) devices= DeviceRepository.getInstance().getAllDevices();
        return devices;
    }

}
