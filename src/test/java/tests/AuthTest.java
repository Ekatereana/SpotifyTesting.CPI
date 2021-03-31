package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;

public class AuthTest {
    private static WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        driver = new ChromeDriver(options);
        // full size window
        driver.manage().window().maximize();
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
        homePage = new HomePage(driver);
        homePage.goToRegistration();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("spotify31test@gmail.com", "test123test");
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("svelte-kdyqkb")));
        Assert.assertEquals("https://www.spotify.com/ua-en/account/overview/?locale=ua-en", driver.getCurrentUrl());
    }

    @After
    public void teardown() {
        driver.quit();
    }


}
