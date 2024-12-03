package lk.ant.cmsgreenshadow.service;

import lk.ant.cmsgreenshadow.dto.UserDto;
import org.springframework.stereotype.Service;

/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
@Service
public interface UserService {
    void saveUser(UserDto user);
    void updateUser(String email, UserDto user);
    boolean searchUser(String email);
    boolean deleteUser(String email);
}
