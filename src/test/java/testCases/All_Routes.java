package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


import PageObjects.AllRoutes;
import PageObjects.LoginPage;

public class All_Routes {
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
	  


	//-----------Adding Routes -------
	  @Test(priority=3)
	
		  public void TestaddRoute() throws InterruptedException {
		  
			    AllRoutes addRoute = new AllRoutes(driver);
			    try {
			     addRoute.addNewRoute(
			        "Kashif ", 
			        "Hello World", 
			        "Talha", 
			        "12345678901", 
			        "Gulshan", 
			        "24.939583577302717, 67.1527126909316", 
			        "24.939583577302717, 67.1527126909316"
			    );}
			    catch(Exception e) {
			    	System.out.println(e.getMessage());
			  
			    }
//		
//		  //  Block of code to handle errors
	  }
	  
		//----------- Update Route -------
	  @Test(priority=4)
	    public void UpdateRoute() throws InterruptedException {
	    AllRoutes  updatePage = new AllRoutes(driver);
	    updatePage.UpdateRoute(
	        "Yahya",
	        "Hello World", 
	        "Talha", 
	        "52345678901", 
	        "Gulshan", 
	        "24.939583577302717, 67.1527126909316", 
	        "24.939583577302717, 67.1527126909316"
	    );}
	  
	//----------- Delete Route -------
	  @Test(priority=5)
	    public void deleteRoute() throws InterruptedException {
	    AllRoutes delRoute = new AllRoutes(driver);
	    delRoute.deleteRoute();
	    }
	  
}
