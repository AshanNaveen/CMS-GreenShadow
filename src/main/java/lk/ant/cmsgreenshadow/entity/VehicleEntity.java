package lk.ant.cmsgreenshadow.entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 10/29/2024
 * @project CMS-GreenShadow
 */
@Entity
@Table(name = "vehicle")
public class VehicleEntity {
    @Id
    private String vehicleId;
    private String licensePlate;
    private String category;
    private String fuelType;
    private String status;
    private String remarks;
    @OneToMany(mappedBy = "vehicleId", cascade = CascadeType.ALL)
    private List<StaffEntity> staff;
}
