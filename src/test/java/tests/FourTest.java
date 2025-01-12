package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import resources.Base;

public class FourTest extends Base{
	public WebDriver driver;
	
	@Test
	public void fourtest() throws IOException, InterruptedException {
		System.out.println("Test Four");
		

		driver=intializerDriver();
		driver.get("https://tutorialsninja.com/demo/");
	       Thread.sleep(2000);
	    
	    
		
	}
	@AfterMethod
	public void Clouser() {
		driver.close();
	}
		

}
