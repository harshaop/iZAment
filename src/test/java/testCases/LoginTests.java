package testCases;

import base.utils.ReadPropertyFile;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import testCases.base.BaseTest;

import java.util.Properties;

public class LoginTest extends BaseTest {
    Properties configuration = ReadPropertyFile.getPropertiesFile("configuration.properties");

    @Test
    public void LoginTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(session.getWebDriver());
        loginPage.enterEmailId(configuration.getProperty("email"));
        loginPage.clickSubmitBtn();
        loginPage.enterPassword(configuration.getProperty("password"));
        loginPage.clickSubmitBtn();
        Assert.assertTrue(loginPage.isLoaded());
    }


}
