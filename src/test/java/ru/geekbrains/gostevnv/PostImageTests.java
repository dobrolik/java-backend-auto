package ru.geekbrains.gostevnv;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class PostImageTests extends BaseTest {

    String uploadedImageHash;
    String encodedImage;

    @BeforeEach
    void setUp() {
        byte[] fileContent = getFileContentInBase64();
        encodedImage = Base64.getEncoder().encodeToString(fileContent);
    }

    @Test
    void uploadBase64FileTest() {
        uploadedImageHash = given()
                .headers("Authorization", token)
                .multiPart("image", encodedImage)
                .expect()
                .body("success", is(true))
                .body("data.id", is(notNullValue()))
                .when()
                .post("")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");
    }

    @Test
    void uploadFileTest() {
        uploadedImageHash = given()
                .headers("Authorization", token)
                .multiPart("image", "/resources/fox.jpg")
                .expect()
                .body("success", is(true))
                .body("data.id", is(notNullValue()))
                .when()
                .post("")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");
    }

    @Test
    void uploadMinSizeFile() {
        uploadedImageHash = given()
                .headers("Authorization", token)
                .multiPart("image", "/resources/Pixel.png")
                .expect()
                .body("success", is(true))
                .body("data.id", is(notNullValue()))
                .body("data.height", is(1))
                .body("data.width", is(1))
                .when()
                .post("")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");
    }

    @Test
    void uploadBigSizeImage() {
        uploadedImageHash = given()
                .headers("Authorization", token)
                .multiPart("image", "/resources/big-size.jpg")
                .expect()
                .body("success", is(true))
                .body("data.id", is(notNullValue()))
                .when()
                .post("")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");

    }

    @Test
    void uploadOverMaxSize() {
        given()
                .headers("Authorization", token)
                .multiPart("image", "/resources/over-max-size.jpg")
                .expect()
                .body("success", is(false))
                .when()
                .post("")
                .prettyPeek()
                .then()
                .statusCode(400)
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");

    }

    @AfterEach
    void tearDown() {
        given()
                .headers("Authorization", token)
                .when()
                .delete("/{deleteHash}", uploadedImageHash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    private byte[] getFileContentInBase64() {
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(Objects.requireNonNull(classLoader.getResource("ava1.jpg")).getFile());
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
}
