package net.guizhanss.issuetracker.handler;

import jakarta.servlet.http.HttpServletRequest;
import net.guizhanss.issuetracker.entity.Response;
import net.guizhanss.issuetracker.exception.BaseException;
import net.guizhanss.issuetracker.exception.UserExistException;
import net.guizhanss.issuetracker.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BaseException.class})
    public ResponseEntity<Response> handleUserNotFound(HttpServletRequest request, Exception ex) {
        BaseException exception = (BaseException) ex;
        return new ResponseEntity<>(Response.error(exception.getCode(), exception.getMessage()), exception.getStatus());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Response> handleBadRequest(HttpServletRequest request, Exception ex) {
        return new ResponseEntity<>(Response.error(400, "Bad request"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<Response> handleNotFound(HttpServletRequest request, Exception ex) {
        return new ResponseEntity<>(Response.error(404, "Route not found"), HttpStatus.NOT_FOUND);
    }
}
