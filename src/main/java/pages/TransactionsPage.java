package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TransactionsPage extends Page {

    @FindBy(id = "deal")
    private WebElement transactionsTab;

    @FindBy(xpath = "//div[@class='box-tools']")
    private WebElement addButton;

    @FindBy(id = "deal_dealDate_date_day")
    private WebElement dateSelector;

    @FindBy(id = "deal_dealDate_date_month")
    private WebElement monthSelector;

    @FindBy(id = "deal_dealDate_date_year")
    private WebElement yearSelector;

    @FindBy(id = "deal_dealType")
    private WebElement typeSelector;

    @FindBy(id = "deal_customer")
    private WebElement buyerSelector;

    @FindBy(id = "deal_provider")
    private WebElement supplierSelector;

    @FindBy(name = "add")
    private WebElement createButton;

    @FindBy(name = "save")
    private WebElement saveButton;

    @FindBy(xpath = "//table[@id='device_list']//tbody")
    private WebElement table;

    public TransactionsPage(WebDriver webDriver){
        super(webDriver);
    }

    public void tableView(){
        clickButton(transactionsTab);
    }

    public int countTableRows(){
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        return rows.size();
    }

    public void addTableRecord(String date, String month, String year, String type, String buyer, String supplier){
        clickButton(addButton);
        selectValueFromSelector(dateSelector, date);
        selectValueFromSelector(monthSelector, month);
        selectValueFromSelector(yearSelector, year);
        selectValueFromSelector(typeSelector, type);
        selectValueFromSelector(buyerSelector, buyer);
        selectValueFromSelector(supplierSelector, supplier);
        clickButton(createButton);
    }

    public String getTableRecord (int index){
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        return rows.get(index).getText();
    }

    private By getRow(int index){
        return By.xpath("//tr[" + index + "]");
    }

    public void updateTableRecord(int index, String newBuyer, String newSupplier){
        clickButton(getRow(index));
        selectValueFromSelector(buyerSelector, newBuyer);
        selectValueFromSelector(supplierSelector, newSupplier);
        clickButton(saveButton);
    }

}