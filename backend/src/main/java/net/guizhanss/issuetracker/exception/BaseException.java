package net.guizhanss.issuetracker.exception;

import lombok.Getter;
import net.guizhanss.issuetracker.entity.Response;
import net.guizhanss.issuetracker.error.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public abstract class BaseException extends RuntimeException {
    private final int code;
    private final HttpStatus status;

    protected BaseException(HttpStatus status, int code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }

    protected BaseException(HttpStatus status, ResponseError responseError) {
        super(responseError.getMessage());
        this.status = status;
        this.code = responseError.getCode();
    }

    public Response toResponse() {
        return Response.error(code, getMessage());
    }

    public ResponseEntity<Response> toResponseEntity() {
        return new ResponseEntity<>(toResponse(), status);
    }

}
