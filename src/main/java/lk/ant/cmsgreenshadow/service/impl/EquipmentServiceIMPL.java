package lk.ant.cmsgreenshadow.service.impl;

import lk.ant.cmsgreenshadow.dto.EquipmentDto;
import lk.ant.cmsgreenshadow.entity.EquipmentEntity;
import lk.ant.cmsgreenshadow.exception.NotFoundException;
import lk.ant.cmsgreenshadow.repository.EquipmentRepository;
import lk.ant.cmsgreenshadow.service.EquipmentService;
import lk.ant.cmsgreenshadow.util.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
@Service
public class EquipmentServiceIMPL implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private MappingUtil mappingUtil;

    @Override
    public void saveEquipment(EquipmentDto equipment) {
        equipment.setEquipmentId(generateEquipmentID());
        equipmentRepository.save(mappingUtil.toEquipmentEntity(equipment));
    }

    @Override
    public void updateEquipment(String id, EquipmentDto equipment) {
        Optional<EquipmentEntity> tmpEquipment = equipmentRepository.findById(id);
        if (tmpEquipment.isPresent()) {
            EquipmentEntity equipmentEntity = mappingUtil.toEquipmentEntity(equipment);
            tmpEquipment.get().setName(equipmentEntity.getName());
            tmpEquipment.get().setStatus(equipmentEntity.getStatus());
            tmpEquipment.get().setType(equipmentEntity.getType());
            tmpEquipment.get().setStaff(equipmentEntity.getStaff());
            tmpEquipment.get().setField(equipmentEntity.getField());
            System.out.println("Equipment updated successfully: " + tmpEquipment.get());
        } else {
            throw new NotFoundException("Equipment not found with id: " + id);
        }
    }

    @Override
    public EquipmentDto searchEquipment(String id) {
        if (equipmentRepository.existsById(id)) {
            EquipmentDto equipmentDTO = mappingUtil.toEquipmentDto(equipmentRepository.getReferenceById(id));
            System.out.println("Equipment searched successfully: " + equipmentDTO);
            return equipmentDTO;
        } else {
            throw new NotFoundException("Equipment not found with id: " + id);
        }
    }

    @Override
    public boolean deleteEquipment(String id) {
        if (equipmentRepository.existsById(id)) {
            equipmentRepository.deleteById(id);
            System.out.println("Equipment deleted successfully: " + id);
            return true;
        } else {
            throw new NotFoundException("Equipment not found with id: " + id);
        }
    }

    @Override
    public List<EquipmentDto> getAllEquipments() {
        return mappingUtil.toEquipmentDtoList(equipmentRepository.findAll());
    }

    private String generateEquipmentID() {
        if (equipmentRepository.count() == 0) {
            return "E001";
        } else {
            String lastId = equipmentRepository.findAll().get(equipmentRepository.findAll().size() - 1).getEuqipmentId();
            int newId = Integer.parseInt(lastId.substring(1)) + 1;
            if (newId < 10) {
                return "E00" + newId;
            } else if (newId < 100) {
                return "E0" + newId;
            } else {
                return "E" + newId;
            }
        }
    }
}
