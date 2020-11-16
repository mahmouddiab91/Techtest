import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;

public class Registration {
    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() throws InterruptedException {

        //Maximize browser and wait till page loaded
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        driver.get("http://automationpractice.com/index.php");

        //Press on sign in button
        driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();

        //Sign up using new Email
        driver.findElement(By.xpath("//*[@id='email_create']")).sendKeys("tect4_test20@gmail.com");
        driver.findElement(By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/form[1]/div[1]/div[3]/button[1]/span[1]")).click();

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        //Fill all mandatory fields
        driver.findElement(By.xpath("//input[@id='id_gender1']")).click();

        driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys("Mahmoud");
        driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys("Diab");
        driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("12345678");

        Thread.sleep(1500);
        WebElement day = driver.findElement(By.id("days"));
        Select dayvalue = new Select(day);
        dayvalue.selectByValue("9");

        WebElement month = driver.findElement(By.id("months"));
        Select monthvalue = new Select(month);
        monthvalue.selectByValue("9");

        WebElement year = driver.findElement(By.id("years"));
        Select yearvalue = new Select(year);
        yearvalue.selectByValue("1990");

        Thread.sleep(1500);

        //Scroll down till find the "Address" Element
        WebElement add1 = driver.findElement(By.xpath("//input[@id='address1']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", add1);

        Thread.sleep(1500);
        driver.findElement(By.xpath("//input[@id='address1']")).sendKeys("7 street");
        driver.findElement(By.id("city")).sendKeys("Los Angeles");


        //Select California as a state
        WebElement state = driver.findElement(By.id("id_state"));
        Select statevalue = new Select(state);
        statevalue.selectByIndex(5);

        Thread.sleep(1500);

        driver.findElement(By.id("postcode")).sendKeys("12345");

        driver.findElement(By.id("phone_mobile")).sendKeys("760-213-3288");

        Thread.sleep(1500);

        //Scroll down in browser page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,120)");

        driver.findElement(By.id("alias")).sendKeys("15 alias street");
        driver.findElement(By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/div[1]/form[1]/div[4]/button[1]/span[1]")).click();

        Thread.sleep(1500);

        //Assert that user logged in successfully and user name appeared in user label
        String ActualTitle = driver.findElement(By.xpath("//span[contains(text(),'Mahmoud Diab')]")).getText();
        String ExpectedTitle = "Mahmoud Diab";
        assertEquals(ActualTitle, ExpectedTitle, "Registration Failed");



    }

}