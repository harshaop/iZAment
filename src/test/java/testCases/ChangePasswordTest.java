package testCases;

import base.utils.CustomSoftAssert;
import base.utils.ReadPropertyFile;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.AccountSettingsPage;
import testCases.base.BaseTest;

import java.util.Properties;

public class ChangePasswordTest extends BaseTest {
    Properties configuration = ReadPropertyFile.getPropertiesFile("configuration.properties");

    @Parameters({"password"})
    @Test()
    public void changePasswordSuccessfully(@Optional("abcd1234") String password) {
        CustomSoftAssert softAssert = new CustomSoftAssert();
        AccountSettingsPage page = new AccountSettingsPage(driver);
        if (!driver.getCurrentUrl().equals(configuration.getProperty("urlAccountSettings")))
            driver.get(configuration.getProperty("urlAccountSettings"));

        page.openChangePwdWindow();
        page.oldPwdFiled(password);
        page.newPwdFiled(password);
        page.repeatedPwdFiled(password);
        page.submitPwdSubmitButton().click();
        softAssert.assertTrue(page.pwdChangeConfirmationText().length() > 10);

        page.closeOverlayBtn().click();
        softAssert.assertTrue(page.isChangePwdOverlayOpen());
        softAssert.assertAll();
    }

    @Parameters({"password"})
    @Test()
    public void changePwdErrorValNotMatching(@Optional("abcd1234") String password) {
        CustomSoftAssert softAssert = new CustomSoftAssert();
        AccountSettingsPage page = new AccountSettingsPage(driver);
        if (!driver.getCurrentUrl().equals(configuration.getProperty("urlAccountSettings")))
            driver.get(configuration.getProperty("urlAccountSettings"));
        page.openChangePwdWindow();

        page.oldPwdFiled(password);
        page.newPwdFiled("test1234");
        page.repeatedPwdFiled("Test4567");
        page.submitPwdSubmitButton().click();
        softAssert.assertTrue(page.isNewFieldErrorDisplayed(), "Validation Error not displayed under new pwd field");
        softAssert.assertTrue(page.isRepeatedFieldErrorDisplayed(), "Validation Error not displayed under Old pwd field");

        page.closeOverlayBtn().click();
        softAssert.assertTrue(page.isChangePwdOverlayOpen());
        softAssert.assertAll();
    }

    @Parameters()
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