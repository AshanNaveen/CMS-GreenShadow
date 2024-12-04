package lk.ant.cmsgreenshadow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lk.ant.cmsgreenshadow.customResponse.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.io.Serializable;
import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 11/23/2024
 * @project CMSGreenShadow
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldDto implements Serializable, Response {
    private String fieldCode;
    @NotBlank(message = "Field name cannot be blank")
    private String fieldName;
    @NotNull(message = "Location cannot be null")
    private Point location;
    @NotBlank(message = "Size cannot be blank")
    private Double size;
    private List<String> crops;
    private List<String> staff;
    @Size(max = 10485760, message = "Image 1 size exceeds maximum allowed length")
    private String image1;
    @Size(max = 10485760, message = "Image 2 size exceeds maximum allowed length")
    private String image2;
}
