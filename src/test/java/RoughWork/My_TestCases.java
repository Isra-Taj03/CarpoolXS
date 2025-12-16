package RoughWork;



import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.AllDrivers;
import PageObjects.AllRoutes;
import PageObjects.LoginPage;
import PageObjects.MyExample;
import Utilities.ExcelUtils;
import org.testng.asserts.SoftAssert;
public class My_TestCases {

    WebDriver driver; 

    @Test(priority = 1)
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://carpool-admin.360xpertsolutions.com/");
        // Optional settings
        driver.manage().window().maximize();
    }

    @Test(dependsOnMethods = {"setup"}, priority = 2)
    public void performLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Fill in login form
        loginPage.setPhoneNumber("923152977618");
        loginPage.setPasword("test123!@#");

        // Click login
        loginPage.ClickLoginBtn();
    }

//    Update Driver
    @Test(priority=4)
    public void TestaddRoute() throws InterruptedException, IOException {
    	 String path = ".\\TestData\\AddRoute.xlsx";
         int rowCount = ExcelUtils.getRowCount(path, "Sheet1");
	    MyExample addRoute = new MyExample(driver);
	    SoftAssert softAssert = new SoftAssert();
	    for (int i = 1; i < rowCount; i++) {
            String driverName = ExcelUtils.getCellData(path, "Sheet1", i, 0);
            String routeName= ExcelUtils.getCellData(path, "Sheet1", i, 1);
            String passengerName = ExcelUtils.getCellData(path, "Sheet1", i, 2);
            String phone = ExcelUtils.getCellData(path, "Sheet1", i, 3);
            String address = ExcelUtils.getCellData(path, "Sheet1", i, 4);
            String pickupLoc = ExcelUtils.getCellData(path, "Sheet1", i, 5);
            String dropoffLoc= ExcelUtils.getCellData(path, "Sheet1", i, 6);

            List<String> validationErrors = addRoute.addNewRoute(driverName,routeName,passengerName,
                    phone, address,pickupLoc,dropoffLoc);

            if (!validationErrors.isEmpty()) {
                for (String error : validationErrors) {
                    softAssert.fail("Validation Error: " + error);
                }
                ExcelUtils.setCellData(path, "Sheet1", i, 6, "FAIL");
                ExcelUtils.fillREDColor(path, "Sheet1", i, 6);
                continue;  // Skip toast check if validation errors
            }

            String toastMessage = addRoute.getToastMessage();
            softAssert.assertEquals(toastMessage, "Route created successfully.", "Unexpected toast message");

            if ("Driver added successfully".equals(toastMessage)) {
                ExcelUtils.setCellData(path, "Sheet1", i, 7, "PASS");
                ExcelUtils.fillGreenColor(path, "Sheet1", i, 7);
            } else {
                ExcelUtils.setCellData(path, "Sheet1", i, 7, "FAIL");
                ExcelUtils.fillREDColor(path, "Sheet1", i, 7);
            }
//	    List<String> validationErrors = addRoute.addNewRoute();

//        if (!validationErrors.isEmpty()) {
//            for (String error : validationErrors) {
//                softAssert.fail("Validation Error: " + error);
//            }
//            ExcelUtils.setCellData(path, "Sheet1", i, 6, "FAIL");
//            ExcelUtils.fillREDColor(path, "Sheet1", i, 6);
//            continue;  // Skip toast check if validation errors
            Thread.sleep(2000);
        }

//        String toastMessage =addRoute .getToastMessage();
//        softAssert.assertEquals(toastMessage, "Route created successfully.", "Unexpected toast message");

//        if ("Driver added successfully".equals(toastMessage)) {
//            ExcelUtils.setCellData(path, "Sheet1", i, 6, "PASS");
//            ExcelUtils.fillGreenColor(path, "Sheet1", i, 6);
//        } else {
//            ExcelUtils.setCellData(path, "Sheet1", i, 6, "FAIL");
//            ExcelUtils.fillREDColor(path, "Sheet1", i, 6);
//        }

        Thread.sleep(2000);
        softAssert.assertAll();
    }

//    softAssert.assertAll();
//	    try {
//	     addRoute.addNewRoute(
//	        "Kashif ", 
//	        "Hello World", 
//	        "Talha", 
//	        "12345678901", 
//	        "Gulshan", 
//	        "24.939583577302717, 67.1527126909316", 
//	        "24.939583577302717, 67.1527126909316"
//	    );}
//	    catch(Exception e) {
//	    	System.out.println(e.getMessage());
//	  
//	    }
//
//  //  Block of code to handle errors


    //-----------------------------
//    // Add Driver Test - Data-Driven
//    @Test(priority = 4)
//    public void TestAddDriver() throws InterruptedException, IOException {
//        AllDrivers addingDriver = new AllDrivers(driver);
//
//        // Get the row count from the AddDriver sheet
//        int rowCount = ExcelUtils.getRowCount("src/test/resources/DriverData.xlsx", "AddDriver");
//
//        // Iterate through each row and add driver
//        for (int i = 1; i <= rowCount; i++) {
//            String img = ExcelUtils.getCellData("src/test/resources/DriverData.xlsx", "AddDriver", i, 0);
//            String name = ExcelUtils.getCellData("src/test/resources/DriverData.xlsx", "AddDriver", i, 1);
//            String phone = ExcelUtils.getCellData("src/test/resources/DriverData.xlsx", "AddDriver", i, 2);
//            String password = ExcelUtils.getCellData("src/test/resources/DriverData.xlsx", "AddDriver", i, 3);
//            String carNumber = ExcelUtils.getCellData("src/test/resources/DriverData.xlsx", "AddDriver", i, 4);
//            String companyName = ExcelUtils.getCellData("src/test/resources/DriverData.xlsx", "AddDriver", i, 5);
//
//            // Add new driver
//            addingDriver.addNewDriver(img, name, phone, password, carNumber, companyName);
//        }
//    }
//
//    //-----------------------------
    // Search Driver Test - Data-Driven
//    @Test(priority = 4)
//    public void DriverSearch() throws InterruptedException, IOException {
//        AllDrivers searchPage = new AllDrivers(driver);
//
//        // Get the row count from the SearchDriver sheet
//        int rowCount = ExcelUtils.getRowCount("src/test/resources/DriverSearchData.xlsx", "SearchDriver");
//
//        // Iterate through each row and perform the search
//        for (int i = 1; i <= rowCount; i++) {
//            String driverID = ExcelUtils.getCellData("src/test/resources/DriverSearchData.xlsx", "SearchDriver", i, 0);
//            String name = ExcelUtils.getCellData("src/test/resources/DriverSearchData.xlsx", "SearchDriver", i, 1);
//            String company = ExcelUtils.getCellData("src/test/resources/DriverSearchData.xlsx", "SearchDriver", i, 2);
//
//            // Perform search with DriverID, Name, and Company
//            String[] values = {driverID, name, company};
////            searchPage.SearchDriver(values);
//        }
//    }

    //-----------------------------
//    // Update Driver Test
//    @Test(priority = 9)
//    public void TestUpdateDriver() throws InterruptedException {
//        AllDrivers updDriver = new AllDrivers(driver);
//        updDriver.UpdateDriver("C:/Users/Tesla Laptops/Pictures/Hamza Sarwar.jpg", "Jamil ALI", "01274567469", "MBH123-cb", "Folio36");
//    }

    //----------------My test-------------
    // Delete Driver Test
    @Test(priority = 4)
    public void DeleteDriver() throws InterruptedException {
        MyExample delDriver = new MyExample(driver);
        SoftAssert softAssert = new SoftAssert();
        delDriver.deleteDriver();
     
       String actual_message=delDriver.pop1();
       softAssert.assertEquals(actual_message, "Driver deleted successfully.", "Success popup message mismatch");

       // Collect results
       softAssert.assertAll();
//        if (actual_message.contains("Driver deleted successfully.")) {
//            System.out.println("Popup appeared with correct message.");
//        } else {
//            System.out.println("Popup message not as expected.");
//        }
//	
    }

    //------------my test-----------------
    // Fetch Driver Location Test
//    @Test(priority = 7)
//    public void FetLocDriver() throws InterruptedException {
//        AllDrivers FLDriver = new AllDrivers(driver);
//        FLDriver.fetchDriverLocation();
//    }

    //-----------------------------
    // Close Browser
    @Test(priority = 8)
    public void closeBrowser() {
        driver.quit();
    }
}
