package tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.out.println("start");
        driver = new ChromeDriver();
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
        homePage.getRegistration().click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.getEmail().sendKeys("spotify31test@gmail.com");
        loginPage.getPassword().sendKeys("test123test");
        loginPage.getSubmit().click();
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("svelte-kdyqkb")));
        Assert.assertEquals("https://www.spotify.com/ua-en/account/overview/?locale=ua-en", driver.getCurrentUrl());
    }

    @After
    public void teardown() {
        driver.quit();
    }


}
