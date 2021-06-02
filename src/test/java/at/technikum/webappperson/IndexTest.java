package at.technikum.webappperson;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Tag("extended")
public class IndexTest {
    @LocalServerPort
    private long port;

    private WebDriver driver;
    private Wait<WebDriver> wait;

    @BeforeAll
    public static void setUpBeforeClass(){
        WebDriverManager.firefoxdriver().setup();
        //System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
    }

    @BeforeEach
    public void setUp(){
        driver = new FirefoxDriver(
                new FirefoxOptions().setHeadless(true)
        );
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,3);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }


    @Test
    public void testIndex(){
        driver.get(getUrl(""));

        var button = driver.findElement(By.tagName("button"));
        assertEquals("alle Personen anzeigen",button.getText());

        var lis = driver.findElements(By.tagName("li"));
        assertEquals(1,lis.size());

        button.click();

        wait.until(
                ExpectedConditions.urlToBe(getUrl("?all=true"))
        );
    }

    @Test
    public void testIndexWithAll(){
        driver.get(getUrl("?all=true"));

        var button = driver.findElement(By.tagName("button"));
        assertEquals("nur aktivierte Personen anzeigen",button.getText());

        var lis = driver.findElements(By.tagName("li"));
        assertEquals(2,lis.size());

        button.submit();

        wait.until(
                ExpectedConditions.urlToBe(getUrl("?"))
        );
    }

    private String getUrl(String path){
        return "http://localhost:"+port+"/"+path;
    }

}
