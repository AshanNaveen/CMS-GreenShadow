package lk.ant.cmsgreenshadow.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 11/23/2024
 * @project CMSGreenShadow
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CropDto {
    private String cropCode;
    private String commonName;
    private String scientificName;
    private String cropImage;
    private String category;
    private String season;
    private List<String> fieldIds;
}
