package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LaunchWebPlayerPage;
import pages.LoginPage;

public class CommonRoutesTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private LaunchWebPlayerPage page;
    private HomePage homePage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("driver"));
        System.out.println("start");
        driver = new ChromeDriver();
        // full size window
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);
//        login user
        driver.get("https://www.spotify.com/ua-en/");
        homePage = new HomePage(driver);
        homePage.goToRegistration();
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
        WebElement free = wait.until(ExpectedConditions.elementToBeClickable((By.linkText("Start Free Trial"))));
        Assert.assertFalse(free.getCssValue("background-color").equals("#000"));


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
