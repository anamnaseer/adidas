package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage
{
    @FindBy(css = "[class='btn btn-success btn-lg']")
    public WebElement addToCartButton;
    @FindBy(css = "[onclick=\"byCat('phone')\"]")
    public WebElement phoneMenuButton;
    @FindBy(css = "[onclick=\"byCat('notebook')\"]")
    public WebElement laptopsMenuButton;
    @FindBy(css = "[onclick=\"byCat('monitor')\"]")
    public WebElement monitorsMenuButton;
    @FindBy(css = "a#cartur")
    public WebElement cartMenuButton;
    @FindBy(css = "h3#totalp")
    public WebElement totalCartAmount;
    @FindBy(css = "[class='btn btn-success']")
    public WebElement placeOrderButton;
    @FindBy(css = "input#name")
    public WebElement name;
    @FindBy(css = "input#country")
    public WebElement country;
    @FindBy(css = "input#city")
    public WebElement city;
    @FindBy(css = "input#card")
    public WebElement creditCard;
    @FindBy(css = "input#month")
    public WebElement month;
    @FindBy(css = "input#year")
    public WebElement year;
    @FindBy(css = "[onclick=\"purchaseOrder()\"]")
    public WebElement purchaseOrderButton;
    @FindBy(css = "[ class=\"confirm btn btn-lg btn-primary\"]")
    public WebElement okButton;

    @FindBy(css = "[class='lead text-muted ']")
    public WebElement purchaseDetails;

    WebDriver driver;

    public MainPage(WebDriver driver1)
    {
        driver = driver1;
        PageFactory.initElements(driver1, this);
    }

    public WebElement getElementByText(String str)
    {
        return driver.findElement(By.xpath("//*[contains(text(),'" + str + "')]"));
    }

    public WebElement deleteFromCart(String str)
    {
        return driver.findElement(By.xpath("//td[text() = '" + str + "']/following-sibling::td/a"));
    }

    public void waitForAlert()
    {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void enterText(WebElement element,String text)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

    public void fillPurchaseForm() throws InterruptedException
    {
        name.sendKeys("test");
     //   city.sendKeys("city");
      //  country.sendKeys("country");
        creditCard.sendKeys("credit");
    //    month.sendKeys("month");
//        year.sendKeys("year");
    }
}
