package study.springboot.security.oauth.jwttutorial.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TokenProvider {
  private static final String AUTHORITIES_KEY ="auth";

  private final String secret;
  private final long tokenValidityInMilliseconds;
  private SecretKey key;

  public TokenProvider(@Value("${jwt.secret}") String secret,
                       @Value("${jwt.token-validity-in-milliseconds}") long tokenValidityInMilliseconds) {
    this.secret = secret;
    this.tokenValidityInMilliseconds = tokenValidityInMilliseconds * 1000;
  }

  @PostConstruct
  public void init() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    this.key = Keys.hmacShaKeyFor(keyBytes);
  }

  public String createToken(Authentication authentication) {
    // 권한
    String authorities = authentication.getAuthorities().stream()
      .map(GrantedAuthority::getAuthority)
      .collect(Collectors.joining(","));

    long now = (new Date()).getTime();
    Date validity = new Date(now + this.tokenValidityInMilliseconds);

    return Jwts.builder()
      .setSubject(authentication.getName())
      .claim(AUTHORITIES_KEY, authorities)
      .signWith(this.key, SignatureAlgorithm.HS512)
      .setExpiration(validity)
      .compact();
  }

  public Authentication getAuthentication(String token) {
    Claims body = Jwts
      .parserBuilder()
      .setSigningKey(this.key)
      .build()
      .parseClaimsJws(token)
      .getBody();

    List<SimpleGrantedAuthority> authorities = Arrays.stream(body.get(AUTHORITIES_KEY).toString().split(","))
      .map(SimpleGrantedAuthority::new)
      .collect(Collectors.toList());

    User principal = new User(body.getSubject(), "", authorities);
    return new UsernamePasswordAuthenticationToken(principal, token, authorities);
  }

  public boolean validateToken(String token) {
    return !Objects.isNull(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token));
  }
}
