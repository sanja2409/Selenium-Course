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

import java.util.Date;
import java.util.concurrent.TimeUnit;


public class TaskEleven {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start () {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tester\\Documents\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void registerUser() {

        //driver.get("http://localhost/litecart/");
        //driver.findElement(By.xpath("//a[text()='New customers click here']")).click();

        //long now = System.currentTimeMillis();

        Date date = new Date(13, 1, 12);
        long diff = date.getTime();


        driver.get("http://localhost/litecart/en/create_account");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement tax_id = driver.findElement(By.name("tax_id"));
        tax_id.sendKeys("");

        WebElement company = driver.findElement(By.name("company"));
        company.sendKeys("");

        WebElement firstName = driver.findElement(By.name("firstname"));
        String name = String.format("Jano%s",diff);
        firstName.sendKeys(name);

        WebElement lastName = driver.findElement(By.name("lastname"));
        lastName.sendKeys("Kowalski");

        WebElement address1 = driver.findElement(By.name("address1"));
        address1.sendKeys("Wierzbowa 25");

        WebElement postcode = driver.findElement(By.name("postcode"));
        postcode.sendKeys("81-521");

        WebElement city = driver.findElement(By.name("city"));
        city.sendKeys("Gdynia");

        WebElement emailField = driver.findElement(By.name("email"));
        String email = String.format("jano%s@gmail.com",diff);
        emailField.sendKeys(email);

        WebElement phone = driver.findElement(By.name("phone"));
        phone.sendKeys("500500500");

        WebElement passwordField = driver.findElement(By.name("password"));
        String password = "qwerty123";
        passwordField.sendKeys(password);

        WebElement confirmedPasswordField = driver.findElement(By.name("confirmed_password"));
        confirmedPasswordField.sendKeys("qwerty123");

        WebElement createButton = driver.findElement(By.name("create_account"));
        createButton.click();

        WebElement logoutButton = driver.findElement(By.xpath("//div[contains(@class,'content')]//ul[contains(@class,'list-vertical')]//a[text()='Logout']"));
        logoutButton.click();

        driver.get("http://localhost/litecart/en/");

        WebElement loginMail = driver.findElement(By.name("email"));
        loginMail.sendKeys(email);

        WebElement loginPassword = driver.findElement(By.name("password"));
        loginPassword.sendKeys(password);

        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();
        //WebElement logoutButton2 = driver.findElement(By.xpath("//div[contains(@class,'content')]//ul[contains(@class,'list-vertical')]//a[text()='Logout']"));
        //logoutButton2.click();
        logoutButton.click();

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
