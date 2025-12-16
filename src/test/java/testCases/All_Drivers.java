package testCases;

import java.io.IOException;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import PageObjects.AllDrivers;

import PageObjects.LoginPage;
import PageObjects.MyExample;
import Utilities.ExcelUtils;

//@Listeners(testCases.MyListener.class)
public class All_Drivers {
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
      Thread.sleep(5000);
  }
  
//-----------------------------
  @DataProvider(name = "SearchData")
  public static Object[][] getData() throws Exception {
      String filePath = "./TestData/DriverSearchData.xlsx";  // Update path as needed
      String sheetName = "Sheet1";                  // Update if your sheet name differs

      int rowCount = ExcelUtils.getRowCount(filePath, sheetName);
      int colCount = ExcelUtils.getCellCount(filePath, sheetName, 1);

      Object[][] searchData = new Object[rowCount][colCount];

      for (int i = 0; i <rowCount; i++) {
          for (int j = 0; j < colCount; j++) {
              searchData[i][j] = ExcelUtils.getCellData(filePath, sheetName, i + 1, j); // Skip header
          }
          
      }
      return searchData;
  }

  
//
//  @Test(priority = 2, dataProvider = "SearchData")
  public void searchDriverWithExcelData(String driverID, String name, String mobile) throws InterruptedException {
      AllDrivers driverPage = new AllDrivers(driver);
        // Use SoftAssert
          SoftAssert softAssert = new SoftAssert();
       // Collect all results

          // --------- Search by Driver ID ---------
          driverPage.SearchDriver(driverID);
          Thread.sleep(1000);

          List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
          boolean foundDriverID = false;
          for (WebElement row : rows) {
              List<WebElement> cells = row.findElements(By.tagName("td"));
              if (cells.size() >= 3) {
                 String  actualID = cells.get(0).getText().trim();
                  if (actualID.toLowerCase().contains(driverID.toLowerCase())) {
                      foundDriverID = true;
                      
                  }
              }
          }
          softAssert.assertTrue(foundDriverID, "Driver ID not found in any row: " + driverID);
////          
          driverPage.clear_fields();

          // --------- Search by Name ---------
          driverPage.SearchDriver(name);
          Thread.sleep(1000);

          rows = driver.findElements(By.xpath("//table/tbody/tr"));
          boolean foundName = false;
          for (WebElement row : rows) {
              List<WebElement> cells = row.findElements(By.tagName("td"));
              if (cells.size() >= 3) {
                  String actualName = cells.get(1).getText().trim();
                  System.out.println(actualName);
                  System.out.println("Cells count: " + cells.size());
                  if (actualName.toLowerCase().contains(name.toLowerCase())) {
                      foundName = true;
                     
                  }
              }
          }
          softAssert.assertTrue(foundName, "Driver Name not found in any row: " + name);
          
          driverPage.clear_fields();

          // --------- Search by Mobile ---------
          driverPage.SearchDriver(mobile);
          Thread.sleep(1000);

          rows = driver.findElements(By.xpath("//table/tbody/tr"));
          boolean foundMobile = false;
          for (WebElement row : rows) {
              List<WebElement> cells = row.findElements(By.tagName("td"));
              if (cells.size() >= 3) {
            	  System.out.println();
                  String actualMobile = cells.get(2).getText().trim();
                 
                  if (actualMobile.toLowerCase().contains(mobile.toLowerCase())) {
                      foundMobile = true;
                      
                  }
              }
          }
          softAssert.assertTrue(foundMobile, "Mobile Number not found in any row: " + mobile);
          driverPage.clear_fields();

         ;

//          }
          Thread.sleep(1000);
//        driverPage .clear_fields();
          driverPage.clear_fields();
      
          softAssert.assertAll();
  }
////-----Adding Driver ------
//@Test(priority=3)
public void addMultipleDriversFromExcel() throws Exception {
    String path = ".\\TestData\\DriverData2.xlsx";
    int rowCount = ExcelUtils.getRowCount(path, "Sheet1");
    SoftAssert softAssert = new SoftAssert();
    AllDrivers allDrivers = new AllDrivers(driver);
    System.out.println(rowCount);
//    for (int i = 1; i <= rowCount; i++) {
//        String img = ExcelUtils.getCellData(path, "Sheet1", i, 0);
//        String name = ExcelUtils.getCellData(path, "Sheet1", i, 1);
//        String phone = ExcelUtils.getCellData(path, "Sheet1", i, 2);
//        String password = ExcelUtils.getCellData(path, "Sheet1", i, 3);
//        String carNum = ExcelUtils.getCellData(path, "Sheet1", i, 4);
//        String company = ExcelUtils.getCellData(path, "Sheet1", i, 5);  
//        String carType=ExcelUtils.getCellData(path, "Sheet1", i, 6);
//        String status=ExcelUtils.getCellData(path, "Sheet1", i, 7);
//
//        List<String> validationErrors = allDrivers.addNewDriver(img, name, phone, password, carNum, company,carType,status);
//      
//        if (!validationErrors.isEmpty()) {
//            for (String error : validationErrors) {
//                softAssert.fail("Validation Error: " + error);
//            }
//            ExcelUtils.setCellData(path, "Sheet1", i, 8, "FAIL");
//            ExcelUtils.fillREDColor(path, "Sheet1", i, 8);
//        continue;  // Skip toast check if validation errors
//        }
//         
//        String toastMessage = allDrivers.getToastMessage();
//        softAssert.assertEquals(toastMessage, "Driver added successfully", "Unexpected toast message");
//
//        if ("Driver added successfully".equals(toastMessage)) {
//            ExcelUtils.setCellData(path, "Sheet1", i, 8, "PASS");
//            ExcelUtils.fillGreenColor(path, "Sheet1", i, 8);
//        } else {
//            ExcelUtils.setCellData(path, "Sheet1", i, 8, "FAIL");
//            ExcelUtils.fillREDColor(path, "Sheet1", i, 8);
//        }
//
//        Thread.sleep(2000);
//    }
    List<String> validationErrors = allDrivers.addNewDriver("C:/Users/Tesla Laptops/Pictures/Hamza Sarwar.jpg","ali", "03478652893", "test123!@#", "Aud-123A", "folio4","Sedan","Active");
    if (!validationErrors.isEmpty()) {
//      for (String error : validationErrors) {
          softAssert.fail("Validation Error: " + validationErrors);
//      }
  
//  continue;  // Skip toast check if validation errors
  }
   
  String toastMessage = allDrivers.getToastMessage();
  softAssert.assertEquals(toastMessage, "Driver Created successfully", "Unexpected toast message");

  if ("Driver added successfully".equals(toastMessage)) {
      System.out.println("Pass");
  } else {
	  System.out.println("Fail");
  }
  softAssert.assertAll();  
  Thread.sleep(1000);// Final assert check
}




//-----------------------------
  
//@Test(priority=4)
 public void TestUpdateDriver() throws InterruptedException {
	 AllDrivers UpdDriver= new AllDrivers(driver);
	UpdDriver.UpdateDriver("C:/Users/Tesla Laptops/Pictures/Hamza Sarwar.jpg","Jamil ALI","03426578912","","Aud-123AB","folio4","Sedan","Inactive");
Thread.sleep(1000);
}
//	
//@Test(priority=5)
//------  delete driver----

public void DeleteDriver() throws InterruptedException {
    AllDrivers delDriver = new AllDrivers(driver);
    SoftAssert softAssert = new SoftAssert();
    delDriver.deleteDriver();
 
   String actual_message=delDriver.pop1();
   softAssert.assertEquals(actual_message, "Driver deleted successfully.", "Success popup message mismatch");

   // Collect results
   softAssert.assertAll();}

//------   driver----
//@Test(priority=4)
public void FetLocDriver() throws InterruptedException{
	 AllDrivers FLDriver=new AllDrivers(driver);
	 SoftAssert softAssert = new SoftAssert();
	 FLDriver.fetchDriverLocation();


	     // Wait for either success or error toast
	     boolean errorShown = FLDriver.isLocationErrorDisplayed();
	     boolean successShown = FLDriver.isLocationSuccessDisplayed();

	     // Assert that one of them showed up
	     Assert.assertTrue(errorShown || successShown, "No toast message displayed after fetching location");

	     // Fail test if error was shown
	     Assert.assertFalse(errorShown, "'Could not fetch location' error was displayed");

	     // Optionally verify success
	     Assert.assertTrue(successShown, "Location was not fetched successfully");
	 }

//%%%%%%%%%%%%%%%%%%%%%%%%%%
//@Test(priority=6)
public void FoundDuolocates() throws InterruptedException{
	 AllDrivers duplicates=new AllDrivers(driver);
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


@AfterClass
public void closeBrowser() {
    driver.quit();
}



  
}
