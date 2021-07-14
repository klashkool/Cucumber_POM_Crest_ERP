package com.Facilities_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Sales_Modules.Sales_SalesOrder_View;
import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Facilities_Shipments extends Base {

	@Rule
	public static JUnitSoftAssertions purchaseShipment_softAssert = new JUnitSoftAssertions();

	public static Logger log = Logger.getLogger(Facilities_Shipments.class);

	public static void PO_ReceiveInventory(List<HashMap<String, String>> data, String sheetname, String prdID, String OrderQty, String PlannedQty, int row,
					int fac) throws InterruptedException {

		log.info("Receiving Inventory for all the products");

		WebdriverWait.findElement("link", "FACILITIES").click();
		WebdriverWait.findElement("link", "Stock Management").click();
		WebdriverWait.findElement("link", "Shipments").click();
		WebdriverWait.findElement("link", "Create Shipment").click();

		Select ShpType_DD = new Select(WebdriverWait.findElement("id", "shipmentTypeId"));
		ShpType_DD.selectByVisibleText("Purchase Shipment");

		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data.get(13).get("Column4"));
		WebdriverWait.findElement("id", "additionalShippingCharge").click();

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='primaryShipGroupSeqId']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(fac).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("id", "createSubmit").click();

		Facilities_Shipments_View.Capture_ShpID(sheetname, row);
		Facilities_Shipments_OrderItems.PO_Recv_Shpmnt(data, PlannedQty);
		Facilities_Facilities_StockManagement.ShipmentQty_Val(data, prdID, OrderQty, PlannedQty);
		Facilities_Facilities_StockManagement.PO_Recv_Shpmnt_Mapping();
		Facilities_Facilities_StockManagement.PO_Receive_Shpmnt_Loc_DD();
		Facilities_Shipments_View.PO_Shpmnt_Status_Val();

		WebdriverWait.findElement("xpath", "//*[@id='primaryOrderIdRowAndPrimaryShipGroupSeqId']/td[2]/a").click();

	}

	// Scenario: Receive PO Inventory (With Looping) Old code

	public static void PO_ReceiveInv(List<HashMap<String, String>> data, String sheetname, int no_of_shpmnt) throws InterruptedException {

		for (int Shp = 1; Shp <= no_of_shpmnt; Shp++) {

			log.info("Receiving Inventory for all the products");

			WebdriverWait.findElement("link", "FACILITIES").click();
			WebdriverWait.findElement("link", "Stock Management").click();
			WebdriverWait.findElement("link", "Shipments").click();
			WebdriverWait.findElement("link", "Create Shipment").click();

			Select ShpType_DD = new Select(WebdriverWait.findElement("id", "shipmentTypeId"));
			ShpType_DD.selectByVisibleText("Purchase Shipment");

			WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data.get(13).get("Column4"));
			WebdriverWait.findElement("id", "additionalShippingCharge").click();

			if (Shp == 1) {

				List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='primaryShipGroupSeqId']//option");
				for (WebElement option : options) {
					if (option.getText().contains(data.get(0).get("Column7"))) {
						option.click();
						break;
					}
				}

				WebdriverWait.findElement("id", "createSubmit").click();

				Facilities_Shipments_View.Capture_ShpID(sheetname, 15);
				Facilities_Shipments_OrderItems.PO_Recv_Shpmnt(data, "Column29");
				Facilities_Facilities_StockManagement.ShipmentQty_Val(data, "Column27", "Column28", "Column29");
				Facilities_Facilities_StockManagement.PO_Recv_Shpmnt_Mapping();
				Facilities_Facilities_StockManagement.PO_Receive_Shpmnt_Loc_DD();
				Facilities_Shipments_View.PO_Shpmnt_Status_Val();

			} else if (Shp == 2) {

				List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='primaryShipGroupSeqId']//option");
				for (WebElement option : options) {
					if (option.getText().contains(data.get(0).get("Column7"))) {
						option.click();
						break;
					}
				}
				WebdriverWait.findElement("id", "createSubmit").click();

				Facilities_Shipments_View.Capture_ShpID(sheetname, 16);
				Facilities_Shipments_OrderItems.PO_Recv_Shpmnt(data, "Column44");
				Facilities_Facilities_StockManagement.ShipmentQty_Val(data, "Column42", "Column43", "Column44");
				Facilities_Facilities_StockManagement.PO_Recv_Shpmnt_Mapping();
				Facilities_Facilities_StockManagement.PO_Receive_Shpmnt_Loc_DD();
				Facilities_Shipments_View.PO_Shpmnt_Status_Val();

			} else if (Shp == 3) {

				List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='primaryShipGroupSeqId']//option");

				if (no_of_shpmnt == 3) {
					for (WebElement option : options) {
						if (option.getText().contains(data.get(2).get("Column7"))) {
							option.click();
							break;
						}
					}
				} else {
					for (WebElement option : options) {
						if (option.getText().contains(data.get(5).get("Column7"))) {
							option.click();
							break;
						}
					}
				}
				WebdriverWait.findElement("id", "createSubmit").click();

				Facilities_Shipments_View.Capture_ShpID(sheetname, 17);
				Facilities_Shipments_OrderItems.PO_Recv_Shpmnt(data, "Column59");
				Facilities_Facilities_StockManagement.ShipmentQty_Val(data, "Column57", "Column58", "Column59");
				Facilities_Facilities_StockManagement.PO_Recv_Shpmnt_Mapping();
				Facilities_Facilities_StockManagement.PO_Receive_Shpmnt_Loc_DD();
				Facilities_Shipments_View.PO_Shpmnt_Status_Val();

				// if (no_of_shpmnt == 3) {
				// WebdriverWait.findElement("xpath",
				// "//*[@id='primaryOrderIdRowAndPrimaryShipGroupSeqId']/td[2]/a").click();
				// }

			} else if (Shp == 4) {

				List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='primaryShipGroupSeqId']//option");
				for (WebElement option : options) {
					if (option.getText().contains(data.get(8).get("Column7"))) {
						option.click();
						break;
					}
				}
				WebdriverWait.findElement("id", "createSubmit").click();

				Facilities_Shipments_View.Capture_ShpID(sheetname, 18);
				Facilities_Shipments_OrderItems.PO_Recv_Shpmnt(data, "Column46");
				Facilities_Facilities_StockManagement.ShipmentQty_Val(data, "Column44", "Column45", "Column46");
				Facilities_Facilities_StockManagement.PO_Recv_Shpmnt_Mapping();
				Facilities_Facilities_StockManagement.PO_Receive_Shpmnt_Loc_DD();
				Facilities_Shipments_View.PO_Shpmnt_Status_Val();

				// WebdriverWait.findElement("xpath",
				// "//*[@id='primaryOrderIdRowAndPrimaryShipGroupSeqId']/td[2]/a").click();
			}
			WebdriverWait.findElement("xpath", "//*[@id='primaryOrderIdRowAndPrimaryShipGroupSeqId']/td[2]/a").click();
		}
	}

	public static void PO_ShipID_Search(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "FACILITIES").click();
		WebdriverWait.findElement("link", "Stock Management").click();
		WebdriverWait.findElement("link", "Shipments").click();

		WebdriverWait.findElement("id", "shipmentId").sendKeys(data.get(13).get("Column5"));

		WebdriverWait.findElement("xpath", "//*[@id='searchCriteria']/table/tbody[1]/tr[5]/td/input").click();

		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a").click();

	}

	// ************************************************************************************************************************************************//
	// SO Flow
	// ************************************************************************************************************************************************//

	public static void SO_CreateShipment(List<HashMap<String, String>> data, String sheetname, String prdID, String ordQty_colnum, String plndQty_colnum,
					int row, int fac) throws InterruptedException {

		WebdriverWait.findElement("link", "FACILITIES").click();
		WebdriverWait.findElement("link", "Stock Management").click();
		WebdriverWait.findElement("link", "Shipments").click();
		WebdriverWait.findElement("link", "Create Shipment").click();

		Select ShpType_DD = new Select(WebdriverWait.findElement("id", "shipmentTypeId"));
		ShpType_DD.selectByVisibleText("Sales Shipment");

		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data.get(13).get("Column4"));
		WebdriverWait.findElement("id", "picklistBinId").click();

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='primaryShipGroupSeqId']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(fac).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("id", "createSubmit").click();
		Facilities_Shipments_View.Capture_ShpID(sheetname, row);
		Facilities_Shipments_ShipmentPlan.Add_ShipmentPlan(data, plndQty_colnum);
		Facilities_Shipments_ShipmentPlan.ShipmentQty_Val(data, prdID, ordQty_colnum, plndQty_colnum);
		Facilities_Shipments_OrderItems.SO_Issue_Shpmnt();
		Facilities_Shipments_Edit.ShpStatus();

	}

	public static void SO_CreateShipment_Old(List<HashMap<String, String>> data, String sheetname) throws InterruptedException {

		for (int Shp = 1; Shp <= 4; Shp++) {

			log.info("Create Shipments for all the products");

			WebdriverWait.findElement("link", "FACILITIES").click();
			WebdriverWait.findElement("link", "Stock Management").click();
			WebdriverWait.findElement("link", "Shipments").click();
			WebdriverWait.findElement("link", "Create Shipment").click();

			Select ShpType_DD = new Select(WebdriverWait.findElement("id", "shipmentTypeId"));
			ShpType_DD.selectByVisibleText("Sales Shipment");

			WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data.get(13).get("Column4"));
			WebdriverWait.findElement("id", "picklistBinId").click();

			if (Shp == 1) {
				log.info("Create Shipment with partial qty for Ship1");

				List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='primaryShipGroupSeqId']//option");
				for (WebElement option : options) {
					if (option.getText().contains(data.get(0).get("Column7"))) {
						option.click();
						break;
					}
				}

				WebdriverWait.findElement("id", "createSubmit").click();
				Facilities_Shipments_View.Capture_ShpID(sheetname, 15);
				Facilities_Shipments_ShipmentPlan.Add_ShipmentPlan(data, "Column22");
				Facilities_Shipments_OrderItems.SO_Issue_Shpmnt();
				Facilities_Shipments_Edit.ShpStatus();

			} else if (Shp == 2) {

				log.info("Create Shipment for remaining partial qty for Ship1");

				List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='primaryShipGroupSeqId']//option");
				for (WebElement option : options) {
					if (option.getText().contains(data.get(0).get("Column7"))) {
						option.click();
						break;
					}
				}
				WebdriverWait.findElement("id", "createSubmit").click();
				Facilities_Shipments_View.Capture_ShpID(sheetname, 16);
				Facilities_Shipments_ShipmentPlan.Add_ShipmentPlan(data, "Column30");
				Facilities_Shipments_OrderItems.SO_Issue_Shpmnt();
				Facilities_Shipments_Edit.ShpStatus();

			} else if (Shp == 3) {

				log.info("Receiving Inventory with Full qty for Ship2");

				List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='primaryShipGroupSeqId']//option");
				for (WebElement option : options) {
					if (option.getText().contains(data.get(5).get("Column7"))) {
						option.click();
						break;
					}
				}
				WebdriverWait.findElement("id", "createSubmit").click();
				Facilities_Shipments_View.Capture_ShpID(sheetname, 17);
				Facilities_Shipments_ShipmentPlan.Add_ShipmentPlan(data, "Column38");
				Facilities_Shipments_OrderItems.SO_Issue_Shpmnt();
				Facilities_Shipments_Edit.ShpStatus();
			} else if (Shp == 4) {

				log.info("Receiving Inventory with Full qty for Ship4");

				List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='primaryShipGroupSeqId']//option");
				for (WebElement option : options) {
					if (option.getText().contains(data.get(8).get("Column7"))) {
						option.click();
						break;
					}
				}
				WebdriverWait.findElement("id", "createSubmit").click();
				Facilities_Shipments_View.Capture_ShpID(sheetname, 18);
				Facilities_Shipments_ShipmentPlan.Add_ShipmentPlan(data, "Column46");
				Facilities_Shipments_OrderItems.SO_Issue_Shpmnt();
				Facilities_Shipments_Edit.ShpStatus();
			}
		}

		// Capturing all the Invoice ID's
		Sales_SalesOrder_View.SO_CaptureInvID(sheetname);
	}

	public static void PO_Return_Shipment(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "FACILITIES").click();
		WebdriverWait.findElement("link", "Stock Management").click();
		WebdriverWait.findElement("link", "Shipments").click();
		WebdriverWait.findElement("link", "Create Shipment").click();

		Select ShpType_DD = new Select(WebdriverWait.findElement("id", "shipmentTypeId"));
		ShpType_DD.selectByVisibleText("Purchase Return");

		WebdriverWait.findElement("name", "primaryReturnId").sendKeys(data.get(13).get("Column10"));

		Select currency_DD = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		currency_DD.selectByVisibleText(data.get(0).get("Column6"));

		WebdriverWait.findElement("id", "createSubmit").click();

	}

	///////////////////////////////////// Purchase Shipment Negative
	///////////////////////////////////// Flow//////////////////////////////////////////

	public static void PurchaseShipment_Navigation() {

		WebdriverWait.findElement("link", "FACILITIES").click();
		WebdriverWait.findElement("link", "Stock Management").click();
		WebdriverWait.findElement("link", "Shipments").click();
		WebdriverWait.findElement("link", "Create Shipment").click();

		Select ShpType_DD = new Select(WebdriverWait.findElement("id", "shipmentTypeId"));
		ShpType_DD.selectByVisibleText("Purchase Shipment");
	}

	public static void MandatoryFieldsVal_CreatePurchaseShipment(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "createSubmit").click();

		String orderID_Req = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String fac = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String cur = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();
		String dest_fac = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[4]").getText();

		purchaseShipment_softAssert.assertThat(orderID_Req).isEqualTo(data.get(11).get("Column21"));
		purchaseShipment_softAssert.assertThat(fac).isEqualTo(data.get(8).get("Column8"));
		purchaseShipment_softAssert.assertThat(cur).isEqualTo(data.get(2).get("Column11"));
		purchaseShipment_softAssert.assertThat(dest_fac).isEqualTo(data.get(10).get("Column8"));

	}

	public static void InvalidOrderID_Val(List<HashMap<String, String>> data, List<HashMap<String, String>> data1) {

		// Validation with Sales Order ID
		Select ShpType_DD = new Select(WebdriverWait.findElement("id", "shipmentTypeId"));
		ShpType_DD.selectByVisibleText("Purchase Shipment");

		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys("UD/SO/00472");
		WebdriverWait.findElement("id", "additionalShippingCharge").click();

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='primaryShipGroupSeqId']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data1.get(0).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("id", "createSubmit").click();

		String salesID_Req = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipment_softAssert.assertThat(salesID_Req).isEqualTo(data.get(12).get("Column21"));

		// Valiation with Completed OrderID
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys("PO1883");
		WebdriverWait.findElement("id", "additionalShippingCharge").click();

		WebdriverWait.findElement("id", "createSubmit").click();

		String PO_CompletedID_Req = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipment_softAssert.assertThat(PO_CompletedID_Req).isEqualTo(data.get(13).get("Column21"));

		// Validation with Processing Order ID
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys("PO1887");
		WebdriverWait.findElement("id", "additionalShippingCharge").click();

		WebdriverWait.findElement("id", "createSubmit").click();

		String PO_processing_Req = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipment_softAssert.assertThat(PO_processing_Req).isEqualTo(data.get(14).get("Column21"));

		// Validation with Cancelled Order ID
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys("PO1863");
		WebdriverWait.findElement("id", "additionalShippingCharge").click();

		WebdriverWait.findElement("id", "createSubmit").click();

		String PO_cancelled_Req = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipment_softAssert.assertThat(PO_cancelled_Req).isEqualTo(data.get(15).get("Column21"));

		// Validaation with Held Order ID
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys("PO1824");
		WebdriverWait.findElement("id", "additionalShippingCharge").click();

		WebdriverWait.findElement("id", "createSubmit").click();

		String PO_held_Req = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipment_softAssert.assertThat(PO_held_Req).isEqualTo(data.get(16).get("Column21"));
	}

	public static void AddtlShippingChargeDesc_255charac_Val(List<HashMap<String, String>> data, List<HashMap<String, String>> data1) {

		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data1.get(13).get("Column4"));
		WebdriverWait.findElement("id", "addtlShippingChargeDesc").sendKeys(data.get(6).get("Column1"));

		String desc = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipment_softAssert.assertThat(desc).isEqualTo(data.get(5).get("Column13"));

		WebdriverWait.findElement("id", "createSubmit").click();

		WebdriverWait.findElement("id", "addtlShippingChargeDesc").clear();

	}

	public static void EstimatedArrival_Val(List<HashMap<String, String>> data, List<HashMap<String, String>> data1) {

		WebdriverWait.findElement("name", "estimatedArrivalDate_i18n").sendKeys(data.get(8).get("Column1"));
		String estimatedArrival_Date = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		// purchaseShipment_softAssert.assertThat(estimatedArrival_Date).isEqualTo(data.get(17).get("Column21"));

	}

	public static void PO_CreateShipment(List<HashMap<String, String>> data) {

		Select ShpType_DD = new Select(WebdriverWait.findElement("id", "shipmentTypeId"));
		ShpType_DD.selectByVisibleText("Purchase Shipment");

		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data.get(13).get("Column4"));
		WebdriverWait.findElement("id", "additionalShippingCharge").click();

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='primaryShipGroupSeqId']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(0).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("id", "createSubmit").click();
	}

	public static void PurchaseReturnShipment_Navigation() {

		WebdriverWait.findElement("link", "FACILITIES").click();
		WebdriverWait.findElement("link", "Stock Management").click();
		WebdriverWait.findElement("link", "Shipments").click();
		WebdriverWait.findElement("link", "Create Shipment").click();

		Select ShpType_DD = new Select(WebdriverWait.findElement("id", "shipmentTypeId"));
		ShpType_DD.selectByVisibleText("Purchase Return");
	}

	public static void MandatoryFieldsVal_CreatePurchaseReturn(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "createSubmit").click();

		String returnID = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String cur = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String fac = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();

		purchaseShipment_softAssert.assertThat(returnID).isEqualTo(data.get(28).get("Column21"));
		purchaseShipment_softAssert.assertThat(cur).isEqualTo(data.get(2).get("Column11"));
		purchaseShipment_softAssert.assertThat(fac).isEqualTo(data.get(11).get("Column8"));

	}

	public static void InvalidReturnID(List<HashMap<String, String>> data) {

		// Enter Sales Return ID
		WebdriverWait.findElement("name", "primaryReturnId").sendKeys("11167");
		WebdriverWait.findElement("id", "createSubmit").click();

		String salesID = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipment_softAssert.assertThat(salesID).isEqualTo(data.get(29).get("Column21"));

		// Enter Return ID with Requested Status
		// WebdriverWait.findElement("name", "primaryReturnId").clear();
		// WebdriverWait.findElement("name",
		// "primaryReturnId").sendKeys("11269");
		// WebdriverWait.findElement("id", "createSubmit").click();
		//
		// String reqStatus = WebdriverWait.findElement("xpath", "//*[@id=
		// 'content-messages']/div/ul/li[1]").getText();
		// purchaseShipment_softAssert.assertThat(reqStatus).isEqualTo(data.get(30).get("Column21"));
	}

	public static void AddShipCharge_Val(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		WebdriverWait.findElement("name", "primaryReturnId").clear();
		WebdriverWait.findElement("name", "primaryReturnId").sendKeys(data.get(13).get("Column10"));

		WebdriverWait.findElement("id", "additionalShippingCharge").sendKeys(Inv_data.get(5).get("Column1"));

		WebdriverWait.findElement("id", "createSubmit").click();

		String addshipcharg = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipment_softAssert.assertThat(addshipcharg).isEqualTo(Inv_data.get(23).get("Column12"));

	}

	public static void AddShipChargeDesc_Val(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		WebdriverWait.findElement("name", "primaryReturnId").clear();
		WebdriverWait.findElement("name", "primaryReturnId").sendKeys(data.get(13).get("Column10"));

		WebdriverWait.findElement("id", "additionalShippingCharge").clear();
		WebdriverWait.findElement("id", "additionalShippingCharge").sendKeys(Inv_data.get(4).get("Column1"));
		WebdriverWait.findElement("id", "addtlShippingChargeDesc").sendKeys(Inv_data.get(6).get("Column1"));

		WebdriverWait.findElement("id", "createSubmit").click();

		String addshipchargDesc = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipment_softAssert.assertThat(addshipchargDesc).isEqualTo(Inv_data.get(5).get("Column13"));

		WebdriverWait.findElement("id", "addtlShippingChargeDesc").clear();
	}

	public static void PurchaseRetrunCancelBtn_Val() {

		WebdriverWait.findElement("link", "Cancel").click();

		WebElement link = WebdriverWait.findElement("link", "Create Shipment");
		purchaseShipment_softAssert.assertThat(link.isDisplayed());
	}

	public static void CreatePurchaseReturn(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "primaryReturnId").sendKeys(data.get(13).get("Column10"));
		WebdriverWait.findElement("id", "createSubmit").click();
	}


	public static void PurchaseShipment_assertions() {
		purchaseShipment_softAssert.assertAll();
	}

}
