package org.filip.storemanagement.controller;

import io.restassured.RestAssured;
import org.filip.storemanagement.controller.dto.CreateProductRequest;
import org.filip.storemanagement.controller.dto.CreateProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StoreManagementControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void createProduct() {
        given()
            .contentType("application/json")
            .log().all()
        .when()
            .body(new CreateProductRequest("name", "description", 2, 22.5))
            .post("/storemng/products")
        .then()
            .statusCode(200)
            .body("uuid", notNullValue())
            .body("name", equalTo("name"))
            .body("description", equalTo("description"))
            .body("quantity", equalTo(2))
            .body("price", equalTo(22.5f));
    }

    @Test
    void getProductById() {
        CreateProductResponse response = given()
            .contentType("application/json")
            .log().all()
        .when()
            .body(new CreateProductRequest("name", "description", 2, 22.5))
            .post("/storemng/products")
            .body()
            .as(CreateProductResponse.class);

        given()
            .contentType("application/json")
            .log().all()
        .when()
            .get("/storemng/products/" + response.uuid())
        .then()
            .statusCode(200)
            .body("uuid", equalTo(response.uuid()))
            .body("name", equalTo("name"))
            .body("description", equalTo("description"))
            .body("quantity", equalTo(2))
            .body("price", equalTo(22.5f));
    }
}
