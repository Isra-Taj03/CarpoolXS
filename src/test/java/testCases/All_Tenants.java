package testCases;

import PageObjects.AllDrivers;
import PageObjects.AllRoutes;
import PageObjects.AllTenants;
import PageObjects.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
//import com.sun.tools.javac.util.List;
import java.util.List;
import Utilities.ExcelUtils;

public class All_Tenants {
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
	
//	@Test(priority=2)
	 public void updateTenant() throws IOException, InterruptedException {
		String path = ".\\TestData\\addTenant.xlsx";
//	    int rowCount = ExcelUtils.getRowCount(path, "Sheet1");
	    SoftAssert softAssert = new SoftAssert();
	    AllTenants updateTenant= new AllTenants(driver);
	    List<String> validationErrors =updateTenant.updtaeTenant("C:/Users/Tesla Laptops/Pictures/Hamza Sarwar.jpg", "ali", "test123!@#", "01010029876", "mycomabc");

		 
	 }
	
	
//	^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^Search Tenant^^^^^^^^^^^^^^^^^^^^^^^
//	@Test(priority=3)
	 public void searchTenant() throws InterruptedException {
        AllTenants driverPage = new AllTenants(driver);
          // Use SoftAssert
            SoftAssert softAssert = new SoftAssert();
         // Collect all results
          
            // --------- Search by Driver ID ---------
            String TenanatID="TNT-211862";
            String TenantName="test";
            String TenantPhone="03002420222";
            String CompanyName="Peaceful Ride";
            
            driverPage.SearchTenant(TenanatID);
            Thread.sleep(1000);

            List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
            boolean foundDriverID = false;
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (cells.size() >= 5) {
                   String  actualID = cells.get(0).getText().trim();
                    if (actualID.toLowerCase().contains(TenanatID.toLowerCase())) {
                        foundDriverID = true;
                        
                    }
                }
            }
            softAssert.assertTrue(foundDriverID, "Tenanat ID not found in any row: " + TenanatID);
////            
            driverPage.clear_fields();

            // --------- Search by Name ---------
//            String name="gulshan";
            driverPage.SearchTenant(TenantName);
            Thread.sleep(1000);

            rows = driver.findElements(By.xpath("//table/tbody/tr"));
            boolean foundName = false;
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (cells.size() >= 5) {
                    String actualName = cells.get(2).getText().trim();
                    System.out.println(actualName);
                    System.out.println("Cells count: " + cells.size());
                    if (actualName.toLowerCase().contains(TenantName.toLowerCase())) {
                        foundName = true;
                       
                    }
                }
            }
            softAssert.assertTrue(foundName, "Tenant Name not found in any row: " + TenantName);
            
            driverPage.clear_fields();

            // --------- Search by Mobile ---------
//            String mobile="Shoaib";
            driverPage.SearchTenant(TenantPhone);
            Thread.sleep(1000);

            rows = driver.findElements(By.xpath("//table/tbody/tr"));
            boolean foundMobile = false;
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (cells.size() >= 5) {
              	  System.out.println();
                    String actualMobile = cells.get(3).getText().trim();
                   
                    if (actualMobile.toLowerCase().contains(TenantPhone.toLowerCase())) {
                        foundMobile = true;
                        
                    }
                }
            }
            softAssert.assertTrue(foundMobile, "Tenant Phone Number not found in any row: " + TenantPhone);
            driverPage.clear_fields();

           ;
           
           //////

//           String mobile="Shoaib";
           driverPage.SearchTenant(CompanyName);
           Thread.sleep(1000);

           rows = driver.findElements(By.xpath("//table/tbody/tr"));
           boolean foundCompany = false;
           for (WebElement row : rows) {
               List<WebElement> cells = row.findElements(By.tagName("td"));
               if (cells.size() >= 5) {
             	  System.out.println();
                   String actualMobile = cells.get(4).getText().trim();
                  
                   if (actualMobile.toLowerCase().contains(CompanyName.toLowerCase())) {
                	   foundCompany = true;
                       
                   }
               }
           }
           softAssert.assertTrue(foundCompany, "Company Name not found in any row: " + CompanyName);
           driverPage.clear_fields();

          ;

//            }
            Thread.sleep(1000);
//          driverPage .clear_fields();
            driverPage.clear_fields();
        
            softAssert.assertAll();
    }
//	****************Delete Tenanat******************
//	 @Test(priority=4)
	    public void deleteRoute() throws InterruptedException {
	    AllTenants delRoute = new AllTenants(driver);
	    SoftAssert softAssert = new SoftAssert();
	    delRoute.deleteTenant();
	   
	    String actual_message=delRoute.delete_Tenant_popUp();
	    softAssert.assertEquals(actual_message, "Tenant Deleted Successfully!", "Success popup message mismatch");

	    // Collect results
	    softAssert.assertAll();
	    
	    }
	    
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	    @Test(priority=4)
	    public void FoundDuolocates() throws InterruptedException{
	    	 AllTenants duplicates=new AllTenants(driver);
	    	 SoftAssert softAssert = new SoftAssert();
	    	 List<String> errors=duplicates.foundDuplicates();
	    	 if (errors.isEmpty()) {
	    	        System.out.println("✅ No duplicates found.");
	    	    } else {
	    	        for (String err : errors) {
	    	            System.out.println("❗ " + err);
	    	            softAssert.fail(err);  // Soft assert for each error found
	    	        }
	    	    }

	    	    softAssert.assertAll(); 
	    	 }
}
