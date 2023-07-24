package com.basic.sign.domain.sign;

import com.basic.sign.domain.sign.dto.request.FindByIdDtoRequest;
import com.basic.sign.domain.user.dto.request.SignUpDtoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SignService {

    private final SignRepository signRepository;

    public void signUp(SignUpDtoRequest request) {

        signRepository.signUp(request);

    }

    public Long findById(FindByIdDtoRequest request) {

        return signRepository.findById(request);

    }


}
