package jpabook.jpashop.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * jwt 생성 유틸
 */
public class JwtUtil {

    /**
     * token expire 여부 로직
     * 만약에 expire 가 지금 시간 (new Date()) 보다 더 전이라면, 
     * 토큰 소멸 -> 인증 불허
     */
    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration().before(new Date());

    }

    public static String createJwt(String userName, String secretKey, Long expiredMs) {

        Claims claims = Jwts.claims();
        claims.put("userName", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

    }
}
