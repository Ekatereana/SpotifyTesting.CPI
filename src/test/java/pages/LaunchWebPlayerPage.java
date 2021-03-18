package pages;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Data
public class LaunchWebPlayerPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "#segmented-desktop-launch")
    private WebElement launchButton;

    @FindBy(name = "g-recaptcha-response")
    private WebElement iFrame;


    public LaunchWebPlayerPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public LaunchWebPlayerPage(WebDriver driver, WebDriverWait wait, ExpectedCondition condition) {
        PageFactory.initElements(driver, this);
        wait.until(condition);
        this.driver = driver;
    }
}
