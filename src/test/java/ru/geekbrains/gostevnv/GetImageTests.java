package ru.geekbrains.gostevnv;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GetImageTests {

    static Properties prop = new Properties();
    static String token;
    static String clientId;
    private static Map<String, String> headers = new HashMap<>();

    @BeforeAll
    static void setUp() {
        loadProperties();
        clientId = prop.getProperty("client.id");
        token = prop.getProperty("token");

        headers.put("Authorization", token);
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = prop.getProperty("base.url");
    }


    @Test
    void getImagePositiveTest() {
        given()
                .log()
                .method()
                .log()
                .uri()
                .headers(headers)
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
                .headers(headers)
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

    private static void loadProperties() {
        try (InputStream file = new FileInputStream("src/test/application.properties")) {
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
