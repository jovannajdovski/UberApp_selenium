package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InviteFriendsPage {

    private WebDriver driver;

    @FindBy(css = "#continueButton")
    WebElement continueBtn;

    WebElement timePicker;
    public InviteFriendsPage(WebDriver webDriver)
    {
        this.driver=webDriver;
        PageFactory.initElements(this.driver,this);
    }

    public void clickContinue(){
        continueBtn.click();
    }

    public boolean isOpened() {
        WebDriverWait wait=new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.visibilityOf(continueBtn));
        return true;
    }
}
