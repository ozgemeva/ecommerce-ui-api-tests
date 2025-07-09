package pages;

import TestDataSets.TestData;
import Utils.ReusableMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private final WebDriver driver;
	WebDriverWait wait=ReusableMethods.getWait();

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;

	}

	public boolean isOnHomePage() {
		wait.until(ExpectedConditions.urlContains(TestData.BASE_URL_STRING));
		return driver.getCurrentUrl().equals(TestData.BASE_URL_STRING);

	}
}
