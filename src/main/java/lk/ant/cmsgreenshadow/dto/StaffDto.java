package lk.ant.cmsgreenshadow.dto;


import jakarta.validation.constraints.*;
import lk.ant.cmsgreenshadow.customResponse.Response;
import lk.ant.cmsgreenshadow.entity.enums.Gender;
import lk.ant.cmsgreenshadow.entity.enums.Role;
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
public class StaffDto implements Serializable, Response {
    private String staffId;
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;
    @NotBlank(message = "Designation is required")
    private String designation;
    @NotNull(message = "Gender is required")
    private Gender gender;
    private Date joinedDate;
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private Date dateOfBirth;
    @NotBlank(message = "Address Line 1 is required")
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid mobile number format")
    private String contactNo;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotNull(message = "Role is required")
    private Role role;
    private List fields;
    private List vehicles;
}
