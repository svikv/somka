package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    private Logger LOG = Logger.getLogger(getClass());

    @FindBy(xpath = ".//*[@class='pull-left image']")
    private WebElement avatar;

    @FindBy(xpath = ".//li[@id='dictionary']//ul[@class='treeview-menu menu-open']//li[@id='prov_cus']")
    private WebElement submenuProviders;

    public HomePage(WebDriver webDriver){
        super(webDriver);
    }

    public boolean isAvatarDisplayed () {
       return isElementDisplayed(avatar);
    }

    public void checkIsAvatarPresent () {
        Assert.assertTrue("Avatar is not displayed", isAvatarDisplayed());
    }

    public void clickOnSubmenuProviders () {
        clickButton(submenuProviders);
    }

}
