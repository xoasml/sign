package com.basic.sign.domain.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@ToString
@Getter @Setter
public class UserDtoRequest {

    @NotEmpty(message = "필수값 입니다.")
    private String id;

    @NotEmpty(message = "필수값 입니다.")
    private String password;

}
