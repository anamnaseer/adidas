package com.example.adidas.stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.MainPage;


public class MainPageTestSteps
{
    private static final Logger log = LoggerFactory.getLogger(MainPageTestSteps.class);
    private static String totalAmount;
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp()
    {
        final String rootDir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver",
            rootDir + "\\src\\test\\resources\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.demoblaze.com/index.html");

        mainPage = new MainPage(driver);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Given("The navigation through product category is working")
    public void checkMenuNavigation()
    {
        log.info("Checking menu buttons");
        mainPage.phoneMenuButton.click();
        mainPage.laptopsMenuButton.click();
        mainPage.monitorsMenuButton.click();
    }

    @When("I select {string} and click add to cart")
    public void selectLaptop(String laptop)
    {
        log.info("selecting laptop::" + laptop);
        mainPage.laptopsMenuButton.click();
        mainPage.getElementByText(laptop).click();
        mainPage.addToCartButton.click();
        mainPage.waitForAlert();
        driver.switchTo().alert().accept();
        mainPage.getElementByText("Home").click();

    }

    @And("I delete {string} from cart")
    public void deleteFromCart(String laptop)
    {
        log.info("deleting laptop::" + laptop);
        mainPage.cartMenuButton.click();
        mainPage.deleteFromCart(laptop).click();
        totalAmount = mainPage.totalCartAmount.getText();
    }

    @Then("I place order by filling form and clicking purchase")
    public void placeOrder() throws InterruptedException
    {
        log.info("placing order");
        mainPage.placeOrderButton.click();
        mainPage.fillPurchaseForm();
        driver.findElement(By.cssSelector("[onclick=\"purchaseOrder()\"]")).click();
    }

    @And("I capture purchase Id and Amount")
    public void logPurchaseIDandAmount() throws InterruptedException
    {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"sweet-alert  showSweetAlert visible\"]")));
        String text = driver.findElement(By.xpath("//div/p[@class=\"lead text-muted \"]")).getText();
        log.info("text shown on popup" + text);
    }

    @And("I validate purchase amount equals expected.")
    public void validateAmount()
    {
    }
}
