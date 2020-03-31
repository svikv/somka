package pages;

import libs.ActionWithWebElements;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TransactionPartiesPage {

    protected WebDriver webDriver;
    Wait<WebDriver> wait;
    private Logger logger = Logger.getLogger(getClass());
    private ActionWithWebElements actionWithWebElements;

    private By dictionaryTab = By.xpath("//i[@class='fa fa-book']");
    private By transactionPartiesTab = By.id("prov_cus");
    private By addButton = By.xpath("//div[@class='box-tools']");
    private By customerNameField = By.id("prov_cus_proCustName");
    private By customerAddressField = By.id("prov_cus_proCustAddress");
    private By customerPhoneField = By.id("prov_cus_proCustPhone");
    private By privatePersonCheckbox = By.id("prov_cus_proCustIsFl");
    private By createButton = By.name("add");
    private By table = By.xpath("//table[@id='device_list']//tbody//tr");
    private By deleteButton = By.name("delete");
    private By saveButton = By.name("save");

    public TransactionPartiesPage(WebDriver webDriver){
        this.webDriver = webDriver;
        actionWithWebElements = new ActionWithWebElements(webDriver);
        PageFactory.initElements(this.webDriver, this);
        wait = new WebDriverWait(webDriver, 1);
    }

    public void clickButton(By element){
        actionWithWebElements.clickButton(element);
    }

    public void fillField(By element, String text){
        actionWithWebElements.fillField(element,text);
    }

    public void waitForVisibilityOfElement(Wait<WebDriver> wait, By transactionPartiesTab){
        actionWithWebElements.waitForVisibilityOfElement(wait, transactionPartiesTab);
    }

    public void goToTable(){
        clickButton(dictionaryTab);
        waitForVisibilityOfElement(wait, transactionPartiesTab);
        clickButton(transactionPartiesTab);
    }

    public int countTableRaws(){
        List<WebElement> rows = webDriver.findElements(table);
        return rows.size();
    }

    public void deleteTableRecord(int index){
        By raw = By.xpath("//tr[" + index + "]//td[4]");
        clickButton(raw);
        clickButton(deleteButton);
    }

    public void updateTableRecord(int index, String name, String address, String phone){
        By raw = By.xpath("//tr[" + index + "]//td[4]");
        clickButton(raw);
        fillField(customerNameField, name);
        fillField(customerAddressField,address);
        fillField(customerPhoneField,phone);
        clickButton(saveButton);
    }

    public String getRecord (int index){
        List<WebElement> rows = webDriver.findElements(table);
        String record = rows.get(index).getText();
        return record;
    }

    public void addTableRecord(String name, String address, String phone){
        clickButton(addButton);
        fillField(customerNameField,name);
        fillField(customerAddressField,address);
        fillField(customerPhoneField,phone);
        clickButton(privatePersonCheckbox);
        clickButton(createButton);
    }
}