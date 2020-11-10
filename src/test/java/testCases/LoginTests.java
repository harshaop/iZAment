package testCases;

import base.utils.CustomSoftAssert;
import base.utils.ReadPropertyFile;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testCases.base.BaseTest;

import java.util.Properties;

public class LoginTests extends BaseTest {
    Properties configuration = ReadPropertyFile.getPropertiesFile("configuration.properties");

    @Parameters({"username", "password"})
    @Test
    public void LoginTest(@Optional("harshaop@gmail.com") String email, @Optional("xxxxxx") String password) {
        CustomSoftAssert softAssert = new CustomSoftAssert();
        LoginPage loginPage = new LoginPage(session.getWebDriver());
        loginPage.enterEmailId(email);
        loginPage.clickSubmitBtn();

        softAssert.assertEquals(loginPage.getUserNameField().contains(email), email);
        loginPage.enterPassword(password);
        loginPage.clickSubmitBtn();

        HomePage homePage = new HomePage(session.getWebDriver());
        softAssert.assertTrue(homePage.isPageLoaded());
        softAssert.assertEquals(driver.getCurrentUrl(), configuration.getProperty("url"));
        softAssert.assertAll();
    }
}
