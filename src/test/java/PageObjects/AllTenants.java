package PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class AllTenants extends BasePage {

public AllTenants(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

//-----Search Tenant -------
	// ************************ Search Tnanat****************************
	  //Locators
	    @FindBy(xpath="/html/body/div/div/div[2]/div/div[2]/input")
	    WebElement inputField;
//	    @FindBy(xpath = "//button[text()='All Tenants']")
	    @FindBy(xpath="/html/body/div/div/nav/div[1]/div[2]/button[3]")
	    WebElement AllTenanats;
	    public void SearchTenant(String values) throws InterruptedException {
	    	 
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	        wait.until(ExpectedConditions.elementToBeClickable(AllTenanats)).click();
	        WebElement allTenantsBtn =  wait.until(ExpectedConditions.elementToBeClickable(AllTenanats));
	        // Step 2: Check if it's already selected (e.g., via class "active")
	        String buttonClass = allTenantsBtn.getAttribute("class");

	        if (!buttonClass.contains("text-[#00AA2F]")) {
	            allTenantsBtn.click();
	            System.out.println("✅ Clicked 'All Tenants' tab.");
	            Thread.sleep(1000);  // Optional: wait for the page to load (better with WebDriverWait)
	        } else {
	            System.out.println("⏭️ 'All Tenants' is already active. Skipping click.");
	        }
	        wait.until(ExpectedConditions.elementToBeClickable(inputField));
	       
	        inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);  // Clear
	        inputField.sendKeys(values);
//	        inputField.clear();
	        Thread.sleep(2000);  // Let the results load
	    }
	public void clear_fields() {
		inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
	}
	
//-----------------------------------AddTenant-------------------------------------
//     @FindBy(xpath = "//button[text()=' Add Tenant']")
     @FindBy(xpath="/html/body/div/div/div[2]/div/div[2]/button")
     WebElement AddTenant;
    
     @FindBy(id = "fileInput")
     WebElement fileInput;
   
     @FindBy(xpath = "//input[contains(@placeholder, 'User Name')]")
     WebElement UserName;
     
     @FindBy(xpath = "//input[contains(@placeholder, 'Phone Number')]")
     WebElement PhoneNumber;
	
     @FindBy(xpath = "//input[contains(@placeholder, 'Password')]")
     WebElement Password;
     @FindBy(xpath = "//input[contains(@placeholder, 'Company Name')]")
     WebElement companyName;
     
     @FindBy(xpath = "//button[text()='Cancel']")
     WebElement cancelBtn;
//     @FindBy(xpath = "//button[text()='Add Tenant']")
     @FindBy(xpath = "/html/body/div/form/div[2]/div/div[2]/button[2]")
     WebElement ConfirmBtn;
	 public List<String> addTenants(String img,String name,String password,String phone, String compName) throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	     
	     
		 SoftAssert softAssert = new SoftAssert();
	     List<String> validationErrors = new ArrayList<>();
	     
//	     wait.until(ExpectedConditions.elementToBeClickable(AllTenanats)).click();
	     WebElement allTenantsBtn =  wait.until(ExpectedConditions.elementToBeClickable(AllTenanats));
	     // Step 2: Check if it's already selected (e.g., via class "active")
	     String buttonClass = allTenantsBtn.getAttribute("class");

	     if (!buttonClass.contains("text-[#00AA2F]")) {
	         allTenantsBtn.click();
	         System.out.println("✅ Clicked 'All Tenants' tab.");
	         Thread.sleep(1000);  // Optional: wait for the page to load (better with WebDriverWait)
	     } else {
	         System.out.println("⏭️ 'All Tenants' is already active. Skipping click.");
	     }
	   
	     wait.until(ExpectedConditions.elementToBeClickable(AddTenant)).click();	
	     Thread.sleep(1000);
//	     wait.until(ExpectedConditions.visibilityOf(fileInput)).sendKeys(img);
	     fileInput.sendKeys(img);
	     Thread.sleep(1000);

	     UserName.clear();
	     Password.clear();
	     companyName.clear();
	     PhoneNumber.clear();

         UserName.sendKeys(name);
         String user_name= UserName.getAttribute("value");
	    	if (user_name.matches("^[a-zA-Z\\s]+$")) {
		        System.out.println("UserName  field is valid, takes only charcters.");
		        // You can add validation logic here
		    } else {
		    	validationErrors.add("UserName field is invalid, should take only characters as input");
//		    	 System.out.println("UserName field is invalid, should take only characters as input");
//		        return validationErrors;
		    	
		    }
	    	 Thread.sleep(1000);

         Password.sendKeys(password);
         Thread.sleep(1000);

         companyName.sendKeys(compName);
         Thread.sleep(1000);

         PhoneNumber.sendKeys(phone);
         Thread.sleep(1000);

         String enteredPhone =  PhoneNumber.getAttribute("value");
 	    if (enteredPhone.matches("\\d+")) {
 	        System.out.println(" Phone Number field is valid, contains only numbers.");
 	        // You can add validation logic here
 	    } else {
 	    	validationErrors.add("Phone Number field is  invalid,should take only numbers as input");
// 	         System.out.println("Phone Number field is  invalid,should take only numbers as input");
 	    	
 	        }
 	   Thread.sleep(1000);

         wait.until(ExpectedConditions.elementToBeClickable(ConfirmBtn)).click();
//         wait.until(ExpectedConditions.elementToBeClickable(cancelImage)).click();
         Thread.sleep(1000);

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
         System.out.println(validationErrors);
         return validationErrors;
         
		 
	 }
	  public String getToastMessage() {
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	            Thread.sleep(2000);
//	            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("_rht_toaster")));
	            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
	          By.xpath("//div[@role='status' and contains(@class,'go3958317564') and contains(text(),'Tenant created successfully')]")));
	            return toast.getText();
	        } catch (Exception e) {
	            return null;
	        }
	        
}
	  
//^^^^^^^^^^^^^^^^^^Update Tenant^^^^^^^^^^^^^^^^^^^^^
	  @FindBy(xpath="/html/body/div/form/div[2]/div/div[1]/div[1]/button")
	  WebElement cancelImg;
      @FindBy(xpath="/html/body/div/form/div[2]/div/div[2]/button[2]")
      WebElement  confirmupdateBtn;
      @FindBy(xpath="/html/body/div/form/div[2]/div/div[1]/div[2]/div[2]/input")
      WebElement updatePass;
  public List<String> updtaeTenant(String img,String name,String password,String phone, String compName) throws InterruptedException {
	   Scanner scanner = new Scanner(System.in); // Create Scanner object
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		 
	     SoftAssert softAssert = new SoftAssert();
	     List<String> validationErrors = new ArrayList<>();

//	     wait.until(ExpectedConditions.elementToBeClickable(AllTenanats)).click();
	     WebElement allTenantsBtn =  wait.until(ExpectedConditions.elementToBeClickable(AllTenanats));
	     // Step 2: Check if it's already selected (e.g., via class "active")
	     String buttonClass = allTenantsBtn.getAttribute("class");

	     if (!buttonClass.contains("text-[#00AA2F]")) {
	         allTenantsBtn.click();
	         System.out.println("✅ Clicked 'All Tenants' tab.");
	         Thread.sleep(1000);  // Optional: wait for the page to load (better with WebDriverWait)
	     } else {
	         System.out.println("⏭️ 'All Tenants' is already active. Skipping click.");
	     }
         System.out.println("*********Please Enter a Row Number for updating a Tenant:******** ");

         int rowNum = scanner.nextInt(); // Read integer input
         scanner.nextLine();
      
         System.out.println("You entered: " + rowNum);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[3]/div/div/div/table/tbody/tr["+rowNum+"]/td[7]/div/button[1]"))).click();
	    Thread.sleep(1000);
	     
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
	     
	     UserName.clear();
	     updatePass.clear();
	     companyName.clear();
	     PhoneNumber.clear();

         UserName.sendKeys(name);
         String user_name= UserName.getAttribute("value");
	    	if (user_name.matches("^[a-zA-Z\\s]+$")) {
		        System.out.println("UserName  field is valid, takes only charcters.");
		        // You can add validation logic here
		    } else {
		    	validationErrors.add("UserName field is invalid, should take only characters as input");
//		    	 System.out.println("UserName field is invalid, should take only characters as input");
//		        return validationErrors;
		    	
		    }
	    	 Thread.sleep(1000);

        
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

         companyName.sendKeys(compName);
         Thread.sleep(1000);

         PhoneNumber.sendKeys(phone);
         Thread.sleep(1000);

         String enteredPhone =  PhoneNumber.getAttribute("value");
 	    if (enteredPhone.matches("\\d+")) {
 	        System.out.println(" Phone Number field is valid, contains only numbers.");
 	        // You can add validation logic here
 	    } else {
 	    	validationErrors.add("Phone Number field is  invalid,should take only numbers as input");
// 	         System.out.println("Phone Number field is  invalid,should take only numbers as input");
 	    	
 	        }
 	   Thread.sleep(1000);
 	   
 	// STEP: Check for duplicate company name + phone number in the tenant list
 	  List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
 	  String enteredCompany = companyName.getAttribute("value").trim().toLowerCase();
 	  String enteredPhone1 = PhoneNumber.getAttribute("value").trim();

 	  int duplicateCount = 0;

 	  for (WebElement row : rows) {
 	      List<WebElement> cells = row.findElements(By.tagName("td"));
 	      if (cells.size() >= 4) { // Adjust based on your table layout
 	          String rowCompany = cells.get(4).getText().trim().toLowerCase(); // Assume company name in column 2 (index 1)
 	          String rowPhone = cells.get(3).getText().trim(); // Assume phone number in column 3 (index 2)

 	          if (rowCompany.equals(enteredCompany) && rowPhone.equals(enteredPhone1)) {
 	              duplicateCount++;
 	          }
 	      }
 	  }

 	  if (duplicateCount >= 2) {
 	      validationErrors.add("Duplicate entry found: Company and phone already exist in more than one record.");
 	      System.out.println("Found " + duplicateCount + " duplicates. Canceling update.");
          
// 	      WebElement cancel = driver.findElement(By.xpath("/html/body/div/form/div[2]/div/div[2]/button[1]"));
// 	      wait.until(ExpectedConditions.elementToBeClickable(cancel)).click();
 	      return validationErrors;
 	  }

         wait.until(ExpectedConditions.elementToBeClickable(confirmupdateBtn)).click();
//         wait.until(ExpectedConditions.elementToBeClickable(cancelImage)).click();
         Thread.sleep(1000);
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
        
    System.out.println(validationErrors);
	return validationErrors;     
  }
  
//  %%%%%%%%%%%%%Deletee Tenat%%%%%%%%%%%%%%%%%%%%%%%%%
//------------------  Delete Route -------------------------
//@FindBy(xpath="/html/body/div/div/div[3]/div/div/div/table/tbody/tr[1]/td[6]/div/button[2]")
//WebElement DeleteBtn;

@FindBy(xpath="/html/body/div/div/div[3]/div/div/div/div[1]/div/div/button[2]")
WebElement clickDelete;


@FindBy(xpath="/html/body/div/div/div[3]/div/div/div/div[1]/div/div/button[1]")
WebElement cancelDelete;

//Action
public void deleteTenant() throws InterruptedException {
   

 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
// wait.until(ExpectedConditions.elementToBeClickable(AllTenanats)).click();
 WebElement allTenantsBtn =  wait.until(ExpectedConditions.elementToBeClickable(AllTenanats));
 // Step 2: Check if it's already selected (e.g., via class "active")
 String buttonClass = allTenantsBtn.getAttribute("class");

 if (!buttonClass.contains("text-[#00AA2F]")) {
     allTenantsBtn.click();
     System.out.println("✅ Clicked 'All Tenants' tab.");
     Thread.sleep(1000);  // Optional: wait for the page to load (better with WebDriverWait)
 } else {
     System.out.println("⏭️ 'All Tenants' is already active. Skipping click.");
 }
 Scanner scanner = new Scanner(System.in); // Create Scanner object
 
 System.out.println("*********Please Enter a Row Number for Tenant Deletion :******** ");

 int rowNum = scanner.nextInt(); // Read integer input


 System.out.println("You entered: " + rowNum);
 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[3]/div/div/div/table/tbody/tr["+rowNum+"]/td[7]/div/button[2]"))).click();
 

	
//	 wait.until(ExpectedConditions.elementToBeClickable(DeleteBtn)).click();
	 wait.until(ExpectedConditions.elementToBeClickable(clickDelete)).click();
   
}
public String delete_Tenant_popUp() {
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(
           By.xpath("//div[@role='status' and contains(text(),'Tenant Deleted Successfully!')]")
       ));
String actual_message=popup.getText();
return actual_message;
}

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
public List<String> foundDuplicates() throws InterruptedException{
	 // Step 1: Locate the "All Tenants" button
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	 List<String> validationErrors = new ArrayList<>();
	    SoftAssert softAssert = new SoftAssert();
     
    
    WebElement allTenantsBtn =  wait.until(ExpectedConditions.elementToBeClickable(AllTenanats));
    // Step 2: Check if it's already selected (e.g., via class "active")
    String buttonClass = allTenantsBtn.getAttribute("class");

    if (!buttonClass.contains("text-[#00AA2F]")) {
        allTenantsBtn.click();
        System.out.println("✅ Clicked 'All Tenants' tab.");
        Thread.sleep(1000);  // Optional: wait for the page to load (better with WebDriverWait)
    } else {
        System.out.println("⏭️ 'All Tenants' is already active. Skipping click.");
    }

	Thread.sleep(2000);
   List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

   Map<String, Integer> companyNumberCount = new HashMap<>();
   Map<String, Integer> phoneNumberCount = new HashMap<>();
   

   for (WebElement row : rows) {
       if (!row.isDisplayed()) continue;

       List<WebElement> cells = row.findElements(By.tagName("td"));

       if (cells.size() >= 5) {
           // Adjust column indexes if needed
           String phoneNumberRaw = cells.get(3).getText();    // Column 3 (index 2)
           String companyRaw = cells.get(4).getText();       // Column 4 (index 3)
           if (phoneNumberRaw == null || companyRaw == null ||
                   phoneNumberRaw.trim().isEmpty() || companyRaw.trim().isEmpty()) {
                   System.out.println("Skipping row due to null or empty values.");
                   continue;}
           // Normalize
           String phoneNumber = phoneNumberRaw.replaceAll("[^\\d]", ""); // Keep only digits
           String company = companyRaw.toLowerCase().replaceAll("\\s+", "");

           System.out.println("Checking Row → Phone: " + phoneNumber + ", Company: " + company);

           phoneNumberCount.put(phoneNumber, phoneNumberCount.getOrDefault(phoneNumber, 0) + 1);
           companyNumberCount.put(company, companyNumberCount.getOrDefault(company, 0) + 1);
       }
   }

   // Logging maps
   System.out.println("Phone Map: " + phoneNumberCount);
   System.out.println("Company Map: " + companyNumberCount);

   // Collect duplicates
   for (Map.Entry<String, Integer> entry : phoneNumberCount.entrySet()) {
       if (entry.getValue() > 1) {
           validationErrors.add("Duplicate phone number found: " + entry.getKey());
          
       }
   }

   for (Map.Entry<String, Integer> entry : companyNumberCount.entrySet()) {
       if (entry.getValue() > 1) {
           validationErrors.add("Duplicate car number found: " + entry.getKey());
          
       }
   }

   return validationErrors;
}

}
