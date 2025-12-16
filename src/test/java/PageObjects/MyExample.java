package PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyExample extends BasePage {
	
public MyExample(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
//***********************  Add_Driver  *****************************************
	  // Elements
    @FindBy(xpath = "//button[text()='Add drivers']")
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
    public List<String> addNewDriver(String img, String name, String phone, String password, String carNum, String company) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        List<String> validationErrors = new ArrayList<>();

        wait.until(ExpectedConditions.elementToBeClickable(addDriverButton)).click();       
        Thread.sleep(1000);

        fileInput.sendKeys(img);
        wait.until(ExpectedConditions.elementToBeClickable(cancelImage)).click();

        driverNameField.clear();
        phoneNumberField.clear();
        passwordField.clear();
        carNumberField.clear();
        companyNameField.clear();

        driverNameField.sendKeys(name);
        phoneNumberField.sendKeys(phone);
        passwordField.sendKeys(password);
        eyeIcon.click();
        eyeIcon.click();
        carNumberField.sendKeys(carNum);
        companyNameField.sendKeys(company);

        // Dropdowns
        Select carTypeSelect = new Select(carTypeDropdown);
        for (WebElement option : carTypeSelect.getOptions()) {
            if (!option.getText().equalsIgnoreCase("Choose Car Type")) {
                carTypeSelect.selectByVisibleText(option.getText());
                break;
            }
        }

        Select statusSelect = new Select(statusDropdown);
        statusSelect.selectByVisibleText("Active");

        confirmDriverButton.click();
        System.out.println("Submit clicked");

        Thread.sleep(1500); // Wait for error/response

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

        return validationErrors;  // Empty list means success
    }
    // Action Method
//    public boolean addNewDriver(String img, String name, String phone, String password, String carNum, String company) throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//        
//        wait.until(ExpectedConditions.elementToBeClickable(addDriverButton)).click();       
//        Thread.sleep(1000);
//
//        fileInput.sendKeys(img);
//        wait.until(ExpectedConditions.elementToBeClickable(cancelImage)).click();
//
//        driverNameField.clear();
//        phoneNumberField.clear();
//        passwordField.clear();
//        carNumberField.clear();
//        companyNameField.clear();
//
//        driverNameField.sendKeys(name);
//        phoneNumberField.sendKeys(phone);
//        passwordField.sendKeys(password);
//        eyeIcon.click();
//        eyeIcon.click();
//        carNumberField.sendKeys(carNum);
//        companyNameField.sendKeys(company);
//
//        // Dropdowns
//        Select carTypeSelect = new Select(carTypeDropdown);
//        for (WebElement option : carTypeSelect.getOptions()) {
//            if (!option.getText().equalsIgnoreCase("Choose Car Type")) {
//                carTypeSelect.selectByVisibleText(option.getText());
//                break;
//            }
//        }
//
//        Select statusSelect = new Select(statusDropdown);
//        statusSelect.selectByVisibleText("Active");
//
//        confirmDriverButton.click();
//        System.out.println(" submit clicked");
//
//        Thread.sleep(1500); // Wait for response
//
//        // Check for validation errors
//        try {
//            List<WebElement> errors = driver.findElements(By.xpath("/html/body/div/form/div[2]/div/div[1]/div[2]//span/p"));
//            System.out.println(errors);	
//            if (!errors.isEmpty()) {
////                WebElement cancel = driver.findElement(By.xpath("//button[text()='Cancel']"));
//            	
//            	WebElement cancel = driver.findElement(By.xpath("/html/body/div/form/div[2]/div/div[2]/button[1]"));
////                cancel.click(); // Close modal
//                wait.until(ExpectedConditions.elementToBeClickable(cancel)).click();    
//               
//                System.out.println("cancle clicked");
//                return false;   // Indicate failure to test
//            }
//        } catch (Exception e) {
//            // No error found
//        	System.out.println("Exception while handling errors: " + e.getMessage());        }
//
//        return true; // Indicate success
//    }
    
    public String getToastMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Thread.sleep(2000);
//            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("_rht_toaster")));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
          By.xpath("//div[@role='status' and contains(@class,'go3958317564') and contains(text(),'Driver added successfully')]")));
            return toast.getText();
        } catch (Exception e) {
            return null;
        }
    }


//    public void addNewDriver(String img, String Name,String PhoneNumber,String Password,String CarNumber, String CompanyName) throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        wait.until(ExpectedConditions.elementToBeClickable(addDriverButton)).click();
//
//        fileInput.sendKeys(img);
//        wait.until(ExpectedConditions.elementToBeClickable(cancelImage)).click();
//
//
//      Thread.sleep(5000);
//
//
//      driverNameField.sendKeys(Name);
//      phoneNumberField.sendKeys(PhoneNumber); 
//
//      wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(Password);
//
//      // Click eye icon twice
//      Thread.sleep(1000);
//      eyeIcon.click();
//      Thread.sleep(1000);
//      eyeIcon.click();
//
//      carNumberField.sendKeys(CarNumber);
//
//      // Select car type
//      Select carTypeSelect = new Select(carTypeDropdown);
//      List<WebElement> carTypes = carTypeSelect.getOptions();
//      for (WebElement option : carTypes) {
//          if (!option.getText().equalsIgnoreCase("Choose Car Type")) {
//              carTypeSelect.selectByVisibleText(option.getText());
//              System.out.println("Selected Car Type: " + option.getText());
//              Thread.sleep(1000);
//          }
//      }
//
//      // Select status
//     Select statusSelect = new Select(statusDropdown);
//     List<WebElement> statusOptions = statusSelect.getOptions();
//     for (WebElement option : statusOptions) {
//     statusSelect.selectByVisibleText(option.getText());
//          System.out.println("Selected Status: " + option.getText());
//         Thread.sleep(1000);
//      }
////  
//      companyNameField.sendKeys(CompanyName);
//     
//      wait.until(ExpectedConditions.elementToBeClickable(confirmDriverButton)).click();
//  }
//


// ************************ Search Driver ****************************
  //Locators
    @FindBy(xpath="/html/body/div/div/div[2]/div/div[2]/input")
    WebElement inputField;
    public void SearchDriver(String value) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(inputField));
        inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);  // Clear
        inputField.sendKeys(value);
        inputField.clear();
        Thread.sleep(2000);  // Let the results load
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
    
//  Updating Driver
 	@FindBy(xpath="/html/body/div/div/div[3]/div/div/div/table/tbody/tr[1]/td[9]/div/button[1]")
 	WebElement editDriverBtn;

//   Delete driver
     @FindBy(xpath="/html/body/div/div/div[3]/div/div/div/table/tbody/tr[1]/td[9]/div/button[2]")
     WebElement DeleteBtn;
     @FindBy(xpath="/html/body/div/div/div[3]/div/div/div/div[1]/div/div/button[2]")
     WebElement clickDelete;
     
//fetch Driver
     @FindBy(xpath="/html/body/div/div/div[3]/div/div/div/table/tbody/tr[1]/td[9]/div/button[3]")
     WebElement fetchBtn;
//     ----
     public void UpdateDriver(String img, String Name,String PhoneNumber,String CarNumber, String CompanyName) throws InterruptedException {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       Thread.sleep(5000);
       wait.until(ExpectedConditions.elementToBeClickable(editDriverBtn)).click();

       fileInput.sendKeys(img);
       wait.until(ExpectedConditions.elementToBeClickable(cancelImage)).click();
       
       driverNameField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
       driverNameField.sendKeys(Name);
       
       phoneNumberField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
       phoneNumberField.sendKeys(PhoneNumber); 

//       *wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(Password);

       // Click eye icon twice
//       Thread.sleep(1000);
//       eyeIcon.click();
//       Thread.sleep(1000);
//       eyeIcon.click();
       carNumberField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
       carNumberField.sendKeys(CarNumber);
        
       // Select car type
       Select carTypeSelect = new Select(carTypeDropdown);
       List<WebElement> carTypes = carTypeSelect.getOptions();
       for (WebElement option : carTypes) {
           if (!option.getText().equalsIgnoreCase("Choose Car Type")) {
               carTypeSelect.selectByVisibleText(option.getText());
               System.out.println("Selected Car Type: " + option.getText());
               Thread.sleep(1000);
           }
       }

       // Select status
       Select statusSelect = new Select(statusDropdown);
       List<WebElement> statusOptions = statusSelect.getOptions();
       for (WebElement option : statusOptions) {
           statusSelect.selectByVisibleText(option.getText());
           System.out.println("Selected Status: " + option.getText());
           Thread.sleep(1000);
       }
       
       companyNameField.sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.DELETE);
       companyNameField.sendKeys(CompanyName);
       confirmDriverButton.click();
   }
     
 //  -------------
     public void deleteDriver() throws InterruptedException {
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	 wait.until(ExpectedConditions.elementToBeClickable(DeleteBtn)).click();
    	wait.until(ExpectedConditions.elementToBeClickable(clickDelete)).click();
    	
    
    	
//    	return actual_message

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
     
     
 //-------Fetch Driver---
     public void fetchDriverLocation() {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        	 wait.until(ExpectedConditions.elementToBeClickable(fetchBtn)).click();
     }


//Add Routes
//Locators
@FindBy(xpath = "//button[text()='All Routes']")
WebElement allRoutesBtn;

//@FindBy(xpath = "//button[text()='Add Routes']")
//WebElement addRouteBtn;
@FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/button")
WebElement addRouteBtn;

@FindBy(xpath = "/html/body/div/form/div[2]/div/div[1]/div[1]/div[2]/input[1]")
WebElement assignDriverInput;

//@FindBy(xpath="//li[text()='Kashif']")
//div[@class= 'cursor-pointer']/descendant::li[text() = '" + text + "']
///html/body/div/form/div[2]/div/div[1]/div[1]/div[2]/ul/li[1]
//WebElement selectedDriverli;

@FindBy(xpath = "//input[@placeholder='Route Name']")
WebElement routeNameInput;

@FindBy(xpath = "//input[@placeholder='Passenger Name']")
WebElement passengerNameInput;

@FindBy(xpath = "//input[@placeholder='Phone Number']")
WebElement phoneNumberInput;

@FindBy(xpath = "//input[@placeholder='Address']")
WebElement addressInput;

@FindBy(xpath = "//input[@placeholder='Pick up location']")
WebElement pickupLocationInput;

@FindBy(xpath = "//input[@placeholder='Drop Off location']")
WebElement dropoffLocationInput;

@FindBy(xpath = "/html/body/div/form/div[2]/div/div[2]/button[1]")
WebElement cancelBtn;

@FindBy(xpath = "/html/body/div/form/div[2]/div/div[2]/button[2]")
WebElement confirmBtn;



// Actions
public List<String> addNewRoute(String driverName, String routeName, String passengerName,
                        String phone, String address, String pickupLoc, String dropoffLoc) throws InterruptedException {
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    List<String> validationErrors = new ArrayList<>();
    wait.until(ExpectedConditions.elementToBeClickable(allRoutesBtn)).click();
    Thread.sleep(2000);
    wait.until(ExpectedConditions.elementToBeClickable(addRouteBtn)).click();

   assignDriverInput.sendKeys(driverName);
//   

   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[contains(text(),'" + driverName+ "')]"))).click();
    // Optional: Verify assignment or fetch the driver value if needed
    
   
   routeNameInput.clear();
   passengerNameInput.clear();;
   phoneNumberInput.clear();
   addressInput.clear();
   pickupLocationInput.clear();
   dropoffLocationInput.clear();
   
    routeNameInput.sendKeys(routeName);
    passengerNameInput.sendKeys(passengerName);
    phoneNumberInput.sendKeys(phone);
    addressInput.sendKeys(address);
    pickupLocationInput.sendKeys(pickupLoc);
    dropoffLocationInput.sendKeys(dropoffLoc);

    confirmBtn.click(); // Or use wait.until to be extra safe
    System.out.println("Submit clicked");
    try {
        List<WebElement> errors = driver.findElements(By.xpath("/html/body/div/form/div[2]/div/div[1]/div[2]//p"));
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

    return validationErrors; 
}
//------------------ Update Route -------------------

// Locators

@FindBy(xpath="//tbody/tr[1]/td[6]/div[1]/button[1]") 
WebElement editBtn;

public void UpdateRoute(String driverName, String routeName) throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
 
wait.until(ExpectedConditions.elementToBeClickable(allRoutesBtn)).click();
Thread.sleep(2000);
    wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
    

    assignDriverInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
    assignDriverInput.sendKeys(driverName);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[contains(text(),'" + driverName + "')]"))).click();

    routeNameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
    routeNameInput.sendKeys(routeName);

    // 🚫 REMOVE passenger, phone, address, pickup/dropoff fields here
    // ✅ You'll handle them using enterPassenger() below in the test case

    confirmBtn.click();
}
public void enterPassenger(int index, String name, String phone, String address, String pickup, String dropoff) {
//    String baseDivXPath = "(//div[contains(@class, 'passenger-section')])[" + (index + 1) + "]";


	

    WebElement nameInput = driver.findElement(By.xpath("//input[@placeholder='Passenger Name']"));
    WebElement phoneInput = driver.findElement(By.xpath("//input[@placeholder='Phone Number']"));
    WebElement addressInput = driver.findElement(By.xpath("//input[@placeholder='Address']"));
    WebElement pickupInput = driver.findElement(By.xpath("//input[@placeholder='Pick up location']"));
    WebElement dropoffInput = driver.findElement(By.xpath("//input[@placeholder='Drop Off location']"));

    nameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, name);
    phoneInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, phone);
    addressInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, address);
    pickupInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, pickup);
    dropoffInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, dropoff);
}

}
