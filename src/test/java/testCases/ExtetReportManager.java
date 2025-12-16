package testCases;
import org.testng.ITestListener;
 import org.testng.ITestContext;
 import org.testng.ITestListener; 
 import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.aventstack.extent reports. ExtentReports;
//import com.aventstack.extent reports. ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extent reports.reporter.Extent SparkReporter; 
//import com.aventstack.extent reports.reporter.configuration. Theme;
public class ExtetReportManager implements ITestListener{
public ExtentSparkReporter sparkReporter;// UI of the report public 
public ExtentReports extent; //populate common info on the report
public ExtentTest test;// creating test case entries in the report and update status of the test method


public void onStart(ITestContext context) {

sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/reports/myReport.html");//
sparkReporter.config().setDocumentTitle("Automation Report"); // Title of report sparkReporter.config().setReportName("Functional Testing"); // name of the report
sparkReporter.config().setTheme (Theme.DARK);
extent=new ExtentReports();
extent.attachReporter (sparkReporter);
//extent.setSystemInfo("Computer Name", "localhost");
extent.setSystemInfo("Environment", "QA");
extent.setSystemInfo("Tester Name", "IsraTaj "); extent.setSystemInfo("os", "Windows11");
extent.setSystemInfo("Browser name", "Chrome");

}
//result comatin all the infoabout test methods
public void onTestSuccess(ITestResult result) {
	test=extent.createTest(result.getName()); // create a new enty in the report
	test.log(Status.PASS, "Test case PASSED is:" + result.getName()); // update status p/f/s
}
public void onTestFailure (ITestResult result) {
test=extent.createTest(result.getName());
test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable());
}
public void onTestSkipped (ITestResult result) {

test=extent.createTest(result.getName());
test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());//this parameter contains actually is whatever}
}

public void onFinish(ITestContext context) {
	extent.flush();//flush will add all the things on report
}

}
