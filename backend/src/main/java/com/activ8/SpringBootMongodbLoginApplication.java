package com.activ8;

import com.activ8.payload.request.LoginRequest;
import com.activ8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.activ8.controller.AuthController;
import com.activ8.model.User;
import com.activ8.payload.request.SignupRequest;
import com.activ8.repository.UserRepository;
@SpringBootApplication
public class SpringBootMongodbLoginApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongodbLoginApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("APPLICATION STARTED");
		//Sign Up
		/*SignupRequest signupRequest = new SignupRequest();
		signupRequest.setUsername("tester00");
		signupRequest.setEmail("tester00@example.com");
		signupRequest.setPassword("password");
		userService.registerUser(signupRequest);*/
		//Sign In
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("tester");
		loginRequest.setPassword("password");
		userService.authenticateUser(loginRequest);
	}

}
