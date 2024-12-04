package lk.ant.cmsgreenshadow.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lk.ant.cmsgreenshadow.customResponse.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Naveen Theekshana
 * @date 11/23/2024
 * @project CMSGreenShadow
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDto implements Serializable, Response {
    private String EquipmentId;
    @NotBlank(message = "Category cannot be blank")
    @Size(max = 50, message = "Category must be at most 50 characters")
    private String Name;
    @NotBlank(message = "Type cannot be blank")
    @Size(max = 50, message = "Type must be at most 50 characters")
    private Enum Type;
    @NotBlank(message = "Status cannot be blank")
    @Size(max = 50, message = "Status must be at most 50 characters")
    private Enum Status;
    private String AssignedStaff;
    private String AssignedField;
}
