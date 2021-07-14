package com.AccountReceivable_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Sales_Modules.Sales_SalesOrder;
import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AR_SalesInvoice_Header extends Base {
	@Rule
	public static JUnitSoftAssertions SalesInvoiceHeader_softAssert = new JUnitSoftAssertions();
	public static Logger log = Logger.getLogger(Sales_SalesOrder.class);

	public static void SO_Invoice_Approve() {

		WebdriverWait.findElement("link", "Header").click();
		WebdriverWait.findElement("id", "referenceNumber").sendKeys("1234");
		WebdriverWait.findElement("link", "Update").click();
		WebdriverWait.findElement("link", "Approve").click();
	}

	///////////////////////////////////////Negative Flows////////////////////////////////////////////////////////////

	public static void clickUpdateBtn() {
		WebdriverWait.findElement("link", "Update").click();
	}
	public static void Validate_by_updating_without_PartyID(
			List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		WebdriverWait.findElement("id", "0_lookupId_partyIdTo").clear();
		Select shipAddrr_dd = new Select(WebdriverWait.findElement("id", "toContactMechId"));
		shipAddrr_dd.selectByVisibleText("UIDAI - Karnataka");
		clickUpdateBtn();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		SalesInvoiceHeader_softAssert.assertThat(validationMsg).isEqualTo(data.get(12).get("Column5"));
		String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
		SalesInvoiceHeader_softAssert.assertThat(validationMsg1).isEqualTo(data.get(1).get("Column7"));
}

	public static void Validate_DueDate_when_updating_by_removing_InvoiceDate(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("id", "invoiceDate_i18n").clear();
		String getDueDate = WebdriverWait.findElement("id", "dueDate_i18n").getText();
		if(getDueDate.contains(data.get(27).get("Column2"))) {
			SalesInvoiceHeader_softAssert.fail("Due Date is displayed as "+getDueDate);
		}else {
			SalesInvoiceHeader_softAssert.wasSuccess();
		}
	}
	public static void Validate_by_updating_without_Currency(List<HashMap<String, String>> data) {
	Select currency_dd = new Select(WebdriverWait.findElement("id", "currencyUomId"));
	currency_dd.selectByVisibleText("-Select-");
	clickUpdateBtn();
	String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
	SalesInvoiceHeader_softAssert.assertThat(validationMsg).isEqualTo(data.get(1).get("Column7"));
	}

	public static String verify_PartyID_Validation_Msg_for_InvalidData(List<HashMap<String, String>> data, int row, String colName) {
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoiceHeader_softAssert.assertThat(validationMsg).isEqualTo(data.get(row).get(colName));
		return validationMsg;
	}

	public static String verify_PartyID_Validation_Msg_for_LeavingEmpty(List<HashMap<String, String>> data) {
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoiceHeader_softAssert.assertThat(validationMsg).isEqualTo(data.get(12).get("Column5"));
		return validationMsg;
	}

	public static void Validate_PartyID_with_InvalidData(List<HashMap<String, String>> data, String col, int msg_row,
			String colName) {
		Select cur_dd = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		cur_dd.selectByIndex(2);
		WebdriverWait.findElement("id", "0_lookupId_partyIdTo").clear();
		for (int i = 0; i <= 5; i++) {
			if(i==3) {
				WebdriverWait.findElement("id", "0_lookupId_partyIdTo").clear();
			}else {
			WebdriverWait.findElement("id", "0_lookupId_partyIdTo").sendKeys(data.get(i).get(col));
			}
			clickUpdateBtn();
			if(i==3) {
				verify_PartyID_Validation_Msg_for_LeavingEmpty(data);
			}else {
				verify_PartyID_Validation_Msg_for_InvalidData(data, msg_row, colName);
			}

		}
	}

	public static void Validate_description_with_more_than_255_Characters(
			List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		WebdriverWait.findElement("id", "0_lookupId_partyIdTo").sendKeys(data_Positive.get(0).get("Column4"));
		Select shipAddrr_dd = new Select(WebdriverWait.findElement("id", "toContactMechId"));
		shipAddrr_dd.selectByIndex(1);
		Select currency_dd = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		currency_dd.selectByVisibleText("INR");
		WebElement description = WebdriverWait.findElement("id", "description");
		description.sendKeys(data.get(6).get("Column1"));
		clickUpdateBtn();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoiceHeader_softAssert.assertThat(validationMsg).isEqualTo("The maximum limit for Description is 255 characters.");
	}

	public static void Validate_ReferenceNo_with_more_than_255_Characters(
			List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		WebdriverWait.findElement("id", "0_lookupId_partyIdTo").sendKeys(data_Positive.get(0).get("Column4"));
		Select shipAddrr_dd = new Select(WebdriverWait.findElement("id", "toContactMechId"));
		shipAddrr_dd.selectByIndex(1);
		Select currency_dd = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		currency_dd.selectByVisibleText("INR");
		WebElement description = WebdriverWait.findElement("id", "description");
		description.clear();
		WebElement refNo = WebdriverWait.findElement("id", "referenceNumber");
		refNo.sendKeys(data.get(6).get("Column1"));
		clickUpdateBtn();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoiceHeader_softAssert.assertThat(validationMsg).isEqualTo("The maximum limit for Reference No. is 255 characters.");
	}

	public static void Validate_OrderAggrrNterms_without_entering_anydata(
			List<HashMap<String, String>> data) {
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div[2]/div[1]/ul/li[2]/a").click();
		WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/div/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoiceHeader_softAssert.assertThat(validationMsg).isEqualTo(data.get(9).get("Column6"));
	}

	public static void Validate_OrderAggrrNterms_with_more_than_255_Characters(
			List<HashMap<String, String>> data) {
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div[2]/div[1]/ul/li[2]/a").click();
		Select shipAddrr_dd = new Select(WebdriverWait.findElement("id", "termTypeId"));
		shipAddrr_dd.selectByIndex(2);
		WebElement description = WebdriverWait.findElement("id", "termDescription");
		description.sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/div/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table[2]/tbody/tr[1]/td[7]").getText();
		SalesInvoiceHeader_softAssert.assertThat(validationMsg).isEqualTo(data.get(6).get("Column1"));
	}

	public static void Validate_OrderAggrrNterms_with_invalid_TermDays(
			List<HashMap<String, String>> data) {
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div[2]/div[1]/ul/li[2]/a").click();
		Select shipAddrr_dd = new Select(WebdriverWait.findElement("id", "termTypeId"));
		shipAddrr_dd.selectByIndex(2);
		WebElement description = WebdriverWait.findElement("id", "termDays");
		description.sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/div/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoiceHeader_softAssert.assertThat(validationMsg).isEqualTo(data.get(28).get("Column2"));
	}

	public static void Validate_OrderAggrrNterms_with_invalid_TermValue(
			List<HashMap<String, String>> data) {
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div[2]/div[1]/ul/li[2]/a").click();
		Select shipAddrr_dd = new Select(WebdriverWait.findElement("id", "termTypeId"));
		shipAddrr_dd.selectByIndex(2);
		WebElement description = WebdriverWait.findElement("id", "termValue");
		description.sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/div/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoiceHeader_softAssert.assertThat(validationMsg).isEqualTo(data.get(15).get("Column21"));
	}

	public static void Validate_AddlnParties_PartyID_with_invalidData(List<HashMap<String, String>> data, String col) {
		for (int i = 0; i <= 5; i++) {
			WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div[3]/div[1]/ul/li[2]/a").click();
			WebdriverWait.findElement("id", "1_lookupId_partyId").clear();
			if(i==3) {
				WebdriverWait.findElement("id", "1_lookupId_partyId").clear();
			}else {
			WebdriverWait.findElement("id", "1_lookupId_partyId").sendKeys(data.get(i).get(col));
			}
			WebdriverWait.findElement("id", "//*[@id='partyContainer']/div/input").click();
			if(i==3) {
				String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
				SalesInvoiceHeader_softAssert.assertThat(validationMsg).isEqualTo(data.get(12).get("Column5"));
				String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
				SalesInvoiceHeader_softAssert.assertThat(validationMsg1).isEqualTo(data.get(10).get("Column6"));
			}else {
			String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
			SalesInvoiceHeader_softAssert.assertThat(validationMsg).isEqualTo(data.get(5).get("Column5"));
			String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
			SalesInvoiceHeader_softAssert.assertThat(validationMsg1).isEqualTo(data.get(10).get("Column6"));
			}
		}
	}

	public static void Validate_AddlnParties_without_RoleType(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div[3]/div[1]/ul/li[2]/a").click();
		WebdriverWait.findElement("id", "1_lookupId_partyId").sendKeys(data_Positive.get(0).get("Column4"));
		WebdriverWait.findElement("id", "//*[@id='partyContainer']/div/input").click();
		String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoiceHeader_softAssert.assertThat(validationMsg1).isEqualTo(data.get(10).get("Column6"));
	}

	public static void Validate_Cancel_button_navigating_to_SalesInvoiceSearchPage() {
		WebdriverWait.findElement("link", "Cancel").click();
		String verifySearchPageIsDisplayed = driver.getTitle();
		SalesInvoiceHeader_softAssert.assertThat(verifySearchPageIsDisplayed).isEqualTo("Crest: Sales Invoice");
	}

	public static void Validate_BackToSearch_link_navigating_to_SalesInvoiceSearchPage() {
		WebdriverWait.findElement("link", "Back to Search").click();
		String verifySearchPageIsDisplayed = driver.getTitle();
		SalesInvoiceHeader_softAssert.assertThat(verifySearchPageIsDisplayed).isEqualTo("Crest: Sales Invoice");
		boolean isValidationMsgDisplayed = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").isDisplayed();
		SalesInvoiceHeader_softAssert.assertThat(isValidationMsgDisplayed).isEqualTo(false);
	}

	public static void SalesInvoiceHeader_softAssert() {
		SalesInvoiceHeader_softAssert.assertAll();
	}

}
