package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

//Locators
@FindBy(xpath="/html/body/div/div[1]/input[1]")
WebElement PhoneNumber;

@FindBy(xpath="/html/body/div/div[1]/input[2]")
WebElement Password;

@FindBy(xpath="/html/body/div/div[1]/button")
WebElement loginBtn;

public void setPhoneNumber(String phoneNumber){
	PhoneNumber.sendKeys(phoneNumber);
	;}
public void setPasword(String password) {
	Password.sendKeys(password);
}
public void ClickLoginBtn() {
	loginBtn.click();
}

}
