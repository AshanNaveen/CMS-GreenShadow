package lk.ant.cmsgreenshadow.dto;


import java.sql.Date;
import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
public class StaffDto {
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private Gender gender;
    private Date joinedDate;
    private Date dateOfBirth;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNo;
    private String email;
    private Role role;
    private List fields;
    private List vehicles;

    public enum Gender {
        MALE, FEMALE, OTHER;
    }
    public enum Role{
        MANAGER, ADMINISTRATIVE, SCIENTIST, OTHER;
    }
}
