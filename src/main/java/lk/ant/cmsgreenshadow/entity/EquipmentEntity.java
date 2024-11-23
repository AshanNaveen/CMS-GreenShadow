package lk.ant.cmsgreenshadow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * @author Naveen Theekshana
 * @date 10/29/2024
 * @project CMS-GreenShadow
 */
@Entity
@Table(name = "equipment")
public class EquipmentEntity {
    @Id
    private String euqipmentId;
    private String name;
    private Enum type;
    private Enum Status;
    @OneToOne
    private StaffEntity assignedStaff;
    @OneToOne
    private FieldEntity assignedField;
}


