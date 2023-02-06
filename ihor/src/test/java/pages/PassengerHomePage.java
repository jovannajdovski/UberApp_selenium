package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PassengerHomePage {

    private WebDriver driver;

    @FindBy(css = "#map")
    WebElement map;
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

    public List<String> clickOnMapForLocations(){
        List<String> addresses = new ArrayList<>();

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);

        Actions actionsDeparture = new Actions(driver);

        int getX = map.getLocation().getX();
        int getY = map.getLocation().getY();

        actionsDeparture.moveByOffset(getX+100, getY-150).click();
        actionsDeparture.build().perform();

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.HOME);

        WebDriverWait wait=new WebDriverWait(this.driver, 10);
        wait.until(new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver driver) {
                            return driver.findElement(By.cssSelector("#startLoc input")).getAttribute("value").length() != 0;
                        }
                    });

        WebDriverWait waitStartInput=new WebDriverWait(this.driver, 10);
        waitStartInput.until(ExpectedConditions.visibilityOf(departureInput));
        System.out.println(departureInput.getText());
        addresses.add(departureInput.getAttribute("value"));

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);

        Actions actionsDestination = new Actions(driver);

        getX = map.getLocation().getX();
        getY = map.getLocation().getY();

        actionsDestination.moveByOffset(getX+500, getY-900).click();
        actionsDestination.build().perform();

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.HOME);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.cssSelector("#finalLoc input")).getAttribute("value").length() != 0;
            }
        });

        WebDriverWait waitFinalInput=new WebDriverWait(this.driver, 10);
        waitFinalInput.until(ExpectedConditions.visibilityOf(destinationInput));

        addresses.add(destinationInput.getAttribute("value"));
        return addresses;
    }

    public void clickContinue(){
        continueBtn.click();
    }

    public void logout(){
        logoutButton.click();
    }
}
