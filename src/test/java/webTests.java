import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.WebAppObject;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

public class webTests {

    WebDriver       driver;
    WebAppObject    wao;
    ChromeOptions   options;

    @Before
    public void setup() {
        if(System.getProperty("os.name").equals("Mac OS X")) {
            System.setProperty("webdriver.chrome.driver", "/users/matt/documents/chromedriver/chromedriver");
        }
        else
        {
            System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        }

        options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Arrays.asList("--test-type"));
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1366, 768));

        wao = new WebAppObject(driver);
    }

//    @Test
//    public void testCorrectLogin()
//    {
//        wao.visit();
//        wao.login("Affiliate1", "Password1");
//
//        assertEquals("Welcome Affiliate1", wao.getElementText("p.wText"));
//    }
//
//    @Test
//    public void testIncorrectLogin(){
//        wao.visit();
//        wao.login("Affiliate1", "Pass");
//
//        assertEquals("Wrong username or password", wao.getElementText("p.loginStatusText"));
//    }
//
//    @Test
//    public void testCorrectWithdraw(){
//        wao.visit();
//        wao.correctLogin();
//
//        wao.sleep(1);
//
//        int balanceBefore = Integer.parseInt(wao.getElementText("span.balanceText"));
//
//        wao.clickElement("withdrawBtn");
//        wao.sleep(1);
//        wao.clickElement("withdrawOkBtn");
//        wao.sleep(1);
//
//        int balanceAfter = Integer.parseInt(wao.getElementText("span.balanceText"));
//
//        assertEquals(balanceBefore, 10);
//        assertEquals(balanceAfter, 0);
//    }
//
//    @Test
//    public void testIncorrectWithdraw(){
//        wao.visit();
//        wao.correctLogin();
//
//        wao.sleep(1);
//
//        wao.withdraw();
//
//        wao.clickElement("withdrawBtn");
//        wao.sleep(1);
//
//        assertEquals("You do not have enough balance to withdraw.", wao.getElementText("p.errorText"));
//    }

    @Test
    public void testHistory()
    {
        wao.visit();
        wao.correctLogin();

        wao.sleep(1);

        wao.clickElement("historyBtn");

        List<WebElement> history = driver.findElements(By.cssSelector("md-list-item.historyRec"));


        System.out.println(wao.getTitle());
//        for(WebElement record : history){
//            //WebElement record = e.findElement(By.cssSelector("p.oldBal"));
//
//            String oldBalance = record.findElement(By.cssSelector("p.oldBal")).getAttribute("innerText");
//            String result =
//        }

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
