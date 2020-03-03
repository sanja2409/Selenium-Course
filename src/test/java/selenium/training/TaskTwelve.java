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
import java.io.File;

import static org.junit.Assert.*;


public class TaskTwelve {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start () {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tester\\Documents\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void addProduct()
    {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();


         driver.get("http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product");

        // General Tab
        // Status
        driver.findElement(By.name("status")).click();
        // Name
        String addProduct = "lipstick-red";
        driver.findElement(By.name("name[en]")).sendKeys(addProduct);

        // Code
        driver.findElement(By.name("code")).sendKeys("73391");

        // Categories
        List<WebElement> categories = driver.findElements(By.name("categories[]"));
        categories.get(1).click();

        // Default Category select list
        driver.findElement(By.cssSelector("div#tab-general select[name=default_category_id]")).click();
        driver.findElement(By.cssSelector("div#tab-general select[name=default_category_id] :nth-child(2)")).click();

        // Product groups
        List<WebElement> gender = driver.findElements(By.cssSelector("div#tab-general input[name=\"product_groups[]\"]"));
        gender.get(0).click();

        // quantity
        WebElement quantity =  driver.findElement(By.name("quantity"));
        quantity.clear();
        String qty = "5";
        quantity.sendKeys(qty);

        // upload of the file
        File directory = new File("./src/test/resources/red-lipstick.jpg");
        WebElement uploadImage = driver.findElement(By.name("new_images[]"));
        uploadImage.sendKeys(directory.getAbsolutePath());

        // dates
        WebElement dateFrom = driver.findElement(By.name("date_valid_from"));
        dateFrom.sendKeys("0020200101");

        WebElement dateTo =  driver.findElement(By.name("date_valid_to"));
        dateTo.sendKeys("0020201231");

        // Information Tab
        WebElement informationTab =  driver.findElement(By.xpath("//*[@class='index']/li[2]/a"));
        informationTab.click();

        // Manufacturer
        driver.findElement(By.name("manufacturer_id")).click();
        driver.findElement(By.cssSelector("select[name=manufacturer_id] :nth-child(2)")).click();

        // Keywords
        driver.findElement(By.name("keywords")).sendKeys("cosmetics");

        // Short description
        driver.findElement(By.name("short_description[en]")).sendKeys("for lips");

        // Description
        String description = "Lovely red shade, suitable for every woman, for every occasion";
        driver.findElement(By.xpath("//*[@class='trumbowyg-editor']")).sendKeys(description);

        // Head Title
        driver.findElement(By.name("head_title[en]")).sendKeys("Red lipstick");

        // Prices Tab
        WebElement pricesTab =  driver.findElement(By.xpath("//*[@class='index']/li[4]/a"));
        pricesTab.click();

        //Purchase price
        WebElement purchasePrice = driver.findElement(By.name("purchase_price"));
        purchasePrice.clear();
        purchasePrice.sendKeys("10");

        driver.findElement(By.name("purchase_price_currency_code")).click();
        WebElement option = driver.findElement(By.cssSelector("select[name=purchase_price_currency_code] :nth-child(2)"));
        option.click();

        // Price Incl. Tax
        WebElement priceUSD = driver.findElement(By.name("gross_prices[USD]"));
        //    WebElement priceUSD = driver.findElement(By.cssSelector("input[name=\"gross_prices[USD]\"]"));
        priceUSD.clear();
        priceUSD.sendKeys("11,11");

        WebElement priceEUR = driver.findElement(By.name("gross_prices[EUR]"));
        priceEUR.clear();
        priceEUR.sendKeys("12,5");

        //Saving the product
        WebElement buttonSave = driver.findElement(By.name("save"));
        buttonSave.click();

        //String addProduct = "lipstick-red";
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        WebElement productName = driver.findElement(By.xpath("//table[contains(@class,'dataTable')]//tr[4]//td[3]//a"));
        //System.out.println(productNameFile.getText());
        Assert.assertEquals(addProduct, productName.getText());
        System.out.println(productName.getText());
        System.out.println(addProduct);


        if (addProduct.equalsIgnoreCase(productName.getText()))
            System.out.println("The product " + addProduct + " appeared in Catalog page");
        else
        System.out.println("The product IS NOT in Catalog page");

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
