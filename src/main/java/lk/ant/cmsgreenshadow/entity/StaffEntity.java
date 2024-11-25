package lk.ant.cmsgreenshadow.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 10/29/2024
 * @project CMS-GreenShadow
 */
@Entity
@Table(name = "staff")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StaffEntity {
    @Id
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    @Enumerated(EnumType.STRING)
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
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<EquipmentEntity> equipment;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicleId;
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<FieldStaffEntity> fieldStaff = new ArrayList<>();
    @OneToMany(mappedBy = "staffEntity", cascade = CascadeType.ALL)
    private List<StaffLogEntity> staffLog = new ArrayList<>();


    public enum Gender {
        MALE, FEMALE, OTHER;
    }
    public enum Role{
        MANAGER, ADMINISTRATIVE, SCIENTIST, OTHER;
    }
}
