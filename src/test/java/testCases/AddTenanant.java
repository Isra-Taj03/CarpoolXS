package testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import PageObjects.AllTenants;
import PageObjects.LoginPage;
import Utilities.ExcelUtils;

public class AddTenanant {
	 WebDriver driver; 
		//  @Test (priority=1)
		 @BeforeClass
		  public void setup () {
			  driver = new ChromeDriver();
		      driver.get("https://carpool-admin.360xpertsolutions.com/");
		      // Optional settings
		      driver.manage().window().maximize();
		  }
		  
		//  @Test(dependsOnMethods = {"setup"},priority=1)
		 @Test(priority=1)
		  public void performlogin() throws InterruptedException {
			  LoginPage loginPage = new LoginPage(driver);

		      // Fill in login form
		      loginPage.setPhoneNumber("923152977618");
		      loginPage.setPasword("test123!@#");

		      // Click login
		      loginPage.ClickLoginBtn();
		      Thread.sleep(3000);
		  }
		 @Test(priority=2)
		 public void addTenant() throws IOException, InterruptedException {
			 	
			  SoftAssert softAssert = new SoftAssert();
			    AllTenants addTenant= new AllTenants(driver);
			
//			        List<String> validationErrors = allDrivers.Add_Tenants(img, name, phone, password,company);
			        List<String> validationErrors =addTenant.addTenants("C:/Users/Tesla Laptops/Pictures/Hamza Sarwar.jpg", "123", "test123!@#", "01010029876", "mycomabc");
			
//			        if (!validationErrors.isEmpty()) {
//			            for (String error : validationErrors) {
//			                softAssert.fail("Validation Error: " + error);
//			            }
//			            ExcelUtils.setCellData(path, "Sheet1", i, 5, "FAIL");
//			            ExcelUtils.fillREDColor(path, "Sheet1", i, 5);
//			        continue;  // Skip toast check if validation errors
//			        }
			
			        String toastMessage = addTenant.getToastMessage();
			        softAssert.assertEquals(toastMessage, "Tenanat created successfully", "Unexpected toast message");
			
			        if ("Tenanat created successfully".equals(toastMessage)) {
			           System.out.println("Tenanat created");
			        } else {
			        	softAssert.fail("Testcase Failed");
			        }
			
			        Thread.sleep(2000);
			        softAssert.assertAll(); 
			    }
			
			
				 
			 }
		 
		 

