package lk.ant.cmsgreenshadow.controller;

import jakarta.validation.Valid;
import lk.ant.cmsgreenshadow.customResponse.ErrorResponse;
import lk.ant.cmsgreenshadow.customResponse.Response;
import lk.ant.cmsgreenshadow.dto.EquipmentDto;
import lk.ant.cmsgreenshadow.exception.DataPersistFailedException;
import lk.ant.cmsgreenshadow.exception.NotFoundException;
import lk.ant.cmsgreenshadow.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Naveen Theekshana
 * @date 12/2/2024
 * @project CMSGreenShadow
 */

@RestController
@RequestMapping("/api/v1/equipment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    private static final Logger logger = Logger.getLogger(EquipmentController.class.getName());

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEquipment(@Valid @RequestBody EquipmentDto equipment) {
        if (equipment != null) {
            try {
                equipmentService.saveEquipment(equipment);
                logger.info("Equipment saved successfully: " + equipment);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistFailedException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                logger.severe("Failed to save equipment: " + equipment);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateEquipment(@Valid @PathVariable("id") String id, @RequestBody EquipmentDto equipment) {
        if (id != null && equipment != null) {
            try {
                equipmentService.updateEquipment(id, equipment);
                logger.info("Equipment updated successfully: " + equipment);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (NotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (DataPersistFailedException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e){
                logger.severe("Failed to update equipment: " + equipment);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response searchEquipment(@PathVariable("id") String id) {
        if (id != null) {
            try {
                EquipmentDto equipmentDTO = equipmentService.searchEquipment(id);
                logger.info("Equipment found successfully: " + equipmentDTO);
                return equipmentDTO;
            } catch (NotFoundException e) {
                return new ErrorResponse("Equipment not found with id: " + id, HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                e.printStackTrace();
                logger.severe("Failed to find equipment with id: " + id);
                return new ErrorResponse("Failed to find equipment with id: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ErrorResponse("Invalid equipment id", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDto> getAllEquipments() {
        try {
            List<EquipmentDto> allEquipments = equipmentService.getAllEquipments();
            logger.info("All equipments found successfully: " + allEquipments);
            return allEquipments;
        } catch (Exception e) {
            logger.severe("Failed to find all equipments");
            return null;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteEquipment(@PathVariable("id") String id) {
        if (id != null) {
            try {
                boolean deleted = equipmentService.deleteEquipment(id);
                if (deleted){
                    logger.info("Equipment deleted successfully: " + id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } catch (NotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                logger.severe("Failed to delete equipment with id: " + id);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
