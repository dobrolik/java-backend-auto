package ru.geekbrains.gostevnv;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {

    static Properties prop = new Properties();
    static String token;
    static String clientId;
    static ResponseSpecification respSpec = null;
    static RequestSpecification reqSpec = null;
    static RequestSpecification withoutAuthSpec = null;

    @BeforeAll
    static void BeforeAll() {
        loadProperties();
        clientId = prop.getProperty("client.id");
        token = prop.getProperty("token");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = prop.getProperty("base.url");

        respSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .build();

        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .setAccept(ContentType.ANY)
                .setContentType(ContentType.JSON)
                .build();

        withoutAuthSpec = new RequestSpecBuilder()
                .addHeader("Authorization", "")
                .build();

    }

    private static void loadProperties() {
        try (InputStream file = new FileInputStream("src/test/resources/application.properties")) {
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
