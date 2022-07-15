package posts;

import org.testng.annotations.DataProvider;

public class PostsDataProvider {
    public static final String INVALID_POST_ID_DATA_PROVIDER = "invalid-post-id-data-provider";

    @DataProvider(name = INVALID_POST_ID_DATA_PROVIDER)
    public Object[][] dpInvalidPostId() {
        return new Object[][]{{-4}, {null}};

    }

}
