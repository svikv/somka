package loginTests;

import org.junit.Test;
import parentTests.AbstractParentTest;

public class LoginWithValidLoginTest extends AbstractParentTest {

    @Test
    public void validLogin() {
        loginPage.loginToPage("Student", password);
        checkExpectedResult("Avatar isn't displayed", homePage.isAvatarDisplayed());
    }

}
