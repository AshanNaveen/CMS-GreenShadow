package lk.ant.cmsgreenshadow.controller;

import jakarta.validation.Valid;
import lk.ant.cmsgreenshadow.dto.UserDto;
import lk.ant.cmsgreenshadow.exception.DataPersistFailedException;
import lk.ant.cmsgreenshadow.exception.NotFoundException;
import lk.ant.cmsgreenshadow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/**
 * @author Naveen Theekshana
 * @date 12/3/2024
 * @project CMSGreenShadow
 */
@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @PatchMapping(value = "/{email}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@Valid @PathVariable("email") String email, @RequestBody UserDto user) {
        if (email != null && user != null) {
            try {
                user.setPassword(user.getPassword());
                userService.updateUser(email, user);
                logger.info("User password updated successfully: " + user);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (NotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (DataPersistFailedException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.severe("Failed to update user: " + user);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable("email") String email) {
        if (email != null) {
            try {
                boolean deleted = userService.deleteUser(email);
                if (deleted) {
                    logger.info("User deleted successfully with email: " + email);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (NotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                logger.severe("Failed to delete user with email: " + email);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUser(@PathVariable("email") String email) {
        if (email != null) {
            try{
                UserDto user = userService.getUser(email);
                if (user != null) {
                    logger.info("User found with email: " + email);
                    return new ResponseEntity<>(user, HttpStatus.OK);
                }
            }catch (NotFoundException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
