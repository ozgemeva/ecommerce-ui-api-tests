package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import TestDataSets.TestData;
import Utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.RegistrationPage;

public class RegisterUISteps {
	WebDriver registerDriver;
	RegistrationPage registrationPage;

	@Given("the user is on the homepage")
	public void userIsOnHomePage() {
		registerDriver = DriverFactory.getDriver();
		registrationPage = new RegistrationPage(registerDriver);
		registerDriver.get(TestData.BASE_URL);
		Assert.assertTrue(registrationPage.isOnHomePage(), "Not on home page!");
	}

	@When("the user clicks the {string} button")
	public void clickSignup_button(String buttonName) {
		System.out.println(" --> Button Name: " + buttonName);
		if (buttonName.equalsIgnoreCase("Signup / Login")) {
			registrationPage.clickSignUp_button();
			Assert.assertTrue(registerDriver.getCurrentUrl().contains("Signup/login"),
					"Not redirected to Signup/login  page");
		} else {
			Assert.fail("Unsupported or unknown button name: " + buttonName);
		}
			
	}
	
	@And("the user enters {string} and {string}")
	public void enter_name_mail(String username,String mail){
		registrationPage.enterMailAndName(username, mail);
	
	}

	
}
