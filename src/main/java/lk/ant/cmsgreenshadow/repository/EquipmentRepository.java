package lk.ant.cmsgreenshadow.repository;

import lk.ant.cmsgreenshadow.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Naveen Theekshana
 * @date 11/16/2024
 * @project CMSGreenShadow
 */
@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentEntity,String> {
}
