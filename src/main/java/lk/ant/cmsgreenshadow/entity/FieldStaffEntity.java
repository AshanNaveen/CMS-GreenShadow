package lk.ant.cmsgreenshadow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "field_staff")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldStaffEntity implements Serializable {
    @Id
    private String fieldStaffId;
    @ManyToOne
    @JoinColumn(name = "field_id")
    @ToString.Exclude
    private FieldEntity field;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity staff;
    @Column(name = "assigned_date", nullable = false)
    private LocalDate assignedDate;
}
