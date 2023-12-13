package com.activ8;

import com.activ8.eventbus.EventBus;
import com.activ8.eventbus.subscribers.StudySessionStartedEventSubscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.activ8", "com.activ8.eventbus.subscribers"})
public class SpringBootMongodbLoginApplication implements CommandLineRunner {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private StudySessionStartedEventSubscriber studySessionStartedSubscriber;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongodbLoginApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("APPLICATION STARTED");

        // Subscribe StudySessionStartedSubscriber to EventBus
        eventBus.subscribe(studySessionStartedSubscriber);

        // Rest of your application initialization logic...
    }
}
