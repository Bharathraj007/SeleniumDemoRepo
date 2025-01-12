package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;
public class LoginTest extends Base{
	
	WebDriver driver;
	Logger log;
	
	@Test(dataProvider="getLoginData")
	public void login(String email,String password,String expectedstatus) throws IOException, InterruptedException {
		
		
		
		LandingPage landingPage=new LandingPage(driver);
		landingPage.myAccountDropdown().click();
		log.debug("Clicked on the My Account");
		landingPage.loginOptions().click();
		log.debug("Clicked on the Login option");
		
		Thread.sleep(3000);
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.emailAddressField().sendKeys(email);
		log.debug("Entered the email address");
		loginPage.passwordField().sendKeys(password);
		log.debug("Entered the email password");
		loginPage.loginButton().click();
		log.debug("Clicked on login Button");
	
		
		AccountPage accountpage= new AccountPage(driver);
		
		String actualResult = null;
		try {
			if(accountpage.editAccountInformation().isDisplayed()) {
			log.debug("User is logged into the application");
			actualResult="Successfully";
			}
			
		}catch(Exception e) {
			
			
			actualResult= "Failure";
			log.debug("User Failed to login into application");
			
			
		}
		Assert.assertEquals(actualResult, expectedstatus);
		log.debug("Test got passed");
		
		
		
		

	}
	@BeforeMethod
	public void openApplication() throws IOException {
		
		log=LogManager.getLogger(LoginTest.class.getName());
		
		driver=intializerDriver();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to Application");
		
	}
	@AfterMethod
	public void closure() {
		
		driver.close();
		log.debug("Browser got closed");
	}
	
	@DataProvider
    public Object[][] getLoginData() {
    	
    	Object [][] data = {{"arun.selenium@gamil.com","Second@123","Successfully"}};
    	return data;
    	
    }
}
 