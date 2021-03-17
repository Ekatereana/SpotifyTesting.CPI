package tests;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

public class AuthTest {
    private WebDriver driver;
    private LoginPage page;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
    }

    @Test
    public void testLoginSuccess(){
        driver.get("https://www.spotify.com/ua-en/");
    }

}
