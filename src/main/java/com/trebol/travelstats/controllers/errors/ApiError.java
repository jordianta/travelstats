package com.trebol.travelstats.controllers.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ApiValidationError> subErrors;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(final HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiError(final HttpStatus status, final Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiError(final HttpStatus status, final String message, final Throwable e) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = e.getLocalizedMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public List<ApiValidationError> getSubErrors() {
        return subErrors;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public class ApiValidationError {

        private final String object;
        private String field;
        private Object rejectedValue;
        private final String message;

        public ApiValidationError(final String object, final String message) {
            this.object = object;
            this.message = message;
        }

        public String getObject() {
            return object;
        }

        public String getField() {
            return field;
        }

        public Object getRejectedValue() {
            return rejectedValue;
        }

        public String getMessage() {
            return message;
        }
    }
}