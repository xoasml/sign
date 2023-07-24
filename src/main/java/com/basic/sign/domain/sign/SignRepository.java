package com.basic.sign.domain.sign;

import com.basic.sign.domain.sign.dto.request.FindByIdDtoRequest;
import com.basic.sign.domain.user.dto.request.SignUpDtoRequest;

import com.basic.sign.entity.QUserInfo;
import com.basic.sign.entity.UserInfo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class SignRepository {

    private final JPAQueryFactory factory;

    private final EntityManager em;

    QUserInfo userInfo = QUserInfo.userInfo;

    public void signUp(SignUpDtoRequest request) {

            em.persist(
                    UserInfo.builder()
                            .userId(request.getUserId())
                            .userPassword(request.getUserPassword())
                            .build()
            );

    }

    public Long findById(FindByIdDtoRequest request) {

        return factory.select(userInfo.count())
                .from(userInfo)
                .where(userInfo.userId.eq(request.getUserId()))
                .fetchOne();

    }

}
