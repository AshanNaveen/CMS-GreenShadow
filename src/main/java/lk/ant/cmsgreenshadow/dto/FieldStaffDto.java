package lk.ant.cmsgreenshadow.dto;

import lk.ant.cmsgreenshadow.customResponse.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Naveen Theekshana
 * @date 12/1/2024
 * @project CMSGreenShadow
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldStaffDto implements Serializable, Response {
    private String fieldStaffId;
    private String field;
    private String staffId;
    private Date assignedDate;
}
