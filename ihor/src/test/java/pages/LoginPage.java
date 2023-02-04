package pages;

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
    public LoginPage(WebDriver webDriver)
    {
        this.driver=webDriver;
        PageFactory.initElements(this.driver,this);
    }
    public void inputUsername(String username)
    {
        usernameField.sendKeys(username);
    }
    public void inputPassword(String password)
    {
        passwordField.sendKeys(password);
    }
    public void clickOnLogin()
    {
        loginButton.click();
    }
    public boolean isOpened() {
        WebDriverWait wait=new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        return true;
    }
}
