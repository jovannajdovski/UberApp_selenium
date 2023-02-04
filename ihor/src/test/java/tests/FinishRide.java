package tests;

import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

public class FinishRide {
    public static WebDriver chrome_driver;
    public static WebDriver firefox_driver;

    private final String DRIVER_EMAIL="driver@gmail.com";
    private final String DRIVER_PASSWORD="NekaSifra123";
    private final String PASSENGER_EMAIL="passenger@gmail.com";
    private final String PASSENGER_PASSWORD="NekaSifra123";

    @BeforeClass
    public void initDriver()
    {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        chrome_driver=new ChromeDriver();

        firefox_driver = new FirefoxDriver();

        chrome_driver.manage().window().setSize(new Dimension(1450,1100));
        firefox_driver.manage().window().setSize(new Dimension(800,1100));
        firefox_driver.manage().window().setPosition(new Point(1200,0));
    }
    @AfterClass
    public void quitDriver()
    {
        chrome_driver.quit();
        firefox_driver.quit();
    }
    @Test
    public void finishRideTest(){
        signInUsers();

        HomePage homePageDriver=new HomePage(chrome_driver);
        HomePage homePagePassenger=new HomePage(firefox_driver);

        CurrentRidePage currentRidePageDriver=new CurrentRidePage(chrome_driver);
        CurrentRidePage currentRidePagePassenger=new CurrentRidePage(firefox_driver);

        checkNoCurrentRideBeforeStartingIt(currentRidePageDriver,currentRidePagePassenger,homePageDriver,homePagePassenger);

        currentRidePageDriver.clickOnNextRides();
        currentRidePagePassenger.clickOnBackToHomePage();

        NextRidesPage nextRidesPage=new NextRidesPage(chrome_driver);
        Assert.assertTrue(nextRidesPage.isPageOpened());
        nextRidesPage.startFirstRide();
        nextRidesPage.clickOnCurrentRide();

        Assert.assertTrue(homePagePassenger.isOpened());
        homePagePassenger.clickOnCurrentRide();

        Assert.assertFalse(currentRidePageDriver.hasNotCurrentRide());
        Assert.assertFalse(currentRidePagePassenger.hasNotCurrentRide());

        Assert.assertTrue(currentRidePageDriver.isCarIconVisible());
        Assert.assertTrue(currentRidePagePassenger.isCarIconVisible());

        currentRidePageDriver.waitToReachDestination();

        currentRidePageDriver.clickOnFinishRide();

        checkNoCurrentRideAfterFinishingIt(currentRidePageDriver,currentRidePagePassenger,homePageDriver,homePagePassenger);
    }

    private void signInUsers()
    {
        UnregisteredUserPage unregisteredUserPageDriver=new UnregisteredUserPage(chrome_driver);
        UnregisteredUserPage unregisteredUserPagePassenger=new UnregisteredUserPage(firefox_driver);

        Assert.assertTrue(unregisteredUserPageDriver.isOpened());
        Assert.assertTrue(unregisteredUserPagePassenger.isOpened());

        unregisteredUserPageDriver.clickOnLogin();
        unregisteredUserPagePassenger.clickOnLogin();

        LoginPage loginPageDriver=new LoginPage(chrome_driver);
        LoginPage loginPagePassenger=new LoginPage(firefox_driver);

        Assert.assertTrue(loginPageDriver.isOpened());
        Assert.assertTrue(loginPagePassenger.isOpened());

        loginPageDriver.inputUsername(DRIVER_EMAIL);
        loginPageDriver.inputPassword(DRIVER_PASSWORD);
        loginPageDriver.clickOnLogin();

        loginPagePassenger.inputUsername(PASSENGER_EMAIL);
        loginPagePassenger.inputPassword(PASSENGER_PASSWORD);
        loginPagePassenger.clickOnLogin();
    }

    private void checkNoCurrentRideBeforeStartingIt(CurrentRidePage currentRidePageDriver, CurrentRidePage currentRidePagePassenger, HomePage homePageDriver, HomePage homePagePassenger){
        Assert.assertTrue(homePageDriver.isOpened());
        Assert.assertTrue(homePagePassenger.isOpened());

        homePageDriver.clickOnCurrentRide();
        homePagePassenger.clickOnCurrentRide();

        Assert.assertTrue(currentRidePageDriver.hasNotCurrentRide());
        Assert.assertTrue(currentRidePagePassenger.hasNotCurrentRide());
    }
    private void checkNoCurrentRideAfterFinishingIt(CurrentRidePage currentRidePageDriver, CurrentRidePage currentRidePagePassenger, HomePage homePageDriver, HomePage homePagePassenger){
        Assert.assertTrue(currentRidePageDriver.hasNotCurrentRide());
        Assert.assertTrue(currentRidePagePassenger.hasNotCurrentRide());

        currentRidePageDriver.clickOnBackToHomePage();
        currentRidePagePassenger.clickOnBackToHomePage();

        Assert.assertTrue(homePageDriver.isOpened());
        Assert.assertTrue(homePagePassenger.isOpened());

        homePageDriver.clickOnCurrentRide();
        homePagePassenger.clickOnCurrentRide();

        Assert.assertTrue(currentRidePageDriver.hasNotCurrentRide());
        Assert.assertTrue(currentRidePagePassenger.hasNotCurrentRide());
    }
}
