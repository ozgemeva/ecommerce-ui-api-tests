package pages;

import TestDataSets.TestData;
import Utils.ReusableMethods;

import java.security.PublicKey;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
	private final WebDriver driver;
	WebDriverWait wait = ReusableMethods.getWait();
	
	String Url;
	@FindBy(xpath = "//a[normalize-space()='Signup / Login']")
	WebElement signup_login_button;

	@FindBy(xpath = "//button[.//p[text()='Consent']]")
	WebElement consent_btn;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isOnHomePage() {
		wait.until(ExpectedConditions.urlContains(TestData.BASE_URL));
		Url = driver.getCurrentUrl();
		System.out.println(" --> currentUrl : " + Url + " Base_Url : " + TestData.BASE_URL);
		return Url.contains(TestData.BASE_URL);
	}

	public void clickSignUp_button() {
		try {
			System.out.println(" --> signup_login_button text: " + signup_login_button.getText());
			wait.until(ExpectedConditions.elementToBeClickable(signup_login_button));
			signup_login_button.click();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			throw e;
		}
	}


}
