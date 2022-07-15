package posts;

import com.freenow.testchallenge.api.APISpecification;
import com.freenow.testchallenge.environment.TestEnvironment;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import posts.payload.Post;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsEndpoints {

    private static final Logger log = LogManager.getLogger();

    public static Response getPostResponseByUserId(Integer userId) {
        log.info("Getting Data for Posts against User Id:" + userId);
        return given()
                .spec(APISpecification.requestSpec)
                .param("userId", userId)
                .get(TestEnvironment.postsEndpoint);
    }

    public static List<Post> getPostsByUserId(Integer userId) {
        log.info("Getting Data for Posts against User Id:" + userId);
        Response postsResponse = given()
                .spec(APISpecification.requestSpec)
                .param("userId", userId)
                .get(TestEnvironment.postsEndpoint);
        return Arrays.asList(postsResponse.getBody().as(Post[].class));

    }

}
