package resources;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener{
	
	WebDriver driver;
	String filePath = System.getProperty(" user.dir" + "\\Reports");
	ExtentTest test;
	ExtentReports extent = GenerateExtentReport.generateReport();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestFailure(ITestResult result) {
		System.out.println("***** Error " + result.getName() + " Test has failed *****");

		String methodName = result.getName().toString().trim();

		ITestContext context = result.getTestContext();

		WebDriver driver = (WebDriver) context.getAttribute("driver");
		takeScreenShot(methodName, driver);
	}

	public void takeScreenShot(String methodName, WebDriver driver) {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// The below method will save the screen shot in d drive with test method name
		try {
			FileUtils.copyFile(scrFile, new File(filePath + methodName + ".png"));

			System.out.println("***Placed screen shot in " + filePath + " ***");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	//	extent = new ExtentReports(new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date()) + "reports.html");

	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context, ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO, result.getMethod().getMethodName() + "test is started");

	}


}
