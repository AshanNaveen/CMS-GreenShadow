package lk.ant.cmsgreenshadow.repository;

import lk.ant.cmsgreenshadow.entity.FieldStaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Naveen Theekshana
 * @date 12/1/2024
 * @project CMSGreenShadow
 */
@Repository
public interface FieldStaffRepository extends JpaRepository<FieldStaffEntity,String> {
}
