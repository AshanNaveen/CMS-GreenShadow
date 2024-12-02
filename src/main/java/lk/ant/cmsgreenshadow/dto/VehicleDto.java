package lk.ant.cmsgreenshadow.dto;


import lk.ant.cmsgreenshadow.customResponse.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto implements Serializable, Response {
    private String vehicleId;
    private String licensePlate;
    private String category;
    private String fuelType;
    private String status;
    private String assignedStaffId;
    private String remarks;
}
