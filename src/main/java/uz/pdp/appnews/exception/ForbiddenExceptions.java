package uz.pdp.appnews.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenExceptions extends RuntimeException{
    private String type;
    private String message;

    public ForbiddenExceptions(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
