package com.group19.OrderManagementSystem_backend.exception;

import com.group19.OrderManagementSystem_backend.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    ResponseEntity<String> handlingRuntimeException(RuntimeException exception){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.UNCATEGORIZE_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZE_EXCEPTION.getMessage());
        return ResponseEntity.badRequest().body(apiResponse.toString());
    }

    @ExceptionHandler(AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatusCode()).body(
                ApiResponse.builder().code(errorCode.getCode())
                        .message(errorCode.getMessage()).build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidationException(MethodArgumentNotValidException e) {
        ApiResponse apiResponse = new ApiResponse();
        String emumKey = e.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_MESSAGE_KEY;
        try {
            errorCode = ErrorCode.valueOf(emumKey);
        } catch (IllegalArgumentException iae) {

        }
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
