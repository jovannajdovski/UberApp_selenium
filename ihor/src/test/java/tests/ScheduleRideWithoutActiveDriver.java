package tests;

import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.awt.*;
import java.util.List;

public class ScheduleRideWithoutActiveDriver {

    public static WebDriver chrome_driver;

    private final String PASSENGER_EMAIL="passenger@gmail.com";
    private final String PASSENGER_PASSWORD="NekaSifra123";

    private final String START_LOCATION="Bulevar Oslobodjenja 32";
    private final String FINAL_LOCATION="Bulevar Evrope 4";
    private final String VEHICLE_TYPE="STANDARD";
    private final Boolean BABY_TRANSPORT=true;
    private final Boolean PET_TRANSPORT=true;

    private String startAddress;
    private String endAddress;

    @BeforeClass
    public void initDriver()
    {
        System.setProperty("webdriver.chrome.driver", "../../chromedriver");
        chrome_driver=new ChromeDriver();

        java.awt.Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int)size.getWidth();
        int height = (int)size.getHeight();

        chrome_driver.manage().window().setSize(new Dimension(width/2,height));

    }
    @AfterClass
    public void quitDriver()
    {
        chrome_driver.quit();
    }
    @Test
    public void scheduleRideWithoutActiveDriverTest(){
        signInPassenger();

        PassengerHomePage homePagePassenger=new PassengerHomePage(chrome_driver);
        Assert.assertTrue(homePagePassenger.isOpened());

        List<String> addresses = homePagePassenger.clickOnMapForLocations();
        startAddress = addresses.get(0);
        endAddress = addresses.get(1);
        homePagePassenger.clickContinue();

        addAdditionalRideInformation();

        OverviewPage overviewPage = new OverviewPage(chrome_driver);
        Assert.assertTrue(overviewPage.isOpened());
        Assert.assertTrue(overviewPage.checkValidity(startAddress, endAddress, BABY_TRANSPORT, PET_TRANSPORT, VEHICLE_TYPE));

        overviewPage.clickConfirm();

        CanScheduleRidePage canScheduleRidePage = new CanScheduleRidePage(chrome_driver);
        Assert.assertTrue(canScheduleRidePage.isOpenedInvalid());
        Assert.assertFalse(canScheduleRidePage.isScheduled());

        canScheduleRidePage.clickBack();

        signOutUsers();
    }

    private void signInPassenger()
    {
        UnregisteredUserPage unregisteredUserPagePassenger=new UnregisteredUserPage(chrome_driver);

        Assert.assertTrue(unregisteredUserPagePassenger.isOpened());

        unregisteredUserPagePassenger.clickOnLogin();

        LoginPage loginPagePassenger=new LoginPage(chrome_driver);
        Assert.assertTrue(loginPagePassenger.isOpened());

        loginPagePassenger.inputUsername(PASSENGER_EMAIL);
        loginPagePassenger.inputPassword(PASSENGER_PASSWORD);
        loginPagePassenger.clickOnLogin();
    }

    private void signOutUsers(){
        PassengerHomePage backHomePagePassenger=new PassengerHomePage(chrome_driver);
        Assert.assertTrue(backHomePagePassenger.isOpened());

        chrome_driver.manage().window().maximize();
        backHomePagePassenger.logout();

        LoginPage loginPagePassenger=new LoginPage(chrome_driver);
        Assert.assertTrue(loginPagePassenger.isOpened());
    }

    private void addAdditionalRideInformation(){
        AdditionalPage additionalPage = new AdditionalPage(chrome_driver);
        Assert.assertTrue(additionalPage.isOpened());

        additionalPage.selectVehicleType(VEHICLE_TYPE);
        additionalPage.selectBabyAndPetFriendly(BABY_TRANSPORT, PET_TRANSPORT);
        additionalPage.clickContinue();

        InviteFriendsPage inviteFriendsPage = new InviteFriendsPage(chrome_driver);
        Assert.assertTrue(inviteFriendsPage.isOpened());

        inviteFriendsPage.clickContinue();
    }
}
