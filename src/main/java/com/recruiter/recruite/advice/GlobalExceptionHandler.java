package com.recruiter.recruite.advice;


import com.recruiter.recruite.service.exception.BusinessException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessExceptions(BusinessException exp){

        Map<String, Object> errorAttributes = null;

        errorAttributes = getCommonErrorAttributes(HttpStatus.INTERNAL_SERVER_ERROR);
        errorAttributes.put("message", exp.getMessage());
        return new ResponseEntity<>(errorAttributes,
                HttpStatus.valueOf((Integer) errorAttributes.get("status")));
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class,
            MethodArgumentTypeMismatchException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleDataValidationExceptions(Exception exp){

        Map<String, Object> errorAttributes = getCommonErrorAttributes(HttpStatus.BAD_REQUEST);

        if (exp instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException mExp = (MethodArgumentNotValidException) exp;
            List<Map<String, Object>> errorList = new ArrayList<>();

            for (FieldError fieldError : mExp.getFieldErrors()) {
                Map<String, Object> errorMap = new LinkedHashMap<>();
                errorMap.put("field", fieldError.getField());
                errorMap.put("message", fieldError.getDefaultMessage());
                errorMap.put("rejected", fieldError.getRejectedValue());
                errorList.add(errorMap);
            }

            errorAttributes.put("message", "Data Validation Failed");
            errorAttributes.put("errors", errorList);
        }else if (exp instanceof ConstraintViolationException){
            ConstraintViolationException cExp = (ConstraintViolationException) exp;

            List<Map<String, Object>> errorList = new ArrayList<>();

            for (ConstraintViolation<?> constraintViolation : cExp.getConstraintViolations()) {
                Map<String, Object> errorMap = new LinkedHashMap<>();
                errorMap.put("message", constraintViolation.getMessage());
                errorMap.put("rejected", constraintViolation.getInvalidValue());
                errorList.add(errorMap);
            }

            errorAttributes.put("message", "Data Validation Failed");
            errorAttributes.put("errors", errorList);
        }else {
            MethodArgumentTypeMismatchException mExp = (MethodArgumentTypeMismatchException) exp;
            errorAttributes.put("message", "Invalid value provided for " + mExp.getName());
        }

        return errorAttributes;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> commonExceptions(Throwable t) {
//        t.printStackTrace();
        Map<String, Object> errorAttributes = getCommonErrorAttributes(HttpStatus.INTERNAL_SERVER_ERROR);
        errorAttributes.put("message", t.getMessage());
        return errorAttributes;
    }

    private Map<String, Object> getCommonErrorAttributes(HttpStatus status){
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("timestamp", LocalDateTime.now().toString());
        errorAttributes.put("status", status.value());
        errorAttributes.put("error", status);
        return errorAttributes;
    }

}
