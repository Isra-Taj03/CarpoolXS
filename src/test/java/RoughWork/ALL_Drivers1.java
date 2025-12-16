package RoughWork;


import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.MyExample;
import PageObjects.AllDrivers;
import PageObjects.LoginPage;
import Utilities.ExcelUtils;
import org.testng.asserts.SoftAssert;
public class ALL_Drivers1 {
    WebDriver driver;

    // Setup method
    @Test(priority = 1)
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://carpool-admin.360xpertsolutions.com/");
        driver.manage().window().maximize();
    }

    // Login method
    @Test(dependsOnMethods = {"setup"}, priority = 2)
    public void performLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setPhoneNumber("923152977618");
        loginPage.setPasword("test123!@#");
        loginPage.ClickLoginBtn();
        Thread.sleep(5000);

    }
//    @Test(priority=4)
//    public void TestAddDriver() throws InterruptedException {
//    MyExample addingDriver=new MyExample(driver);
//    addingDriver.addNewDriver("C:/Users/Tesla Laptops/Pictures/Hamza Sarwar.jpg", "Jamil","994566693","test123!@#","MBH123-ab","Folio36");
//    }
//    @Test(priority = 3)
//    public void addMultipleDriversFromExcel() throws Exception {
//        String path = ".\\TestData\\DriverData2.xlsx";
//        int rowCount = ExcelUtils.getRowCount(path, "Sheet1");
//        SoftAssert softAssert = new SoftAssert();
//        MyExample allDrivers = new MyExample(driver);
//
//        for (int i = 1; i < rowCount; i++) {
//            String img = ExcelUtils.getCellData(path, "Sheet1", i, 0);
//            String name = ExcelUtils.getCellData(path, "Sheet1", i, 1);
//            String phone = ExcelUtils.getCellData(path, "Sheet1", i, 2);
//            String password = ExcelUtils.getCellData(path, "Sheet1", i, 3);
//            String carNum = ExcelUtils.getCellData(path, "Sheet1", i, 4);
//            String company = ExcelUtils.getCellData(path, "Sheet1", i, 5);
//
//            boolean isSuccess = allDrivers.addNewDriver(img, name, phone, password, carNum, company);
//           
//            String toastMessage = allDrivers.getToastMessage();
//            if (toastMessage != null && toastMessage.equals("Driver added successfully")) {
//                ExcelUtils.setCellData(path, "Sheet1", i, 6, "PASS");
//                ExcelUtils.fillGreenColor(path, "Sheet1", i, 6);
////                softAssert.assertEquals(toastMessage, "Driver added successfully", "Toast message did not match");
////                Assert.assertEquals(toastMessage, "Driver added successfully", "Toast message did not match");
//            } else {
//                ExcelUtils.setCellData(path, "Sheet1", i, 6, "FAIL");
//                ExcelUtils.fillREDColor(path, "Sheet1", i, 6);
////                Assert.fail("Driver was not added or toast did not appear.");
//            }
////            
//////
//            if (isSuccess) {
//                ExcelUtils.setCellData(path, "Sheet1", i, 6, "PASS");
//                ExcelUtils.fillGreenColor(path, "Sheet1", i, 6);
//            } else {
//                ExcelUtils.setCellData(path, "Sheet1", i, 6, "FAIL");
//                ExcelUtils.fillREDColor(path, "Sheet1", i, 6);
//            }
//
//            Thread.sleep(2000); // small wait before next record
//        }
//        softAssert.assertAll();
//        
//    }
    @Test(priority = 3) 
    public void addMultipleDriversFromExcel() throws Exception {
        String path = ".\\TestData\\DriverData2.xlsx";
        int rowCount = ExcelUtils.getRowCount(path, "Sheet1");
        SoftAssert softAssert = new SoftAssert();
        MyExample allDrivers = new MyExample(driver);

        for (int i = 1; i < rowCount; i++) {
            String img = ExcelUtils.getCellData(path, "Sheet1", i, 0);
            String name = ExcelUtils.getCellData(path, "Sheet1", i, 1);
            String phone = ExcelUtils.getCellData(path, "Sheet1", i, 2);
            String password = ExcelUtils.getCellData(path, "Sheet1", i, 3);
            String carNum = ExcelUtils.getCellData(path, "Sheet1", i, 4);
            String company = ExcelUtils.getCellData(path, "Sheet1", i, 5);

            List<String> validationErrors = allDrivers.addNewDriver(img, name, phone, password, carNum, company);

            if (!validationErrors.isEmpty()) {
                for (String error : validationErrors) {
                    softAssert.fail("Validation Error: " + error);
                }
                ExcelUtils.setCellData(path, "Sheet1", i, 6, "FAIL");
                ExcelUtils.fillREDColor(path, "Sheet1", i, 6);
                continue;  // Skip toast check if validation errors
            }

            String toastMessage = allDrivers.getToastMessage();
            softAssert.assertEquals(toastMessage, "Driver added successfully", "Unexpected toast message");

            if ("Driver added successfully".equals(toastMessage)) {
                ExcelUtils.setCellData(path, "Sheet1", i, 6, "PASS");
                ExcelUtils.fillGreenColor(path, "Sheet1", i, 6);
            } else {
                ExcelUtils.setCellData(path, "Sheet1", i, 6, "FAIL");
                ExcelUtils.fillREDColor(path, "Sheet1", i, 6);
            }

            Thread.sleep(2000);
        }

        softAssert.assertAll();  // Final assert check
    }




//
//    @DataProvider(name = "SearchData")
//    public static Object[][] getData() throws Exception {
//        String filePath = "./TestData/DriverSearchData.xlsx";  // Update path as needed
//        String sheetName = "Sheet1";                  // Update if your sheet name differs
//
//        int rowCount = ExcelUtils.getRowCount(filePath, sheetName);
//        int colCount = ExcelUtils.getCellCount(filePath, sheetName, 1);
//
//        Object[][] searchData = new Object[rowCount][colCount];
//
//        for (int i = 0; i < rowCount; i++) {
//            for (int j = 0; j < colCount; j++) {
//                searchData[i][j] = ExcelUtils.getCellData(filePath, sheetName, i + 1, j); // Skip header
//            }
//        }
//        return searchData;
//    }
//
//    
//
//    @Test(priority = 3, dataProvider = "SearchData")
//    public void searchDriverWithExcelData(String driverID, String name, String mobile) throws InterruptedException {
//        AllDrivers driverPage = new AllDrivers(driver);
//        driverPage.SearchDriver(driverID);
//        driverPage.SearchDriver(name);
//        driverPage.SearchDriver(mobile);
//    }
//    @DataProvider(name = "AddDriverInfo")
//    public static Object[][] getAddDriverData() throws Exception {
//        String filePath =System.getProperty("user.dir")+"\\TestData\\DriverData.xlsx";;  // adjust path as needed
//        String sheetName = "AddDriver";  // adjust sheet name
//
//        int rowCount = ExcelUtils.getRowCount(filePath, sheetName);
//        int colCount = ExcelUtils.getCellCount(filePath, sheetName, 1);
//
//        Object[][] data = new Object[rowCount][colCount];
//
//        for (int i = 0; i < rowCount; i++) {
//            for (int j = 0; j < colCount; j++) {
//                data[i][j] = ExcelUtils.getCellData(filePath, sheetName, i + 1, j);
//            }
//        }
//
//        return data;
//    }
   

//    @DataProvider(name = "driverdata")
//    public static Object[][] getDriverData() throws Exception {
//        String filePath = System.getProperty("user.dir") + "\\TestData\\DriverCredentials.xlsx";
//        String sheetName = "Sheet1";
//
//        int rowCount = ExcelUtils.getRowCount(filePath, sheetName); // total rows including header
//        int colCount = ExcelUtils.getCellCount(filePath, sheetName, 1);
//
//        Object[][] data = new Object[rowCount - 1][colCount]; // skip header row
//
//        for (int i = 1; i < rowCount; i++) { // start from 1 to skip headers
//            for (int j = 0; j < colCount; j++) {
//                data[i - 1][j] = ExcelUtils.getCellData(filePath, sheetName, i, j);
//            }
//        }
//
//        return data;
//    }
//
//    @Test(dataProvider = "driverdata")
//    public void add_driver_test(String img, String name, String phone, String password, String carNumber, String company, String carType, String status, String expectedResult) throws InterruptedException {
//        MyExample driversPage = new MyExample(driver);
//        driversPage.addNewDriver(img, name, phone, password, carNumber, company, carType, status);
//
//        if (expectedResult.equalsIgnoreCase("Invalid")) {
//            try {
//                if (phone.length() != 11) {
//                    Assert.assertEquals(driver.findElement(By.id("errorPhone")).getText(), "Phone must be 11 digits");
//                }
//                if (name == null || name.trim().isEmpty()) {
//                    Assert.assertEquals(driver.findElement(By.id("errorName")).getText(), "Name is required");
//                }
//                if (password.length() < 8 || !password.matches(".*[!@#$%^&*0-9].*")) {
//                    Assert.assertEquals(driver.findElement(By.id("errorPassword")).getText(), "Password too short or weak");
//                }
//                if (company == null || company.trim().isEmpty()) {
//                    Assert.assertEquals(driver.findElement(By.id("errorCompany")).getText(), "Company name is required");
//                }
//                if (carNumber == null || carNumber.trim().isEmpty()) {
//                    Assert.assertEquals(driver.findElement(By.id("errorCar")).getText(), "Car number is required");
//                }
//                // Optional: reset form
//                driver.findElement(By.id("cancelButton")).click(); // Replace with real ID
//            } catch (NoSuchElementException e) {
//                Assert.fail("Expected error message not found");
//            }
//        } else {
//            // Valid input: assert success
//            Assert.assertTrue(driver.getPageSource().contains("Driver added successfully"), "Success message not found!");
//        }
//    }


//
//    @Test(priority = 4, dataProvider = "AddDriverInfo")
//    public void TestAddDriver(String img, String name, String phone, String password, String carNumber, String company, String carType, String status) throws InterruptedException {
//        MyExample addingDriver = new MyExample(driver);
//        addingDriver.addNewDriver(img, name, phone, password, carNumber, company);
//        
//        if (expectedResult.equalsIgnoreCase("Invalid")) {
//            // Perform validation checks
//            if (phone.length() != 11) {
//                Assert.assertEquals(driver.findElement(By.id("errorPhone")).getText(), "Phone must be 11 digits");
//            }
//            if (name == null || name.trim().isEmpty()) {
//                Assert.assertEquals(driver.findElement(By.id("errorName")).getText(), "Name is required");
//            }
//            if (password.length() < 8 || !password.matches(".*[!@#$%^&*0-9].*")) {
//                Assert.assertEquals(driver.findElement(By.id("errorPassword")).getText(), "Password too short or weak");
//            }
//            if (company == null || company.trim().isEmpty()) {
//                Assert.assertEquals(driver.findElement(By.id("errorCompany")).getText(), "Company name is required");
//            }
//            if (carNumber == null || carNumber.trim().isEmpty()) {
//                Assert.assertEquals(driver.findElement(By.id("errorCar")).getText(), "Car number is required");
//            }
//
//            // Click cancel to reset the form
//            driver.findElement(By.id("cancelButton")).click(); // Replace with actual locator
//
//        } else {
//            // Valid case: assert success message or presence in table
//            Assert.assertTrue(driver.getPageSource().contains("Driver added successfully")); // Adjust as needed
//        }   
//    }
    
    
    
   @Test(priority=4)
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
    


      
    // Close the browser
    @Test(priority = 8)
    public void closeBrowser() {
        driver.quit();
    }
}
