package Selenium_training;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class myFirstHomework {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public  void start () {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstHomework() {
        driver.get("http://googgle.com/");
        driver.findElement(By.name("q").sendKeys("Politechnika Gdańska"));
        driver.findElement(By.name("btnG")).click();
        wait.until(WebDriver::getTitle);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;

    }
}
