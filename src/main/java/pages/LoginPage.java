package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {

    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void login(String user, String pass) {
        visible(username).sendKeys(user);
        visible(password).sendKeys(pass);
        clickable(loginBtn).click();
        waitForBreadcrumb();
    }
}