package testCases;
import org.testng.Assert;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//import com.sun.tools.javac.util.Assert;

import PageObjects.LoginPage;
import Utilities.ExcelUtils;

public class LoginDataDrivenTest {
	
  WebDriver driver; 
  @Test(priority=1)
  public void setup () {
	  driver = new ChromeDriver();
      driver.get("https://carpool-admin.360xpertsolutions.com/");
      // Optional settings
      driver.manage().window().maximize();
      
  }
  
  @Test(dependsOnMethods = {"setup"},priority=2)
  public void performlogin() throws IOException, InterruptedException {
	  LoginPage loginPage = new LoginPage(driver);
	  SoftAssert softAssert = new SoftAssert();

      // Fill in login form
	  String file_path=System.getProperty("user.dir")+"\\TestData\\LoginTest.xlsx";
	  int totalRows=ExcelUtils.getRowCount(file_path, "sheet1");
	  
	  for(int i=1; i<totalRows; i++) {
		  
	  String Phone_Number=ExcelUtils.getCellData(file_path,"sheet1",i,0);
	  String Pwd=ExcelUtils.getCellData(file_path,"sheet1",i,1);
	  String Expected_Result=ExcelUtils.getCellData(file_path,"sheet1",i,2);
//	  Pssing dara to application

      loginPage.setPhoneNumber(Phone_Number);
     
      loginPage.setPasword(Pwd);

      // Click login
      
    loginPage.ClickLoginBtn();
    Thread.sleep(10000);
    
     
//      validation
    String Actual_Result;

    try {
        // Try to find any error message (generic XPath or specific ones)
        String errorMsg = driver.findElement(By.xpath("//*[contains(text(),'Invalid')]")).getText();

        // If error message is found
        System.out.println("Error Message Displayed: " + errorMsg);
        Actual_Result = "Failed";
        loginPage.clearFields();
    	
      
    
      
      
    } catch (Exception e) {
        // No error message means login succeeded
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://carpool-admin.360xpertsolutions.com/list")) {
        	Actual_Result = "Passed";
        	driver.findElement(By.xpath("/html/body/div/div/nav/div[2]/div/div/div[2]")).click();
            driver.findElement(By.xpath("/html/body/div/div/nav/div[2]/div/div[2]/ul/li")).click();// Navigate back to login page
        } else {
        	Actual_Result = "Failed"; // fallback if unexpected behavior
        	loginPage.clearFields();
        	
        	

        	
        }
        
    }

//    String currentUrl = driver.getCurrentUrl();
//	if (currentUrl.equals("https://carpool-admin.360xpertsolutions.com/dashboard")) {
//        Actual_Result = "Passed";
//    } else {
//        Actual_Result = "Failed";
//    }

    // Write actual result to Excel
    ExcelUtils.setCellData(file_path, "sheet1", i, 3, Actual_Result); // Result column = index 3

    // Compare Expected vs Actual and highlight in Excel
//    if (Actual_Result.equalsIgnoreCase(Excepted_Result)) {
//        ExcelUtils.fillGreenColor(file_path, "sheet1", i, 3);
//    } else {
//        ExcelUtils.fillREDColor(file_path, "sheet1", i, 3);
//    }
//    try {
//        Assert.assertEquals(Actual_Result, Excepted_Result, "Validation failed for row " + i);
//        ExcelUtils.fillGreenColor(file_path, "sheet1", i, 3);
//    } catch (AssertionError ae) {
//        ExcelUtils.fillREDColor(file_path, "sheet1", i, 3);
//        System.out.println("Assertion failed for row " + i + ": " + ae.getMessage());
//    }
//
//    System.out.println("Row " + i + ": Expected = " + Excepted_Result + " | Actual = " + Actual_Result);
//   // Highlight the cell
    if (Actual_Result.equalsIgnoreCase(Expected_Result)) {
        ExcelUtils.fillGreenColor(file_path, "sheet1", i, 3);
    } else {
        ExcelUtils.fillREDColor(file_path, "sheet1", i, 3);
    }

    // Soft assertion
    softAssert.assertEquals(Actual_Result, Expected_Result, "Validation failed for row " + i);
    System.out.println("Row " + i + ": Expected = " + Expected_Result + " | Actual = " + Actual_Result);

  
    Thread.sleep(1000); 
//    Thread.sleep(1000);
  }
	 }
  
  @Test(priority=3)
  public void loginviaEnter() {
	  LoginPage loginPage = new LoginPage(driver);
	  SoftAssert softAssert = new SoftAssert();
	  loginPage.setPhoneNumber("923152977618");
	  loginPage.pressEnterOnPhone();
      loginPage.setPasword("test123!@#");
      loginPage.pressEnterOnPass();
      
      String currentUrl = driver.getCurrentUrl();
    	  softAssert.assertEquals(
    			    currentUrl,
    			    "https://carpool-admin.360xpertsolutions.com/list",
    			    "Login failed: Unexpected URL - Expected dashboard/list page"
    			);
      
  }
  
}