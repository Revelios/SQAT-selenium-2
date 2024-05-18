import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.LocalFileDetector;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.io.File;


class ProfilePage extends PageBase {
    private By titleBy = By.xpath("//div[@class='col-lg-8 loginpg']/h3");
    private By logoutButtonBy = By.xpath("//div[@class='nav flex-column nav-pills myaccounttab']/button[5]");

    private By profilePictureMenuBy = By.xpath("//div[@class='accordion']/div[4]/h2");
    private By fileInputBy = By.xpath("//input[@type='file']");
    private By saveButtonBy = By.xpath("//*[@id='f4']/div/form/div[4]/div/button");


    public ProfilePage(WebDriver driver) {
        super(driver);
    }   

    public String getTitle() {
        return this.waitAndReturnElement(titleBy).getText();
    }

    public void clickLogoutButton() {
        this.waitAndReturnElement(logoutButtonBy).click();
    }

    public void clickProfilePictureMenu() {
        this.waitAndReturnElement(profilePictureMenuBy).click();
    }

    public void uploadImage() {
        try {
            WebElement fileInput = this.waitAndReturnElement(By.xpath("//input[@type='file']"));
            String path = "src/test/resources/20240407_170544.jpg";

            // Display the file input element using JavaScript if it's hidden
            if (!fileInput.isDisplayed()) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].style.display = 'block';", fileInput);
            }

            File uploadFile = new File(path);
            fileInput.sendKeys(uploadFile.getAbsolutePath());

            System.out.println("File uploaded successfully: " + path);
        } catch (Exception e) {
            System.err.println("Error occurred while uploading the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickSaveImage() {
        this.waitAndReturnElement(saveButtonBy).click();
    }
}
