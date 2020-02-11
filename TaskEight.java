package selenium.training;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.junit.Assert;
import java.util.concurrent.TimeUnit;


public class TaskEight {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start () {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tester\\Documents\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void taskEight() {
        driver.get("http://localhost/litecart/");
        List<WebElement> allProducts = driver.findElements(By.cssSelector("li.product"));
        int numberOfProducts= allProducts.size();
        
        List <WebElement> stickers = driver.findElements(By.cssSelector("div.sticker"));
        int numberOfStickers= stickers.size();

        Assert.assertEquals(numberOfProducts,numberOfStickers);

       //if (Assert.assertEquals(numberOfProducts,numberOfStickers)) {
       //     System.out.println("OK");
        //}
        //for ([i] in allProducts): {
        //        System.out.println(elm + "has more than one sticker");
        //}

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
