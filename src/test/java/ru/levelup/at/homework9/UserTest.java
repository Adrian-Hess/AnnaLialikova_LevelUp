package ru.levelup.at.homework9;

import static io.restassured.RestAssured.given;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.levelup.at.java.homework9.user.UserData;
import ru.levelup.at.java.homework9.user.UserResponse;
import ru.levelup.at.java.homework9.user.UsersListResponse;

public class UserTest {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;
    private static final Faker FAKER = new Faker();

    @BeforeMethod
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
            .log(LogDetail.ALL)
            .setBaseUri("https://gorest.co.in")
            .setContentType(ContentType.JSON)
            .addQueryParam("access-token", "3ae4f0a21a4d9d7043dd096d767bce4d8567437c411c849cb0a180f27c40fed9")
            .build();

        responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();
    }

    @Test
    public void postUserTest() {

        var name = FAKER.name().username();
        var email = name + "@mail.ru";
        var gender = "male";
        var status = "active";
        var request = UserData.builder()
                              .name(name)
                              .email(email)
                              .gender(gender)
                              .status(status)
                              .build();

        var actualResponse = given()
            .spec(requestSpecification)
            .body(request)
            .when()
            .post("/public/v1/users")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED)
            .extract()
            .as(UserResponse.class);

        var expectedResponse = UserResponse.builder()
                                           .data(UserData.builder()
                                                         .name(name)
                                                         .email(email)
                                                         .gender(gender)
                                                         .status(status)
                                                         .build());

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualResponse)
                  .usingRecursiveComparison()
                  .ignoringExpectedNullFields()
                  .isEqualTo(expectedResponse);
        });
    }

    @Test
    public void putUserTest() {

        var name = FAKER.name().username();
        var email = name + "@mail.ru";
        var gender = "male";
        var status = "inactive";
        var request = UserData.builder()
                              .name(name)
                              .email(email)
                              .gender(gender)
                              .status(status)
                              .build();

        var actualResponse = given()
            .spec(requestSpecification)
            .body(request)
            .when()
            .put("/public/v1/users/2200")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(UserResponse.class);

        var expectedResponse = UserResponse.builder()
                                           .data(UserData.builder()
                                                         .name(name)
                                                         .email(email)
                                                         .gender(gender)
                                                         .status(status)
                                                         .build());

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualResponse)
                  .usingRecursiveComparison()
                  .ignoringExpectedNullFields()
                  .isEqualTo(expectedResponse);
        });
    }

    @Test
    public void getUserTest() {
        UserResponse actualUser = given()
            .spec(requestSpecification)
            .pathParam("userId", 2200)
            .when()
            .get("/public/v1/users/{userId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(UserResponse.class);

    }

    @Test
    public void getAllUsersTest() {
        UsersListResponse actualUsers = given()
            .spec(requestSpecification)
            .when()
            .get("/public/v1/users")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(UsersListResponse.class);

        System.out.println(actualUsers);
    }

    @Test
    public void deleteUserTest() {
        given()
            .spec(requestSpecification)
            .pathParam("userId", 2201)
            .when()
            .delete("/public/v1/users/{userId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }


}
