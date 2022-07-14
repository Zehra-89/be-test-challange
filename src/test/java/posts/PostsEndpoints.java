package posts;

import com.freenow.testchallenge.api.APISpecification;
import com.freenow.testchallenge.environment.TestEnvironment;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class PostsEndpoints {

    private static final Logger log = LogManager.getLogger();

    public static Response getPostByUserId(int userId) {
        log.info("Getting Data for Posts against User Id:" + userId);
        return given()
                .spec(APISpecification.requestSpec)
                .param("userId", userId).log().body()
                .get(TestEnvironment.postsEndpoint);
    }

}
