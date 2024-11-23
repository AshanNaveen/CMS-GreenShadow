package lk.ant.cmsgreenshadow.repository;

import lk.ant.cmsgreenshadow.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Naveen Theekshana
 * @date 11/21/2024
 * @project CMSGreenShadow
 */
public interface UserRepository extends JpaRepository<UserEntity,String> {
}
