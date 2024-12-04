package lk.ant.cmsgreenshadow.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lk.ant.cmsgreenshadow.customResponse.ErrorResponse;
import lk.ant.cmsgreenshadow.customResponse.Response;
import lk.ant.cmsgreenshadow.dto.FieldDto;
import lk.ant.cmsgreenshadow.exception.DataPersistFailedException;
import lk.ant.cmsgreenshadow.exception.NotFoundException;
import lk.ant.cmsgreenshadow.service.FieldService;
import lk.ant.cmsgreenshadow.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
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
@RequestMapping("/api/v1/field")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    private static final Logger logger = Logger.getLogger(FieldController.class.getName());

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveField(
            @Valid
            @RequestPart("fieldName") String fieldName,
            @RequestPart("location") String fieldLocation,
            @RequestPart("size") String fieldSize,
            @RequestPart("fieldImg1") MultipartFile fieldImg1,
            @RequestPart("fieldImg2") MultipartFile fieldImg2,
            @RequestPart("staffs") String staffs) {
        try {
            Point location = AppUtil.parseLocation(fieldLocation);
            byte[] img1 = fieldImg1.getBytes();
            byte[] img2 = fieldImg2.getBytes();
            String base64Img1 = AppUtil.toBase64Pic(img1);
            String base64Img2 = AppUtil.toBase64Pic(img2);
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> staffList = objectMapper.readValue(staffs, new TypeReference<>() {});
            FieldDto fieldDTO = new FieldDto();
            fieldDTO.setFieldName(fieldName);
            fieldDTO.setLocation(location);
            fieldDTO.setSize(Double.parseDouble(fieldSize));
            fieldDTO.setImage1(base64Img1);
            fieldDTO.setImage2(base64Img2);
            fieldDTO.setStaff(staffList);
            fieldService.saveField(fieldDTO);
            logger.info("Field saved successfully: " + fieldName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Failed to save field: " + fieldName);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateField(
            @Valid
            @PathVariable("id") String id,
            @RequestPart("fieldName") String fieldName,
            @RequestPart("location") String fieldLocation,
            @RequestPart("size") String fieldSize,
            @RequestPart("fieldImg1") MultipartFile fieldImg1,
            @RequestPart("fieldImg2") MultipartFile fieldImg2,
            @RequestPart("staffs") String staffs) {
        try {
            Point location = AppUtil.parseLocation(fieldLocation);
            byte[] img1 = fieldImg1.getBytes();
            byte[] img2 = fieldImg2.getBytes();
            String base64Img1 = AppUtil.toBase64Pic(img1);
            String base64Img2 = AppUtil.toBase64Pic(img2);
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> staffList = objectMapper.readValue(staffs, new TypeReference<>() {});
            FieldDto fieldDTO = new FieldDto();
            fieldDTO.setFieldName(fieldName);
            fieldDTO.setLocation(location);
            fieldDTO.setSize(Double.parseDouble(fieldSize));
            fieldDTO.setImage1(base64Img1);
            fieldDTO.setImage2(base64Img2);
            fieldDTO.setStaff(staffList);
            fieldService.updateField(id, fieldDTO);
            logger.info("Field updated successfully: " + fieldName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistFailedException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Failed to update field: " + fieldName);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findField(@PathVariable("id") String id) {
        if (id != null) {
            try {
                FieldDto fieldDTO = fieldService.searchField(id);
                logger.info("Field found with id: " + id);
                return fieldDTO;
            } catch (NotFoundException e) {
                return new ErrorResponse("Field not found with id: " + id, HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                logger.severe("Failed to find field with id: " + id);
                return new ErrorResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ErrorResponse("Invalid field id", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public  List<FieldDto> getAllFields() {
        try {
            List<FieldDto> allFields = fieldService.getAllFields();
            logger.info("All fields found");
            return allFields;
        } catch (Exception e) {
            logger.severe("Failed to find all fields");
            return null;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteField(@PathVariable("id") String id) {
        if (id != null) {
            try {
                boolean deleted = fieldService.deleteField(id);
                if (deleted) {
                    logger.info("Field deleted successfully: " + id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } catch (NotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                logger.severe("Failed to delete field: " + id);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
