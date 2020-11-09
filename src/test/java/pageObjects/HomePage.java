package pageObjects;

import base.utils.ReadPropertyFile;
import io.github.sukgu.Shadow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;


public class HomePage extends BasePage {
    String avatarByCss = "div[class='avatar-wrap']";
    Properties configuration = ReadPropertyFile.getPropertiesFile("configuration.properties");
    Shadow shadow = new Shadow(driver);
    String userPopUpByCss = ".user-popup";
    String accountSettingsBtnByCss = ".main-link.account-settings";
    String referenceElementByCss = ".TitleForVerticalNav-sc-1u1a8z4-1";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement avatar() {
        return shadow.findElement(avatarByCss);
    }

    public WebElement userPopUp() {
        return shadow.findElement(userPopUpByCss);
    }

    public WebElement accountSettingsButton() {
        return shadow.findElement(accountSettingsBtnByCss);
    }

    public boolean isPageLoaded() {
        return (waitUntilVisible(By.cssSelector(referenceElementByCss)).isDisplayed()
                && driver.getCurrentUrl().equals(configuration.getProperty("url")));
    }

}
