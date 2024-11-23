package lk.ant.cmsgreenshadow.entity;

import jakarta.persistence.*;

/**
 * @author Naveen Theekshana
 * @date 10/29/2024
 * @project CMS-GreenShadow
 */
@Entity
@Table(name = "crop")
public class CropEntity {
    @Id
    private String cropCode;
    private String commonName;
    private String scientificName;
    @Column(columnDefinition = "LONGTEXT")
    private String cropImage;
    private String category;
    private String season;
    @ManyToOne
    private FieldEntity field;
}
