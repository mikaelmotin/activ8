package com.activ8.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activ8.model.User;
import com.activ8.payload.request.LoginRequest;
import com.activ8.payload.request.SignupRequest;
import com.activ8.payload.response.MessageResponse;
import com.activ8.payload.response.UserInfoResponse;
import com.activ8.repository.UserRepository;
import com.activ8.security.jwt.JwtUtils;
import com.activ8.service.UserDetailsImpl;


/**
 * Controller responsible for user authentication and authorization operations.
 * This controller supports user sign-in and sign-up functionalities, providing
 * secure authentication and authorization for accessing protected resources.
 * All endpoints are secured and require valid authentication.
 *
 * Endpoints:
 * - POST /api/auth/signin: Authenticates a user by validating the provided credentials
 *   and returns a JWT cookie along with user information upon successful authentication.
 * - POST /api/auth/signup: Registers a new user with the provided signup information.
 *
 * Security Features:
 * - Cross-Origin Resource Sharing (CORS) is enabled to allow requests from any origin.
 * - JWT (JSON Web Token) is used for secure and stateless user authentication.
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;


  /**
 * Authenticates a user by validating the provided credentials.
 *
 * @param loginRequest The login request containing the username and password.
 * @return ResponseEntity containing a JWT cookie and user information if authentication is successful.
 */
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
                                  loginRequest.getUsername(), 
                                  loginRequest.getPassword()
        ));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);


    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
        .body(new UserInfoResponse(userDetails.getId(),
                                   userDetails.getUsername(),
                                   userDetails.getEmail()));
  }

/**
 * Registers a new user with the provided signup information.
 *
 * @param signUpRequest The signup request containing the username, email, and password.
 * @return ResponseEntity indicating the success or failure of the registration process.
 */
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(), 
                         signUpRequest.getEmail(),
                         encoder.encode(signUpRequest.getPassword()),
                         0);

    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}