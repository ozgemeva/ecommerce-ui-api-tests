package hooks;

import Utils.DriverFactory;

import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	private static ExtentReports extent;
	private static ExtentTest test;
	private WebDriver driver;

	@Before
	public void setUp(Scenario scenario) {
		DriverFactory.startDriver("chrome");
		driver = DriverFactory.getDriver();
		System.out.println("The browser is STARTED");

		// Create test report
		if (extent == null) {
			String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
			reporter.config().setReportName("Ecommerce UI & API Tests");
			reporter.config().setDocumentTitle("Automation Report");

			extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester", "Ozge Meva Yılmaz");
			extent.setSystemInfo("Environment", "QA");
		}
		// to start test for each test scenario
		test = extent.createTest(scenario.getName());
		test.info("Scenario started: " + scenario.getName());
	}

	@After
	public void setDown(Scenario scenario) {
		// to write result report
		if (scenario.isFailed()) {
			test.fail("Scenario FAILED");

			try {
				// to add Screenshot forUI test
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				String base64Screenshot = Base64.getEncoder().encodeToString(screenshot);
				test.addScreenCaptureFromBase64String(base64Screenshot, scenario.getName());
			} catch (Exception e) {
				test.warning("Screenshot capture failed: " + e.getMessage());
			}

		} else {
			test.pass("Scenario PASSED");
		}
		DriverFactory.quitDriver();
		System.out.println("The browser is CLOSED");
		extent.flush();// finalise report
	}
	
	 public static ExtentTest getTest() {
	        return test;
	    }

	    public static ExtentReports getExtent() {
	        return extent;
	    }
}
