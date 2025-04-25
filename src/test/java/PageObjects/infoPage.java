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
	 
	    
	    @FindBy(xpath = "/html/body/div/div[4]/div/div[3]/button[1]")
	    WebElement cancelbtn;

	    @FindBy(xpath = "/html/body/div/div[4]/div/div[3]/button[2]")
	    WebElement confirmDriverBtn;

//	    Actions
	    public void clickDriver() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    	wait.until(ExpectedConditions.elementToBeClickable(clickDriverID)).click();
	    }
	   
	    public void EditDriver(String img, String Name,String PhoneNumber,String CarNumber, String CompanyName) throws InterruptedException {
		       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		  
		       Thread.sleep(5000);
		       wait.until(ExpectedConditions.elementToBeClickable(editBtnDriverInfo)).click();
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
		       confirmDriverBtn.click();
		   }
	    
//	addPasenger
//Locators
       
	    @FindBy(xpath = "//button[text()='All Routes']")
	    WebElement allRoutesBtn;
	    
	    @FindBy(xpath="/html/body/div/div/div[3]/div/div/div/table/tbody/tr[1]")
	    WebElement RouteId;
	    
	    @FindBy(xpath = "/html/body/div/div[3]/div/div[2]/div[1]/button")
	    WebElement RouteInfoBtn;
	    
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
	    public void RouteInfo(String PassengerName, String Phone, String Address, String Pick_up, String Drop_off ) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    	
	    	  wait.until(ExpectedConditions.elementToBeClickable(allRoutesBtn)).click();
	    	  wait.until(ExpectedConditions.elementToBeClickable(RouteId)).click();
	          wait.until(ExpectedConditions.elementToBeClickable(RouteInfoBtn)).click();
	          wait.until(ExpectedConditions.elementToBeClickable(addPassengerBtn)).click();

	          PassName.sendKeys(PassengerName);
	          PassengerPhoneNumber.sendKeys(Phone);	   
	          PassengerAddress.sendKeys(Address);
	          Pick_up_location.sendKeys(Pick_up);
	          drop_off_location.sendKeys(Drop_off);
	          wait.until(ExpectedConditions.elementToBeClickable(ConfirmBtn)).click();
	    }
	 

}

