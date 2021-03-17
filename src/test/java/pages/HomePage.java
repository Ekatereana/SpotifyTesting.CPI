package pages;

import jdk.jfr.DataAmount;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


@Data
public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"__next\"]/div[1]/header/div/nav/ul/li[6]/a")
    private WebElement registration;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
