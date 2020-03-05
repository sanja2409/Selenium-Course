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

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class TaskFourteen {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start () {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tester\\Documents\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
    }

    @Test
    public void checkWindows() {
        driver.get("http://localhost/litecart/admin/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        //WebElement linkToCountries = driver.findElement(By.xpath("//li[contains(@id,'app')]//a//span[text()='Countries']"));
        //wait.until(elementToBeClickable(linkToCountries)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String mainWindow = driver.getWindowHandle();
        System.out.println(mainWindow);
        Set<String> existingWindows = driver.getWindowHandles();
        System.out.println(existingWindows);

        WebElement linkToAustralia = driver.findElement(By.xpath("//table[contains(@class,'dataTable')]//a[text()='Australia']"));
        wait.until(elementToBeClickable(linkToAustralia)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement firstLink = driver.findElement(By.xpath("//a[contains(@href,'ISO_3166-1_alpha-2')]"));
        wait.until(elementToBeClickable(firstLink)).click();
        switchWindows (mainWindow);

        WebElement secondLink = driver.findElement(By.xpath("//a[contains(@href,'ISO_3166-1_alpha-2')]"));
        wait.until(elementToBeClickable(secondLink)).click();
        switchWindows (mainWindow);

        WebElement taxIdLink = driver.findElement(By.xpath("//*[@id='content']/form/table[1]/tbody/tr[6]/td/a"));
        wait.until(elementToBeClickable(taxIdLink)).click();
        switchWindows (mainWindow);

        WebElement addressLink = driver.findElement(By.xpath("//a[contains(@href,'address-formats')]"));
        wait.until(elementToBeClickable(addressLink)).click();
        switchWindows (mainWindow);

        WebElement postcodeLink = driver.findElement(By.xpath("//*[@id='content']/form/table[1]/tbody/tr[8]/td/a"));
        wait.until(elementToBeClickable(postcodeLink)).click();
        switchWindows (mainWindow);

        WebElement currencyCodeLink = driver.findElement(By.xpath("//a[contains(@href,'List_of_countries_and_capitals_with_currency_and_language')]"));
        wait.until(elementToBeClickable(currencyCodeLink)).click();
        switchWindows (mainWindow);

        WebElement phoneCountryLink = driver.findElement(By.xpath("//a[contains(@href,'List_of_country_calling_codes')]"));
        wait.until(elementToBeClickable(phoneCountryLink)).click();
        switchWindows (mainWindow);

    }

    private void switchWindows(String mainWindow) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Set<String> newExistingWindows = driver.getWindowHandles();
        System.out.println(newExistingWindows);
        String NewWindow = new ArrayList<>(newExistingWindows).get(1);
        System.out.println(NewWindow);
        driver.switchTo().window(NewWindow);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.close();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.switchTo().window(mainWindow);
     }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
