package cucumberTests;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.Before;
import pageobjects.WebAppObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinitions {

    WebAppObject webAppObject;

    @Before
    public void buildUp() throws Throwable
    {
        webAppObject = new WebAppObject();
    }

    @Given("^I am an affiliate trying to log in$")
    public void i_am_an_affiliate_trying_to_log_in() throws Throwable {
        webAppObject.visit();
    }

    @When("^I login using valid credentials$")
    public void i_login_using_valid_credentials() throws Throwable {

        webAppObject.login("Affiliate1","Password1");
    }

    @Then("^I should be taken to my account admin page$")
    public void i_should_be_taken_to_my_account_admin_page() throws Throwable {

        webAppObject.sleep(1);
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
        webAppObject.visit();
        webAppObject.login("Affiliate1","Password1");
    }

    @When("^I visit my account admin page$")
    public void i_visit_my_account_admin_page() throws Throwable {
        //leave empty?
    }

    @Then("^I should see my balance$")
    public void i_should_see_my_balance() throws Throwable {
        assertTrue(webAppObject.elementExistsCSS("span.balanceText"));
    }

    @Then("^I should see a button allowing me to withdraw my balance$")
    public void i_should_see_a_button_allowing_me_to_withdraw_my_balance() throws Throwable {
        assertTrue(webAppObject.elementExistsID("withdrawBtn"));
    }

    @Given("^my balance is (\\d+)\\.(\\d+)$")
    public void my_balance_is(int arg1, int arg2) throws Throwable {
        StringBuilder sb = new StringBuilder();

        sb.append(arg1);
        sb.append(".");
        sb.append(arg2);

        webAppObject.setBalance(sb.toString());
    }

    @When("^I try to withdraw my balance$")
    public void i_try_to_withdraw_my_balance() throws Throwable {
        webAppObject.sleep(1);
        webAppObject.clickElement("withdrawBtn");
    }

    @Then("^I should see a message indicating error$")
    public void i_should_see_a_message_indicating_error() throws Throwable {

        webAppObject.sleep(1);
        assertEquals("Error",webAppObject.getElementText("h2.dialogTitle"));
    }

    @Then("^my new balance will be (\\d+)\\.(\\d+)$")
    public void my_new_balance_will_be(int arg1, int arg2) throws Throwable {
        StringBuilder sBuild = new StringBuilder();

        sBuild.append(arg1);
        sBuild.append(".");
        sBuild.append(arg2);

        assertEquals(sBuild.toString(),webAppObject.getElementText("span.balanceText"));
    }

    @Then("^I should see a message indicating success$")
    public void i_should_see_a_message_indicating_success() throws Throwable {
        webAppObject.sleep(1);
        assertEquals("Success",webAppObject.getElementText("h2.dialogTitle"));
    }

    @After
    public void tearDown(){
        webAppObject.close();
    }
}
