package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CurrentRidePage {
    private WebDriver driver;

    @FindBy(css = "#continueButton")
    WebElement backToHomePageButton;
    @FindBy(css = "#finishButton")
    WebElement finishButton;

    @FindBy(css = "#next-rides-button")
    WebElement nextRidesButton;
    @FindBy(css = "#map")
    WebElement mapView;

    @FindBy(xpath = "//img[contains(@src,'/car')]")
    WebElement busyCarIcon;
    @FindBy(xpath = "//img[contains(@src,'freecar')]")
    WebElement freeCarIcon;
    public CurrentRidePage(WebDriver webDriver)
    {
        this.driver=webDriver;
        PageFactory.initElements(this.driver,this);
    }
    public boolean hasNotCurrentRide(){
        try{
            WebDriverWait wait=new WebDriverWait(this.driver, 3);
            wait.until(ExpectedConditions.elementToBeClickable(backToHomePageButton));
            return true;
        }
        catch(NoSuchElementException | TimeoutException e)
        {
            WebDriverWait wait=new WebDriverWait(this.driver, 10);
            wait.until(ExpectedConditions.visibilityOf(mapView));
            return false;
        }

    }
    public void clickOnNextRides()
    {
        nextRidesButton.click();
    }
    public void clickOnFinishRide(){
        finishButton.click();
    }
    public void clickOnBackToHomePage(){
        backToHomePageButton.click();
    }

    public boolean isCarIconVisible()
    {
        try{
            WebDriverWait wait=new WebDriverWait(this.driver, 3);
            wait.until(ExpectedConditions.visibilityOf(busyCarIcon));
        }
        catch(NoSuchElementException | TimeoutException e)
        {
            WebDriverWait wait=new WebDriverWait(this.driver, 3);
            wait.until(ExpectedConditions.visibilityOf(freeCarIcon));
        }
        return true;
    }
    public void waitToReachDestination(){
        WebDriverWait wait=new WebDriverWait(this.driver, 5*60);
        wait.until(ExpectedConditions.visibilityOf(freeCarIcon));
    }
}
