package com.expense.io.error;

import com.expense.io.renderers.ErrorResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> generateResponse(Error error) {
        return new ResponseEntity<>(new ErrorResponse<>(error), error.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        var error = new Error(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST, Codes.VALIDATION_ERROR);
        return generateResponse(error);
    }

    //other exception handlers below

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException exception) {
        var error = new Error(exception.getLocalizedMessage(),
                              HttpStatus.NOT_FOUND, Codes.NOT_FOUND);

        return generateResponse(error);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseEntity<Object> handleNotFound(EmptyResultDataAccessException exception) {
        var error = new Error(exception.getLocalizedMessage(),
                              HttpStatus.NOT_FOUND, Codes.NOT_FOUND);

        return generateResponse(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        final List<FieldError> fieldErrors = ex.getBindingResult()
                                               .getFieldErrors()
                                               .stream()
                                               .map(error -> new FieldError(error.getField(),
                                                                            error.getDefaultMessage()))
                                               .collect(Collectors.toUnmodifiableList());

        var message = String.format("Error in %d fields", fieldErrors.size());
        var error = new Error(message, HttpStatus.BAD_REQUEST, Codes.VALIDATION_ERROR, fieldErrors);
        return generateResponse(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleLoginFail(BadCredentialsException exception) {
        var error = new Error(exception.getLocalizedMessage(), HttpStatus.UNAUTHORIZED, Codes.FIELDS_ERROR);
        return generateResponse(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstrainViolation(ConstraintViolationException exception) {
        var message = exception.getCause()
                               .getMessage()
                               .split("\n")[1].strip();

        var error = new Error(message, HttpStatus.BAD_REQUEST, Codes.VALIDATION_ERROR);
        return generateResponse(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        var message = exception.getCause()
                               .getMessage()
                               .split("\n")[1].strip();

        var error = new Error(message, HttpStatus.SERVICE_UNAVAILABLE, Codes.INTEGRITY_ERROR);
        return generateResponse(error);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        var error = new Error(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, Codes.INTERNAL_ERROR);
        return generateResponse(error);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatus status,
                                                                         WebRequest request) {
        var error = new Error(ex.getLocalizedMessage(), HttpStatus.METHOD_NOT_ALLOWED, Codes.INVALID_METHOD);
        return generateResponse(error);
    }

}