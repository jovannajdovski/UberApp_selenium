package tests;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PassengerHomePage;
import pages.UnregisteredUserPage;

public class PassengerLoginValidationTest {
    public static WebDriver chrome_driver;

    private final String PASSENGER_WRONG_EMAIL_FORMAT = "passenger12@gma";
    private final String PASSENGER_WRONG_EMAIL = "passenger12@gmail.com";
    private final String PASSENGER_WRONG_PASSWORD = "PeraPera123";

    private final String PASSENGER_EMAIL = "passenger@gmail.com";
    private final String PASSENGER_PASSWORD = "NekaSifra123";

    @BeforeClass
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver", "../../chromedriver.exe");
        chrome_driver = new ChromeDriver();

        chrome_driver.manage().window().maximize();
    }

    @AfterClass
    public void quitDriver() {
        chrome_driver.quit();
    }

    @Test
    public void passengerLogin() {
        UnregisteredUserPage unregisteredUserPagePassenger = new UnregisteredUserPage(chrome_driver);

        Assert.assertTrue(unregisteredUserPagePassenger.isOpened());

        unregisteredUserPagePassenger.clickOnLogin();

        LoginPage loginPagePassenger = new LoginPage(chrome_driver);
        Assert.assertTrue(loginPagePassenger.isOpened());

        loginPagePassenger.inputEmailWrongFormat();
        loginPagePassenger.inputUsername(PASSENGER_WRONG_EMAIL_FORMAT);
        Assert.assertTrue(loginPagePassenger.emailErrorIsShown());

        loginPagePassenger.inputUsername(PASSENGER_WRONG_EMAIL);
        loginPagePassenger.inputPassword(PASSENGER_WRONG_PASSWORD);
        loginPagePassenger.clickOnLogin();

        Assert.assertTrue(loginPagePassenger.errorIsShown());

        loginPagePassenger.inputUsername(PASSENGER_EMAIL);
        loginPagePassenger.inputPassword(PASSENGER_PASSWORD);
        loginPagePassenger.clickOnLogin();

        PassengerHomePage homePagePassenger=new PassengerHomePage(chrome_driver);

        Assert.assertTrue(homePagePassenger.isOpened());
    }
}
