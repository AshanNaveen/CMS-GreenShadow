package lk.ant.cmsgreenshadow.entity;

import jakarta.persistence.*;
import lk.ant.cmsgreenshadow.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Naveen Theekshana
 * @date 10/29/2024
 * @project CMS-GreenShadow
 */
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {
    @Id
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
