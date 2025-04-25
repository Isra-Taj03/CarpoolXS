package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import PageObjects.LoginPage;
import PageObjects.infoPage;

public class Info_Page {
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
	  @Test(priority=3)
	  public void editDriver() throws InterruptedException {
		  infoPage editdriver= new infoPage(driver);

	      // Fill in login form
	      editdriver.clickDriver();
	      editdriver.EditDriver("C:/Users/Tesla Laptops/Pictures/Hamza Sarwar.jpg","Jamil ALI","01274567469", "MBH123-cb","Folio36");
	         
	  }
	  
//	  @Test(priority=4)
//	  public void RouteInfo() {
//		  infoPage addPassenger= new infoPage(driver);
//		  
		  
//		  
//	  }
	  
	  
}
