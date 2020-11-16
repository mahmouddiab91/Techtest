import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.support.ui.ExpectedConditions;


public class CartCheckout {
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

        //Enter Username and Password then press Submit
        driver.findElement(By.id("email")).sendKeys("test_hbll@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("12345678");
        driver.findElement(By.id("SubmitLogin")).click();

        Thread.sleep(1500);

        //Assert that user logged in successfully and user name appeared in user label
        String ActualTitle = driver.findElement(By.xpath("//span[contains(text(),'ABC DEF')]")).getText();
        String ExpectedTitle = "ABC DEF";
        assertEquals(ActualTitle, ExpectedTitle, "Registration Failed");

        //Select "Women" category
        driver.findElement(By.xpath("//header/div[3]/div[1]/div[1]/div[6]/ul[1]/li[1]/a[1]")).click();


        //Scroll down till find the first "item" Element "Faded Short Sleeve T-shirts" then select
        WebElement item1 = driver.findElement(By.xpath("//img[@title='Faded Short Sleeve T-shirts']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", item1);
        item1.click();

        Thread.sleep(1500);

        //Press on "Add to Cart" button
        driver.switchTo().frame(0);
        driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]")).click();

        //Wait till "Proceed to checkout" button appeared
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/div[3]/div[1]/div[1]/div[4]/div[1]/div[2]/div[4]/a[1]/span[1]")));

        //Press on "process to checkout 1" button
        driver.findElement(By.xpath("//header/div[3]/div[1]/div[1]/div[4]/div[1]/div[2]/div[4]/a[1]/span[1]")).click();

        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        //Assert that user on "Shopping cart" page
        String CartTitle = driver.findElement(By.xpath("//body/div[@id='page']/div[2]/div[1]/div[1]/span[2]")).getText();
        String expTitle = "Your shopping cart";
        assertEquals(CartTitle, expTitle, "User is not at shopping Cart Page");

        Thread.sleep(1500);

        //Scroll down in the page
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0,175)");

        //Press on "Proceed to checkout" buttom
        driver.findElement(By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/p[2]/a[1]/span[1]")).click();

        Thread.sleep(1500);

        //Assert that user at "Address" step
        String ActualAdd = driver.findElement(By.xpath("//span[contains(text(),'Addresses')]")).getText();
        String ExpAdd = "Addresses";
        assertEquals(ActualAdd, ExpAdd, "User is not at Address Page");

        Thread.sleep(1500);

        //Scroll down at the page
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("window.scrollBy(0,175)");

        //Press on "Proceed to checkout" button
        driver.findElement(By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/form[1]/p[1]/button[1]/span[1]")).click();

        Thread.sleep(1500);

        //Assert that user at "Shipping" step
        String ActualShipping = driver.findElement(By.xpath("//body/div[@id='page']/div[2]/div[1]/div[1]/span[2]")).getText();
        String ExpShipping = "Shipping";
        assertEquals(ActualShipping, ExpShipping, "User is not at shipping Page");

        Thread.sleep(1500);

        //Scroll down at the page
        JavascriptExecutor js3 = (JavascriptExecutor) driver;
        js3.executeScript("window.scrollBy(0,175)");

        Thread.sleep(1500);

        //Accept Terms and Condition in Shipping page
        driver.findElement(By.xpath("//input[@id='cgv']")).click();

        Thread.sleep(1500);

        //Press on "Proceed to checkout" button
        driver.findElement(By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/div[1]/form[1]/p[1]/button[1]/span[1]")).click();

        Thread.sleep(1500);

        //Assert that user at "Shipping" step
        String ActualPay = driver.findElement(By.xpath("//span[contains(text(),'Your payment method')]")).getText();
        String ExpPay = "Your payment method";
        assertEquals(ActualPay, ExpPay, "User is not at payment Page");

        //Scroll down till find the "Pay By methods" elements
        WebElement BankBtn = driver.findElement(By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/p[1]/a[1]"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", BankBtn);

        Thread.sleep(1500);

        //Click on "Pay by bank wire"
        driver.findElement(By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/p[1]/a[1]")).click();

        //Assert that user at "Bank wire payment" page
        String actualBankWire = driver.findElement(By.xpath("//span[contains(text(),'Bank-wire payment.')]")).getText();
        String expBankWire = "Bank-wire payment.";
        assertEquals(actualBankWire, expBankWire, "User is not at bank wire payment Page");

        //Scroll down at the page
        JavascriptExecutor js5 = (JavascriptExecutor) driver;
        js5.executeScript("window.scrollBy(0,185)");

        Thread.sleep(1500);

        //Press on "I confirm my order" button
        driver.findElement(By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/form[1]/p[1]/button[1]/span[1]")).click();

        //Scroll down at the page
        JavascriptExecutor js6 = (JavascriptExecutor) driver;
        js6.executeScript("window.scrollBy(0,175)");

        //Assert that user at "Order Confirmation" page
        String actualConfirmOrd = driver.findElement(By.xpath("//span[contains(text(),'Order confirmation')]")).getText();
        String expConfirmOrd = "Order confirmation";
        assertEquals(actualConfirmOrd, expConfirmOrd, "User is not at order confirmation Page");

        Thread.sleep(1500);

    }

}