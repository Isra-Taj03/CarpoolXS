package testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import PageObjects.AllDrivers;
import PageObjects.AllRoutes;
import PageObjects.LoginPage;
import PageObjects.MyExample;
import Utilities.ExcelUtils;

public class All_Routes {
	WebDriver driver; 
	  @Test(priority=1)
	  public void setup () {
		  driver = new ChromeDriver();
	      driver.get("https://carpool-admin.360xpertsolutions.com/");
	      // Optional settings
	      driver.manage().window().maximize();
	  }
	  @Test(dependsOnMethods = {"setup"},priority=2)
	  public void performlogin() throws InterruptedException {
		  LoginPage loginPage = new LoginPage(driver);

	      // Fill in login form
	      loginPage.setPhoneNumber("923152977618");
	      loginPage.setPasword("test123!@#");

	      // Click login
	      loginPage.ClickLoginBtn();
	      Thread.sleep(3000);
	  }
	  


	//-----------Adding Routes -------
//	  @Test(priority=3)
	    public void TestaddRoute() throws InterruptedException, IOException {
	    	 String path = ".\\TestData\\Adding_Route.xlsx";
	         int rowCount = ExcelUtils.getRowCount(path, "Sheet1");
		    AllRoutes addRoute = new AllRoutes(driver);
		    SoftAssert softAssert = new SoftAssert();
		    for (int i = 1; i <= rowCount; i++) {
	            String driverName = ExcelUtils.getCellData(path, "Sheet1", i, 0);
	            String routeName= ExcelUtils.getCellData(path, "Sheet1", i, 1);
	            String passengerName = ExcelUtils.getCellData(path, "Sheet1", i, 2);
	            String phone = ExcelUtils.getCellData(path, "Sheet1", i, 3);
	            String address = ExcelUtils.getCellData(path, "Sheet1", i, 4);
	            String pickupLoc = ExcelUtils.getCellData(path, "Sheet1", i, 5);
	            String dropoffLoc= ExcelUtils.getCellData(path, "Sheet1", i, 6);
                System.out.println("row count is:"+rowCount);
                List<String> validationErrors = addRoute.addNewRoute(driverName,routeName,passengerName,
	                    phone, address,pickupLoc,dropoffLoc);

	            if (!validationErrors.isEmpty()) {
	                for (String error : validationErrors) {
	                    softAssert.fail("Validation Error: " + error);
	                }
	                ExcelUtils.setCellData(path, "Sheet1", i, 7, "FAIL");
	                ExcelUtils.fillREDColor(path, "Sheet1", i, 7);
	                continue;  // Skip toast check if validation errors
	            }

	            String toastMessage = addRoute.getToastMessage();
	            softAssert.assertEquals(toastMessage, "Route created successfully.", "Unexpected toast message");
	            System.out.println("toatst msg"+toastMessage);

	            if ("Route created successfully.".equals(toastMessage)) {
	                ExcelUtils.setCellData(path, "Sheet1", i, 7, "PASS");
	                ExcelUtils.fillGreenColor(path, "Sheet1", i, 7);
	            } else {                                            
	                ExcelUtils.setCellData(path, "Sheet1", i, 7, "FAIL");
	                
	                
	                ExcelUtils.fillREDColor(path, "Sheet1", i, 7);
	            }
//		    List<String> validationErrors = addRoute.addNewRoute();

//	        if (!validationErrors.isEmpty()) {
//	            for (String error : validationErrors) {
//	                softAssert.fail("Validation Error: " + error);
//	            }
//	            ExcelUtils.setCellData(path, "Sheet1", i, 6, "FAIL");
//	            ExcelUtils.fillREDColor(path, "Sheet1", i, 6);
//	            continue;  // Skip toast check if validation errors
	            Thread.sleep(2000);
	        }

//	        String toastMessage =addRoute .getToastMessage();
//	        softAssert.assertEquals(toastMessage, "Route created successfully.", "Unexpected toast message");

//	        if ("Driver added successfully".equals(toastMessage)) {
//	            ExcelUtils.setCellData(path, "Sheet1", i, 6, "PASS");
//	            ExcelUtils.fillGreenColor(path, "Sheet1", i, 6);
//	        } else {
//	            ExcelUtils.setCellData(path, "Sheet1", i, 6, "FAIL");
//	            ExcelUtils.fillREDColor(path, "Sheet1", i, 6);
//	        }

	        Thread.sleep(2000);
	        softAssert.assertAll();
	    }
	  
		//----------- Update Route -------
	  @Test(priority=4)
	    public void UpdateRoute() throws InterruptedException {
	    AllRoutes  updatePage = new AllRoutes(driver);
	    SoftAssert softAssert = new SoftAssert();
//	    updatePage.UpdateRoute(
//	        "Jamil ALI",
//	        "Hello World","taha","03482908387", "Shah Faisal", "24.918000, 67.063000", "24.918000, 67.063000"
//	       
//	    );
	    List<String> validationErrors =  updatePage.UpdateRoute(
		       "Sheraz Nadeem",
		        "Hello World","taha","0348290838", "02345678998", "24.918000, 67.063000", "24.918000, 67.063000"
		       
		    );

	    if (!validationErrors.isEmpty()) {
            for (String error : validationErrors) {
                softAssert.fail("Validation Error: " + error);
            }
             // Skip toast check if validation errors
        }
	    String toastMessage = updatePage.getUpdateToastMessage();
        softAssert.assertEquals(toastMessage, "Route updated successfully.", "Unexpected toast message");
        System.out.println("toatst msg"+toastMessage);

        if ("Route created successfully.".equals(toastMessage)) {
        	System.out.println("Route updated successfully");
        }
        else {
        	softAssert.fail("Testcase Failed");
        }
        
//	    updatePage.enterPassenger(0, "Taha", "03482908387", "Shah Faisal", "24.918000, 67.063000", "24.918000, 67.063000");
//	    updatePage.enterPassenger(1, "Ali", "03482908389", "Gulshan", "24.918000, 67.063000", "24.918000, 67.063000");
//	    updatePage.enterPassenger(2, "Ahmed", "03482908399", "Nazimabad", "24.918000, 67.063000", "24.918000, 67.063000");
	    Thread.sleep(3000);
	    softAssert.assertAll();
	  }
	  
	//----------- Delete Route -------
//	  @Test(priority=5)
	    public void deleteRoute() throws InterruptedException {
	    AllRoutes delRoute = new AllRoutes(driver);
	    SoftAssert softAssert = new SoftAssert();
	    delRoute.deleteRoute();
	   
	    String actual_message=delRoute.delete_Route_popUp();
	    softAssert.assertEquals(actual_message, "Route Deleted Successfully!", "Success popup message mismatch");

	    // Collect results
	    softAssert.assertAll();
	    
	    }
	  
	    
//	    -------Search Route
//	    @Test(priority=6)
	    public void searchRoute() throws InterruptedException {
	        AllRoutes driverPage = new AllRoutes(driver);
	          // Use SoftAssert
	            SoftAssert softAssert = new SoftAssert();
	         // Collect all results

	            // --------- Search by Driver ID ---------
	            String RouteID="RTE120066299";
	            String Routename="gulshan";
	            String driverName="Shoaib";
	            
	            driverPage.SearchRoute(RouteID);
	            Thread.sleep(1000);

	            List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
	            boolean foundDriverID = false;
	            for (WebElement row : rows) {
	                List<WebElement> cells = row.findElements(By.tagName("td"));
	                if (cells.size() >= 3) {
	                   String  actualID = cells.get(0).getText().trim();
	                    if (actualID.toLowerCase().contains(RouteID.toLowerCase())) {
	                        foundDriverID = true;
	                        
	                    }
	                }
	            }
	            softAssert.assertTrue(foundDriverID, "Route ID not found in any row: " + RouteID);
////	            
	            driverPage.clear_fields();

	            // --------- Search by Name ---------
//	            String name="gulshan";
	            driverPage.SearchRoute(Routename);
	            Thread.sleep(1000);

	            rows = driver.findElements(By.xpath("//table/tbody/tr"));
	            boolean foundName = false;
	            for (WebElement row : rows) {
	                List<WebElement> cells = row.findElements(By.tagName("td"));
	                if (cells.size() >= 3) {
	                    String actualName = cells.get(1).getText().trim();
	                    System.out.println(actualName);
	                    System.out.println("Cells count: " + cells.size());
	                    if (actualName.toLowerCase().contains(Routename.toLowerCase())) {
	                        foundName = true;
	                       
	                    }
	                }
	            }
	            softAssert.assertTrue(foundName, "Route Name not found in any row: " + Routename);
	            
	            driverPage.clear_fields();

	            // --------- Search by Mobile ---------
//	            String mobile="Shoaib";
	            driverPage.SearchRoute(driverName);
	            Thread.sleep(1000);

	            rows = driver.findElements(By.xpath("//table/tbody/tr"));
	            boolean foundMobile = false;
	            for (WebElement row : rows) {
	                List<WebElement> cells = row.findElements(By.tagName("td"));
	                if (cells.size() >= 3) {
	              	  System.out.println();
	                    String actualMobile = cells.get(2).getText().trim();
	                   
	                    if (actualMobile.toLowerCase().contains(driverName.toLowerCase())) {
	                        foundMobile = true;
	                        
	                    }
	                }
	            }
	            softAssert.assertTrue(foundMobile, "DriverName not found in any row: " + driverName);
	            driverPage.clear_fields();

	           ;

//	            }
	            Thread.sleep(1000);
//	          driverPage .clear_fields();
	            driverPage.clear_fields();
	        
	            softAssert.assertAll();
	    }
}
