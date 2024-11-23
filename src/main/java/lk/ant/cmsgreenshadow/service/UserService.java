package lk.ant.cmsgreenshadow.service;

import lk.ant.cmsgreenshadow.dto.UserDto;

/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
public interface UserService {
    void saveUser(UserDto user);
    void updateUser(String email, UserDto user);
    boolean searchUser(String email);
    boolean deleteUser(String email);
}
