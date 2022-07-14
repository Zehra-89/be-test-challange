package posts;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.Test;
import posts.payload.Posts;
import users.UserDataProvider;
import users.UsersEndpoints;
import users.payload.User;

import java.util.Arrays;
import java.util.List;




public class PostsAPITest {
    private static final Logger log = LogManager.getLogger();
    private Integer userId;

    @Test
    public void verifyGetUserId() {
        Response userResponse = UsersEndpoints.getUser(UserDataProvider.userName);
        List<User> users = Arrays.asList(userResponse.getBody().as(User[].class));
        userId=users.get(0).getId();
        Assert.assertNotNull(users.get(0).getId());
    }

    @Test(dependsOnMethods = {"verifyGetUserId"})
    public void verifyGetAllPostsByUserId() {
        Response postsResponse = PostsEndpoints.getPostByUserId(userId);
        postsResponse.then().statusCode(200);
        List<Posts> posts = Arrays.asList(postsResponse.getBody().as(Posts[].class));
        if (posts.size() > 0) {
            posts.forEach(post -> Assert.assertEquals(userId, post.userId));
        } else {
            log.info("No Posts found against user id:" + userId);
        }
    }

    @Test(dataProvider = UserDataProvider.INVALID_USER_ID_DATA_PROVIDER, dataProviderClass = UserDataProvider.class)
    public static void verifyEmptyResponseReturnForInvalidUserId(Integer userId) {
        Response postsResponse = PostsEndpoints.getPostByUserId(userId);
        postsResponse.then().statusCode(200);
        List<Posts> posts = Arrays.asList(postsResponse.getBody().as(Posts[].class));
        Assert.assertEquals("Posts found with invalid user Id: " + userId, 0, posts.size());


    }

}
