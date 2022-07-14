package users;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.Test;
import users.payload.User;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;

public class UsersAPITest {
    private static final Logger log = LogManager.getLogger();
    private final String userName = UserDataProvider.userName;

    @Test
    public void verifyUsersResponseIsNotNull() {

        Response userResponse = UsersEndpoints.getUser(userName);
        userResponse
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void verifyUniqueRecordIsPresentForGivenUserName() {
        Response userResponse = UsersEndpoints.getUser(userName);
        userResponse.then().statusCode(200);
        List<User> users = Arrays.asList(userResponse.getBody().as(User[].class));
        Assert.assertEquals(1, users.size());
    }


    @Test
    public void verifyUserIdIsNotNull() {
        Response userResponse = UsersEndpoints.getUser(userName);
        List<User> users = Arrays.asList(userResponse.getBody().as(User[].class));
        Assert.assertNotNull(users.get(0).getId());
    }

    @Test(dataProvider = UserDataProvider.USERNAME_DATA_PROVIDER, dataProviderClass = UserDataProvider.class)
    public void verifyGivenNameIsPresent(String userName) {
        Response userResponse = UsersEndpoints.getUser(userName);
        List<User> users = Arrays.asList(userResponse.getBody().as(User[].class));
        Assert.assertEquals(1, users.size());

    }

    @Test(dataProvider = UserDataProvider.INVALID_USERNAME_DATA_PROVIDER, dataProviderClass = UserDataProvider.class)
    public void verifyUserDoesNotExistForInvalidUserName(String userName) {
        Response userResponse = UsersEndpoints.getUser(userName);
        List<User> users = Arrays.asList(userResponse.getBody().as(User[].class));
        Assert.assertEquals("No users found with name " + userName, 0, users.size());

    }


}


