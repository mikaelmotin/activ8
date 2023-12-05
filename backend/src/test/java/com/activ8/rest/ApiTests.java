package com.activ8.rest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080"; // Set the base URI for all requests
    }

    @Test
    public void testGetStudyFolders() {
        given()
                .header("Cookie", "activ8=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIyMjAxIiwiaWF0IjoxNzAxMTgyMTA5LCJleHAiOjE3MDEyNjg1MDl9.G854Qj4XoMNCKWZxJ3de7BI1ENnu6mm-GvMk4IrBiaE")
                .header("content-type", "application/json")
                .when()
                .get("/api/studyfolders")
                .then()
                .statusCode(200)
                // Add assertions as needed
                .body("property", equalTo("expectedValue"))
                .body("listProperty", hasSize(3))
                .header("Content-Type", "application/json")
                .log().all(); // Log response for debugging purposes
    }

    @Test
    public void testPostStudyFolders() {
        given()
                .header("Cookie", "activ8=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIyMjAxIiwiaWF0IjoxNzAxMTgyMTA5LCJleHAiOjE3MDEyNjg1MDl9.G854Qj4XoMNCKWZxJ3de7BI1ENnu6mm-GvMk4IrBiaE")
                .header("content-type", "application/json")
                .body("{\"title\": \"testFolder\", \"description\": \"A test folder\"}")
                .when()
                .post("/api/studyfolders")
                .then()
                .statusCode(200)
                // Add assertions as needed
                .log().all(); // Log response for debugging purposes
    }

    @Test
    public void testPutStudyFolders() {
        given()
                .header("Cookie", "activ8=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTAwIiwiaWF0IjoxNzAxMDAyNzc4LCJleHAiOjE3MDEwODkxNzh9.8ud65wllsuZ2RUHDoW31X1O-hRsW6qQ5NF68kuwo7O4")
                .header("content-type", "application/json")
                .body("{\"title\": \"test title2UU\", \"description\": \"test desc.2UU\"}")
                .when()
                .put("/api/studyfolders/65635676cd44ec3fc70fbcdf")
                .then()
                .statusCode(200)
                // Add assertions as needed
                .log().all(); // Log response for debugging purposes
    }

    @Test
    public void testSignUp() {
        given()
                .header("content-type", "application/json")
                .body("{\"username\": \"tester\", \"email\": \"tester@example.com\", \"password\": \"password\"}")
                .when()
                .post("/api/auth/signup")
                .then()
                .statusCode(200)
                // Add assertions as needed
                .log().all(); // Log response for debugging purposes
    }

    @Test
    public void testSignIn() {
        given()
                .header("content-type", "application/json")
                .body("{\"username\": \"tester\", \"password\": \"password\"}")
                .when()
                .post("/api/auth/signin")
                .then()
                .statusCode(200)
                .header("Set-Cookie", notNullValue()) // Check if the Set-Cookie header is not null
                .log().all(); // Log response for debugging purposes
    }
}