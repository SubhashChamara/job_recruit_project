package com.recruiter.recruite.service.exception;

public enum BusinessExceptionType {

    BUSINESS(1000, "Business Exception"),
    FILE_NOT_CREATE(1001, "File Not Create"),
    FILE_NOT_UPLOAD(1001, "File Not Upload"),
    FILE_NOT_FOUND(1001, "File Not Found");


    private final int code;
    private final String message;

    BusinessExceptionType(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
