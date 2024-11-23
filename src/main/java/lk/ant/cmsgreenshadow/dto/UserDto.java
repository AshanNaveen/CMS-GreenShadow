package lk.ant.cmsgreenshadow.dto;


/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
public class UserDto {
    private String email;
    private String password;
    private Role role;

    public enum Role{
        MANAGER, ADMINISTRATIVE, SCIENTIST, OTHER;
    }
}
