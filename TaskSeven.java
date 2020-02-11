package selenium.training;

import org.junit.After;
import org.junit.Assert;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;


public class TaskSeven {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start () {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tester\\Documents\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void taskSeven() {
        driver.get("http://localhost/litecart/admin/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //driver.findElements(By.cssSelector("li.app-"));
        //driver.findElements(By.cssSelector("span.name"));
        driver.findElement(By.xpath("//li[contains(@id,'app')]//span[text()='Appearence']")).click();
        driver.findElement(By.xpath("//li[contains(@id,'app')]//span[text()='Template']")).click();
        driver.findElement(By.xpath("//li[contains(@id,'app')]//span[text()='Logotype']")).click();

        driver.findElement(By.xpath("//li[contains(@id,'app')]//span[text()='Catalog']")).click();
        driver.findElement(By.xpath("//li[contains(@id,'app')]//span[text()='Manufacturers']")).click();
        driver.findElement(By.xpath("//li[contains(@id,'app')]//span[text()='Suppliers']")).click();

        List<WebElement> checkHeader = driver.findElements(By.xpath("//td[contains(@id,'content')]"));
        WebElement headerSuppliers = driver.findElement(By.xpath("//h1[text()=' Suppliers']"));
        Assert.assertEquals(headerSuppliers," Suppliers");
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
