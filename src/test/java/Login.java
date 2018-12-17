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
        //1) Open Login Page
        driver.findElement(link_login).click();
        driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(button_login)));
        Assert.assertTrue(driver.findElement(button_login).isDisplayed());

        //2)Insert username and password
        driver.findElement(field_username).sendKeys(strUsername);
        driver.findElement(field_password).sendKeys(strPassword);
        driver.findElement(button_login).click();
        Thread.sleep(3000);


        //3)Navigate to add staff
        driver.findElement(button_stuff).click();
        Thread.sleep(2000);
        driver.findElement(button_add_employees).click();
        Thread.sleep(2000);

        //4)Populate fields and save employee
        driver.findElement(field_stuff_firstname).sendKeys("TestF");
        driver.findElement(field_stuff_lastname).sendKeys("TestL");
        driver.findElement(field_stuff_email).sendKeys(new_user_name_email);
        driver.findElement(button_save_employee).click();
        Thread.sleep(10000);

        //5)Clock out
        driver.findElement(button_time_clock).click();
        Thread.sleep(5000);
        driver.findElement(button_clock_in).click();
        Thread.sleep(5000);
        driver.findElement(button_clock_out).click();


    }


}
