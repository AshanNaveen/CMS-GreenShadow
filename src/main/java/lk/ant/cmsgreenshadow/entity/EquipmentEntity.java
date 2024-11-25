package lk.ant.cmsgreenshadow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Naveen Theekshana
 * @date 10/29/2024
 * @project CMS-GreenShadow
 */
@Entity
@Table(name = "equipment")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipmentEntity {
    @Id
    private String euqipmentId;
    private String name;
    private String type;
    private String Status;
    @ManyToOne
    @JoinColumn(name = "field_id")
    private FieldEntity field;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity staff;
}


