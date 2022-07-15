package comments;

import com.freenow.testchallenge.api.APISpecification;
import com.freenow.testchallenge.environment.TestEnvironment;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class CommentsEndpoints {

    private static final Logger log = LogManager.getLogger();

    public static Response getCommentsByPostId(Integer postId) {
        log.info("Getting Comments for Post Id:" + postId);
        return given()
                .spec(APISpecification.requestSpec)
                .param("postId", postId)
                .get(TestEnvironment.commentsEndpoint);
    }
}
