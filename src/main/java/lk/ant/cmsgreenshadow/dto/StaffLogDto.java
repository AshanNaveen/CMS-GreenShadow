package lk.ant.cmsgreenshadow.dto;

import lk.ant.cmsgreenshadow.customResponse.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Naveen Theekshana
 * @date 12/1/2024
 * @project CMSGreenShadow
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StaffLogDto implements Serializable, Response {
    private String staffLogId;
    private String staffId;
    private String logId;
}
