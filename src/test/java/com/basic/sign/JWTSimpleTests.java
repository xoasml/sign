package com.basic.sign;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.Date;


class SignApplicationTests {

	private void printToken(String token){
		String[] tokens = token.split("\\.");
		System.out.println("Header : " + new String (Base64.getDecoder().decode(tokens[0])));
		System.out.println("body : " + new String (Base64.getDecoder().decode(tokens[1])));
	}

	@DisplayName("1. jjwt 를 이용한 토큰 테스트")
	@Test
	void contextLoads() {
//		String okta_token = Jwts.builder().addClaims(
//				Map.of("name", "hoon", "price", 3000)
//		).signWith(SignatureAlgorithm.HS256, "hoon")
//				.compact();
//
//		System.out.println(okta_token);
//		printToken(okta_token);
//
//		Jws<Claims> tokenInfo = Jwts.parser().setSigningKey("hoon").parseClaimsJws(okta_token);
//		System.out.println(tokenInfo);
	}


	@DisplayName("2. java-jwt 를 이용한 토큰 테스트")
	@Test
	void test(){
		String oauth0_token = JWT.create()
				.withClaim("name", "kim")  // 이름 필드, 값 추가
				.withClaim("price", 5000)  // 금액 필드, 값 추가
				.sign(Algorithm.HMAC256("kim")); // 시그니처 추가

		System.out.println(oauth0_token);
		printToken(oauth0_token);
		DecodedJWT verified = JWT.require(Algorithm.HMAC256("kim")).build().verify(oauth0_token);
		System.out.println(verified.getClaims());

	}

	@DisplayName("3. 만료 시간 테스트")
	@Test
	void test_3() throws InterruptedException {

		final Algorithm AL = Algorithm.HMAC256("hoon"); // 시그니처는 상수이기 때문에 파이널로 선언

		String token = JWT.create().withSubject("a1234")
				.withNotBefore(new Date(System.currentTimeMillis()+1000)) // 토큰 유효 시작시간 추가 1초
				.withExpiresAt(new Date(System.currentTimeMillis()+3000)) // 토큰 유효 만료시간 추가 3초
				.sign(AL); // 시그니처 추가

		Thread.sleep(1500); // 1 ~ 3 초로 설정해야 토큰이 유효함

		try {
			DecodedJWT verify = JWT.require(AL).build().verify(token);
			System.out.println("유효한 토큰 입니다.");
			System.out.println(verify.getClaims());
		}catch (Exception ex) {
			System.out.println("유효하지 않은 토큰 입니다...");
			DecodedJWT decode = JWT.decode(token);
			System.out.println(decode.getClaims());

		}


	}
}
