package com.recruiter.recruite.service.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private final BusinessExceptionType businessExceptionType;

    public BusinessException(BusinessExceptionType businessExceptionTypes) {
        this.businessExceptionType = businessExceptionTypes;
    }

    public BusinessException(BusinessExceptionType type, Throwable cause) {
        super(type.getCode() +"-" + type.getMessage(), cause);
        this.businessExceptionType = type;
    }

    public BusinessException(BusinessExceptionType type, String message, Throwable cause) {
        super(type.getCode() +"-"  + message, cause);
        this.businessExceptionType = type;
    }

    public BusinessException(BusinessExceptionType type, String message) {
        super(type.getCode() +"-" + message);
        this.businessExceptionType = type;
    }
}
