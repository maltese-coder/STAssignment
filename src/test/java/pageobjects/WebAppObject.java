package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

/**
 * Created by matt on 13/12/2016.
 */
public class WebAppObject {

    WebDriver driver;
    ChromeOptions options;

    public WebAppObject()
    {
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
    }

    public void visit(){
        driver.get("localhost:3000");
    }

    public void login(String username, String password){
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginBtn")).click();
    }

    public void correctLogin(){
        driver.findElement(By.id("username")).sendKeys("Affiliate1");
        driver.findElement(By.id("password")).sendKeys("Password1");
        driver.findElement(By.id("loginBtn")).click();
    }

    public boolean elementExistsCSS(String css){
        if(driver.findElements(By.cssSelector(css)).size() > 0){
            return true;
        }

        return false;
    }

    public boolean elementExistsID(String id){
        if(driver.findElements(By.id(id)).size() > 0){
            return true;
        }

        return false;
    }

    public String getElementText(String name)
    {
        return driver.findElement(By.cssSelector(name)).getAttribute("innerText");
    }

    public String getTitle(){
        return this.getElementText("h4.md-title");
    }

    public String getElementText(WebElement e, String name){
        return e.findElement(By.cssSelector(name)).getAttribute("innerText");
    }

    public void clickElement(String name){
        driver.findElement(By.id(name)).click();
    }

    public void withdraw() {
        this.clickElement("withdrawBtn");
        sleep(1);
        this.clickElement("withdrawOkBtn");
        sleep(1);
    }

    public void setBalance(String balance){
        this.clickElement("setBalanceBtn");

        driver.findElement(By.id("setBalanceText")).sendKeys(balance);

        this.clickElement("setBalanceConfirmBtn");
    }

    public void close(){
        driver.close();
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
