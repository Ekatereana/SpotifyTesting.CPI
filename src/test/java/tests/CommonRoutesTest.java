package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LaunchWebPlayerPage;
import pages.LoginPage;

import java.net.URL;

public class CommonRoutesTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private LaunchWebPlayerPage page;
    private HomePage homePage;

    @SneakyThrows
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
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
        System.out.println("http://"+System.getProperty("host") + ":4444/wd/hub/");
        driver = new RemoteWebDriver(
                new URL("http://"+System.getProperty("host") + ":4444/wd/hub/"), options);

        // full size window

//        login user
        driver.get("https://www.spotify.com/ua-en/");
        wait = new WebDriverWait(driver, 20);
        homePage = new HomePage(driver);
        homePage.goToRegistration(wait);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("spotify31test@gmail.com", "test123test");
// go to launch webpalyer page
        WebElement menu =
                wait.until(ExpectedConditions
                        .elementToBeClickable(By.xpath("//*[@id=\"__next\"]/div/div/div[1]/header/div/div[1]/a")));
        menu.click();
    }

    //    link test failed text
    @Test
    public void testLaunchButtonBackgroundColor() {
//        go to launch web player page
        Assert.assertEquals("https://www.spotify.com/ua-en/home/", driver.getCurrentUrl());
        if(Boolean.valueOf(System.getProperty("failure"))){
            WebElement free = wait.until(ExpectedConditions.elementToBeClickable((By.linkText("Start Free Trial"))));
            Assert.assertFalse(free.getCssValue("background-color").equals("#000"));
        }


    }

    //    test css
    @Test
    public void testBackgroundColorOnHover() throws InterruptedException {
//        go to launch web player page
        LaunchWebPlayerPage launchWebPlayerPage =
                new LaunchWebPlayerPage(
                        driver,
                        wait,
                        ExpectedConditions.elementToBeClickable(By.cssSelector("#segmented-desktop-launch")));
        Assert.assertEquals("rgba(0, 0, 0, 0)",
                launchWebPlayerPage.getLaunchButton().getCssValue("background-color"));
    }

    //   name negative test passed
    @Test
    public void testIFrameNotDisplayed() {
        LaunchWebPlayerPage launchWebPlayerPage = new LaunchWebPlayerPage(
                driver,
                wait,
                ExpectedConditions.numberOfElementsToBeMoreThan(By.name("g-recaptcha-response"), 0)
        );
        Assert.assertFalse(launchWebPlayerPage.getIFrame().isDisplayed());


    }

    @After
    public void teardown() {
        driver.quit();
    }
}
