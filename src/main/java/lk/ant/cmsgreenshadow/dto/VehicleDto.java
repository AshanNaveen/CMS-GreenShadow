package lk.ant.cmsgreenshadow.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Number plate cannot be blank")
    @Pattern(regexp = "^[A-Z0-9-]{1,15}$", message = "Number plate must be alphanumeric and at most 15 characters")
    private String licensePlate;
    @NotBlank(message = "Category cannot be blank")
    @Size(max = 30, message = "Category must be at most 30 characters")
    private String category;
    @NotBlank(message = "Fuel type cannot be blank")
    @Size(max = 15, message = "Fuel type must be at most 15 characters")
    private String fuelType;
    @NotBlank(message = "Status cannot be blank")
    @Size(max = 50, message = "Status must be at most 50 characters")
    private String status;
    private String assignedStaffId;
    @Size(max = 255, message = "Remarks must be at most 255 characters")
    private String remarks;
}
