package org.airtribe.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.airtribe.JwtUtil.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // request would have authorizatipon header - jwt token
    // if token is null -> reject request -> unauthorized
    // if token is provided
       // validate the token
        // check the signature
        // checkk the exprity
        // check the secret
    // if token is invalid -> reject request -> unauthorized
    // if token is valid -> Let the user access the endpoint

    String jwtToken = request.getHeader("Authorization");
    if (jwtToken == null) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("Missing Authorization header");
      return;
    }

    Claims claims = JwtUtil.validateJwtToken(jwtToken);
    logger.info("Claims: " + claims);

    String username = claims.getSubject();
    String role = claims.get("roles", String.class);
    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(username, null, authorities);

    SecurityContextHolder.getContext().setAuthentication(authentication);

    if (claims == null) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("Invalid or expired JWT token");
      return;
    }

    filterChain.doFilter(request, response);


  }

  @Override
  public boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String path = request.getRequestURI();
    return path.contains("/register") || path.contains("/verifyRegistration") || path.contains("/signin");
  }
}
