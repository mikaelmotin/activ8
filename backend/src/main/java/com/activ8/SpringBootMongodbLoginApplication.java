package com.activ8;

import com.activ8.eventbus.EventBus;
import com.activ8.eventbus.subscribers.StudySessionStartedEventSubscriber;

import com.activ8.model.StudySet;
import com.activ8.service.StudyFolderService;
import com.activ8.service.StudySetService;
import com.activ8.service.UserService;
import com.activ8.payload.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@ComponentScan({"com.activ8", "com.activ8.eventbus.subscribers"})
public class SpringBootMongodbLoginApplication implements CommandLineRunner {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private StudySessionStartedEventSubscriber studySessionStartedSubscriber;
    @Autowired
    private UserService userService;
    @Autowired
    private StudyFolderService studyFolderService;
    @Autowired
    private StudySetService studySetService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongodbLoginApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("APPLICATION STARTED");

        // Subscribe StudySessionStartedSubscriber to EventBus
        eventBus.subscribe(studySessionStartedSubscriber);

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

        //Create StudySet

        StudySet studySet = new StudySet(userIdString,"6578d03c67f47247a3d0539a", "testYUUURset", "A test StudySet");
        /*
        studySetService.saveStudySet(studySet);
*/
        //Get StudySet
        studySetService.getAllStudySets(studySet.studyFolderId());
        System.out.println(studySetService.getAllStudySets(studySet.studyFolderId()));

        //Delete StudySet
        studySetService.deleteStudySet(studySet.ownerId(),"657a17db3ebc2d0a16bdf2e1");

        //title to id algo for studyset
        /*
        String findTitle = "testYUUURset";
        List<StudySet> usersStudySet = studySetService.getAllStudySets(studySet.studyFolderId());
        for (StudySet set : usersStudySet){
            if (set.title().equals(findTitle)){
                //return set.id();
            }
        }*/


    }
}
