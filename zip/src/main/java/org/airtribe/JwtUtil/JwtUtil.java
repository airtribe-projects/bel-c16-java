package org.airtribe.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import java.util.Date;
import org.airtribe.entity.User;


public class JwtUtil {

  public static String generateJwtToken(User user) {
    return Jwts.builder()
        .subject(user.getUsername())
        .setIssuedAt(new Date())
        .claim("roles", "ROLE_" + user.getRole())
        .claim("emailVerified", user.isActive())
        .setExpiration(new Date(System.currentTimeMillis() + 8 * 60 * 60 * 1000))
        .signWith(SignatureAlgorithm.HS256,
            "secretKeyAirtribeTestingsecretKeyAirtribeTestingsecretKeyAirtribeTestingsecretKeyAirtribeTestingsecretKeyAirtribeTestingsecretKeyAirtribeTesting")
        .compact();

  }

  // seevice -> ȩrtifcates

  public static Claims validateJwtToken(String jwtToken) {
    try {
      Claims claims = Jwts.parser()
          .setSigningKey(
              "secretKeyAirtribeTestingsecretKeyAirtribeTestingsecretKeyAirtribeTestingsecretKeyAirtribeTestingsecretKeyAirtribeTestingsecretKeyAirtribeTesting")
          .build()
          .parseClaimsJws(jwtToken)
          .getBody();

      return claims;
    } catch (SignatureException exception) {
      System.err.println("Invalid JWT signature: " + exception.getMessage());
      return null;
    } catch (io.jsonwebtoken.ExpiredJwtException exception) {
      System.err.println("JWT token is expired: " + exception.getMessage());
      return null;
    } catch (io.jsonwebtoken.MalformedJwtException exception) {
      System.err.println("Invalid JWT token: " + exception.getMessage());
      return null;
    } catch (io.jsonwebtoken.UnsupportedJwtException exception) {
      System.err.println("JWT token is unsupported: " + exception.getMessage());
      return null;
    } catch (IllegalArgumentException exception) {
      System.err.println("JWT claims string is empty: " + exception.getMessage());
      return null;
    }
  }
}
