package users;

import com.freenow.testchallenge.api.APISpecification;
import com.freenow.testchallenge.environment.TestEnvironment;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import users.payload.User;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;


public class UsersEndpoints {

    private static final Logger log = LogManager.getLogger();


    public static Response getUser(String name) {
        log.info("Getting Data for User Name:" + name);
        return given()
                .spec(APISpecification.requestSpec)
                .param("username", name)
                .get(TestEnvironment.usersEndpoint);

    }

    public static Integer getUserId(String userName) {
        log.info("Getting Data for User Name:" + userName);
        Response userResponse = given()
                .spec(APISpecification.requestSpec)
                .param("username", userName)
                .get(TestEnvironment.usersEndpoint);
        List<User> users = Arrays.asList(userResponse.getBody().as(User[].class));
        return users.get(0).getId();

    }
}
