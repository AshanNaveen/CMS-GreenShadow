package lk.ant.cmsgreenshadow.customResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse implements Response, Serializable {
    private String message;
    private HttpStatus error;
}
