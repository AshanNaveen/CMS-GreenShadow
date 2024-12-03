package lk.ant.cmsgreenshadow.service.impl;

import lk.ant.cmsgreenshadow.dto.StaffDto;
import lk.ant.cmsgreenshadow.entity.StaffEntity;
import lk.ant.cmsgreenshadow.exception.NotFoundException;
import lk.ant.cmsgreenshadow.repository.StaffRepository;
import lk.ant.cmsgreenshadow.service.StaffService;
import lk.ant.cmsgreenshadow.util.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
@Service
public class StaffServiceIMPL implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private MappingUtil mappingUtil;

    @Override
    public void saveStaff(StaffDto staff) {
        staff.setStaffId(generateStaffID());
        staff.setJoinedDate(Date.valueOf(LocalDate.now()));
        StaffEntity staffEntity = mappingUtil.toStaffEntity(staff);
        staffRepository.save(staffEntity);
        System.out.println("Staff saved successfully: " + staffEntity);
    }

    @Override
    public void updateStaff(String id, StaffDto staff) {
        Optional<StaffEntity> tmpStaffEntity = staffRepository.findById(id);
        if (tmpStaffEntity.isPresent()) {
            StaffEntity staffEntity = mappingUtil.toStaffEntity(staff);
            tmpStaffEntity.get().setFirstName(staffEntity.getFirstName());
            tmpStaffEntity.get().setLastName(staffEntity.getLastName());
            tmpStaffEntity.get().setDesignation(staffEntity.getDesignation());
            tmpStaffEntity.get().setGender(staffEntity.getGender());
            tmpStaffEntity.get().setDateOfBirth(staffEntity.getDateOfBirth());
            tmpStaffEntity.get().setAddressLine1(staffEntity.getAddressLine1());
            tmpStaffEntity.get().setAddressLine2(staffEntity.getAddressLine2());
            tmpStaffEntity.get().setAddressLine3(staffEntity.getAddressLine3());
            tmpStaffEntity.get().setAddressLine4(staffEntity.getAddressLine4());
            tmpStaffEntity.get().setAddressLine5(staffEntity.getAddressLine5());
            tmpStaffEntity.get().setContactNo(staffEntity.getContactNo());
            tmpStaffEntity.get().setEmail(staffEntity.getEmail());
            tmpStaffEntity.get().setRole(staffEntity.getRole());
            tmpStaffEntity.get().setVehicleId(staffEntity.getVehicleId());
            System.out.println("Staff updated successfully: " + tmpStaffEntity.get());
        } else {
            throw new NotFoundException("Staff not found with id: " + id);
        }
    }

    @Override
    public boolean deleteStaff(String id) {
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
            System.out.println("Staff deleted successfully: " + id);
            return true;
        } else {
            throw new NotFoundException("Staff not found with id: " + id);
        }
    }

    @Override
    public StaffDto searchStaff(String id) {
        if (staffRepository.existsById(id)) {
            return mappingUtil.toStaffDto(staffRepository.getReferenceById(id));
        } else {
            throw new NotFoundException("Staff not found with id: " + id);
        }
    }

    @Override
    public List<StaffDto> getAllStaffs() {
        return mappingUtil.toStaffDtoList(staffRepository.findAll());
    }

    private String generateStaffID() {
        if (staffRepository.count() == 0) {
            return "S001";
        } else {
            String lastId = staffRepository.findAll().get(staffRepository.findAll().size() - 1).getStaffId();
            int newId = Integer.parseInt(lastId.substring(1)) + 1;
            if (newId < 10) {
                return "S00" + newId;
            } else if (newId < 100) {
                return "S0" + newId;
            } else {
                return "S" + newId;
            }
        }
    }
}
