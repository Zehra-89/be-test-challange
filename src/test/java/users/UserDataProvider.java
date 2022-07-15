package users;

import org.testng.annotations.DataProvider;

public class UserDataProvider {
    public static final String USERNAME_DATA_PROVIDER = "username-data-provider";
    public static final String INVALID_USERNAME_DATA_PROVIDER = "invalid-username-data-provider";
    public static final String INVALID_USER_ID_DATA_PROVIDER = "invalid-user-id-data-provider";

    public static int invalidUserId = -45;

    public static String userName = "Samantha";

    @DataProvider(name = USERNAME_DATA_PROVIDER)
    public Object[][] dpValidUserNames() {
        return new Object[][]{{"Samantha"}, {"SAMANTHA"}, {" Samantha"}, {"  Samantha  "}};

    }

    @DataProvider(name = INVALID_USERNAME_DATA_PROVIDER)
    public Object[][] dpInvalidUserNames() {
        return new Object[][]{{"Invalid..224"}, {"3"}, {"Samantha   102"}, {"  "}};

    }

    @DataProvider(name = INVALID_USER_ID_DATA_PROVIDER)
    public Object[][] dpInvalidUserIds() {
        return new Object[][]{{-44}, {null}};

    }

}
