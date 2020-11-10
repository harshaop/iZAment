package testCases;

import base.utils.CustomSoftAssert;
import base.utils.ReadPropertyFile;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testCases.base.BaseTest;

import java.util.Properties;

public class LoginTests extends BaseTest {
    Properties configuration = ReadPropertyFile.getPropertiesFile("configuration.properties");

    @Test
    public void LoginTest() throws InterruptedException {
        CustomSoftAssert softAssert = new CustomSoftAssert();
        LoginPage loginPage = new LoginPage(session.getWebDriver());
        String email = configuration.getProperty("email");
        String password = configuration.getProperty("password");
        loginPage.enterEmailId(email);
        loginPage.clickSubmitBtn();

        softAssert.assertEquals(loginPage.getUserNameField(), email);
        Thread.sleep(500);
        loginPage.enterPassword(password);
        loginPage.clickSubmitBtn();

        HomePage homePage = new HomePage(session.getWebDriver());
        softAssert.assertTrue(homePage.isPageLoaded());
        softAssert.assertEquals(driver.getCurrentUrl(), configuration.getProperty("url"));
        softAssert.assertAll();
    }
}
