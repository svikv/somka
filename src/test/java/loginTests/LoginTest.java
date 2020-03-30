package loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private WebDriver webDriver = driverInit();

    private WebDriver driverInit() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @Test
    public void validLogin() {
        webDriver.get("http://v3.test.itpmgroup.com");
        webDriver.findElement(By.name("_username")).clear();
        webDriver.findElement(By.name("_username")).sendKeys("Student");
        webDriver.findElement(By.name("_password")).clear();
        webDriver.findElement(By.name("_password")).sendKeys("909090");
        webDriver.findElement(By.xpath(".//button[@type='submit']")).click();

        Assert.assertTrue(webDriver.findElement(By.xpath(".//div[@class='pull-left info']")).isDisplayed());
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }
}
