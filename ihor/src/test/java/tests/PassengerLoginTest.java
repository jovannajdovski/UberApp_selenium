package tests;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UnregisteredUserPage;

public class PassengerLoginTest {
    public static WebDriver chrome_driver;

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

        loginPagePassenger.inputUsername(PASSENGER_EMAIL);
        loginPagePassenger.inputPassword(PASSENGER_PASSWORD);
        loginPagePassenger.clickOnLogin();
    }
}
