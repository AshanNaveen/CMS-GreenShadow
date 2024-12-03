package lk.ant.cmsgreenshadow.service;

import lk.ant.cmsgreenshadow.dto.StaffDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
@Service
public interface StaffService {
    void saveStaff(StaffDto staff);
    void updateStaff(String id, StaffDto staff);
    boolean deleteStaff(String id);
    StaffDto searchStaff(String id);
    List<StaffDto> getAllStaffs();
}
