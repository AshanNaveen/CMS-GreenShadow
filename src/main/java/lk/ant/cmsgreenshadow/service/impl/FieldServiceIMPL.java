package lk.ant.cmsgreenshadow.service.impl;

import lk.ant.cmsgreenshadow.dto.FieldDto;
import lk.ant.cmsgreenshadow.dto.FieldStaffDto;
import lk.ant.cmsgreenshadow.entity.EquipmentEntity;
import lk.ant.cmsgreenshadow.entity.FieldEntity;
import lk.ant.cmsgreenshadow.entity.FieldStaffEntity;
import lk.ant.cmsgreenshadow.entity.StaffEntity;
import lk.ant.cmsgreenshadow.exception.NotFoundException;
import lk.ant.cmsgreenshadow.repository.FieldRepository;
import lk.ant.cmsgreenshadow.repository.FieldStaffRepository;
import lk.ant.cmsgreenshadow.service.FieldService;
import lk.ant.cmsgreenshadow.util.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
@Service
public class FieldServiceIMPL implements FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private FieldStaffRepository fieldStaffRepository;

    @Autowired
    private MappingUtil mappingUtil;


    @Override
    public void saveField(FieldDto field) {
        String fieldId = generateFieldID();
        field.setFieldCode(fieldId);
        FieldEntity fieldEntity = mappingUtil.toFieldEntity(field);
        fieldRepository.save(fieldEntity);

        for (String staffId : field.getStaff()) {
            FieldStaffDto fieldStaffDTO = new FieldStaffDto();
            fieldStaffDTO.setFieldStaffId(generateFieldStaffID());
            fieldStaffDTO.setField(fieldId);
            fieldStaffDTO.setStaffId(staffId);
            fieldStaffDTO.setAssignedDate(Date.valueOf(LocalDate.now()));
            FieldStaffEntity fieldStaffEntity = mappingUtil.toFieldStaffEntity(fieldStaffDTO);
            fieldStaffRepository.save(fieldStaffEntity);
        }
    }

    @Override
    public void updateField(String id, FieldDto field) {
        FieldEntity existingFieldEntity = fieldRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Field not found with id: " + id));
        existingFieldEntity.setFieldName(field.getFieldName());
        existingFieldEntity.setLocation(field.getLocation());
        existingFieldEntity.setSize(field.getSize());
        existingFieldEntity.setImage1(field.getImage1());
        existingFieldEntity.setImage2(field.getImage2());

        fieldRepository.save(existingFieldEntity);
        updateFieldStaffAssignments(id, field.getStaff());
        System.out.println("Field updated successfully: " + field);

    }

    @Override
    public FieldDto searchField(String id) {
        if (fieldRepository.existsById(id)) {
            List<FieldStaffEntity> byFieldFieldId = fieldStaffRepository.findByField_FieldId(id);
            FieldDto fieldDTO = mappingUtil.toFieldDto(fieldRepository.getReferenceById(id));
            fieldDTO.setStaff(byFieldFieldId.stream().map(FieldStaffEntity::getStaff).map(StaffEntity::getStaffId).collect(Collectors.toList()));
            return fieldDTO;
        } else {
            throw new NotFoundException("Field not found with id: " + id);
        }
    }

    @Override
    public boolean deleteField(String id) {
        if (fieldRepository.existsById(id)) {
            fieldRepository.deleteById(id);
            fieldStaffRepository.deleteByField_FieldId(id);
            System.out.println("Field deleted successfully with id: " + id);
            return true;
        } else {
            throw new NotFoundException("Field not found with id: " + id);
        }
    }

    @Override
    public List<FieldDto> getAllFields() {
        return List.of();
    }

    private String generateFieldID() {
        if (fieldRepository.count() == 0) {
            return "F001";
        } else {
            String lastId = fieldRepository.findAll().get(fieldRepository.findAll().size() - 1).getFieldId();
            int newId = Integer.parseInt(lastId.substring(1)) + 1;
            if (newId < 10) {
                return "F00" + newId;
            } else if (newId < 100) {
                return "F0" + newId;
            } else {
                return "F" + newId;
            }
        }
    }

    private String generateFieldStaffID() {
        if (fieldStaffRepository.count() == 0) {
            return "FS001";
        } else {
            String lastId = fieldStaffRepository.findAll().get(fieldStaffRepository.findAll().size() - 1).getFieldStaffId();
            int newId = Integer.parseInt(lastId.substring(2)) + 1;
            if (newId < 10) {
                return "FS00" + newId;
            } else if (newId < 100) {
                return "FS0" + newId;
            } else {
                return "FS" + newId;
            }
        }
    }

    private void updateFieldStaffAssignments(String fieldId, List<String> staffIds) {
        fieldStaffRepository.deleteByField_FieldId(fieldId);

        for (String staffId : staffIds) {
            FieldStaffDto fieldStaffDTO = new FieldStaffDto();
            fieldStaffDTO.setFieldStaffId(generateFieldStaffID());
            fieldStaffDTO.setField(fieldId);
            fieldStaffDTO.setStaffId(staffId);
            fieldStaffDTO.setAssignedDate(Date.valueOf(LocalDate.now()));
            FieldStaffEntity fieldStaffEntity = mappingUtil.toFieldStaffEntity(fieldStaffDTO);
            fieldStaffRepository.save(fieldStaffEntity);
        }
    }
}
