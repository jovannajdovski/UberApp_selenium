package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;

    @FindBy(css = "#currentRideBtn")
    WebElement currentRideButton;
    @FindBy(css = "#next-rides-button")
    WebElement nextRidesButton;
    @FindBy(css = "#sign-out-button")
    WebElement logoutButton;
    public HomePage(WebDriver webDriver)
    {
        this.driver=webDriver;
        PageFactory.initElements(this.driver,this);
    }
    public void clickOnCurrentRide()
    {
        currentRideButton.click();
    }
    public void clickOnNextRides()
    {
        nextRidesButton.click();
    }
    public boolean isOpened() {
        WebDriverWait wait=new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.visibilityOf(currentRideButton));
        return true;
    }

    public void logout(){
        logoutButton.click();
    }
}
