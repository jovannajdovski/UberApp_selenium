package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PassengerHomePage {

    private WebDriver driver;

    @FindBy(css = "#currentRideBtn")
    WebElement currentRideButton;
    @FindBy(css = "#next-rides-button")
    WebElement nextRidesButton;
    @FindBy(css = "#sign-out-button")
    WebElement logoutButton;

    @FindBy(css = "#startLoc input")
    WebElement departureInput;

    @FindBy(css = "#finalLoc input")
    WebElement destinationInput;

    @FindBy(css = "#estimateBtn")
    WebElement continueBtn;
    public PassengerHomePage(WebDriver webDriver)
    {
        this.driver=webDriver;
        PageFactory.initElements(this.driver,this);
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

    public void enterLocations(String departure, String destination){
        departureInput.sendKeys(departure);
        destinationInput.sendKeys(destination);
    }

    public void clickContinue(){
        continueBtn.click();
    }

    public void logout(){
        logoutButton.click();
    }
}
