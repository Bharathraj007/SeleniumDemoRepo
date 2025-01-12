package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.Base;

public class ThreeTest extends Base {
	public WebDriver driver;
	@Test
	public  void threetest() throws IOException, InterruptedException {
		System.out.println("Test Three");
		

	    driver=intializerDriver();
		driver.get("https://tutorialsninja.com/demo/");
	    Thread.sleep(2000);
	    driver.close();
		
	}

}
