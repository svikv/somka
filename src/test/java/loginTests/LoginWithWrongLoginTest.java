package loginTests;

import org.junit.Test;

public class LoginWithWrongLoginTest extends AbstractParentTest {

    @Test
    public void validLogin() {
        loginPage.loginToPage("Vasya", "180150");
        checkExpectedResult("LoginBox isn't displayed", loginPage.isLoginBoxDisplayed());
    }

}
