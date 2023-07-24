package com.basic.sign.domain.sign.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@ToString
//@Builder
@Getter @Setter
public class FindByIdDtoRequest {

    @NotEmpty(message = "필수값 입니다.")
    private String userId;

}
