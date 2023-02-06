package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    @FindBy(css = "*[name='email']")
    WebElement usernameField;
    @FindBy(css = "*[name='password']")
    WebElement passwordField;
    @FindBy(css = "#loginbtn")
    WebElement loginButton;
    @FindBy(css = ".login-error")
    WebElement loginError;
    @FindBy(css = ".input-error")
    WebElement emailError;

    public LoginPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(this.driver, this);
    }

    public void inputUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void inputPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickOnLogin() {
        loginButton.click();
    }

    public void clickOnEmailInput() {
        usernameField.click();
    }

    public void inputEmailWrongFormat() {
        usernameField.click();
        usernameField.sendKeys(Keys.SPACE);
        usernameField.sendKeys(Keys.RETURN);

    }

    public boolean isOpened() {
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        return true;
    }

    public boolean errorIsShown() {
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.visibilityOf(loginError));
        return true;
    }

    public boolean emailErrorIsShown() {
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.visibilityOf(emailError));
        return true;
    }
}
