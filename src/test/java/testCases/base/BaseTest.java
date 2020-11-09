package testCases.base;

import base.WebDriverSession;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public abstract class BaseTest {

    protected WebDriverSession session;
    protected static WebDriver driver;

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
