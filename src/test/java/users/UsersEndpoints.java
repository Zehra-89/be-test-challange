package users;

import com.freenow.testchallenge.api.APISpecification;
import com.freenow.testchallenge.environment.TestEnvironment;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;


public class UsersEndpoints {

    private static final Logger log = LogManager.getLogger();
    public static String userName = "samantha";

    public static Response getUser(String name) {
        log.info("Getting Data for User Name:" + name);
        return given()
                .spec(APISpecification.requestSpec)
                .param("username", name)
                .get(TestEnvironment.usersEndpoint);

    }
}
