package pages;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public void loginUser(String username, String passwd) {
        email.sendKeys(username);
        password.sendKeys(passwd);
        submit.click();
    }


}
