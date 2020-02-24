package selenium.training;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TaskTen {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start () {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tester\\Documents\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkProduct() {
        driver.get("http://localhost/litecart/");

        // getting the text of the product on the main page
        WebElement productMainPage = driver.findElement(By.cssSelector("#box-campaigns div.name"));
        String productNameMainPage = productMainPage.getAttribute("textContent");
        // getting price elements on the main page
        WebElement grayPriceElementMainPage = driver.findElement(By.cssSelector("#box-campaigns div.price-wrapper s.regular-price"));
        WebElement redPriceElementMainPage = driver.findElement(By.cssSelector("#box-campaigns div.price-wrapper strong.campaign-price"));

        // getting the value of the price on the main page
        String redPriceMainPage = redPriceElementMainPage.getAttribute("textContent");
        String grayPriceMainPage = grayPriceElementMainPage.getAttribute("textContent");

        // getting the colors of the price on the main page
        String grayPriceColor = grayPriceElementMainPage.getCssValue("color");
        String redPriceColor = redPriceElementMainPage.getCssValue("color");

        // getting the styles of the price on the main page
        String grayPriceStyle = grayPriceElementMainPage.getCssValue("text-decoration");
        String redPriceStyle = redPriceElementMainPage.getCssValue("font-weight");

        // opening product page
        driver.findElement(By.cssSelector("#box-campaigns a.link")).click();
        // getting the text of the product on the product page
        String productNameProductPage = driver.findElement(By.cssSelector("h1.title")).getAttribute("textContent");
        // getting price elements on the product page
        WebElement grayPriceElementProductPage = driver.findElement(By.cssSelector("s.regular-price"));
        WebElement redPriceElementProductPage = driver.findElement(By.cssSelector("strong.campaign-price"));

        // getting the value of the price on the product page
        String redPriceProductPage = redPriceElementProductPage.getAttribute("textContent");
        String grayPriceProductPage = grayPriceElementProductPage.getAttribute("textContent");

        // getting the colors of the price on the product page
        // String grayPriceColorProductPage = grayPriceElementProductPage.getCssValue("color");
        String grayPriceColorProductPage = grayPriceElementProductPage.getCssValue("color");
        String redPriceColorProductPage = redPriceElementProductPage.getCssValue("color");

        // getting the styles of the price on the product page
        String grayPriceStyleProductPage = grayPriceElementProductPage.getCssValue("text-decoration");
        String redPriceStyleProductPage = redPriceElementProductPage.getCssValue("font-weight");

        //System.out.println(grayPriceColorProductPage);
        // int[] greyPrice_RGB_MainPage = convertRGBAtoArray(grayPriceColor);

        //System.out.println(arrayOfString);
        //int [] greyPrice_RGB_MainPage = rgbNumbers;
        //System.out.println(greyPrice_RGB_MainPage);
        //System.out.println(greyPrice_RGB_MainPage.length);

        //System.out.println(greyPrice_RGB_MainPage[0]);
        //System.out.println(greyPrice_RGB_MainPage[1]);
        //System.out.println(greyPrice_RGB_MainPage[2]);

        // compare name of products
        Assert.assertEquals(productNameMainPage, productNameProductPage);
        // compare prices of products
        Assert.assertEquals(redPriceMainPage, redPriceProductPage);
        Assert.assertEquals(grayPriceMainPage, grayPriceProductPage);
        // comparing colors of prices
        System.out.println(redPriceColorProductPage);
        System.out.println(grayPriceColor);
        System.out.println(grayPriceColorProductPage);

        Assert.assertEquals(redPriceColor, redPriceColorProductPage);
        Assert.assertEquals(grayPriceColor, grayPriceColorProductPage);

        // checking if price is red
        int[] redPriceMainPAgeRGB = changeRGBAtoIntList(redPriceColor);
        int[] redPriceProductPageRGB = changeRGBAtoIntList(redPriceColorProductPage);
        Assert.assertEquals(0,redPriceMainPAgeRGB[1], redPriceMainPAgeRGB[2]);
        Assert.assertEquals(0,redPriceProductPageRGB[1], redPriceProductPageRGB[2]);

        // checking if price is gray
        int[] greyPriceMainPageRGB = changeRGBAtoIntList(grayPriceColor);
        int[] greyPriceProductPageRGB = changeRGBAtoIntList(grayPriceColorProductPage);
        Assert.assertEquals(greyPriceMainPageRGB[0],greyPriceMainPageRGB[1], greyPriceMainPageRGB[2]);
        Assert.assertEquals(greyPriceProductPageRGB[0],greyPriceProductPageRGB[1], greyPriceProductPageRGB[2]);


        // comparing styles of prices
        Assert.assertEquals(redPriceStyle, redPriceStyleProductPage);
        Assert.assertEquals(grayPriceStyle, grayPriceStyleProductPage);

        }

    public static int[] changeRGBAtoIntList (String colorRGBA)
    {
        int colorStringLength = colorRGBA.length();
        String colorText = colorRGBA.substring(5, (colorStringLength - 1));
        //System.out.println(grayText);
        String[] arrayOfString = colorText.split(",");
        int[] RGBAIntList = new int[3];
        for (int i = 0; i < 3; i++) {
            RGBAIntList[i] = Integer.parseInt(arrayOfString[i].trim());
        }
        return RGBAIntList;

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
