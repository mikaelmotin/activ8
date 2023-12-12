package com.activ8.service;
import com.activ8.model.User;
import com.activ8.service.UserService;
import com.activ8.payload.request.LoginRequest;
import com.activ8.payload.request.SignupRequest;
import com.activ8.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        // "testUser", "test@test.com", "password"
        SignupRequest signupRequest = new SignupRequest();

        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);

        userService.registerUser(signupRequest);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_UsernameExists() {
        //"existingUser", "test@test.com", "password"
        SignupRequest signupRequest = new SignupRequest();

        when(userRepository.existsByUsername(signupRequest.getUsername())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userService.registerUser(signupRequest));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testRegisterUser_EmailExists() {
        //"testUser", "existing@test.com", "password"
        SignupRequest signupRequest = new SignupRequest();

        when(userRepository.existsByUsername(signupRequest.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(signupRequest.getEmail())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userService.registerUser(signupRequest));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testAuthenticateUser_Success() {
        //"testUser", "password"
        LoginRequest loginRequest = new LoginRequest();
        User existingUser = new User("testUser", "test@test.com", "encodedPassword");

        when(userRepository.findByUsername(loginRequest.getUsername())).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.matches(loginRequest.getPassword(), existingUser.getPassword())).thenReturn(true);

        Optional<User> authenticatedUser = userService.authenticateUser(loginRequest);

        assertTrue(authenticatedUser.isPresent());
        assertEquals(existingUser, authenticatedUser.get());
    }

    @Test
    void testAuthenticateUser_WrongCredentials() {
        //"testUser", "wrongPassword"
        LoginRequest loginRequest = new LoginRequest();

        when(userRepository.findByUsername(loginRequest.getUsername())).thenReturn(Optional.empty());

        Optional<User> authenticatedUser = userService.authenticateUser(loginRequest);

        assertTrue(authenticatedUser.isEmpty());
    }
}
