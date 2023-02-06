package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdditionalPage {

    private WebDriver driver;

    @FindBy(css = "#continueButton")
    WebElement continueBtn;

    @FindBy(css = "#vehicleType mat-label")
    WebElement selectLabel;

    @FindBy(name = "vehicleType")
    WebElement selectVehicleType;

    @FindBy(css = "#mat-option-1")
    WebElement standardOption;

    @FindBy(css = "#mat-option-2")
    WebElement luxuryOption;

    @FindBy(css = "#mat-option-3")
    WebElement vanOption;

    @FindBy(css = "#babyCheckbox label")
    WebElement babyTransportLabel;
    @FindBy(css = "#babyCheckbox-input")
    WebElement babyTransport;

    @FindBy(css = "#petsCheckbox-input")
    WebElement petTransport;

    @FindBy(css = "input.time-picker")
    WebElement timePicker;
    public AdditionalPage(WebDriver webDriver)
    {
        this.driver=webDriver;
        PageFactory.initElements(this.driver,this);
    }

    public void selectVehicleType(String type){
        selectVehicleType.click();
        WebDriverWait wait=new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.visibilityOf(standardOption));
        switch (type) {
            case "LUXURY" -> luxuryOption.click();
            case "VAN" -> vanOption.click();
            default -> standardOption.click();
        }
    }

    public void selectBabyAndPetFriendly(Boolean baby, Boolean pet){
        WebDriverWait wait=new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.visibilityOf(babyTransportLabel));
        if (baby) babyTransport.click();
        if (pet) petTransport.click();
    }

    public void clickContinue(){
        continueBtn.click();
    }

    public boolean isOpened() {
        WebDriverWait wait=new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.visibilityOf(selectLabel));
        return true;
    }
}
