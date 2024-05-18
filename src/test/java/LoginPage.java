import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class LoginPage extends PageBase {
    private By emailBy = By.xpath("(//input[@type='email'])[2]");
    private By passwordBy = By.xpath("(//input[@type='password'])[2]");
    private By loginButtonBy = By.xpath("(//button[@name='login'])[2]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }   

    public void setEmail(String email) {
        this.waitAndReturnElement(emailBy).click();
        this.waitAndReturnElement(emailBy).sendKeys(email);
    }

    public void setPassword(String password) {
        this.waitAndReturnElement(passwordBy).click();
        this.waitAndReturnElement(passwordBy).sendKeys(password);
    }

    public ProfilePage clickLoginButton() {
        this.waitAndReturnElement(loginButtonBy).click();
        return new ProfilePage(this.driver);
    }
}
