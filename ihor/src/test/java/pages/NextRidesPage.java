package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NextRidesPage {
    private WebDriver driver;
    @FindBy(css = "#currentRideBtn")
    WebElement currentRideButton;
    @FindBy(css = ".btn-play:first-child")
    WebElement startRideButton;
    @FindBy(css = "#title")
    WebElement pageTitle;
    public NextRidesPage(WebDriver webDriver)
    {
        this.driver=webDriver;
        PageFactory.initElements(this.driver,this);
    }
    public void startFirstRide()
    {
        startRideButton.click();
    }
    public boolean isPageOpened(){
        WebDriverWait wait=new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return true;
    }
    public void clickOnCurrentRide()
    {
        currentRideButton.click();
    }
}
