package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AllRoutes extends BasePage {

	public AllRoutes(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

    // Locators
    @FindBy(xpath = "//button[text()='All Routes']")
    WebElement allRoutesBtn;

//    @FindBy(xpath = "//button[text()='Add Routes']")
//    WebElement addRouteBtn;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/button")
    WebElement addRouteBtn;

    @FindBy(xpath = "/html/body/div/form/div[2]/div/div[1]/div[1]/div[2]/input[1]")
    WebElement assignDriverInput;
    
//    @FindBy(xpath="//li[text()='Kashif']")
  //div[@class= 'cursor-pointer']/descendant::li[text() = '" + text + "']
//    /html/body/div/form/div[2]/div/div[1]/div[1]/div[2]/ul/li[1]
//    WebElement selectedDriverli;

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
    public void addNewRoute(String driverName, String routeName, String passengerName,
                            String phone, String address, String pickupLoc, String dropoffLoc) throws InterruptedException {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(allRoutesBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addRouteBtn)).click();

       assignDriverInput.sendKeys(driverName);
//       

       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[contains(text(),'" + driverName+ "')]"))).click();
        // Optional: Verify assignment or fetch the driver value if needed

        routeNameInput.sendKeys(routeName);
        passengerNameInput.sendKeys(passengerName);
        phoneNumberInput.sendKeys(phone);
        addressInput.sendKeys(address);
        pickupLocationInput.sendKeys(pickupLoc);
        dropoffLocationInput.sendKeys(dropoffLoc);

        confirmBtn.click(); // Or use wait.until to be extra safe
    }
//***************** Edit Route *********************************************
    
//------------------ Update Route -------------------

    // Locators

    @FindBy(xpath="//tbody/tr[1]/td[6]/div[1]/button[1]") 
    WebElement editBtn;//tbody/tr[1]/td[6]/div[1]/button[1]//*[name()='svg']//*[name()='path' and contains(@d,'M12 3H5a2 ')]

    // Actions
    public void UpdateRoute(String driverName, String routeName, String passengerName,
                            String phone, String address, String pickupLoc, String dropoffLoc) throws InterruptedException {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//    	 wait.until(ExpectedConditions.elementToBeClickable(allRoutesBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
        
       
       assignDriverInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
       assignDriverInput.sendKeys(driverName);
       
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[contains(text(),'" + driverName+ "')]"))).click();
//       

        // Optional: Verify assignment or fetch the driver value if needed
       
        routeNameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        routeNameInput.sendKeys(routeName);
        
        passengerNameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        passengerNameInput.sendKeys(passengerName);
        
        phoneNumberInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);;
        phoneNumberInput.sendKeys(phone);
        
        addressInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        addressInput.sendKeys(address);
        
        pickupLocationInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        pickupLocationInput.sendKeys(pickupLoc);
        
        dropoffLocationInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        dropoffLocationInput.sendKeys(dropoffLoc);

        confirmBtn.click(); // Or use wait.until to be extra safe
    }

//------------------  Delete Route -------------------------
    @FindBy(xpath="/html/body/div/div/div[3]/div/div/div/table/tbody/tr[1]/td[6]/div/button[2]")
    WebElement DeleteBtn;
    
    @FindBy(xpath="/html/body/div/div/div[3]/div/div/div/div[1]/div/div/button[2]")
    WebElement clickDelete;
    
    
    @FindBy(xpath="/html/body/div/div/div[3]/div/div/div/div[1]/div/div/button[1]")
    WebElement cancelDelete;
    
// Action
   public void deleteRoute() {
	   

	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//	 wait.until(ExpectedConditions.elementToBeClickable(allRoutesBtn)).click();
  	 wait.until(ExpectedConditions.elementToBeClickable(DeleteBtn)).click();
  	 wait.until(ExpectedConditions.elementToBeClickable(clickDelete)).click();
	   
   }
}
