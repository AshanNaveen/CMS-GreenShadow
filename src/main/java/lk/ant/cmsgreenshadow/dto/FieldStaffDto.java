package lk.ant.cmsgreenshadow.dto;

import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Field ID cannot be empty")
    private String field;
    @NotEmpty(message = "Staff ID cannot be empty")
    private String staffId;
    private Date assignedDate;
}
