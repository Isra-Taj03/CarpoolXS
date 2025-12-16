package PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class AllRoutes extends BasePage {

	public AllRoutes(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//Locators
	@FindBy(xpath = "/html/body/div/div/nav/div[1]/div[2]/button[2]")
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
		    SoftAssert softAssert = new SoftAssert();
//	    wait.until(ExpectedConditions.elementToBeClickable(allRoutesBtn)).click();
		    WebElement allTenantsBtn =  wait.until(ExpectedConditions.elementToBeClickable(allRoutesBtn));
		    // Step 2: Check if it's already selected (e.g., via class "active")
		    String buttonClass = allTenantsBtn.getAttribute("class");

		    if (!buttonClass.contains("text-[#00AA2F]")) {
		        allTenantsBtn.click();
		        System.out.println("✅ Clicked 'All Routes' tab.");
		        Thread.sleep(1000);  // Optional: wait for the page to load (better with WebDriverWait)
		    } else {
		        System.out.println("⏭️ 'All Routes' is already active. Skipping click.");
		    }
	    Thread.sleep(2000);
	    wait.until(ExpectedConditions.elementToBeClickable(addRouteBtn)).click();

	    assignDriverInput.click();
	    assignDriverInput.sendKeys(driverName);
	   Thread.sleep(2000);
	   String enteredDriverName = assignDriverInput.getAttribute("value");
	    if (enteredDriverName.matches("^[a-zA-Z\\s]+$")) {
	        System.out.println("Driver name field is valid, takes only charcters.");
	        // You can add validation logic here
	    } else {
	    	validationErrors.add("Driver name field is invalid, should take only characters as input");
//	        System.out.println("driver name is invalid as it takes numbers .");
//	        return validationErrors;
	    }
	//   
	   
	    	try {  
	    		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[contains(text(),'" + driverName+ "')]"))).click();  		 
	     }
	     catch(Exception e) {
	    	 WebElement cancel = driver.findElement(By.xpath("/html/body/div/form/div[2]/div/div[2]/button[1]"));
	         wait.until(ExpectedConditions.elementToBeClickable(cancel)).click();	         
	    	}
     
	     Thread.sleep(1000);
	   
	   
	   
//	   ----------------------------------------------
	   routeNameInput.clear();
	   passengerNameInput.clear();;
	   phoneNumberInput.clear();
	   addressInput.clear();
	   pickupLocationInput.clear();
	   dropoffLocationInput.clear();
	   
	    routeNameInput.sendKeys(routeName);
	    passengerNameInput.sendKeys(passengerName);
	    String enteredName = passengerNameInput.getAttribute("value");
	    if (enteredName.matches("^[a-zA-Z\\s]+$")) {
	        System.out.println("Passenger Name field is  valid, contains only cahracters.");
	        // You can add validation logic here
	    } else {
	    	validationErrors.add("Passenger Name field is  invalid,should take only characters as input");
//	        System.out.println("Passenger name is invalid numbers are there.");
//	        return validationErrors;
	    }
	    phoneNumberInput.sendKeys(phone);
	    String enteredPhone =  phoneNumberInput.getAttribute("value");
	    if (enteredPhone.matches("\\d+")) {
	        System.out.println(" Phone Number field is valid, contains only numbers.");
	        // You can add validation logic here
	    } else {
	    	validationErrors.add("Phone Number field is  invalid,should take only numbers as input");
//	        System.out.println("phone field is invalid as it takes charcaters.");
	        }
	    
	    addressInput.sendKeys(address);
	    pickupLocationInput.sendKeys(pickupLoc);
	    String enteredpickupLoc =  pickupLocationInput.getAttribute("value");
	    if (enteredpickupLoc.matches("[-+]?\\\\d*\\\\.\\\\d+|[-+]?\\\\d+\\\\.\\\\d*|[-+]?\\\\d+")) {
	        System.out.println(" pick_up_Location field is valid, contains only numbers.");
	        // You can add validation logic here
	    } else {
	    	validationErrors.add("pick_up_Location field is  invalid,should take only numbers as input");
//	        System.out.println("phone field is invalid as it takes charcaters.");
	        }
	    dropoffLocationInput.sendKeys(dropoffLoc);
	    String entereddropoffLoc =  dropoffLocationInput.getAttribute("value");
	    if (entereddropoffLoc.matches("[-+]?\\\\d*\\\\.\\\\d+|[-+]?\\\\d+\\\\.\\\\d*|[-+]?\\\\d+")) {
	        System.out.println("  drop_off_Location field is valid, contains only numbers.");
	        // You can add validation logic here
	    } else {
	    	validationErrors.add("drop_off_Location field is  invalid,should take only numbers as input");
//	        System.out.println("phone field is invalid as it takes charcaters.");
	        }

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
	    System.out.println(validationErrors);
	    return validationErrors; 
	}
    
    public String getToastMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Thread.sleep(2000);
//            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("_rht_toaster")));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
          By.xpath("//div[@role='status' and contains(@class,'go3958317564') and contains(text(),'Route created successfully.')]")));
            return toast.getText();
        } catch (Exception e) {
            return null;
        }
    }
//***************** Edit Route *********************************************
    
//------------------ Update Route -------------------

    // Locators

//    @FindBy(xpath="//tbody/tr[1]/td[6]/div[1]/button[1]") 
//    WebElement editBtn;//tbody/tr[1]/td[6]/div[1]/button[1]//*[name()='svg']//*[name()='path' and contains(@d,'M12 3H5a2 ')]

    // Actions
//    String driverName, String routeName, String passengerName,
//    String phone, String address, String pickupLoc, String dropoffLoc
    public  List<String> UpdateRoute(String driverName, String routeName, String passengerName,String phone, String address, String pickupLoc, String dropoffLoc) throws InterruptedException {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	 wait.until(ExpectedConditions.elementToBeClickable(allRoutesBtn)).click();
// 	    WebElement allTenantsBtn =  wait.until(ExpectedConditions.elementToBeClickable(allRoutesBtn));
//	    // Step 2: Check if it's already selected (e.g., via class "active")
//	    String buttonClass = allTenantsBtn.getAttribute("class");
//
//	    if (!buttonClass.contains("text-[#00AA2F]")) {
//	        allTenantsBtn.click();
//	        System.out.println("✅ Clicked 'All Routes' tab.");
//	        Thread.sleep(1000);  // Optional: wait for the page to load (better with WebDriverWait)
//	    } else {
//	        System.out.println("⏭️ 'All Routes' is already active. Skipping click.");
//	    }
    	 Scanner scanner = new Scanner(System.in); // Create Scanner object
         
         System.out.println("*********Please Enter a Row Number for updating a Route :******** ");

         int rowNum = scanner.nextInt(); // Read integer input

      
         System.out.println("You entered: " + rowNum);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr["+rowNum+"]/td[6]/div[1]/button[1]"))).click();
        List<String> validationErrors = new ArrayList<>();
    	
//        Alert alert = driver.switchTo().alert();
//        alert.accept();
       assignDriverInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
//       assignDriverInput.clear();

       assignDriverInput.sendKeys(driverName);
//       Alert alert = driver.switchTo().alert();
//       alert.accept();
     Thread.sleep(1000);
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[contains(text(),'" + driverName+ "')]"))).click();
//       
  
//
       
        // Optional: Verify assignment or fetch the driver value if needed
       
          routeNameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
          routeNameInput.sendKeys(routeName);
         
          
        passengerNameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        passengerNameInput.sendKeys(passengerName);
//        
        phoneNumberInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);;
        phoneNumberInput.sendKeys(phone);
//        
        addressInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
         addressInput.sendKeys(address);
//        
        pickupLocationInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        pickupLocationInput.sendKeys(pickupLoc);
//        
        dropoffLocationInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        dropoffLocationInput.sendKeys(dropoffLoc);

//        confirmBtn.click(); // Or use wait.until to be extra safe
          confirmBtn.click();
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
 
    public String getUpdateToastMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Thread.sleep(2000);
//            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("_rht_toaster")));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
          By.xpath("//div[@role='status' and contains(@class,'go3958317564') and contains(text(),'Route updated successfully.')]")));
            return toast.getText();
        } catch (Exception e) {
            return null;
        }
        
    }
//    public static  void enterPassenger(int index, String name, String phone, String address, String pickup, String dropoff) {
//        // Assuming each passenger section is a <div> with a unique order on the page
////        String baseDivXPath = "(//div[contains(@class, 'passenger-section')])[" + (index + 1) + "]";
//
////        WebElement nameInput = driver.findElement(By.xpath(baseDivXPath + "//input[@placeholder='Passenger Name']"));
////        WebElement phoneInput = driver.findElement(By.xpath(baseDivXPath + "//input[@placeholder='Phone Number']"));
////        WebElement addressInput = driver.findElement(By.xpath(baseDivXPath + "//input[@placeholder='Address']"));
////        WebElement pickupInput = driver.findElement(By.xpath(baseDivXPath + "//input[@placeholder='Pick up location']"));
////        WebElement dropoffInput = driver.findElement(By.xpath(baseDivXPath + "//input[@placeholder='Drop Off location']"));
//
//    	 passengerNameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, name);
//    	 phoneNumberInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, phone);
//        addressInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, address);
//        pickupLocationInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, pickup);
//        dropoffLocationInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, dropoff);
//        
//    }
//------------------  Delete Route -------------------------
//    @FindBy(xpath="/html/body/div/div/div[3]/div/div/div/table/tbody/tr[1]/td[6]/div/button[2]")
//    WebElement DeleteBtn;
    
    @FindBy(xpath="/html/body/div/div/div[3]/div/div/div/div[1]/div/div/button[2]")
    WebElement clickDelete;
    
    
    @FindBy(xpath="/html/body/div/div/div[3]/div/div/div/div[1]/div/div/button[1]")
    WebElement cancelDelete;
    
// Action
   public void deleteRoute() throws InterruptedException {
	   

	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//	 wait.until(ExpectedConditions.elementToBeClickable(allRoutesBtn)).click();
	    WebElement AllRoutesBtn =  wait.until(ExpectedConditions.elementToBeClickable(allRoutesBtn));
	    // Step 2: Check if it's already selected (e.g., via class "active")
	    String buttonClass = AllRoutesBtn.getAttribute("class");

	    if (!buttonClass.contains("text-[#00AA2F]")) {
	    	AllRoutesBtn.click();
	        System.out.println("✅ Clicked 'All Routes' tab.");
	        Thread.sleep(1000);  // Optional: wait for the page to load (better with WebDriverWait)
	    } else {
	        System.out.println("⏭️ 'All Routes' is already active. Skipping click.");
	    }
	 Scanner scanner = new Scanner(System.in); // Create Scanner object
     
     System.out.println("*********Please Enter a Row Number for Route Deletion :******** ");

     int rowNum = scanner.nextInt(); // Read integer input

  
     System.out.println("You entered: " + rowNum);
     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[3]/div/div/div/table/tbody/tr["+rowNum+"]/td[6]/div/button[2]"))).click();
     
 
    	
//  	 wait.until(ExpectedConditions.elementToBeClickable(DeleteBtn)).click();
  	 wait.until(ExpectedConditions.elementToBeClickable(clickDelete)).click();
	   
   }
   public String delete_Route_popUp() throws InterruptedException {
  	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    WebElement AllRoutesBtn =  wait.until(ExpectedConditions.elementToBeClickable(allRoutesBtn));
	    // Step 2: Check if it's already selected (e.g., via class "active")
	    String buttonClass = AllRoutesBtn.getAttribute("class");

	    if (!buttonClass.contains("text-[#00AA2F]")) {
	    	AllRoutesBtn.click();
	        System.out.println("✅ Clicked 'All Routes' tab.");
	        Thread.sleep(1000);  // Optional: wait for the page to load (better with WebDriverWait)
	    } else {
	        System.out.println("⏭️ 'All Routes' is already active. Skipping click.");
	    }
		WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(
               By.xpath("//div[@role='status' and contains(text(),'Route Deleted Successfully!')]")
           ));
	String actual_message=popup.getText();
	return actual_message;
	}
//-^^^^^^^^^^^^^^^^^^^^^^^^^^^^SearchROUTE^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
// ************************ ****************************
  //Locators
    @FindBy(xpath="/html/body/div/div/div[2]/div/div[2]/input")
    WebElement inputField;
    public void SearchRoute(String values) throws InterruptedException {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	 wait.until(ExpectedConditions.elementToBeClickable(allRoutesBtn)).click();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(inputField));
       
        inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);  // Clear
        inputField.sendKeys(values);
//        inputField.clear();
        Thread.sleep(2000);  // Let the results load
    }
public void clear_fields() {
	inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
}
   
}
