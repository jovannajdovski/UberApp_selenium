package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CanScheduleRidePage {

    private WebDriver driver;

    @FindBy(css = "h1")
    WebElement heading;
    @FindBy(css = "#continueButton")
    WebElement backBtn;
    public CanScheduleRidePage(WebDriver webDriver)
    {
        this.driver=webDriver;
        PageFactory.initElements(this.driver,this);
    }

    public boolean isOpened() {
        WebDriverWait wait=new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.visibilityOf(backBtn));
        return true;
    }

    public boolean isScheduled() {
        return heading.getText().equals("Payment successfull");
    }

    public void clickBack(){
        backBtn.click();
    }
}
