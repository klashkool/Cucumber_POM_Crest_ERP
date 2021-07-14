package com.CRM;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class CRM_Opportunities extends Base {

	public static String opp_ID;

	@Rule
	public static JUnitSoftAssertions opportunity_softAssert = new JUnitSoftAssertions();

	public static void clickOpportunityAndCaptureAccountNo(String sheetName) {
		WebdriverWait.findElement("link", "Create Opportunity").click();
		String getAccount = WebdriverWait.findElement("xpath", "//*[@id='createSalesOpportunity']/div/div/div[2]/div/div/div").getText();
		ExcelWriter.writeExcelFile(sheetName, 50, 2, getAccount);
	}

	public static void createOpportunity(List<HashMap<String, String>> data, String sheetName) {
		WebdriverWait.findElement("id", "opportunityName").sendKeys(data.get(52).get("Column2"));
		Select statusDD = new Select(WebdriverWait.findElement("id", "opportunityStageId"));
		statusDD.selectByVisibleText(data.get(52).get("Column3"));
		// Select currencyDD = new Select(WebdriverWait.findElement("id",
		// "currencyUomId"));
		// currencyDD.selectByVisibleText(data.get(52).get("Column4"));
		Select nextStepDD = new Select(WebdriverWait.findElement("id", "nextStep"));
		nextStepDD.selectByVisibleText(data.get(52).get("Column5"));
		Select priorityDD = new Select(WebdriverWait.findElement("id", "typeEnumId"));
		priorityDD.selectByVisibleText(data.get(52).get("Column6"));
		Select sourceDD = new Select(WebdriverWait.findElement("id", "dataSourceId"));
		sourceDD.selectByVisibleText(data.get(52).get("Column7"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String closeDate = (dateFormat.format(new Date()));
		WebdriverWait.findElement("id", "estimatedCloseDate_i18n").sendKeys(closeDate);
		ExcelWriter.writeExcelFile(sheetName, 54, 8, closeDate);
		WebdriverWait.findElement("id", "description").sendKeys(data.get(52).get("Column9"));
		WebdriverWait.findElement("xpath", "//*[@type='submit']").click();
		String getSuccessMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		opportunity_softAssert.assertThat(getSuccessMsg).isEqualTo("Sales opportunity created successfully.");
	}

	public static void captureOpportunityID(String sheetName) throws InterruptedException {

		WebdriverWait.findElement("xpath", "//*[@id='modifySearch']").click();
		WebdriverWait.findElement("id", "submit").click();
		String getOpportunity = WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr[1]/td[2]/a").getText();
		ExcelWriter.writeExcelFile(sheetName, 54, 1, getOpportunity);
		opp_ID = getOpportunity;
		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr[1]/td[2]/a").click();
	}

	public static void opportunity_assertions() {
		opportunity_softAssert.assertAll();
	}
}
