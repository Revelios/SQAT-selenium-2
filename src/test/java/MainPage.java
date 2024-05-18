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
import org.openqa.selenium.Cookie;
import java.util.Set;


class MainPage extends PageBase {
    private By footerBy = By.xpath("//div[@class='footer']/div[2]");
    private By cookiesButtonBy = By.xpath("//div[@class='qc-cmp2-summary-buttons']/button[2]/span");
    private By firstNewsBy = By.xpath("//div[@class='cikk first ']/div");
    
    public MainPage(WebDriver driver) {
        super(driver);

        this.driver.get("https://szekelyhon.ro/");
        this.waitAndReturnElement(cookiesButtonBy).click();
    }    

    public String getFooterText() {
        return this.waitAndReturnElement(footerBy).getText();
    }

    public LoginPage clickLoginButton() {
        this.clickProfileButton();
        return new LoginPage(this.driver);
    }

    public NewsPage clickNews() {
        this.waitAndReturnElement(firstNewsBy).click();
        return new NewsPage(this.driver);
    }
}
