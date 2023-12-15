/**
 * Test class to verify the functionality of User class methods.
 */
package com.activ8.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User testUser = new User("testUser","testuser@test.com" ,"password",0);

    /**
     * Tests the retrieval of the username from the User object.
     */
    @Test
    void getUsername() {
        String username = testUser.getUsername();
        assertEquals(username, "testUser");
    }

    /**
     * Tests the retrieval of the email from the User object.
     */
    @Test
    void getEmail() {
        String email = testUser.getEmail();
        assertEquals(email, "testuser@test.com");
    }

    /**
     * Tests the retrieval of the password from the User object.
     */
    @Test
    void getPassword() {
        String password = testUser.getPassword();
        assertEquals(password, "password");
    }
}