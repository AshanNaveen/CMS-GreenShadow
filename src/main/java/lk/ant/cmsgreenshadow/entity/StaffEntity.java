package lk.ant.cmsgreenshadow.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.sql.Date;
import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 10/29/2024
 * @project CMS-GreenShadow
 */
@Entity
@Table(name = "staff")
public class StaffEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String designation;
    private Enum gender;
    private Date joinedDate;
    private Date dateOfBirth;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNo;
    private String email;
    private Enum role;
    @OneToMany
    private List<FieldEntity> fields;
    @OneToMany
    private List<VehicleEntity> vehicles;
}
