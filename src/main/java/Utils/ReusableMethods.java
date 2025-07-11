package Utils;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableMethods {

	public static WebDriverWait getWait() {
		System.out.println("wait method is working");
		return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
	}

	public static void handleButton(WebElement button_name) {
		  try {
	            WebDriverWait wait = getWait();
	            wait.until(ExpectedConditions.elementToBeClickable(button_name));
	            button_name.click();
	            System.out.println("Clicked button: " + button_name.getAccessibleName());
	        } catch (Exception e) {
	            System.out.println("Button not clickable or already handled: " + e.getMessage());
	        }
	}
    public static boolean waitForUrlToContain(String partialUrl, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.urlContains(partialUrl));
        } catch (TimeoutException e) {
            return false;
        }
    }

}
