package lk.ant.cmsgreenshadow.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lk.ant.cmsgreenshadow.customResponse.ErrorResponse;
import lk.ant.cmsgreenshadow.customResponse.Response;
import lk.ant.cmsgreenshadow.dto.CropDto;
import lk.ant.cmsgreenshadow.exception.DataPersistFailedException;
import lk.ant.cmsgreenshadow.exception.NotFoundException;
import lk.ant.cmsgreenshadow.service.CropService;
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
 * @date 11/24/2024
 * @project CMSGreenShadow
 */

@RestController
@RequestMapping("/api/v1/crop")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CropController {
    @Autowired
    private CropService cropService;

    private static final Logger logger = Logger.getLogger(CropController.class.getName());

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveCrop(
            @Valid
            @RequestPart("commonName") String commonName,
            @RequestPart("scientificName") String scientificName,
            @RequestPart("category") String category,
            @RequestPart("season") String season,
            @RequestPart("cropImg") MultipartFile cropImg,
            @RequestPart("fields") String fields) {
        try {
            byte[] img = cropImg.getBytes();
            String base64Img = AppUtil.toBase64Pic(img);

            ObjectMapper objectMapper = new ObjectMapper();
            List<String> fieldList = objectMapper.readValue(fields, new TypeReference<>() {});

            CropDto cropDTO = new CropDto();
            cropDTO.setCommonName(commonName);
            cropDTO.setScientificName(scientificName);
            cropDTO.setCategory(category);
            cropDTO.setSeason(season);
            cropDTO.setCropImage(base64Img);
            cropDTO.setFieldIds(fieldList);
            cropService.saveCrop(cropDTO);
            logger.info("Crop saved successfully: " + commonName);
            return new ResponseEntity<>("Crop saved successfully", HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Failed to save crop: " + commonName);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateCrop(
            @Valid
            @PathVariable String id,
            @RequestPart("commonName") String commonName,
            @RequestPart("scientificName") String scientificName,
            @RequestPart("category") String category,
            @RequestPart("season") String season,
            @RequestPart("cropImg") MultipartFile cropImg,
            @RequestPart("fields") String fields) {
        try {
            byte[] img = cropImg.getBytes();
            String base64Img = AppUtil.toBase64Pic(img);
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> fieldList = objectMapper.readValue(fields, new TypeReference<>() {});
            CropDto cropDTO = new CropDto();
            cropDTO.setCommonName(commonName);
            cropDTO.setScientificName(scientificName);
            cropDTO.setCategory(category);
            cropDTO.setSeason(season);
            cropDTO.setCropImage(base64Img);
            cropDTO.setFieldIds(fieldList);
            cropService.updateCrop(id, cropDTO);
            logger.info("Crop updated successfully: " + commonName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistFailedException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Failed to update crop: " + commonName);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response searchCrop(@PathVariable String id) {
        try {
            CropDto cropDTO = cropService.searchCrop(id);
            logger.info("Crop searched successfully: " + cropDTO.getCommonName());
            return cropDTO;
        } catch (Exception e) {
            logger.severe("Failed to search crop with id: " + id);
            return new ErrorResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDto> getAllCrops() {
        try {
            List<CropDto> allCrops = cropService.getAllCrops();
            logger.info("All crops fetched successfully");
            return allCrops;
        } catch (Exception e) {
            logger.severe("Failed to fetch all crops");
            return null;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteCrop(@PathVariable String id) {
        if (id != null) {
            try {
                boolean deleted = cropService.deleteCrop(id);
                if (deleted){
                    logger.info("Crop deleted successfully with id: " + id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } catch (NotFoundException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                logger.severe("Failed to delete crop with id: " + id);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
