package PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AllDrivers extends BasePage {

	public AllDrivers(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
//***********************  Add_Driver  *****************************************
	  // Elements
    @FindBy(xpath = "//button[text()='Add drivers']")
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

    // Action Method
    public void addNewDriver(String img, String Name,String PhoneNumber,String Password,String CarNumber, String CompanyName) throws InterruptedException {
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(addDriverButton)).click();

        fileInput.sendKeys(img);
        wait.until(ExpectedConditions.elementToBeClickable(cancelImage)).click();

        driverNameField.sendKeys(Name);
        phoneNumberField.sendKeys(PhoneNumber); 

        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(Password);

        // Click eye icon twice
        Thread.sleep(1000);
        eyeIcon.click();
        Thread.sleep(1000);
        eyeIcon.click();

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

        companyNameField.sendKeys(CompanyName);
        confirmDriverButton.click();
    }
// ************************ Search Driver ****************************
  //Locators
    @FindBy(xpath="/html/body/div/div/div[2]/div/div[2]/input")
    WebElement inputField;


    public void SearchDriver(String[] values) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	for (int i = 0; i < values.length; i++) {
    	    String value = values[i];

//    	    wait.until(ExpectedConditions.visibilityOfElementLocated(inputField));
    	    wait.until(ExpectedConditions.elementToBeClickable(inputField));
    	    // Use keyboard to clear input
    	    inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);

    	    inputField.sendKeys(value);

    	    Thread.sleep(2000); // Simulate wait

    	    // After last input, clear again
    	    if (i == values.length - 1) {
    	    	inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
    	        wait.until(ExpectedConditions.attributeToBe(inputField, "value", ""));
    	        break;
    	    }
    	}
    }
 
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
     public void deleteDriver() {
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	 wait.until(ExpectedConditions.elementToBeClickable(DeleteBtn)).click();
    	wait.until(ExpectedConditions.elementToBeClickable(clickDelete)).click();
     }
     
     
 //-------Fetch Driver---
     public void fetchDriverLocation() {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        	 wait.until(ExpectedConditions.elementToBeClickable(fetchBtn)).click();
     }}
         
