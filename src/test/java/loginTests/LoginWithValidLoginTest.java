package loginTests;

import org.junit.Test;
import pages.Page;
import parentTests.AbstractParentTest;

public class LoginWithValidLoginTest extends AbstractParentTest {

    @Test
    public void validLogin() {
        loginPage.loginToPage(user, password);
        checkExpectedResult("Avatar is displayed", homePage.isAvatarDisplayed());
    }

}
