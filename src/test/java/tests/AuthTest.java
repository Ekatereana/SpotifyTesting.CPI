package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;

import java.net.URL;

public class AuthTest {
    private static WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;

    @SneakyThrows
    @Before
    public void setup() {
//        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--proxy-server='direct://'");
        options.addArguments("--proxy-bypass-list=*");
        options.addArguments("--start-maximized");

        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--ignore-certificate-errors");
        driver = new RemoteWebDriver(
                new URL("http://"+System.getProperty("host") + ":4444/wd/hub/"), options);
    }

    //    xpath example
    @Test
    public void testRedirectToRegisterPage() {
        driver.get("https://www.spotify.com/ua-en/");
        homePage = new HomePage(driver);
        homePage.getRegistration().click();
        Assert.assertEquals(driver.getTitle(), "Войти - Spotify");
    }

//    id example
    @Test
    public void testLoginSuccess() {
        driver.get("https://www.spotify.com/ua-en/");
        wait = new WebDriverWait(driver, 15);
        homePage = new HomePage(driver);
        homePage.goToRegistration(wait);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("spotify31test@gmail.com", "test123test");
        wait.until(ExpectedConditions.elementToBeClickable(By.className("svelte-kdyqkb")));
        Assert.assertEquals("https://www.spotify.com/ua-en/account/overview/?locale=ua-en", driver.getCurrentUrl());
    }

    @After
    public void teardown() {
        driver.quit();
    }


}
