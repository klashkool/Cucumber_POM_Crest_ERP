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



public class CRM_Leads extends Base {
	
	@Rule
	public static JUnitSoftAssertions Lead_softAssert = new JUnitSoftAssertions();

	public static void clickCreateLead() {
		WebdriverWait.findElement("link", "Create Lead").click();
	}
	
	public static void CreateLead(List<HashMap<String, String>> data, String sheetName) {
		WebdriverWait.findElement("id", "firstName").sendKeys(data.get(37).get("Column1"));
		WebdriverWait.findElement("id", "lastName").sendKeys(data.get(37).get("Column2"));
		WebdriverWait.findElement("id", "groupName").sendKeys(data.get(37).get("Column3"));
		WebdriverWait.findElement("id", "personalTitle").sendKeys(data.get(37).get("Column4"));
		WebdriverWait.findElement("id", "numEmployees").sendKeys(data.get(37).get("Column5"));
		WebdriverWait.findElement("id", "officeSiteName").sendKeys(data.get(37).get("Column6"));
		Select industryDD = new Select(WebdriverWait.findElement("id", "industry"));
		industryDD.selectByVisibleText(data.get(37).get("Column7"));
		Select priorityDD = new Select(WebdriverWait.findElement("id", "priority"));
		priorityDD.selectByVisibleText(data.get(37).get("Column8"));
		Select assignToDD = new Select(WebdriverWait.findElement("id", "assignTo"));
		assignToDD.selectByVisibleText(data.get(37).get("Column9"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String expClosureDate = (dateFormat.format(new Date()));
		WebdriverWait.findElement("id", "expClosureDate_i18n").sendKeys(expClosureDate);
		ExcelWriter.writeExcelFile(sheetName, 39, 10, expClosureDate);
		Select categoryDD = new Select(WebdriverWait.findElement("id", "category"));
		categoryDD.selectByVisibleText(data.get(37).get("Column11"));
		WebdriverWait.findElement("id", "leadSummary").sendKeys(data.get(37).get("Column12"));
		CRM_Contacts.enterContactInfo(data);
		Select dataSourceIdDD = new Select(WebdriverWait.findElement("id", "dataSourceId"));
		dataSourceIdDD.selectByVisibleText(data.get(45).get("Column1"));
		Select contactListIdDD = new Select(WebdriverWait.findElement("id", "contactListId"));
		contactListIdDD.selectByVisibleText(data.get(45).get("Column2"));
		WebdriverWait.findElement("xpath", "//*[@type='submit']").click();
	}
	
	public static void captureLeadDetails(String sheetName) {
		String getLead = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/div/div/div/div/div[1]/div/table/tbody[1]/tr/td").getText();
		String getName = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/div/div/div/div/div[2]/div/table/tbody[1]/tr/td").getText();
		String getLeadOwner = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/div/div/div/div/div[1]/div/table/tbody[2]/tr/td").getText();
		String getAssignedTo = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/div/div/div/div/div[2]/div/table/tbody[2]/tr/td").getText();
		String getStatus = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/div/div/div/div/div[2]/div/table/tbody[7]/tr/td").getText();
		ExcelWriter.writeExcelFile(sheetName, 50, 1, getLead);
		ExcelWriter.writeExcelFile(sheetName, 50, 3, getName);
		ExcelWriter.writeExcelFile(sheetName, 50, 4, getLeadOwner);
		ExcelWriter.writeExcelFile(sheetName, 50, 5, getAssignedTo);
		ExcelWriter.writeExcelFile(sheetName, 50, 6, getStatus);
	}
	
	public static void convertToAccount(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("link", "Convert Lead").click();
		WebdriverWait.findElement("xpath", "//*[@value='Convert']").click();
		String getSuccessMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		Lead_softAssert.assertThat(getSuccessMsg).isEqualTo("Lead converted successfully");
	}
	
	public static void Lead_assertions() {
		Lead_softAssert.assertAll();
	}
		
}
