package common;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.cucumber.java.*;

public class ExtentReportListener {

	private static ExtentReports extent;
	private static ExtentTest test;
	private static WebDriver driver;
	public static String reportextentLog = "";
	static String path = System.getProperty("user.dir");

	// Setup the Extent Report before the first scenario runs
	@Before
	public void setup(Scenario scenario) {
		if (extent == null) {
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

			// Original report with timestamp
			String reportDirPath = System.getProperty("user.dir") + "/ExtentReports/" + timeStamp;
			File reportDir = new File(reportDirPath);
			if (!reportDir.exists()) {
				reportDir.mkdirs(); // Create directories if they don't exist
			}
			String reportFilePathWithTimestamp = reportDirPath + "/extentReport.html";

			// New report without timestamp
			String reportDirPathWithoutTimestamp = System.getProperty("user.dir") + "/ExtentReports/";
			File reportDirWithoutTimestamp = new File(reportDirPathWithoutTimestamp);
			if (!reportDirWithoutTimestamp.exists()) {
				reportDirWithoutTimestamp.mkdirs();
			}
			String reportFilePathWithoutTimestamp = reportDirPathWithoutTimestamp + "Report.html";

			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportFilePathWithTimestamp);
			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config().setDocumentTitle("BDD Test Report");
			htmlReporter.config().setReportName(scenario.getName());

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);

			// Attach another report without timestamp
			ExtentHtmlReporter htmlReporterWithoutTimestamp = new ExtentHtmlReporter(reportFilePathWithoutTimestamp);
			htmlReporterWithoutTimestamp.config().setTheme(Theme.STANDARD);
			htmlReporterWithoutTimestamp.config().setDocumentTitle("BDD Test Report");
			htmlReporterWithoutTimestamp.config().setReportName(scenario.getName());

			extent.attachReporter(htmlReporterWithoutTimestamp);
		}

		test = extent.createTest(scenario.getName());
	}

	@AfterStep
	public void afterStep(Scenario scenario) {
		String eachstep = CommonUtil.GetXMLData(
				Paths.get(path.toString(), "src", "test", "java", "ApplicationSettings.xml").toString(),
				"EnableEachStepScreenshot");
		try {
			driver = WebBrowser.getBrowser();
			String stepName = StepListener.stepName;
			String finalLog = stepName + "<br>" + reportextentLog.replace("\n", "<br>");
			if (scenario.isFailed()) {
				TakesScreenshot ts = (TakesScreenshot) driver;
				String screenshotBase64 = ts.getScreenshotAs(OutputType.BASE64);
				Media media = MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build();
				test.fail("Step Failed", media);
			} else if (stepName.toLowerCase().contains("verify") || eachstep.equalsIgnoreCase("true")) {
				TakesScreenshot ts = (TakesScreenshot) driver;
				String screenshotBase64 = ts.getScreenshotAs(OutputType.BASE64);
				Media media = MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build();
				test.pass(finalLog, media);
			} else {
				test.pass(finalLog);
			}
		} catch (Exception ex) {
			System.err.println("Error in afterStep: " + ex.getMessage());
		} finally {

			reportextentLog = "";
		}
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			test.log(Status.FAIL, "Scenario Failed: " + scenario.getName());
		} else {
			test.log(Status.PASS, "Scenario Passed: " + scenario.getName());
		}
	}

	@AfterAll
	public static void flushReport() {
		if (extent != null) {
			extent.flush();
		}
	}
}
