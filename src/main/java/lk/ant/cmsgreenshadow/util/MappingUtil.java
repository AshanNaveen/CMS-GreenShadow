package lk.ant.cmsgreenshadow.util;

import lk.ant.cmsgreenshadow.dto.*;
import lk.ant.cmsgreenshadow.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 11/23/2024
 * @project CMSGreenShadow
 */
@Component
public class MappingUtil {
    @Autowired
    private ModelMapper modelMapper;

    // **** Crop Entity - Crop Dto

    public CropDto toCropDto(CropEntity entity) {
        return modelMapper.map(entity, CropDto.class);
    }

    public CropEntity toCropEntity(CropDto dto) {
        return modelMapper.map(dto, CropEntity.class);
    }

    public List<CropDto> toCropDtoList(List<CropEntity> entityList) {
        return modelMapper.map(entityList, new TypeToken<List<CropDto>>(){}.getType());
    }

    public List<CropEntity> toCropEntityList(List<CropDto> dtoList) {
        return modelMapper.map(dtoList,new TypeToken<List<CropEntity>>(){}.getType());
    }

    //*****************

    // **** Equipment Entity - Equipment Dto

    public EquipmentDto toEquipmentDto(EquipmentEntity entity) {
        return modelMapper.map(entity, EquipmentDto.class);
    }

    public EquipmentEntity toEquipmentEntity(EquipmentDto dto) {
        return modelMapper.map(dto, EquipmentEntity.class);
    }

    public List<EquipmentDto> toEquipmentDtoList(List<EquipmentEntity> entityList) {
        return modelMapper.map(entityList, new TypeToken<List<EquipmentEntity>>(){}.getType());
    }

    public List<EquipmentEntity> toEquipmentEntityList(List<EquipmentDto> dtoList) {
        return modelMapper.map(dtoList,new TypeToken<List<EquipmentEntity>>(){}.getType());
    }

    //*****************

    // **** Field Entity - Field Dto

    public FieldDto toFieldDto(EquipmentEntity entity) {
        return modelMapper.map(entity, FieldDto.class);
    }

    public EquipmentEntity toEquipmentEntity(FieldDto dto) {
        return modelMapper.map(dto, EquipmentEntity.class);
    }

    public List<FieldDto> toFieldDtoList(List<EquipmentEntity> entityList) {
        return modelMapper.map(entityList, new TypeToken<List<FieldDto>>(){}.getType());
    }

    public List<FieldDto> toFieldEntityList(List<FieldDto> dtoList) {
        return modelMapper.map(dtoList,new TypeToken<List<EquipmentEntity>>(){}.getType());
    }

    //*****************

    // **** Log Entity - Log Dto

    public LogDto toLogDto(LogEntity entity) {
        return modelMapper.map(entity, LogDto.class);
    }

    public LogEntity toEquipmentEntity(LogDto dto) {
        return modelMapper.map(dto, LogEntity.class);
    }

    public List<LogDto> toLogDtoList(List<LogEntity> entityList) {
        return modelMapper.map(entityList, new TypeToken<List<LogDto>>(){}.getType());
    }

    public List<LogEntity> toLogEntityList(List<LogDto> dtoList) {
        return modelMapper.map(dtoList,new TypeToken<List<LogEntity>>(){}.getType());
    }

    //*****************

    // **** Staff Entity - Staff Dto

    public StaffDto toStaffDto(StaffEntity entity) {
        return modelMapper.map(entity, StaffDto.class);
    }

    public StaffEntity toStaffEntity(StaffDto dto) {
        return modelMapper.map(dto, StaffEntity.class);
    }

    public List<StaffDto> toStaffDtoList(List<StaffEntity> entityList) {
        return modelMapper.map(entityList, new TypeToken<List<StaffDto>>(){}.getType());
    }

    public List<StaffEntity> toStaffEntityList(List<StaffDto> dtoList) {
        return modelMapper.map(dtoList,new TypeToken<List<StaffEntity>>(){}.getType());
    }

    //*****************

    // **** User Entity - User Dto

    public UserDto toUserDto(UserEntity entity) {
        return modelMapper.map(entity, UserDto.class);
    }

    public UserEntity toUserEntity(UserDto dto) {
        return modelMapper.map(dto, UserEntity.class);
    }

    public List<UserDto> toUserDtoList(List<UserEntity> entityList) {
        return modelMapper.map(entityList, new TypeToken<List<UserDto>>(){}.getType());
    }

    public List<UserEntity> toUserEntityList(List<UserDto> dtoList) {
        return modelMapper.map(dtoList,new TypeToken<List<UserEntity>>(){}.getType());
    }

    //*****************

    // **** Vehicle Entity - Vehicle Dto

    public VehicleDto toVehicleDto(VehicleEntity entity) {
        return modelMapper.map(entity, VehicleDto.class);
    }

    public VehicleEntity toVehicleEntity(VehicleDto dto) {
        return modelMapper.map(dto, VehicleEntity.class);
    }

    public List<VehicleDto> toVehicleDtoList(List<VehicleEntity> entityList) {
        return modelMapper.map(entityList, new TypeToken<List<VehicleDto>>(){}.getType());
    }

    public List<VehicleEntity> toVehicleEntityList(List<VehicleDto> dtoList) {
        return modelMapper.map(dtoList,new TypeToken<List<VehicleEntity>>(){}.getType());
    }

    //*****************
}
