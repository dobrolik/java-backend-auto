package ru.geekbrains.gostevnv;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.geekbrains.gostevnv.dto.GetImageResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@Slf4j
public class GetImageTests extends BaseTest {

    @Test
    void getImagePositiveTest() {
        given()
                .spec(reqSpec)
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get("/KhiV5Gs")
                .prettyPeek()
                .then()
                .spec(respSpec);
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
                .spec(reqSpec)
                .log()
                .uri()
                .when()
                .get("/KhiV")
                .then()
                .statusCode(404);
    }


    @Test
    void badAuthTokenTest() {
        given()
                .spec(withoutAuthSpec)
                .log()
                .uri()
                .when()
                .get("/daVMThb")
                .then()
                .statusCode(403);

    }

    @Test
    void getImageManyChecks() {
        given()
                .spec(reqSpec)
                .expect()
                .body("success", is(true))
                .body("data.width", is(800))
                .body("data.id", is(notNullValue()))
                .when()
                .get("/daVMThb")
                .then()
                .spec(respSpec);
    }

    @Test
    void getImagePositiveWithObjectTest() {
        GetImageResponse response = given()
                .spec(reqSpec)
                .when()
                .get("/KhiV5Gs")
                .prettyPeek()
                .then()
                .extract()
                .body()
                .as(GetImageResponse.class);
        assertThat(response.getStatus(), equalTo(200));
        assertThat(response.getImageData().getWidth(), equalTo(3000));
        assertThat(response.getImageData().getHeight(), equalTo(2000));
        assertThat(response.getImageData().getAccountId(), equalTo(145270851));
    }

}
