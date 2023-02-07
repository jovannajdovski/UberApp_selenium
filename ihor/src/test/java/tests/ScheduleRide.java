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

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScheduleRide {

    public static WebDriver chrome_driver;
    public static WebDriver firefox_driver;

    private final String DRIVER_EMAIL="driver@gmail.com";
    private final String DRIVER_PASSWORD="NekaSifra123";
    private final String PASSENGER_EMAIL="passenger@gmail.com";
    private final String PASSENGER_PASSWORD="NekaSifra123";

    private final String START_LOCATION="Bulevar patrijarha Pavla 36, Novi Sad";
    private final String FINAL_LOCATION="Bulevar Evrope 4, Novi Sad";

    private final String VEHICLE_TYPE="STANDARD";

    private final Boolean BABY_TRANSPORT=true;
    private final Boolean PET_TRANSPORT=true;

    private String startAddress;
    private String endAddress;

    @BeforeClass
    public void initDriver()
    {
        System.setProperty("webdriver.gecko.driver", "../../geckodriver");
        System.setProperty("webdriver.chrome.driver", "../../chromedriver");
        chrome_driver=new ChromeDriver();

        firefox_driver = new FirefoxDriver();

        java.awt.Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int)size.getWidth();
        int height = (int)size.getHeight();

        chrome_driver.manage().window().setSize(new Dimension(width/2,height));
        firefox_driver.manage().window().setSize(new Dimension(width/2,height));
        firefox_driver.manage().window().setPosition(new Point(width/2,0));

    }
    @AfterClass
    public void quitDriver()
    {
        chrome_driver.quit();
        firefox_driver.quit();
    }
    @Test
    public void scheduleRideTest(){
        signInUsers();

        HomePage homePageDriver = new HomePage(chrome_driver);
        PassengerHomePage homePagePassenger=new PassengerHomePage(firefox_driver);

        Assert.assertTrue(homePageDriver.isOpened());
        Assert.assertTrue(homePagePassenger.isOpened());

        List<String> addresses = homePagePassenger.clickOnMapForLocations();
        startAddress = addresses.get(0);
        endAddress = addresses.get(1);
        homePagePassenger.clickContinue();

        addAdditionalRideInformation();

        OverviewPage overviewPage = new OverviewPage(firefox_driver);
        Assert.assertTrue(overviewPage.isOpened());

        Assert.assertTrue(overviewPage.checkValidity(startAddress, endAddress, BABY_TRANSPORT, PET_TRANSPORT, VEHICLE_TYPE));

        overviewPage.clickConfirm();

        CanScheduleRidePage canScheduleRidePage = new CanScheduleRidePage(firefox_driver);
        Assert.assertTrue(canScheduleRidePage.isOpened());
        Assert.assertTrue(canScheduleRidePage.isScheduled());

        canScheduleRidePage.clickBack();

        signOutUsers(homePageDriver);
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

    private void signOutUsers(HomePage homePageDriver){
        PassengerHomePage backHomePagePassenger=new PassengerHomePage(firefox_driver);
        Assert.assertTrue(backHomePagePassenger.isOpened());

        backHomePagePassenger.logout();

        LoginPage loginPagePassenger=new LoginPage(firefox_driver);
        Assert.assertTrue(loginPagePassenger.isOpened());

        chrome_driver.manage().window().maximize();
        homePageDriver.logout();
        LoginPage loginPageDriver=new LoginPage(chrome_driver);
        Assert.assertTrue(loginPageDriver.isOpened());
    }

    private void addAdditionalRideInformation(){
        AdditionalPage additionalPage = new AdditionalPage(firefox_driver);
        Assert.assertTrue(additionalPage.isOpened());

        additionalPage.selectVehicleType(VEHICLE_TYPE);
        additionalPage.selectBabyAndPetFriendly(BABY_TRANSPORT, PET_TRANSPORT);
        additionalPage.clickContinue();

        InviteFriendsPage inviteFriendsPage = new InviteFriendsPage(firefox_driver);
        Assert.assertTrue(inviteFriendsPage.isOpened());

        inviteFriendsPage.clickContinue();
    }
}
