package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
//import io.cucumber.testng.CucumberOptions;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
features = "src/test/resources/features",
glue= {"stepdefinitions"},
plugin = {"pretty","html:TestReports/report.html"},
monochrome = true
)
public class Runner {

}
