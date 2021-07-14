package com.Sales_Modules;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.support.ui.Select;

import com.CRM.CRM_Opportunities;
import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Sales_Quotes extends Base {

	@Rule
	public static JUnitSoftAssertions Sales_Quotes_softAssert = new JUnitSoftAssertions();

	public static void Create_SO_Quotes(List<HashMap<String, String>> data, String sheetname)
			throws InterruptedException {

		WebdriverWait.findElement("link", "SALES").click();
		WebdriverWait.findElement("link", "Quotes").click();
		WebdriverWait.findElement("link", "Create Quote").click();

		Select orgName_dd = new Select(WebdriverWait.findElement("id", "billToCustomerPartyId"));
		orgName_dd.selectByVisibleText(data.get(0).get("Column3"));

		WebdriverWait.findElement("name", "partyId").sendKeys(data.get(0).get("Column4"));
		Calendar sys = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String todayDate = (date.format(new Date()));
		String sysTime = (sdf.format(sys.getTime()));

		WebdriverWait.findElement("name", "description").sendKeys("SO " + todayDate + sysTime);

		Select prdStore_dd = new Select(WebdriverWait.findElement("id", "productStoreId"));
		prdStore_dd.selectByVisibleText("General Sales Store");

		// SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// String Fromdate = (dateFormat.format(new Date()));
		// WebdriverWait.findElement("name",
		// "issueDate_i18n").sendKeys(Fromdate);

		Select salesChn_dd = new Select(WebdriverWait.findElement("id", "salesChannelEnumId"));
		salesChn_dd.selectByVisibleText("E-Mail Channel");

		Select cur_dd = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		cur_dd.selectByVisibleText(data.get(0).get("Column6"));

		Select ordType_dd = new Select(WebdriverWait.findElement("id", "orderType"));
		ordType_dd.selectByVisibleText(data.get(0).get("Column5"));

		Select numType_dd = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		numType_dd.selectByVisibleText("Sales Quote");

		WebdriverWait.findElement("xpath", "//*[@id='createQuote']/div/table/tbody[1]/tr[17]/td/input").click();

		Sales_Quotes_Edit.Capture_SO_QuoteID(sheetname);

	}

	public static void createQuoteFromCRM(List<HashMap<String, String>> data, String sheetName) {

		WebdriverWait.findElement("link", "Opportunities").click();
		WebdriverWait.findElement("id", "modifySearch").click();
		WebdriverWait.findElement("id", "salesOpportunityId").sendKeys(CRM_Opportunities.opp_ID);
		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[2]/a").click();

		WebdriverWait.findElement("link", "Create Quote").click();
		Select salesChn_dd = new Select(WebdriverWait.findElement("id", "salesChannelEnumId"));
		salesChn_dd.selectByVisibleText("E-Mail Channel");
		WebdriverWait.findElement("xpath", "//*[@type='submit']").click();
		String getSuccessMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		Sales_Quotes_softAssert.assertThat(getSuccessMsg).isEqualTo("Quote created successfully");
		String getQuoteID = WebdriverWait.findElement("xpath", "//*[@id='updateQuote']/div/table/tbody[1]/tr[1]/td[2]").getText();
		ExcelWriter.writeExcelFile(sheetName, 15, 3, getQuoteID);
	}



	//////////////////////////////////////// Negative flow
	//////////////////////////////////////// ////////////////////////////////////////////

	public static void Navigate_To_Create_SO_Quote() {
		WebdriverWait.findElement("link", "SALES").click();
		WebdriverWait.findElement("link", "Quotes").click();
		WebdriverWait.findElement("link", "Create Quote").click();
	}

	public static void Navigate_To_Quotes() {
		WebdriverWait.findElement("link", "SALES").click();
		WebdriverWait.findElement("link", "Quotes").click();
	}

	public static void click_Create_btn() {
		WebdriverWait.findElement("xpath", "//*[@id='createQuote']/div/table/tbody[1]/tr[17]/td/input").click();
	}

	public static void Validating_Quotes_date_range_with_invalid_date(List<HashMap<String, String>> data) {
		// Valid Date Range - From
		WebdriverWait.findElement("id", "validFromDate_i18n").sendKeys(data.get(8).get("Column1"));
		// Valid Date Range - Thru
		WebdriverWait.findElement("id", "validThruDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Search']").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_Quotes_softAssert.assertThat(validationMsg).isEqualTo(data.get(21).get("Column2"));
		WebdriverWait.findElement("id", "validFromDate_i18n").clear();
		WebdriverWait.findElement("id", "validThruDate_i18n").clear();
		// Issue Date Range - From
		WebdriverWait.findElement("id", "issueFromDate_i18n").sendKeys(data.get(8).get("Column1"));
		// Issue Date Range - Thru
		WebdriverWait.findElement("id", "issueThruDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Search']").click();
		String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_Quotes_softAssert.assertThat(validationMsg1).isEqualTo(data.get(22).get("Column2"));
	}

	public static void Create_SO_Quotes_without_Items_NegativeFlow(List<HashMap<String, String>> data_positive) throws InterruptedException {
		Navigate_To_Create_SO_Quote();
		WebdriverWait.findElement("name", "partyId").sendKeys(data_positive.get(0).get("Column4"));
		click_Create_btn();
		WebdriverWait.findElement("link", "Edit").click();
}

	public static void Create_SO_Quotes_NegativeFlow(List<HashMap<String, String>> data,
			List<HashMap<String, String>> data_positive, String sheetname) throws InterruptedException {

		Navigate_To_Create_SO_Quote();
		WebdriverWait.findElement("name", "partyId").sendKeys(data_positive.get(0).get("Column4"));
		WebdriverWait.findElement("id", "validThruDate_i18n").clear();
		click_Create_btn();
		Sales_Quotes_QuoteItems.Add_Products(data_positive);
		WebdriverWait.findElement("link", "Edit").click();
		Sales_Quotes_Edit.Capture_SO_QuoteID(sheetname);
//		String getSuccessMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
//		Sales_Quotes_softAssert.assertThat(getSuccessMsg).isEqualTo(data.get(12).get("Column17"));
	}

	public static String verify_Validation_Msg(List<HashMap<String, String>> data, int row, String colName) {
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_Quotes_softAssert.assertThat(validationMsg).isEqualTo(data.get(row).get(colName));
		return validationMsg;
	}

	public static void Validate_PartyID_with_InvalidData(List<HashMap<String, String>> data, String col, int msg_row,
			String colName) {

		for (int i = 0; i <= 5; i++) {
			WebdriverWait.findElement("id", "0_lookupId_partyId").sendKeys(data.get(i).get(col));
			click_Create_btn();
			verify_Validation_Msg(data, msg_row, colName);
			WebdriverWait.findElement("id", "0_lookupId_partyId").clear();
		}
	}

	public static void Validate_ValidThruDate_with_InvalidData(List<HashMap<String, String>> data_positive,
			List<HashMap<String, String>> data, int row, String col, int msg_row, String colName) {
		WebdriverWait.findElement("name", "partyId").sendKeys(data_positive.get(0).get("Column4"));
		WebdriverWait.findElement("id", "validThruDate_i18n").sendKeys(data.get(row).get(col));
		click_Create_btn();
		verify_Validation_Msg(data, msg_row, colName);
	}

	public static void Validating_without_Mandatory_details(List<HashMap<String, String>> data) {
		Select orgNameDD = new Select(WebdriverWait.findElement("id", "billToCustomerPartyId"));
		orgNameDD.selectByVisibleText("-Select-");
		WebdriverWait.findElement("id", "issueDate_i18n").clear();
		Select orderTypeDD = new Select(WebdriverWait.findElement("id", "orderType"));
		orderTypeDD.selectByVisibleText("-Select-");
		WebdriverWait.findElement("id", "validFromDate_i18n").clear();
		click_Create_btn();
		String path1 = "//*[@id='content-messages']/div/ul/li[";
		String path2 = "]";
		for (int i = 1; i <= 7; i++) {
			String all_ValidationMsg = WebdriverWait.findElement("xpath", path1 + i + path2).getText();
			if (i == 1) {
				Sales_Quotes_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(1).get("Column4"));
			} else if (i == 2) {
				Sales_Quotes_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(0).get("Column5"));
			} else if (i == 3) {
				Sales_Quotes_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(3).get("Column3"));
			} else if (i == 4) {
				Sales_Quotes_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(4).get("Column6"));
			} else if (i == 5) {
				Sales_Quotes_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(11).get("Column2"));
			} else if (i == 6) {
				Sales_Quotes_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(2).get("Column6"));
			} else if (i == 7) {
				Sales_Quotes_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(12).get("Column2"));
			}
		}
	}

	public static void Sales_Quote_assertions() {
		Sales_Quotes_softAssert.assertAll();
	}
}
