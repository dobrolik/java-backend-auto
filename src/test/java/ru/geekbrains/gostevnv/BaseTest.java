package ru.geekbrains.gostevnv;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {

    static Properties prop = new Properties();
    static String token;
    static String clientId;

    @BeforeAll
    static void BeforeAll() {
        loadProperties();
        clientId = prop.getProperty("client.id");
        token = prop.getProperty("token");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = prop.getProperty("base.url");
    }

    private static void loadProperties() {
        try (InputStream file = new FileInputStream("src/test/resources/application.properties")) {
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
