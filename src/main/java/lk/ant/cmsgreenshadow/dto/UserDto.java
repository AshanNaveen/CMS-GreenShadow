package lk.ant.cmsgreenshadow.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lk.ant.cmsgreenshadow.customResponse.Response;
import lk.ant.cmsgreenshadow.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable, Response {
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$", message = "Invalid password format")
    private String password;
    private Role role;
}
