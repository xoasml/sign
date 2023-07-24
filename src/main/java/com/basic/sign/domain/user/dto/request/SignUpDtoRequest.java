package com.basic.sign.domain.user.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@ToString
@Getter @Setter
public class SignUpDtoRequest {

    @NotEmpty(message = "필수값 입니다.")
    private String userId;

    @NotEmpty(message = "필수값 입니다.")
    private String userPassword;

}
