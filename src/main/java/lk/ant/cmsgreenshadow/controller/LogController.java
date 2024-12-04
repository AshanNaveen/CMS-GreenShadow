package lk.ant.cmsgreenshadow.controller;

import jakarta.validation.Valid;
import lk.ant.cmsgreenshadow.customResponse.ErrorResponse;
import lk.ant.cmsgreenshadow.customResponse.Response;
import lk.ant.cmsgreenshadow.dto.LogDto;
import lk.ant.cmsgreenshadow.exception.DataPersistFailedException;
import lk.ant.cmsgreenshadow.service.LogService;
import lk.ant.cmsgreenshadow.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Naveen Theekshana
 * @date 12/3/2024
 * @project CMSGreenShadow
 */

@RestController
@RequestMapping("/api/v1/log")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class LogController {
    @Autowired
    private LogService logService;

    private static final Logger logger = Logger.getLogger(LogController.class.getName());

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveLog(
            @Valid
            @RequestPart("details") String details,
            @RequestPart("temperature") String temperature,
            @RequestPart("observedImg") MultipartFile observedImg,
            @RequestPart("fieldId") String fieldId,
            @RequestPart("cropId") String cropId) {
        try {
            byte[] img = observedImg.getBytes();
            String base64Img = AppUtil.toBase64Pic(img);
            LogDto logDTO = new LogDto();
            logDTO.setObservation(details);
            logDTO.setObservedImage(base64Img);
            logDTO.setField(fieldId);
            logDTO.setCrop(cropId);
            logService.saveLog(logDTO);
            logger.info("Log saved successfully: " + logDTO);
            return new ResponseEntity<>("Log saved successfully", HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            e.printStackTrace();
            logger.severe("Failed to save log: " + details + " - " + e.getMessage());
            return new ResponseEntity<>("Failed to save log", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Failed to save log: " + details + " - " + e.getMessage());
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateLog(
            @Valid
            @PathVariable String id,
            @RequestPart("details") String details,
            @RequestPart("temperature") String temperature,
            @RequestPart("observedImg") MultipartFile observedImg,
            @RequestPart("fieldId") String fieldId,
            @RequestPart("cropId") String cropId) {
        try {
            byte[] img = observedImg.getBytes();
            String base64Img = AppUtil.toBase64Pic(img);
            LogDto logDTO = new LogDto();
            logDTO.setObservation(details);
            logDTO.setObservedImage(base64Img);
            logDTO.setField(fieldId);
            logDTO.setCrop(cropId);
            logService.updateLog(id, logDTO);
            logger.info("Log updated successfully: " + logDTO);
            return new ResponseEntity<>("Log updated successfully", HttpStatus.NO_CONTENT);
        } catch (DataPersistFailedException e) {
            e.printStackTrace();
            logger.severe("Failed to update log: " + details + " - " + e.getMessage());
            return new ResponseEntity<>("Failed to update log", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Failed to update log: " + details + " - " + e.getMessage());
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findLog(@PathVariable String id) {
        if (id != null) {
            try {
                LogDto logDTO = logService.searchLog(id);
                logger.info("Log found successfully: " + logDTO);
                return logDTO;
            } catch (Exception e) {
                e.printStackTrace();
                logger.severe("Failed to find log: " + id + " - " + e.getMessage());
                return new ErrorResponse("Failed to find log", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ErrorResponse("Log id is required", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LogDto> getAllLogs() {
        try {
            List<LogDto> allLogs = logService.getAllLogs();
            logger.info("All logs found successfully");
            return allLogs;
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Failed to find all logs: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteLog(@PathVariable String id) {
        if (id != null) {
            try {
                boolean deleted = logService.deleteLog(id);
                if (deleted) {
                    logger.info("Log deleted successfully: " + id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.severe("Failed to delete log: " + id + " - " + e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
