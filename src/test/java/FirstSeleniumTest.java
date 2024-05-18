import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FirstSeleniumTest {
    public WebDriver driver;
    private String email;
    private String password;

    @Before
    public void setup() throws MalformedURLException, IOException {
        ChromeOptions options = new ChromeOptions();
        
        options.addArguments("--disable-extensions"); // Disable extensions
        options.addArguments("--disable-infobars"); // Disable infobars

        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();

        // Load configuration properties
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/config.properties"));
        email = properties.getProperty("email");
        password = properties.getProperty("password");
    }

    private ProfilePage performLogin(MainPage mainPage) {
        LoginPage loginPage = mainPage.clickLoginButton();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        return loginPage.clickLoginButton();
    }

    @Test
    public void testPage() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.getFooterText().contains("kelyhon"));
    }

    @Test
    public void testLogin() {
        MainPage mainPage = new MainPage(this.driver);
        ProfilePage profilePage = performLogin(mainPage);
        profilePage.clickProfileButton();
        Assert.assertTrue(profilePage.getTitle().contains("inform"));
    }

    @Test
    public void testLogout() {
        MainPage mainPage = new MainPage(this.driver);
        ProfilePage profilePage = performLogin(mainPage);
        profilePage.clickProfileButton();
        profilePage.clickLogoutButton();
    }

    @Test
    public void testNews() {
        MainPage mainPage = new MainPage(this.driver);
        ProfilePage profilePage = performLogin(mainPage);
        NewsPage newsPage = mainPage.clickNews();
        String title = newsPage.getNewsTitle();
        System.out.println(title);
        Assert.assertTrue("The title length is 0", title.length() > 0);
    }

    @Test
    public void testProfilePictureUpload() {
        MainPage mainPage = new MainPage(this.driver);
        ProfilePage profilePage = performLogin(mainPage);
        profilePage.clickProfileButton();
        profilePage.clickProfilePictureMenu();
        profilePage.uploadImage();
        profilePage.clickSaveImage();
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
