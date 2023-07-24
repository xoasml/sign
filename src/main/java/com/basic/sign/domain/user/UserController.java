package com.basic.sign.domain.user;

import com.basic.sign.domain.user.dto.request.SignUpDtoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     */
    @PostMapping("/1111")
    public void signUp(@Valid @RequestBody SignUpDtoRequest request) {


        System.out.println(request);

    }

}
