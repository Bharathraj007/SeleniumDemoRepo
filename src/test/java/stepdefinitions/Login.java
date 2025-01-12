package stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class Login extends Base {
	WebDriver driver;
	LandingPage landingPage;
	LoginPage loginPage;
	AccountPage accountpage;
	@Given("Open any Browser")
    public void open_any_browser() throws IOException {
		driver=intializerDriver();
        
    }

    @And("Navigate to Login page")
    public void navigate_to_login_page() {
    	driver.get(prop.getProperty("url"));
    	landingPage=new LandingPage(driver);
    	landingPage.myAccountDropdown().click();
    	landingPage.loginOptions().click();
        
    }

    @When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\" into the fields$")
    public void user_enters_credentials(String email, String password) throws InterruptedException {
    	
        loginPage=new LoginPage(driver);
		loginPage.emailAddressField().sendKeys(email);
    	loginPage.passwordField().sendKeys(password);
    	Thread.sleep(3000);
    	
        
    }

    @And("User clicks on Login button")
    public void user_clicks_on_login_button() {
    	loginPage.loginButton().click();
        
    }
    
    @Then("^Verify user is able to successfully login$")
    public void verify_user_is_able_to_successfully_login() {
    	
        accountpage= new AccountPage(driver);
    	Assert.assertTrue(accountpage.editAccountInformation().isDisplayed());
    	
    }
    @After
    public void closure() {
    	driver.close();
    }
    
	
	
	
	
	

}
