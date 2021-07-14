package com.Sales_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Procurement_Modules.Procurement_PurchaseOrder_View;
import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Sales_SalesOrder extends Base {

	@Rule
	public static JUnitSoftAssertions Sales_SO_softAssert = new JUnitSoftAssertions();

	public static Logger log = Logger.getLogger(Sales_SalesOrder.class);

	public static String so_Number;

	public static void SO_StartOrder(List<HashMap<String, String>> data, String soname, String payterm) {

		WebdriverWait.findElement("link", "SALES").click();
		WebdriverWait.findElement("link", "Sales Order").click();
		WebdriverWait.findElement("link", "Create Sales Order").click();

		// System.out.println(data.get(0).get("Column3"));

		Select org_dd = new Select(WebdriverWait.findElement("id", "billToCustomerPartyId"));
		org_dd.selectByVisibleText(data.get(0).get("Column3"));

		WebdriverWait.findElement("name", "partyId").sendKeys(data.get(0).get("Column4"));//

		WebdriverWait.findElement("name", "orderName").sendKeys(soname);

		// Adding Current Date(From Date)

		// SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// String Fromdate = (dateFormat.format(new Date()));
		// WebdriverWait.findElement("name",
		// "orderDate_i18n").sendKeys(Fromdate);

		Select ordType_dd = new Select(WebdriverWait.findElement("id", "orderType"));
		ordType_dd.selectByVisibleText(data.get(0).get("Column5"));

		Select currency_dd = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		currency_dd.selectByVisibleText(data.get(0).get("Column6"));

		Select prdStore_dd = new Select(WebdriverWait.findElement("id", "productStoreId"));
		prdStore_dd.selectByVisibleText("General Sales Store");

		Select salesChn_dd = new Select(WebdriverWait.findElement("id", "salesChannelEnumId"));
		salesChn_dd.selectByVisibleText("Web Channel");

		Select numType_dd = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		numType_dd.selectByVisibleText("UIDAI Sales Order");

		Select payTerm_dd = new Select(WebdriverWait.findElement("id", "agreementId"));
		payTerm_dd.selectByVisibleText(payterm);

	}

	public static void SO_ClickContinueBtn() {
		WebdriverWait.findElement("link", "Continue").click();
	}

	public static void SO_Shipping_Details_SingleFacility(List<HashMap<String, String>> data) {

		log.info("Adding Ship Groups");
		Select shpFacility = new Select(WebdriverWait.findElement("id", "0_shipGroupFacilityId"));
		shpFacility.selectByVisibleText(data.get(0).get("Column7"));

		WebdriverWait.findElement("link", "Continue").click();
	}

	public static void SO_Shipping_Details_MultipleFacility(List<HashMap<String, String>> data, int loopcount, int facility1, int facility2, int facility3) {

		log.info("Adding Ship Groups");
		String facility = "_shipGroupFacilityId";

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

	public static void SO_OrderItems(List<HashMap<String, String>> data, int loopcount) throws InterruptedException {

		log.info("SO Adding products");
		for (int p = 0; p <= loopcount; p++) {

			Thread.sleep(1000);
			WebdriverWait.findElement("name", "add_product_id").sendKeys(data.get(p).get("Column2"));

			WebdriverWait.findElement("name", "quantity").sendKeys(data.get(p).get("Column9"));

			Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
			dept_dd.selectByVisibleText(data.get(p).get("Column8"));

			Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
			discountType_dd.selectByVisibleText("-Select-");

			List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='toGroupIndex']//option");
			for (WebElement option : options) {
				if (option.getText().contains(data.get(p).get("Column7"))) {
					option.click();
					break;
				}
			}

			WebElement ATP = WebdriverWait.findElement("id", "ATP");
			WebElement QOH = WebdriverWait.findElement("id", "QOH");
			Assert.assertEquals(true, ATP.isDisplayed());
			Assert.assertEquals(true, QOH.isDisplayed());

			WebElement price = WebdriverWait.findElement("id", "priceValue");
			Assert.assertEquals(true, price.isDisplayed());
			WebdriverWait.findElement("name", "itemComment").sendKeys("Test");
			WebdriverWait.findElement("name", "submitItems").click();
		}

		WebdriverWait.findElement("link", "Continue").click();
	}

	public static void SO_OrderItems_DropShip(List<HashMap<String, String>> data, int loopcount) throws InterruptedException {

		log.info("SO Adding products");
		for (int p = 0; p <= loopcount; p++) {

			Thread.sleep(1000);
			WebdriverWait.findElement("name", "add_product_id").sendKeys(data.get(p).get("Column2"));

			WebdriverWait.findElement("name", "quantity").sendKeys(data.get(p).get("Column9"));

			Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
			dept_dd.selectByVisibleText(data.get(p).get("Column8"));
//			Thread.sleep(10000);
//			//Drop Ship YES
//			Select dropShip_dd = new Select(WebdriverWait.findElement("name", "dropShipReq"));
//			dropShip_dd.selectByVisibleText("Yes");
//			Thread.sleep(10000);
//			//Drop Ship Supplier
//			Select supplierPartyId_dd = new Select(WebdriverWait.findElement("name", "supplierPartyId"));
//			supplierPartyId_dd.selectByVisibleText(data.get(13).get("Column2"));
			if(p==0) {
			Thread.sleep(3000);
			WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form/table[2]/tbody/tr[4]/td[2]/span[1]").click();
			Thread.sleep(3000);
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ARROW_UP).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(3000);
			}
			Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
			discountType_dd.selectByVisibleText("-Select-");

			List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='toGroupIndex']//option");
			for (WebElement option : options) {
				if (option.getText().contains(data.get(p).get("Column7"))) {
					option.click();
					break;
				}
			}

			WebElement ATP = WebdriverWait.findElement("id", "ATP");
			WebElement QOH = WebdriverWait.findElement("id", "QOH");
			Assert.assertEquals(true, ATP.isDisplayed());
			Assert.assertEquals(true, QOH.isDisplayed());

			WebElement price = WebdriverWait.findElement("id", "priceValue");
			Assert.assertEquals(true, price.isDisplayed());
			WebdriverWait.findElement("name", "itemComment").sendKeys("Test");
			WebdriverWait.findElement("name", "submitItems").click();
		}

		WebdriverWait.findElement("link", "Continue").click();
	}

	public static void SO_ReviewCreateSO(String sheetname) throws InterruptedException {

		WebdriverWait.findElement("link", "Create").click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Sales_SalesOrder_View.Capture_SONum(sheetname);

	}

	public static void SO_ReviewCreateSO_WithoutAlert(String sheetname) {

		WebdriverWait.findElement("link", "Create").click();
		Sales_SalesOrder_View.Capture_SONum(sheetname);

	}

	public static void OfflinePayment_Chkbox() {

		WebdriverWait.findElement("xpath", "//*[@id= 'paymentMethodTypeAndId'][@value = 'EXT_OFFLINE']").click();
		WebdriverWait.findElement("link", "Continue").click();
	}

	public static void BillingAcct_Chkbox() {

		WebdriverWait.findElement("xpath", "//*[@id='paymentMethodTypeAndId'][@value ='Payment_billing']").click();
		WebElement billingAcctBal = WebdriverWait.findElement("id", "remainingBalance");
		Assert.assertEquals(true, billingAcctBal.isDisplayed());
	}

	public static void BillingAcct_value_Val(List<HashMap<String, String>> data) {

		String billingAcct_Value = WebdriverWait.findElement("id", "remainingBalance").getText().replace(" INR", "");
		Assert.assertEquals(data.get(13).get("Column1"), billingAcct_Value);

	}

	public static void SO_ReviewCreateSO_TC01(String sheetname) {
		WebdriverWait.findElement("link", "Create").click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Sales_SalesOrder_View.Capture_SONum(sheetname);
	}

	public static void Search_SO(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "SALES").click();
		WebdriverWait.findElement("link", "Sales Order").click();
		WebdriverWait.findElement("id", "orderId").sendKeys(data.get(13).get("Column4"));
		WebdriverWait.findElement("xpath", "//*[@id='searchCriteria']/div/table/tbody/tr[16]/td/input").click();
		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[2]/a").click();

	}

	public static void Edit_lineItem_Discounts(List<HashMap<String, String>> data) {

		// Search_PO(data);
		Sales_SalesOrder_View.EditOrderLink();

		for (int a = 1; a <= 5; a++) {

			Select disType = new Select(WebdriverWait.findElement("id", "dtm_" + a));
            disType.selectByVisibleText(data.get(a - 1).get("Column16"));

            WebdriverWait.findElement("id", "dvm_" + a).sendKeys(data.get(a - 1).get("Column17"));

            WebdriverWait.findElement("id", "selectedItem_" + a).click();
		}

		WebdriverWait.findElement("link", "Recalculate Selected").click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public static void Edit_lineItem_Discounts_WithoutAlert(List<HashMap<String, String>> data) {

		// Search_PO(data);
		Sales_SalesOrder_View.EditOrderLink();

		for (int a = 1; a <= 5; a++) {

			Select disType = new Select(WebdriverWait.findElement("id", "dtm_" + a));
            disType.selectByVisibleText(data.get(a - 1).get("Column16"));

            WebdriverWait.findElement("id", "dvm_" + a).sendKeys(data.get(a - 1).get("Column17"));

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

		Select fac = new Select(WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[7]/div[2]/form[2]/table/tbody/tr[1]/td[4]/select"));
		fac.selectByVisibleText(data.get(0).get("Column7"));

		WebdriverWait.findElement("id", "amount").clear();

		String AdjValue = data.get(6).get("Column19");
		String Val = AdjValue.replace("₹", "").replace("$", "").replace(",", "");

		WebdriverWait.findElement("id", "amount").sendKeys("-" + Val);
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div//form[2]//table//tbody//tr[3]//td//input").click();

		String adj_success_msg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		Assert.assertEquals("Order Adjustment created successfully.", adj_success_msg);

	}

	public static void ServiceInvoicedQty_Val(List<HashMap<String, String>> data, String prdID, String ordQty_colnum, String invoicedQty_colnum) {

		List<WebElement> service_items = WebdriverWait.findElements("xpath", "//*[@id='serviceInvoicedContainer']/table/tbody/tr");

		System.out.println(service_items.size());

		int j = 0;
		for (int a = 1; a <= service_items.size(); a++) {
			String PrdId = WebdriverWait.findElement("xpath", "//*[@id='serviceInvoicedContainer']/table/tbody/tr[" + a + "]/td[3]").getText();

			System.out.println(PrdId);

			if (PrdId.contains(data.get(j).get(prdID))) {

				String totalOrdQty = WebdriverWait.findElement("xpath", "//*[@id='serviceInvoicedContainer']/table/tbody/tr[" + a + "]/td[4]").getText();
				String invoicedQty = WebdriverWait.findElement("xpath", "//*[@id='serviceInvoicedContainer']/table/tbody/tr[" + a + "]/td[5]").getText();

				Assert.assertEquals(data.get(j).get(ordQty_colnum), totalOrdQty);
				Assert.assertEquals(data.get(j).get(invoicedQty_colnum), invoicedQty);
				j++;
			}
		}

		WebdriverWait.findElement("xpath", "//*[@id='serviceSalesHeaderContainer']/table/tbody/tr[1]/td[2]/a").click();
	}

	public static void Create_ServiceOrder_Inv1(List<HashMap<String, String>> data, String prdID, String plndQty_colnum) {

		List<WebElement> service_items = WebdriverWait.findElements("xpath", "//*[@id='serviceInvoiceContainer']/form/table/tbody/tr");

		int j = 0;
		for (int a = 1; a <= service_items.size(); a++) {

			String PrdId = WebdriverWait.findElement("xpath", "//*[@id='serviceInvoiceContainer']/form/table/tbody/tr[" + a + "]/td[3]").getText();
			if (PrdId.contains(data.get(j).get(prdID))) {

				WebdriverWait.findElement("xpath", "//*[@id='serviceInvoiceContainer']/form/table/tbody/tr[" + a + "]/td[1]/input").click();
				WebdriverWait.findElement("xpath", "//*[@id='serviceInvoiceContainer']/form/table/tbody/tr[" + a + "]/td[7]/input").clear();
				WebdriverWait.findElement("xpath", "//*[@id='serviceInvoiceContainer']/form/table/tbody/tr[" + a + "]/td[7]/input")
								.sendKeys(data.get(j).get(plndQty_colnum));
				j++;
			}
		}
		WebdriverWait.findElement("xpath", "//*[@id='serviceInvoiceContainer']/form/div/input").click();
	}

	public static void Create_ServiceOrder_Inv2(List<HashMap<String, String>> data, String prdID, String plndQty_colnum) {

		List<WebElement> service_items = WebdriverWait.findElements("xpath", "//*[@id='serviceInvoiceContainer']/form/table/tbody/tr");

		int j = 0;
		for (int a = 1; a <= service_items.size(); a++) {

			String PrdId = WebdriverWait.findElement("xpath", "//*[@id='serviceInvoiceContainer']/form/table/tbody/tr[" + a + "]/td[3]").getText();
			if (PrdId.contains(data.get(j).get(prdID))) {

				WebdriverWait.findElement("xpath", "//*[@id='serviceInvoiceContainer']/form/table/tbody/tr[" + a + "]/td[1]/input").click();
				WebdriverWait.findElement("xpath", "//*[@id='serviceInvoiceContainer']/form/table/tbody/tr[" + a + "]/td[7]/input").clear();
				WebdriverWait.findElement("xpath", "//*[@id='serviceInvoiceContainer']/form/table/tbody/tr[" + a + "]/td[7]/input")
								.sendKeys(data.get(j).get(plndQty_colnum));
				j++;
			}
		}
		WebdriverWait.findElement("xpath", "//*[@id='serviceInvoiceContainer']/form/div/input").click();
	}

	public static void Create_ServiceOrder_Inv3(List<HashMap<String, String>> data, String prdID, String plndQty_colnum) {

		List<WebElement> service_items = WebdriverWait.findElements("xpath", "//*[@id='serviceInvoiceContainer']/form/table/tbody/tr");

		int j = 0;
		for (int a = 1; a <= service_items.size(); a++) {

			String PrdId = WebdriverWait.findElement("xpath", "//*[@id='serviceInvoiceContainer']/form/table/tbody/tr[" + a + "]/td[3]").getText();
			if (PrdId.contains(data.get(j).get(prdID))) {

				WebdriverWait.findElement("xpath", "//*[@id='serviceInvoiceContainer']/form/table/tbody/tr[" + a + "]/td[1]/input").click();
				WebdriverWait.findElement("xpath", "//*[@id='serviceInvoiceContainer']/form/table/tbody/tr[" + a + "]/td[7]/input").clear();
				WebdriverWait.findElement("xpath", "//*[@id='serviceInvoiceContainer']/form/table/tbody/tr[" + a + "]/td[7]/input")
								.sendKeys(data.get(j).get(plndQty_colnum));
				j++;
			}
		}
		WebdriverWait.findElement("xpath", "//*[@id='serviceInvoiceContainer']/form/div/input").click();
	}

	//////////////////////////////////////// Negative Flows///////////////////////////////////////

	public static void NavigateToCreateSO() {
		WebdriverWait.findElement("link", "SALES").click();
		WebdriverWait.findElement("link", "Sales Order").click();
		WebdriverWait.findElement("link", "Create Sales Order").click();
	}

	public static void NavigateToSO() {
		WebdriverWait.findElement("link", "SALES").click();
		WebdriverWait.findElement("link", "Sales Order").click();
	}

	public static void Validate_SO_StartOrder_without_Mandatory_Fields(List<HashMap<String, String>> data) {

		Select org_dd = new Select(WebdriverWait.findElement("id", "billToCustomerPartyId"));
		org_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("id", "orderDate_i18n").clear();

		Select ordType_dd = new Select(WebdriverWait.findElement("id", "orderType"));
		ordType_dd.selectByVisibleText("-Select-");

		Select currency_dd = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		currency_dd.selectByVisibleText("-Select-");

		Select salesChn_dd = new Select(WebdriverWait.findElement("id", "salesChannelEnumId"));
		salesChn_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("link", "Continue").click();

		String path1 = "//*[@id='content-messages']/div/ul/li[";
		String path2 = "]";
		for (int i = 1; i <= 8; i++) {
			String all_ValidationMsg = WebdriverWait.findElement("xpath", path1 + i + path2).getText();
			if (i == 1) {
				Sales_SO_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(2).get("Column4"));
			} else if (i == 2) {
				Sales_SO_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(6).get("Column5"));
			} else if (i == 3) {
				Sales_SO_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(5).get("Column6"));
			} else if (i == 4) {
				Sales_SO_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(13).get("Column2"));
			} else if (i == 5) {
				Sales_SO_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(3).get("Column6"));
			} else if (i == 6) {
				Sales_SO_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(6).get("Column3"));
			} else if (i == 7) {
				Sales_SO_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(7).get("Column6"));
			} else if (i == 8) {
				Sales_SO_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(4).get("Column21"));
			}

		}
	}

	public static void navigateToShippingDetailsPage(List<HashMap<String, String>> data_Positive, String payterm) {
		SO_StartOrder(data_Positive, "TestSO", payterm);
		OfflinePayment_Chkbox();
		SO_ClickContinueBtn();
	}

	public static void Validate_SO_StartOrder_with_invalid_Date(List<HashMap<String, String>> data_Positive, List<HashMap<String, String>> data) {
		Select org_dd = new Select(WebdriverWait.findElement("id", "billToCustomerPartyId"));
		org_dd.selectByVisibleText(data_Positive.get(0).get("Column3"));

		WebdriverWait.findElement("name", "partyId").sendKeys(data_Positive.get(0).get("Column4"));

		Select salesChn_dd = new Select(WebdriverWait.findElement("id", "salesChannelEnumId"));
		salesChn_dd.selectByVisibleText("Web Channel");

		WebdriverWait.findElement("id", "orderDate_i18n").clear();
		WebdriverWait.findElement("id", "orderDate_i18n").sendKeys(data.get(5).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='EXT_OFFLINE']").click();

		WebdriverWait.findElement("link", "Continue").click();

		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_SO_softAssert.assertThat(validationMsg).isEqualTo(data.get(13).get("Column2"));
	}

	public static void Validate_SO_Shipping_Details_without_Mandatory_Fields(List<HashMap<String, String>> data) {

		Select billToAddrr = new Select(WebdriverWait.findElement("id", "billTocontactMechId"));
		billToAddrr.selectByVisibleText("-Select-");
		WebdriverWait.findElement("link", "Continue").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_SO_softAssert.assertThat(validationMsg).isEqualTo(data.get(5).get("Column8"));

		WebdriverWait.findElement("link", "Continue").click();
		String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_SO_softAssert.assertThat(validationMsg1).isEqualTo(data.get(6).get("Column8"));

		Select shpFacility = new Select(WebdriverWait.findElement("id", "0_shipGroupFacilityId"));
		shpFacility.selectByIndex(1);
		Select shpToAddrr = new Select(WebdriverWait.findElement("id", "0_contactMechId"));
		shpToAddrr.selectByVisibleText("-Select-");
		WebdriverWait.findElement("link", "Continue").click();
		String validationMsg2 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_SO_softAssert.assertThat(validationMsg2).isEqualTo(data.get(7).get("Column8"));

		Select shpMethod = new Select(WebdriverWait.findElement("id", "0_shipping_method"));
		shpMethod.selectByVisibleText("-Select-");
		WebdriverWait.findElement("link", "Continue").click();
		String validationMsg3 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_SO_softAssert.assertThat(validationMsg3).isEqualTo(data.get(8).get("Column8"));
	}

	public static void Validate_SO_Shipping_Details_with_invalid_date(List<HashMap<String, String>> data) {
		// Select billToAddrr = new Select(WebdriverWait.findElement("id",
		// "billTocontactMechId"));
		// billToAddrr.selectByIndex(1);
		Select shpFacility = new Select(WebdriverWait.findElement("id", "0_shipGroupFacilityId"));
		shpFacility.selectByIndex(1);
		Select shpToAddrr = new Select(WebdriverWait.findElement("id", "0_contactMechId"));
		shpToAddrr.selectByIndex(1);
		WebdriverWait.findElement("id", "0_shipBeforeDate_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("id", "0_shipAfterDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("link", "Continue").click();
		WebdriverWait.findElement("id", "0_shipBeforeDate_i18n").clear();
		WebdriverWait.findElement("id", "0_shipAfterDate_i18n").clear();
		String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		Sales_SO_softAssert.assertThat(validationMsg1).isEqualTo(data.get(16).get("Column2"));
		String validationMsg2 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
		Sales_SO_softAssert.assertThat(validationMsg2).isEqualTo(data.get(14).get("Column2"));
	}

	public static void Validate_SO_Shipping_Details_by_removing_ShipGroup(List<HashMap<String, String>> data) {
		// Select billToAddrr = new Select(WebdriverWait.findElement("id",
		// "billTocontactMechId"));
		// billToAddrr.selectByIndex(1);
		Select shpFacility = new Select(WebdriverWait.findElement("id", "0_shipGroupFacilityId"));
		shpFacility.selectByIndex(1);
		Select shpToAddrr = new Select(WebdriverWait.findElement("id", "0_contactMechId"));
		shpToAddrr.selectByIndex(1);
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[1]/ul/li[2]/a").click();// New
																												// Ship
																												// Group
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form/table[3]/tbody/tr[1]/td[2]/a").click();// Remove
																																							// Ship
																																							// Group
		WebDriverWait waitForAlert = new WebDriverWait(driver, 5);
		if (waitForAlert.until(ExpectedConditions.alertIsPresent()) == null)
			System.out.println("alert was not present");
		else
			System.out.println("alert was present");
		String getAlertMsg = driver.switchTo().alert().getText();
		Sales_SO_softAssert.assertThat(getAlertMsg).contains(data.get(9).get("Column21"));
		driver.switchTo().alert().accept();
		// WebDriverWait isElementVisible = new WebDriverWait(driver, 10);
		// if
		// (isElementVisible.until(ExpectedConditions.visibilityOfElementLocated(
		// By.xpath("//*[@id='content-main-section']/div[2]/div[2]/form/table[3]/tbody/tr[1]/td[1]/h1")))
		// == null)
		// Sales_SO_softAssert.assertThat("Ship Group removed successfully");
		// else
		// Sales_SO_softAssert.fail("Remove Ship Group feature is not working");
		try {
			if (driver.findElement(By.xpath("//*[@id='content-main-section']/div[2]/div[2]/form/table[3]/tbody/tr[1]/td[1]/h1")).isDisplayed())
				// if(WebdriverWait.findElement("xpath","//*[@id='content-main-section']/div[2]/div[2]/form/table[3]/tbody/tr[1]/td[1]/h1").isDisplayed())
				Sales_SO_softAssert.fail("Remove Ship Group feature is not working");
			else
				Sales_SO_softAssert.assertThat("Ship Group removed successfully");
		} catch (Exception e) {
			Sales_SO_softAssert.assertThat("Ship Group removed successfully");
		}
	}

	public static void Validate_SO_Shipping_Details_SameFacility_more_than_one_time(List<HashMap<String, String>> data) {
		for (int i = 0; i <= 1; i++) {
			Select shpFacility = new Select(WebdriverWait.findElement("id", i + "_shipGroupFacilityId"));
			shpFacility.selectByIndex(1);
			if (i == 1) {
				WebdriverWait.findElement("link", "Continue").click();
				try {
					if (driver.findElement(By.xpath("//*[@id='content-main-section']/div[2]/div[1]/ul/li")).isDisplayed()) {
						Sales_SO_softAssert.fail("Application is accepting same ship group more than one time");
					} else if (WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText()
									.equals("Yet to add validation msg. Appln is accepting same ship group more than one time")) {
						Sales_SO_softAssert.assertThat("Passed");
					} else {
						String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
						Sales_SO_softAssert.assertThat(validationMsg).isEqualTo("Yet to add validation msg");
					}
				} catch (Exception e) {
					e.getStackTrace();
				}

			}

			if (i == 0) {
				WebdriverWait.findElement("link", "New Ship Group").click();
			}
		}

	}

	public static void Validate_SO_Shipping_address_isDisplayed(List<HashMap<String, String>> data) {
		Select shpFacility = new Select(WebdriverWait.findElement("id", "0_shipGroupFacilityId"));
		shpFacility.selectByIndex(1);
		Select shpToAddrr = new Select(WebdriverWait.findElement("id", "0_contactMechId"));
		shpToAddrr.selectByIndex(1);
		String shipAddress = WebdriverWait.findElement("id", "0_shipAddress").getText();
		if(shipAddress.isEmpty()) {
		Sales_SO_softAssert.fail("Ship Address is empty");
	}
	}


	public static void Validating_SO_Search_date_range_with_invalid_date(List<HashMap<String, String>> data) {
		// Valid Date Range - From
		WebdriverWait.findElement("id", "minDate_i18n").sendKeys(data.get(8).get("Column1"));
		// Valid Date Range - Thru
		WebdriverWait.findElement("id", "maxDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Search']").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_SO_softAssert.assertThat(validationMsg).isEqualTo(data.get(19).get("Column2"));
	}

	public static void SO_OrderItems_AddLineItems_NegativeFlow(List<HashMap<String, String>> data, int loopcount) throws InterruptedException {

		log.info("SO Adding products");
		for (int p = 0; p <= loopcount; p++) {
			clearFields();
			Thread.sleep(1000);
			WebdriverWait.findElement("name", "add_product_id").sendKeys(data.get(p).get("Column2"));

			WebdriverWait.findElement("name", "quantity").sendKeys(data.get(p).get("Column9"));

			Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
			dept_dd.selectByVisibleText(data.get(p).get("Column8"));
			Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
			discountType_dd.selectByVisibleText("-Select-");
			WebdriverWait.findElement("id", "discount").clear();
			WebdriverWait.findElement("name", "itemComment").sendKeys("Test");
			WebdriverWait.findElement("name", "submitItems").click();
		}

	}

	public static void Validate_SO_OrderItems_without_Mandatory_Fields(List<HashMap<String, String>> data) {

		Select toGroupIndex_dd = new Select(WebdriverWait.findElement("id", "toGroupIndex"));
		toGroupIndex_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("name", "submitItems").click();

		String path1 = "//*[@id='content-messages']/div/ul/li[";
		String path2 = "]";
		for (int i = 1; i <= 5; i++) {
			String all_ValidationMsg = WebdriverWait.findElement("xpath", path1 + i + path2).getText();
			if (i == 1) {
				Sales_SO_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(7).get("Column3"));
			} else if (i == 2) {
				Sales_SO_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(12).get("Column10"));
			} else if (i == 3) {
				Sales_SO_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(9).get("Column8"));
			} else if (i == 4) {
				Sales_SO_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(2).get("Column11"));
			} else if (i == 5) {
				Sales_SO_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(1).get("Column9"));
			}
		}
	}

	public static void Validate_ProductID_with_InvalidData(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		WebdriverWait.findElement("id", "0_lookupId_add_product_id").sendKeys(data.get(5).get("Column1"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(4).get("Column1"));
		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data_Positive.get(0).get("Column8"));
		Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		discountType_dd.selectByVisibleText("-Select-");
		WebdriverWait.findElement("name", "itemComment").sendKeys("Test");
		WebdriverWait.findElement("name", "submitItems").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_SO_softAssert.assertThat(actMsg).isEqualTo(data.get(1).get("Column3"));
	}

	public static void clearFields() {
		WebdriverWait.findElement("name", "add_product_id").clear();
		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "itemComment").clear();
	}

	public static void Validate_Qty_with_SPL_characters(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		clearFields();
		WebdriverWait.findElement("name", "add_product_id").sendKeys(data_Positive.get(0).get("Column2"));

		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(0).get("Column1"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data_Positive.get(0).get("Column8"));

		Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		discountType_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("name", "itemComment").sendKeys("Test");
		WebdriverWait.findElement("name", "submitItems").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_SO_softAssert.assertThat(actMsg).isEqualTo(data.get(11).get("Column10"));
	}

	public static void Validate_Qty_with_Negative_Value(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		clearFields();
		WebdriverWait.findElement("name", "add_product_id").sendKeys(data_Positive.get(0).get("Column2"));

		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(1).get("Column1"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data_Positive.get(0).get("Column8"));

		Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		discountType_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("name", "itemComment").sendKeys("Test");
		WebdriverWait.findElement("name", "submitItems").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_SO_softAssert.assertThat(actMsg).isEqualTo(data.get(1).get("Column10"));
	}

	public static void Validate_Discount_with_SPL_characters(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		clearFields();
		WebdriverWait.findElement("name", "add_product_id").sendKeys(data_Positive.get(0).get("Column2"));

		WebdriverWait.findElement("name", "quantity").sendKeys(data_Positive.get(0).get("Column9"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data_Positive.get(0).get("Column8"));

		Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		discountType_dd.selectByVisibleText(data_Positive.get(0).get("Column16"));

		WebdriverWait.findElement("id", "discount").sendKeys(data.get(0).get("Column1"));

		WebdriverWait.findElement("name", "itemComment").sendKeys("Test");
		WebdriverWait.findElement("name", "submitItems").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_SO_softAssert.assertThat(actMsg).isEqualTo(data.get(0).get("Column14"));
		WebdriverWait.findElement("id", "discount").clear();
	}

	public static void Validate_Discount_without_DiscountType(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		clearFields();
		WebdriverWait.findElement("name", "add_product_id").sendKeys(data_Positive.get(0).get("Column2"));

		WebdriverWait.findElement("name", "quantity").sendKeys(data_Positive.get(0).get("Column9"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data_Positive.get(0).get("Column8"));

		Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		discountType_dd.selectByVisibleText("-Select-");
		WebdriverWait.findElement("id", "discount").sendKeys(data.get(0).get("Column17"));
		WebdriverWait.findElement("name", "itemComment").sendKeys("Test");
		WebdriverWait.findElement("name", "submitItems").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_SO_softAssert.assertThat(actMsg).isEqualTo(data.get(4).get("Column14"));
		WebdriverWait.findElement("id", "discount").clear();
	}

	public static void Validate_DiscountType_without_Discount(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		clearFields();
		WebdriverWait.findElement("name", "add_product_id").sendKeys(data_Positive.get(0).get("Column2"));

		WebdriverWait.findElement("name", "quantity").sendKeys(data_Positive.get(0).get("Column9"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data_Positive.get(0).get("Column8"));

		Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		discountType_dd.selectByVisibleText(data_Positive.get(0).get("Column16"));
		WebdriverWait.findElement("name", "itemComment").sendKeys("Test");
		WebdriverWait.findElement("name", "submitItems").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_SO_softAssert.assertThat(actMsg).isEqualTo(data.get(1).get("Column14"));
	}

	public static void Validate_without_DropShip(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) throws InterruptedException {
		clearFields();
		WebdriverWait.findElement("name", "add_product_id").sendKeys(data_Positive.get(0).get("Column2"));

		WebdriverWait.findElement("name", "quantity").sendKeys(data_Positive.get(0).get("Column9"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data_Positive.get(0).get("Column8"));

		WebdriverWait.findElement("id", "select2-dropShipReq-container").click();
		WebdriverWait.findElement("xpath", "//*[@id='select2-dropShipReq-results']/li[1]").click();

		// Select dropShip_dd = new Select(WebdriverWait.findElement("id",
		// "dropShipReq"));
		// dropShip_dd.selectByVisibleText("-Select-");

		Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		discountType_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("name", "itemComment").sendKeys("Test");
		WebdriverWait.findElement("name", "submitItems").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_SO_softAssert.assertThat(actMsg).isEqualTo(data.get(10).get("Column21"));
		WebdriverWait.findElement("id", "discount").clear();
	}

	public static void Validate_RemoveSelected_without_selecting_lineItem(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		clearFields();
		WebdriverWait.findElement("name", "add_product_id").sendKeys(data_Positive.get(0).get("Column2"));

		WebdriverWait.findElement("name", "quantity").sendKeys(data_Positive.get(0).get("Column9"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data_Positive.get(0).get("Column8"));
		Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		discountType_dd.selectByVisibleText("-Select-");
		WebdriverWait.findElement("name", "itemComment").sendKeys("Test");
		WebdriverWait.findElement("name", "submitItems").click();
		WebdriverWait.findElement("link", "Remove Selected").click();
		try {
			if (driver.findElement(By.xpath("//*[@id='content-messages']/div/ul/li")).isDisplayed()) {
				String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				Sales_SO_softAssert.assertThat(actMsg).isEqualTo("yet to add alert msg");// appln
																							// is
																							// not
																							// throwing
																							// alert
																							// msg
			} else {
				Sales_SO_softAssert.fail("No validation message displayed when trying to remove line item without selecting its checkbox");
			}
		} catch (Exception e) {
			Sales_SO_softAssert.fail("No validation message displayed when trying to remove line item without selecting its checkbox");
		}

		// boolean isLineItemExist = WebdriverWait.findElement("xpath",
		// "//*[@id='content-main-section']/div[3]/div[2]/form/table/tbody/tr/td[1]").isDisplayed();
		// Sales_SO_softAssert.assertThat(isLineItemExist).isEqualTo("true");
	}

	public static void Validate_RecalculateOrder_without_selecting_lineItem(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		clearFields();
		WebdriverWait.findElement("name", "add_product_id").sendKeys(data_Positive.get(0).get("Column2"));

		WebdriverWait.findElement("name", "quantity").sendKeys(data_Positive.get(0).get("Column9"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data_Positive.get(0).get("Column8"));
		Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		discountType_dd.selectByVisibleText(data_Positive.get(0).get("Column16"));
		WebdriverWait.findElement("id", "discount").sendKeys(data.get(0).get("Column17"));
		WebdriverWait.findElement("name", "itemComment").sendKeys("Test");
		WebdriverWait.findElement("name", "submitItems").click();
		WebdriverWait.findElement("link", "Recalculate Order").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_SO_softAssert.assertThat(actMsg).isEqualTo("yet to add alert msg");// appln
																					// is
																					// not
																					// throwing
																					// alert
																					// msg
	}

	public static void Validate_OrderItems_Back_button(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		clearFields();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[4]/a").click();// back
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[1]/ul/li[1]").getText();
		Sales_SO_softAssert.assertThat(actMsg).isEqualTo("Sales Order Shipping");
		WebdriverWait.findElement("link", "Continue").click();
	}

	public static void Create_SO_For_NegativeFlow(List<HashMap<String, String>> data_Positive, String sheetPositiveData, String payterm) {
		// NavigateToCreateSO();
		SO_StartOrder(data_Positive, "TestSONew", payterm);
		OfflinePayment_Chkbox();
		SO_Shipping_Details_SingleFacility(data_Positive);
		for (int i = 0; i <= 4; i++) {
			WebdriverWait.findElement("name", "add_product_id").sendKeys(data_Positive.get(i).get("Column2"));
			WebdriverWait.findElement("name", "quantity").sendKeys(data_Positive.get(i).get("Column9"));
			Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
			dept_dd.selectByVisibleText(data_Positive.get(i).get("Column8"));
			Select discountType_dd = new Select(WebdriverWait.findElement("id", "discountType"));
			discountType_dd.selectByVisibleText(data_Positive.get(i).get("Column16"));
			WebdriverWait.findElement("id", "discount").sendKeys(data_Positive.get(i).get("Column17"));
			WebdriverWait.findElement("name", "itemComment").sendKeys("Test");
			WebdriverWait.findElement("name", "submitItems").click();
		}
		WebdriverWait.findElement("link", "Continue").click();
		WebdriverWait.findElement("link", "Create").click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		log.info("SO Number Created Successfully");
		// String so_Num = WebdriverWait
		// .findElement("xpath",
		// "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[1]/td[2]/a")
		// .getText();
		// System.out.println("SO Number = " + so_Num);
		// so_Number = so_Num;
		// ExcelWriter.writeExcelFile(sheetPositiveData, 15, 4, so_Num);
	}

	public static void SO_View_assertions() {
		Sales_SO_softAssert.assertAll();
	}
}