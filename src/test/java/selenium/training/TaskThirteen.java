package selenium.training;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


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

        // adding product to the cart
        driver.findElement(By.name(addCartProduct)).click();

        // checking if basket is updated
        WebElement cartItem = driver.findElement(By.cssSelector("span.quantity"));
        int itemsExpectedNumber = 1;
        String itemsExpectedNumberAsString = Integer.toString(itemsExpectedNumber);
        wait.until(attributeContains(cartItem,"textContent" , itemsExpectedNumberAsString));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.className("general-0")).click();
        driver.findElement(By.xpath("//div[contains(@id,'box-latest-products')]//ul[contains(@class,'listing-wrapper')]//li[4]//div[starts-with(@class,'name')]")).click();
        driver.findElement(By.name(addCartProduct)).click();
        WebElement cartItem2 = driver.findElement(By.cssSelector("span.quantity"));
        int itemsExpectedNumber2 = 2;
        String itemsExpectedNumberAsString2 = Integer.toString(itemsExpectedNumber2);
        wait.until(attributeContains(cartItem2,"textContent" , itemsExpectedNumberAsString2));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.className("general-0")).click();
        driver.findElement(By.xpath("//div[contains(@id,'box-latest-products')]//ul[contains(@class,'listing-wrapper')]//li[5]//div[starts-with(@class,'name')]")).click();
        driver.findElement(By.name(addCartProduct)).click();
        WebElement cartItem3 = driver.findElement(By.cssSelector("span.quantity"));
        int itemsExpectedNumber3 = 3;
        String itemsExpectedNumberAsString3 = Integer.toString(itemsExpectedNumber3);
        wait.until(attributeContains(cartItem3,"textContent" , itemsExpectedNumberAsString3));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.className("general-0")).click();
        driver.findElement(By.cssSelector("div#cart")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // checking the value of the table
        int itemAmount = 3 ;

        for (int i=0; i<itemAmount; i++)
        {
            wait.until(numberOfElementsToBe(By.cssSelector("td.item"), itemAmount--));
            wait.until(visibilityOfElementLocated(By.name("remove_cart_item"))).click();
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
