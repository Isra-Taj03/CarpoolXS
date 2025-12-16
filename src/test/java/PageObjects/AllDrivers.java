package PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.*;

public class AllDrivers extends BasePage {

	public AllDrivers(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
//***********************  Add_Driver  *****************************************
	  // Elements
    @FindBy(xpath = "//button[text()='Add Drivers']")
//@FindBy(xpath="/html/body/div/div/div[2]/div/div[2]/button")
    WebElement addDriverButton;

    @FindBy(id = "fileInput")
    WebElement fileInput;

    @FindBy(xpath = "/html/body/div/form/div[2]/div/div[1]/div[1]/button")
    WebElement cancelImage;

    @FindBy(xpath = "//input[contains(@placeholder, 'Driver Name')]")
    WebElement driverNameField;

    @FindBy(xpath = "//input[contains(@placeholder, 'Phone Number')]")
    WebElement phoneNumberField;

    @FindBy(xpath = "//input[contains(@placeholder, 'Password')]")
    WebElement passwordField;

    @FindBy(xpath = "/html/body/div/form/div[2]/div/div[1]/div[2]/div[4]/button")
    WebElement eyeIcon;

    @FindBy(xpath = "//input[contains(@placeholder, 'Enter Car Number e.g MH12AB1234')]")
    WebElement carNumberField;

    @FindBy(xpath = "//input[contains(@placeholder, 'Company')]")
    WebElement companyNameField;

    @FindBy(xpath = "/html/body/div/form/div[2]/div/div[1]/div[2]/div[3]/select[1]")
    WebElement carTypeDropdown;

    @FindBy(xpath = "/html/body/div/form/div[2]/div/div[1]/div[2]/div[3]/select[2]")
    WebElement statusDropdown;

    @FindBy(xpath = "//button[@type='submit']\r\n")
    WebElement confirmDriverButton;
    public List<String> addNewDriver(String img, String name, String phone, String password, String carNum, String company,String carType, String status) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Scanner scanner = new Scanner(System.in);
        SoftAssert softAssert = new SoftAssert();
        List<String> validationErrors = new ArrayList<>();
//*************
        // STEP 1: Capture all existing car numbers from the table and store them in a list
        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
        List<String> existingCarNumbers = new ArrayList<>();
        
        // Capture all car numbers in the table and store them in the list
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 4) {
                String carNumber = cells.get(3).getText().trim(); // Assuming car number is in the 5th column (index 4)
                existingCarNumbers.add(carNumber); // Store in lowercase for case-insensitive comparison
            }
        }
        System.out.println("car list"+existingCarNumbers);
//*******************************

        
        
        
        
        
        
// **************************************

        wait.until(ExpectedConditions.elementToBeClickable(addDriverButton)).click();       
        Thread.sleep(1000);

//        fileInput.sendKeys(img);
        System.out.println("*********Wants to update Image (Yes/No):******** ");

	     String userInput= scanner.nextLine();
	     scanner.nextLine();
	     // Read integer input
	     System.out.println("Please Enter yes or no: " );
	     System.out.println("You entered: " + userInput);
	     if (userInput.equalsIgnoreCase("yes")){
	    	 cancelImg.click();
		     fileInput.sendKeys(img);
		     Thread.sleep(1000);
	     }
	     else {
	    	 System.out.println("continue....");
	     }
      Thread.sleep(2000);
        Thread.sleep(1000);
//        wait.until(ExpectedConditions.elementToBeClickable(cancelImage)).click();

        driverNameField.clear();
        phoneNumberField.clear();
        passwordField.clear();
        carNumberField.clear();
        companyNameField.clear();

        driverNameField.sendKeys(name);
        Thread.sleep(1000);
        phoneNumberField.sendKeys(phone);
        Thread.sleep(1000);
        passwordField.sendKeys(password);
        Thread.sleep(1000);
        eyeIcon.click();
        eyeIcon.click();
        carNumberField.sendKeys(carNum);
        
//        String cn="Sedan - "+carNum;
//        System.out.println(cn);
//        carNumberField.sendKeys(carNum);
        
     // STEP 4: Validate the entered car number against the list of existing car numbers
//        if (existingCarNumbers.contains(cn)) {
//            validationErrors.add("CarNumber already exists: " + carNum);
//            System.out.println("Validation failed - duplicate car number: " + carNum);
//            softAssert.fail("cr existed");
//            // If car number exists, close the form without submitting
//            WebElement cancel = driver.findElement(By.xpath("/html/body/div/form/div[2]/div/div[2]/button[1]"));
//            wait.until(ExpectedConditions.elementToBeClickable(cancel)).click();
//            System.out.println("cancel clicked car number");
//            return validationErrors;
//        }
//        else{
//        	carNumberField.sendKeys(carNum);
//        	System.out.println("car number not exited ");
//        }
//    -----

     


//        ----------------------------------------------------------------------------------
//        softAssert.assertTrue(foundName, "Driver Name not found in any row: " + name);
        Thread.sleep(1000);
//        companyNameField.sendKeys(company);
        companyNameField.click();
        companyNameField.sendKeys(company);
        Thread.sleep(2000);
//        String com=company;
//        if(com=="IBEX") {
//        	System.out.println("in if");   
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[contains(text(),'" +com+ "')]"))).click();
//        
//        }
//        else {
//             WebElement cancel = driver.findElement(By.xpath("/html/body/div/form/div[2]/div/div[2]/button[1]"));
//        	 wait.until(ExpectedConditions.elementToBeClickable(cancel)).click();
//        	 softAssert.fail("IncorrectCompanyName");
//        	 softAssert.assertAll();
//        }
        String com = company;  // Company name to verify

     // Get the list of company elements (all <li> inside <ul>)
     List<WebElement> companyList = driver.findElements(By.xpath("//ul/li"));

     boolean companyFound = false;

     for (WebElement companyElement : companyList) {
         String companyName = companyElement.getText().trim();
         if (companyName.equalsIgnoreCase(com)) {   // Match ignoring case
             System.out.println("Company found: " + companyName);
             wait.until(ExpectedConditions.elementToBeClickable(companyElement)).click();
             companyFound = true;
             break;  // No need to check further
         }
     }

     if (!companyFound) {
    	  validationErrors.add("IncorrectCompanyName: " + company);
         System.out.println("Company not found: " + com);
         softAssert.fail("IncorrectCompanyName: " + com);
         WebElement cancel = driver.findElement(By.xpath("/html/body/div/form/div[2]/div/div[2]/button[1]"));
         wait.until(ExpectedConditions.elementToBeClickable(cancel)).click();
         System.out.println("cancel clicked");
         return validationErrors; 
//         softAssert.assertAll();
     }
     Thread.sleep(1000);
        // Dropdowns
        Select carTypeSelect = new Select(carTypeDropdown);
//        for (WebElement option : carTypeSelect.getOptions()) {
//            if (!option.getText().equalsIgnoreCase("Choose Car Type")) {
                carTypeSelect.selectByVisibleText(carType);
//                break;
//            }
//        }
        Thread.sleep(1000);

        Select statusSelect = new Select(statusDropdown);
        statusSelect.selectByVisibleText(status);
        
      confirmDriverButton.click();
//    System.out.println("Submit clicked");
      
      Thread.sleep(1500); // Wait for error/response
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        String fullCarNumber = carType+ " - " + carNum;
        System.out.println("Full Car Number: " + fullCarNumber);
        // STEP 4: Check if car number already exists BEFORE submission
        if (existingCarNumbers.contains(fullCarNumber)) {
            System.out.println("Validation: Duplicate car number detected.");
               
            
            System.out.println("Submit clicked with duplicate car number.");
            Thread.sleep(1000); 
            // Check if the toast message incorrectly shows success
            String toastMsg = getToastMessage();
            Thread.sleep(1000); 
            System.out.println("toast mesg"+toastMsg);
            if (toastMsg != null && toastMsg.contains("Driver Created successfully")) {
            	validationErrors.add("IncorrectCarNumber");
                softAssert.fail("Test Failed: Duplicate car number was allowed - " + carNum);
                
            } else {
                System.out.println("Correct behavior: Toast not shown for duplicate car number.");
                WebElement cancel = driver.findElement(By.xpath("/html/body/div/form/div[2]/div/div[2]/button[1]"));
                wait.until(ExpectedConditions.elementToBeClickable(cancel)).click();
                
            }
            

            
            return validationErrors;
            
            
            
            
           
           
        }
        
        /////

        
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
       
      //*[@id="root"]/section/div[2]/div/div[3]/form/p

        
        
        try {
            List<WebElement> errors = driver.findElements(By.xpath("/html/body/div/form/div[2]/div/div[1]/div[2]//span/p"));
            for (WebElement error : errors) {
                String msg = error.getText().trim();
                if (!msg.isEmpty()) {
                    validationErrors.add(msg);
                }
            }

            if (!validationErrors.isEmpty()) {
                WebElement cancel = driver.findElement(By.xpath("/html/body/div/form/div[2]/div/div[2]/button[1]"));
                wait.until(ExpectedConditions.elementToBeClickable(cancel)).click();
                System.out.println("Cancel clicked due to validation errors.");
            }
        } catch (Exception e) {
            System.out.println("Exception while checking errors: " + e.getMessage());
        }
        
        softAssert.assertAll();
        return validationErrors;  // Empty list means success
       
    }

    public String getToastMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Thread.sleep(2000);
//            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("_rht_toaster")));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
          By.xpath("//div[@role='status' and contains(@class,'go3958317564') and contains(text(),'Driver Created successfully')]")));
            return toast.getText();
        } catch (Exception e) {
            return null;
        }
        
        
    }

// ************************ Search Driver ****************************
  //Locators
    @FindBy(xpath="/html/body/div/div/div[2]/div/div[2]/input")
    WebElement inputField;
    public void SearchDriver(String values) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(inputField));
       
        inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);  // Clear
        inputField.sendKeys(values);
//        inputField.clear();
        Thread.sleep(2000);  // Let the results load
    }
public void clear_fields() {
	inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
}


//    public void SearchDriver(String[] values) throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//    	for (int i = 0; i < values.length; i++) {
//    	    String value = values[i];
//
////    	    wait.until(ExpectedConditions.visibilityOfElementLocated(inputField));
//    	    wait.until(ExpectedConditions.elementToBeClickable(inputField));
//    	    // Use keyboard to clear input
//    	    inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
//
//    	    inputField.sendKeys(value);
//
//    	    Thread.sleep(2000); // Simulate wait
//
//    	    // After last input, clear again
//    	    if (i == values.length - 1) {
//    	    	inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
//    	        wait.until(ExpectedConditions.attributeToBe(inputField, "value", ""));
//    	        break;
//    	    }
//    	}
//    }
// 
    
    
    
// *********************************   Edit Driver ***************************
// ---------------- Locators -------------------- 

//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
////  Updating Driver
 	@FindBy(xpath="/html/body/div/form/div[2]/div/div[2]/button[2]")
 	WebElement updateDriverBtn;
 	@FindBy(xpath="/html/body/div/form/div[2]/div/div[2]/button[1]")
 	WebElement cancelBtn;

 	@FindBy(xpath="/html/body/div/form/div[2]/div/div[1]/div[1]/button")
 	WebElement cancelImg;
 	
 	@FindBy(xpath="/html/body/div/form/div[2]/div/div[1]/div[2]/div[4]/input")
 	WebElement updatePass;
//     ----
 
     public List<String> UpdateDriver(String img, String Name,String PhoneNumber,String password,String carNum, String CompanyName,String carType,String status) throws InterruptedException {
      
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       SoftAssert softAssert = new SoftAssert();
       List<String> validationErrors = new ArrayList<>();
//*************
     //*************
       // STEP 1: Capture all existing car numbers from the table and store them in a list
       List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
       List<String> existingCarNumbers = new ArrayList<>();
       
       // Capture all car numbers in the table and store them in the list
       for (WebElement row : rows) {
           List<WebElement> cells = row.findElements(By.tagName("td"));
           if (cells.size() >= 4) {
               String carNumber = cells.get(3).getText().trim(); // Assuming car number is in the 5th column (index 4)
               existingCarNumbers.add(carNumber); // Store in lowercase for case-insensitive comparison
           }
       }
       System.out.println(existingCarNumbers);
//*******************************
//       // STEP 1: Capture all existing phine from the table and store them in a list
//       List<WebElement> rows1 = driver.findElements(By.xpath("//table/tbody/tr"));
//       List<String> existingPhoneNumber = new ArrayList<>();
//       
//       // Capture all car numbers in the table and store them in the list
//       for (WebElement row : rows1) {
//           List<WebElement> cells = row.findElements(By.tagName("td"));
//           if (cells.size() >= 4) {
//               String phoneNumber = cells.get(2).getText(); // Assuming car number is in the 5th column (index 4)
//               existingPhoneNumber.add(phoneNumber); // Store in lowercase for case-insensitive comparison
//           }
//       }
//       System.out.println("phone list"+existingPhoneNumber);
//*******************************
       Thread.sleep(3000);
       Scanner scanner = new Scanner(System.in); // Create Scanner object
       
       System.out.println("*********Please Enter a Row Number:******** ");

       int rowNum = scanner.nextInt(); // Read integer input


       System.out.println("You entered: " + rowNum);
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[3]/div/div/div/table/tbody/tr["+rowNum+"]/td[9]/div/button[1]"))).click();

//       fileInput.sendKeys(img);
       System.out.println("*********Wants to update Image (Yes/No):******** ");

	     String userInput= scanner.nextLine();
	     scanner.nextLine();
	     // Read integer input
	     System.out.println("Please Enter yes or no: " );
	     System.out.println("You entered: " + userInput);
	     if (userInput.equalsIgnoreCase("yes")){
	    	 cancelImg.click();
		     fileInput.sendKeys(img);
		     Thread.sleep(1000);
	     }
	     else {
	    	 System.out.println("continue....");
	     }
       Thread.sleep(2000);
//       wait.until(ExpectedConditions.elementToBeClickable(cancelImage)).click();
       
       driverNameField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
       driverNameField.sendKeys(Name);
       
       phoneNumberField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
       phoneNumberField.sendKeys(PhoneNumber); 
       
       
       System.out.println("*********Wants to update password (Yes/No):******** ");
       
	     String pass= scanner.nextLine();
	     System.out.println("Please Enter yes or no: " );
	     System.out.println("You entered: " + pass);// Read integer input
	     if (pass.equalsIgnoreCase("yes")) {
	    	 Thread.sleep(1000);
	    	 updatePass.sendKeys(password);
		     Thread.sleep(1000);
	     }
	     else {
	    	 System.out.println("continue....");
	     }
       Thread.sleep(1000);

//       *wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(Password);

       // Click eye icon twice
//       Thread.sleep(1000);
//       eyeIcon.click();
//       Thread.sleep(1000);
//       eyeIcon.click();
       carNumberField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
       carNumberField.sendKeys(carNum);
        

       companyNameField.sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.DELETE);
//       companyNameField.sendKeys(CompanyName);
       companyNameField.click();
       companyNameField.sendKeys(CompanyName);
       Thread.sleep(2000);
       String com=CompanyName;
       // Get the list of company elements (all <li> inside <ul>)
       List<WebElement> companyList = driver.findElements(By.xpath("//ul/li"));

       boolean companyFound = false;

       for (WebElement companyElement : companyList) {
           String companyName = companyElement.getText().trim();
           if (companyName.equalsIgnoreCase(com)) {   // Match ignoring case
               System.out.println("Company found: " + companyName);
               wait.until(ExpectedConditions.elementToBeClickable(companyElement)).click();
               companyFound = true;
               break;  // No need to check further
           }
       }

       if (!companyFound) {
      	  validationErrors.add("IncorrectCompanyName: " + CompanyName);
           System.out.println("Company not found: " + com);
           softAssert.fail("IncorrectCompanyName: " + com);
           WebElement cancel = driver.findElement(By.xpath("/html/body/div/form/div[2]/div/div[2]/button[1]"));
           wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
           System.out.println("comonaynotfooundcancel");
           System.out.println("cancel clicked");
           return validationErrors; 
//           softAssert.assertAll();
       }
       
       Select carTypeSelect = new Select(carTypeDropdown);
//     for (WebElement option : carTypeSelect.getOptions()) {
//         if (!option.getText().equalsIgnoreCase("Choose Car Type")) {
             carTypeSelect.selectByVisibleText(carType);
//             break;
//         }
//     }
     Thread.sleep(1000);

     Select statusSelect = new Select(statusDropdown);
     statusSelect.selectByVisibleText(status);

     wait.until(ExpectedConditions.elementToBeClickable(updateDriverBtn)).click();
//     wait.until(ExpectedConditions.elementToBeClickable(updateDriverBtn)).click();
       //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
       String fullCarNumber = carType+ " - " + carNum;
       System.out.println("Full Car Number: " + fullCarNumber);
       // STEP 4: Check if car number already exists BEFORE submission
       if (existingCarNumbers.contains(fullCarNumber)) {
           System.out.println("Validation: Duplicate car number detected.");
              
           
           System.out.println("Submit clicked with duplicate car number.");
           Thread.sleep(1000); 
           // Check if the toast message incorrectly shows success
           String toastMsg = getToastMessageforupdate();
           Thread.sleep(1000); 
           System.out.println("toast mesg"+toastMsg);
           if (toastMsg != null && toastMsg.contains("Driver updated successfully")) {
           	validationErrors.add("IncorrectCarNumber");
               softAssert.fail("Test Failed: Duplicate car number was allowed - " + carNum);
               
           } else {
               System.out.println("Correct behavior: Toast not shown for duplicate car number.");
               WebElement cancel = driver.findElement(By.xpath("/html/body/div/form/div[2]/div/div[2]/button[1]"));
               System.out.println("toatmsg");
               wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
               
           }
           softAssert.assertAll();
           
           return validationErrors;
           
           
           
           
          
          
       }
       ///
//       String fullPhoneNumber=PhoneNumber;
    // STEP 4: Check if car number already exists BEFORE submission
//       if (existingPhoneNumber.contains(PhoneNumber)) {
//           System.out.println("Validation: Duplicate phone number detected.");
//              
//           
//           System.out.println("Submit clicked with duplicate phone number.");
//           Thread.sleep(1000); 
//           // Check if the toast message incorrectly shows success
//           String toastMsg = getToastMessage();
//           Thread.sleep(1000); 
//           System.out.println("toast mesg"+toastMsg);
//           if (toastMsg != null && toastMsg.contains("Driver updated successfully")) {
//           	validationErrors.add("IncorrectPhoneNumber");
//               softAssert.fail("Test Failed: Duplicate phone number was allowed - " + PhoneNumber);
//               
//           } else {
//               System.out.println("Correct behavior: Toast not shown for duplicate car number.");
//               WebElement cancel = driver.findElement(By.xpath("/html/body/div/form/div[2]/div/div[2]/button[1]"));
//               wait.until(ExpectedConditions.elementToBeClickable(cancel)).click();
//               
//           }
//           
//
//           
//           return validationErrors;
//           
//            }
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
      

       Thread.sleep(3000);
//       	System.out.println("in if");   
//       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[contains(text(),'" +com+ "')]"))).click(); 
//       confirmDriverButton.click();
       softAssert.assertAll();
	return validationErrors; 
   }
     public String getToastMessageforupdate() {
         try {
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
             Thread.sleep(2000);
//             WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("_rht_toaster")));
             WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
           By.xpath("//div[@role='status' and contains(@class,'go3958317564') and contains(text(),'Driver updated successfully')]")));
             return toast.getText();
         } catch (Exception e) {
             return null;
         }
         
         
     }
     
 //  -------------************Delete DRiver***************
//   Delete driver
     @FindBy(xpath="/html/body/div/div/div[3]/div/div/div/table/tbody/tr[1]/td[9]/div/button[2]")
     WebElement DeleteBtn;
     @FindBy(xpath="/html/body/div/div/div[3]/div/div/div/div[1]/div/div/button[2]")
     WebElement clickDelete;
     


     public void deleteDriver() throws InterruptedException {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
         Scanner scanner = new Scanner(System.in); // Create Scanner object
         
         System.out.println("*********Please Enter a Row Number for Deletion :******** ");

         int rowNum = scanner.nextInt(); // Read integer input

      
         System.out.println("You entered: " + rowNum);
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[3]/div/div/div/table/tbody/tr["+rowNum+"]/td[9]/div/button[2]"))).click();
//        	 wait.until(ExpectedConditions.elementToBeClickable(DeleteBtn)).click();
        	wait.until(ExpectedConditions.elementToBeClickable(clickDelete)).click();
        	
        
        	
//        	return actual_message

                // Optionally, verify the message content
             
         }
         public String pop1() {
        	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    		WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(
                     By.xpath("//div[@role='status' and contains(text(),'Driver deleted successfully.')]")
                 ));
     	String actual_message=popup.getText();
     	return actual_message;
     	}
     
     
 //-------************Fetch Driver*****************---
       //fetch Driver
//         @FindBy(xpath="/html/body/div/div/div[3]/div/div/div/table/tbody/tr[2]/td[9]/div/button[3]")
//         WebElement fetchBtn;
     public void fetchDriverLocation() {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
         Scanner scanner = new Scanner(System.in); // Create Scanner object
         System.out.println("*********Please Enter a Row Number for fetch:******** ");

         int rowNum = scanner.nextInt(); // Read integer input

      
         System.out.println("You entered: " + rowNum);
        	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[3]/div/div/div/table/tbody/tr["+rowNum+"]/td[9]/div/button[3]"))).click();
     }
     @FindBy(xpath = "//*[contains(text(), 'Could not fetch location')]")
     WebElement locationErrorToast;

     @FindBy(xpath = "//*[contains(text(), 'Location fetched successfully')]")
     WebElement locationSuccessToast;

     public boolean isLocationErrorDisplayed() {
         try {
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
             return wait.until(ExpectedConditions.visibilityOf(locationErrorToast)).isDisplayed();
         } catch (TimeoutException e) {
             return false;
         }
     }

     public boolean isLocationSuccessDisplayed() {
         try {
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
             return wait.until(ExpectedConditions.visibilityOf(locationSuccessToast)).isDisplayed();
         } catch (TimeoutException e) {
             return false;
         }
     }
     
   //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
     public List<String> foundDuplicates() throws InterruptedException{
//    	  List<String> validationErrors = new ArrayList<>();
//
//    	    List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
//
//    	    Map<String, Integer> carNumberCount = new HashMap<>();
//    	    Map<String, Integer> phoneNumberCount = new HashMap<>();
//
//    	    for (WebElement row : rows) {
//    	        List<WebElement> cells = row.findElements(By.tagName("td"));
//
//    	        if (cells.size() >= 4) {
//    	            String carNumber = cells.get(3).getText().trim().toLowerCase();
//    	            String phoneNumber = cells.get(2).getText().trim();
//    	            
//    	           
//    	            
//    	            System.out.println("Row - Car: " + carNumber + ", Phone: " + phoneNumber);
//
//    	            carNumberCount.put(carNumber, carNumberCount.getOrDefault(carNumber, 0) + 1);
//    	            phoneNumberCount.put(phoneNumber, phoneNumberCount.getOrDefault(phoneNumber, 0) + 1);
//    	        }
//    	    }
//    	    System.out.println("Car Number Map: " + carNumberCount);
//    	    System.out.println("Phone Number Map: " + phoneNumberCount);
//    	    // Check for duplicates in car numbers
//    	    for (Map.Entry<String, Integer> entry : carNumberCount.entrySet()) {
//    	        if (entry.getValue() > 1) {
//    	            validationErrors.add("Duplicate car number found: " + entry.getKey());
//    	        }
//    	    }
//
//    	    // Check for duplicates in phone numbers
//    	    for (Map.Entry<String, Integer> entry : phoneNumberCount.entrySet()) {
//    	        if (entry.getValue() > 1) {
//    	            validationErrors.add("Duplicate phone number found: " + entry.getKey());
//    	        }
//    	    }
//
//    	    return validationErrors;
//        %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
    		    List<String> validationErrors = new ArrayList<>();
    		     SoftAssert softAssert = new SoftAssert();
    		    List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

    		    Map<String, Integer> carNumberCount = new HashMap<>();
    		    Map<String, Integer> phoneNumberCount = new HashMap<>();

    		    for (WebElement row : rows) {
    		        if (!row.isDisplayed()) continue;

    		        List<WebElement> cells = row.findElements(By.tagName("td"));

    		        if (cells.size() >= 4) {
    		            // Adjust column indexes if needed
    		            String phoneNumberRaw = cells.get(2).getText();    // Column 3 (index 2)
    		            String carNumberRaw = cells.get(3).getText();       // Column 4 (index 3)
    		            if (phoneNumberRaw == null || carNumberRaw == null ||
    		                    phoneNumberRaw.trim().isEmpty() || carNumberRaw.trim().isEmpty()) {
    		                    System.out.println("Skipping row due to null or empty values.");
    		                    continue;}
    		            // Normalize
    		            String phoneNumber = phoneNumberRaw.replaceAll("[^\\d]", ""); // Keep only digits
    		            String carNumber = carNumberRaw.toLowerCase().replaceAll("\\s+", "");

    		            System.out.println("Checking Row → Phone: " + phoneNumber + ", Car: " + carNumber);

    		            phoneNumberCount.put(phoneNumber, phoneNumberCount.getOrDefault(phoneNumber, 0) + 1);
    		            carNumberCount.put(carNumber, carNumberCount.getOrDefault(carNumber, 0) + 1);
    		        }
    		    }

    		    // Logging maps
    		    System.out.println("Phone Map: " + phoneNumberCount);
    		    System.out.println("Car Map: " + carNumberCount);

    		    // Collect duplicates
    		    for (Map.Entry<String, Integer> entry : phoneNumberCount.entrySet()) {
    		        if (entry.getValue() > 1) {
    		            validationErrors.add("Duplicate phone number found: " + entry.getKey());
    		           
    		        }
    		    }

    		    for (Map.Entry<String, Integer> entry : carNumberCount.entrySet()) {
    		        if (entry.getValue() > 1) {
    		            validationErrors.add("Duplicate car number found: " + entry.getKey());
    		           
    		        }
    		    }
    	
    		    return validationErrors;
    		}
     }

      
