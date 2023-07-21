package com.basic.sign.domain.sign;

import com.basic.sign.domain.user.dto.request.UserDtoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/sign")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public void signUp(@Valid @RequestBody UserDtoRequest request) {

        System.out.println("받았다 십탱아");
        System.out.println(request);

    }

}
