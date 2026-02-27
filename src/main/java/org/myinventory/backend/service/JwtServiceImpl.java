package org.myinventory.backend.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.xml.datatype.DatatypeConstants;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private  String secretKey = "dfdfsgfdgdf34lfskdjflkgjlfk!!!R#fbfbf";

    @Override
    public String getToken(String key, Object value) {

        Date now = new Date();
        Date expTime = new Date();
        expTime.setTime(expTime.getTime() + 1000 * 60 * 20);
        SecretKey keyBytes = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));


        return Jwts.builder()
                .setIssuedAt(now)          // 발급 시간
                .setExpiration(expTime)    // 만료 시간
                .claim(key, value)         // payload 값
                .signWith(keyBytes, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public Claims getClaims(String token) {
        if(token != null && !"".equals(token))
        {
            try{
                // JWT 만들 때 썼던 secretKey 와 동일한 키 다시 생성, 키가 다르면 검증 실패
                SecretKey keyBytes = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
                // 토큰 구조 정상 확인, 서명 검증, 만료시간체크
                return Jwts.parserBuilder().setSigningKey(keyBytes).build().parseClaimsJws(token).getBody();
            }catch (ExpiredJwtException e)
            {
                // jwt 만료
            }catch (JwtException e)
            {
                // jwt 유효하지 않음
            }
        }
        return null;
    }
}
