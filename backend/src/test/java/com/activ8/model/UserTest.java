package com.activ8.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User testUser = new User("testUser","testuser@test.com" ,"password");


    @Test
    void getUsername() {
        String username = testUser.getUsername();
        assertEquals(username, "testUser");
    }
    @Test
    void getEmail() {
        String Email = testUser.getEmail();
        assertEquals(Email, "testuser@test.com");
    }

    @Test
    void getPassword() {
        String password = testUser.getPassword();
        assertEquals(password, "password");
    }

}