package com.basic.sign;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;


class SignApplicationTests {

	private void printToken(String token){

	}

	@DisplayName("1. jjwt 를 이용한 토큰 테스트")
	@Test
	void contextLoads() {
		String okta_token = Jwts.builder().addClaims(
				Map.of("name", "hoon", "price", 3000)
		).signWith(SignatureAlgorithm.HS256, "hoon")
				.compact();

		System.out.println(okta_token);
	}

	@DisplayName("2. java-jwt 를 이용한 토큰 테스트")
	@Test
	void test(){


	}

}
