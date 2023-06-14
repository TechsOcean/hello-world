package com.chandan.hibernateentitymappings.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/*  ----  ResponseEntityExceptionHandler  contains below error handling methods ------
HttpRequestMethodNotSupportedException.class,
HttpMediaTypeNotSupportedException.class,
HttpMediaTypeNotAcceptableException.class,
MissingPathVariableException.class,
MissingServletRequestParameterException.class,
ServletRequestBindingException.class,
ConversionNotSupportedException.class,
TypeMismatchException.class,
HttpMessageNotReadableException.class,
HttpMessageNotWritableException.class,
MethodArgumentNotValidException.class,
MissingServletRequestPartException.class,
BindException.class,
NoHandlerFoundException.class,
AsyncRequestTimeoutException.class
 */

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    Future<String> future = new CompletableFuture<>();

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleStudentNotFoundException(
            StudentNotFoundException studentNotFoundException) {
        ErrorMessage errorMessage = new ErrorMessage(
                String.valueOf(HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now(),
                null,
                studentNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(org.springframework.web.bind.MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage();
        ex.getBindingResult().getFieldErrors().forEach(
                fieldError -> {
                    errorMessage.setLocalDate(LocalDateTime.now());
                    errorMessage.setErrorMessage(fieldError.getDefaultMessage());
                    errorMessage.setStatus(HttpStatus.BAD_REQUEST);
                    errorMessage.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
                }
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()),
                HttpStatus.METHOD_NOT_ALLOWED,
                LocalDateTime.now(),
                null,
                ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintValidationException(ConstraintViolationException exception) {
        HashMap<String, String> hashMap = new HashMap<>();

        exception.getConstraintViolations().forEach(ex -> {
            hashMap.put("errorMessage", ex.getMessage() + ", Invalid value = " + ex.getInvalidValue().toString());
        });

        ErrorMessage errorMessage = new ErrorMessage(
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now(),
                null,
                hashMap.get("errorMessage"));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
