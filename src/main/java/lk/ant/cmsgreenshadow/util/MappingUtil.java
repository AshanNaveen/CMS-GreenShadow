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
    // **** Field_Crop Entity - Field_Crop Dto

    public FieldCropDto toFieldCropDto(FieldCropEntity entity) {
        return modelMapper.map(entity, FieldCropDto.class);
    }

    public FieldCropEntity toFieldCropEntity(FieldCropDto dto) {
        return modelMapper.map(dto, FieldCropEntity.class);
    }

    public List<FieldCropDto> toFieldCropDtoList(List<FieldCropEntity> entityList) {
        return modelMapper.map(entityList, new TypeToken<List<FieldCropDto>>(){}.getType());
    }

    public List<FieldCropEntity> toFieldCropEntityList(List<FieldCropDto> dtoList) {
        return modelMapper.map(dtoList,new TypeToken<List<FieldCropEntity>>(){}.getType());
    }

    //*****************

    // **** Field_Crop Entity - Field_Crop Dto

    public FieldStaffDto toFieldStaffDto(FieldStaffEntity entity) {
        return modelMapper.map(entity, FieldStaffDto.class);
    }

    public FieldStaffEntity toFieldStaffEntity(FieldStaffDto dto) {
        return modelMapper.map(dto, FieldStaffEntity.class);
    }

    public List<FieldStaffDto> toFieldStaffDtoList(List<FieldStaffEntity> entityList) {
        return modelMapper.map(entityList, new TypeToken<List<FieldStaffDto>>(){}.getType());
    }

    public List<FieldStaffEntity> toFieldStaffEntityList(List<FieldStaffDto> dtoList) {
        return modelMapper.map(dtoList,new TypeToken<List<FieldStaffEntity>>(){}.getType());
    }

    //*****************

    // **** Field_Crop Entity - Field_Crop Dto

    public StaffLogDto toStaffLogDto(StaffLogEntity entity) {
        return modelMapper.map(entity, StaffLogDto.class);
    }

    public StaffLogEntity toStaffLogEntity(StaffLogDto dto) {
        return modelMapper.map(dto, StaffLogEntity.class);
    }

    public List<StaffLogDto> toStaffLogDtoList(List<StaffLogEntity> entityList) {
        return modelMapper.map(entityList, new TypeToken<List<StaffLogDto>>(){}.getType());
    }

    public List<StaffLogEntity> toStaffLogEntity(List<StaffLogDto> dtoList) {
        return modelMapper.map(dtoList,new TypeToken<List<StaffLogEntity>>(){}.getType());
    }

    //*****************
}
