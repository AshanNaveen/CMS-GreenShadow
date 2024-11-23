package lk.ant.cmsgreenshadow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.sql.Date;
import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 11/2/2024
 * @project CMS-GreenShadow
 */
@Entity
@Table(name = "log")
public class LogServiceEntity {
    @Id
    private String logCode;
    private Date logDate;
    private String observation;
    private String observedImage;
    @OneToMany
    private List<FieldEntity> fields;
    @OneToMany
    private List<CropEntity> crops;
    @OneToMany
    private List<StaffEntity> staff;
}
