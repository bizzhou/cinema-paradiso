package com.paridiso.cinema.controller;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class AdminControllerTest {

    static final String url = "http://localhost:8080/admin/";

    @Test
    public void suspendUser() {
        ValidatableResponse validatableResponse = given()
                .pathParam("id", "2")
                .when().post(url + "suspend/{id}")
                .then().statusCode(200).body("success", equalTo(true));
    }

    @Test
    public void suspendUserNotFind() {
        ValidatableResponse validatableResponse = given()
                .pathParam("id", "5")
                .when().post(url + "suspend/{id}")
                .then().statusCode(500);
    }

    @Test
    public void getUsers() {
        ValidatableResponse resp = given()
                .when().get(url + "get/users")
                .then().statusCode(200).body("size()", equalTo(2));
    }

    @Test
    public void verifyCritic() {

    }
}