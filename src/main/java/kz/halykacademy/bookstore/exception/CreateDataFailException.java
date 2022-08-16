package kz.halykacademy.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CreateDataFailException extends RuntimeException{
    public CreateDataFailException(String message) {
        super(message);
    }


}
