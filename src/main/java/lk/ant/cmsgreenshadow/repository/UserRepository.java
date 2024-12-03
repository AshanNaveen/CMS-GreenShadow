package lk.ant.cmsgreenshadow.repository;

import lk.ant.cmsgreenshadow.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Naveen Theekshana
 * @date 11/21/2024
 * @project CMSGreenShadow
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
}
