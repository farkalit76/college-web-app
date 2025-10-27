package org.usman.api.college.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BusinessException extends RuntimeException {

    private final int code;
    private final String errorCode;
    private final String errorMessage;

    public BusinessException(){
        this.code = 400;
        this.errorCode = "BUSINESS_ERR";
        this.errorMessage = "General Business Error";
    }
}