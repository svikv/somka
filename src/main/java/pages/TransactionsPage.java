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

public class TransactionsPage {

    private WebDriver webDriver;
    private Wait<WebDriver> wait;
    private Logger logger = Logger.getLogger(getClass());
    private ActionWithWebElements actionWithWebElements;

    private By transactionsTab = By.id("deal");
    private By addButton = By.xpath("//div[@class='box-tools']");
    private By dateSelector = By.id("deal_dealDate_date_day");
    private By monthSelector = By.id("deal_dealDate_date_month");
    private By yearSelector = By.id("deal_dealDate_date_year");
    private By typeSelector = By.id("deal_dealType");
    private By buyerSelector = By.id("deal_customer");
    private By supplierSelector = By.id("deal_provider");
    private By createButton = By.name("add");
    private By table = By.xpath("//table[@id='device_list']//tbody//tr");
    private By saveButton = By.name("save");

    public TransactionsPage(WebDriver webDriver){
        this.webDriver = webDriver;
        actionWithWebElements = new ActionWithWebElements(webDriver);
        PageFactory.initElements(this.webDriver, this);
        wait = new WebDriverWait(webDriver, 1);
    }

    private void clickButton(By element){
        actionWithWebElements.clickButton(element);
    }

    public void tableView(){
        clickButton(transactionsTab);
    }

    public int countTableRaws(){
        List<WebElement> rows = webDriver.findElements(table);
        return rows.size();
    }

    public void addTableRecord(String date, String month, String year, String type, String buyer, String supplier){
        clickButton(addButton);
        actionWithWebElements.selectValueFromSelector(dateSelector, date);
        actionWithWebElements.selectValueFromSelector(monthSelector, month);
        actionWithWebElements.selectValueFromSelector(yearSelector, year);
        actionWithWebElements.selectValueFromSelector(typeSelector, type);
        actionWithWebElements.selectValueFromSelector(buyerSelector, buyer);
        actionWithWebElements.selectValueFromSelector(supplierSelector, supplier);
        clickButton(createButton);
    }

    public String getTableRecord (int index){
        List<WebElement> rows = webDriver.findElements(table);
        return rows.get(index).getText();
    }

    private By getRaw(int index){
        return By.xpath("//tr[" + index + "]");
    }

    public void updateTableRecord(int index, String newBuyer, String newSupplier){
        clickButton(getRaw(index));
        actionWithWebElements.selectValueFromSelector(buyerSelector, newBuyer);
        actionWithWebElements.selectValueFromSelector(supplierSelector, newSupplier);
        clickButton(saveButton);
    }
}