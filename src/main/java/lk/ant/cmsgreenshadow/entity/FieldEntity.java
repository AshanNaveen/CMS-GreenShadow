package lk.ant.cmsgreenshadow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 10/29/2024
 * @project CMS-GreenShadow
 */
@Entity
@Table(name="field")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldEntity {
    @Id
    private String fieldId;
    private String fieldName;
    private Point location;
    private Double size;
    @Column(columnDefinition = "LONGTEXT")
    private String image1;
    @Column(columnDefinition = "LONGTEXT")
    private String image2;
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<EquipmentEntity> equipment;
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<LogEntity> logs;
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<FieldCropEntity> fieldCrops = new ArrayList<>();
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<FieldStaffEntity> fieldStaffs = new ArrayList<>();
}
