package org.filip.storemanagement.controller;

import io.restassured.RestAssured;
import org.filip.storemanagement.controller.dto.CreateProductRequest;
import org.filip.storemanagement.controller.dto.CreateProductResponse;
import org.filip.storemanagement.controller.dto.PatchProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.UUID;

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
    void createProduct_validFields_productReturned() {
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
    void createProducts_quantityIsString_errorMessageReturned() {
        given()
            .contentType("application/json")
            .log().all()
        .when()
            .body("{\"name\": \"a name\", \"description\": \"a description\", \"quantity\": \"quantity\"}")
            .post("/storemng/products")
        .then()
            .statusCode(400)
            .body("errorCode", equalTo(400))
            .body("errorMessage", equalTo("JSON parse error: Cannot deserialize value of type `int` from String \"quantity\": not a valid `int` value"));
    }

    @Test
    void getProductById_productFound_productReturned() {
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
            .body("name", equalTo("name"))
            .body("description", equalTo("description"))
            .body("quantity", equalTo(2))
            .body("price", equalTo(22.5f));
    }

    @Test
    void getProductById_productNotFound_errorMessageReturned() {
        given()
            .contentType("application/json")
            .log().all()
        .when()
            .get("/storemng/products/" + UUID.randomUUID())
        .then()
            .statusCode(404)
            .body("errorCode", equalTo(404))
            .body("errorMessage", equalTo("Product not found"));
    }

    @Test
    void patchProduct_patchOnlyThePrice_onlyPriceChanges() {
        CreateProductResponse response = given()
            .contentType("application/json")
            .log().all()
        .when()
            .body(new CreateProductRequest("name", "description", 2, 22.5))
            .post("/storemng/products")
            .as(CreateProductResponse.class);

        given()
            .contentType("application/json")
            .log().all()
        .when()
            .body("{\"price\": 100.0}")
            .patch("/storemng/products/" + response.uuid())
        .then()
            .statusCode(200)
            .body("name", equalTo("name"))
            .body("description", equalTo("description"))
            .body("quantity", equalTo(2))
            .body("price", equalTo(100.0f));
    }
}
