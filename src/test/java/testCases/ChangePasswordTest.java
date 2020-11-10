package testCases;

import base.utils.CustomSoftAssert;
import base.utils.ReadPropertyFile;
import org.testng.annotations.Test;
import pageObjects.AccountSettingsPage;
import testCases.base.BaseTest;

import java.util.Properties;

public class ChangePasswordTest extends BaseTest {
    Properties configuration = ReadPropertyFile.getPropertiesFile("configuration.properties");

    @Test()
    public void changePasswordSuccessfully() {
        CustomSoftAssert softAssert = new CustomSoftAssert();
        AccountSettingsPage page = new AccountSettingsPage(driver);
        if (!driver.getCurrentUrl().equals(configuration.getProperty("urlAccountSettings")))
            driver.get(configuration.getProperty("urlAccountSettings"));

        page.openChangePwdWindow();
        page.oldPwdFiled(configuration.getProperty("password"));
        page.newPwdFiled(configuration.getProperty("password"));
        page.repeatedPwdFiled(configuration.getProperty("password"));
        page.submitPwdSubmitButton().click();
        softAssert.assertTrue(page.pwdChangeConfirmationText().length() > 10);

        page.closeOverlayBtn().click();
        softAssert.assertTrue(page.isChangePwdOverlayOpen());
        softAssert.assertAll();
    }

    @Test()
    public void changePwdErrorValNotMatching() {
        CustomSoftAssert softAssert = new CustomSoftAssert();
        AccountSettingsPage page = new AccountSettingsPage(driver);
        if (!driver.getCurrentUrl().equals(configuration.getProperty("urlAccountSettings")))
            driver.get(configuration.getProperty("urlAccountSettings"));
        page.openChangePwdWindow();

        page.oldPwdFiled(configuration.getProperty("password"));
        page.newPwdFiled("test1234");
        page.repeatedPwdFiled("Test4567");
        page.submitPwdSubmitButton().click();
        softAssert.assertTrue(page.isNewFieldErrorDisplayed(), "Validation Error not displayed under new pwd field");
        softAssert.assertTrue(page.isRepeatedFieldErrorDisplayed(), "Validation Error not displayed under Old pwd field");

        page.closeOverlayBtn().click();
        softAssert.assertTrue(page.isChangePwdOverlayOpen());
        softAssert.assertAll();
    }

    @Test()
    public void changePwdErrorValPwdCriteria() {
        CustomSoftAssert softAssert = new CustomSoftAssert();
        AccountSettingsPage page = new AccountSettingsPage(driver);
        if (!driver.getCurrentUrl().equals(configuration.getProperty("urlAccountSettings")))
            driver.get(configuration.getProperty("urlAccountSettings"));
        page.openChangePwdWindow();

        page.oldPwdFiled("");
        page.newPwdFiled("testTest");
        page.repeatedPwdFiled("testTest");
        page.submitPwdSubmitButton().click();
        softAssert.assertTrue(page.isNewFieldErrorDisplayed());

        page.closeOverlayBtn().click();
        softAssert.assertTrue(page.isChangePwdOverlayOpen());
        softAssert.assertAll();
    }
}