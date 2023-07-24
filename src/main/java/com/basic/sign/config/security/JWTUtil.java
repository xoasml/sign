package com.basic.sign.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.basic.sign.entity.UserInfo;

import java.time.Instant;

public class JWTUtil {

    private static final Algorithm ALGORITHM = Algorithm.HMAC256("hoon");
    private static final long AUTH_TIME = 20*60;
    private static final long REFRESH_TIME = 60*60+7;

    
    /**
     * AuthToken 생성
     */
    public static String makeAuthToken(UserInfo userInfo) {
        return JWT.create().withSubject(userInfo.getUserId())
                .withClaim("exp", Instant.now().getEpochSecond()+AUTH_TIME)
                .sign(ALGORITHM);
    }
    
    /**
     * RefreshToken 생성
     */
    public static String makeRefreshToken(UserInfo userInfo) {
        return JWT.create().withSubject(userInfo.getUserId())
                .withClaim("exp", Instant.now().getEpochSecond()+REFRESH_TIME)
                .sign(ALGORITHM);
    }

    public static VerifyResult verify(String token){
        try {
            DecodedJWT verify = JWT.require(ALGORITHM).build().verify(token);
            JWT.require(ALGORITHM).build().verify(token);
            return VerifyResult.builder().success(true)
                    .userId(verify.getSubject()).build();
        }catch(Exception ex) {
            DecodedJWT decode = JWT.decode(token);
            return VerifyResult.builder().success(false)
                    .userId(decode.getSubject()).build();
        }
    }







}
