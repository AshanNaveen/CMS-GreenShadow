package lk.ant.cmsgreenshadow.repository;

import lk.ant.cmsgreenshadow.entity.FieldCropEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 12/1/2024
 * @project CMSGreenShadow
 */
public interface FieldCropRepository extends JpaRepository<FieldCropEntity,String> {
    void deleteByCrop_CropId(String cropCropId);
    List<FieldCropEntity> findByCrop_CropId(String cropCropId);
}
