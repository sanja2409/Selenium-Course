package selenium.training;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class TaskThirteen {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start () {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tester\\Documents\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
    }

    @Test
    public void addProductToCart() {
        driver.get("http://localhost/litecart/en/");

        WebElement product = driver.findElement(By.xpath("//div[contains(@id,'box-latest-products')]//ul[contains(@class,'listing-wrapper')]//li[3]//div[starts-with(@class,'name')]"));
        wait.until(elementToBeClickable(product)).click();
        String addCartProduct = "add_cart_product";
        int item = 0;
        addToCart(addCartProduct, item);

        driver.findElement(By.xpath("//div[contains(@id,'box-latest-products')]//ul[contains(@class,'listing-wrapper')]//li[4]//div[starts-with(@class,'name')]")).click();
        int item1 = 1;
        addToCart(addCartProduct, item1);

        driver.findElement(By.xpath("//div[contains(@id,'box-latest-products')]//ul[contains(@class,'listing-wrapper')]//li[5]//div[starts-with(@class,'name')]")).click();
        int item2 = 2;
        addToCart(addCartProduct, item2);

        driver.findElement(By.cssSelector("div#cart")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        wait.until(visibilityOfElementLocated(By.cssSelector("a.inact"))).click();
        wait.until(visibilityOfElementLocated(By.name("remove_cart_item"))).click();
        wait.until(visibilityOfElementLocated(By.cssSelector("a.act"))).click();
        wait.until(visibilityOfElementLocated(By.name("remove_cart_item"))).click();
        wait.until(visibilityOfElementLocated(By.name("remove_cart_item"))).click();

    }

    private void addToCart(String addCartProduct, int item) {
        driver.findElement(By.name(addCartProduct)).click();
        WebElement cartItem = driver.findElement(By.cssSelector("span.quantity"));
        int itemsExpectedNumber = item + 1;
        String itemsExpectedNumberAsString = Integer.toString(itemsExpectedNumber);
        wait.until(attributeContains(cartItem, "textContent", itemsExpectedNumberAsString));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.className("general-0")).click();

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
