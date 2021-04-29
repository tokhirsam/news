package uz.pdp.appnews.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) @AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException{

    private final String resourceName; //lavozim
    private final String resourceField; //name boyicga qidirdim
    private final Object object; //USER, ADMIN, 1, 500 ni qidirdim


}
