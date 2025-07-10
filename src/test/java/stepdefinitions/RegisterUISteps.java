package stepdefinitions;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import TestDataSets.TestData;
import Utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.RegistrationPage;

public class RegisterUISteps {
	WebDriver registerDriver = DriverFactory.getDriver(); // calls driver from Utils folder
	RegistrationPage registrationPage = new RegistrationPage(registerDriver);

	@Given("the user is on the homepage")
	public void userIsOnHomePage() {
		registerDriver.get(TestData.BASE_URL);
		Assert.assertTrue(registrationPage.isOnHomePage(), "Not on home page!");
	}

	@When("the user clicks the {string} button")
	public void clickSignup_button(String buttonName) {
	System.out.println(" --> Button Name: "+ buttonName);
		if (buttonName.equals("Signup / Login")) {
	        registrationPage.clickSignUp_button();
		}
	}

}
