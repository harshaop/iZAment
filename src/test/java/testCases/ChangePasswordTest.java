package testCases;

import base.utils.ReadPropertyFile;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.AccountSettingsPage;
import testCases.base.BaseTest;


import java.util.Properties;

public class ChangePasswordTest extends BaseTest {
    Properties configuration = ReadPropertyFile.getPropertiesFile("configuration.properties");

    @Test()
    public void changePasswordSuccessfully()  {
        SoftAssert softAssert = new SoftAssert();
        AccountSettingsPage page = new AccountSettingsPage(driver);
        if (!driver.getCurrentUrl().equals(configuration.getProperty("urlAccountSettings")))
            driver.get(configuration.getProperty("urlAccountSettings"));

        page.openChangePwdWindow();

        page.oldPwdFiled(configuration.getProperty("password"));
        page.newPwdFiled(configuration.getProperty("password"));
        page.repeatedPwdFiled(configuration.getProperty("password"));
        page.submitPwdSubmitButton().click();
        softAssert.assertTrue(page.pwdChangeConfirmationText().contains("updated"));

        page.closeOverlayBtn().click();
        softAssert.assertTrue(!page.isChangePwdOverlayOpen());
    }

    @Test()
    public void changePwdErrorValNotMatching() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        AccountSettingsPage page = new AccountSettingsPage(driver);
        if (!driver.getCurrentUrl().equals(configuration.getProperty("urlAccountSettings")))
            driver.get(configuration.getProperty("urlAccountSettings"));
        page.openChangePwdWindow();

        page.oldPwdFiled(configuration.getProperty("password"));
        page.newPwdFiled("test1234");
        page.repeatedPwdFiled("Test4567");
        page.submitPwdSubmitButton().click();
        softAssert.assertTrue(page.isNewFieldErrorDisplayed());
        softAssert.assertTrue(page.isRepeatedFieldErrorDisplayed());

        page.closeOverlayBtn().click();
        softAssert.assertTrue(!page.isChangePwdOverlayOpen());
    }

    @Test()
    public void changePwdErrorValPwdCriteria() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
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
        softAssert.assertTrue(!page.isChangePwdOverlayOpen());
    }
}