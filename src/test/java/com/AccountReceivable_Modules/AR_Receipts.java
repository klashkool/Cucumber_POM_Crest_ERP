package com.AccountReceivable_Modules;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.support.ui.Select;

import com.Sales_Modules.Sales_SalesOrder;
import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AR_Receipts extends Base {
	public static Logger log = Logger.getLogger(Sales_SalesOrder.class);

	@Rule
	public static JUnitSoftAssertions AR_Receipts_softAssert = new JUnitSoftAssertions();

	public static void Receipts(List<HashMap<String, String>> data, String sheetname, String PaymentType, int reciptID_Row, String UOM)
					throws InterruptedException {

		log.info("Receiving Payments for all the Invoice ID");
		WebdriverWait.findElement("link", "ACCOUNTS RECEIVABLE").click();
		WebdriverWait.findElement("link", "Receipts").click();// Receipts link
		WebdriverWait.findElement("link", "Create Receipt").click();// Create
																	// Receipts

		// Organization Dropdown
		Select Org_DD = new Select(WebdriverWait.findElement("id", "partyIdTo"));
		Org_DD.selectByVisibleText(data.get(0).get("Column3"));

		// Payment Type Dropdown
		Select Paytype_DD = new Select(WebdriverWait.findElement("id", "paymentTypeId"));
		Paytype_DD.selectByVisibleText("Customer Payment");

		// PartyID
		WebdriverWait.findElement("name", "partyIdFrom").sendKeys(data.get(0).get("Column4"));// Workphilia

		// Account Dropdown
		Select Acct_DD = new Select(WebdriverWait.findElement("id", "paymentMethodId"));
		Acct_DD.selectByVisibleText("ICICI-2960");

		// Payment Method
		Select Paymethd_DD = new Select(WebdriverWait.findElement("id", "paymentMethod"));
		Paymethd_DD.selectByVisibleText("Cash");

		// Numbering Type
		Select NumType_DD = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		NumType_DD.selectByVisibleText("Receve pay");

		WebdriverWait.findElement("id", "paymentRefNum").sendKeys("1234");

		// Amount
		WebdriverWait.findElement("id", "amount").sendKeys(data.get(17).get("Column13").replace("₹", "").replace(",", ""));

		// Currency
		Select Cur_DD = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		Cur_DD.selectByVisibleText(UOM);

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/table/tbody[1]/tr[10]/td/input").click();

		AR_Receipts_Header.ReceiptIDCapture(sheetname, reciptID_Row);
		AR_Receipts_Applications.SelectInvoiceID(data, PaymentType);
		AR_Receipts_Overview.ConfirmPayment();

		// if (PaymentType.contains("FullPayment")) {
		// Sales_SalesOrder_View.InvID_PaymntStatus_Val();
		// } else if (PaymentType.contains("PartialPayment")) {
		// Sales_SalesOrder_View.InvID_PartialPaymntStatus_Val();
		// } else if (PaymentType.contains("ExchangeRate")) {
		// Sales_SalesOrder_View.InvID_ExchangeRatePaymntStatus_Val();
		// }
	}

	public static void Receipts_PayTerm(List<HashMap<String, String>> data, String sheetname, String PaymentType, int reciptID_Row, String UOM)
					throws InterruptedException {

		log.info("Receiving Payments for all the Invoice ID");
		WebdriverWait.findElement("link", "ACCOUNTS RECEIVABLE").click();
		WebdriverWait.findElement("link", "Receipts").click();// Receipts link
		WebdriverWait.findElement("link", "Create Receipt").click();// Create
																	// Receipts

		// Organization Dropdown
		Select Org_DD = new Select(WebdriverWait.findElement("id", "partyIdTo"));
		Org_DD.selectByVisibleText(data.get(0).get("Column3"));

		// Payment Type Dropdown
		Select Paytype_DD = new Select(WebdriverWait.findElement("id", "paymentTypeId"));
		Paytype_DD.selectByVisibleText("Customer Payment");

		// PartyID
		WebdriverWait.findElement("name", "partyIdFrom").sendKeys(data.get(0).get("Column4"));// Workphilia

		// Account Dropdown
		Select Acct_DD = new Select(WebdriverWait.findElement("id", "paymentMethodId"));
		Acct_DD.selectByVisibleText("ICICI-2960");

		// Payment Method
		Select Paymethd_DD = new Select(WebdriverWait.findElement("id", "paymentMethod"));
		Paymethd_DD.selectByVisibleText("Cash");

		// Numbering Type
		Select NumType_DD = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		NumType_DD.selectByVisibleText("Receve pay");

		WebdriverWait.findElement("id", "paymentRefNum").sendKeys("1234");

		// Amount
		WebdriverWait.findElement("id", "amount").sendKeys(data.get(17).get("Column13").replace("₹", "").replace(",", ""));

		// Currency
		Select Cur_DD = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		Cur_DD.selectByVisibleText(UOM);

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/table/tbody[1]/tr[10]/td/input").click();

		AR_Receipts_Header.ReceiptIDCapture(sheetname, reciptID_Row);
		AR_Receipts_Applications.SelectInvoiceID_PayTerm(data, PaymentType);
		AR_Receipts_Overview.ConfirmPayment();

		// if (PaymentType.contains("FullPayment")) {
		// Sales_SalesOrder_View.InvID_PaymntStatus_Val();
		// } else if (PaymentType.contains("PartialPayment")) {
		// Sales_SalesOrder_View.InvID_PartialPaymntStatus_Val();
		// } else if (PaymentType.contains("ExchangeRate")) {
		// Sales_SalesOrder_View.InvID_ExchangeRatePaymntStatus_Val();
		// }
	}

	public static void CustomerDeposit(List<HashMap<String, String>> data, String sheetname, String Currency, int reciptID_Row) throws InterruptedException {

		WebdriverWait.findElement("link", "ACCOUNTS RECEIVABLE").click();
		WebdriverWait.findElement("link", "Receipts").click();
		WebdriverWait.findElement("link", "Create Receipt").click();

		// Organization Dropdown
		Select Org_DD = new Select(WebdriverWait.findElement("id", "partyIdTo"));
		Org_DD.selectByVisibleText(data.get(0).get("Column3"));

		// Payment Type Dropdown
		Select Paytype_DD = new Select(WebdriverWait.findElement("id", "paymentTypeId"));
		Paytype_DD.selectByVisibleText("Customer Deposit");

		// PartyID
		WebdriverWait.findElement("name", "partyIdFrom").sendKeys(data.get(0).get("Column4"));

		// Account Dropdown
		Select Acct_DD = new Select(WebdriverWait.findElement("id", "paymentMethodId"));
		Acct_DD.selectByVisibleText("ICICI-2960");

		// Payment Method
		Select Paymethd_DD = new Select(WebdriverWait.findElement("id", "paymentMethod"));
		Paymethd_DD.selectByVisibleText("Cash");

		// Numbering Type
		Select NumType_DD = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		NumType_DD.selectByVisibleText("Receve pay");

		// Reference Number
		Calendar sys = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String todayDate = (date.format(new Date()));
		String sysTime = (sdf.format(sys.getTime()));
		WebdriverWait.findElement("id", "paymentRefNum").sendKeys("RefNo: " + todayDate + sysTime);

		// Amount
		String InvValue = data.get(13).get("Column9");
		String Inv = InvValue.replace("₹", "").replace("$", "").replace(",", "");

		WebdriverWait.findElement("id", "amount").sendKeys(Inv);

		// Currency
		Select Cur_DD = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		Cur_DD.selectByVisibleText(Currency);

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/table/tbody[1]/tr[10]/td/input").click();

		AR_Receipts_Header.ReceiptIDCapture(sheetname, reciptID_Row);
		// AR_Receipts_Header.ReceiptStatus_Val();
	}

	public static void Confirm_CustomerDeposit(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "ACCOUNTS RECEIVABLE").click();
		WebdriverWait.findElement("link", "Receipts").click();
		WebdriverWait.findElement("id", "paymentId").sendKeys(data.get(13).get("Column10"));

		Select pmtType_DD = new Select(WebdriverWait.findElement("id", "paymentTypeId"));
		pmtType_DD.selectByVisibleText("Customer Deposit");

		WebdriverWait.findElement("id", "submit").click();

		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a").click();

		WebdriverWait.findElement("link", "Receive").click();
		WebdriverWait.findElement("link", "Confirm").click();
	}

	////////////////////////////////////// Negative
	////////////////////////////////////// flows//////////////////////////////////////////////////////

	public static void navigateToReceipts() {
		WebdriverWait.findElement("link", "ACCOUNTS RECEIVABLE").click();
		WebdriverWait.findElement("link", "Receipts").click();
	}

	public static void clickCreateReceipt() {
		WebdriverWait.findElement("link", "Create Receipt").click();
	}

	public static void clickCreateBtn() {
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/table/tbody[1]/tr[10]/td/input").click();
	}

	public static void Validating_Receipts_date_range_with_invalid_date(List<HashMap<String, String>> data) {
		// Valid Date Range - From
		WebdriverWait.findElement("id", "effectiveFromDate_i18n").sendKeys(data.get(8).get("Column1"));
		// Valid Date Range - Thru
		WebdriverWait.findElement("id", "effectiveThruDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Search']").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_Receipts_softAssert.assertThat(validationMsg).isEqualTo(data.get(30).get("Column2"));
	}

	public static void Validate_Cancel_button_navigating_to_SalesInvoiceSearchPage() {
		WebdriverWait.findElement("link", "Cancel").click();
		String verifySearchPageIsDisplayed = driver.getTitle();
		AR_Receipts_softAssert.assertThat(verifySearchPageIsDisplayed).isEqualTo("Crest: Receipts");
	}

	public static void Validating_without_Mandatory_details(List<HashMap<String, String>> data) {
		clickCreateReceipt();
		Select partyIdToDD = new Select(WebdriverWait.findElement("id", "partyIdTo"));
		partyIdToDD.selectByVisibleText("-Select-");
		Select paymentTypeIdDD = new Select(WebdriverWait.findElement("id", "paymentTypeId"));
		paymentTypeIdDD.selectByVisibleText("-Select-");
		WebdriverWait.findElement("id", "effectiveDate_i18n").clear();
		Select paymentMethodDD = new Select(WebdriverWait.findElement("id", "paymentMethod"));
		paymentMethodDD.selectByVisibleText("-Select-");
		clickCreateBtn();
		String path1 = "//*[@id='content-messages']/div/ul/li[";
		String path2 = "]";
		for (int i = 1; i <= 8; i++) {
			String all_ValidationMsg = WebdriverWait.findElement("xpath", path1 + i + path2).getText();
			if (i == 1) {
				AR_Receipts_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(13).get("Column5"));
			} else if (i == 2) {
				AR_Receipts_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(13).get("Column6"));
			} else if (i == 3) {
				AR_Receipts_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(18).get("Column21"));
			} else if (i == 4) {
				AR_Receipts_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(31).get("Column2"));
			} else if (i == 5) {
				AR_Receipts_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(19).get("Column21"));
			} else if (i == 6) {
				AR_Receipts_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(7).get("Column6"));
			} else if (i == 7) {
				AR_Receipts_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(1).get("Column7"));
			} else if (i == 8) {
				AR_Receipts_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(20).get("Column21"));
			}
		}
	}

	public static String verify_PartyID_Validation_Msg_for_InvalidData(List<HashMap<String, String>> data, int row, String colName) {
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_Receipts_softAssert.assertThat(validationMsg).isEqualTo(data.get(row).get(colName));
		return validationMsg;
	}

	public static String verify_PartyID_Validation_Msg_for_LeavingEmpty(List<HashMap<String, String>> data) {
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_Receipts_softAssert.assertThat(validationMsg).isEqualTo(data.get(15).get("Column5"));
		return validationMsg;
	}

	public static void Validate_PartyID_with_InvalidData(List<HashMap<String, String>> data, String col, int msg_row, String colName) {
		navigateToReceipts();
		clickCreateReceipt();
		Select paymentMethodId_dd = new Select(WebdriverWait.findElement("id", "paymentMethodId"));
		paymentMethodId_dd.selectByIndex(1);
		WebdriverWait.findElement("id", "0_lookupId_partyIdFrom").clear();
		WebdriverWait.findElement("id", "paymentRefNum").sendKeys("Test");
		for (int i = 0; i <= 6; i++) {
			if (i == 3) {
				WebdriverWait.findElement("id", "0_lookupId_partyIdFrom").clear();
			} else {
				WebdriverWait.findElement("id", "0_lookupId_partyIdFrom").sendKeys(data.get(i).get(col));
			}
			clickCreateBtn();
			if (i == 3) {
				verify_PartyID_Validation_Msg_for_LeavingEmpty(data);
			} else {
				verify_PartyID_Validation_Msg_for_InvalidData(data, msg_row, colName);
			}
		}
	}

	public static void Validate_RefNo_with_255Characters(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		navigateToReceipts();
		clickCreateReceipt();
		Select paymentMethodId_dd = new Select(WebdriverWait.findElement("id", "paymentMethodId"));
		paymentMethodId_dd.selectByIndex(1);
		WebdriverWait.findElement("id", "0_lookupId_partyIdFrom").sendKeys(data_Positive.get(0).get("Column4"));
		WebdriverWait.findElement("id", "paymentRefNum").sendKeys(data.get(6).get("Column1"));
		clickCreateBtn();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_Receipts_softAssert.assertThat(validationMsg).isEqualTo("The maximum limit for Reference No. is 60 characters.");
	}

	public static void Validate_comments_with_255Characters(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		navigateToReceipts();
		clickCreateReceipt();
		Select paymentMethodId_dd = new Select(WebdriverWait.findElement("id", "paymentMethodId"));
		paymentMethodId_dd.selectByIndex(1);
		WebdriverWait.findElement("id", "0_lookupId_partyIdFrom").sendKeys(data_Positive.get(0).get("Column4"));
		WebdriverWait.findElement("id", "paymentRefNum").sendKeys("Test");
		WebdriverWait.findElement("id", "comments").sendKeys(data.get(6).get("Column1"));
		clickCreateBtn();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_Receipts_softAssert.assertThat(validationMsg).isEqualTo("The maximum limit for Comment is 255 characters.");
	}

	public static void create_Receipt_for_Neg_Flow(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		navigateToReceipts();
		clickCreateReceipt();
		Select paymentMethodId_dd = new Select(WebdriverWait.findElement("id", "paymentMethodId"));
		paymentMethodId_dd.selectByIndex(1);
		WebdriverWait.findElement("id", "0_lookupId_partyIdFrom").sendKeys(data_Positive.get(0).get("Column4"));
		WebdriverWait.findElement("id", "paymentRefNum").sendKeys("Test");
		clickCreateBtn();
	}

	public static void Validate_Amount_with_InvalidData(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		navigateToReceipts();
		clickCreateReceipt();
		Select paymentTypeId_dd = new Select(WebdriverWait.findElement("id", "paymentTypeId"));
		paymentTypeId_dd.selectByVisibleText("Customer Deposit");
		Select paymentMethodId_dd = new Select(WebdriverWait.findElement("id", "paymentMethodId"));
		paymentMethodId_dd.selectByIndex(1);
		WebdriverWait.findElement("id", "0_lookupId_partyIdFrom").sendKeys(data_Positive.get(0).get("Column4"));
		WebdriverWait.findElement("id", "paymentRefNum").sendKeys("Test");
		for (int i = 0; i <= 4; i++) {
			WebdriverWait.findElement("id", "amount").clear();
			if (i == 2) {
				WebdriverWait.findElement("id", "amount").clear();
			} else if (i == 3) {
				WebdriverWait.findElement("id", "amount").sendKeys(data.get(i + 2).get("Column1"));
			} else if (i == 4) {
				WebdriverWait.findElement("id", "amount").sendKeys(data.get(i + 12).get("Column1"));
			} else {
				WebdriverWait.findElement("id", "amount").sendKeys(data.get(i).get("Column1"));
			}
			clickCreateBtn();
			if (i == 1 || i == 3) {
				String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				AR_Receipts_softAssert.assertThat(validationMsg).isEqualTo(data.get(14).get("Column12"));

			} else if (i == 2) {
				String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				AR_Receipts_softAssert.assertThat(validationMsg).isEqualTo(data.get(13).get("Column12"));

			} else {
				String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				AR_Receipts_softAssert.assertThat(validationMsg).isEqualTo("Amount is Invalid.");
			}
		}
	}

	public static void AR_Receipts_softAssert() {
		AR_Receipts_softAssert.assertAll();
	}
}
