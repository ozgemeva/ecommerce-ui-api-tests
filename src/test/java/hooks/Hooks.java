package hooks;

import Utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	@Before
	public void setUp() {
		DriverFactory.startDriver("chrome");
		System.out.println("The browser is STARTED");
	}

	@After
	public void setDown() {
		DriverFactory.quitDriver();
		System.out.println("The browser is CLOSED");
	}

}
