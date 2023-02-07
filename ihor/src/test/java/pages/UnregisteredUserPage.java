package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UnregisteredUserPage {
    private WebDriver driver;
    @FindBy(css = "#login-button")
    WebElement loginButton;

    public UnregisteredUserPage(WebDriver webDriver) {
        this.driver = webDriver;
        this.driver.get("http://localhost:4200/");
        PageFactory.initElements(this.driver, this);

    }

    public void clickOnLogin() {
        loginButton.click();
    }

    public boolean isOpened() {
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        return true;
    }

}
