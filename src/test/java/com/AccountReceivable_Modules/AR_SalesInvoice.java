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

public class AR_SalesInvoice extends Base {

	public static Logger log = Logger.getLogger(Sales_SalesOrder.class);
	@Rule
	public static JUnitSoftAssertions SalesInvoice_softAssert = new JUnitSoftAssertions();

	public static void SO_InvoicePosting(List<HashMap<String, String>> data, int row) {

		WebdriverWait.findElement("link", "ACCOUNTS RECEIVABLE").click();
		WebdriverWait.findElement("link", "Sales Invoice").click();
		WebdriverWait.findElement("id", "invoiceId").sendKeys(data.get(row).get("Column6"));
		WebdriverWait.findElement("id", "submit").click();
		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a").click();

		AR_SalesInvoice_Header.SO_Invoice_Approve();
		AR_SalesInvoice_Overview.SO_Invoice_Post();

		System.out.println("SO Invoice ID's Posted Successfully");

	}

	public static void Invoice_Adj_Posting(List<HashMap<String, String>> data, int InvID) throws InterruptedException {

		WebdriverWait.findElement("link", "ACCOUNTS RECEIVABLE").click();
		WebdriverWait.findElement("link", "Sales Invoice").click();
		WebdriverWait.findElement("id", "invoiceId").sendKeys(data.get(InvID).get("Column6"));
		WebdriverWait.findElement("id", "submit").click();
		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a").click();

		AR_SalesInvoice_Applications.Inv_Adjustments(data);
		// AR_SalesInvoice_Applications.Val_AdjustedInv_Amt(data);
		AR_SalesInvoice_Header.SO_Invoice_Approve();
		AR_SalesInvoice_Overview.SO_Invoice_Post();

		// Thread.sleep(1000);
		//
		// WebdriverWait.findElement("xpath",
		// "//*[@id='content-main-section']/div[2]/div[2]/div[1]/ul/li[2]/a").click();
		// // Expanding
		// // the
		// // Applied
		// // payments
		// WebdriverWait.findElement("xpath",
		// "//*[@id='paymentsContainer']/table/tbody/tr/td[1]/a").click();//
		// Click
		// // PaymentID
		//
		// AP_Payments_Overview.SentPayment();
		// AP_Payments_Overview.ConfirmPayment();
	}

	//////////////////////////////////////Negative Scenarios////////////////////////////////////////////////////

	public static void Validating_SalesInvoice_date_range_with_invalid_date(List<HashMap<String, String>> data) {
		// Valid Invoice Date - From
		WebdriverWait.findElement("id", "fromInvoiceDate_i18n").sendKeys(data.get(8).get("Column1"));
		// Valid Invoice Date - Thru
		WebdriverWait.findElement("id", "thruInvoiceDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Search']").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoice_softAssert.assertThat(validationMsg).isEqualTo(data.get(25).get("Column2"));
		WebdriverWait.findElement("id", "fromInvoiceDate_i18n").clear();
		WebdriverWait.findElement("id", "thruInvoiceDate_i18n").clear();
		// Issue Due Date - From
		WebdriverWait.findElement("id", "fromDueDate_i18n").sendKeys(data.get(8).get("Column1"));
		// Issue Due Date - Thru
		WebdriverWait.findElement("id", "thruDueDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Search']").click();
		String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoice_softAssert.assertThat(validationMsg1).isEqualTo(data.get(26).get("Column2"));
//		// Valid Invoice Date - From
//		WebdriverWait.findElement("id", "fromInvoiceDate_i18n").sendKeys(data.get(8).get("Column1"));
//		// Issue Due Date - From
//		WebdriverWait.findElement("id", "fromDueDate_i18n").sendKeys(data.get(7).get("Column1"));
//		String validationMsg2 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
//		SalesInvoice_softAssert.assertThat(validationMsg2).isEqualTo(data.get(26).get("Column2"));
		}

	public static void clickCreateSalesInvoice() {
		WebdriverWait.findElement("link", "Create Sales Invoice").click();
	}

	public static void clickCreateBtn() {
		WebdriverWait.findElement("link", "Create").click();
	}

	public static void Validate_Cancel_button_navigating_to_SalesInvoiceSearchPage() {
		WebdriverWait.findElement("link", "Cancel").click();
		String verifySearchPageIsDisplayed = driver.getTitle();
		SalesInvoice_softAssert.assertThat(verifySearchPageIsDisplayed).isEqualTo("Crest: Sales Invoice");
	}

	public static void Validating_without_Mandatory_details(List<HashMap<String, String>> data) {

		clickCreateSalesInvoice();
		Select invoiceTypeDD = new Select(WebdriverWait.findElement("id", "invoiceTypeId"));
		invoiceTypeDD.selectByVisibleText("-Select-");
		Select orgDD = new Select(WebdriverWait.findElement("id", "organizationPartyId"));
		orgDD.selectByVisibleText("-Select-");
		WebdriverWait.findElement("id", "invoiceDate_i18n").clear();
		WebdriverWait.findElement("id", "dueDate_i18n").clear();
		clickCreateBtn();
		String path1 = "//*[@id='content-messages']/div/ul/li[";
		String path2 = "]";
		//Exception is there in the application need to alter the looping based on the bug fix
		for (int i = 1; i <= 9; i++) {
			String all_ValidationMsg = WebdriverWait.findElement("xpath", path1 + i + path2).getText();
			if (i == 1) {
				SalesInvoice_softAssert.assertThat(all_ValidationMsg).isEqualTo("Invoice Type is Required.");
			} else if (i == 2) {
				SalesInvoice_softAssert.assertThat(all_ValidationMsg).isEqualTo("Organization is Required.");
			} else if (i == 3) {
				SalesInvoice_softAssert.assertThat(all_ValidationMsg).isEqualTo("Party ID is Required.");
			} else if (i == 4) {
				SalesInvoice_softAssert.assertThat(all_ValidationMsg).isEqualTo("Invoice Date is Required.");
			} else if (i == 5) {
				SalesInvoice_softAssert.assertThat(all_ValidationMsg).isEqualTo("Shipping Address is Required.");
			}else if (i == 6) {
				SalesInvoice_softAssert.assertThat(all_ValidationMsg).isEqualTo("Party Billing Address is Required.");
			} else if (i == 7) {
				SalesInvoice_softAssert.assertThat(all_ValidationMsg).isEqualTo("Due Date is Required.");
			} else if (i == 8) {
				SalesInvoice_softAssert.assertThat(all_ValidationMsg).isEqualTo("Currency is Required.");
			} else if (i == 9) {
				SalesInvoice_softAssert.assertThat(all_ValidationMsg).isEqualTo("Numbering Type is Required.");
			}
		}
	}

	public static void verify_DueDate_by_removing_InvoiceDate(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("id", "invoiceDate_i18n").clear();
		String getDueDate = WebdriverWait.findElement("id", "dueDate_i18n").getText();
		if(getDueDate.contains(data.get(27).get("Column2"))) {
			SalesInvoice_softAssert.fail("Due Date is displayed as "+getDueDate);
		}else {
			SalesInvoice_softAssert.wasSuccess();
		}
	}

	public static String verify_PartyID_Validation_Msg_for_InvalidData(List<HashMap<String, String>> data, int row, String colName) {
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoice_softAssert.assertThat(validationMsg).isEqualTo(data.get(row).get(colName));
		return validationMsg;
	}

	public static String verify_PartyID_Validation_Msg_for_LeavingEmpty(List<HashMap<String, String>> data) {
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoice_softAssert.assertThat(validationMsg).isEqualTo(data.get(12).get("Column5"));
		return validationMsg;
	}

	public static void Validate_PartyID_with_InvalidData(List<HashMap<String, String>> data, String col, int msg_row,
			String colName) {

		AR_Main.clickSalesInvoice();
		clickCreateSalesInvoice();

		Select cur_dd = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		cur_dd.selectByIndex(2);
		WebdriverWait.findElement("id", "0_lookupId_partyIdTo").clear();
		for (int i = 0; i <= 5; i++) {
			if(i==3) {
				WebdriverWait.findElement("id", "0_lookupId_partyIdTo").clear();
			}else {
			WebdriverWait.findElement("id", "0_lookupId_partyIdTo").sendKeys(data.get(i).get(col));
			}
			clickCreateBtn();
			if(i==3) {
				verify_PartyID_Validation_Msg_for_LeavingEmpty(data);
			}else {
				verify_PartyID_Validation_Msg_for_InvalidData(data, msg_row, colName);
			}

		}
	}

	public static void Validate_description_with_more_than_255_Characters(
			List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {

		WebdriverWait.findElement("id", "0_lookupId_partyIdTo").clear();
		WebdriverWait.findElement("id", "0_lookupId_partyIdTo").sendKeys(data_Positive.get(0).get("Column4"));
		Select shipAddrr_dd = new Select(WebdriverWait.findElement("id", "toContactMechId"));
		shipAddrr_dd.selectByIndex(1);
		WebElement description = WebdriverWait.findElement("id", "description");
		description.sendKeys(data.get(6).get("Column1"));
		clickCreateBtn();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoice_softAssert.assertThat(validationMsg).isEqualTo("The maximum limit for Description is 255 characters.");
	}

	public static void Validate_ReferenceNo_with_more_than_255_Characters(
			List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {

		WebElement refno = WebdriverWait.findElement("id", "referenceNumber");
		refno.sendKeys(data.get(6).get("Column1"));
		clickCreateBtn();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoice_softAssert.assertThat(validationMsg).isEqualTo("The maximum limit for Reference No. is 255 characters.");
	}

	public static void Validate_without_entering_anydata(
			List<HashMap<String, String>> data) {
		AR_Main.clickAR();
		AR_Main.clickSalesInvoice();
		clickCreateSalesInvoice();
		clickCreateBtn();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		SalesInvoice_softAssert.assertThat(validationMsg).isEqualTo(data.get(12).get("Column5"));
		String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
		SalesInvoice_softAssert.assertThat(validationMsg1).isEqualTo(data.get(1).get("Column7"));
	}

	public static void Validate_OrderAggrrNterms_without_entering_anydata(
			List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "toggleLink").click();
		WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table[1]/tbody[1]/tr[5]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoice_softAssert.assertThat(validationMsg).isEqualTo(data.get(9).get("Column6"));
	}

	public static void Validate_OrderAggrrNterms_with_more_than_255_Characters(
			List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "toggleLink").click();
		Select shipAddrr_dd = new Select(WebdriverWait.findElement("id", "termTypeId"));
		shipAddrr_dd.selectByIndex(2);
		WebElement description = WebdriverWait.findElement("id", "termDescription");
		description.sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table[1]/tbody[1]/tr[5]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table[2]/tbody/tr[1]/td[7]").getText();
		SalesInvoice_softAssert.assertThat(validationMsg).isEqualTo(data.get(6).get("Column1"));
	}

	public static void Validate_OrderAggrrNterms_with_invalid_TermDays(
			List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "toggleLink").click();
		Select shipAddrr_dd = new Select(WebdriverWait.findElement("id", "termTypeId"));
		shipAddrr_dd.selectByIndex(2);
		WebElement description = WebdriverWait.findElement("id", "termDays");
		description.sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table[1]/tbody[1]/tr[5]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoice_softAssert.assertThat(validationMsg).isEqualTo(data.get(28).get("Column2"));
	}

	public static void Validate_OrderAggrrNterms_with_invalid_TermValue(
			List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "toggleLink").click();
		Select shipAddrr_dd = new Select(WebdriverWait.findElement("id", "termTypeId"));
		shipAddrr_dd.selectByIndex(2);
		WebElement description = WebdriverWait.findElement("id", "termValue");
		description.sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table[1]/tbody[1]/tr[5]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoice_softAssert.assertThat(validationMsg).isEqualTo(data.get(15).get("Column21"));
	}

	public static void Validate_AddlnParties_PartyID_with_invalidData(List<HashMap<String, String>> data, String col) {

		for (int i = 0; i <= 5; i++) {
			WebdriverWait.findElement("id", "toggleLinkParty").click();
			WebdriverWait.findElement("id", "1_lookupId_partyId").clear();
			if(i==3) {
				WebdriverWait.findElement("id", "1_lookupId_partyId").clear();
			}else {
			WebdriverWait.findElement("id", "1_lookupId_partyId").sendKeys(data.get(i).get(col));
			}
			WebdriverWait.findElement("id", "//*[@id='partyContainer']/table/tbody[1]/tr[3]/td/input").click();
			if(i==3) {
				String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
				SalesInvoice_softAssert.assertThat(validationMsg).isEqualTo(data.get(12).get("Column5"));
				String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
				SalesInvoice_softAssert.assertThat(validationMsg1).isEqualTo(data.get(10).get("Column6"));
			}else {
			String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
			SalesInvoice_softAssert.assertThat(validationMsg).isEqualTo(data.get(5).get("Column5"));
			String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
			SalesInvoice_softAssert.assertThat(validationMsg1).isEqualTo(data.get(10).get("Column6"));
			}
		}
	}

	public static void Validate_AddlnParties_without_RoleType(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {

		WebdriverWait.findElement("id", "toggleLinkParty").click();
		WebdriverWait.findElement("id", "1_lookupId_partyId").sendKeys(data_Positive.get(0).get("Column4"));
		WebdriverWait.findElement("id", "//*[@id='partyContainer']/table/tbody[1]/tr[3]/td/input").click();
		String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoice_softAssert.assertThat(validationMsg1).isEqualTo(data.get(10).get("Column6"));
	}

	public static void CreateSalesInvoice_For_NegFlow(
			List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		AR_Main.clickAR();
		AR_Main.clickSalesInvoice();
		clickCreateSalesInvoice();
		WebdriverWait.findElement("id", "0_lookupId_partyIdTo").sendKeys(data_Positive.get(0).get("Column4"));
		Select shipAddrr_dd = new Select(WebdriverWait.findElement("id", "toContactMechId"));
		shipAddrr_dd.selectByVisibleText("UIDAI - Karnataka");
		clickCreateBtn();
		}

	public static void SalesInvoice_assertions() {
		SalesInvoice_softAssert.assertAll();
	}
}
