package lk.ant.cmsgreenshadow.service.impl;

import lk.ant.cmsgreenshadow.dto.VehicleDto;
import lk.ant.cmsgreenshadow.entity.VehicleEntity;
import lk.ant.cmsgreenshadow.exception.NotFoundException;
import lk.ant.cmsgreenshadow.repository.VehicleRepository;
import lk.ant.cmsgreenshadow.service.VehicleService;
import lk.ant.cmsgreenshadow.util.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
@Service
public class VehicleServiceIMPL implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private MappingUtil mappingUtil;

    @Override
    public void saveVehicle(VehicleDto vehicle) {
        vehicle.setVehicleId(generateVehicleID());
        vehicleRepository.save(mappingUtil.toVehicleEntity(vehicle));
        System.out.println("Vehicle saved successfully: " + vehicle);
    }

    @Override
    public void updateVehicle(String id, VehicleDto vehicle) {
        Optional<VehicleEntity> tmpVehicle = vehicleRepository.findById(id);
        if (tmpVehicle.isPresent()) {
            VehicleEntity vehicleEntity = mappingUtil.toVehicleEntity(vehicle);
            tmpVehicle.get().setCategory(vehicleEntity.getCategory());
            tmpVehicle.get().setLicensePlate(vehicleEntity.getLicensePlate());
            tmpVehicle.get().setFuelType(vehicleEntity.getFuelType());
            tmpVehicle.get().setStatus(vehicleEntity.getStatus());
            tmpVehicle.get().setRemarks(vehicleEntity.getRemarks());
            System.out.println("Vehicle updated successfully: " + tmpVehicle.get());
        } else {
            throw new NotFoundException("Vehicle not found with id: " + id);
        }
    }

    @Override
    public VehicleDto searchVehicle(String id) {
        if (vehicleRepository.existsById(id)) {
            return mappingUtil.toVehicleDto(vehicleRepository.getReferenceById(id));
        } else {
            throw new NotFoundException("Vehicle not found with id: " + id);
        }
    }

    @Override
    public boolean deleteVehicle(String id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return true;
        } else {
            throw new NotFoundException("Vehicle not found with id: " + id);
        }
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        return mappingUtil.toVehicleDtoList(vehicleRepository.findAll());
    }

    private String generateVehicleID() {
        if (vehicleRepository.count() == 0) {
            return "V001";
        } else {
            String id = vehicleRepository.findAll().get(vehicleRepository.findAll().size() - 1).getVehicleId();
            int i = Integer.parseInt(id.replace("V", "")) + 1;
            if (i < 10) {
                return "V00" + i;
            } else if (i < 100) {
                return "V0" + i;
            } else {
                return "V" + i;
            }
        }
    }
}
