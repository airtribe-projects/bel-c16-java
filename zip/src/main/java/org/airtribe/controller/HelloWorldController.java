package org.airtribe.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
  @GetMapping("/hello")
  public String helloWorld() {
    return "Hello, World!";
  }

  @GetMapping("/test")
  @PreAuthorize("hasAnyRole('USER')")
  public String test() {
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    String username = authentication.getName();
//    System.out.println("Authenticated user: " + username);
//
//    if (authentication.getAuthorities().isEmpty()) {
//      return "No authorities found for user: " + username;
//    }
//
//    if (authentication.getAuthorities().toString().equals("[ROLE_USER]")) {
//      return "User does not have privilege to this endpoint";
//    }

    return "Test endpoint is working!";
  }

  @GetMapping("/someEndpoint")
  public String someEndpoint() {
    return "Some endpoint is working!";
  }
}
