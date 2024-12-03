package lk.ant.cmsgreenshadow.service;

import lk.ant.cmsgreenshadow.dto.VehicleDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
@Service
public interface VehicleService {
    void saveVehicle(VehicleDto vehicle);
    void updateVehicle(String id, VehicleDto vehicle);
    VehicleDto searchVehicle(String id);
    boolean deleteVehicle(String id);
    List<VehicleDto> getAllVehicles();
}
