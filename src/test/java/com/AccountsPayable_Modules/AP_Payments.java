package com.AccountsPayable_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.support.ui.Select;

import com.Procurement_Modules.Procurement_PurchaseOrder_View;
import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AP_Payments extends Base {

	@Rule
	public static JUnitSoftAssertions AP_Payments_softAssert = new JUnitSoftAssertions();

	public static Logger log = Logger.getLogger(AP_Payments.class);

	public static void Payments(List<HashMap<String, String>> data, String sheetname, String PaymentType, int row) throws InterruptedException {

		log.info("Making Payments for all the Invoice ID");
		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
		WebdriverWait.findElement("link", "Payments").click();// Payments link
		WebdriverWait.findElement("xpath", "//*[@id='searchContainer']//a").click();// Create
																					// Payments

		// Organization Dropdown
		Select Org_DD = new Select(WebdriverWait.findElement("id", "partyIdFrom"));
		Org_DD.selectByVisibleText(data.get(0).get("Column3"));

		// Payment Type Dropdown
		Select Paytype_DD = new Select(WebdriverWait.findElement("id", "paymentTypeId"));
		Paytype_DD.selectByVisibleText("Vendor Payment");

		// PartyID
		WebdriverWait.findElement("name", "partyIdTo").sendKeys(data.get(0).get("Column4"));

		// Account Dropdown
		Select Acct_DD = new Select(WebdriverWait.findElement("id", "paymentMethodId"));
		Acct_DD.selectByVisibleText("ICICI-2960");

		// Payment Method
		Select Paymethd_DD = new Select(WebdriverWait.findElement("id", "paymentMethod"));
		Paymethd_DD.selectByVisibleText("Cash");

		// Numbering Type
		Select NumType_DD = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		NumType_DD.selectByVisibleText("Payables Pay");

		// Currency
		// Select Cur_DD = new Select(WebdriverWait.findElement("id",
		// "currencyUomId"));
		// Cur_DD.selectByVisibleText("INR");

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/table/tbody[1]/tr[8]/td/input").click();

		AP_Payments_Header.PaymentIDCapture(sheetname, row);
		AP_Payments_Applications.SelectInvoiceID(data, PaymentType);
		AP_Payments_Overview.ConfirmPayment();

		if (PaymentType.contains("FullPayment")) {
			Procurement_PurchaseOrder_View.InvID_PaymntStatus_Val();
		} else if (PaymentType.contains("PartialPayment")) {
			Procurement_PurchaseOrder_View.InvID_PartialPaymntStatus_Val();
		} else if (PaymentType.contains("ExchangeRate")) {
			Procurement_PurchaseOrder_View.InvID_ExchangeRatePaymntStatus_Val();
		}
	}

	public static void PayTerm_Payments(List<HashMap<String, String>> data, String sheetname, int row) throws InterruptedException {

		log.info("Making Payments for all the Invoice ID");
		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
		WebdriverWait.findElement("link", "Payments").click();// Payments link
		WebdriverWait.findElement("xpath", "//*[@id='searchContainer']//a").click();// Create
																					// Payments

		// Organization Dropdown
		Select Org_DD = new Select(WebdriverWait.findElement("id", "partyIdFrom"));
		Org_DD.selectByVisibleText(data.get(0).get("Column3"));

		// Payment Type Dropdown
		Select Paytype_DD = new Select(WebdriverWait.findElement("id", "paymentTypeId"));
		Paytype_DD.selectByVisibleText("Vendor Payment");

		// PartyID
		WebdriverWait.findElement("name", "partyIdTo").sendKeys(data.get(0).get("Column4"));

		// Account Dropdown
		Select Acct_DD = new Select(WebdriverWait.findElement("id", "paymentMethodId"));
		Acct_DD.selectByVisibleText("ICICI-2960");

		// Payment Method
		Select Paymethd_DD = new Select(WebdriverWait.findElement("id", "paymentMethod"));
		Paymethd_DD.selectByVisibleText("Cash");

		// Numbering Type
		Select NumType_DD = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		NumType_DD.selectByVisibleText("Payables Pay");

		// Currency
		// Select Cur_DD = new Select(WebdriverWait.findElement("id",
		// "currencyUomId"));
		// Cur_DD.selectByVisibleText("INR");

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/table/tbody[1]/tr[8]/td/input").click();

		AP_Payments_Header.PaymentIDCapture(sheetname, row);
		AP_Payments_Applications.SelectInvoiceID_PayTerm(data);
		AP_Payments_Overview.ConfirmPayment();
		Procurement_PurchaseOrder_View.InvID_PaymntStatus_Val();
	}

	public static void VendorPrePayments(List<HashMap<String, String>> data, String sheetname, String Currency, int row) {

		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
		WebdriverWait.findElement("link", "Payments").click();// Payments
																							// link
		WebdriverWait.findElement("xpath", "//*[@id='searchContainer']//a").click();// Create
																					// Payments

		// Organization Dropdown
		Select Org_DD = new Select(WebdriverWait.findElement("id", "partyIdFrom"));
		Org_DD.selectByVisibleText(data.get(0).get("Column3"));

		// Payment Type Dropdown
		Select Paytype_DD = new Select(WebdriverWait.findElement("id", "paymentTypeId"));
		Paytype_DD.selectByVisibleText("Vendor Prepayment");

		// PartyID
		WebdriverWait.findElement("name", "partyIdTo").sendKeys(data.get(0).get("Column4"));

		// Account Dropdown
		Select Acct_DD = new Select(WebdriverWait.findElement("id", "paymentMethodId"));
		Acct_DD.selectByVisibleText("ICICI-2960");

		// Payment Method
		Select Paymethd_DD = new Select(WebdriverWait.findElement("id", "paymentMethod"));
		Paymethd_DD.selectByVisibleText("Cash");

		// Numbering Type
		Select NumType_DD = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		NumType_DD.selectByVisibleText("Payables Pay");

		// Currency
		Select Cur_DD = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		Cur_DD.selectByVisibleText(Currency);

		// Amount
		String InvValue = data.get(13).get("Column9");
		String Inv = InvValue.replace("₹", "").replace("$", "").replace(",", "");

		WebdriverWait.findElement("id", "amount").sendKeys(Inv);

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/table/tbody[1]/tr[8]/td/input").click();

		// AP_Payments_Header.PaymentStatus_Val();
		AP_Payments_Header.PaymentIDCapture(sheetname, row);
	}

	public static void Confirm_VendorPrePayment(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
		WebdriverWait.findElement("link", "Payments").click();// Payments link
		WebdriverWait.findElement("id", "paymentId").sendKeys(data.get(13).get("Column10"));

		Select pmtType_DD = new Select(WebdriverWait.findElement("id", "paymentTypeId"));
		pmtType_DD.selectByVisibleText("Vendor Prepayment");

		WebdriverWait.findElement("xpath", "//*[@id='searchCriteria']/table/tbody[1]/tr[7]/td/input").click();

		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[2]/a").click();

		AP_Payments_Overview.SentPayment();
		AP_Payments_Overview.ConfirmPayment();

	}

	//////////////////////////////////// Negative
	//////////////////////////////////// Flow//////////////////////////////////

	public static void AccountsPayableLink() {

		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
	}

	public static void PaymentsLink() {

		WebdriverWait.findElement("link", "Payments").click();
	}

	public static void CreatePaymentLink() {

		WebdriverWait.findElement("link", "Create Payment").click();
	}

	public static void InvalidDates_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "effectiveFromDate_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("id", "effectiveThruDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Search']").click();
		String AdjDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_Payments_softAssert.assertThat(AdjDate).isEqualTo(data.get(27).get("Column2"));

	}

	public static void MandatoryFields_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "adjustmentDate_i18n").clear();

		WebdriverWait.findElement("name", "getDoc").click();

		String supp = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String currency = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String AdjDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();

		AP_Payments_softAssert.assertThat(supp).isEqualTo(data.get(14).get("Column5"));
		AP_Payments_softAssert.assertThat(currency).isEqualTo(data.get(2).get("Column11"));
		AP_Payments_softAssert.assertThat(AdjDate).isEqualTo(data.get(29).get("Column2"));

	}

	public static void CreatePayment_MandatoryFields_Val(List<HashMap<String, String>> data) {

		Select org_DD = new Select(WebdriverWait.findElement("id", "partyIdFrom"));
		org_DD.selectByVisibleText("-Select-");

		Select pmtType_DD = new Select(WebdriverWait.findElement("id", "paymentTypeId"));
		pmtType_DD.selectByVisibleText("-Select-");

		Select pmtMethod_DD = new Select(WebdriverWait.findElement("id", "paymentMethod"));
		pmtMethod_DD.selectByVisibleText("-Select-");

		WebdriverWait.findElement("id", "effectiveDate_i18n").clear();

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/table/tbody[1]/tr[8]/td/input").click();

		String org = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String payType = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String acct = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();
		String payMethod = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[4]").getText();
		String date = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[5]").getText();
		String currency = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[6]").getText();
		String numType = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[7]").getText();

		AP_Payments_softAssert.assertThat(org).isEqualTo(data.get(2).get("Column4"));
		AP_Payments_softAssert.assertThat(payType).isEqualTo(data.get(7).get("Column6"));
		AP_Payments_softAssert.assertThat(acct).isEqualTo(data.get(8).get("Column6"));
		AP_Payments_softAssert.assertThat(payMethod).isEqualTo(data.get(9).get("Column6"));
		AP_Payments_softAssert.assertThat(date).isEqualTo(data.get(30).get("Column2"));
		AP_Payments_softAssert.assertThat(currency).isEqualTo(data.get(2).get("Column11"));
		AP_Payments_softAssert.assertThat(numType).isEqualTo(data.get(6).get("Column6"));

		driver.navigate().refresh();

	}

	public static void RefNo_255CharacVal(List<HashMap<String, String>> data) {

		// PartyID
		WebdriverWait.findElement("name", "partyIdTo").sendKeys("11680");

		// Account Dropdown
		Select Acct_DD = new Select(WebdriverWait.findElement("id", "paymentMethodId"));
		Acct_DD.selectByVisibleText("ICICI-2960");

		// Payment Method
		Select Paymethd_DD = new Select(WebdriverWait.findElement("id", "paymentMethod"));
		Paymethd_DD.selectByVisibleText("Cash");

		// Numbering Type
		Select NumType_DD = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		NumType_DD.selectByVisibleText("Payables Pay");

		WebdriverWait.findElement("id", "paymentRefNum").sendKeys(data.get(6).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/table/tbody[1]/tr[8]/td/input").click();

		String refNo = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_Payments_softAssert.assertThat(refNo).isEqualTo(data.get(7).get("Column13"));

		WebdriverWait.findElement("id", "paymentRefNum").clear();
	}

	public static void Comments_255CharacVal(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "comments").sendKeys(data.get(6).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/table/tbody[1]/tr[8]/td/input").click();

		String comments = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_Payments_softAssert.assertThat(comments).isEqualTo(data.get(3).get("Column13"));

		WebdriverWait.findElement("id", "comments").clear();

	}

	public static void CreatePayments() {

		// PartyID
		WebdriverWait.findElement("name", "partyIdTo").sendKeys("11680");

		// Account Dropdown
		Select Acct_DD = new Select(WebdriverWait.findElement("id", "paymentMethodId"));
		Acct_DD.selectByVisibleText("ICICI-2960");

		// Payment Method
		Select Paymethd_DD = new Select(WebdriverWait.findElement("id", "paymentMethod"));
		Paymethd_DD.selectByVisibleText("Cash");

		// Numbering Type
		Select NumType_DD = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		NumType_DD.selectByVisibleText("Payables Pay");

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/table/tbody[1]/tr[8]/td/input").click();

	}

	public static void AP_Payments_softAssert() {
		AP_Payments_softAssert.assertAll();
	}

}
