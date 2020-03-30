package pages;

import libs.ActionWithWebElements;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TransactionPartiesPage {

    protected WebDriver webDriver;
    Wait<WebDriver> wait;
    protected Logger logger = Logger.getLogger(getClass());
    protected ActionWithWebElements actionWithWebElements;
    private final static String  URL = "http://v3.test.itpmgroup.com";

    By dictionaryTab = By.xpath("//i[@class='fa fa-book']");
    By transactionPartiesTab = By.id("prov_cus");
    By addButton = By.xpath("//div[@class='box-tools']");

    @FindBy(id = "prov_cus_proCustName")
    private WebElement customerNameField;

    @FindBy(id = "prov_cus_proCustAddress")
    private WebElement customerAddressField;

    @FindBy(id = "prov_cus_proCustPhone")
    private WebElement customerPhoneField;

    @FindBy(id = "prov_cus_proCustIsFl")
    private WebElement isPrivatePersonCheckbox;

    @FindBy(id = "add")
    private WebElement createButton;

    By table = By.xpath("//table[@id='device_list']//tbody//tr");

    public void clickButton(By transactionPartiesTab){
        actionWithWebElements.clickButton(transactionPartiesTab);
    }

    public TransactionPartiesPage(WebDriver webDriver){
        this.webDriver = webDriver;
        actionWithWebElements = new ActionWithWebElements(webDriver);
        PageFactory.initElements(this.webDriver, this);
        wait = new WebDriverWait(webDriver, 1);
    }

    public void goToDictionaryPage(){
        webDriver.get(URL);
        clickButton(dictionaryTab);
        actionWithWebElements.waitForVisibilityOfElement(wait, transactionPartiesTab);
        clickButton(transactionPartiesTab);
//        actionWithWebElements.waitForVisibilityOfElement(wait, addButton);

        String str = "//table[@id='device_list']//tbody//tr";
        List<WebElement> rows = webDriver.findElements(table);
        int countBefore = rows.size();

        clickButton(addButton);
        customerNameField.sendKeys("Viktor");
        customerAddressField.sendKeys("Kiev");
        customerPhoneField.sendKeys("0502501256");
        isPrivatePersonCheckbox.click();
//        createButton.click();

        try {
            new Thread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

