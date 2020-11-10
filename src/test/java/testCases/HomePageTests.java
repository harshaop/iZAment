package testCases;

import base.utils.CustomSoftAssert;
import base.utils.ReadPropertyFile;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testCases.base.BaseTest;

import java.util.Properties;

public class HomePageTests extends BaseTest {
    Properties configuration = ReadPropertyFile.getPropertiesFile("configuration.properties");

    @Test()
    public void navigateToAccountSettings() {
        CustomSoftAssert softAssert = new CustomSoftAssert();
        HomePage homePage = new HomePage(driver);
        homePage.avatar().click();
        softAssert.assertTrue(homePage.userPopUp().isDisplayed());
        homePage.accountSettingsButton().click();
        softAssert.assertEquals(driver.getCurrentUrl(), configuration.getProperty("urlAccountSettings"));
        softAssert.assertAll();
    }
}
