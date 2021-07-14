package com.Procurement_Modules;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Procurement_PurchaseOrder extends Base {

	public static Logger log = Logger.getLogger(Procurement_PurchaseOrder.class);

	public static String po_Number;
	public static String AdhocPo_Number;
	public static String EncPo_Number;


	public static JUnitSoftAssertions PO_PurchaseOrder_softAssert = new JUnitSoftAssertions();

	public static void PO_Start_Order(List<HashMap<String, String>> data, String poname, String order_type, String payTerm) {

		log.info("Creating a New PO");
		WebdriverWait.findElement("link", "PROCUREMENT").click();
		WebdriverWait.findElement("link", "Purchase Order").click();
		WebdriverWait.findElement("link", "Create Purchase Order").click();

		// Select org_dd = new Select(WebdriverWait.findElement("id",
		// "billToCustomerPartyId"));
		// org_dd.selectByVisibleText(data.get(0).get("Column3"));

		Select sup_dd = new Select(WebdriverWait.findElement("id", "supplierPartyId"));
		sup_dd.selectByVisibleText(data.get(1).get("Column4"));

		WebdriverWait.findElement("id", "orderName").sendKeys(poname);

		Select ordType_dd = new Select(WebdriverWait.findElement("id", "orderType"));
		ordType_dd.selectByVisibleText(order_type);

		Select currency_dd = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		currency_dd.selectByVisibleText(data.get(0).get("Column6"));

		WebdriverWait.findElement("id", "workeffortId").sendKeys("");

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String Fromdate = (dateFormat.format(new Date()));

		WebdriverWait.findElement("name", "orderDate_i18n").clear();
		WebdriverWait.findElement("name", "orderDate_i18n").sendKeys(Fromdate);

		Select numType_dd = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		numType_dd.selectByVisibleText("Purchase Order");

		Select payTerm_dd = new Select(WebdriverWait.findElement("id", "agreementId"));
		payTerm_dd.selectByVisibleText(payTerm);// -Select-

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div[3]/div[3]/a[1]").click();

	}
	public static void PO_Shipping_Details_MultipleFacility(List<HashMap<String, String>> data, int loopcount, int facility1, int facility2, int facility3) {

		log.info("Adding Ship Groups");
		String facility = "_facilityId";

		for (int a = 0; a <= loopcount; a++) {
			Select shpFacility = new Select(WebdriverWait.findElement("id", a + facility));
			if (a == 0) {
				shpFacility.selectByVisibleText(data.get(facility1).get("Column7"));
				WebdriverWait.findElement("link", "New Ship Group").click();
			} else if (a == 1) {
				shpFacility.selectByVisibleText(data.get(facility2).get("Column7"));
				if (loopcount == 1) {
					break;
				} else {
					WebdriverWait.findElement("link", "New Ship Group").click();
				}
			} else if (a == 2) {
				shpFacility.selectByVisibleText(data.get(facility3).get("Column7"));
			}
		}
		WebdriverWait.findElement("link", "Continue").click();
	}

	public static void PO_Shipping_Details_SingleFacility(List<HashMap<String, String>> data) {

		log.info("Adding Ship Groups");
		WebdriverWait.findElement("name", "internal_order_notes").sendKeys("Internal Notes Test");
		WebdriverWait.findElement("name", "shippingNotes").sendKeys("Supplier Notes Test");

		Select shpFacility = new Select(WebdriverWait.findElement("id", "0_facilityId"));
		shpFacility.selectByVisibleText(data.get(0).get("Column7"));

		WebdriverWait.findElement("link", "Continue").click();
	}
	public static void PO_OrderItems(List<HashMap<String, String>> data, int loopcount) throws InterruptedException {

		log.info("Adding products");
		for (int p = 0; p <= loopcount; p++) {
			Select ordType_dd = new Select(WebdriverWait.findElement("id", "addItemType"));
			ordType_dd.selectByVisibleText(data.get(0).get("Column5"));
			Thread.sleep(1000);
			WebdriverWait.findElement("name", "add_product_id").sendKeys(data.get(p).get("Column2"));
			Thread.sleep(1000);
			WebdriverWait.findElement("name", "quantity").sendKeys(data.get(p).get("Column9"));

			Select uom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
			uom_dd.selectByVisibleText(data.get(p).get("Column10"));

			Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
			dept_dd.selectByVisibleText(data.get(p).get("Column8"));

			WebdriverWait.findElement("name", "itemComment").sendKeys("Test");

			Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
			discountType_dd.selectByVisibleText("-Select-");

			List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='toGroupIndex']//option");
			for (WebElement option : options) {
				if (option.getText().contains(data.get(p).get("Column7"))) {
					option.click();
					break;
				}
			}

			WebdriverWait.findElement("name", "submitItems").click();
		}
		WebdriverWait.findElement("link", "Continue").click();
	}

	public static void Charges(List<HashMap<String, String>> data) {

		int j = 24;

		for (int a = 1; a <= 2; a++) {

			WebdriverWait.findElement("name", "taxConfiguratorId").sendKeys(data.get(j).get("Column1"));

			// Select fac = new Select(WebdriverWait.findElement("name",
			// "shipGroupSeqId"));
			// fac.selectByVisibleText(data.contains(data.get(0).get("Column7"));

			List<WebElement> options = driver.findElements(By.xpath("//*[@id='shipGroupSeqId']//option"));

			for (WebElement option : options) {
			    if (option.getText().contains(data.get(0).get("Column7"))) {
			        option.click();
			        break;
			    }
			}

			Select taxOnChargeAmt = new Select(WebdriverWait.findElement("name", "gstCategoryId"));
			taxOnChargeAmt.selectByVisibleText("-Select-");

			WebdriverWait.findElement("name", "submitMap").click();
			j++;
		}

		WebdriverWait.findElement("link", "Continue").click();
	}

	public static void Continue_ChargesPage_WithoutCharges() {
		WebdriverWait.findElement("link", "Continue").click();
	}

	public static void PO_ReviewCreatePO(String sheetname) {

		WebdriverWait.findElement("link", "Create").click();
		Procurement_PurchaseOrder_View.POnum_Capture(sheetname);
		Procurement_PurchaseOrder_View.POnum_Capture_Return("PR01_TC03");
	}

	public static void PO_ReviewCreatePO_TC02(String sheetname) {
		WebdriverWait.findElement("link", "Create").click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Procurement_PurchaseOrder_View.POnum_Capture(sheetname);
	}

	// ************************************************************************************************************************************************//
	// PO With Adhoc Flow
	// ************************************************************************************************************************************************//

	public static void PO_Adhoc_OrderItems(List<HashMap<String, String>> data, int loopcount) throws InterruptedException {

		log.info("Adding Inv products & Adhoc Products");
		for (int p = 0; p <= loopcount; p++) {

			Select ordType_dd = new Select(WebdriverWait.findElement("id", "addItemType"));
			ordType_dd.selectByVisibleText(data.get(p).get("Column5"));

			Thread.sleep(1000);

			WebdriverWait.findElement("name", "add_item_description").sendKeys(data.get(p).get("Column2"));
			WebdriverWait.findElement("xpath", "//*[@id='adHoc']/table/tbody/tr[2]/td[2]/input").sendKeys(data.get(p).get("Column9"));

			String unitPrice = data.get(p).get("Column11");
			String price = unitPrice.replace("₹", "").replace("$", "").replace(",", "");

			WebdriverWait.findElement("name", "price").sendKeys(price);

			Select taxCat_dd = new Select(WebdriverWait.findElement("id", "gstCategoryId"));
			taxCat_dd.selectByVisibleText("GST 5%");

			Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName1"));
			dept_dd.selectByVisibleText(data.get(p).get("Column8"));

			// Select fac_dd = new Select(
			// WebdriverWait.findElement("xpath",
			// "/html/body/div[2]/div[4]/div/div[1]/div[2]/div[2]/form[2]/div/table/tbody/tr[4]/td[4]/select"));
			// fac_dd.selectByVisibleText(data.get(5).get("Column7"));

			WebdriverWait.findElement("xpath", "//*[@id='adHoc']/table/tbody/tr[6]/td/input").click();
		}
		WebdriverWait.findElement("link", "Continue").click();
		log.info("PO Number Created Successfully");

	}

	public static void Po_Enc_Approve_Po() {

		log.info("Approve PO for Encumbrance");
		WebdriverWait.findElement("link", "Review Order").click();
		WebdriverWait.findElement("link", "Review 2 Order").click();
		WebdriverWait.findElement("link", "Approve Order").click();
		String App_Status_Val = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[5]/td[2]").getText();
		Assert.assertEquals("Approved", App_Status_Val);
		log.info("Status validated successfully as Approved");

	}

	public static void Search_PO(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "PROCUREMENT").click();
		WebdriverWait.findElement("link", "Purchase Order").click();
		WebdriverWait.findElement("id", "orderId").sendKeys(data.get(13).get("Column4"));
		// WebdriverWait.findElement("xpath",
		// "//*[@id='searchCriteria']/div/table/tbody/tr[17]/td/input").click();
		WebdriverWait.findElement("xpath", "//*[@value = 'Search']").click();
		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[3]/a").click();

	}

	public static void Edit_lineItem_Discounts(List<HashMap<String, String>> data) {

		// Search_PO(data);
		Procurement_PurchaseOrder_View.EditOrderLink();

		for (int a = 1; a <= 4; a++) {

			Select disType = new Select(WebdriverWait.findElement("id", "dvmValDisc_" + a));
			disType.selectByVisibleText(data.get(a - 1).get("Column16"));

			WebdriverWait.findElement("id", "dvmVal_" + a).sendKeys(data.get(a - 1).get("Column17"));

			WebdriverWait.findElement("id", "selectedItem_" + a).click();
		}

		WebdriverWait.findElement("link", "Recalculate Selected").click();
	}

	public static void Edit_Adjustments(List<HashMap<String, String>> data) {

		// Search_PO(data);
		Procurement_PurchaseOrder_View.EditOrderLink();

		Select adj = new Select(WebdriverWait.findElement("name", "orderAdjustmentTypeId"));
		adj.selectByVisibleText("Discount");

		WebdriverWait.findElement("id", "description").sendKeys("Flat Discount");

		Select fac = new Select(WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[9]/div[2]/form[2]/table/tbody/tr[1]/td[4]/select"));
		fac.selectByVisibleText(data.get(0).get("Column7"));

		WebdriverWait.findElement("id", "amount").clear();

		String AdjValue = data.get(6).get("Column19");
		String Val = AdjValue.replace("₹", "").replace("$", "").replace(",", "");

		WebdriverWait.findElement("id", "amount").sendKeys("-" + Val);
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div//form[2]//table//tbody//tr[3]//td//input").click();

		String adj_success_msg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		Assert.assertEquals("Order Adjustment created successfully.", adj_success_msg);

	}

	//////////////////////////////////////////////////// Negative
	//////////////////////////////////////////////////// Flow///////////////////////////////////////////////////////////

	public static void PurchaseOrder_Navigation() {

		WebdriverWait.findElement("link", "PROCUREMENT").click();
		WebdriverWait.findElement("link", "Purchase Order").click();

	}

	public static void PurchaseOrder_SearchPage_InvalidDate_Val(List<HashMap<String, String>> data) {

		// PO Date with past date as Thru date
		WebdriverWait.findElement("id", "minDate_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("id", "maxDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Search']").click();

		String PO_ThruDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(PO_ThruDate).isEqualTo(data.get(21).get("Column2"));
	}

	public static void PO_StartOrder_MandatoryFields_POtable(List<HashMap<String, String>> data) {

		log.info("Creating a New PO");
		WebdriverWait.findElement("link", "PROCUREMENT").click();
		WebdriverWait.findElement("link", "Purchase Order").click();
		WebdriverWait.findElement("link", "Create Purchase Order").click();

		Select org_dd = new Select(WebdriverWait.findElement("id", "billToCustomerPartyId"));
		org_dd.selectByVisibleText("-Select-");

		Select ordType_dd = new Select(WebdriverWait.findElement("id", "orderType"));
		ordType_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("name", "orderDate_i18n").clear();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div[3]/div[3]/a[1]").click();

		String PO_Date = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String org_Name = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String supplier_name = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();
		String order_Type = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[4]").getText();
		String num_type = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[5]").getText();

		PO_PurchaseOrder_softAssert.assertThat(PO_Date).isEqualTo(data.get(16).get("Column2"));
		PO_PurchaseOrder_softAssert.assertThat(org_Name).isEqualTo(data.get(3).get("Column4"));
		PO_PurchaseOrder_softAssert.assertThat(supplier_name).isEqualTo(data.get(12).get("Column5"));
		PO_PurchaseOrder_softAssert.assertThat(order_Type).isEqualTo(data.get(5).get("Column6"));
		PO_PurchaseOrder_softAssert.assertThat(num_type).isEqualTo(data.get(6).get("Column6"));

	}

	public static void PO_CreatePOLink() {

		WebdriverWait.findElement("link", "Create Purchase Order").click();
	}

	public static void PO_StartOrder_CancelBtn() {

		WebdriverWait.findElement("link", "Cancel").click();

		WebElement CreatePurchaseOrderLink = WebdriverWait.findElement("link", "Create Purchase Order");
		Assert.assertEquals(true, CreatePurchaseOrderLink.isDisplayed());
	}

	public static void PO_ShpDetails_MandatoryFields(List<HashMap<String, String>> data) {

		Select shpFromAdd_dd = new Select(WebdriverWait.findElement("id", "fromcontactMechId"));
		shpFromAdd_dd.selectByVisibleText("-Select-");

		Select partyBillAdd_dd = new Select(WebdriverWait.findElement("id", "billFromcontactMechId"));
		partyBillAdd_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("link", "Continue").click();

		String shpFromAdd = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String partyBillAdd = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();

		PO_PurchaseOrder_softAssert.assertThat(shpFromAdd).isEqualTo(data.get(5).get("Column8"));
		PO_PurchaseOrder_softAssert.assertThat(partyBillAdd).isEqualTo(data.get(6).get("Column8"));

		Select fac_dd = new Select(WebdriverWait.findElement("id", "0_facilityId"));
		fac_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("link", "Continue").click();

		String fac = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(fac).isEqualTo(data.get(4).get("Column8"));

		Select fac_dd1 = new Select(WebdriverWait.findElement("id", "0_facilityId"));
		fac_dd1.selectByVisibleText("Chennai Warehouse");

		Select shpToAdd_dd = new Select(WebdriverWait.findElement("id", "0_contactMechId"));
		shpToAdd_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("link", "Continue").click();

		String ShpToAdd = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(ShpToAdd).isEqualTo(data.get(7).get("Column8"));

	}

	public static void PO_ShpDetails_AddRemoveShpGrp() {

		WebdriverWait.findElement("link", "New Ship Group").click();

		String shpGrpNo = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/form/div/table[3]/tbody/tr[1]/td[1]/h1").getText();
		System.out.println(shpGrpNo);

		WebdriverWait.findElement("link", "Remove Ship Group").click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Assert.assertTrue("ShipGroup 2 Found!", shpGrpNo.equals("Ship Group No. 2"));

	}

	public static void PO_OrderItems_MandatoryFields(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "submitItems").click();

		String prdID = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String qty = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String price = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();
		String UOM = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[4]").getText();
		String dept = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[5]").getText();

		PO_PurchaseOrder_softAssert.assertThat(prdID).isEqualTo(data.get(5).get("Column3"));
		PO_PurchaseOrder_softAssert.assertThat(qty).isEqualTo(data.get(0).get("Column10"));
		PO_PurchaseOrder_softAssert.assertThat(price).isEqualTo(data.get(9).get("Column12"));
		PO_PurchaseOrder_softAssert.assertThat(UOM).isEqualTo(data.get(1).get("Column11"));
		PO_PurchaseOrder_softAssert.assertThat(dept).isEqualTo(data.get(1).get("Column9"));

	}

	public static void PO_OrderItems_Invalid_PrdID(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		WebdriverWait.findElement("name", "add_product_id").sendKeys(Inv_data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(0).get("Column9"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data.get(0).get("Column8"));

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='toGroupIndex']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(0).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("name", "submitItems").click();

		String prdID_Invalid = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String price = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String UOM = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();
		String prd_sup_map = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[4]").getText();

		PO_PurchaseOrder_softAssert.assertThat(prdID_Invalid).isEqualTo(Inv_data.get(1).get("Column3"));
		PO_PurchaseOrder_softAssert.assertThat(price).isEqualTo(Inv_data.get(9).get("Column12"));
		PO_PurchaseOrder_softAssert.assertThat(UOM).isEqualTo(Inv_data.get(1).get("Column11"));
		PO_PurchaseOrder_softAssert.assertThat(prd_sup_map).isEqualTo(Inv_data.get(13).get("Column5"));

		WebdriverWait.findElement("name", "add_product_id").clear();
		WebdriverWait.findElement("name", "quantity").clear();

	}

	public static void PO_OrderItems_InvalidQty(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		Select ordType_dd = new Select(WebdriverWait.findElement("id", "addItemType"));
		ordType_dd.selectByVisibleText(data.get(0).get("Column5"));

		WebdriverWait.findElement("name", "add_product_id").sendKeys(data.get(0).get("Column2"));

		// Negative Value Qty
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(1).get("Column1"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data.get(0).get("Column8"));

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='toGroupIndex']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(0).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("name", "submitItems").click();

		String qty_grt0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(qty_grt0).isEqualTo(Inv_data.get(1).get("Column10"));

		// 0 Value Qty
		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(3).get("Column1"));
		WebdriverWait.findElement("name", "submitItems").click();

		String qty_grt_0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(qty_grt_0).isEqualTo(Inv_data.get(1).get("Column10"));

		// Qty with special characters.
		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "submitItems").click();

		String qty_num1 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(qty_num1).isEqualTo(Inv_data.get(2).get("Column10"));

		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(4).get("Column1"));

	}

	public static void PO_OrderItems_DiscMsg_Val(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		WebdriverWait.findElement("name", "add_product_id").clear();
		WebdriverWait.findElement("name", "add_product_id").sendKeys(data.get(0).get("Column2"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(0).get("Column9"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data.get(0).get("Column8"));

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='toGroupIndex']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(0).get("Column7"))) {
				option.click();
				break;
			}
		}

		// Keeping Discount field Empty
		Select discType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		discType_dd.selectByVisibleText("Percentage");

		WebdriverWait.findElement("name", "submitItems").click();

		String disc = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(disc).isEqualTo(Inv_data.get(1).get("Column14"));

		// Keeping Discount Type DD Empty
		Select discType_dd1 = new Select(WebdriverWait.findElement("id", "discountType"));
		discType_dd1.selectByVisibleText("-Select-");

		WebdriverWait.findElement("id", "discount").sendKeys(Inv_data.get(4).get("Column1"));

		WebdriverWait.findElement("name", "submitItems").click();
		String discType = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(discType).isEqualTo(Inv_data.get(4).get("Column14"));

		// Discount field with special Characters
		Select discType_dd3 = new Select(WebdriverWait.findElement("id", "discountType"));
		discType_dd3.selectByVisibleText("Percentage");

		WebdriverWait.findElement("id", "discount").sendKeys(Inv_data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "submitItems").click();

		String valid_disc = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(valid_disc).isEqualTo(Inv_data.get(0).get("Column14"));

	}

	public static void PO_OrderItems_AddComments_255Charac(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		Select ordType_dd = new Select(WebdriverWait.findElement("id", "addItemType"));
		ordType_dd.selectByVisibleText(data.get(0).get("Column5"));

		WebdriverWait.findElement("name", "add_product_id").clear();
		WebdriverWait.findElement("name", "add_product_id").sendKeys(data.get(0).get("Column2"));

		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(0).get("Column9"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data.get(0).get("Column8"));

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='toGroupIndex']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(0).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("id", "itemComment").sendKeys(Inv_data.get(6).get("Column1"));

		Select discType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		discType_dd.selectByVisibleText("Percentage");

		WebdriverWait.findElement("id", "discount").clear();
		WebdriverWait.findElement("id", "discount").sendKeys(data.get(0).get("Column17"));

		WebdriverWait.findElement("name", "submitItems").click();

		String comments = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(comments).isEqualTo(Inv_data.get(3).get("Column13"));

		WebdriverWait.findElement("id", "itemComment").clear();
		WebdriverWait.findElement("name", "submitItems").click();

	}

	public static void PO_OrderItems_Backbtn() {

		WebdriverWait.findElement("link", "Back").click();

		WebElement shpgrp_link = WebdriverWait.findElement("link", "New Ship Group");
		PO_PurchaseOrder_softAssert.assertThat(shpgrp_link.isDisplayed());

		WebdriverWait.findElement("link", "Continue").click();

	}

	public static void PO_OrderItems_RemoveLineItems() {

		WebdriverWait.findElement("name", "selectAll").click();
		WebdriverWait.findElement("link", "Remove Selected").click();

		WebElement Noorderitemstodisplay = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/form/div");
		PO_PurchaseOrder_softAssert.assertThat(Noorderitemstodisplay.isDisplayed());

	}

	public static void PO_ReviewCreatePO_Notes_Val() {

		String intNotes = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[11]/div[2]/table/tbody/tr/td[1]").getText();
		String supNotes = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[11]/div[2]/table/tbody/tr/td[2]").getText();

		PO_PurchaseOrder_softAssert.assertThat(intNotes).isEqualTo("Internal Notes Test");
		PO_PurchaseOrder_softAssert.assertThat(supNotes).isEqualTo("Supplier Notes Test");

	}

	public static void PO_Edit_OrderItems_InvalidQty(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		// Empty Qty Field
		WebdriverWait.findElement("name", "iqm_00001:00001").clear();
		WebdriverWait.findElement("id", "selectedItem_1").click();
		WebdriverWait.findElement("link", "Recalculate Selected").click();

		String qty_req = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(qty_req).isEqualTo(Inv_data.get(10).get("Column10"));

		// Negative Value Qty
		WebdriverWait.findElement("name", "iqm_00001:00001").clear();
		WebdriverWait.findElement("name", "iqm_00001:00001").sendKeys(Inv_data.get(1).get("Column1"));
		WebdriverWait.findElement("id", "selectedItem_1").click();
		WebdriverWait.findElement("link", "Recalculate Selected").click();

		String qty_grt0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(qty_grt0).isEqualTo(Inv_data.get(12).get("Column10"));

		// 0 Value Qty
		WebdriverWait.findElement("name", "iqm_00001:00001").clear();
		WebdriverWait.findElement("name", "iqm_00001:00001").sendKeys(Inv_data.get(3).get("Column1"));
		WebdriverWait.findElement("id", "selectedItem_1").click();
		WebdriverWait.findElement("link", "Recalculate Selected").click();

		String qty_grt_0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(qty_grt_0).isEqualTo(Inv_data.get(12).get("Column10"));

		// Qty with special characters.
		WebdriverWait.findElement("name", "iqm_00001:00001").clear();
		WebdriverWait.findElement("name", "iqm_00001:00001").sendKeys(Inv_data.get(0).get("Column1"));
		WebdriverWait.findElement("id", "selectedItem_1").click();
		WebdriverWait.findElement("link", "Recalculate Selected").click();

		String qty_num1 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(qty_num1).isEqualTo(Inv_data.get(2).get("Column10"));
	}

	public static void PO_Edit_OrderItems_Discounts(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) throws InterruptedException {

		// Keeping Discount field Empty
		Select discType_dd = new Select(WebdriverWait.findElement("id", "dvmValDisc_1"));
		discType_dd.selectByVisibleText("Percentage");

		WebdriverWait.findElement("id", "selectedItem_1").click();
		WebdriverWait.findElement("link", "Recalculate Selected").click();

		Alert alert = driver.switchTo().alert();
		String discReq = alert.getText();
		PO_PurchaseOrder_softAssert.assertThat(discReq).isEqualTo(Inv_data.get(2).get("Column14"));
		alert.accept();

		// Keeping Discount Type DD Empty
		Select discType_dd1 = new Select(WebdriverWait.findElement("id", "dvmValDisc_1"));
		discType_dd1.selectByVisibleText("-Select-");

		WebdriverWait.findElement("id", "dvmVal_1").sendKeys(Inv_data.get(4).get("Column1"));
		WebdriverWait.findElement("id", "selectedItem_1").click();
		WebdriverWait.findElement("link", "Recalculate Selected").click();

		Alert alert1 = driver.switchTo().alert();
		String discTypeReq = alert1.getText();
		PO_PurchaseOrder_softAssert.assertThat(discTypeReq).isEqualTo(Inv_data.get(3).get("Column14"));
		alert1.accept();

		// Discount field with special Characters
		Select discType_dd3 = new Select(WebdriverWait.findElement("id", "dvmValDisc_1"));
		discType_dd3.selectByVisibleText("Percentage");

		WebdriverWait.findElement("id", "dvmVal_1").sendKeys(Inv_data.get(0).get("Column1"));
		WebdriverWait.findElement("id", "selectedItem_1").click();
		WebdriverWait.findElement("link", "Recalculate Selected").click();

		String valid_disc = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(valid_disc).isEqualTo(Inv_data.get(0).get("Column14"));

	}

	public static void PO_Edit_OrderItems_Cancel_lineitems(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "selectedItem_5").click();
		WebdriverWait.findElement("link", "Cancel Selected").click();

		String desc = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		PO_PurchaseOrder_softAssert.assertThat(desc).isEqualTo(data.get(4).get("Column19"));
	}

	public static void PO_Edit_OrderItems_Adjustments_MandatoryFields(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "amount").clear();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[9]/div[2]/form[2]/table/tbody/tr[3]/td/input").click();

		String fac = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String desc = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String val = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();

		PO_PurchaseOrder_softAssert.assertThat(fac).isEqualTo(data.get(9).get("Column8"));
		PO_PurchaseOrder_softAssert.assertThat(desc).isEqualTo(data.get(4).get("Column13"));
		PO_PurchaseOrder_softAssert.assertThat(val).isEqualTo(data.get(4).get("Column12"));

	}

	public static void PO_Edit_OrderItems_Adjustments_InvalidValue(List<HashMap<String, String>> data) {

		Select adj_dd = new Select(WebdriverWait.findElement("name", "orderAdjustmentTypeId"));
		adj_dd.selectByVisibleText("Discount");

		Select fac_dd = new Select(WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[9]/div[2]/form[2]/table/tbody/tr[1]/td[4]/select"));
		fac_dd.selectByVisibleText("UIDAI - Inventory - WH");

		WebdriverWait.findElement("id", "description").sendKeys("Test");
		WebdriverWait.findElement("id", "amount").sendKeys(data.get(0).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[9]/div[2]/form[2]/table/tbody/tr[3]/td/input").click();

		String val = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(val).isEqualTo(data.get(10).get("Column12"));
	}

	public static void PO_Edit_OrderItems_Adjustments_Desc255Charc(List<HashMap<String, String>> data) {

		Select adj_dd = new Select(WebdriverWait.findElement("name", "orderAdjustmentTypeId"));
		adj_dd.selectByVisibleText("Discount");

		Select fac_dd = new Select(WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[9]/div[2]/form[2]/table/tbody/tr[1]/td[4]/select"));
		fac_dd.selectByVisibleText("UIDAI - Inventory - WH");

		WebdriverWait.findElement("id", "description").sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("id", "amount").clear();
		WebdriverWait.findElement("id", "amount").sendKeys(data.get(4).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[9]/div[2]/form[2]/table/tbody/tr[3]/td/input").click();

		String desc = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(desc).isEqualTo(data.get(5).get("Column13"));

	}

	public static void PO_Edit_OrderItems_AdjLineitem_InvalidValue(List<HashMap<String, String>> data) {

		// Procurement_PurchaseOrder_View.EditOrderLink();

		Select adj_dd = new Select(WebdriverWait.findElement("name", "orderAdjustmentTypeId"));
		adj_dd.selectByVisibleText("Discount");

		Select fac_dd = new Select(WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[9]/div[2]/form[2]/table/tbody/tr[1]/td[4]/select"));
		fac_dd.selectByVisibleText("UIDAI - Inventory - WH");

		WebdriverWait.findElement("id", "description").clear();
		WebdriverWait.findElement("id", "description").sendKeys("Test");
		WebdriverWait.findElement("id", "amount").clear();
		WebdriverWait.findElement("id", "amount").sendKeys(data.get(4).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[9]/div[2]/form[2]/table/tbody/tr[3]/td/input").click();

		Procurement_PurchaseOrder_View.EditOrderLink();

		WebdriverWait.findElement("name", "amount").clear();
		WebdriverWait.findElement("name", "amount").sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[9]/div[2]/form[2]/table/tbody/tr/td[5]/input").click();

		String val = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(val).isEqualTo(data.get(10).get("Column12"));

	}

	public static void PO_Edit_OrderItems_DeleteAdjustments(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Delete").click();

		String desc = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		PO_PurchaseOrder_softAssert.assertThat(desc).isEqualTo(data.get(2).get("Column18"));
		Procurement_PurchaseOrder_View.EditOrderLink();
	}

	public static void PO_Edit_AddOrderItems_MandatoryFields(List<HashMap<String, String>> data) {

		Select fac_dd = new Select(WebdriverWait.findElement("id", "shipGroupSeqId"));
		fac_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("name", "submitItems").click();

		String prdID = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String qty = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String fac = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();
		String uom = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[4]").getText();
		String dept = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[5]").getText();

		PO_PurchaseOrder_softAssert.assertThat(prdID).isEqualTo(data.get(5).get("Column3"));
		PO_PurchaseOrder_softAssert.assertThat(qty).isEqualTo(data.get(0).get("Column10"));
		PO_PurchaseOrder_softAssert.assertThat(fac).isEqualTo(data.get(8).get("Column8"));
		PO_PurchaseOrder_softAssert.assertThat(uom).isEqualTo(data.get(1).get("Column11"));
		PO_PurchaseOrder_softAssert.assertThat(dept).isEqualTo(data.get(1).get("Column9"));
	}

	public static void PO_Edit_AddOrderItems_Invalid_PrdID(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		WebdriverWait.findElement("name", "productId").sendKeys(Inv_data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(0).get("Column9"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data.get(0).get("Column8"));

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='shipGroupSeqId']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(0).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("name", "submitItems").click();

		String prdID_Invalid = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String UOM = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();

		PO_PurchaseOrder_softAssert.assertThat(prdID_Invalid).isEqualTo(Inv_data.get(1).get("Column3"));
		PO_PurchaseOrder_softAssert.assertThat(UOM).isEqualTo(Inv_data.get(1).get("Column11"));

		WebdriverWait.findElement("name", "productId").clear();
		WebdriverWait.findElement("name", "quantity").clear();

	}

	public static void PO_Edit_AddOrderItems_InvalidQty(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		WebdriverWait.findElement("name", "productId").sendKeys(data.get(0).get("Column2"));

		// Negative Value Qty
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(1).get("Column1"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data.get(0).get("Column8"));

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='shipGroupSeqId']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(0).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("name", "submitItems").click();

		String qty_grt0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(qty_grt0).isEqualTo(Inv_data.get(1).get("Column10"));

		// 0 Value Qty
		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(3).get("Column1"));
		WebdriverWait.findElement("name", "submitItems").click();

		String qty_grt_0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(qty_grt_0).isEqualTo(Inv_data.get(1).get("Column10"));

		// Qty with special characters.
		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "submitItems").click();

		String qty_num1 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(qty_num1).isEqualTo(Inv_data.get(2).get("Column10"));

		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(4).get("Column1"));

	}

	public static void PO_Edit_AddOrderItems_DiscMsg_Val(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		WebdriverWait.findElement("name", "productId").clear();
		WebdriverWait.findElement("name", "productId").sendKeys(data.get(0).get("Column2"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(0).get("Column9"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data.get(0).get("Column8"));

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='shipGroupSeqId']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(0).get("Column7"))) {
				option.click();
				break;
			}
		}

		// Keeping Discount field Empty
		Select discType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		discType_dd.selectByVisibleText("Percentage");

		WebdriverWait.findElement("name", "submitItems").click();

		String disc = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(disc).isEqualTo(Inv_data.get(1).get("Column14"));

		// Keeping Discount Type DD Empty
		Select discType_dd1 = new Select(WebdriverWait.findElement("id", "discountType"));
		discType_dd1.selectByVisibleText("-Select-");

		WebdriverWait.findElement("id", "discount").sendKeys(Inv_data.get(4).get("Column1"));

		WebdriverWait.findElement("name", "submitItems").click();
		String discType = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(discType).isEqualTo(Inv_data.get(4).get("Column14"));

		// Discount field with special Characters
		Select discType_dd3 = new Select(WebdriverWait.findElement("id", "discountType"));
		discType_dd3.selectByVisibleText("Percentage");

		WebdriverWait.findElement("id", "discount").sendKeys(Inv_data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "submitItems").click();

		String valid_disc = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(valid_disc).isEqualTo(Inv_data.get(0).get("Column14"));

	}

	public static void PO_Edit_AddOrderItems_AddComments_255Charac(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		WebdriverWait.findElement("name", "productId").clear();
		WebdriverWait.findElement("name", "productId").sendKeys(data.get(0).get("Column2"));

		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(0).get("Column9"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data.get(0).get("Column8"));

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='shipGroupSeqId']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(0).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("id", "itemComment").sendKeys(Inv_data.get(6).get("Column1"));

		Select discType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		discType_dd.selectByVisibleText("Percentage");

		WebdriverWait.findElement("id", "discount").clear();
		WebdriverWait.findElement("id", "discount").sendKeys(data.get(0).get("Column17"));

		WebdriverWait.findElement("name", "submitItems").click();

		String comments = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(comments).isEqualTo(Inv_data.get(3).get("Column13"));

	}

	public static void PO_Edit_OtherDetails_Notes(List<HashMap<String, String>> data) {

		// Update Notes
		Procurement_PurchaseOrder_View.EditOrderLink();

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[12]/div[2]/table/tbody/tr[1]/td[2]/a[1]").click();
		WebdriverWait.findElement("id", "noteInfo").clear();
		WebdriverWait.findElement("xpath", "//*[@id='OrderupdateNote']/div/table/tbody[1]/tr[3]/td[2]/input").click();

		String notesdesc = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(notesdesc).isEqualTo(data.get(2).get("Column13"));

		WebdriverWait.findElement("id", "noteInfo").clear();
		WebdriverWait.findElement("id", "noteInfo").sendKeys("Update Test");

		WebdriverWait.findElement("xpath", "//*[@id='OrderupdateNote']/div/table/tbody[1]/tr[3]/td[2]/input").click();

		String sucMsg = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/p").getText();
		PO_PurchaseOrder_softAssert.assertThat(sucMsg).isEqualTo(data.get(9).get("Column17"));

		String intNotes = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[15]/div[2]/table/tbody/tr/td[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(intNotes).isEqualTo("Update Test");
		Procurement_PurchaseOrder_View.EditOrderLink();

		// Add Notes (Empty)
		WebdriverWait.findElement("link", "Add Note").click();
		WebdriverWait.findElement("xpath", "//*[@id='OrderNewNote']/div/table/tbody[1]/tr[3]/td[2]/input").click();

		String notesdesc1 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(notesdesc1).isEqualTo(data.get(2).get("Column13"));

		// Adding Notes
		WebdriverWait.findElement("id", "note").sendKeys("Add Notes Test");
		WebdriverWait.findElement("xpath", "//*[@id='OrderNewNote']/div/table/tbody[1]/tr[3]/td[2]/input").click();

		// String sucMsg = WebdriverWait.findElement("xpath", "//*[@id=
		// 'content-messages']/div/p").getText();
		// PO_PurchaseOrder_softAssert.assertThat(sucMsg).isEqualTo(data.get(9).get("Column17"));

		String addNotes = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[15]/div[2]/table/tbody/tr[2]/td[1]").getText();
		PO_PurchaseOrder_softAssert.assertThat(addNotes).isEqualTo("Add Notes Test");
		Procurement_PurchaseOrder_View.EditOrderLink();

		// Delete Notes
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[12]/div[2]/table/tbody/tr[2]/td[2]/a[2]").click();

		String sucMsgdelete = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		PO_PurchaseOrder_softAssert.assertThat(sucMsgdelete).isEqualTo(data.get(1).get("Column19"));

		// WebElement addednotes = WebdriverWait.findElement("xpath",
		// "//*[@id='content-main-section']/div[15]/div[2]/table/tbody/tr[2]/td[1]");
		// Assert.assertNull(addednotes);

	}

	public static void PO_Edit_AddOrderItems_AddProducts(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "productId").sendKeys(data.get(4).get("Column2"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(4).get("Column9"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data.get(4).get("Column8"));

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='shipGroupSeqId']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(4).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("name", "submitItems").click();
		// WebdriverWait.findElement("link", "View Order").click();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[9]/div[1]/ul/li[4]/a").click();

	}

	public static void CreateAsNewOrder() {

		WebdriverWait.findElement("link", "Create As New Order").click();

	}

	public static void PO_PurchaseOrder_assertions() {
		PO_PurchaseOrder_softAssert.assertAll();
	}
}
