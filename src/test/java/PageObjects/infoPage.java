package PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class infoPage extends BasePage{

	public infoPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
//	Locators
//	Driver info
	 @FindBy(xpath = "/html/body/div/div/div[3]/div/div/div/table/tbody/tr[1]")
	    WebElement clickDriverID;

	    @FindBy(xpath = "/html/body/div/div[3]/div/div[1]/div[1]/button")
	    WebElement editBtnDriverInfo;
	    

	    @FindBy(xpath = "//*[@id=\"fileInput\"]")
	    WebElement fileInput;
	    
	    @FindBy(xpath="/html/body/div/div[4]/div/div[1]/div/button")
	    WebElement cancelImage;

	    @FindBy(xpath = "/html/body/div/div[4]/div/div[2]/div[1]/input")
	    WebElement driverNameField;

	    @FindBy(xpath = "/html/body/div/div[4]/div/div[2]/div[2]/input")
	    WebElement phoneNumberField;

	    @FindBy(xpath = "/html/body/div/div[4]/div/div[2]/div[3]/input")
	    WebElement carNumberField;

	    @FindBy(xpath = "/html/body/div/div[4]/div/div[2]/div[5]/div/input")
	    WebElement Pass;

	    @FindBy(xpath = "/html/body/div/div[4]/div/div[2]/div[5]/div/button")
	    WebElement eyeiconBtn;
	    
	    @FindBy(xpath = "/html/body/div/div[4]/div/div[2]/div[4]/div[1]/select")
	    WebElement cartypeDropdown;

	    @FindBy(xpath = "/html/body/div/div[4]/div/div[2]/div[4]/div[2]/select")
	    WebElement statusDropdown;
	    
	    @FindBy(xpath="/html/body/div/div[4]/div/div[2]/div[6]/input")
	    WebElement companyNameField;
	 
	    
//	    @FindBy(xpath = "/html/body/div/div[4]/div/div[3]/button[1]")
//	    WebElement cancelbtn;

	    @FindBy(xpath = "/html/body/div/div[4]/div/div[3]/button[2]")
	    WebElement confirmDriverBtn;

//	    Actions
//	    public  void clickDriver() {
//	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//	    	  Scanner scanner = new Scanner(System.in); // Create Scanner object
//	          System.out.println("*********Please Enter a Row Number for driverInfo:******** ");
//
//	          int rowNum = scanner.nextInt(); // Read integer input
//	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[3]/div/div/div/table/tbody/tr["+rowNum+"]"))).click();
//	    	
//	    }
//	   
	    public List<String> EditDriver(String img, String Name,String PhoneNumber,String CarNumber, String CompanyName) throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    	  Scanner scanner = new Scanner(System.in); // Create Scanner object
	          System.out.println("*********Please Enter a Row Number for driverInfo:******** ");

	          int rowNum = scanner.nextInt(); // Read integer input
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[3]/div/div/div/table/tbody/tr["+rowNum+"]"))).click();
	    	
//	    	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		       List<String> validationErrors = new ArrayList<>();
		       SoftAssert softAssert = new SoftAssert();
		       Thread.sleep(5000);
		       wait.until(ExpectedConditions.elementToBeClickable(editBtnDriverInfo)).click();
//		       fileInput.sendKeys(img);
               if(img==null) {
		       fileInput.sendKeys(img);}
               else {
		       wait.until(ExpectedConditions.elementToBeClickable(cancelImage)).click();
            	   fileInput.sendKeys(img);
            	   
               }
		       driverNameField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		       driverNameField.sendKeys(Name);
		       
		       phoneNumberField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		       phoneNumberField.sendKeys(PhoneNumber); 

//		       *wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(Password);

		       // Click eye icon twice
//		       Thread.sleep(1000);
//		       eyeIcon.click();
//		       Thread.sleep(1000);
//		       eyeIcon.click();
		       carNumberField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		       carNumberField.sendKeys(CarNumber);
		        
		       // Select car type
		       Select carTypeSelect = new Select(cartypeDropdown);
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
		           WebElement cancel = driver.findElement(By.xpath("/html/body/div/div[4]/div/div[3]/button[1]"));
		           wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
		           System.out.println("comonaynotfooundcancel");
		           System.out.println("cancel clicked");
		           return validationErrors; 
//		           softAssert.assertAll();
		       }
		       
		       confirmDriverBtn.click();
		       return validationErrors;
		   }
	    
//	addPasenger
//Locators
       
	    @FindBy(xpath = "//button[text()='All Routes']")
	    WebElement allRoutesBtn;
	    
//	    @FindBy(xpath="/html/body/div/div/div[3]/div/div/div/table/tbody/tr[1]")
//	    WebElement RouteId;
	   
//	    @FindBy(xpath = "/html/body/div/div[3]/div/div[2]/div[1]/button[1]")
//	    WebElement RouteInfoBtn;
//	    /html/body/div/div[3]/div/div[2]/div[1]/button
//	    /html/body/div/div[3]/div/div[2]/div[1]/button[1]
//	    		/html/body/div/div[3]/div/div[2]/div[1]/button[2]
	    @FindBy(xpath = "/html/body/div/div[3]/div/div[2]/div[2]/div[1]/button")
	    WebElement addPassengerBtn;
	    
	    @FindBy(xpath = "//input[contains(@placeholder, 'Name')]")
	    WebElement PassName;

	    @FindBy(xpath = "//input[contains(@placeholder, 'Phone')]")
	    WebElement PassengerPhoneNumber;
	    
	    @FindBy(xpath = "//input[contains(@placeholder, 'Address')]")
	    WebElement PassengerAddress;
	    
	    @FindBy(xpath = "//input[contains(@placeholder, 'Pick up location')]")
	    WebElement Pick_up_location;
	    
	    @FindBy(xpath = "//input[contains(@placeholder, 'Drop-off location')]")
	    WebElement drop_off_location;
	    
	    @FindBy(xpath = "/html/body/div/div[4]/div/div[3]/button[1]")
	    WebElement cancelBtn;
	    
	    @FindBy(xpath = "/html/body/div/div[4]/div/div[3]/button[2]")
	    WebElement ConfirmBtn;
	    public void RouteInfo(String PassengerName, String Phone, String Address, String Pick_up, String Drop_off ) throws InterruptedException {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    	  
	    	  wait.until(ExpectedConditions.elementToBeClickable(allRoutesBtn)).click();
	    	  Scanner scanner = new Scanner(System.in); // Create Scanner object
	          System.out.println("*********Please Enter a Row Number for roouteInfo:******** ");

	          int rowNum = scanner.nextInt(); // Read integer input

	       
	    	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[3]/div/div/div/table/tbody/tr["+rowNum +"]"))).click();
	    	  wait.until(ExpectedConditions.elementToBeClickable(allRoutesBtn)).click();
	    	
	          System.out.println("*********Please Enter a 1 for first route , 2 for 2nd route and 3 for 3rd froute ...:******** ");

	          int rowNum1 = scanner.nextInt(); // Read integer input
	          wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[3]/div/div[2]/div[1]/button["+rowNum1 +"]"))).click();
	          Thread.sleep(1000);
	          wait.until(ExpectedConditions.elementToBeClickable(addPassengerBtn)).click();

	          PassName.sendKeys(PassengerName);
	          PassengerPhoneNumber.sendKeys(Phone);	   
	          PassengerAddress.sendKeys(Address);
	          Pick_up_location.sendKeys(Pick_up);
	          drop_off_location.sendKeys(Drop_off);
	          wait.until(ExpectedConditions.elementToBeClickable(ConfirmBtn)).click();
	    }
//	    /html/body/div/div[3]/div/div[2]/div[2]/div[2]/table/tbody/tr[]/td[6]/div/button
//	    %%%%%%%%%%%%%%%%%%%%%%%%%
//	  
	    //  -------------************Delete DRiver***************
	//   Delete driver
//	     @FindBy(xpath="/html/body/div/div/div[3]/div/div/div/table/tbody/tr[1]/td[9]/div/button[2]")
//	     WebElement DeleteBtn;
	     @FindBy(xpath="/html/body/div/div[4]/div/div/button[2]")
	     WebElement clickDelete;
	     


	     public void DeletePassenger() throws InterruptedException {
	         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	         Scanner scanner = new Scanner(System.in); // Create Scanner object
	         
	         System.out.println("*********Please Enter a Row Number for Deletion :******** ");

	         int rowNum = scanner.nextInt(); // Read integer input

	 
	         System.out.println("You entered: " + rowNum);
	         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[3]/div/div[2]/div[2]/div[2]/table/tbody/tr["+rowNum+"]/td[6]/div/button"))).click();
//	        	 wait.until(ExpectedConditions.elementToBeClickable(DeleteBtn)).click();
	        	wait.until(ExpectedConditions.elementToBeClickable(clickDelete)).click();
	        	
	        
	        	
//	        	return actual_message

	                // Optionally, verify the message content
	             
	         }
//	         public String pop1() {
//	        	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//	    		WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(
//	                     By.xpath("//div[@role='status' and contains(text(),'Driver deleted successfully.')]")
//	                 ));
//	     	String actual_message=popup.getText();
//	     	return actual_message;
//	     	}
}

