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
import java.util.List;

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
        String addCartProduct = "add_cart_product";

        int chosenProductAmount = 3;

        List <WebElement> products = driver.findElements(By.xpath("//div[contains(@id,'box-latest-products')]//ul[contains(@class,'listing-wrapper')]//li//div[starts-with(@class,'name')]"));

        for (int i=0; i < chosenProductAmount; i++) {
            products =  driver.findElements(By.xpath("//div[contains(@id,'box-latest-products')]//ul[contains(@class,'listing-wrapper')]//li//div[starts-with(@class,'name')]"));
            wait.until(elementToBeClickable(products.get(i+1))).click();
            addToCart(addCartProduct, i);
        }
        
        driver.findElement(By.cssSelector("div#cart a.content")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        List <WebElement> productsInBasket =  driver.findElements(By.cssSelector("a.inact"));
        for (int i=0; i<chosenProductAmount; i++)
        {
            wait.until(numberOfElementsToBe(By.cssSelector("td.item"), chosenProductAmount--));
            productsInBasket =  driver.findElements(By.cssSelector("li.shortcut a"));
            wait.until(elementToBeClickable(productsInBasket.get(0))).click();
            wait.until(visibilityOfElementLocated(By.name("remove_cart_item"))).click();
            wait.until(stalenessOf(productsInBasket.get(0)));
        }
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
