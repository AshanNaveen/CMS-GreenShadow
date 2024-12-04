package lk.ant.cmsgreenshadow.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lk.ant.cmsgreenshadow.customResponse.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class CropDto implements Serializable, Response {
    private String cropCode;
    @NotBlank(message = "Common name cannot be blank")
    @Size(max = 100, message = "Common name must be at most 100 characters")
    private String commonName;
    @NotBlank(message = "Scientific name cannot be blank")
    @Size(max = 100, message = "Scientific name must be at most 100 characters")
    private String scientificName;
    @Size(max = 10485760, message = "Image size exceeds maximum allowed length")
    private String cropImage;
    @NotBlank(message = "Category cannot be blank")
    private String category;
    @NotBlank(message = "Season cannot be blank")
    private String season;
    @NotEmpty(message = "Fields cannot be empty")
    private List<String> fieldIds;
}
