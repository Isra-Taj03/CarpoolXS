package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import PageObjects.LoginPage;
import PageObjects.infoPage;

public class RouteInfo {
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
	  public void RouteInfo() throws InterruptedException {
		  infoPage routeInfo = new infoPage(driver);

	      routeInfo.RouteInfo("Taha", "03462345678","gulshan", "24.939583577302717, 67.1527126909316", "24.939583577302719, 67.1527126909315");;
	  }
}
