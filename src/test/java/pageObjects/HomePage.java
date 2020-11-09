package pageObjects;

import base.utils.ReadPropertyFile;
import io.github.sukgu.Shadow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;


public class HomePage extends BasePage {
    Properties configuration = ReadPropertyFile.getPropertiesFile("configuration.properties");
    Shadow shadow = new Shadow(driver);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement avatar() {
        return shadow.findElement("div[class='avatar-wrap']");
    }

    public WebElement userPopUp() {
        return shadow.findElement(".user-popup");
    }

    public WebElement accountSettingsButton() {
        return shadow.findElement(".main-link.account-settings");
    }

    public boolean isPageLoaded() {
        return waitUntilVisible(By.cssSelector(".TitleForVerticalNav-sc-1u1a8z4-1")).isDisplayed();

    }
}
