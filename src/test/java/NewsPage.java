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


class NewsPage extends PageBase {
    private By newsTitleBy = By.xpath("//h2");

    public NewsPage(WebDriver driver) {
        super(driver);
    }   

    public String getNewsTitle() {
        return this.waitAndReturnElement(newsTitleBy).getText();
    }
}
