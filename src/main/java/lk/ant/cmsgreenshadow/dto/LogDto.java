package lk.ant.cmsgreenshadow.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class LogDto {
    private String logId;
    private Date logDate;
    private String observation;
    private String observedImage;
    private List fields;
    private List crops;
    private List staff;
}
