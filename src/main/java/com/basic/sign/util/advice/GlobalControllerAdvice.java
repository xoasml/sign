package com.basic.sign.util.advice;

import com.basic.sign.util.advice.dto.Error;
import com.basic.sign.util.advice.dto.ErrorResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice(basePackages = "com.basic.sign.domain")
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e, HttpServletRequest httpServletRequest) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        log.error("ERROR");
        log.info("[SERVER ERROR] {}", e.getClass().getName());
        exceptionInfoLogger(e);
        e.printStackTrace();
        log.error("ERROR");

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTime(currentDateTime.toString());
        errorResponse.setMessage("unknown error ");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        errorResponse.setResultCdoe("FAIL");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity httpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest httpServletRequest) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        log.error("ERROR");
        log.error("[JSON PARSE MISS] {}", e.getClass().getName());
        log.error("ERROR");

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("JSON parse error");
        errorResponse.setTime(currentDateTime.toString());
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCdoe("FAIL");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest, WebRequest webRequest) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        List<Error> errorList = new ArrayList<>();

        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors().forEach(error -> {
            FieldError field = (FieldError) error;

            String fieldName = field.getField();
            String message = field.getDefaultMessage();
            String value = field.getRejectedValue() == null ? "null" : field.getRejectedValue().toString();

            Error errorMassage = new Error();
            errorMassage.setBecause(fieldName);
            errorMassage.setMessage(message);
            errorMassage.setInvalidValue(value);

            errorList.add(errorMassage);
        });

        log.error("ERROR");
        log.error("[API VALID MISS] {}", e.getClass().getName());
        errorList.forEach(index -> log.error(String.valueOf(index)));
        log.error("ERROR");

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setTime(currentDateTime.toString());
        errorResponse.setMessage("Validation failed for argument");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCdoe("FAIL");

//        saveLog(errorResponse, currentDateTime, httpServletRequest);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private void exceptionInfoLogger(Exception e) {
        StackTraceElement[] ste = e.getStackTrace();
        StringBuffer str = new StringBuffer();
        int lastIndex = ste.length - 1;
        int count = 1;
        for (int i = lastIndex; i > lastIndex - 3; i--) {
            String className = ste[i].getClassName();
            String methodName = ste[i].getMethodName();
            int lineNumber = ste[i].getLineNumber();
            String fileName = ste[i].getFileName();

            str.append("\n").append("[" + count++ + "]").append("className :").append(className).append("\n").append("methodName :").append(methodName).append("\n").append("fileName :").append(fileName).append("\n").append("lineNumber :").append(lineNumber).append("\n").append("message :").append(e.getMessage()).append("\n").append("cause :").append(e.getCause()).append("\n");
        }
        log.error(str.toString());
    }
}
