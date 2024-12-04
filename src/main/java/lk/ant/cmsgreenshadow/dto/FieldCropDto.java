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
public class FieldCropDto implements Serializable, Response {
    private String fieldCropId;
    @NotEmpty(message = "Crop IDs cannot be empty")
    private String field;
    @NotEmpty(message = "Field IDs cannot be empty")
    private String crop;
    private Date assignedDate;
}
