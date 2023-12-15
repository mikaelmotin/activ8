package com.activ8.API;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTests {
    //Server needs to be running (Run SpringBootMongodbTerminalApplication.java)
    //We didn't have time to test all API Call methods, but here are the one that have to do with Study Folders
    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080"; // Set the base URI for all requests
    }

    // Tests that the API call SignUp creates a user successfully.
    @Test
    public void testSignUp() {
        //change nonExistingUsername to a non existing username ex. friedBanana6789
        //change nonExistingEmail@example.com to a non existing email ex. friedBanana6789@example.com
        given()
                .header("content-type", "application/json")
                .body("{\"username\": \"nonExistingUsername\", \"email\": \"nonExistingEmail@example.com\", \"password\": \"password\"}")
                .when()
                .post("/api/auth/signup")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) // Assert content type is JSON
                .body("message", equalTo("User registered successfully!")) // Assert message in the response body
                .log().all(); // Log response for debugging purposes
    }

    // Tests that the API call Sign In logs in a user successfully.
    @Test
    public void testSignIn() {
        //Copy the Set-Cookie value that is printed out, looks something like this : activ8=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZXIiLCJpYXQiOjE3MDI2NDY2NTYsImV4cCI6MTcwMjczMzA1Nn0.PsA4ypzWjmdWkaAPygPHDdp2XFGWMONsI5co5Wg8DAY
        Response response = given()
                .header("content-type", "application/json")
                .body("{\"username\": \"tester\", \"password\": \"password\"}")
                .when()
                .post("/api/auth/signin")
                .then()
                .statusCode(200)
                .header("Set-Cookie", notNullValue())
                .extract().response();

        String cookieValue = response.getHeader("Set-Cookie");
        System.out.println("Set-Cookie value: " + cookieValue);
    }

    // Tests that the API call CreateStudyFolder creates a Study Folder for a user successfully.
    @Test
    public void testCreateStudyFolder() {
        //Type in the copied Set-Cookie in header "Cookie".
        given()
                .header("Cookie", "PASTE SET-COOKIE HERE") //<-- Paste Set-Cookie here
                .header("content-type", "application/json")
                .body("{\"title\": \"testFolder31\", \"description\": \"A test folder\"}")
                .when()
                .post("/api/studyfolders")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("testFolder31"))
                .body("description", equalTo("A test folder"))
                .log().all(); // Log response for debugging purposes
    }

    // Tests that the API call GetAllStudyFolders returns All the Study Folders belonging to user successfully.
    @Test
    public void testGetAllStudyFolders() {
        //Type in the copied Set-Cookie in header "Cookie".
        //Copy one of the StudyFolderIds for a later test
        given()
                .header("Cookie", "PASTE SET-COOKIE HERE") // <-- Paste the Set-Cookie here
                .header("content-type", "application/json")
                .when()
                .get("/api/studyfolders")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body("id", notNullValue())
                .body("title", hasItem("testFolder"))
                .body("description", hasItem("A test folder"))
                .log().all(); // Log response for debugging purposes
    }

    // Tests that the API call EditExisitingStudyFolder edits a Study Folder for a user successfully.
    @Test
    public void testEditExistingStudyFolder() {
        //Type in the copied Set-Cookie in header "Cookie".
        //Type the folderId of a folder you want to edit, replace folderToEdit, from earlier tests there should be some folders created already

        given()
                .header("Cookie", "PASTE SET-COOKIE HERE") //<-- Paste Set-Cookie here
                .header("content-type", "application/json")
                .body("{\"title\": \"testFolder31\", \"description\": \"A test folder31\"}")
                .when()
                .put("/api/studyfolders/folderIdToEdit")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("testFolder31"))
                .body("description", equalTo("A test folder31"))
                .log().all(); // Log response for debugging purposes
    }
    // Tests that the API call DeleteStudyFolder deletes a Study Folder for a user successfully.
    @Test
    public void testDeleteStudyFolder() {
        //Type the copied Set-Cookie in header "Cookie".
        //Type the folderId of a folder you want to delete, replace TYPE HERE THE FOLDERID, from earlier tests there should be some folders created already
        given()
                .header("Cookie", "PASTE SET-COOKIE HERE") //<-- Paste Set-Cookie here
                .header("Content-Type", "application/json")
                .when()
                .delete("/api/studyfolders/{folderId}", "TYPE HERE THE FOLDERID")
                .then()
                .statusCode(200)
                .log().all();
    }
}