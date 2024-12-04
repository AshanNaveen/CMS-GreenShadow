package lk.ant.cmsgreenshadow.controller;

import jakarta.validation.Valid;
import lk.ant.cmsgreenshadow.customResponse.ErrorResponse;
import lk.ant.cmsgreenshadow.customResponse.Response;
import lk.ant.cmsgreenshadow.dto.StaffDto;
import lk.ant.cmsgreenshadow.exception.DataPersistFailedException;
import lk.ant.cmsgreenshadow.exception.NotFoundException;
import lk.ant.cmsgreenshadow.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Naveen Theekshana
 * @date 12/3/2024
 * @project CMSGreenShadow
 */

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    private static final Logger logger = Logger.getLogger(LogController.class.getName());

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(@Valid @RequestBody StaffDto staff) {
        if (staff != null) {
            try {
                staffService.saveStaff(staff);
                logger.info("Staff saved successfully: " + staff);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistFailedException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                logger.severe("Failed to save staff: " + staff);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateStaff(@Valid @PathVariable("id") String id, @RequestBody StaffDto staff) {
        if (id != null && staff != null) {
            try {
                staffService.updateStaff(id, staff);
                logger.info("Staff updated successfully: " + staff);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }catch (NotFoundException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }catch (DataPersistFailedException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                logger.severe("Failed to update staff: " + staff);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findStaff(@PathVariable("id") String id) {
        if (id != null) {
            try {
                StaffDto staffDTO = staffService.searchStaff(id);
                logger.info("Staff found with id: " + id);
                return staffDTO;
            } catch (NotFoundException e) {
                return new ErrorResponse("Staff not found with id: " + id, HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                logger.severe("Failed to find staff with id: " + id);
                return new ErrorResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ErrorResponse("Invalid staff id", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDto> getAllStaffs() {
        try {
            List<StaffDto> allStaffs = staffService.getAllStaffs();
            logger.info("All staffs found");
            return allStaffs;
        } catch (Exception e) {
            logger.severe("Failed to find all staffs");
            return null;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable("id") String id) {
        if (id != null) {
            try {
                boolean deleted = staffService.deleteStaff(id);
                if (deleted) {
                    logger.info("Staff deleted successfully: " + id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } catch (NotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                logger.severe("Failed to delete staff with id: " + id);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
