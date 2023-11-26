package com.activ8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.activ8.controller.AuthController;
import com.activ8.model.User;
import com.activ8.payload.request.SignupRequest;
import com.activ8.repository.UserRepository;

@SpringBootApplication
public class SpringBootMongodbLoginApplication implements CommandLineRunner{
	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthController authController;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongodbLoginApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//createUsers();
		//showAllUsers();
		System.out.println("APPLICATION STARTED");
	}

	private void createUsers() {
		

		//authController.registerUser(new SignupRequest());
	}

	// private void showAllUsers() {
	// 	authController.authenticateUser(null)
	// }




	// public String getItemDetails(User user) {

	// 	System.out.println(
	// 	"Name: " + user.getUsername() + 
	// 	", \nPass: " + user.getPassword() +
	// 	", \nEmail: " + user.getEmail()
	// 	);
		
	// 	return "";
	// }
}
