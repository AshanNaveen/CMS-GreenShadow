package lk.ant.cmsgreenshadow.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lk.ant.cmsgreenshadow.customResponse.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDto implements Serializable, Response {
    private String logId;
    private String logDate;
    @NotBlank(message = "Details cannot be blank")
    @Size(max = 255, message = "Details must be at most 255 characters")
    private String observation;
    @Size(max = 10485760, message = "Image size exceeds maximum allowed length")
    private String observedImage;
    @NotEmpty(message = "Fields list cannot be empty")
    private String field;
    @NotEmpty(message = "Crops list cannot be empty")
    private String crop;
    private List<String> staff;
}
