package lk.ant.cmsgreenshadow.repository;

import lk.ant.cmsgreenshadow.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Naveen Theekshana
 * @date 11/21/2024
 * @project CMSGreenShadow
 */
@Repository
public interface StaffRepository extends JpaRepository<StaffEntity,String> {
    Optional<StaffEntity> findByEmail(String email);
}
