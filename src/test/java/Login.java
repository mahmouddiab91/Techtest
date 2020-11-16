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

public class Login {
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

        //Scroll down in browser page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,80)");

        //Enter Username and Password
        driver.findElement(By.id("email")).sendKeys("test_hbll@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("12345678");

        driver.findElement(By.id("SubmitLogin")).click();

        Thread.sleep(1500);

        //Assert that user logged in successfully and user name appeared in user label
        String ActualTitle = driver.findElement(By.xpath("//span[contains(text(),'ABC DEF')]")).getText();
        String ExpectedTitle = "ABC DEF";
        assertEquals(ActualTitle, ExpectedTitle, "Login Failed");

    }

}