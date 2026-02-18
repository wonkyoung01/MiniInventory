package org.myinventory.backend.controller;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.myinventory.backend.entity.Item;
import org.myinventory.backend.entity.Member;
import org.myinventory.backend.repository.ItemRepository;
import org.myinventory.backend.repository.MemberRepository;
import org.myinventory.backend.service.JwtService;
import org.myinventory.backend.service.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    JwtService jwtService;

    @PostMapping("/api/account/login")
   public ResponseEntity<?> login(@RequestBody Member params,
                               HttpServletResponse res){

      Member member = memberRepository.findByLoginIdAndPassword(params.getLoginId(),params.getPassword());

      if(member != null) {
          JwtService jwtService = new JwtServiceImpl();
          int id = member.getId();
          String token =  jwtService.getToken("id", id);

          ResponseCookie cookie = ResponseCookie.from("token", token)
                  .httpOnly(true)
                  .secure(false) // HTTPS면 true
                  .path("/")
                  .maxAge(60 * 10)
                  .sameSite("Lax")     // 🔥 핵심 Lax는 같은 사이트 요청일때만 가능, 지금은 proxy로 우회해서 같은도메인사용중 * SameSite=None 쓰면  Secure=true(HTTPS) 필수
                  .build();

          return ResponseEntity.ok()
                  .header("Set-Cookie", cookie.toString())
                  .build();
      }

      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 실패");
    }


    @GetMapping("/api/account/check")
    public ResponseEntity<?> check(@CookieValue(value="token", required = false) String token)
    {
       Claims claims = jwtService.getClaims(token);

        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Integer id = claims.get("id", Integer.class);

        return ResponseEntity.ok(id);
    }


}
