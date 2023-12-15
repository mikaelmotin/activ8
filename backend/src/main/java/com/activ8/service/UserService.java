/**
 * Service class for user-related operations such as user registration, authentication, and retrieval of user details.
 */
package com.activ8.service;

import com.activ8.model.User;
import com.activ8.payload.request.LoginRequest;
import com.activ8.payload.request.SignupRequest;
import com.activ8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user if the username and email are unique, saving the user's details into the database.
     *
     * @param signupRequest Object containing user details for registration
     * @throws RuntimeException if the provided username or email is already taken
     */
    public void registerUser(SignupRequest signupRequest) {
        // Check if the username or email already exists
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            System.out.println("Username is already taken!");
            throw new RuntimeException("Username is already taken!");
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            System.out.println("Email is already in use!");
            throw new RuntimeException("Email is already in use!");
        }

        // Create a new user entity and save it to the database
        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()), 0);

        userRepository.save(user);
        System.out.println("Successfully created user!");
    }

    /**
     * Authenticates a user based on the provided login credentials.
     *
     * @param loginRequest Object containing user login details
     * @return Optional containing the user information if authentication is successful, otherwise empty
     */
    public Optional<User> authenticateUser(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Check if the password matches the encoded password in the database
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                System.out.println("Successfully Logged In!");
                return userOptional;
            }
        }
        System.out.println("Wrong Password or Username!");
        return Optional.empty(); // Return empty optional if authentication fails
    }

    /**
     * Retrieves the user ID by username from the database.
     *
     * @param username Username of the user
     * @return Optional containing the user ID if the username exists, otherwise empty
     */
    public Optional<String> getUserIdByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return Optional.of(user.getId());
        } else {
            return Optional.empty();
        }
    }
}