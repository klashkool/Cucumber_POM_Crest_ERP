package com.Procurement_Modules;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Crest_ERP_Login.Crest_Login;
import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Procurement_PurchaseOrder_View extends Base {

	public static String po_Number;

	public static JUnitSoftAssertions PO_View_softAssert = new JUnitSoftAssertions();

	public static void POnum_Capture(String sheetname) {

		String PO_Num = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div//table//tbody//tr[1]//td[2]//a").getText();

		System.out.println("PO Number = " + PO_Num);
		po_Number = PO_Num;
		ExcelWriter.writeExcelFile(sheetname, 15, 4, PO_Num);

	}

	public static void POnum_Capture_Return(String sheetname) {

		String PO_Num = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div//table//tbody//tr[1]//td[2]//a").getText();

		System.out.println("PO Number = " + PO_Num);
		po_Number = PO_Num;
		ExcelWriter.writeExcelFile(sheetname, 15, 4, PO_Num);

	}
	public static void PO_Tax_Val(List<HashMap<String, String>> data, String Header) {

		// Procurement_PurchaseOrder.Search_PO(data);

		log.info("Validating Total Tax");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[8]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[8]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();
			System.out.println("TotatlTax" + totalTax);

			if (totalTax.contains("Tax Amount (Consolidated)")) {

				String TotalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[8]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");
				PO_View_softAssert.assertThat(TotalTax).isEqualTo(data.get(17).get(Header));
				System.out.println("Tax Value validated successfully");
			}
		}
	}

	public static void PayTerm_Val(List<HashMap<String, String>> data) {

		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='orderTermsOriginal']/tbody/tr[1]/td[5]").getText().replace(",", "");
		PO_View_softAssert.assertThat(payterm1).isEqualTo(data.get(17).get("Column18"));

		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='orderTermsOriginal']/tbody/tr[2]/td[5]").getText().replace(",", "");
		PO_View_softAssert.assertThat(payterm2).isEqualTo(data.get(17).get("Column19"));

	}

	public static void PO_SubTotal_Val(List<HashMap<String, String>> data, String Header) {

		log.info("Validating SubTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[8]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String subTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[8]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (subTotal.contains("Sub Total")) {

				String SubTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[8]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");
				System.out.println("SubTotal" + SubTotal);
				PO_View_softAssert.assertThat(SubTotal).isEqualTo(data.get(17).get(Header));
				System.out.println("Sub Total validated successfully");
			}
		}
	}

	public static void PO_Approve() {

		log.info("Approve PO for Inv");
		WebdriverWait.findElement("link", "Review Order").click();
		WebdriverWait.findElement("link", "Review 2 Order").click();
		WebdriverWait.findElement("link", "Approve Order").click();
		String App_Status_Val = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[5]/td[2]").getText();
		Assert.assertEquals("Approved", App_Status_Val);
		log.info("Status validated successfully as Approved");

	}

	public static void CaptureInvID(String sheetname) {

		log.info("Receiving Inventory for all the products");
		int j = 15;

		List<WebElement> InvTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[7]/div[2]/table/tbody");

		for (int Inv = 1; Inv <= InvTable.size(); Inv++) {

			System.out.println(InvTable.size());

			String bpath = "//*[@id='content-main-section']/div[7]/div[2]/table/tbody[";
			String apath = "]/tr/td[1]/a";// Inv ID

			String InvID = WebdriverWait.findElement("xpath", bpath + Inv + apath).getText();

			ExcelWriter.writeExcelFile(sheetname, j, 6, InvID);
			j++;
		}
	}

	public static void InvID_PaymntStatus_Val() {

		JUnitSoftAssertions FullPay_softAssert = new JUnitSoftAssertions();
		List<WebElement> InvID_List = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[7]/div[2]/table/tbody/tr");
		// System.out.println(InvID_List.size());

		for (int Inv = 1; Inv <= InvID_List.size(); Inv++) {
			String Inv_sts = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[7]/div[2]/table/tbody[" + Inv + "]/tr/td[4]").getText();
			// Assert.assertEquals("Paid", Inv_sts);
			FullPay_softAssert.assertThat(Inv_sts).isEqualTo("Paid");
			FullPay_softAssert.assertAll();
		}
	}

	public static void InvID_PartialPaymntStatus_Val() {

		JUnitSoftAssertions PartialPay_softAssert = new JUnitSoftAssertions();
		List<WebElement> InvID_List = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[7]/div[2]/table/tbody/tr");
		// System.out.println(InvID_List.size());

		for (int Inv = 1; Inv <= InvID_List.size(); Inv++) {
			String Inv_sts = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[7]/div[2]/table/tbody[" + Inv + "]/tr/td[4]").getText();

			if (Inv == 1) {
				// Assert.assertEquals("Posted", Inv_sts);
				PartialPay_softAssert.assertThat(Inv_sts).isEqualTo("Posted");
			} else {
				// Assert.assertEquals("Paid", Inv_sts);
				PartialPay_softAssert.assertThat(Inv_sts).isEqualTo("Paid");
			}

			PartialPay_softAssert.assertAll();
		}
	}

	public static void InvID_ExchangeRatePaymntStatus_Val() {

		JUnitSoftAssertions ExchangePay_softAssert = new JUnitSoftAssertions();
		List<WebElement> InvID_List = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[7]/div[2]/table/tbody/tr");
		// System.out.println(InvID_List.size());

		for (int Inv = 1; Inv <= InvID_List.size(); Inv++) {
			String Inv_sts = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[7]/div[2]/table/tbody[" + Inv + "]/tr/td[4]").getText();

			if (Inv == 1) {
				// Assert.assertEquals("Paid", Inv_sts);
				ExchangePay_softAssert.assertThat(Inv_sts).isEqualTo("Paid");
			} else {
				// Assert.assertEquals("Posted", Inv_sts);
				ExchangePay_softAssert.assertThat(Inv_sts).isEqualTo("Posted");
			}
			ExchangePay_softAssert.assertAll();
		}
	}

	public static void PO_WFC_Approvals(List<HashMap<String, String>> data) {

		Procurement_PurchaseOrder.Search_PO(data);
		String GrndTotal = data.get(17).get("Column17");
		String GT = GrndTotal.replace("₹", "").replace("$", "").replace(",", "");
		// String GrndTotal = "5000.45";
		double f = Double.parseDouble(GT);

		System.out.println(f);

		if (f <= 1000000.00) {

			String status = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[5]/td[2]").getText();
			Assert.assertEquals("Processing", status);

			// List<WebElement> approvalTab =
			// WebdriverWait.findElements("xpath",
			// "//*[@id='list']/div[2]/ol/li");
			// approvalTab.size();
			// Assert.assertEquals(2, approvalTab.size());
			Crest_Login.PurchaseVP_login();
			PO_Multilevel_Approval(data, "Approve", "Approved");

		} else if (f > 1000000.00) {

			System.out.println("Grand Total is greater than 100000");

			String status = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[5]/td[2]").getText();
			Assert.assertEquals("Processing", status);

			// List<WebElement> approvalTab =
			// WebdriverWait.findElements("xpath",
			// "//*[@id='list']/div[2]/ol/li");
			// approvalTab.size();
			// Assert.assertEquals(4, approvalTab.size());

			Crest_Login.PurchaseVP_login();
			PO_Multilevel_Approval(data, "Approve", "Approved");
			Crest_Login.PurchaseCEO_login();
			PO_Multilevel_Approval(data, "Approve", "Approved");

		}
	}

	public static void PO_WFC_Approvals_Imports(List<HashMap<String, String>> data) {

		Procurement_PurchaseOrder.Search_PO(data);
		String GrndTotal = data.get(17).get("Column17");
		String GT = GrndTotal.replace("₹", "").replace("$", "").replace(",", "");
		// String GrndTotal = "5000.45";
		double f = Double.parseDouble(GT);

		System.out.println(f);

		if (f <= 5000.00) {

			String status = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[5]/td[2]").getText();
			Assert.assertEquals("Processing", status);

			// List<WebElement> approvalTab =
			// WebdriverWait.findElements("xpath",
			// "//*[@id='list']/div[2]/ol/li");
			// approvalTab.size();
			// Assert.assertEquals(2, approvalTab.size());
			Crest_Login.PurchaseVP_login();
			PO_Multilevel_Approval(data, "Approve", "Approved");

		} else if (f > 5000.00) {

			System.out.println("Grand Total is greater than 5000");

			String status = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[5]/td[2]").getText();
			Assert.assertEquals("Processing", status);

			// List<WebElement> approvalTab =
			// WebdriverWait.findElements("xpath",
			// "//*[@id='list']/div[2]/ol/li");
			// approvalTab.size();
			// Assert.assertEquals(4, approvalTab.size());

			Crest_Login.PurchaseVP_login();
			PO_Multilevel_Approval(data, "Approve", "Approved");
			Crest_Login.PurchaseCEO_login();
			PO_Multilevel_Approval(data, "Approve", "Approved");
		}
	}
	public static void PO_Multilevel_Approval(List<HashMap<String, String>> data, String linkname, String comments) {

		String notification = WebdriverWait.findElement("xpath", "//*[@id='masthead']/ul/li[3]/ul/li[2]/a/span").getText();
		int n = Integer.parseInt(notification);
		System.out.println(n);

		WebdriverWait.findElement("xpath", "//*[@id='masthead']/ul/li[3]/ul/li[2]/a").click();

		List<WebElement> notificationTable = WebdriverWait.findElements("xpath", "//*[@id='example']/tbody/tr");

		for (int a = 1; a <= notificationTable.size(); a++) {
			String poNum = WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr[" + a + "]/td[4]/a").getText();
			if (poNum.contains(data.get(13).get("Column4"))) {
				PO_View_softAssert.assertThat(data.get(13).get("Column4")).isEqualTo(poNum);
				WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr[" + a + "]/td[4]/a").click();
				break;
			}
		}
		WebdriverWait.findElement("id", "workflowComments").sendKeys(comments);
		WebdriverWait.findElement("link", linkname).click();

		List<WebElement> approvalTab = WebdriverWait.findElements("xpath", "//*[@id='list']/div[2]/ol/li");
		approvalTab.size();

		// if (approvalTab.size() == 2 || approvalTab.size() == 4) {
		//
		// String notification1 = WebdriverWait.findElement("xpath",
		// "//*[@id='masthead']/ul/li[3]/ul/li[1]/a/span").getText();
		// int n1 = Integer.parseInt(notification1);
		// Assert.assertEquals(n, n1);
		// } else {
		// String notification1 = WebdriverWait.findElement("xpath",
		// "//*[@id='masthead']/ul/li[3]/ul/li[1]/a/span").getText();
		// int n1 = Integer.parseInt(notification1);
		// Assert.assertEquals(n - 1, n1);
		// }
	}

	public static void EditOrderLink() {

		WebdriverWait.findElement("link", "Edit Order").click();
	}

	public static void CreateReturnLink() {

		WebdriverWait.findElement("link", "Create Return").click();
	}

	public static void PO_GrdTotal_Val(List<HashMap<String, String>> data, String Header) {

		log.info("Validating GrandTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[8]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String grandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[8]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (grandTotal.contains("Grand Total")) {

				String GrandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[8]/div[2]/table/tbody/tr[" + a + "]/td[3]")
								.getText().replace(",", "");
				System.out.println("GrandTotal" + GrandTotal);
				PO_View_softAssert.assertThat(GrandTotal).isEqualTo(data.get(17).get(Header));
				log.info("Grand Total validated Successfully");
				System.out.println("Grand Total Validated Successfully");
			}
		}
	}
	public static void Add_Charges(List<HashMap<String, String>> data) throws InterruptedException {

		// WebElement element = WebdriverWait.findElement("link", "Add
		// Charge(s)");
		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", element);

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[11]/div[1]/ul/li[3]/a").click();

		int j = 24;

		for (int a = 1; a <= 3; a++) {

			WebdriverWait.findElement("name", "taxConfiguratorId").sendKeys(data.get(j).get("Column1"));

			Select fac = new Select(WebdriverWait.findElement("name", "shipGroupSeqId"));
			fac.selectByVisibleText(data.get(0).get("Column7"));

			Select taxOnChargeAmt = new Select(WebdriverWait.findElement("name", "gstCategoryId"));
			taxOnChargeAmt.selectByVisibleText("-Select-");

			WebdriverWait.findElement("name", "submitMap").click();
			j++;
		}

		WebdriverWait.findElement("link", "Back to Order").click();
	}

	public static void AddThirdparty_Charges(List<HashMap<String, String>> data) {

		// WebElement element = WebdriverWait.findElement("link", "Add
		// Charge(s)");
		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", element);

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[11]/div[1]/ul/li[3]/a").click();

		WebdriverWait.findElement("name", "taxConfiguratorId").sendKeys(data.get(26).get("Column1"));

		Select fac = new Select(WebdriverWait.findElement("name", "shipGroupSeqId"));
		fac.selectByVisibleText(data.get(0).get("Column7"));

		Select taxOnChargeAmt = new Select(WebdriverWait.findElement("name", "gstCategoryId"));
		taxOnChargeAmt.selectByVisibleText("-Select-");

		WebdriverWait.findElement("name", "submitMap").click();
		WebdriverWait.findElement("link", "Back to Order").click();
	}

	public static void TaxBreakup_Table_Val(List<HashMap<String, String>> data) {

		String taxType = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div//table//tbody//tr[1]//td[9]").getText();

		PO_View_softAssert.assertThat(taxType.contains("INT"));

		System.out.println(taxType);

		List<WebElement> taxBreakupTable = WebdriverWait.findElements("xpath", "//*[@id='taxContainer']/table/tbody/tr");
		int b = taxBreakupTable.size();

		if (taxType.equalsIgnoreCase("INTRA")) {

			System.out.println("--------------------------------entered the loop Intra---------------------------");

			String CGST = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",", "");
			PO_View_softAssert.assertThat(CGST).isEqualTo(data.get(23).get("Column21"));

			String SGST = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[3]").getText().replace(",", "");
			PO_View_softAssert.assertThat(SGST).isEqualTo(data.get(23).get("Column22"));

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]").getText().replace(",", "");
			PO_View_softAssert.assertThat(totalTax).isEqualTo(data.get(24).get("Column23"));

		} else if (taxType.equalsIgnoreCase("INTER")) {

			String IGST = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[4]").getText().replace(",", "");
			PO_View_softAssert.assertThat(IGST).isEqualTo(data.get(24).get("Column23"));

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]").getText().replace(",", "");
			PO_View_softAssert.assertThat(totalTax).isEqualTo(data.get(24).get("Column23"));

		}

		System.out.println("Tax Breakup Table Validated Successfully");
	}

	public static void ThirdPartyTax_Table_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[1]").click();// 3rd
																												// party
																												// Taxes
																												// Button

		List<WebElement> thirdPartyTaxTable = WebdriverWait.findElements("xpath", "//*[@id='tab_tab1_tax']/table/tbody/tr");
		int b = thirdPartyTaxTable.size();

		String thirdPartyTotalTax = WebdriverWait.findElement("xpath", "//*[@id='tab_tab1_tax']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",", "");
		PO_View_softAssert.assertThat(thirdPartyTotalTax).isEqualTo(data.get(24).get("Column23"));
		System.out.println("Third Party Tax Table validated successfully");
	}

	public static void ThirdPartyCharges_Table_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[2]").click();// 3rd
																												// party
																												// Charges
																												// Button

		List<WebElement> thirdPartyChargesTable = WebdriverWait.findElements("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr");
		int b = thirdPartyChargesTable.size();

		String thirdPartyTotalCharges = WebdriverWait.findElement("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",",
						"");
		PO_View_softAssert.assertThat(thirdPartyTotalCharges).isEqualTo(data.get(26).get("Column3"));
		System.out.println("Third Party Charges Table validated successfully");

	}

	public static void OtherCharges_PopupBtn_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "myBtn1").click();

		String ShipChrg = WebdriverWait.findElement("xpath", "//*[@id='myModal1']/div/div/div[2]/table/tbody/tr[2]/td[2]").getText();
		PO_View_softAssert.assertThat(ShipChrg).isEqualTo(data.get(24).get("Column1"));

		String ShipChrgvalue = WebdriverWait.findElement("xpath", "//*[@id='myModal1']/div/div/div[2]/table/tbody/tr[2]/td[11]").getText().replace(",", "");
		PO_View_softAssert.assertThat(ShipChrgvalue).isEqualTo(data.get(24).get("Column3"));

		String Chkpost = WebdriverWait.findElement("xpath", "//*[@id='myModal1']/div/div/div[2]/table/tbody/tr[3]/td[2]").getText();
		PO_View_softAssert.assertThat(Chkpost).isEqualTo(data.get(25).get("Column1"));

		String Chkpostvalue = WebdriverWait.findElement("xpath", "//*[@id='myModal1']/div/div/div[2]/table/tbody/tr[3]/td[11]").getText().replace(",", "");
		PO_View_softAssert.assertThat(Chkpostvalue).isEqualTo(data.get(25).get("Column3"));

		WebdriverWait.findElement("xpath", "//*[@id='myModal1']/div/span").click();
		System.out.println("Other Charges button validated successfully");
	}

	public static void Tax_Consolidated_PopupBtn_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "myBtn").click();

		List<WebElement> taxBtn_Table = WebdriverWait.findElements("xpath", "//*[@id='taxSubContainer']/table/tbody/tr");
		int b = taxBtn_Table.size();

		String TotalTax = WebdriverWait.findElement("xpath", "//*[@id='taxSubContainer']/table/tbody/tr[" + b + "]/td[2]/span").getText().replace(",", "");
		PO_View_softAssert.assertThat(TotalTax).isEqualTo(data.get(17).get("Column16"));
		WebdriverWait.findElement("xpath", "//*[@id='myModal']/div/span").click();
		System.out.println("Tax(Consolidated) button validated successfully");
	}

	public static void dropShip_PO_Tax_Val(List<HashMap<String, String>> data) {

		// Procurement_PurchaseOrder.Search_PO(data);
		log.info("Validating Total Tax");
		String TotalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[8]/div[2]/table/tbody/tr[8]/td[3]").getText().replace(",",
						"");
		PO_View_softAssert.assertThat(TotalTax).isEqualTo(data.get(17).get("Column16"));
		System.out.println("Tax Value validated successfully");
	}

	public static void dropShip_PO_SubTotal_Val(List<HashMap<String, String>> data) throws InterruptedException {
		Thread.sleep(10000);
		log.info("Validating SubTotal");
		String SubTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[8]/div[2]/table/tbody/tr[6]/td[3]").getText().replace(",",
						"");
		System.out.println("SubTotal" + SubTotal);
		PO_View_softAssert.assertThat(SubTotal).isEqualTo(data.get(17).get("Column15"));
		System.out.println("Sub Total validated successfully");
	}

	public static void dropShip_PO_GrdTotal_Val(List<HashMap<String, String>> data) {

		log.info("Validating GrandTotal");
		String GrandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[8]/div[2]/table/tbody/tr[9]/td[3]").getText().replace(",",
						"");
		System.out.println("GrandTotal" + GrandTotal);
		PO_View_softAssert.assertThat(GrandTotal).isEqualTo(data.get(17).get("Column17"));
		log.info("Grand Total validated Successfully");
		System.out.println("Grand Total Validated Successfully");
	}

	public static void switchTab(int tabNum) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabNum));
	}

	/////////////////////////////////////////// Negative
	/////////////////////////////////////////// Flow//////////////////////////////////////////////////////////////////////

	public static void AddCharges_MandatoryFields(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Add Charge(s)").click();
		WebdriverWait.findElement("name", "submitMap").click();

		String tax = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String shpGrp = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String val = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();

		PO_View_softAssert.assertThat(tax).isEqualTo(data.get(0).get("Column15"));
		PO_View_softAssert.assertThat(shpGrp).isEqualTo(data.get(1).get("Column8"));
		PO_View_softAssert.assertThat(val).isEqualTo(data.get(4).get("Column12"));

	}

	public static void AddCharges_InvalidChargeID(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "taxConfiguratorId").sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "submitMap").click();

		String chargeID = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_View_softAssert.assertThat(chargeID).isEqualTo(data.get(1).get("Column15"));
		WebdriverWait.findElement("name", "taxConfiguratorId").clear();
	}

	public static void AddCharges_InvalidValue(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		WebdriverWait.findElement("name", "taxConfiguratorId").sendKeys(data.get(24).get("Column1"));

		Select shpGrp_dd = new Select(WebdriverWait.findElement("name", "shipGroupSeqId"));
		shpGrp_dd.selectByVisibleText(data.get(0).get("Column7"));

		WebdriverWait.findElement("id", "manualAdjustmentAmount").clear();
		WebdriverWait.findElement("id", "manualAdjustmentAmount").sendKeys(Inv_data.get(0).get("Column1"));

		WebdriverWait.findElement("name", "submitMap").click();

		String splchar = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_View_softAssert.assertThat(splchar).isEqualTo(Inv_data.get(10).get("Column12"));
		WebdriverWait.findElement("name", "taxConfiguratorId").clear();

	}

	public static void AddCharges_AddingSameChargeIDTwice(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		// Adding First Time
		WebdriverWait.findElement("name", "taxConfiguratorId").sendKeys(data.get(24).get("Column1"));

		Select shpGrp_dd = new Select(WebdriverWait.findElement("name", "shipGroupSeqId"));
		shpGrp_dd.selectByVisibleText(data.get(0).get("Column7"));

		WebdriverWait.findElement("name", "submitMap").click();

		// Adding the same Charge ID second time
		WebdriverWait.findElement("name", "taxConfiguratorId").sendKeys(data.get(24).get("Column1"));

		Select shpGrp_dd1 = new Select(WebdriverWait.findElement("name", "shipGroupSeqId"));
		shpGrp_dd1.selectByVisibleText(data.get(0).get("Column7"));

		WebdriverWait.findElement("name", "submitMap").click();

		String existingChargeID = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_View_softAssert.assertThat(existingChargeID).isEqualTo(Inv_data.get(2).get("Column15"));

	}

	public static void AddCharges_DeleteCharges() {

		WebdriverWait.findElement("name", "selectAll").click();
		WebdriverWait.findElement("link", "Delete").click();

		WebElement NoChargestodisplay = WebdriverWait.findElement("xpath", "//*[@id='selectAllForm']/div");
		PO_View_softAssert.assertThat(NoChargestodisplay.isDisplayed());

		WebdriverWait.findElement("link", "Back to Order").click();

	}

	public static void CancelOrder() {

		WebdriverWait.findElement("link", "Cancel Order").click();
		WebdriverWait.findElement("name", "note").sendKeys("Testing purpose");
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/div/table/tbody[1]/tr[3]/td[2]/input").click();

		String cancelStatus = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[5]/td[2]").getText();
		PO_View_softAssert.assertThat(cancelStatus).isEqualTo("Cancelled");

		Calendar sys = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String todayDate = (date.format(new Date()));
		String sysTime = (sdf.format(sys.getTime()));

		String cancelledNotes = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[15]/div[2]/table/tbody/tr[2]/td[1]").getText();
		PO_View_softAssert.assertThat(cancelledNotes).contains("Testing purpose - By R, Kailash");

	}

	public static void WFC_History_Validations() {

		WebdriverWait.findElement("link", "Show History").click();

		// Purchase Executive
		String purExe = WebdriverWait.findElement("xpath", "//*[@id='workflowHistoryContainer']/table/tbody/tr[1]/td[2]").getText();
		PO_View_softAssert.assertThat(purExe).contains("Purchase Executive (11792)");

		String purExeStatus = WebdriverWait.findElement("xpath", "//*[@id='workflowHistoryContainer']/table/tbody/tr[1]/td[3]").getText();
		PO_View_softAssert.assertThat(purExeStatus).contains("Requested");

		// Purchase VP
		String purVP = WebdriverWait.findElement("xpath", "//*[@id='workflowHistoryContainer']/table/tbody/tr[2]/td[2]").getText();
		PO_View_softAssert.assertThat(purVP).contains("Purchase VP (11790)");

		String purVPStatus = WebdriverWait.findElement("xpath", "//*[@id='workflowHistoryContainer']/table/tbody/tr[2]/td[3]").getText();
		PO_View_softAssert.assertThat(purVPStatus).contains("Approved");

		// Purchase CEO
		String purCEO = WebdriverWait.findElement("xpath", "//*[@id='workflowHistoryContainer']/table/tbody/tr[3]/td[2]").getText();
		PO_View_softAssert.assertThat(purCEO).contains("Purchase CEO (11801)");

		String purCEOStatus = WebdriverWait.findElement("xpath", "//*[@id='workflowHistoryContainer']/table/tbody/tr[3]/td[3]").getText();
		PO_View_softAssert.assertThat(purCEOStatus).contains("Returned");

		// Purchase VP (Rejected)
		String purVPreject = WebdriverWait.findElement("xpath", "//*[@id='workflowHistoryContainer']/table/tbody/tr[4]/td[2]").getText();
		PO_View_softAssert.assertThat(purVPreject).contains("Purchase VP (11790)");

		String purVPrejectStatus = WebdriverWait.findElement("xpath", "//*[@id='workflowHistoryContainer']/table/tbody/tr[4]/td[3]").getText();
		PO_View_softAssert.assertThat(purVPrejectStatus).contains("Rejected");

	}

	public static void PO_View_assertions() {
		PO_View_softAssert.assertAll();
	}

}