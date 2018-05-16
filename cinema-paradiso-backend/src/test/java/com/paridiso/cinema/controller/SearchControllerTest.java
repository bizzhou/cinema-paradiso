package com.paridiso.cinema.controller;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class SearchControllerTest {

    static final String url = "http://localhost:8080/search";

//    @Test
//    public void search() {
//        ValidatableResponse validatableResponse = given()
//                .pathParam("keyword", "the")
//                .when().post(url)
//                .then().statusCode(200).body();
//    }

}