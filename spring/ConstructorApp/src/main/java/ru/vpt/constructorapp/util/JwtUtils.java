package ru.vpt.constructorapp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration lifetime;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        claims.put("roles", rolesList);
        claims.put("sub", userDetails.getUsername());


        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + lifetime.toMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsername(String token) {
        return getClaimsFromToken(token).get("sub", String.class);

    }

    public List<String> getRoles(String token) {
        return getClaimsFromToken(token).get("roles", List.class);
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key()).build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}
