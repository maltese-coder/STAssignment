import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by matt on 12/12/2016.
 */
public class webTests {

    WebDriver driver;

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/chromedriver.exe");
        driver = new ChromeDriver();
        //gpo = new GooglePageObject(driver);
    }

    @Test
    public void openPage()
    {
        driver.get("localhost:3000");
    }

    @After
    public void tearDown() {
      //  driver.quit();
    }


}