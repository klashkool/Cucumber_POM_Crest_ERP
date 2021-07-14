package runner_file;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;



@RunWith(Cucumber.class)
@CucumberOptions(features = "D:\\Projects\\Cucumber_POM_CREST_ERP\\src\\test\\resources\\Procurement_flow_feature_files\\TC01.feature",
glue = {"stepdefinition_files"},
plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
monochrome = true,
				dryRun = false
// dryRun = true
// tags = "@TC05-01"
)

public class TestRunner {


}