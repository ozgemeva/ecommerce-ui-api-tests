package Utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableMethods {
	static WebDriverWait wait;

	public static WebDriverWait getWait() {
		System.out.println("wait method is working");
		return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
	}

//General button click method
	public static void handleButton(WebElement button_name) {
		try {
			wait = getWait();
			wait.until(ExpectedConditions.elementToBeClickable(button_name));
			button_name.click();
			System.out.println("Clicked button: " + button_name.getAccessibleName());
		} catch (Exception e) {
			System.out.println("Button not clickable or already handled: " + e.getMessage());
		}
	}

	// It waits to load all the url
	public static boolean waitForUrlToContain(String partialUrl, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(timeoutSeconds));
		return wait.until(ExpectedConditions.urlContains(partialUrl));
	}

//If there is a consent or manage button. 
	public static void clickConsentIfPresent() {
		WebDriver driver = DriverFactory.getDriver();

		try {
			List<WebElement> consentOverlay = driver.findElements(By.cssSelector(".fc-dialog-overlay"));
			if (!consentOverlay.isEmpty()) {
				System.out.println("Consent overlay detected");

				List<WebElement> consentButtons = driver.findElements(By.xpath("//p[text()='Consent']/.."));
				if (!consentButtons.isEmpty()) {
					WebDriverWait wait = getWait();
					wait.until(ExpectedConditions.elementToBeClickable(consentButtons.get(0)));
					consentButtons.get(0).click();
					wait.until(ExpectedConditions.invisibilityOf(consentButtons.get(0)));
					System.out.println("Clicked consent button");
				} else {
					System.out.println("Consent button not found");
				}
			} else {
				System.out.println("Consent overlay not present, skipping...");
			}

		} catch (Exception e) {
			System.out.println("Consent handling failed: " + e.getMessage());
		}
	}
	
	public static void clearTextIfNotEmpty(WebElement element) {
		String value = element.getAttribute("value");
		if (value != null && !value.trim().isEmpty()) {
			element.clear();
		}
	}

}