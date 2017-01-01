package cucumberTests;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.WebAppObject;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    WebDriver driver;
    WebAppObject webAppObject;

    @Given("^I am an affiliate trying to log in$")
    public void i_am_an_affiliate_trying_to_log_in() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        driver = new ChromeDriver();

        webAppObject = new WebAppObject(driver);
        webAppObject.visit();
    }

    @When("^I login using valid credentials$")
    public void i_login_using_valid_credentials() throws Throwable {

        webAppObject.login("Affiliate1","Password1");
    }

    @Then("^I should be taken to my account admin page$")
    public void i_should_be_taken_to_my_account_admin_page() throws Throwable {

        assertEquals("Main Menu", webAppObject.getTitle());

    }

    @When("^I login using invalid credentials$")
    public void i_login_using_invalid_credentials() throws Throwable {

        webAppObject.login("WrongUserName","WrongPassword");
    }

    @Then("^I should see an error message$")
    public void i_should_see_an_error_message() throws Throwable {
        assertEquals("Wrong username or password", webAppObject.getElementText("p.loginStatusText"));
    }

    @Then("^I should remain on the login page$")
    public void i_should_remain_on_the_login_page() throws Throwable {
        assertEquals("Login", webAppObject.getTitle());

    }

    @Given("^I am a logged in affiliate$")
    public void i_am_a_logged_in_affiliate() throws Throwable {
        driver = new ChromeDriver();

        webAppObject = new WebAppObject(driver);
        webAppObject.visit();
        webAppObject.login("Affiliate1","Password1");
    }

    @When("^I visit my account admin page$")
    public void i_visit_my_account_admin_page() throws Throwable {
        //leave empty?
    }

    @Then("^I should see my balance$")
    public void i_should_see_my_balance() throws Throwable {
        assertEquals(1,driver.findElements(By.cssSelector("span.balanceText")).size());
    }

    @Then("^I should see a button allowing me to withdraw my balance$")
    public void i_should_see_a_button_allowing_me_to_withdraw_my_balance() throws Throwable {
        assertEquals(1,driver.findElements(By.id("withdrawBtn")).size());

    }

    @Given("^my balance is (\\d+)\\.(\\d+)$")
    public void my_balance_is(int arg1, int arg2) throws Throwable {


    }

    @When("^I try to withdraw my balance$")
    public void i_try_to_withdraw_my_balance() throws Throwable {

        webAppObject.clickElement("homeBtn");
        webAppObject.clickElement("withdrawBtn");
    }

    @Then("^I should see a message indicating error$")
    public void i_should_see_a_message_indicating_error() throws Throwable {

        assertEquals("Error",webAppObject.getElementText("h2.dialogTitle"));
    }

    @Then("^my new balance will be (\\d+)\\.(\\d+)$")
    public void my_new_balance_will_be(int arg1, int arg2) throws Throwable {
        StringBuilder sBuild = new StringBuilder();

        sBuild.append(arg1);
        sBuild.append(".");
        sBuild.append(arg2);

        assertEquals(sBuild.toString(),driver.findElement(By.cssSelector("span.balanceText")));
    }

    @Then("^I should see a message indicating success$")
    public void i_should_see_a_message_indicating_success() throws Throwable {
        assertEquals("Success",webAppObject.getElementText("h2.dialogTitle"));
    }

    @After
    public void cleanUp(){
        driver.close();
    }
}
