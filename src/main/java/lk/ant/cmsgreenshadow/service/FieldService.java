package lk.ant.cmsgreenshadow.service;

import lk.ant.cmsgreenshadow.dto.FieldDto;

import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
public interface FieldService {
    void saveField(FieldDto field);
    void updateField(String id, FieldDto field);
    FieldDto searchField(String id);
    boolean deleteField(String id);
    List<FieldDto> getAllFields();
}
