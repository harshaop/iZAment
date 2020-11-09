package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AccountSettingsPage extends BasePage {
    By changePwdButton = By.xpath("//*[@href='/settings/change_password']");
    By oldPwdField = By.id("user_password_old_password");
    By newPwdField = By.id("user_password_new_password");
    By repeatedPwdField = By.id("user_password_new_password_repeated");
    By changePwdOverlay = By.cssSelector(".overlay");
    By closeOverlay = By.cssSelector(".close.close-icon");
    By newPwdValidationErrorFiled = By.cssSelector(".validation-errors.new_password");
    By oldPwdValidationErrorFiled = By.cssSelector(".validation-errors.new_password_repeated");
    By submitPwdButton = By.xpath("//*[@class='overlay']//button[@type='submit']");
    By pwdChangeConfirmationText = By.xpath("//*[@class='overlay']//*[@class='change-password']/p");
    String isNewPwdValidationErrorFiledDisplayedByCss = ".validation-errors.new_password";

    public AccountSettingsPage(WebDriver driver) {
        super(driver);
    }

    public void openChangePwdWindow() {
        waitUntilVisible(changePwdButton).click();
    }

    public boolean isChangePwdOverlayOpen() {
        return waitUntilVisible(changePwdOverlay).isDisplayed();
    }

    public boolean isNewFieldErrorDisplayed() {
        return waitUntilVisible(newPwdValidationErrorFiled).isDisplayed();
    }

    public boolean isOldFieldErrorDisplayed() {
        return waitUntilVisible(oldPwdValidationErrorFiled).isDisplayed();
    }

    public boolean isRepeatedFieldErrorDisplayed() {
        return waitUntilVisible(repeatedPwdField).isDisplayed();
    }

    public void oldPwdFiled(String keys) {
        WebElement e = waitUntilVisible(oldPwdField);
        e.clear();
        e.sendKeys(keys);
    }

    public void newPwdFiled(String keys) {
        WebElement e = driver.findElement(newPwdField);
        e.clear();
        e.sendKeys(keys);
    }

    public void repeatedPwdFiled(String keys) {
        WebElement e = driver.findElement(repeatedPwdField);
        e.clear();
        e.sendKeys(keys);
    }

    public WebElement closeOverlayBtn() {
        return driver.findElement(closeOverlay);
    }

    public WebElement submitPwdSubmitButton() {
        return waitUntilClickable(submitPwdButton);
    }

    public boolean isNewPwdValidationErrorFiledDisplayed() {
        return waitUntilVisible(By.cssSelector(isNewPwdValidationErrorFiledDisplayedByCss)).isDisplayed();
    }

    public String pwdChangeConfirmationText() {
        return waitUntilVisible(pwdChangeConfirmationText).getText();
    }
}
