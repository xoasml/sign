package com.basic.sign.domain.sign;

import com.basic.sign.domain.sign.dto.request.FindByIdDtoRequest;
import com.basic.sign.domain.user.dto.request.SignUpDtoRequest;
import com.basic.sign.util.aop.annotation.MethodInfoLogging;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/sign")
public class SignController {

    private final SignService signService;

    /**
     * 회원가입
     */
    @MethodInfoLogging(description = "회원가입")
    @PostMapping("/signup")
    public String signUp(@Valid @RequestBody SignUpDtoRequest request) {

        FindByIdDtoRequest findByIdDtoRequest = new FindByIdDtoRequest();
        findByIdDtoRequest.setUserId(request.getUserId());

        if(signService.findById(findByIdDtoRequest) == 0) {
            signService.signUp(request);
            return "SUCCESS";
        } else {
            return "PRIMARY KEY EXISTS";
        }
    }

    @MethodInfoLogging(description = "아이디 유효 체크")
    @PostMapping("/findbyid")
    public Long findById(@Valid @RequestBody FindByIdDtoRequest request){
        return signService.findById(request);
    }

}
