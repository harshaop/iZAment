package testCases.base;

import base.WebDriverSession;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

    protected static WebDriver driver;
    protected WebDriverSession session;

    @Parameters({"browser"})
    @BeforeSuite
    public void setup(@Optional("chrome") String browser) {
        session = new WebDriverSession();
        session.init(browser);
        driver = session.getWebDriver();
    }

    @AfterSuite
    public void driverClose() {
        //session.tearDown();
    }
}
