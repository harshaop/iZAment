package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmailId(String emailId) {
        WebElement emailField = waitUntilVisible(By.id("email"));
        emailField.clear();
        emailField.sendKeys(emailId);
    }

    public void clickSubmitBtn() {
        WebElement submitBtn = waitUntilClickable(By.id("submitBtn"));
        submitBtn.click();
    }

    public void enterPassword(String password) throws InterruptedException {
        WebElement passwordField = waitUntilVisible(By.id("password"));
        Thread.sleep(1000);
        passwordField.sendKeys(password);
    }

    public boolean isLoaded() {
        return true; //TODO
    }
}
