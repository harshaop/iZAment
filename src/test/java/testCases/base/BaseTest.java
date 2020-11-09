package testCases.base;

import base.WebDriverSession;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

    protected static WebDriver driver;
    protected WebDriverSession session;

    @Parameters({"browser"})
    @BeforeTest
    public void setup(@Optional("chrome") String browser) {
        session = new WebDriverSession();
        session.init(browser);
        driver = session.getWebDriver();
    }

    @AfterTest
    public void driverClose() {
        session.tearDown();
    }
}
