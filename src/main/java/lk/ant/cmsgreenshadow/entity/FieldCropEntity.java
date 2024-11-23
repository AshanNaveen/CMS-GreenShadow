package lk.ant.cmsgreenshadow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "field_crop")
@Entity
public class FieldCropEntity implements Serializable {
    @Id
    private String fieldCropId;
    @ManyToOne
    @JoinColumn(name = "field_id", referencedColumnName = "fieldId")
    private FieldEntity field;
    @ManyToOne
    @JoinColumn(name = "crop_id", referencedColumnName = "cropId")
    private CropEntity crop;
    private Date assignedDate;
}