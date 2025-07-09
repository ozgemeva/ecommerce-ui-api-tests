package Utils;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableMethods {
	static WebDriver driver = DriverFactory.getDriver();

	public static WebDriverWait getWait() {
		return new WebDriverWait(driver, Duration.ofSeconds(10));
	}

}
