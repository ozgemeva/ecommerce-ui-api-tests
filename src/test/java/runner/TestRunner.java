package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = { "pretty","html:target/cucumber-report.html" }, 
		features = {"src/test/resources/features"},
		glue = { "stepdefinitions","hooks" },
		tags = "@api", 
		monochrome = true, 
		dryRun = false)
public class TestRunner extends AbstractTestNGCucumberTests {

}

