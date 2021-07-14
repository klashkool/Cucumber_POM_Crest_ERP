package com.Sales_Modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Sales_SalesOrder_View extends Base {

	public static String so_Number;

	@Rule
	public static JUnitSoftAssertions SO_View_softAssert = new JUnitSoftAssertions();
	public static JUnitSoftAssertions SO_View_DropShip_softAssert = new JUnitSoftAssertions();

	public static void Capture_SONum(String sheetname) {

		String so_Num = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[1]/td[2]/a").getText();
		System.out.println("SO Number = " + so_Num);
		so_Number = so_Num;
		ExcelWriter.writeExcelFile(sheetname, 15, 4, so_Num);

	}

	public static void Capture_SONum_for_SalesReturn(String sheetname1) {

		String so_Num = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[1]/td[2]/a").getText();
		System.out.println("SO Number = " + so_Num);
		so_Number = so_Num;
		ExcelWriter.writeExcelFile(sheetname1, 15, 4, so_Num);

	}

	public static void Capture_SONum_for_SalesReturnReplacement(String sheetname) {

		String so_Num = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[1]/td[2]/a").getText();
		System.out.println("SO Number = " + so_Num);
		so_Number = so_Num;
		ExcelWriter.writeExcelFile(sheetname, 15, 4, so_Num);

	}

	public static void Approve_SO(List<HashMap<String, String>> data) {

		log.info("Approve SO Normal Flow");

		Sales_SalesOrder.Search_SO(data);

		WebdriverWait.findElement("link", "Approve Order").click();
		String App_Status_Val = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div//table//tbody//tr[14]//td[2]").getText();
		Assert.assertEquals("Approved", App_Status_Val);
		log.info("Status validated successfully as Approved");
	}

	public static void SO_CaptureInvID(String sheetname) {

		log.info("Capturing Invoice ID's for all the Shipments");

		List<WebElement> InvTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr");

		int j = 15;
		for (int Inv = 1; Inv <= InvTable.size(); Inv++) {

			String InvID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr[" + Inv + "]/td[6]/a").getText();

			ExcelWriter.writeExcelFile(sheetname, j, 6, InvID);
			j++;
		}
	}

	public static void InvID_PaymntStatus_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "SALES").click();
		WebdriverWait.findElement("link", "Sales Order").click();
		WebdriverWait.findElement("id", "orderId").sendKeys(data.get(13).get("Column4"));
		WebdriverWait.findElement("xpath", "//*[@id= 'searchCriteria']/div/table/tbody[1]/tr[16]/td/input").click();
		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[2]/a").click();

		List<WebElement> InvID_List = WebdriverWait.findElements("xpath", "//*[@id= 'content-main-section']/div[4]/div[2]/table/tbody/tr/td[6]/a");
		System.out.println(InvID_List.size());

		for (int Inv = 1; Inv <= InvID_List.size(); Inv++) {

			WebdriverWait.findElement("xpath", "//*[@id= 'content-main-section']/div[4]/div[2]/table/tbody/tr/td[6]/a[" + Inv + "]").click();

			String Inv_sts = WebdriverWait.findElement("xpath", "//*[@id= 'content-main-section']/div[2]/div[2]/table/tbody[1]/tr[8]/td[2]").getText();
			Assert.assertEquals("Paid", Inv_sts);

			WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[1]/td[1]/a").click();

		}
	}

	public static void PayTerm_Val(List<HashMap<String, String>> data) {

		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='orderTermsOriginal']/tbody/tr[1]/td[5]").getText().replace(",", "");
		SO_View_softAssert.assertThat(payterm1).isEqualTo(data.get(17).get("Column18"));

		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='orderTermsOriginal']/tbody/tr[2]/td[5]").getText().replace(",", "");
		SO_View_softAssert.assertThat(payterm2).isEqualTo(data.get(17).get("Column19"));

	}

	public static void SO_SubTotal_Val(List<HashMap<String, String>> data, String Header) {

		log.info("Validating SubTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String subTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (subTotal.contains("Sub Total")) {

				String SubTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");
				System.out.println("SubTotal" + SubTotal);
				SO_View_softAssert.assertThat(data.get(17).get(Header)).isEqualTo(SubTotal);
				System.out.println("Sub Total validated successfully");
			}
		}
	}

	public static void SO_GrdTotal_Val(List<HashMap<String, String>> data, String Header) {

		log.info("Validating GrandTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String grandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (grandTotal.contains("Grand Total")) {

				String GrandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]")
								.getText().replace(",", "");
				System.out.println("GrandTotal" + GrandTotal);
				SO_View_softAssert.assertThat(data.get(17).get(Header)).isEqualTo(GrandTotal);
				log.info("Grand Total validated Successfully");
				System.out.println("Grand Total Validated Successfully");
			}
		}
	}

	public static void Tax_Consolidated_PopupBtn_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "myBtn").click();

		List<WebElement> taxBtn_Table = WebdriverWait.findElements("xpath", "//*[@id='taxSubContainer']/table/tbody/tr");
		int b = taxBtn_Table.size();

		String TotalTax = WebdriverWait.findElement("xpath", "//*[@id='taxSubContainer']/table/tbody/tr[" + b + "]/td[2]/span").getText().replace(",", "");
		SO_View_softAssert.assertThat(data.get(17).get("Column16")).isEqualTo(TotalTax);
		WebdriverWait.findElement("xpath", "//*[@id='myModal']/div/span").click();
		System.out.println("Tax(Consolidated) button validated successfully");
	}

	public static void SO_Tax_Val(List<HashMap<String, String>> data, String Header) {

		// Procurement_PurchaseOrder.Search_PO(data);

		log.info("Validating Total Tax");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();
			System.out.println("TotatlTax" + totalTax);

			if (totalTax.contains("Tax Amount (Consolidated)")) {

				String TotalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");
				SO_View_softAssert.assertThat(data.get(17).get(Header)).isEqualTo(TotalTax);
				System.out.println("Tax Value validated successfully");
			}
		}
	}

	public static void TaxBreakup_Table_Val(List<HashMap<String, String>> data) {

		String taxType = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div//table//tbody//tr[1]//td[9]").getText();

		List<WebElement> taxBreakupTable = WebdriverWait.findElements("xpath", "//*[@id='taxContainer']/table/tbody/tr");
		int b = taxBreakupTable.size();

		if (taxType.equalsIgnoreCase("INTRA")) {

			String CGST = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",", "");
			SO_View_softAssert.assertThat(data.get(23).get("Column21")).isEqualTo(CGST);

			String SGST = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[3]").getText().replace(",", "");
			SO_View_softAssert.assertThat(data.get(23).get("Column22")).isEqualTo(SGST);

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]").getText().replace(",", "");
			SO_View_softAssert.assertThat(data.get(24).get("Column23")).isEqualTo(totalTax);

		} else if (taxType.equalsIgnoreCase("INTER")) {

			String IGST = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[4]").getText().replace(",", "");
			SO_View_softAssert.assertThat(data.get(24).get("Column23")).isEqualTo(IGST);

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]").getText().replace(",", "");
			SO_View_softAssert.assertThat(data.get(24).get("Column23")).isEqualTo(totalTax);

		}

		System.out.println("Tax Breakup Table Validated Successfully");
	}

	public static void EditOrderLink() {

		WebdriverWait.findElement("link", "Edit Order").click();
	}

	public static void Add_Charges(List<HashMap<String, String>> data) throws InterruptedException {

		WebElement element = WebdriverWait.findElement("xpath", "//a[contains(text(),'Add Charge')]");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		Thread.sleep(1000);
		WebdriverWait.findElement("link", "Create Adjustment").click();

		int j = 24;

		for (int a = 0; a <= 2; a++) {

			WebdriverWait.findElement("name", "taxConfiguratorId").clear();
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

	public static void InvID_PaymntStatus_Val() {

		List<WebElement> InvID_List = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr");
		// System.out.println(InvID_List.size());

		for (int Inv = 1; Inv <= InvID_List.size(); Inv++) {
			String Inv_sts = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody[" + Inv + "]/tr/td[5]").getText();
			Assert.assertEquals("Confirmed", Inv_sts);
		}
	}

	public static void InvID_PartialPaymntStatus_Val() {

		List<WebElement> InvID_List = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr");
		// System.out.println(InvID_List.size());

		for (int Inv = 1; Inv <= InvID_List.size(); Inv++) {
			String Inv_sts = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody[" + Inv + "]/tr/td[5]").getText();

			if (Inv == 1) {
				Assert.assertEquals("Posted", Inv_sts);
			} else {
				Assert.assertEquals("Paid", Inv_sts);
			}
		}
	}

	public static void InvID_ExchangeRatePaymntStatus_Val() {

		List<WebElement> InvID_List = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr");
		// System.out.println(InvID_List.size());

		for (int Inv = 1; Inv <= InvID_List.size(); Inv++) {
			String Inv_sts = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody[" + Inv + "]/tr/td[5]").getText();

			if (Inv == 1) {
				Assert.assertEquals("Paid", Inv_sts);
			} else {
				Assert.assertEquals("Posted", Inv_sts);
			}
		}
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
		SO_View_softAssert.assertThat(thirdPartyTotalCharges).isEqualTo(data.get(26).get("Column3"));
		System.out.println("Third Party Charges Table validated successfully");

	}

	public static void Generate_ServiceOrder_Inv(List<HashMap<String, String>> data) {

		Sales_SalesOrder.Search_SO(data);
		WebdriverWait.findElement("link", "Generate Service Order Invoice").click();
	}

	public static void click_firstLineItem_ViewDetails() {
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[1]/td[4]/a").click();
	}

	public static void capture_ReturnReplacementSO_ID(String sheetname) {
		String getReturnReplacment_SO_ID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[22]/td[2]/a").getText();
		ExcelWriter.writeExcelFile(sheetname, 15, 2, getReturnReplacment_SO_ID);
	}

	public static void click_ReturnReplacementSO_ID() {
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[22]/td[2]/a").click();
	}

	public static void switchTab(int tabNum) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(tabNum));
	}

	public static void verifySalesReturnInvoiceDetails_Prd_N_Qty(List<HashMap<String, String>> data) {
		String getProductID =	WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[1]/td[1]").getText();
		Assert.assertEquals(getProductID, getProductID.contains(data.get(0).get("Column25")));

		String getReturnedQty =	WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[1]/td[5]").getText();
		Assert.assertEquals(getReturnedQty, data.get(0).get("Column27"));
	}

	public static void verify_ReturnReplacementSO_GrdTotal_Value(List<HashMap<String, String>> data) {

		String grandTotal_actual = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[6]/td[3]/span").getText().replace("₹", "");
			System.out.println("GrandTotal" + grandTotal_actual);
			SO_View_softAssert.assertThat(data.get(17).get("Column3")).isEqualTo(grandTotal_actual);
				log.info("Grand Total validated Successfully");
				System.out.println("Grand Total Validated Successfully");
	}

	public static void verify_and_capture_DropShipPO_ID(String sheetname) {
		boolean getDropShipPO_ID_isPresent = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[3]/td[2]/a").isDisplayed();
		Assert.assertTrue(getDropShipPO_ID_isPresent);
		String getDropShipPO_ID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[3]/td[2]/a").getText();
		ExcelWriter.writeExcelFile(sheetname, 15, 3, getDropShipPO_ID);
	}

	public static void click_DropShipPO_ID(String sheetname) {
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[3]/td[2]/a").click();
	}

	//////////////////////////////////////// Negative
	//////////////////////////////////////// Flow///////////////////////////////////////////////////////

	public static void Validate_AddCharges_without_Charges(List<HashMap<String, String>> data) throws InterruptedException {

		WebElement element = WebdriverWait.findElement("xpath", "//a[contains(text(),'Add Charge')]");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		Thread.sleep(1000);
		WebdriverWait.findElement("link", "Create Adjustment").click();
		WebdriverWait.findElement("name", "submitMap").click();
		String actMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		SO_View_softAssert.assertThat(actMsg1).isEqualTo(data.get(0).get("Column15"));
		String actMsg2 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
		SO_View_softAssert.assertThat(actMsg2).isEqualTo(data.get(16).get("Column8"));
		String actMsg3 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[3]").getText();
		SO_View_softAssert.assertThat(actMsg3).isEqualTo(data.get(4).get("Column12"));
		WebdriverWait.findElement("link", "Back to Order").click();
	}

	public static void Validate_Add_Charges_with_huge_value(List<HashMap<String, String>> data_positive_TC05, List<HashMap<String, String>> data)
					throws InterruptedException {

		WebElement element = WebdriverWait.findElement("xpath", "//a[contains(text(),'Add Charge')]");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		Thread.sleep(1000);
		WebdriverWait.findElement("link", "Create Adjustment").click();

		WebdriverWait.findElement("name", "taxConfiguratorId").clear();
		WebdriverWait.findElement("name", "taxConfiguratorId").sendKeys(data_positive_TC05.get(24).get("Column1"));

		Select fac = new Select(WebdriverWait.findElement("name", "shipGroupSeqId"));
		fac.selectByIndex(1);
		Select taxOnChargeAmt = new Select(WebdriverWait.findElement("name", "gstCategoryId"));
		taxOnChargeAmt.selectByVisibleText("-Select-");

		WebdriverWait.findElement("id", "manualAdjustmentAmount").clear();
		WebdriverWait.findElement("id", "manualAdjustmentAmount").sendKeys(data.get(16).get("Column1"));

		WebdriverWait.findElement("name", "submitMap").click();
		try {
			if (driver.findElement(By.xpath("//*[@id='content-messages']/div/ul/li")).isDisplayed()) {
				String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				SO_View_softAssert.assertThat(actMsg).isEqualTo("The maximum limit for value is 15 Numbers.");
			} else {
				SO_View_softAssert.fail("Application is throwing exception. Instead validation msg should be displayed");
			}
		} catch (Exception e) {
			SO_View_softAssert.fail("Application is throwing exception. Instead validation msg should be displayed");
		}

		WebdriverWait.findElement("link", "Back to Order").click();
	}

	public static void Validate_AddCharges_DeleteBtn_without_selecting_checkbox(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive)
					throws InterruptedException {
		WebElement element = WebdriverWait.findElement("xpath", "//a[contains(text(),'Add Charge')]");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		Thread.sleep(1000);
		WebdriverWait.findElement("link", "Create Adjustment").click();
		WebdriverWait.findElement("name", "taxConfiguratorId").clear();
		WebdriverWait.findElement("name", "taxConfiguratorId").sendKeys(data_Positive.get(24).get("Column1"));

		Select fac = new Select(WebdriverWait.findElement("name", "shipGroupSeqId"));
		fac.selectByVisibleText(data_Positive.get(0).get("Column7"));

		Select taxOnChargeAmt = new Select(WebdriverWait.findElement("name", "gstCategoryId"));
		taxOnChargeAmt.selectByVisibleText("-Select-");

		WebdriverWait.findElement("name", "submitMap").click();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/div/a[1]").click();
		try {
			if (driver.findElement(By.xpath("//*[@id='content-messages']/div/ul/li")).isDisplayed()) {
				String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				SO_View_softAssert.assertThat(actMsg).isEqualTo("Application is not throwing validation msg. Instead success msg is displayed");
			} else {
				SO_View_softAssert.fail("No validation message displayed when trying to remove line item without selecting its checkbox");
			}
		} catch (Exception e) {
			SO_View_softAssert.fail("No validation message displayed when trying to remove line item without selecting its checkbox");
		}
	}

	public static void Validate_AddCharges_DeleteBtn_by_selecting_lineItem_checkbox(List<HashMap<String, String>> data,
					List<HashMap<String, String>> data_Positive) throws InterruptedException {
		// WebElement element = WebdriverWait.findElement("xpath",
		// "//a[contains(text(),'Add Charge')]");
		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", element);
		// element.click();
		// Thread.sleep(1000);
		// WebdriverWait.findElement("link", "Create Adjustment").click();
		// WebdriverWait.findElement("name", "taxConfiguratorId").clear();
		// WebdriverWait.findElement("name",
		// "taxConfiguratorId").sendKeys(data_Positive.get(24).get("Column1"));
		//
		// Select fac = new Select(WebdriverWait.findElement("name",
		// "shipGroupSeqId"));
		// fac.selectByVisibleText(data_Positive.get(0).get("Column7"));
		//
		// Select taxOnChargeAmt = new Select(WebdriverWait.findElement("name",
		// "gstCategoryId"));
		// taxOnChargeAmt.selectByVisibleText("-Select-");
		//
		// WebdriverWait.findElement("name", "submitMap").click();
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/div/a[1]").click();

		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		SO_View_softAssert.assertThat(actMsg).isEqualTo(data.get(8).get("Column17"));
	}

	public static void Validate_Ship_before_and_after_date_with_past_date(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("id", "shipByDate_00001_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("id", "shipAfterDate_00001_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr/td[7]/input").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SO_View_softAssert.assertThat(actMsg).isEqualTo(data.get(14).get("Column2"));
	}

	public static void Validate_RecalculateSelected_without_selecting_lineItem(List<HashMap<String, String>> data) {
		// EditOrderLink();
		WebdriverWait.findElement("link", "Recalculate Selected").click();
		try {
			if (WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").isDisplayed()) {
				String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				SO_View_softAssert.assertThat(actMsg).isEqualTo(data.get(0).get("Column16"));
			}
		} catch (Exception e) {
			SO_View_softAssert.fail("Validation msg is not displayed when Recalculate Selected without selecting lineItem");
		}

	}

	public static void Validate_RecalculateSelected_lineItem_Qty_with_negativeValue(List<HashMap<String, String>> data) {
		// EditOrderLink();
		WebElement Qty = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[7]/div[2]/form[1]/table/tbody/tr[1]/td[6]/input");
		Qty.clear();
		Qty.sendKeys(data.get(1).get("Column1"));
		WebdriverWait.findElement("id", "selectedItem_1").click();
		WebdriverWait.findElement("link", "Recalculate Selected").click();
		try {
			if (WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").isDisplayed()) {
				String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				SO_View_softAssert.assertThat(actMsg).isEqualTo(data.get(1).get("Column10"));
			}
		} catch (Exception e) {
			SO_View_softAssert.fail("Validation msg is not displayed when Recalculate Selected with negative Value as Qty");
		}

	}

	public static void Validate_RecalculateSelected_lineItem_Qty_with_SPL_characters(List<HashMap<String, String>> data) {
		// EditOrderLink();
		WebElement Qty = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[7]/div[2]/form[1]/table/tbody/tr[1]/td[6]/input");
		Qty.clear();
		Qty.sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("id", "selectedItem_1").click();
		WebdriverWait.findElement("link", "Recalculate Selected").click();
		try {
			if (WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").isDisplayed()) {
				String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				SO_View_softAssert.assertThat(actMsg).isEqualTo(data.get(11).get("Column10"));
			}
		} catch (Exception e) {
			SO_View_softAssert.fail("Validation msg is not displayed when Recalculate Selected with SPL character as Qty");
		}
	}

	public static void Validate_RecalculateSelected_lineItem_Discount_with_emptyValue(List<HashMap<String, String>> data) {
		// EditOrderLink();
		Select DiscountTypeDD = new Select(WebdriverWait.findElement("id", "dtm_1"));
		DiscountTypeDD.selectByVisibleText("Percentage");
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[7]/div[2]/form[1]/table/tbody/tr[1]/td[11]/input[2]").clear();
		WebdriverWait.findElement("id", "selectedItem_1").click();
		WebdriverWait.findElement("link", "Recalculate Selected").click();
		try {
			if (WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").isDisplayed()) {
				String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				SO_View_softAssert.assertThat(actMsg).isEqualTo(data.get(2).get("Column14"));
			}
		} catch (Exception e) {
			SO_View_softAssert.fail("Validation msg is not displayed when Recalculate Selected by leaving discount field as empty");
		}
	}

	public static void Validate_RecalculateSelected_lineItem_without_DiscountType_with_DiscountValue(List<HashMap<String, String>> data) {
		// EditOrderLink();
		Select DiscountTypeDD = new Select(WebdriverWait.findElement("id", "dtm_1"));
		DiscountTypeDD.selectByVisibleText("-Select-");
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[7]/div[2]/form[1]/table/tbody/tr[1]/td[11]/input[2]")
						.sendKeys(data.get(4).get("Column1"));
		WebdriverWait.findElement("id", "selectedItem_1").click();
		WebdriverWait.findElement("link", "Recalculate Selected").click();
		try {
			if (WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").isDisplayed()) {
				String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				SO_View_softAssert.assertThat(actMsg).isEqualTo(data.get(3).get("Column14"));
			}
		} catch (Exception e) {
			SO_View_softAssert.fail("Validation msg is not displayed when Recalculate Selected with discount value and without selecting discount type");
		}

	}

	public static void Validate_CancelSelected_without_selecting_lineItem(List<HashMap<String, String>> data) {
		// EditOrderLink();
		WebdriverWait.findElement("link", "Cancel Selected").click();
		try {
			if (WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").isDisplayed()) {
				String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				SO_View_softAssert.assertThat(actMsg).isEqualTo(data.get(5).get("Column16"));
			}
		} catch (Exception e) {
			SO_View_softAssert.fail("Validation msg is not displayed when Cancel Selected without selecting line item checkbox");
		}

	}

	public static void Validate_Select_And_CancelSelected_lineItem(List<HashMap<String, String>> data) {
		// EditOrderLink();
		WebdriverWait.findElement("id", "selectedItem_1").click();
		WebdriverWait.findElement("link", "Cancel Selected").click();
		try {
			if (WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").isDisplayed()) {
				String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
				SO_View_softAssert.assertThat(actMsg).isEqualTo(data.get(4).get("Column19"));
			} else if (WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").isDisplayed()) {
				SO_View_softAssert.fail("Success msg not displayed. Instead validation msg displayed as "
								+ WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText());
			}
		} catch (Exception e) {
			SO_View_softAssert.fail("Success msg is not displayed");
		}

	}

	public static void Validate_AddOrderItemsSection_without_mandatory_fields(List<HashMap<String, String>> data) {
		EditOrderLink();
		Select shipGroupSeqIdDD = new Select(WebdriverWait.findElement("id", "shipGroupSeqId"));
		shipGroupSeqIdDD.selectByVisibleText("-Select-");
		WebdriverWait.findElement("name", "submitItems").click();
		String actMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		String actMsg2 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
		String actMsg3 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[3]").getText();
		String actMsg4 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[4]").getText();
		String actMsg5 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[5]").getText();
		SO_View_softAssert.assertThat(actMsg1).isEqualTo(data.get(7).get("Column3"));
		SO_View_softAssert.assertThat(actMsg2).isEqualTo(data.get(12).get("Column10"));
		SO_View_softAssert.assertThat(actMsg3).isEqualTo(data.get(2).get("Column8"));
		SO_View_softAssert.assertThat(actMsg4).isEqualTo(data.get(2).get("Column11"));
		SO_View_softAssert.assertThat(actMsg5).isEqualTo(data.get(1).get("Column9"));
	}

	public static void Validate_by_updating_OtherDetails_Section_without_NoteDescription(List<HashMap<String, String>> data) {
		// WebdriverWait.findElement("xpath",
		// "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr[1]/td[2]/a").click();
		WebdriverWait.findElement("link", "Add Note").click();
		WebdriverWait.findElement("id", "note").sendKeys("Test Note");
		WebdriverWait.findElement("xpath", "//*[@value='Submit']").click();
		EditOrderLink();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[14]/div[2]/table/tbody/tr/td[2]/a[1]").click();
		WebdriverWait.findElement("id", "noteInfo").clear();
		WebdriverWait.findElement("xpath", "//*[@value='Update']").click();
		String actAlertMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SO_View_softAssert.assertThat(actAlertMsg).isEqualTo(data.get(12).get("Column10"));
		WebdriverWait.findElement("link", "Back to Order").click();
	}

	public static void Validate_successMsg_by_updating_OtherDetails_Section(List<HashMap<String, String>> data) {
		EditOrderLink();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[14]/div[2]/table/tbody/tr/td[2]/a[1]").click();
		WebdriverWait.findElement("id", "noteInfo").sendKeys("Test Note updated");
		WebdriverWait.findElement("xpath", "//*[@value='Update']").click();
		String actSuccessMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		SO_View_softAssert.assertThat(actSuccessMsg).isEqualTo(data.get(15).get("Column17"));
	}

	public static void Validate_Delete_OtherDetails(List<HashMap<String, String>> data) {
		EditOrderLink();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[14]/div[2]/table/tbody/tr/td[2]/a[2]").click();
		String actAlertMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		SO_View_softAssert.assertThat(actAlertMsg).isEqualTo(data.get(2).get("Column19"));
	}

	public static void Validate_by_Adding_OtherDetails_Section_without_NoteDescription(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("link", "Add Note").click();
		WebdriverWait.findElement("xpath", "//*[@value='Submit']").click();
		String actAlertMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SO_View_softAssert.assertThat(actAlertMsg).isEqualTo(data.get(3).get("Column13"));
		WebdriverWait.findElement("link", "Back to Order").click();
	}

	public static void ApproveSalesOrder() {
		WebdriverWait.findElement("link", "Approve Order").click();
	}

	public static void ClickCreateNewShipment() {
		WebdriverWait.findElement("link", "Create New Shipment").click();
	}

	// public static void Inc_Qty_After_PartialShipment(List<HashMap<String,
	// String>> data_Positive) throws InterruptedException {
	// Facilities_Shipments_Sales.MakePartialSalesShipmentAndUpdateTheStatusToShipped(data_Positive);
	// Facilities_Shipments_View_Sales.ClickSOLinkFromSalesShipmentViewScreen();
	// EditOrderLink();
	// }

	public static void CreateAsNewOrder() {
		WebdriverWait.findElement("link", "Create As New Order").click();
		WebdriverWait.findElement("link", "Create").click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public static void Validate_Cancelled_Item_Status(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		CreateAsNewOrder();
		EditOrderLink();
		Validate_Select_And_CancelSelected_lineItem(data);
		CreateAsNewOrder();
		String actAlertMsg = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[1]/td[1]").getText();
		SO_View_softAssert.assertThat(actAlertMsg).doesNotContain(data_Positive.get(0).get("Column2"));
	}

	public static void Validate_Order_Values(List<HashMap<String, String>> data_Positive) {
		CreateAsNewOrder();
		String SubTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[3]/td[3]").getText().replace(",",
						"");
		System.out.println("SubTotal" + SubTotal);
		SO_View_softAssert.assertThat(data_Positive.get(23).get("Column12")).isEqualTo(SubTotal);

		String Tax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[5]/td[3]").getText().replace(",", "");
		System.out.println("Tax" + Tax);
		SO_View_softAssert.assertThat(data_Positive.get(24).get("Column12")).isEqualTo(Tax);

		String GrandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[9]/td[3]/span").getText()
						.replace(",", "");
		System.out.println("GrandTotal" + GrandTotal);
		SO_View_softAssert.assertThat(data_Positive.get(25).get("Column12")).isEqualTo(GrandTotal);
	}

	public static void Validate_Cancel_Order_Without_Note(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("link", "Cancel Order").click();
		WebdriverWait.findElement("xpath", "//*[@value='Cancel Order']").click();
		String actAlertMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SO_View_softAssert.assertThat(actAlertMsg).isEqualTo(data.get(3).get("Column13"));
	}

	public static void Validate_edit_SO_by_removing_2_lineItems(List<HashMap<String, String>> data_Positive) {
		EditOrderLink();
		WebdriverWait.findElement("id", "selectedItem_4").click();
		WebdriverWait.findElement("id", "selectedItem_5").click();
		WebdriverWait.findElement("link", "Cancel Selected").click();

		String isLineItem4Cancelled = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[4]/td[4]").getText();
		SO_View_softAssert.assertThat(isLineItem4Cancelled).contains("Cancelled");
		String isLineItem5Cancelled = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[5]/td[4]").getText();
		SO_View_softAssert.assertThat(isLineItem5Cancelled).contains("Cancelled");
		String actSubTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[6]/td[3]").getText();
		SO_View_softAssert.assertThat(actSubTotal).isEqualTo(data_Positive.get(17).get("Column15"));
		String actTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[8]/td[3]").getText();
		SO_View_softAssert.assertThat(actTax).isEqualTo(data_Positive.get(17).get("Column16"));
		String actGrandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[9]/td[3]/span").getText();
		SO_View_softAssert.assertThat(actGrandTotal).isEqualTo(data_Positive.get(17).get("Column17"));
	}

	public static void Validate_edit_SO_by_adding_2_lineItems(List<HashMap<String, String>> data_Positive_1) {
		for (int i = 3; i <= 4; i++) {
			EditOrderLink();
			WebdriverWait.findElement("name", "productId").sendKeys(data_Positive_1.get(i).get("Column2"));
			WebdriverWait.findElement("name", "quantity").sendKeys(data_Positive_1.get(i).get("Column9"));
			Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
			dept_dd.selectByVisibleText(data_Positive_1.get(i).get("Column8"));
			Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
			discountType_dd.selectByVisibleText(data_Positive_1.get(i).get("Column16"));
			WebdriverWait.findElement("id", "discount").sendKeys(data_Positive_1.get(i).get("Column17"));
			WebdriverWait.findElement("name", "itemComment").sendKeys("Test");
			WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[8]/div[2]/form/table/tbody/tr[7]/td/input").click();
		}
		String actSubTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[7]/div[2]/form[1]/table/tbody/tr[8]/td[3]").getText();
		SO_View_softAssert.assertThat(actSubTotal).isEqualTo(data_Positive_1.get(17).get("Column15"));
		String actTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[7]/div[2]/form[1]/table/tbody/tr[10]/td[3]").getText();
		SO_View_softAssert.assertThat(actTax).isEqualTo(data_Positive_1.get(17).get("Column16"));
		String actGrandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[7]/div[2]/form[1]/table/tbody/tr[11]/td[3]/span")
						.getText();
		SO_View_softAssert.assertThat(actGrandTotal).isEqualTo(data_Positive_1.get(17).get("Column17"));
	}

	public static void Validate_edit_SO_OrderItems_Add_Button_without_mandatory_fields(List<HashMap<String, String>> data) {
		EditOrderLink();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[7]/div[2]/form[2]/table/tbody/tr[3]/td/input").click();
		String actAlertMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		SO_View_softAssert.assertThat(actAlertMsg1).isEqualTo(data.get(11).get("Column8"));
		String actAlertMsg2 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
		SO_View_softAssert.assertThat(actAlertMsg2).isEqualTo(data.get(3).get("Column13"));
		String actAlertMsg3 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[3]").getText();
		SO_View_softAssert.assertThat(actAlertMsg3).isEqualTo(data.get(4).get("Column12"));
	}

	public static void Validate_Edit_SO_Adj_description_with_more_than_255_Characters(List<HashMap<String, String>> data) {
		EditOrderLink();
		Select facility_dd = new Select(WebdriverWait.findElement("name", "shipGroupSeqId"));
		facility_dd.selectByValue("00001");
		WebElement description = WebdriverWait.findElement("id", "description");
		description.sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("id", "amount").sendKeys("5");
		WebdriverWait.findElement("name", "submitItems").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SO_View_softAssert.assertThat(validationMsg).isEqualTo("Validation msg yet to add. Appln throws exception.");
	}

	public static void Validate_Edit_SO_with_Adj_Value_with_SPL_Characters(List<HashMap<String, String>> data) {
		EditOrderLink();
		Select facility_dd = new Select(WebdriverWait.findElement("name", "shipGroupSeqId"));
		facility_dd.selectByValue("00001");
		WebElement description = WebdriverWait.findElement("id", "description");
		description.clear();
		description.sendKeys("Test");
		WebdriverWait.findElement("id", "amount").sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "submitItems").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SO_View_softAssert.assertThat(validationMsg).isEqualTo(data.get(7).get("Column14"));
	}

	public static void Validate_edit_SO_under_AddOrderItem_section_without_mandatory_fields(List<HashMap<String, String>> data) {
		EditOrderLink();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[8]/div[2]/form/table/tbody/tr[7]/td/input").click();
		String actAlertMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		SO_View_softAssert.assertThat(actAlertMsg1).isEqualTo(data.get(7).get("Column3"));
		String actAlertMsg2 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
		SO_View_softAssert.assertThat(actAlertMsg2).isEqualTo(data.get(12).get("Column10"));
		String actAlertMsg3 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[3]").getText();
		SO_View_softAssert.assertThat(actAlertMsg3).isEqualTo(data.get(2).get("Column11"));
		String actAlertMsg4 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[4]").getText();
		SO_View_softAssert.assertThat(actAlertMsg4).isEqualTo(data.get(1).get("Column9"));
	}

	public static void Validate_edit_SO_under_AddOrderItem_section_productID_with_SPL_characters(List<HashMap<String, String>> data) {
		EditOrderLink();
		WebdriverWait.findElement("name", "productId").sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[8]/div[2]/form/table/tbody/tr[7]/td/input").click();
		String actAlertMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		SO_View_softAssert.assertThat(actAlertMsg1).isEqualTo(data.get(12).get("Column10"));
		String actAlertMsg2 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
		SO_View_softAssert.assertThat(actAlertMsg2).isEqualTo(data.get(2).get("Column11"));
		String actAlertMsg3 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[3]").getText();
		SO_View_softAssert.assertThat(actAlertMsg3).isEqualTo(data.get(1).get("Column9"));
	}

	public static void Validate_edit_SO_under_AddOrderItem_section_Qty_with_SPL_characters(List<HashMap<String, String>> data) {
		EditOrderLink();
		WebdriverWait.findElement("name", "productId").clear();
		WebdriverWait.findElement("id", "quantity").sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[8]/div[2]/form/table/tbody/tr[7]/td/input").click();
		String actAlertMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		SO_View_softAssert.assertThat(actAlertMsg1).isEqualTo(data.get(7).get("Column3"));
		String actAlertMsg2 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
		SO_View_softAssert.assertThat(actAlertMsg2).isEqualTo(data.get(11).get("Column10"));
	}

	public static void Validate_edit_SO_under_AddOrderItem_section_Comments_with_moreThan_250_characters(List<HashMap<String, String>> data_Positive,
					List<HashMap<String, String>> data) {
		EditOrderLink();
		WebdriverWait.findElement("name", "productId").sendKeys(data_Positive.get(0).get("Column2"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data_Positive.get(0).get("Column9"));
		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data_Positive.get(0).get("Column8"));
		Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		discountType_dd.selectByVisibleText(data_Positive.get(0).get("Column16"));
		WebdriverWait.findElement("id", "discount").sendKeys(data_Positive.get(0).get("Column17"));
		WebdriverWait.findElement("name", "itemComment").sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[8]/div[2]/form/table/tbody/tr[7]/td/input").click();
		String actAlertMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SO_View_softAssert.assertThat(actAlertMsg).isEqualTo(data.get(4).get("Column13"));
	}

	public static void Validate_edit_SO_under_AddOrderItem_section_with_Invalid_Date_Range(List<HashMap<String, String>> data_Positive,
					List<HashMap<String, String>> data) {
		EditOrderLink();
		WebdriverWait.findElement("name", "productId").sendKeys(data_Positive.get(0).get("Column2"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data_Positive.get(0).get("Column9"));
		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data_Positive.get(0).get("Column8"));
		Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		discountType_dd.selectByVisibleText(data_Positive.get(0).get("Column16"));
		WebdriverWait.findElement("id", "discount").sendKeys(data_Positive.get(0).get("Column17"));
		WebdriverWait.findElement("name", "itemComment").sendKeys("Test");
		WebdriverWait.findElement("name", "itemComment").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("name", "itemComment").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[8]/div[2]/form/table/tbody/tr[7]/td/input").click();
		String actAlertMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		SO_View_softAssert.assertThat(actAlertMsg).isEqualTo(data.get(16).get("Column2"));
		String actAlertMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
		SO_View_softAssert.assertThat(actAlertMsg1).isEqualTo(data.get(17).get("Column2"));
	}

	public static void Update_SO_to_Held_Status(List<HashMap<String, String>> data_positive_TC05) {
		Approve_SO(data_positive_TC05);
		WebdriverWait.findElement("link", "Hold Order").click();
		String Held_Status_Val = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div//table//tbody//tr[11]//td[2]").getText();
		Assert.assertEquals("Held", Held_Status_Val);
		log.info("Status validated successfully as Held");
	}

	public static void Update_SO_to_Cancelled_Status() {
		WebdriverWait.findElement("link", "Cancel Order").click();
		WebdriverWait.findElement("id", "note").sendKeys("Cancelling the SO");
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/div/table/tbody[1]/tr[3]/td/input").click();
		String Cancelled_Status_Val = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div//table//tbody//tr[11]//td[2]").getText();
		Assert.assertEquals("Held", Cancelled_Status_Val);
		log.info("Status validated successfully as Cancelled");
	}

	public static void SO_View_assertions() {
		SO_View_softAssert.assertAll();
	}
	public static void SO_View_DropShip_assertions() {
		SO_View_DropShip_softAssert.assertAll();
	}
}
