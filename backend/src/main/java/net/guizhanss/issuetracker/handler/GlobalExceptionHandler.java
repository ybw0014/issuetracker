package net.guizhanss.issuetracker.handler;

import net.guizhanss.issuetracker.entity.Response;
import net.guizhanss.issuetracker.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BaseException.class})
    public ResponseEntity<Response> handleUserNotFound(BaseException ex) {
        return ex.toResponseEntity();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Response> handleBadRequest(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
            .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        return new ResponseEntity<>(new Response<>(400, "Bad Request", errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<Response> handleNotFound(NoResourceFoundException ex) {
        return new ResponseEntity<>(Response.error(404, "Route not found"), HttpStatus.NOT_FOUND);
    }
}
