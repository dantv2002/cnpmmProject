package hcmute.edu.vn.finalproject20110451.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UsernameInvalidException extends RuntimeException {
    public UsernameInvalidException(String message) {
        super(message);
    }
}



