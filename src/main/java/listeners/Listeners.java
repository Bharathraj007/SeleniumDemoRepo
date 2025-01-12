package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utilities.ExtentReporter;

public class Listeners extends Base implements ITestListener {
	ExtentReports extentReport = ExtentReporter .getExtentReport();
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> extendTestThread=new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		
		String testName=result.getName();
	    extentTest = extentReport.createTest(testName);
	    extendTestThread.set(extentTest);
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String testName=result.getName();
		
		//extentTest.log(Status.PASS,testName+" got passed" );
		extendTestThread.get().log(Status.PASS, testName+ " got passed");		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		
		String testName=result.getName();
		//extentTest.fail(result.getThrowable());
		extendTestThread.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		try {
			String screenshotFilepath = takescreenshoot(testName, driver);
			extendTestThread.get().addScreenCaptureFromPath(screenshotFilepath, testName);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		
	}

}
