package com.basic.sign.util.advice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Error {

    private String because;
    private String message;
    private String invalidValue;

}
