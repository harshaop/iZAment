package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By emailFieldById = By.id("email");
    private final By submitButtonById = By.id("submitBtn");
    private final By pwdFieldById = By.id("password");
    private final By userNameFieldByCss = By.cssSelector(".username-selector");

    public void enterEmailId(String emailId) {
        WebElement email = waitUntilVisible(emailFieldById);
        email.clear();
        email.sendKeys(emailId);
    }

    public void clickSubmitBtn() {
        WebElement submitBtn = waitUntilClickable(submitButtonById);
        submitBtn.click();
    }

    public void enterPassword(String password) {
        WebElement pwdField = waitUntilVisible(pwdFieldById);
        pwdField.sendKeys(password);
    }

    public String getUserNameField() {
        return waitUntilVisible(userNameFieldByCss).getText();
    }
}
