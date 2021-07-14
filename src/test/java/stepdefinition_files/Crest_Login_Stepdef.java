package stepdefinition_files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.Crest_ERP_Login.Crest_Login;
import com.Utils.Base;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Crest_Login_Stepdef extends Base {

	// Crest Login

	@Given("^Launch the Application$")
	public void launch_the_Application() {
		Base.initialization();
	}

	@Then("^Login with valid Credentials$")
	public void login_with_valid_Credentials() {
		Crest_Login.Login();

	}

	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);

		}
	}

}
