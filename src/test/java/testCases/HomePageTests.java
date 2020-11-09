package testCases;

import base.WebDriverSession;
import base.utils.ReadPropertyFile;
import io.github.sukgu.Shadow;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.AccountSettingsPage;
import pageObjects.BasePage;
import pageObjects.HomePage;
import testCases.base.BaseTest;

import java.util.Properties;

public class HomePageTests extends BaseTest {
    Properties configuration = ReadPropertyFile.getPropertiesFile("configuration.properties");

    @Test()
    public void navigateToAccountSettings()  {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);

        homePage.avatar().click();
        softAssert.assertTrue(homePage.userPopUp().isDisplayed());
        homePage.accountSettingsButton().click();
        softAssert.assertEquals(driver.getCurrentUrl(),configuration.getProperty("urlAccountSettings"));
        softAssert.assertAll();
    }
}
