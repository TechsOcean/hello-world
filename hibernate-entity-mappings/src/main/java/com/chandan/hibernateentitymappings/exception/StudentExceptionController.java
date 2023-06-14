package com.chandan.hibernateentitymappings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class StudentExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public HashMap<String, String> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        HashMap<String, String> fieldsErrors = new HashMap<>();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> {
                    fieldsErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
                });
        return fieldsErrors;
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({StudentNotFoundException.class})
    public HashMap<String, String> studentNotFoundException(StudentNotFoundException exception) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("errorMessage", exception.getMessage());
        return hashMap;
    }


//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler({ConstraintViolationException.class})
//    public HashMap<String, String> constraintViolationException(ConstraintViolationException exception) {
//        HashMap<String, String> hashMap = new HashMap<>();
//        exception.getConstraintViolations().forEach(ex -> {
//            hashMap.put("invalidValue", ex.getInvalidValue().toString());
//            hashMap.put("field", ex.getPropertyPath().toString());
//            hashMap.put("errorMessage", ex.getMessage());
//        });
//        return hashMap;
//    }
}
