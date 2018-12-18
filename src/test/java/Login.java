import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;

public class Login {
    WebDriver driver;
    WebDriverWait driverWait;
    String baseURL = "https://www.humanity.com/";
    String strUsername = "branislavzoric987@gmail.com";
    String strPassword = "lokosi10";
    Random rand = new Random();
    int rand_int1 = rand.nextInt(1000);
    String new_user_name_email= "user" + rand_int1 + "@testmail.com";



    //elememts
    By link_login = (By.className("login-button"));
    By field_username = (By.id("email"));
    By field_password = (By.id("password"));
    By button_login = (By.name("login"));
    By button_stuff = (By.xpath("//*[@id='sn_staff']"));
    By button_add_employees = By.id("act_primary");
    By field_stuff_firstname = By.id("_asf1");
    By field_stuff_lastname = By.id("_asl1");
    By field_stuff_email = By.id("_ase1");
    By button_save_employee = By.id("_as_save_multiple");
    By button_time_clock = By.xpath("//*[@id='sn_timeclock']/span/i");
    By button_clock_in = By.id("tc_tl_ci");
    By button_clock_out = By.id("tc_tl_co");
    By error_message = By.xpath("//*[@id='response-message']");
    By first_name_error_message = By.xpath("//*[@id=\'_status\']");
    By email_error_message = By.xpath("//*[@id=\'_status\']");



    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driverWait = new WebDriverWait(driver, 15);
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    @Test
    public void test() throws InterruptedException {

        //Login Test case

        //1)Open Login Page
        driver.findElement(link_login).click();
        driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(button_login)));
        Assert.assertTrue(driver.findElement(button_login).isDisplayed());

        //2)Leave blank username
        driver.findElement(field_password).sendKeys("strPassword");
        driver.findElement(button_login).click();
        Assert.assertTrue(driver.findElement(error_message).isDisplayed());

        Thread.sleep(3000);

        //3)Enter wrong password

        driver.findElement(field_username).sendKeys(strUsername);
        driver.findElement(field_password).clear();
        driver.findElement(field_password).sendKeys("wrongpassword");
        driver.findElement(button_login).click();
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(error_message).isDisplayed());
        Thread.sleep(3000);

        //4)Enter valid username and password

        driver.findElement(field_username).clear();
        driver.findElement(field_password).clear();

        driver.findElement(field_username).sendKeys(strUsername);
        driver.findElement(field_password).sendKeys(strPassword);
        driver.findElement(button_login).click();
        Thread.sleep(3000);




        //Add staff test case

        //1)Navigate to stuff tab
        driver.findElement(button_stuff).click();
        Thread.sleep(2000);

        //2)Navigate to add employee edit mode
        driver.findElement(button_add_employees).click();
        Thread.sleep(2000);

        //3)Username blank error message
        driver.findElement(field_stuff_lastname).sendKeys("failedtest");
        driver.findElement(button_save_employee).click();
        Assert.assertTrue(driver.findElement(first_name_error_message).isDisplayed());
        Thread.sleep(1000);

        //4)Email exist error message
        driver.findElement(field_stuff_lastname).clear();
        driver.findElement(field_stuff_firstname).sendKeys("failedtest");
        driver.findElement(field_stuff_email).sendKeys("test1test@testmail.com");
        driver.findElement(button_save_employee).click();
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(email_error_message).isDisplayed());


        //5)Populate fields and save employee
        driver.findElement(field_stuff_firstname).clear();
        driver.findElement(field_stuff_lastname).clear();
        driver.findElement(field_stuff_email).clear();
        driver.findElement(field_stuff_firstname).sendKeys("TestF");
        driver.findElement(field_stuff_lastname).sendKeys("TestL");
        driver.findElement(field_stuff_email).sendKeys(new_user_name_email);
        driver.findElement(button_save_employee).click();
        Thread.sleep(10000);

        //Clock in and clock out Test case

        //1)Navigate to Time clock tab
        driver.findElement(button_time_clock).click();
        Thread.sleep(5000);
        //2)Clock in
        driver.findElement(button_clock_in).click();
        Thread.sleep(5000);
        //3)Clock out
        driver.findElement(button_clock_out).click();
        Thread.sleep(5000);


    }

    @AfterTest
    public void quit() {
        driver.quit();
    }


}
