package com.Facilities_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import com.Sales_Modules.Sales_SalesOrder;
import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Facilities_Shipments_OrderItems extends Base {

	@Rule
	public static JUnitSoftAssertions purchaseShipmentOrderItems_softAssert = new JUnitSoftAssertions();

	public static Logger log = Logger.getLogger(Sales_SalesOrder.class);

	public static void SO_Issue_Shpmnt() {

		WebdriverWait.findElement("link", "Order Items").click();
		// WebdriverWait.findElement("xpath",
		// "//*[@id='content-main-section']//div//tr[3]//td//input").click();
		WebdriverWait.findElement("name", "selectAll").click();
		WebdriverWait.findElement("id", "submitButton").click();
	}

	public static void PO_Recv_Shpmnt(List<HashMap<String, String>> data, String plndQty_column) throws InterruptedException {

		WebdriverWait.findElement("link", "Order Items").click();
		Thread.sleep(500);
		WebdriverWait.findElement("xpath", "//*[@id='FindOrderItems']/table/tbody/tr[3]/td/input").click();

		List<WebElement> Shpqty_List = WebdriverWait.findElements("xpath", "//*[@id='AddOrderItems']/table/tbody/tr");
		System.out.println(Shpqty_List.size());
		for (int a = 0; a <= Shpqty_List.size() - 2; a++) {
			Thread.sleep(500);
			WebdriverWait.findElement("id", "quantity_o_" + (a)).clear();
			WebdriverWait.findElement("id", "quantity_o_" + (a)).sendKeys(data.get(a).get(plndQty_column));
			// }
		}
		WebdriverWait.findElement("xpath", "//*[@id='AddOrderItems']/div/input").click();
		WebdriverWait.findElement("link", "Receive Inventory").click();
	}

	public static void PO_Return_IssueInv(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Order Items").click();
		WebdriverWait.findElement("id", "0_lookupId_button").click();
		WebdriverWait.findElement("xpath", "//*[@id='0_lookupId']/table/tbody/tr[4]/td[1]/a").click();
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(0).get("Column27"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr[2]/td[10]/input").click();
	}

	//////////////////////////////////////// Purchase Shipment negative
	//////////////////////////////////////// flow///////////////////////////////////////

	public static void InvalidQtyVal_AddToShipmentPlan_Table(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Order Items").click();

		WebdriverWait.findElement("xpath", "//*[@id='FindOrderItems']/table/tbody/tr[3]/td/input").click();

		// Empty Qty
		WebdriverWait.findElement("id", "quantity_o_0").clear();

		WebdriverWait.findElement("xpath", "//*[@id='AddOrderItems']/div/input").click();

		String qty_req = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		// String qty_num = WebdriverWait.findElement("xpath", "//*[@id=
		// 'content-messages']/div/ul/li[2]").getText();

		purchaseShipmentOrderItems_softAssert.assertThat(qty_req).isEqualTo(data.get(10).get("Column10"));
		// purchaseShipmentOrderItems_softAssert.assertThat(qty_num).isEqualTo(data.get(11).get("Column10"));

		// Adding Qty more than the Shipment Qty
		WebdriverWait.findElement("xpath", "//*[@id='FindOrderItems']/table/tbody/tr[3]/td/input").click();

		WebdriverWait.findElement("id", "quantity_o_0").clear();
		WebdriverWait.findElement("id", "quantity_o_0").sendKeys("1000");

		WebdriverWait.findElement("xpath", "//*[@id='AddOrderItems']/div/input").click();

		String valMsg = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();

		purchaseShipmentOrderItems_softAssert.assertThat(valMsg).contains("Not adding Order Item to plan for shipment");

		// Adding Qty with special characters
		WebdriverWait.findElement("xpath", "//*[@id='FindOrderItems']/table/tbody/tr[3]/td/input").click();

		WebdriverWait.findElement("id", "quantity_o_0").clear();
		WebdriverWait.findElement("id", "quantity_o_0").sendKeys(data.get(0).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='AddOrderItems']/div/input").click();

		String qty_num1 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipmentOrderItems_softAssert.assertThat(qty_num1).contains(data.get(12).get("Column10"));

		// Adding Qty with Negative Value
		WebdriverWait.findElement("xpath", "//*[@id='FindOrderItems']/table/tbody/tr[3]/td/input").click();

		WebdriverWait.findElement("id", "quantity_o_0").clear();
		WebdriverWait.findElement("id", "quantity_o_0").sendKeys(data.get(1).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='AddOrderItems']/div/input").click();

		String qty_neg = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipmentOrderItems_softAssert.assertThat(qty_neg).contains(data.get(12).get("Column10"));

	}

	public static void AddingComments_255Charac_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='FindOrderItems']/table/tbody/tr[3]/td/input").click();
		WebdriverWait.findElement("id", "shipmentContentDescription_o_0").clear();
		WebdriverWait.findElement("id", "shipmentContentDescription_o_0").sendKeys(data.get(6).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='AddOrderItems']/div/input").click();

		String comments = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr[2]/td[11]").getText();
		comments.length();

		purchaseShipmentOrderItems_softAssert.assertThat(comments.length()).isEqualTo(255);
	}

	public static void RemoveItemsIncluded() {

		List<WebElement> rows1 = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr");
		rows1.size();

		WebdriverWait.findElement("xpath", "//*[@id='removeOrderShipmentFromShipment']/a").click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

		List<WebElement> rows2 = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr");
		rows2.size();

		purchaseShipmentOrderItems_softAssert.assertThat(rows2.size()).isEqualTo(rows1.size() - 1);

	}

	public static void PO_Recv_Shpmnt_Negative() throws InterruptedException {

		WebdriverWait.findElement("link", "Order Items").click();
		WebdriverWait.findElement("link", "Receive Inventory").click();
	}

	public static void ReceiveInvLink() {

		WebdriverWait.findElement("link", "Receive Inventory").click();
	}

	public static void OrderItemsLink() {

		WebdriverWait.findElement("link", "Order Items").click();
	}

	public static void MandatoryFields_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[10]/input").click();

		String invID = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String qty_req = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();

		purchaseShipmentOrderItems_softAssert.assertThat(invID).contains(data.get(31).get("Column21"));
		purchaseShipmentOrderItems_softAssert.assertThat(qty_req).contains(data.get(0).get("Column10"));
	}

	public static void Invalid_Qty(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "0_lookupId_inventoryItemId").sendKeys("142140");

		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(3).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[10]/input").click();

		String zerovalue = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipmentOrderItems_softAssert.assertThat(zerovalue).contains(data.get(17).get("Column10"));

		// Negative Qty
		WebdriverWait.findElement("id", "0_lookupId_inventoryItemId").sendKeys("142140");

		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(1).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[10]/input").click();

		String negvalue = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipmentOrderItems_softAssert.assertThat(negvalue).contains(data.get(18).get("Column10"));

		// More than the Order Qty
		WebdriverWait.findElement("id", "0_lookupId_inventoryItemId").sendKeys("142140");

		WebdriverWait.findElement("name", "quantity").sendKeys("1000");
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[10]/input").click();

		String moreQty = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipmentOrderItems_softAssert.assertThat(moreQty).contains(data.get(19).get("Column10"));

		// Spl Charc
		WebdriverWait.findElement("id", "0_lookupId_inventoryItemId").sendKeys("142140");

		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(5).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[10]/input").click();

		String SplCharc = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipmentOrderItems_softAssert.assertThat(SplCharc).contains(data.get(2).get("Column10"));
	}

	public static void InvalidInventoryID_Val(List<HashMap<String, String>> data) {

		// Spl Charc
		WebdriverWait.findElement("name", "quantity").sendKeys("10");

		WebdriverWait.findElement("id", "0_lookupId_inventoryItemId").sendKeys(data.get(5).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[10]/input").click();

		String SplCharc = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipmentOrderItems_softAssert.assertThat(SplCharc).contains(data.get(17).get("Column21"));
	}

	public static void PurchaseShipmentOrderItems_assertions() {
		purchaseShipmentOrderItems_softAssert.assertAll();
	}
}
