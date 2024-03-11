package net.guizhanss.issuetracker.exception;

import net.guizhanss.issuetracker.error.ResponseError;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, ResponseError.USER_NOT_FOUND);
    }
}
