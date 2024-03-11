package net.guizhanss.issuetracker.exception;

import net.guizhanss.issuetracker.error.ResponseError;
import org.springframework.http.HttpStatus;

public class InvalidJwtException extends BaseException {
    public InvalidJwtException() {
        super(HttpStatus.UNAUTHORIZED, ResponseError.INVALID_TOKEN);
    }
}
