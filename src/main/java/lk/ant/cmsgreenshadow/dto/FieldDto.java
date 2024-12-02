package lk.ant.cmsgreenshadow.dto;

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
    private String fieldName;
    private Point location;
    private Double size;
    private List crops;
    private List staff;
    private String image1;
    private String image2;
}
