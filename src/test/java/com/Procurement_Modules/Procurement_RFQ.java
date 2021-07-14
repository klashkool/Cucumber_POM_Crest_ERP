package com.Procurement_Modules;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Procurement_RFQ extends Base {

	public static Logger log = Logger.getLogger(Procurement_RFQ.class);

	@Rule
	public static JUnitSoftAssertions PO_RFQ_softAssert = new JUnitSoftAssertions();

	public static void PO_RFQ(List<HashMap<String, String>> data, String sheetname, String orderType) throws InterruptedException {

		log.info("Creating RFQ for PO");

		Calendar sys = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String todayDate = (date.format(new Date()));
		String sysTime = (sdf.format(sys.getTime()));

		WebdriverWait.findElement("xpath", "//*[@id='custRequestName']").sendKeys(todayDate + sysTime);

		// Organization DD
		Select rfqOrg_dd = new Select(WebdriverWait.findElement("id", "fromPartyId"));
		rfqOrg_dd.selectByVisibleText(data.get(0).get("Column3"));

		// Adding Request Date
		// Adding Current Date(From Date)
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String Fromdate = (dateFormat.format(new Date()));
		WebdriverWait.findElement("name", "custRequestDate_i18n").clear();
		WebdriverWait.findElement("name", "custRequestDate_i18n").sendKeys(Fromdate);

		// Response Required Date
		// Adding 10days extra from Current Date(ToDate)
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 10);
		String newDate = dateFormat1.format(cal.getTime());
		WebdriverWait.findElement("name", "responseRequiredDate_i18n").sendKeys(newDate);

		// Numbering Type DD
		Select numType_dd = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		numType_dd.selectByVisibleText("RFQ");

		// Category DD
		Select category_dd = new Select(WebdriverWait.findElement("id", "custRequestCategoryId"));
		category_dd.selectByVisibleText("From Requirement");

		if (orderType.contains("PO Supplies to Exp")) {

			WebdriverWait.findElement("name", "partyId").sendKeys(data.get(0).get("Column4"));

			// WebdriverWait.findElement("id", "noteName").click();
			//
			// Select roleType_dd = new Select(WebdriverWait.findElement("id",
			// "roleTypeId"));
			// roleType_dd.selectByVisibleText("Supplier");

			WebdriverWait.findElement("name", "submitRole").click();

			Select numType2_dd = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
			numType2_dd.selectByVisibleText("RFQ");

		}

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/a[1]").click();// Create
																								// Button

		Procurement_RFQ_RFQItem.RFQ_Status_Val();
		Procurement_RFQ_RFQItem.RFQ_LoadItems(data);
		Procurement_RFQ_RFQHeader.RFQ_ID_Capture(sheetname);
		Procurement_RFQ_RFQHeader.Update_QuoteInfo();

	}

	////////////////////////////////////////// Negative
	////////////////////////////////////////// Flow//////////////////////////////////////

	public static void RFQ_Navigation() {

		WebdriverWait.findElement("link", "PROCUREMENT").click();
		WebdriverWait.findElement("link", "Request For Quote").click();
	}

	public static void Create_RFQ_Link() {
		WebdriverWait.findElement("link", "Create Request For Quote").click();
	}

	public static void RFQ_SearchPage_InvalidDate_Val(List<HashMap<String, String>> data) {

		// RFQ Date Range with past date as Thru date
		WebdriverWait.findElement("id", "custRequestFromDate_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("id", "custRequestThruDate_i18n").sendKeys(data.get(7).get("Column1"));

		// Response Required Date Range with past date as Thru date
		WebdriverWait.findElement("id", "responseRequiredFromDate_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("id", "responseRequiredThruDate_i18n").sendKeys(data.get(7).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='searchCriteria']/table/tbody[1]/tr[6]/td/input").click();

		String rfq_ThruDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String resReq_ThruDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();

		PO_RFQ_softAssert.assertThat(rfq_ThruDate).isEqualTo(data.get(17).get("Column2"));
		PO_RFQ_softAssert.assertThat(resReq_ThruDate).isEqualTo(data.get(18).get("Column2"));

	}

	public static void RFQ_Mandatory_Fields_CreateRFQTable(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "custRequestDate_i18n").clear();
		WebdriverWait.findElement("link", "Create").click();

		String rfq_Name = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String res_date = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String num_Type = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();
		String category = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[4]").getText();

		PO_RFQ_softAssert.assertThat(rfq_Name).isEqualTo(data.get(1).get("Column21"));
		PO_RFQ_softAssert.assertThat(res_date).isEqualTo(data.get(5).get("Column2"));
		PO_RFQ_softAssert.assertThat(num_Type).isEqualTo(data.get(2).get("Column6"));
		PO_RFQ_softAssert.assertThat(category).isEqualTo(data.get(2).get("Column21"));

	}

	public static void Invalid_ResponseReqDate_CreateRFQTable(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "custRequestName").sendKeys("Test");

		// Numbering Type DD
		Select numType_dd = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		numType_dd.selectByVisibleText("RFQ");

		// Category DD
		Select category_dd = new Select(WebdriverWait.findElement("id", "custRequestCategoryId"));
		category_dd.selectByVisibleText("From Requirement");

		WebdriverWait.findElement("id", "responseRequiredDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("link", "Create").click();

		String res_date = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li").getText();
		PO_RFQ_softAssert.assertThat(res_date).isEqualTo(data.get(14).get("Column2"));

	}

	public static void RFQ_Mandatory_Fields_SupplierTable(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "submitRole").click();

		String partyID = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_RFQ_softAssert.assertThat(partyID).isEqualTo(data.get(6).get("Column5"));

	}

	public static void RFQ_AddSupplier(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "partyId").sendKeys("11680");


		// Select roleType_dd = new Select(
		// WebdriverWait.findElement("xpath",
		// "/html/body/div[3]/div[4]/div/div[1]/form/div[2]/div[2]/div/table/tbody[1]/tr[1]/td[4]/select"));
		// roleType_dd.selectByVisibleText("Supplier");

		WebdriverWait.findElement("name", "submitRole").click();

		String addPartyMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		PO_RFQ_softAssert.assertThat(addPartyMsg).isEqualTo("Successfully Added Supplier to the RFQ");

	}

	public static void RFQ_AddSameSupplierTwice(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "partyId").sendKeys("11680");

		WebdriverWait.findElement("name", "submitRole").click();
		String partyID = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_RFQ_softAssert.assertThat(partyID).isEqualTo("Entered Party is already added.");

	}

	public static void RFQ_RemoveSupplier(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "removeRole").click();
		String addPartyMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		PO_RFQ_softAssert.assertThat(addPartyMsg).isEqualTo(data.get(1).get("Column18"));

		// Adding the Removed Party again
		WebdriverWait.findElement("name", "partyId").sendKeys("11680");
		WebdriverWait.findElement("name", "submitRole").click();
	}

	public static void RFQ_Mandatory_Fields_RFQNotesTable(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "submitNote").click();

		String notes = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_RFQ_softAssert.assertThat(notes).isEqualTo(data.get(0).get("Column13"));

	}

	public static void AddingNotes_255Char(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "noteName").sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("id", "noteInfo").sendKeys(data.get(6).get("Column1"));

		WebdriverWait.findElement("name", "submitNote").click();

		String noteName = WebdriverWait.findElement("xpath", "//*[@id='noteContainer']/table[2]/tbody/tr/td[1]").getText();
		noteName.length();

		PO_RFQ_softAssert.assertThat(noteName.length()).isEqualTo("255");

		String noteInfo = WebdriverWait.findElement("xpath", "//*[@id='noteContainer']/table[2]/tbody/tr[1]/td[2]").getText();
		noteInfo.length();

		PO_RFQ_softAssert.assertThat(noteInfo.length()).isEqualTo("255");

	}

	public static void RemovingNotes(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='noteContainer']/table[2]/tbody/tr/td[3]/input[1]").click();
		String removeMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		PO_RFQ_softAssert.assertThat(removeMsg).isEqualTo(data.get(3).get("Column17"));

	}

	public static void PO_Manual_RFQ(List<HashMap<String, String>> data, String RFQType) throws InterruptedException {

		log.info("Creating RFQ for PO");

		Calendar sys = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String todayDate = (date.format(new Date()));
		String sysTime = (sdf.format(sys.getTime()));

		WebdriverWait.findElement("xpath", "//*[@id='custRequestName']").sendKeys(todayDate + sysTime);

		// Organization DD
		Select rfqOrg_dd = new Select(WebdriverWait.findElement("id", "fromPartyId"));
		rfqOrg_dd.selectByVisibleText(data.get(0).get("Column3"));

		// Adding Request Date
		// Adding Current Date(From Date)
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String Fromdate = (dateFormat.format(new Date()));
		WebdriverWait.findElement("name", "custRequestDate_i18n").clear();
		WebdriverWait.findElement("name", "custRequestDate_i18n").sendKeys(Fromdate);

		// Response Required Date
		// Adding 10days extra from Current Date(ToDate)
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 10);
		String newDate = dateFormat1.format(cal.getTime());
		WebdriverWait.findElement("name", "responseRequiredDate_i18n").sendKeys(newDate);

		// Numbering Type DD
		Select numType_dd = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		numType_dd.selectByVisibleText("RFQ");

		// Category DD
		Select category_dd = new Select(WebdriverWait.findElement("id", "custRequestCategoryId"));
		category_dd.selectByVisibleText(RFQType);

		WebdriverWait.findElement("name", "partyId").sendKeys(data.get(0).get("Column4"));
		WebdriverWait.findElement("name", "submitRole").click();

		Select numType2_dd = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		numType2_dd.selectByVisibleText("RFQ");

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/a[1]").click();// Create
																								// Button
	}

	public static void PO_RFQ_assertions() {
		PO_RFQ_softAssert.assertAll();
	}

}
