package comments;

import com.freenow.testchallenge.validator.EmailValidator;
import comments.payload.Comments;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import posts.PostsDataProvider;
import posts.PostsEndpoints;
import posts.payload.Post;
import users.UserDataProvider;
import users.UsersEndpoints;

import java.util.Arrays;
import java.util.List;

import static posts.PostsDataProvider.INVALID_POST_ID_DATA_PROVIDER;

public class CommentsAPITest {
    private static final Logger log = LogManager.getLogger();

    private static List<Post> posts;

    @BeforeClass
    public static void initializeTestData() {
        Integer userId = UsersEndpoints.getUserId(UserDataProvider.userName);
        posts = PostsEndpoints.getPostsByUserId(userId);
    }

    @Test
    @Epic("Comments API Tests")
    @Story("Search for valid email addresses in Comments")
    @Description("Verify the email in the user comments are in valid format")
    public void verifyCommentContainsValidEmail() {
        posts.forEach(post -> {
            int postId = post.id;
            Response commentsResponse = CommentsEndpoints.getCommentsByPostId(postId);
            List<Comments> comments = Arrays.asList(commentsResponse.getBody().as(Comments[].class));
            comments.forEach(comment -> {
                Assert.assertTrue(EmailValidator.isValidEmail(comment.email));

            });
        });
    }

    @Test(dataProvider = INVALID_POST_ID_DATA_PROVIDER, dataProviderClass = PostsDataProvider.class)
    public void verifyNoCommentsFoundForInvalidPostId(Integer postId) {
        Response commentsResponse = CommentsEndpoints.getCommentsByPostId(postId);
        List<Comments> comments = Arrays.asList(commentsResponse.getBody().as(Comments[].class));
        Assert.assertEquals("Comments found for Invalid Post Id", 0, comments.size());
    }

    @Test
    public void verifyCommentsBelongToUserPostId() {
        posts.forEach(post -> {
            Integer postId = post.id;
            Response commentsResponse = CommentsEndpoints.getCommentsByPostId(postId);
            List<Comments> comments = Arrays.asList(commentsResponse.getBody().as(Comments[].class));
            comments.forEach(comment -> {
                Assert.assertEquals("Comment doesnt belongs to user Posts: " + postId, postId, comment.postId);
            });
        });

    }

}
