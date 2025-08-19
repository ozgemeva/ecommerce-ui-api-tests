
package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import TestDataSets.TestData;
import Utils.DriverFactory;
import Utils.ReusableMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
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
		if (!buttonName.equalsIgnoreCase("Signup / Login")) {
			Assert.fail("Unsupported or unknown button name: " + buttonName);
		}

		registrationPage.clickSignUp_button();

		boolean onLogin = ReusableMethods.getWait().until(ExpectedConditions.urlContains("/login"));
		Assert.assertTrue(onLogin, "Not redirected to /login page");

	}

	@And("the user enters {string} and {string}")
	public void enter_name_mail(String username, String mail) {

		registrationPage.enterMailAndName(username, mail);
		Assert.assertFalse(registrationPage.getEnteredName().isBlank(), "Name was not entered");
		Assert.assertFalse(registrationPage.getEnteredEmail().isBlank(), "Email was not entered");

	}

	@And("clicks the Signup button")
	public void clickButtonForNewUser() {

		registrationPage.clickSignUpButton_for_new_account();
		Assert.assertTrue(registerDriver.getCurrentUrl().contains("signup"), "Not redirected to Signup  page");

	}

	@Then("the user should see the Enter Account Information section")
	public void userEnterAccountInformation() {
		registerDriver = DriverFactory.getDriver();
		registrationPage = new RegistrationPage(registerDriver);
		Assert.assertTrue(registrationPage.isOnSignupPage(TestData.signUpURL), "Not on signup page!");

	}

}
