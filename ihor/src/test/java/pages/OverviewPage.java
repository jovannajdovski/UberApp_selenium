package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OverviewPage {

    private WebDriver driver;

    @FindBy(css = "#continueButton")
    WebElement confirmBtn;

    @FindBy(css = "#location > :first-child")
    WebElement departureElement;

    @FindBy(css = "#location > :last-child")
    WebElement destinationElement;


    @FindBy(css = "#right > :nth-child(1) > :last-child")
    WebElement babyTransportElement;

    @FindBy(css = "#right > :nth-child(2) > :last-child")
    WebElement petTransportElement;

    @FindBy(css = "#right > :nth-child(3) > :last-child")
    WebElement vehicleTypeElement;
    public OverviewPage(WebDriver webDriver)
    {
        this.driver=webDriver;
        PageFactory.initElements(this.driver,this);
    }

    public void clickConfirm(){
        confirmBtn.click();
    }

    public boolean isOpened() {
        WebDriverWait wait=new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.visibilityOf(confirmBtn));
        return true;
    }

    public boolean checkValidity(String departure, String destination, Boolean babyTransport, Boolean petTransport, String vehicleType){
        if(!departureElement.getText().equals(departure)) return false;
        if(!destinationElement.getText().equals(destination)) return false;

        if(babyTransport && babyTransportElement.getText().equals("No baby transport")) return false;
        if(!babyTransport && babyTransportElement.getText().equals("Baby transport")) return false;

        if(petTransport && petTransportElement.getText().equals("No pet transport")) return false;
        if(!petTransport && petTransportElement.getText().equals("Pet transport")) return false;

        if(!vehicleTypeElement.getText().equals(vehicleType)) return false;

        return true;
    }
}
