package com.activ8.rest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTests {
    //Response response =
    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080"; // Set the base URI for all requests
    }

    // Tests that the API call Sign creates a user successfully.
    /*
    @Test
    public void testSignUp() {
        given()
                .header("content-type", "application/json")
                .body("{\"username\": \"tester9\", \"email\": \"tester9@example.com\", \"password\": \"password\"}")
                .when()
                .post("/api/auth/signup")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) // Assert content type is JSON
                .body("message", equalTo("User registered successfully!")) // Assert message in the response body
                .log().all(); // Log response for debugging purposes
    } */

    @Test
    public void testSignIn() {
        Response response = given()
                .header("content-type", "application/json")
                .body("{\"username\": \"tester\", \"password\": \"password\"}")
                .when()
                .post("/api/auth/signin")
                .then()
                .statusCode(200)
                .header("Set-Cookie", notNullValue()) // Check if the Set-Cookie header is not null
                .extract().response(); // Extract the response

        // Retrieve the value of the Set-Cookie header from the response
        String cookieValue = response.getHeader("Set-Cookie");
        System.out.println("Set-Cookie value: " + cookieValue);
    }

    @Test
    public void testGetAllStudyFolders() {
        given()
                .header("Cookie", "activ8=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZXIiLCJpYXQiOjE3MDE4MDI4MjUsImV4cCI6MTcwMTg4OTIyNX0.FNfyWwXYgldL4ujYysYPQox8xp6ibRq7SJxwFUgqemY")
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

    @Test
    public void testCreateStudyFolders() {
        given()
                .header("Cookie", "activ8=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZXIiLCJpYXQiOjE3MDE4Nzc1NjgsImV4cCI6MTcwMTk2Mzk2OH0.aWWqkHMrU3yeCeT1REBrGWuaqKe6yR9VE7F3wqlzDBM")
                .header("content-type", "application/json")
                .body("{\"title\": \"testFolder\", \"description\": \"A test folder\"}")
                .when()
                .post("/api/studyfolders")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) // Assert content type is JSON
                .body("title", equalTo("testFolder")) // Assert 'title' field in the response body
                .body("description", equalTo("A test folder")) // Assert 'description' field in the response body
                .log().all(); // Log response for debugging purposes
    }

    @Test
    public void testEditExistingStudyFolders() {
        given()
                .header("Cookie", "activ8=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZXIiLCJpYXQiOjE3MDE4Nzc1NjgsImV4cCI6MTcwMTk2Mzk2OH0.aWWqkHMrU3yeCeT1REBrGWuaqKe6yR9VE7F3wqlzDBM")
                .header("content-type", "application/json")
                .body("{\"title\": \"testFolder2\", \"description\": \"A test folder2\"}")
                .when()
                .put("/api/studyfolders/65635676cd44ec3fc70fbcdf")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) // Assert content type is JSON
                .body("title", equalTo("testFolder2")) // Assert 'title' field in the response body
                .body("description", equalTo("A test folder2")) // Assert 'description' field in the response body
                .log().all(); // Log response for debugging purposes
    }
    /*@Test
    public void testGetAllFlashcardsInStudySet() {
        given()
                .header("Cookie", "activ8=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTAyIiwiaWF0IjoxNzAxMjgzNjI5LCJleHAiOjE3MDEzNzAwMjl9.21ukDUCqpEGA2UTRv6zyOdy3MzCHnEjJKQH7BtIVFZo")
                .header("content-type", "application/json")
                .when()
                .get("/api/flashcards/all/6567877386dcd351d6f3515e")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                // Add assertions as needed for the response body
                .body("property", equalTo("expectedValue"))
                .body("listProperty", hasSize(3))
                // Add more assertions based on the response structure
                .log().all(); // Log response for debugging purposes
    }
*/
}