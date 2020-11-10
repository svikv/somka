package suits;

import loginTests.LoginWithValidLoginTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                LoginWithValidLoginTest.class,
                LoginWithValidLoginTest.class
        }
)
public class LoginSuit {

}
