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
 * @date 11/2/2024
 * @project CMS-GreenShadow
 */
@Entity
@Table(name = "log")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogEntity {
    @Id
    private String logId;
    private Date logDate;
    private String observation;
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;
    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private FieldEntity field;
    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private CropEntity crop;
    @OneToMany(mappedBy = "log", cascade = CascadeType.ALL)
    private List<StaffLogEntity> staffLog = new ArrayList<>();
}
