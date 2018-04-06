package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.UserProfile;
import io.restassured.response.ValidatableResponse;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegUserControllerTest {

    static final String url = "http://localhost:8080/user/";

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void userLogout() {
    }

//    @Test
//    public void userSignup() {
//        Map map = new HashMap();
//        map.put("username", "testuser");
//        map.put("password", "123");
//        map.put("email", "testuser@admin.com");
//
//        ValidatableResponse validatableResponse = given()
//                .contentType("application/json")
//                .body(map)
//                .when().post(url + "signup")
//                .then().statusCode(200);
//        validatableResponse.body("username", equalTo("testuser"));
//        validatableResponse.body("role", equalTo("ROLE_USER"));
//    }

    @Test
    public void userLogin() {
        ValidatableResponse validatableResponse = given()
                .param("email", "user@admin.com")
                .param("password", "123")
                .when().post(url + "login")
                .then().statusCode(200);
        validatableResponse.body("username", equalTo("user"));
        validatableResponse.body("role", equalTo("ROLE_USER"));
    }

    @Test
    public void checkUsername() {

        ValidatableResponse validatableResponse = given()
                .pathParam("username", "testuser")
                .when().post(url + "check/username/{username}")
                .then().statusCode(200);
        validatableResponse.body("taken", equalTo(false));

    }

    @Test
    public void checkUsernameNotTaken() {

        ValidatableResponse validatableResponse = given()
                .pathParam("username", "admin")
                .when().post(url + "check/username/{username}")
                .then().statusCode(200);
        validatableResponse.body("taken", equalTo(true));

    }

    @Test
    public void checkUserEmail() {

        ValidatableResponse validatableResponse = given()
                .pathParam("email", "testuser@admin.com")
                .when().post(url + "check/email/{email}")
                .then().statusCode(200);
        validatableResponse.body("taken", equalTo(false));

    }

    @Test
    public void changePassword() {
        ValidatableResponse validatableResponse = given()
                .param("old_password", "123")
                .param("new_password", "234")
                .when().post(url + "change/password")
                .then().statusCode(200);
        validatableResponse.body("username", equalTo("admin"));
        validatableResponse.body("role", equalTo("ROLE_ADMIN"));
    }


    @Test
    public void changePasswordFail() {
        ValidatableResponse validatableResponse = given()
                .param("old_password", "345")
                .param("new_password", "123")
                .when().post(url + "change/password")
                .then().statusCode(400);
    }

    @Test
    public void changeProfilePicture() {
    }

    @Test
    public void getAvatar() {


    }

    @Test
    public void upload() {
    }

    @Test
    public void verifyCritic() {
    }

    @Test
    public void updateProfile() {

//        HashMap hashMap = new HashMap<>();
//        hashMap.put("id", "1");
//        hashMap.put("name", "Bin Zhou");
//        hashMap.put("isCritic", false);

        UserProfile userProfile = new UserProfile("Bin Zhou", null, "Hello", null, null, false, false, null);
        userProfile.setId(1);


        ValidatableResponse validatableResponse = given()
                .contentType("application/json")
                .body(userProfile)
                .when().post(url + "update/profile")
                .then().statusCode(200);

        validatableResponse.body("name", equalTo("Bin Zhou"));
//        validatableResponse.body("biography", equalTo("This is me"));


    }

//    @Test
//    public void makeSummaryPrivate() {
//
//        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwidXNlcm5hbWUiOiJ1c2VyIiwicm9sZSI6IlJPTEVfVVNFUiIsImlkIjozLCJwcm9maWxlX2lkIjoxfQ.obr4RphbYxzPwYJhSRXhsIDcogQkWakkpE25KT8zMpg";
//        ValidatableResponse validatableResponse = given()
//                .when().post(url + "update/profile")
//                .then().statusCode(200);
//
//
//
//    }
}