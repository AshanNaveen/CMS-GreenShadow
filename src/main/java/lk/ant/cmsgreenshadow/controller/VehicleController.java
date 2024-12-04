package lk.ant.cmsgreenshadow.controller;

import lk.ant.cmsgreenshadow.customResponse.ErrorResponse;
import lk.ant.cmsgreenshadow.customResponse.Response;
import lk.ant.cmsgreenshadow.dto.VehicleDto;
import lk.ant.cmsgreenshadow.exception.DataPersistFailedException;
import lk.ant.cmsgreenshadow.exception.NotFoundException;
import lk.ant.cmsgreenshadow.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Naveen Theekshana
 * @date 12/4/2024
 * @project CMSGreenShadow
 */
@RestController
@RequestMapping("/api/v1/vehicle")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    private static final Logger logger = Logger.getLogger(VehicleController.class.getName());

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveVehicle( @RequestBody VehicleDto vehicle) {
        if (vehicle != null) {
            try {
                vehicleService.saveVehicle(vehicle);
                logger.info("Vehicle saved successfully: " + vehicle);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistFailedException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.severe("Failed to save vehicle: " + vehicle);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateVehicle( @PathVariable("id") String id, @RequestBody VehicleDto vehicle) {
        if (id != null && vehicle != null) {
            try {
                vehicleService.updateVehicle(id, vehicle);
                logger.info("Vehicle updated successfully: " + vehicle);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (NotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (DataPersistFailedException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.severe("Failed to update vehicle: " + vehicle);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response searchVehicle(@PathVariable("id") String id) {
        if (id != null) {
            try {
                VehicleDto vehicleDTO = vehicleService.searchVehicle(id);
                logger.info("Vehicle found successfully: " + vehicleDTO);
                return vehicleDTO;
            } catch (NotFoundException e) {
                return new ErrorResponse("Vehicle not found with id: " + id, HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                logger.severe("Failed to find vehicle: " + id);
                return new ErrorResponse("Failed to find vehicle with id: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ErrorResponse("Invalid vehicle id", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDto> getAllVehicles() {
        try {
            List<VehicleDto> allVehicles = vehicleService.getAllVehicles();
            logger.info("All vehicles found successfully");
            return allVehicles;
        } catch (Exception e) {
            logger.severe("Failed to find all vehicles");
            return null;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") String id) {
        if (id != null) {
            try {
                boolean deleted = vehicleService.deleteVehicle(id);
                if (deleted) {
                    logger.info("Vehicle deleted successfully: " + id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } catch (NotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                logger.severe("Failed to delete vehicle: " + id);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
