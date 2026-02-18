package org.myinventory.backend.service;

import io.jsonwebtoken.Claims;

public interface JwtService {

    //토큰 생성
    public String getToken(String key, Object value);

    Claims getClaims(String token);
}
