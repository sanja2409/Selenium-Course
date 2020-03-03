package selenium.training;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MyFirstHomework {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start () {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tester\\Documents\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstHomework() {
        driver.get("http://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("Politechnika Gdańska");

        //driver.findElement(By.name("btnG")).click();
        //wait.until(WebDriver::getTitle);
        //wait.until(titleIs("Politechnika Gdańska: Strona Główna"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
