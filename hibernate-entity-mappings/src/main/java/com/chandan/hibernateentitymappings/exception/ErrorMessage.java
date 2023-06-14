package com.chandan.hibernateentitymappings.exception;

import com.chandan.hibernateentitymappings.response.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage extends ApiResponse {

    private String errorMessage;

    public ErrorMessage(String code, HttpStatus status, LocalDateTime localDate, String message, String errorMessage) {
        super(code, status, localDate, message);
        this.errorMessage = errorMessage;
    }
}
