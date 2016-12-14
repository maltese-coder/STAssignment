package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by matt on 13/12/2016.
 */
public class WebAppObject {

    WebDriver driver;

    public WebAppObject(WebDriver _driver)
    {
        driver = _driver;
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

    public String getElementText(String name)
    {
        return driver.findElement(By.cssSelector(name)).getAttribute("innerText");
    }

    public String getTitle(){
        return this.getElementText("h4.md-title");
    }

//    public String getElementTextObj(WebElement e){
//        return e.getAttribute("")
//    }

    public void clickElement(String name){
        driver.findElement(By.id(name)).click();
    }

    public void withdraw() {
        this.clickElement("withdrawBtn");
        sleep(1);
        this.clickElement("withdrawOkBtn");
        sleep(1);
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
