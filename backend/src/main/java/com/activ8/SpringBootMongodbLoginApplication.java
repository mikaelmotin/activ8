package com.activ8;

import com.activ8.model.StudyFolder;
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
import com.activ8.service.StudyFolderService;

import java.util.Optional;

@SpringBootApplication
public class SpringBootMongodbLoginApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	@Autowired
	private StudyFolderService studyFolderService;

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

		//Library(Folders)
		Optional<String> userId = userService.getUserIdByUsername("tester");
		String userIdString = userId.toString();
		//Create Folder
/*
		StudyFolder studyFolder = new StudyFolder( userIdString, "testYUUUR", "A test folder");
		studyFolderService.saveStudyFolder(studyFolder);
*/
		//Get All Folders
		studyFolderService.getAllStudyFolders(userIdString);
		System.out.println(studyFolderService.getAllStudyFolders(userIdString));

		//Delete Folder
		studyFolderService.deleteStudyFolder(userIdString,"6578d16cf1685e4c589fa24f");
	}

}
