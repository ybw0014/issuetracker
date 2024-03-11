package net.guizhanss.issuetracker.exception;

import net.guizhanss.issuetracker.error.ResponseError;
import org.springframework.http.HttpStatus;

public class UnauthenticatedException extends BaseException {
    public UnauthenticatedException() {
        super(HttpStatus.UNAUTHORIZED, ResponseError.UNAUTHENTICATED);
    }
}
