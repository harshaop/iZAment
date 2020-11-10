package base;

import base.utils.ReadPropertyFile;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class WebDriverSession {

    public static WebDriver webDriver;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private void setUpFirefoxBrowser() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        webDriver = new FirefoxDriver(firefoxOptions);
        maximizeBrowser();
    }

    private void setUpChromeBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
    //    options.addArguments("user-data-dir=C:\\Users\\HarshaOmprakash\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
        webDriver = new ChromeDriver(options);
    }

    private void setUpMobileDevice() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("androidPackage", "com.android.chrome");
        webDriver = new ChromeDriver(options);
    }

    private void setUpChromeMobileEmulator() {
        WebDriverManager.chromedriver().setup();
        Map<String, String> mobileEmulation = new HashMap<>();
        ChromeOptions chromeOptions = new ChromeOptions();
        mobileEmulation.put("deviceName", "iPhone X");
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        //  chromeOptions.addArguments("user-data-dir=C:\\Users\\HarshaOmprakash\\AppData\\Local\\Google\\Chrome\\User Data\\Default");

        webDriver = new ChromeDriver(chromeOptions);
    }

    public void init(String browser) {
        log.info("Init Webdriver session");
        Properties configuration = ReadPropertyFile.getPropertiesFile("configuration.properties");

        //String driverType = configuration.getProperty("browser");
        if ("firefox".equals(browser)) {
            setUpFirefoxBrowser();
        } else if ("chrome".equals(browser)) {
            setUpChromeBrowser();
        } else if ("chrome_mobile".equals(browser)) {
            setUpChromeMobileEmulator();
        } else if ("mobile_device".equals(browser)) {
            setUpMobileDevice();
        }
        getWebDriver().get(configuration.getProperty("url"));
    }

    public void maximizeBrowser() {
        webDriver.manage().window().maximize();
    }

    public void tearDown() {
        if (webDriver != null)
            webDriver.quit();
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    protected WebElement waitUntilVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitUntilClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
