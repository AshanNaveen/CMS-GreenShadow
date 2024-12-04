package lk.ant.cmsgreenshadow.service.impl;

import lk.ant.cmsgreenshadow.dto.UserDto;
import lk.ant.cmsgreenshadow.entity.StaffEntity;
import lk.ant.cmsgreenshadow.entity.UserEntity;
import lk.ant.cmsgreenshadow.exception.DataPersistFailedException;
import lk.ant.cmsgreenshadow.exception.NotFoundException;
import lk.ant.cmsgreenshadow.repository.StaffRepository;
import lk.ant.cmsgreenshadow.repository.UserRepository;
import lk.ant.cmsgreenshadow.service.UserService;
import lk.ant.cmsgreenshadow.util.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private MappingUtil mappingUtil;

    @Override
    public void saveUser(UserDto user) {
        user.setRole(UserDto.Role.valueOf(getUserRole(user.getEmail()).name()));
        UserEntity savedUser = userRepository.save(mappingUtil.toUserEntity(user));
        if (savedUser != null) {
            System.out.println("User saved successfully");
        } else {
            throw new DataPersistFailedException("User save unsuccessful");
        }
    }

    @Override
    public void updateUser(String email, UserDto user) {
        Optional<UserEntity> tmpUserEntity = userRepository.findByEmail(email);
        if (tmpUserEntity.isPresent()){
            UserEntity userEntity = mappingUtil.toUserEntity(user);
            tmpUserEntity.get().setPassword(userEntity.getPassword());
            System.out.println("User password updated successfully: " + tmpUserEntity.get());
        } else {
            throw new NotFoundException("User not found with email: " + email);
        }
    }

    @Override
    public boolean searchUser(String email) {
        Optional<StaffEntity> user = staffRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public boolean deleteUser(String email) {
        return false;
    }

    @Override
    public UserDto getUser(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isPresent()){
            UserDto userDto = mappingUtil.toUserDto(user.get());
            System.out.println("User returned successfully: " + userDto);
            return userDto;
        } else {
            throw new NotFoundException("User not found with email: " + email);
        }
    }

    private StaffEntity.Role getUserRole(String email) {
        return staffRepository.findByEmail(email)
                .map(StaffEntity::getRole)
                .orElseThrow(() ->
                        new NotFoundException("User not found for email: " + email));
    }
}
