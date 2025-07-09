package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Utils.DriverFactory;
import io.cucumber.java.en.Given;
import pages.RegistrationPage;

public class RegisterUISteps {
	WebDriver registerDriver = DriverFactory.getDriver(); // calls driver from Utils folder
	RegistrationPage registrationPage = new RegistrationPage(registerDriver);

	@Given("the user is on the homepage")
	public void on_the_homePage() {
		Assert.assertTrue(registrationPage.isOnHomePage(), "Not on home page!");
	}

}
