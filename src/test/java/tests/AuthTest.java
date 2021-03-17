package tests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.RegistrationPage;

public class AuthTest {
    private static WebDriver driver;
    private RegistrationPage registrationPagepage;
    private HomePage homePage;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.out.println("start");
        driver = new ChromeDriver();
        //Maximize Window
        driver.manage().window().maximize();
    }

//    xpath example
    @Test
    public void testRedirectToRegisterPage(){
        driver.get("https://www.spotify.com/ua-en/");
        homePage = new HomePage(driver);
        homePage.getRegistration().click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.spotify.com/ua-en/signup/");
    }

    @Test
    public void testLoginSuccess(){

    }

    @AfterClass
    public static void teardown () {
        driver.quit();
    }



}
