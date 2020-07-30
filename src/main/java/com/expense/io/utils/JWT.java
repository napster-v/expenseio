package com.expense.io.utils;


import com.expense.io.jwt.Token;
import com.expense.io.jwt.TokenResponse;
import com.expense.io.project.auth.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

@Service
public class JWT {

    public static final String SECRET = "*xdoq1o+p($6nnt9p^l=2smj5tpl^v&#m7luh0z)u69odvc=4$/n";

    public TokenResponse generateAuthTokens(AppUser user) {
        return createToken(user);
    }


    private TokenResponse createToken(AppUser user) {
        final String jws = Jwts.builder()
                               .setClaims(Map.of("user", user.getId()))
                               .setSubject(user.getUsername())
                               .setIssuedAt(Date.valueOf(LocalDate.now()))
                               .setExpiration(Date.valueOf(LocalDate.now()
                                                                    .plusDays(1)))
                               .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                               .setHeader(Map.of("typ", "JWT"))
                               .compact();

        return new TokenResponse(new Token(jws, jws), user);
    }

    public Claims getClaims(String token) throws SignatureException {

        try {
            return Jwts.parserBuilder()
                       .setSigningKey(SECRET.getBytes())
                       .build()
                       .parseClaimsJws(token)
                       .getBody();

        } catch (SignatureException e) {
            e.printStackTrace();
            throw new SignatureException("Token not valid");
        }
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }
}
