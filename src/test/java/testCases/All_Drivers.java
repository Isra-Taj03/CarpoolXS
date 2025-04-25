package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;


import PageObjects.AllDrivers;

import PageObjects.LoginPage;

public class All_Drivers {
 WebDriver driver; 
  @Test(priority=1)
  public void setup () {
	  driver = new ChromeDriver();
      driver.get("https://carpool-admin.360xpertsolutions.com/");
      // Optional settings
      driver.manage().window().maximize();
  }
  @Test(dependsOnMethods = {"setup"},priority=2)
  public void performlogin() {
	  LoginPage loginPage = new LoginPage(driver);

      // Fill in login form
      loginPage.setPhoneNumber("923152977618");
      loginPage.setPasword("test123!@#");

      // Click login
      loginPage.ClickLoginBtn();
  }
  
//-----------------------------
//Search Driver
//@Test
//public void DriverSearch() throws InterruptedException {
//SearchDriver searchPage = new SearchDriver(driver);
//// List of values to enter
//String[] values = {
//	    "DVR-AP12NA1244",
//	    "sameed",
//	    "taha"
//	};
//searchPage.SearchDriver(values);
//}
//-----Adding Driver ------
@Test(priority=4)
public void TestAddDriver() throws InterruptedException {
AllDrivers addingDriver=new AllDrivers(driver);
addingDriver.addNewDriver("C:/Users/Tesla Laptops/Pictures/Hamza Sarwar.jpg", "Jamil khan","76874566693","test123!@#","MBH123-ab","Folio3");
}


//-----------------------------
//      Search Driver
@Test(priority=3)
public void DriverSearch() throws InterruptedException {
	AllDrivers searchPage = new AllDrivers(driver);
    // List of values to enter
    String[] values = {
  		    "DVR-AP12NA1244",
  		    "sameed",
  		    "taha"
  		};
    searchPage.SearchDriver(values);
}
  
@Test(priority=5)
 public void TestUpdateDriver() throws InterruptedException {
	 AllDrivers UpdDriver= new AllDrivers(driver);
	UpdDriver.UpdateDriver("C:/Users/Tesla Laptops/Pictures/Hamza Sarwar.jpg","Jamil ALI","01274567469", "MBH123-cb","Folio36");
}
	
//------  delete driver----
@Test(priority=6)
 public void DeleteDriver() throws InterruptedException{
	 AllDrivers delDriver= new AllDrivers(driver);
	 delDriver.deleteDriver();
 }
//------   driver----
@Test(priority=7)
public void FetLocDriver() throws InterruptedException{
	 AllDrivers FLDriver=new AllDrivers(driver);
	 FLDriver.fetchDriverLocation();
	
	 ;


//public void closeBrowser() {
//    driver.quit();
//}

//-----------Adding Routes -------
//public void TestaddRoute() throws InterruptedException {
//RoutePage routePage = new RoutePage(driver);
//routePage.addNewRoute(
//"Waseem", 
//"Hello World", 
//"Talha", 
//"12345678901", 
//"Gulshan", 
//"24.939583577302717, 67.1527126909316", 
//"24.939583577302717, 67.1527126909316"
//);}
// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[3]/div/div/div/table/tbody/tr[1]"))).click();


  
}}
