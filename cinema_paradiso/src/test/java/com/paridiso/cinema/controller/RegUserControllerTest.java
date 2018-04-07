package com.paridiso.cinema.controller;

import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//import com.paridiso.cinema.entity.UserProfileBuilder;

@FixMethodOrder(MethodSorters.DEFAULT)
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
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwidXNlcm5hbWUiOiJ1c2VyIiwicm9sZSI6IlJPTEVfVVNFUiIsImlkIjozLCJwcm9maWxlX2lkIjoxfQ.obr4RphbYxzPwYJhSRXhsIDcogQkWakkpE25KT8zMpg";
        ValidatableResponse validatableResponse = given()
                .auth().preemptive().oauth2(jwtToken)
                .param("old_password", "123")
                .param("new_password", "234")
                .when().post(url + "change/password")
                .then().statusCode(200).body("success", equalTo(true));
    }


    @Test
    public void changePasswordFail() {
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwidXNlcm5hbWUiOiJ1c2VyIiwicm9sZSI6IlJPTEVfVVNFUiIsImlkIjozLCJwcm9maWxlX2lkIjoxfQ.obr4RphbYxzPwYJhSRXhsIDcogQkWakkpE25KT8zMpg";
        ValidatableResponse validatableResponse = given()
                .auth().preemptive().oauth2(jwtToken)
                .param("old_password", "342")
                .param("new_password", "234")
                .when().post(url + "change/password")
                .then().statusCode(200).body("success", equalTo(false));
    }

    @Test
    public void changeProfilePicture() {
    }

    @Test
    public void getAvatar() {
        given()
                .pathParam("fileName", "1.jpeg")
                .when().get(url + "avatar/{fileName}")
                .then().statusCode(200);
    }

    @Test
    public void verifyCritic() {
    }

//    @Test
//    public void updateProfile() {
//        UserProfile build = new UserProfileBuilder()
//                .withId(1)
//                .withName("Bin Zhou")
//                .withProfileImage(null)
//                .withBiography("This is me")
//                .withWatchList(new WatchList())
//                .withWishList(new WishList())
//                .withReviews(null)
//                .build();
//
//
//        ValidatableResponse validatableResponse = given()
//                .contentType("application/json")
//                .body(build)
//                .when().post(url + "update/profile")
//                .then().statusCode(200);
//
//        validatableResponse.body("name", equalTo("Bin Zhou"));
//        validatableResponse.body("biography", equalTo("This is me"));
//    }

    @Test
    public void makeSummaryPrivate() {
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwidXNlcm5hbWUiOiJ1c2VyIiwicm9sZSI6IlJPTEVfVVNFUiIsImlkIjozLCJwcm9maWxlX2lkIjoxfQ.obr4RphbYxzPwYJhSRXhsIDcogQkWakkpE25KT8zMpg";
        ValidatableResponse validatableResponse1 = given()
                .auth().preemptive().oauth2(jwtToken)
                .when().post(url + "set/private")
                .then().statusCode(200).body("success", equalTo(true));
    }

    @Test
    public void getProfile() {

        // should get the name of the
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwidXNlcm5hbWUiOiJ1c2VyIiwicm9sZSI6IlJPTEVfVVNFUiIsImlkIjozLCJwcm9maWxlX2lkIjoxfQ.obr4RphbYxzPwYJhSRXhsIDcogQkWakkpE25KT8zMpg";
        ValidatableResponse validatableResponse1 = given()
                .auth().preemptive().oauth2(jwtToken)
                .when().get(url + "get/profile")
                .then().statusCode(200).body("name", equalTo("John Doe"));
    }

}