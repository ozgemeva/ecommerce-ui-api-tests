package pages;

import java.time.Duration;
import TestDataSets.TestData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private final WebDriver driver;
	WebDriverWait wait;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	public boolean isOnHomePage() {
		wait.until(ExpectedConditions.urlContains(TestData.BASE_URL_STRING));
		return driver.getCurrentUrl().equals(TestData.BASE_URL_STRING);

	}
}
