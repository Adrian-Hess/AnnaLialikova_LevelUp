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
import ru.levelup.at.java.homework9.comment.CommentData;
import ru.levelup.at.java.homework9.comment.CommentResponse;
import ru.levelup.at.java.homework9.comment.CommentsListResponse;

public class CommentTest {

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
    public void postCommentTest() {

        var postId = 1413;
        var name = FAKER.name().username();
        var email = name + "@mail.ru";
        var body = "Test Comment Body";
        var request = CommentData.builder()
                                 .postId(postId)
                                 .name(name)
                                 .email(email)
                                 .body(body)
                                 .build();

        var actualResponse = given()
            .spec(requestSpecification)
            .body(request)
            .when()
            .post("/public/v1/comments")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED)
            .extract()
            .as(CommentResponse.class);

        var expectedResponse = CommentResponse.builder()
                                           .data(CommentData.builder()
                                                            .postId(postId)
                                                            .name(name)
                                                            .email(email)
                                                            .body(body)
                                                         .build());

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualResponse)
                  .usingRecursiveComparison()
                  .ignoringExpectedNullFields()
                  .isEqualTo(expectedResponse);
        });
    }

    @Test
    public void putCommentTest() {

        var postId = 1413;
        var name = FAKER.name().username();
        var email = name + "@mail.ru";
        var body = "Test Comment Body Update";
        var request = CommentData.builder()
                                 .postId(postId)
                                 .name(name)
                                 .email(email)
                                 .body(body)
                                 .build();

        var actualResponse = given()
            .spec(requestSpecification)
            .pathParam("commentId", 1455)
            .body(request)
            .when()
            .put("/public/v1/comments/{commentId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(CommentResponse.class);

        var expectedResponse = CommentResponse.builder()
                                              .data(CommentData.builder()
                                                               .postId(postId)
                                                               .name(name)
                                                               .email(email)
                                                               .body(body)
                                                               .build());

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualResponse)
                  .usingRecursiveComparison()
                  .ignoringExpectedNullFields()
                  .isEqualTo(expectedResponse);
        });
    }

    @Test
    public void getCommentTest() {
        given()
            .spec(requestSpecification)
            .pathParam("commentId", 1455)
            .when()
            .get("/public/v1/comments/{commentId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(CommentResponse.class);

    }

    @Test
    public void getAllCommentsTest() {
        CommentsListResponse actualComments = given()
            .spec(requestSpecification)
            .when()
            .get("/public/v1/comments")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(CommentsListResponse.class);

        System.out.println(actualComments);
    }

    @Test
    public void deleteCommentTest() {
        given()
            .spec(requestSpecification)
            .pathParam("commentId", 1456)
            .when()
            .delete("/public/v1/comments/{commentId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }


}
