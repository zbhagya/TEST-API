package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", plugin = { "json:target/jsonReports/cucumber-report.json",
		"junit:target/cucumber-reports/Cucumber.xml" }, glue = { "stepDefinition" })

public class TestRunner {

}
