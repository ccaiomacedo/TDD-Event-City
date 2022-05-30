package com.devsuperior.bds02.controllers.exceptions;

import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError er = new StandardError();
        HttpStatus status = HttpStatus.NOT_FOUND;
        er.setTimeStamp(Instant.now());
        er.setStatus(status.value());
        er.setError("Resource not found");
        er.setMessage(e.getMessage());
        er.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(er);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        StandardError er = new StandardError();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        er.setTimeStamp(Instant.now());
        er.setStatus(status.value());
        er.setError("Database exception");
        er.setMessage(e.getMessage());
        er.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(er);
    }

}
