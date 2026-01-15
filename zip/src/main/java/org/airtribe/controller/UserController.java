package org.airtribe.controller;

import org.airtribe.entity.User;
import org.airtribe.entity.UserDTO;
import org.airtribe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

  @Autowired
  private UserService _userService;

  @PostMapping("/register")
  public User registerUser(@RequestBody User user) {
    User registeredUser = _userService.registerUser(user);
    String verificationToken = java.util.UUID.randomUUID().toString();
    String verificationTokenUrl = "http://localhost:1200/verifyRegistration?token=" + verificationToken;
    System.out.println("Please verify your registration by clicking on the following link: " + verificationTokenUrl);
    _userService.persistVerificationToken(registeredUser, verificationToken);
    return registeredUser;
  }

  @PostMapping("/verifyRegistration")
  public String verifyRegistration(@RequestParam("token") String verificationToken) {
    return _userService.verifyRegistration(verificationToken);
  }

  @PostMapping("/signin")
  public String loginUser(@RequestBody UserDTO userDTO) {
    return _userService.loginUser(userDTO.getUsername(), userDTO.getPassword());
  }
}
