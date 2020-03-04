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
import java.util.ArrayList;
import java.util.Collections;


public class TaskNine {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start () {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tester\\Documents\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkCountries() {
        driver.get("http://localhost/litecart/admin/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        //driver.findElement(By.xpath("//li[contains(@id,'app')]//span[text()='Countries']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //List<WebElement> countries  = driver.findElements(By.cssSelector("tr.row :nth-child(5) a"));
        List<WebElement> countries  = driver.findElements(By.xpath("//tr[contains(@class,'row')]//td[5]"));
        ArrayList <String> countriesNames = new ArrayList<>();

        for (WebElement webElement : countries) {
            String name = webElement.getAttribute("textContent");
            countriesNames.add(name);
        }

        System.out.println(countriesNames);

        ArrayList<String> countriesSorted = new ArrayList<>();

        for(String s:countriesNames) {
            countriesSorted.add(s);
        }

        Collections.sort(countriesSorted);

        System.out.println(countriesSorted);

        Assert.assertEquals(countriesSorted, countriesNames);

    }



    @Test
    public void checkZones() {
        driver.get("http://localhost/litecart/admin/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        //driver.findElement(By.xpath("//li[contains(@id,'app')]//span[text()='Countries']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        List<WebElement> countries  = driver.findElements(By.xpath("//tr[contains(@class,'row')]//td[5]//a"));

        List<WebElement> numberOfZones = driver.findElements(By.xpath("//tr[contains(@class,'row')]//td[6]"));


        ArrayList<String> linksToCountryPage = new ArrayList<>();
        ArrayList<String> zoneNames = new ArrayList<>();

        /* getting the list of links for countries, which have more than 0 zones*/

         for (int i = 0; i < numberOfZones.size(); i++)    {
            int zoneNumber = Integer.parseInt(numberOfZones.get(i).getAttribute("textContent"));
            if ( zoneNumber > 0)
                System.out.println(zoneNumber);
                linksToCountryPage.add(countries.get(i).getAttribute("href"));
        }

        System.out.println(linksToCountryPage);

        // opening the pages of the countries which have more than 0 zones

        for (String link : linksToCountryPage) {
            driver.get(link);
            // getting the zone Names from Country page
            List <WebElement> zonesNames = driver.findElements(By.cssSelector("table.dataTable tr :nth-child(3) input[type=hidden]"));

            for (WebElement zonesName : zonesNames) {
                String zoneName = zonesName.getAttribute("value");
                zoneNames.add(zoneName);
            }

            System.out.println(zoneNames);

            checkIfZonesAreSorted(zoneNames);

            if (countries.size() > 0) {
                countries.clear();
            }
        }
    }



    @Test
    public void checkGeoZones()
    {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        List<WebElement> countriesElements = driver.findElements(By.xpath("//tr[contains(@class,'row')]//td[3]//a"));
        ArrayList<String> linksToCountries = new ArrayList<>();

        for (WebElement countriesElement : countriesElements) {
            String link = countriesElement.getAttribute("href");
            linksToCountries.add(link);
        }

        ArrayList<String> geoZonesNames = new ArrayList<>();

        for (String link : linksToCountries) {
            driver.get(link);
            List<WebElement> zonesElements = driver.findElements(By.cssSelector("table#table-zones tr :nth-child(3) select option[selected]"));


            for (WebElement zonesElement : zonesElements) {
                String zoneName = zonesElement.getAttribute("textContent");
                geoZonesNames.add(zoneName);
            }

            System.out.println(geoZonesNames);

            checkIfZonesAreSorted(geoZonesNames);

            if (zonesElements.size() != 0 || geoZonesNames.size() != 0) {
                zonesElements.clear();
                geoZonesNames.clear();
            }
        }

    }


    private static void checkIfZonesAreSorted(ArrayList<String> inputList) {
        ArrayList<String> sortedList = new ArrayList<>();

        for(String name:inputList) sortedList.add(name);

        Collections.sort(sortedList);
        Assert.assertEquals(sortedList, inputList);
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
