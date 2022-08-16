package kz.halykacademy.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UpdateDataFailException extends RuntimeException{

    public UpdateDataFailException(String message) {
        super(message);
    }
}
