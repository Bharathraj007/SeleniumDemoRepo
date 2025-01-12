package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Base {
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver intializerDriver() throws IOException {

	    prop=new Properties();
		String propPath=System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties";
		FileInputStream fis= new FileInputStream(propPath);
		
		prop.load(fis);
		
		String browserName= prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
		}else if(browserName.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			 
		}else if(browserName.equalsIgnoreCase("IE")){
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
	                
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
	}

	public String takescreenshoot(String testName,WebDriver driver) throws IOException {
		
		File Sourcefile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
		FileUtils.copyFile(Sourcefile,new File(destinationFilePath));
		
		return destinationFilePath;
		
		
		
	}
}

