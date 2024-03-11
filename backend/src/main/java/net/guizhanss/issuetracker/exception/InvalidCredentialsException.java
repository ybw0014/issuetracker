package net.guizhanss.issuetracker.exception;

import net.guizhanss.issuetracker.error.ResponseError;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BaseException {
    public InvalidCredentialsException() {
        super(HttpStatus.UNAUTHORIZED, ResponseError.INVALID_CREDENTIALS);
    }
}
