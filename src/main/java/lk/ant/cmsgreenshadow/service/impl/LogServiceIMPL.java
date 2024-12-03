package lk.ant.cmsgreenshadow.service.impl;

import lk.ant.cmsgreenshadow.dto.FieldDto;
import lk.ant.cmsgreenshadow.dto.LogDto;
import lk.ant.cmsgreenshadow.dto.StaffLogDto;
import lk.ant.cmsgreenshadow.entity.FieldStaffEntity;
import lk.ant.cmsgreenshadow.entity.LogEntity;
import lk.ant.cmsgreenshadow.entity.StaffEntity;
import lk.ant.cmsgreenshadow.entity.StaffLogEntity;
import lk.ant.cmsgreenshadow.exception.NotFoundException;
import lk.ant.cmsgreenshadow.repository.*;
import lk.ant.cmsgreenshadow.service.LogService;
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
public class LogServiceIMPL implements LogService {

    @Autowired
    private LogServiceRepository logRepository;

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private FieldStaffRepository fieldStaffRepository;

    @Autowired
    private StaffLogRepository staffLogRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private MappingUtil mappingUtil;



    @Override
    public void saveLog(LogDto log) {
        String logId = generateLogID();
        log.setLogId(logId);
        log.setLogDate(Date.valueOf(LocalDate.now()));
        LogEntity logEntity = mappingUtil.toLogEntity(log);
        logRepository.save(logEntity);

        List<FieldStaffEntity> byFieldFieldId = fieldStaffRepository.findByField_FieldId(log.getField());
        FieldDto fieldDTO = mappingUtil.toFieldDto(fieldRepository.getReferenceById(log.getField()));
        fieldDTO.setStaff(byFieldFieldId.stream()
                .map(FieldStaffEntity::getStaff)
                .map(StaffEntity::getStaffId)
                .collect(Collectors.toList()));

        for (String staffId : fieldDTO.getStaff()) {
            StaffLogDto staffLogDTO = new StaffLogDto();
            staffLogDTO.setStaffLogId(generateStaffLogID());
            staffLogDTO.setLogId(logId);
            staffLogDTO.setStaffId(staffId);
            StaffLogEntity staffLogEntity = staffLogConvertToEntity(staffLogDTO);
            staffLogRepository.save(staffLogEntity);

            System.out.println("Staff Log saved for staff: " + staffId);
        }

        System.out.println("Log saved successfully: " + log);

    }

    @Override
    public void updateLog(String id, LogDto log) {
        Optional<LogEntity> tmpLogEntity = logRepository.findById(id);
        if (tmpLogEntity.isPresent()) {
            LogEntity logEntity = mappingUtil.toLogEntity(log);
            tmpLogEntity.get().setObservation(logEntity.getObservation());
            tmpLogEntity.get().setObservedImage(logEntity.getObservedImage());
            tmpLogEntity.get().setField(logEntity.getField());
            tmpLogEntity.get().setCrop(logEntity.getCrop());

            logRepository.save(tmpLogEntity.get()); // Save the updated log details

            staffLogRepository.deleteByLog_LogId(id); // Delete old staff logs for this log

            List<FieldStaffEntity> fieldStaffEntities = fieldStaffRepository.findByField_FieldId(log.getField());
            List<String> staffIds = fieldStaffEntities.stream()
                    .map(FieldStaffEntity::getStaff)
                    .map(StaffEntity::getStaffId)
                    .collect(Collectors.toList());
            for (String staffId : staffIds) {
                StaffLogDto staffLogDTO = new StaffLogDto();
                staffLogDTO.setLogId(id);
                staffLogDTO.setStaffId(staffId);

                // Manually assign an ID if necessary
                StaffLogEntity staffLogEntity = staffLogConvertToEntity(staffLogDTO);
                staffLogEntity.setStaffLogId(generateStaffLogID());

                staffLogRepository.save(staffLogEntity);
            }

            System.out.println("Log updated successfully: " + log);
        } else {
            System.out.println("Log not found with id: " + id);
            throw new NotFoundException("Log not found with id: " + id);
        }
    }

    @Override
    public LogDto searchLog(String id) {
        if (logRepository.existsById(id)) {
            List<StaffLogEntity> byLogEntityLogId = staffLogRepository.findByLog_LogId(id);
            LogDto logDTO = mappingUtil.toLogDto(logRepository.getReferenceById(id));
            logDTO.setStaff(byLogEntityLogId.stream().map(StaffLogEntity::getStaff).map(StaffEntity::getStaffId).collect(Collectors.toList()));
            return logDTO;
        } else {
            System.out.println("Log not found with id: " + id);
            throw new NotFoundException("Log not found with id: " + id);
        }
    }

    @Override
    public boolean deleteLog(String id) {
        if (logRepository.existsById(id)) {
            logRepository.deleteById(id);
            staffLogRepository.deleteByLog_LogId(id);
            System.out.println("Log deleted successfully with id: " + id);
            return true;
        } else {
            System.out.println("Log not found with id: " + id);
            throw new NotFoundException("Log not found with id: " + id);
        }
    }

    @Override
    public List<LogDto> getAllLogs() {
        return logRepository.findAll().stream().map(logEntity -> {
            List<StaffLogEntity> byLogEntityLogId = staffLogRepository.findByLog_LogId(logEntity.getLogId());
            LogDto logDTO = mappingUtil.toLogDto(logEntity);
            logDTO.setStaff(byLogEntityLogId.stream().map(StaffLogEntity::getStaff).map(StaffEntity::getStaffId).collect(Collectors.toList()));
            return logDTO;
        }).collect(Collectors.toList());
    }

    private String generateLogID() {
        if (logRepository.count() == 0) {
            return "L001";
        } else {
            String lastId = logRepository.findAll().get(logRepository.findAll().size() - 1).getLogId();
            int newId = Integer.parseInt(lastId.substring(1)) + 1;
            if (newId < 10) {
                return "L00" + newId;
            } else if (newId < 100) {
                return "L0" + newId;
            } else {
                return "L" + newId;
            }
        }
    }

    private String generateStaffLogID() {
        if (staffLogRepository.count() == 0) {
            return "SL001";
        } else {
            String lastId = staffLogRepository.findAll().get(staffLogRepository.findAll().size() - 1).getStaffLogId();
            int newId = Integer.parseInt(lastId.substring(2)) + 1;
            if (newId < 10) {
                return "SL00" + newId;
            } else if (newId < 100) {
                return "SL0" + newId;
            } else {
                return "SL" + newId;
            }
        }
    }

    public StaffLogEntity staffLogConvertToEntity(StaffLogDto staffLogDTO) {
        StaffLogEntity staffLogEntity = new StaffLogEntity();
        staffLogEntity.setStaffLogId(staffLogDTO.getStaffLogId());

        if (staffLogDTO.getStaffId() != null) {
            StaffEntity staffEntity = staffRepository.getReferenceById(staffLogDTO.getStaffId());
            staffLogEntity.setStaff(staffEntity);
        }

        if (staffLogDTO.getLogId() != null) {
            LogEntity logEntity = logRepository.getReferenceById(staffLogDTO.getLogId());
            staffLogEntity.setLog(logEntity);
        }

        return staffLogEntity;
    }
}
