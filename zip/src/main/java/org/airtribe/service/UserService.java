package org.airtribe.service;

import java.util.Date;
import org.airtribe.JwtUtil.JwtUtil;
import org.airtribe.entity.User;
import org.airtribe.entity.VerificationToken;
import org.airtribe.repository.UserRepository;
import org.airtribe.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository _userRepository;

  @Autowired
  private VerificationTokenRepository _verificationTokenRepository;

  @Autowired
  private BCryptPasswordEncoder _passwordEncoder;

  public User registerUser(User user) {
    User userToBeSaved = new User();
    userToBeSaved.setUsername(user.getUsername());
    userToBeSaved.setPassword(_passwordEncoder.encode(user.getPassword()));
    userToBeSaved.setRole("USER");
    userToBeSaved.setActive(false);
    return _userRepository.save(userToBeSaved);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User fetchedUser = _userRepository.findByUsername(username);
    if (fetchedUser == null) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    } else {
      return org.springframework.security.core.userdetails.User
          .withUsername(fetchedUser.getUsername())
          .password(fetchedUser.getPassword())
          .roles(fetchedUser.getRole())
          .disabled(!fetchedUser.isActive())
          .build();
    }
  }

  public void persistVerificationToken(User registeredUser, String verificationToken) {
    VerificationToken tokenToBeRegistered = new VerificationToken();
    tokenToBeRegistered.setToken(verificationToken);
    tokenToBeRegistered.setUser(registeredUser);
    tokenToBeRegistered.setExpiryDate(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
    _verificationTokenRepository.save(tokenToBeRegistered);
  }

  public String verifyRegistration(String verificationToken) {
    VerificationToken token = _verificationTokenRepository.findByToken(verificationToken);
    if (token == null) {
      return "Invalid verification token, please re-register";
    }

    if (token.getExpiryDate().before(new Date())) {
      _verificationTokenRepository.delete(token);
      return "Verification token expired, please re-register";
    }

    User user = token.getUser();
    user.setActive(true);
    _userRepository.save(user);
    _verificationTokenRepository.delete(token);
    return "User verified successfully, you can now login";
  }

  // Is user registered in the sy
  // User is enabled or not
  // Match hashes of the password
  // Generate JWT Token and return it
  public String loginUser(String username, String password) {
    User registeredUser = _userRepository.findByUsername(username);
    if (registeredUser == null) {
      return "User not registered";
    }

    Boolean isEnabled = registeredUser.isActive();
    if (!isEnabled) {
      return "User not enabled, please verify your email";
    }

    Boolean isPasswordMatch = _passwordEncoder.matches(password, registeredUser.getPassword());
    if (!isPasswordMatch) {
      return "Invalid credentials, please provide correct username or password";
    }

    return JwtUtil.generateJwtToken(registeredUser);

  }
}
