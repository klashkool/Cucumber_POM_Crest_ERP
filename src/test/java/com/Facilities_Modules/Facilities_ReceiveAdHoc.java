package com.Facilities_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import com.Procurement_Modules.Procurement_PurchaseOrder;
import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Facilities_ReceiveAdHoc extends Base {

	@Rule
	public static JUnitSoftAssertions ReceiveAdhoc_softAssert = new JUnitSoftAssertions();

	public static Logger log = Logger.getLogger(Facilities_Shipments.class);

	// Scenario : Receive Adhoc Products

	public static void PO_Adhoc_Recv_Shpmt(List<HashMap<String, String>> data, String col) {

		WebdriverWait.findElement("link", "FACILITIES").click();
		WebdriverWait.findElement("link", "Stock Management").click();
		WebdriverWait.findElement("link", "Receive Ad Hoc").click();
		WebdriverWait.findElement("id", "0_lookupId_purchaseOrderId").sendKeys(data.get(13).get("Column4"));
		WebdriverWait.findElement("xpath", "//*[@id='ui-id-1']/li/a").click();
		WebdriverWait.findElement("id", "0_lookupId_purchaseOrderId_lookupDescription").click();
		WebdriverWait.findElement("id", "send").click();

		WebdriverWait.findElement("name", "selectAll").click();

		List<WebElement> adhocqty_List = WebdriverWait.findElements("xpath", "//*[@id='selectAllForm']/div/table[2]/tbody/tr");
		System.out.println(adhocqty_List.size());
		for (int a = 0; a <= adhocqty_List.size() - 2; a++) {

			WebdriverWait.findElement("name", "remainingQty_o_" + (a)).clear();
			WebdriverWait.findElement("name", "remainingQty_o_" + (a)).sendKeys(data.get(a).get(col));
			WebdriverWait.findElement("name", "_rowSubmit_o_" + (a)).click();
		}

		WebdriverWait.findElement("link", "Receive").click();

		Procurement_PurchaseOrder.Search_PO(data);

	}

	///////////////////////////// Negative Flow///////////////////////////

	public static void FacilitiesLink() {

		WebdriverWait.findElement("link", "FACILITIES").click();
	}

	public static void StockManagementLink() {

		WebdriverWait.findElement("link", "Stock Management").click();
	}

	public static void ReceiveAdhocLink() {

		WebdriverWait.findElement("link", "Receive Ad Hoc").click();
	}

	public static void InvalidOrderID_Val(List<HashMap<String, String>> data) {

		// Invalid ID (Spl Characters)
		WebdriverWait.findElement("id", "0_lookupId_purchaseOrderId").sendKeys(data.get(5).get("Column1"));
		WebdriverWait.findElement("id", "send").click();

		String invalidID = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		ReceiveAdhoc_softAssert.assertThat(invalidID).contains(data.get(6).get("Column3"));

		// Inventory OrderID
		WebdriverWait.findElement("id", "0_lookupId_purchaseOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_purchaseOrderId").sendKeys("PO1923");
		WebdriverWait.findElement("id", "send").click();

		String inventoryID = WebdriverWait.findElement("xpath", "//*[@id='selectAllForm']/div").getText();
		ReceiveAdhoc_softAssert.assertThat(inventoryID).contains("There are no items in the PO to receive.");

		// Empty ID
		// WebdriverWait.findElement("id",
		// "0_lookupId_purchaseOrderId").clear();
		// WebdriverWait.findElement("link", "Get Details").click();
		//
		// String emptyID = WebdriverWait.findElement("xpath", "//*[@id=
		// 'content-messages']/div/ul/li[1]").getText();
		// ReceiveAdhoc_softAssert.assertThat(emptyID).contains(data.get(17).get("Column21"));
	}

	public static void ReceiveAdhoc_WithoutSelectingLineItems(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "0_lookupId_purchaseOrderId").sendKeys(data.get(13).get("Column4"));
		WebdriverWait.findElement("xpath", "//*[@id='ui-id-1']/li/a").click();
		WebdriverWait.findElement("id", "0_lookupId_purchaseOrderId_lookupDescription").click();
		WebdriverWait.findElement("id", "send").click();

		WebdriverWait.findElement("name", "selectAll").click();
		WebdriverWait.findElement("link", "Receive").click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

		// String alertmsg = alert.getText();
		// ReceiveAdhoc_softAssert.assertThat(alertmsg).contains(data.get(4).get("Column16"));
		// alert.accept();
	}

	public static void InvalidQty_Val(List<HashMap<String, String>> data) throws InterruptedException {

		// Empty Qty Val
		Thread.sleep(1000);
		WebdriverWait.findElement("name", "remainingQty_o_0").clear();
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		Thread.sleep(1000);
		WebdriverWait.findElement("link", "Receive").click();

		String emptyQty = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		ReceiveAdhoc_softAssert.assertThat(emptyQty).isEqualTo((data.get(20).get("Column10")));

		// Negative Qty
		WebdriverWait.findElement("name", "remainingQty_o_0").clear();
		WebdriverWait.findElement("name", "remainingQty_o_0").sendKeys((data.get(1).get("Column1")));
		WebdriverWait.findElement("name", "selectAll").click();
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		Thread.sleep(1000);
		WebdriverWait.findElement("link", "Receive").click();

		String negQty = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		ReceiveAdhoc_softAssert.assertThat(negQty).isEqualTo((data.get(21).get("Column10")));

		// Zero Qty
		WebdriverWait.findElement("name", "remainingQty_o_0").clear();
		WebdriverWait.findElement("name", "selectAll").click();
		WebdriverWait.findElement("name", "remainingQty_o_0").sendKeys((data.get(3).get("Column1")));
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		Thread.sleep(1000);
		WebdriverWait.findElement("link", "Receive").click();

		String ZeroQty = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		ReceiveAdhoc_softAssert.assertThat(ZeroQty).isEqualTo((data.get(21).get("Column10")));

		// SplCharcters
		WebdriverWait.findElement("name", "remainingQty_o_0").clear();
		WebdriverWait.findElement("name", "selectAll").click();
		WebdriverWait.findElement("name", "remainingQty_o_0").sendKeys((data.get(5).get("Column1")));
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		Thread.sleep(1000);
		WebdriverWait.findElement("link", "Receive").click();

		String splCharc = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		ReceiveAdhoc_softAssert.assertThat(splCharc).isEqualTo((data.get(22).get("Column10")));

		// More Qty
		WebdriverWait.findElement("name", "remainingQty_o_0").clear();
		WebdriverWait.findElement("name", "selectAll").click();
		WebdriverWait.findElement("name", "remainingQty_o_0").sendKeys("10000");
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		Thread.sleep(1000);
		WebdriverWait.findElement("link", "Receive").click();

		String moreQty = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		ReceiveAdhoc_softAssert.assertThat(moreQty).isEqualTo((data.get(23).get("Column10")));
	}

	public static void Cancel_ReceiveAdhoc() {

		WebdriverWait.findElement("link", "Cancel").click();

		// WebElement link = WebdriverWait.findElement("link", "Get Details");
		// ReceiveAdhoc_softAssert.assertThat(link.isDisplayed());
	}

	public static void ReceiveAdhoc_softAssert_assertions() {
		ReceiveAdhoc_softAssert.assertAll();
	}

}
