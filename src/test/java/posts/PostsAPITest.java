package posts;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import posts.payload.Post;
import users.UserDataProvider;
import users.UsersEndpoints;

import java.util.Arrays;
import java.util.List;


public class PostsAPITest {
    private static final Logger log = LogManager.getLogger();
    private static Integer userId;

    @BeforeClass
    public static void initializeTestData() {
        userId = UsersEndpoints.getUserId(UserDataProvider.userName);
    }

    @Test(dataProvider = UserDataProvider.INVALID_USER_ID_DATA_PROVIDER, dataProviderClass = UserDataProvider.class)
    @Epic("Posts API Tests")
    @Story("Search for Posts against invalid User")
    @Description("Verify post data should not return for invalid user id")
    public static void verifyEmptyResponseReturnForInvalidUserId(Integer userId) {
        Response postsResponse = PostsEndpoints.getPostResponseByUserId(userId);
        postsResponse.then().statusCode(200);
        List<Post> posts = Arrays.asList(postsResponse.getBody().as(Post[].class));
        Assert.assertEquals("Posts found with invalid user Id: " + userId, 0, posts.size());


    }

    @Test
    @Epic("Posts API Tests")
    @Story("Search for user Posts against valid User")
    @Description("Verify api returns all the posts on given valid user id ")
    public void verifyGetAllPostsByUserId() {
        Response postsResponse = PostsEndpoints.getPostResponseByUserId(userId);
        postsResponse.then().statusCode(200);
        List<Post> posts = Arrays.asList(postsResponse.getBody().as(Post[].class));
        if (posts.size() > 0) {
            posts.forEach(post -> Assert.assertEquals(userId, post.userId));
        } else {
            log.info("No Posts found against user id:" + userId);
        }
    }

}
