package pages;

import lombok.Data;
import lombok.Value;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Data
public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "login-username")
    private WebElement email;

    @FindBy(id = "login-password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement submit;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }




}
