package com.AccountsPayable_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AP_PurchaseInvoices extends Base {

	public static Logger log = Logger.getLogger(AP_PurchaseInvoices.class);

	@Rule
	public static JUnitSoftAssertions PurchaseInvoice_softAssert = new JUnitSoftAssertions();

	public static void Invoice_Posting(List<HashMap<String, String>> data, int InvID) throws InterruptedException {

		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
		WebdriverWait.findElement("link", "Purchase Invoices").click();
		WebdriverWait.findElement("id", "invoiceId").sendKeys(data.get(InvID).get("Column6"));
		WebdriverWait.findElement("xpath", "//*[@id='FindPurchaseInvoice']/table/tbody[1]/tr[7]/td/input").click();// Search
																													// Button
		// WebdriverWait.findElement("xpath",
		// "//*[@id='example']/tbody/tr/td[1]/a").click();
		Thread.sleep(500);

		WebElement element = WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();

		AP_PurchaseInvoices_Header.PI_Approve();
		AP_PurchaseInvoices_Overview.PI_Post();

		System.out.println("PO Invoice ID's Posted Successfully");
	}

	// Invoice posting method with looping - Old Code
	// public static void InvoicePosting(List<HashMap<String, String>> data, int
	// loopcount) throws InterruptedException {
	//
	// log.info("Validating and Posting all the Invoice ID's");
	// int j = 13;
	// for (int a = 1; a <= loopcount; a++) {
	//
	// WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
	// WebdriverWait.findElement("link", "Purchase Invoices").click();
	// WebdriverWait.findElement("id",
	// "invoiceId").sendKeys(data.get(j).get("Column6"));
	// WebdriverWait.findElement("xpath",
	// "//*[@id='FindPurchaseInvoice']/table/tbody[1]/tr[7]/td/input").click();//
	// Search
	// // Button
	// WebdriverWait.findElement("xpath", "//*[@id=
	// 'example']/tbody/tr/td[1]/a").click();
	//
	// AP_PurchaseInvoices_Header.PI_Approve();
	// AP_PurchaseInvoices_Overview.PI_SubTotal_Validations(data, j);
	// AP_PurchaseInvoices_Overview.PI_Tax_Val(data, j);
	// AP_PurchaseInvoices_Overview.PI_GrdTotal_Val(data, j);
	// AP_PurchaseInvoices_Overview.PI_Post();
	// j++;
	// }
	//
	// System.out.println("PO Invoice ID's Posted Successfully");
	// }
	//
	// }

	public static void Invoice_Adj_Posting(List<HashMap<String, String>> data, int InvID) throws InterruptedException {

		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
		WebdriverWait.findElement("link", "Purchase Invoices").click();
		WebdriverWait.findElement("id", "invoiceId").sendKeys(data.get(InvID).get("Column6"));
		WebdriverWait.findElement("xpath", "//*[@id='FindPurchaseInvoice']/table/tbody[1]/tr[7]/td/input").click();// Search
																													// Button
		WebdriverWait.findElement("xpath", "//*[@id= 'example']/tbody/tr/td[1]/a").click();

		AP_PurchaseInvoices_Applications.Inv_Adjustments(data);
		// AP_PurchaseInvoices_Applications.Val_AdjustedInv_Amt(data);
		AP_PurchaseInvoices_Header.PI_Approve();
		AP_PurchaseInvoices_Overview.PI_Post();

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

	public static void CreateAddtnlCostInvoice_Link() {
		WebdriverWait.findElement("link", "Create Additional Cost Invoice").click();
	}

	public static void CreateAddtnlCostInvoice(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "partyIdFrom").sendKeys("10020");

		Select recvAddrss = new Select(WebdriverWait.findElement("id", "toContactMechId"));
		recvAddrss.selectByVisibleText("UIDAI - Karnataka");

		Select billingAddrss = new Select(WebdriverWait.findElement("id", "fromContactMechId"));
		billingAddrss.selectByVisibleText("Metal shop");

		WebdriverWait.findElement("id", "referenceNumber").sendKeys("1234");

		WebdriverWait.findElement("link", "Create").click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

	///////////////////////////////////////// Negative
	///////////////////////////////////////// Flow/////////////////////////////////////////////////

	public static void PurchaseInvoice_Navigation() {
		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
		WebdriverWait.findElement("link", "Purchase Invoices").click();
	}

	public static void CreatePI_Link() {

		WebdriverWait.findElement("link", "Create Purchase Invoice").click();
	}

	public static void InvoiceSearch(List<HashMap<String, String>> data) throws InterruptedException {

		PurchaseInvoice_Navigation();
		WebdriverWait.findElement("id", "invoiceId").sendKeys(data.get(13).get("Column6"));
		WebdriverWait.findElement("xpath", "//*[@id='FindPurchaseInvoice']/table/tbody[1]/tr[7]/td/input").click();// Search
																													// Button
		// WebdriverWait.findElement("xpath",
		// "//*[@id='example']/tbody/tr/td[1]/a").click();
		Thread.sleep(500);

		WebElement element = WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public static void Invalid_InvoiceDate_DueDate_Val(List<HashMap<String, String>> data) {

		// Invoice date with past date as Thru date
		WebdriverWait.findElement("id", "fromInvoiceDate_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("id", "thruInvoiceDate_i18n").sendKeys(data.get(7).get("Column1"));

		// Due Date Range with past date as Thru date
		WebdriverWait.findElement("id", "fromDueDate_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("id", "thruDueDate_i18n").sendKeys(data.get(7).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='FindPurchaseInvoice']/table/tbody[1]/tr[7]/td/input").click();

		String InvoiceDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String DueDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();

		PurchaseInvoice_softAssert.assertThat(InvoiceDate).isEqualTo(data.get(23).get("Column2"));
		PurchaseInvoice_softAssert.assertThat(DueDate).isEqualTo(data.get(24).get("Column2"));

	}

	public static void MandatoryFields_Val_CreatePIPage(List<HashMap<String, String>> data) {

		Select invType = new Select(WebdriverWait.findElement("id", "invoiceTypeId"));
		invType.selectByVisibleText("-Select-");

		Select org = new Select(WebdriverWait.findElement("id", "organizationPartyId"));
		org.selectByVisibleText("-Select-");

		WebdriverWait.findElement("id", "invoiceDate_i18n").clear();
		WebdriverWait.findElement("id", "dueDate_i18n").clear();

		Select numType = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		numType.selectByVisibleText("-Select-");

		WebdriverWait.findElement("link", "Create").click();

		String invTypeReq = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String orgReq = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String invoiceDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();
		String dueDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[4]").getText();
		String refNo = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[5]").getText();
		String numType1 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[6]").getText();

		PurchaseInvoice_softAssert.assertThat(invTypeReq).isEqualTo(data.get(19).get("Column2"));
		PurchaseInvoice_softAssert.assertThat(orgReq).isEqualTo(data.get(20).get("Column2"));

		// Need to complete

	}

	public static void Invalid_DueDate_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "dueDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("link", "Create").click();
		String DueDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[5]").getText();
		PurchaseInvoice_softAssert.assertThat(DueDate).isEqualTo("Due Date is Required.");
	}

	public static void Desc_255CharacVal_PICreatePage(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "partyIdFrom").sendKeys("11680");

		Select recvAddrs = new Select(WebdriverWait.findElement("id", "toContactMechId"));
		recvAddrs.selectByVisibleText("UIDAI - Karnataka");

		WebdriverWait.findElement("id", "description").sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("id", "referenceNumber").sendKeys("Test");
		WebdriverWait.findElement("link", "Create").click();
		Alert alert = driver.switchTo().alert();
		alert.accept();

		// need to keep validations
	}

	public static void CancelBtnVal_CreatePIPage() {

		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
		WebdriverWait.findElement("link", "Purchase Invoices").click();
		WebdriverWait.findElement("link", "Create Purchase Invoice").click();
		WebdriverWait.findElement("link", "Cancel").click();

		WebElement link = WebdriverWait.findElement("link", "Create Purchase Invoice");
		PurchaseInvoice_softAssert.assertThat(link.isDisplayed());
	}

	public static void ApproveInv_WithoutRefNo_Duedate(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Approve").click();
		String dueDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String refNo = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();

		PurchaseInvoice_softAssert.assertThat(dueDate).isEqualTo(data.get(25).get("Column2"));
		PurchaseInvoice_softAssert.assertThat(refNo).isEqualTo(data.get(20).get("Column21"));
	}

	public static void VoidInv_WithoutReason(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Void").click();
		WebdriverWait.findElement("xapth", "//*[@id='content-main-section']/form/div/div[2]/table/tbody[1]/tr[2]/td/input").click();

		String voidReason = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PurchaseInvoice_softAssert.assertThat(voidReason).isEqualTo("Void Reason is Required");
	}

	public static void VoidInv_WithoutVoidDate(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Void").click();
		WebdriverWait.findElement("id", "voidDate_i18n").clear();
		WebdriverWait.findElement("id", "voidReason").sendKeys("Test");
		WebdriverWait.findElement("xapth", "//*[@id='content-main-section']/form/div/div[2]/table/tbody[1]/tr[2]/td/input").click();

		String voidDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PurchaseInvoice_softAssert.assertThat(voidDate).isEqualTo(data.get(26).get("Column2"));

		WebdriverWait.findElement("link", "Back to Invoice").click();
	}

	public static void BackToInvoiceLink_Val() {

		WebdriverWait.findElement("link", "Back to Invoice").click();

		String header = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[1]/ul/li[1]").getText();
		PurchaseInvoice_softAssert.assertThat(header).isEqualTo("Purchase Invoice");
	}

	public static void CopyLink_Val() {

		WebdriverWait.findElement("link", "Copy").click();

		// Need to code
	}

	public static void PurchaseInvoice_softAssert() {
		PurchaseInvoice_softAssert.assertAll();
	}
}
