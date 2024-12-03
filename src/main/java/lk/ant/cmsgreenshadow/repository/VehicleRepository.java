package lk.ant.cmsgreenshadow.repository;

import lk.ant.cmsgreenshadow.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Naveen Theekshana
 * @date 11/21/2024
 * @project CMSGreenShadow
 */
@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity,String> {
}
