package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Praktikum3 {
    File json = new File("src/main/resources/newCard.json");

    @Before
    public void setup() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru/";
    }

    @Test
    public void getMyInfoStatusCode() {
        given()
                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmNhMmJiYmQ1NmMxNDAwM2Q0Nzk3ZWMiLCJpYXQiOjE3MjUyMDUzODMsImV4cCI6MTcyNTgxMDE4M30.-n4uep4IdPgxhqJiZLZJzuKNdR1GuAUmk0JfpgNPeyg")
                .get("/api/users/me")
                .then().assertThat().body("data.name", equalTo("Жак-Ив Кусто"));
    }

    @Test
    public void createNewPlaceAndCheckResponse() {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmNhMmJiYmQ1NmMxNDAwM2Q0Nzk3ZWMiLCJpYXQiOjE3MjUyMDUzODMsImV4cCI6MTcyNTgxMDE4M30.-n4uep4IdPgxhqJiZLZJzuKNdR1GuAUmk0JfpgNPeyg")
                .and()
                .body(json)
                .when()
                .post("/api/cards");
        response.then().assertThat().body("data._id", notNullValue())
                .and()
                .statusCode(201);
    }
}
