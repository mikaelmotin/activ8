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
                passwordEncoder.encode(signupRequest.getPassword()));

        userRepository.save(user);
        System.out.println("Successfully created user!");
    }

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