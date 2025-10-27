package org.usman.api.college.common.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

public record ErrorResponse(int code, String errorCode, String message, List<String> errors) {

    @JsonCreator
    public ErrorResponse(@JsonProperty("status_code") int code, @JsonProperty("error_code") String errorCode,
                         @JsonProperty("message") String message, @JsonProperty("errors") List<String> errors) {
        this.code = code;
        this.errorCode = errorCode;
        this.message = message;
        this.errors = errors;
    }

    public ErrorResponse(int code, String message) {
        this(code, null, message, Collections.emptyList());
    }

    public ErrorResponse(int code, String message, List<String> errors) {
        this(code, null, message, errors);
    }

    public ErrorResponse(int code, String errorCode, String message) {
        this(code, errorCode, message, Collections.emptyList());
    }

    public ErrorResponse(String errorCode, String message) {
        this(0, errorCode, message, null);
    }

    public ErrorResponse(String errorCode, String message, List<String> errors) {
        this(0, errorCode, message, errors);
    }
}
