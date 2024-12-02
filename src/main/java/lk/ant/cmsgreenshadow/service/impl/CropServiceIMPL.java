package lk.ant.cmsgreenshadow.service.impl;

import lk.ant.cmsgreenshadow.dto.CropDto;
import lk.ant.cmsgreenshadow.dto.FieldCropDto;
import lk.ant.cmsgreenshadow.entity.CropEntity;
import lk.ant.cmsgreenshadow.entity.FieldCropEntity;
import lk.ant.cmsgreenshadow.entity.FieldEntity;
import lk.ant.cmsgreenshadow.exception.NotFoundException;
import lk.ant.cmsgreenshadow.repository.CropRepository;
import lk.ant.cmsgreenshadow.repository.FieldCropRepository;
import lk.ant.cmsgreenshadow.repository.FieldRepository;
import lk.ant.cmsgreenshadow.service.CropService;
import lk.ant.cmsgreenshadow.util.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
@Service
public class CropServiceIMPL implements CropService {

    @Autowired
    private CropRepository cropRepository;
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private FieldCropRepository fieldCropRepository;
    @Autowired
    private MappingUtil mappingUtil;

    @Override
    public void saveCrop(CropDto crop) {
        String cropId = generateCropID();
        crop.setCropCode(cropId);
        CropEntity cropEntity = mappingUtil.toCropEntity(crop);
        cropRepository.save(cropEntity);
        for (String fieldId : crop.getFieldIds()) {
            FieldCropDto fieldCropDTO = new FieldCropDto(generateFieldCropID(), cropId, fieldId, Date.valueOf(LocalDate.now()));
            fieldCropRepository.save(mappingUtil.toFieldCropEntity(fieldCropDTO));
        }
    }

    @Override
    public void updateCrop(String id, CropDto crop) {
        Optional<CropEntity> tmpCropEntity = cropRepository.findById(id);
        if (tmpCropEntity.isPresent()) {
            CropEntity cropEntity = mappingUtil.toCropEntity(crop);
            tmpCropEntity.get().setCommonName(cropEntity.getCommonName());
            tmpCropEntity.get().setScientificName(cropEntity.getScientificName());
            tmpCropEntity.get().setCategory(cropEntity.getCategory());
            tmpCropEntity.get().setSeason(cropEntity.getSeason());
            tmpCropEntity.get().setCropImage(cropEntity.getCropImage());
            cropRepository.save(tmpCropEntity.get());
            fieldCropRepository.deleteByCrop_CropId(id);

            for (String fieldId : crop.getFieldIds()) {
                FieldCropDto fieldCropDTO = new FieldCropDto(generateFieldCropID(), id, fieldId, Date.valueOf(LocalDate.now()));
                fieldCropRepository.save(mappingUtil.toFieldCropEntity(fieldCropDTO));
            }
            System.out.println("Crop updated successfully: " + crop);
        } else {
            throw new NotFoundException("Crop not found with id: " + id);
        }
    }

    @Override
    public CropDto searchCrop(String id) {
        if (cropRepository.existsById(id)) {
            List<FieldCropEntity> byCropCropId = fieldCropRepository.findByCrop_CropId(id);
            CropDto cropDTO = mappingUtil.toCropDto(cropRepository.getReferenceById(id));
            cropDTO.setFieldIds(byCropCropId.stream().map(FieldCropEntity::getField).map(FieldEntity::getFieldId).collect(Collectors.toList()));
            return cropDTO;
        } else {
            throw new NotFoundException("Crop not found with id: " + id);
        }
    }

    @Override
    public boolean deleteCrop(String id) {
        if (cropRepository.existsById(id)) {
            cropRepository.deleteById(id);
            fieldCropRepository.deleteByCrop_CropId(id);
            System.out.println("Crop deleted successfully with id: " + id);
            return true;
        } else {
            throw new NotFoundException("Crop not found with id: " + id);
        }
    }

    @Override
    public List<CropDto> getAllCrops() {
        return cropRepository.findAll().stream().map(cropEntity -> {
            List<FieldCropEntity> byCropCropId = fieldCropRepository.findByCrop_CropId(cropEntity.getCropId());
            CropDto cropDTO = mappingUtil.toCropDto(cropEntity);
            cropDTO.setFieldIds(byCropCropId.stream().map(FieldCropEntity::getField).map(FieldEntity::getFieldId).collect(Collectors.toList()));
            return cropDTO;
        }).collect(Collectors.toList());
    }

    private String generateCropID() {
        if (cropRepository.count() == 0) {
            return "C001";
        } else {
            String lastId = cropRepository.findAll().get(cropRepository.findAll().size() - 1).getCropId();
            int newId = Integer.parseInt(lastId.substring(1)) + 1;
            if (newId < 10) {
                return "C00" + newId;
            } else if (newId < 100) {
                return "C0" + newId;
            } else {
                return "C" + newId;
            }
        }
    }

    private String generateFieldCropID() {
        if (fieldRepository.count() == 0) {
            return "FC001";
        } else {
            String lastId = fieldCropRepository.findAll().get(fieldCropRepository.findAll().size() - 1).getFieldCropId();
            int newId = Integer.parseInt(lastId.substring(2)) + 1;
            if (newId < 10) {
                return "FC00" + newId;
            } else if (newId < 100) {
                return "FC0" + newId;
            } else {
                return "FC" + newId;
            }
        }
    }
}
