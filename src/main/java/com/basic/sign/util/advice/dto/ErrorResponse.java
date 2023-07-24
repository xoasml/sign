package com.basic.sign.util.advice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class ErrorResponse {

    String statusCode;
    String requestUrl;
    String time;
    String code;
    String message;
    String resultCdoe;

    List<Error> errorList;

}
