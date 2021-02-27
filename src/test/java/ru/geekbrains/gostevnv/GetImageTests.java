package ru.geekbrains.gostevnv;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class GetImageTests extends BaseTest {

    @Test
    void getImagePositiveTest() {
        given()
                .log()
                .method()
                .log()
                .uri()
                .headers("Authorization", token)
                .when()
                .get("/KhiV5Gs")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getImageNoAuth() {
        given()
                .log()
                .method()
                .log()
                .uri()
                .headers("Authorization", clientId)
                .when()
                .get("/daVMThb")
                .prettyPeek()
                .then()
                .statusCode(200);


    }

    @Test
    void badImageHashTest() {
        given()
                .log()
                .uri()
                .headers("Authorization", token)
                .when()
                .get("/KhiV")
                .then()
                .statusCode(404);
    }


    @Test
    void badAuthTokenTest() {
        given()
                .log()
                .uri()
                .when()
                .headers("Autorization", "Bearer ")
                .get("/daVMThb")
                .then()
                .statusCode(401);

    }

    @Test
    void getImageManyCheks() {
        given()
                .headers("Authorization", token)
                .expect()
                .body("success", is(true))
                .body("data.width", is(800))
                .body("data.id", is(notNullValue()))
                .when()
                .get("/daVMThb")
                .then()
                .statusCode(200);
    }

}
