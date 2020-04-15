package parentTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import utilities.Utils;

import java.util.concurrent.TimeUnit;

public class AbstractParentTest {
    protected WebDriver webDriver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected TransactionPartiesPage transactionParties;
    protected TransactionsPage transactions;
    protected String user;
    protected String password;
    private Utils utils;
    private String pathToScreenShot;

    @Rule
    public TestName testName = new TestName();

    @BeforeClass
    public static void setup() {
        Page.serverHealthCheck();
    }

    @Before
    public  void SetUp() throws Exception {

        pathToScreenShot = "../somka/target/screenshot/" + this.getClass().getPackage().getName() +
                "/" + this.getClass().getSimpleName() + this.testName.getMethodName() + ".jpg";

        webDriver = driverInit();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        transactionParties = new TransactionPartiesPage(webDriver);
        transactions = new TransactionsPage(webDriver);
        user = Page.getAuthUser().get("authUser");
        password = Page.getAuthUser().get("authPass");
        utils = new Utils();
    }

    private WebDriver driverInit() {
//        WebDriverManager.chromedriver().setup();
        WebDriverManager.chromedriver().version("81").setup();
        return new ChromeDriver();
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }

    protected  void checkExpectedResult(String message, boolean actualResult) {

        if (!actualResult) {
            utils.captureScreenshot(pathToScreenShot, webDriver);
        }

        Assert.assertTrue(message, actualResult);
    }

}
