package RoughWork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import PageObjects.LoginPage;
import PageObjects.MyExample;

public class HelloWorld {
	WebDriver driver;
	@Test(priority = 1)
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://carpool-admin.360xpertsolutions.com/");
        driver.manage().window().maximize();
    }

    // Login method
    @Test(dependsOnMethods = {"setup"}, priority = 2)
    public void performLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setPhoneNumber("923152977618");
        loginPage.setPasword("test123!@#");
        loginPage.ClickLoginBtn();
        Thread.sleep(5000);

    }
	@Test(priority=4)
	public void UpdateRoute() throws InterruptedException {
	    MyExample updatePage = new MyExample(driver);

	    // Step 1: Update route-level info (driver, route name)
	    updatePage.UpdateRoute("Abdul Aziz Warsi", "Hello World");

	    // Step 2: Add multiple passengers
	    updatePage.enterPassenger(0, "Taha", "01548290838", "Shah Faisal", "24.918000, 67.063000", "24.918000, 67.063000");
	    updatePage.enterPassenger(1, "Ali", "02482908389", "Gulshan", "24.918000, 67.063000", "24.918000, 67.063000");
	    updatePage.enterPassenger(2, "Ahmed", "05482908399", "Nazimabad", "24.918000, 67.063000", "24.918000, 67.063000");

	    Thread.sleep(3000);
	}

}
