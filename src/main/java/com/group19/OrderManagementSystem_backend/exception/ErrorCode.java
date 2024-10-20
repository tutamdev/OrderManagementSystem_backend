package com.group19.OrderManagementSystem_backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
public enum ErrorCode {
    UNCATEGORIZE_EXCEPTION(9999, "Uncategorized exception :D", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXITED(1001, "User exited", HttpStatus.BAD_REQUEST),
    INVALID_MESSAGE_KEY(1002, "Invalid message key", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username is invalid", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password is invalid", HttpStatus.BAD_REQUEST),
    USER_NOT_EXITED(1005, "User not exited", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "Unauthorized", HttpStatus.FORBIDDEN),
    DO_NOT_HAVE_PERMISSIONS(1008, "Do not have permissions", HttpStatus.FORBIDDEN),
    SHIFT_ALREADY_ACTIVE(1009, "There is an active shift, you cannot create a new one.", HttpStatus.BAD_REQUEST),
    SHIFT_NOT_ACTIVE(1010, "Shift does not active", HttpStatus.BAD_REQUEST),
    SHIFT_NOT_EXITED(1011, "Shift not exited", HttpStatus.BAD_REQUEST),
    NO_ACTIVE_SHIFT(1012, "There are no active shifts", HttpStatus.BAD_REQUEST),
    AREA_EXITED(1013, "Area exited", HttpStatus.BAD_REQUEST),
    AREA_NOT_EXITED(1014, "Area not exited", HttpStatus.BAD_REQUEST),
    TABLE_NOT_EXITED(1015, "Table exited", HttpStatus.BAD_REQUEST),
    CATEGORY_EXITED(1016, "Category exited", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_EXITED(1017, "Category not exited", HttpStatus.BAD_REQUEST),
    FOOD_NOT_EXITED(1018, "food not exited", HttpStatus.BAD_REQUEST),


    ;
    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode (int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
