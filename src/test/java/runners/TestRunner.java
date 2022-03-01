package runners;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"features/"},
    glue = {"com.example.adidas.stepDefinitions"},
    tags = "@test",
    plugin = {
        "pretty"
        , "html:target/cucumberReports/Report.html",
        "json:target/cucumberReports/cucumber.json"}

)
public class TestRunner
{

    @BeforeClass
    public static void setUp()
    {

        final String rootDir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver",
            rootDir + "\\src\\test\\resources\\drivers\\chromedriver.exe");
        final ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.demoblaze.com/index.html");
    }

    @AfterClass
    public static void tearDown()
    {

    }

}
