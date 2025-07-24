package hooks;

import org.testng.annotations.AfterMethod;
import Utils.DriverFactory;
import io.cucumber.java.Before;

public class Hooks {
	@Before
	public void setUp() {
		DriverFactory.startDriver("chrome");
		System.out.println("The browser is STARTED");
	}
	@AfterMethod
    public void setDown() {
        DriverFactory.quitDriver(); 
        System.out.println("The browser is CLOSED");
    }

}
