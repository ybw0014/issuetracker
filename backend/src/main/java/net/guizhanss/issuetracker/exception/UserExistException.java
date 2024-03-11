package net.guizhanss.issuetracker.exception;

import net.guizhanss.issuetracker.error.ResponseError;
import org.springframework.http.HttpStatus;

public class UserExistException extends BaseException {
    public UserExistException() {
        super(HttpStatus.FORBIDDEN, ResponseError.USER_EXIST);
    }
}
