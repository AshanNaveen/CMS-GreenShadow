package lk.ant.cmsgreenshadow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 10/29/2024
 * @project CMS-GreenShadow
 */
@Entity
@Table(name = "crop")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CropEntity {
    @Id
    private String cropId;
    private String commonName;
    private String scientificName;
    @Column(columnDefinition = "LONGTEXT")
    private String cropImage;
    private String category;
    private String season;
    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL)
    private List<LogEntity> logs;
    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL)
    private List<FieldCropEntity> fieldCrops = new ArrayList<>();
}
