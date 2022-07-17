package users;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
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
    @Epic("Users API Tests")
    @Story("Search for User Name")
    @Description("Verify api should not return null response on giving valid user name")
    public void verifyUsersResponseIsNotNull() {

        Response userResponse = UsersEndpoints.getUser(userName);
        userResponse
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Epic("Users API Tests")
    @Story("Search for Valid User Name")
    @Description("Verify api should return response on giving valid user name")
    public void verifyProvidedUserNameIsPresent() {
        Response userResponse = UsersEndpoints.getUser(userName);
        userResponse.then().statusCode(200);
        User user= Arrays.asList(userResponse.getBody().as(User[].class)).get(0);
        Assert.assertEquals("Given user name is not found in users api response", userName,user.getUsername());
    }

    @Test
    @Epic("Users API Tests")
    @Story("Search for Valid User Name")
    @Description("Verify UserId is not null in user api response")
    public void verifyUserIdIsNotNull() {
        Response userResponse = UsersEndpoints.getUser(userName);
        List<User> users = Arrays.asList(userResponse.getBody().as(User[].class));
        Assert.assertNotNull("User Id not found in the users api response: ", users.get(0).getId());
    }

    @Test(dataProvider = UserDataProvider.USERNAME_DATA_PROVIDER, dataProviderClass = UserDataProvider.class)
    @Epic("Users API Tests")
    @Story("Search for Valid User Name")
    @Description("Verify given user name is present in the user api response")
    public void verifyUniqueRecordIsPresentForGivenUserName(String userName) {
        Response userResponse = UsersEndpoints.getUser(userName);
        List<User> users = Arrays.asList(userResponse.getBody().as(User[].class));
        Assert.assertEquals("Searched data not found in response: " + userName, 1, users.size());

    }

    @Test(dataProvider = UserDataProvider.INVALID_USERNAME_DATA_PROVIDER, dataProviderClass = UserDataProvider.class)
    @Epic("Users API Tests")
    @Story("Search for Invalid User Name")
    @Description("Verify api should return null response on giving invalid user name")
    public void verifyUserDoesNotExistForInvalidUserName(String userName) {
        Response userResponse = UsersEndpoints.getUser(userName);
        List<User> users = Arrays.asList(userResponse.getBody().as(User[].class));
        Assert.assertEquals("No users found with name " + userName, 0, users.size());

    }


}


