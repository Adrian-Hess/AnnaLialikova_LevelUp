package ru.levelup.at.homework9;

import static io.restassured.RestAssured.given;

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
import ru.levelup.at.java.homework9.post.PostData;
import ru.levelup.at.java.homework9.post.PostResponse;
import ru.levelup.at.java.homework9.post.PostsListResponse;

public class PostTest {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

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
    public void postPostsTest() {

        var userId = 2152;
        var title = "Test title of post";
        var body = "Test body of post";
        var request = PostData.builder()
                              .userId(userId)
                              .title(title)
                              .body(body)
                              .build();

        var actualResponse = given()
            .spec(requestSpecification)
            .body(request)
            .when()
            .post("/public/v1/posts")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED)
            .extract()
            .as(PostResponse.class);

        var expectedResponse = PostResponse.builder()
                                           .data(PostData.builder()
                                                         .userId(userId)
                                                         .title(title)
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
    public void putPostsTest() {

        var userId = 2152;
        var title = "Test title of post Update";
        var body = "Test body of post Update";
        var request = PostData.builder()
                              .userId(userId)
                              .title(title)
                              .body(body)
                              .build();

        var actualResponse = given()
            .spec(requestSpecification)
            .body(request)
            .when()
            .put("/public/v1/posts/1418")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(PostResponse.class);

        var expectedResponse = PostResponse.builder()
                                           .data(PostData.builder()
                                                         .userId(userId)
                                                         .title(title)
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
    public void getPostTest() {
        PostResponse actualPost = given()
            .spec(requestSpecification)
            .pathParam("postId", 1418)
            .when()
            .get("/public/v1/posts/{postId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(PostResponse.class);

    }

    @Test
    public void getAllPostsTest() {
        PostsListResponse actualPosts = given()
            .spec(requestSpecification)
            .when()
            .get("/public/v1/posts")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(PostsListResponse.class);

        System.out.println(actualPosts);
    }

    @Test
    public void deletePostTest() {
        given()
            .spec(requestSpecification)
            .pathParam("postId", 1419)
            .when()
            .delete("/public/v1/posts/{postId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }


}
