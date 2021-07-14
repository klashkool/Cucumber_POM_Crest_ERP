package com.Facilities_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.support.ui.Select;

import com.Sales_Modules.Sales_SalesOrder;
import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Facilities_Shipments_Edit extends Base {

	@Rule
	public static JUnitSoftAssertions ShipmentEdit_softAssert = new JUnitSoftAssertions();

	public static Logger log = Logger.getLogger(Sales_SalesOrder.class);

	public static void ShpStatus() throws InterruptedException {

		WebdriverWait.findElement("link", "Edit").click();

		Select status_dd1 = new Select(WebdriverWait.findElement("id", "statusId"));
		status_dd1.selectByVisibleText("Picked");
		Thread.sleep(1000);
		WebdriverWait.findElement("id", "submitForm").click();
		Thread.sleep(1000);

		Select status_dd2 = new Select(WebdriverWait.findElement("id", "statusId"));
		status_dd2.selectByVisibleText("Packed");
		Thread.sleep(1000);
		WebdriverWait.findElement("id", "submitForm").click();
		Thread.sleep(1000);

		Select status_dd3 = new Select(WebdriverWait.findElement("id", "statusId"));
		status_dd3.selectByVisibleText("Shipped");
		Thread.sleep(1000);
		WebdriverWait.findElement("id", "submitForm").click();
		Thread.sleep(1000);

		Facilities_Shipments_View.Shpmnt_Status_Val();

		WebdriverWait.findElement("xpath", "//*[@id='primaryOrderIdRowAndPrimaryShipGroupSeqId']/td[2]/a").click();
		log.info("All Qty Shipped successfully");
	}

	public static void PR_ShpStatus() throws InterruptedException {

		WebdriverWait.findElement("link", "Edit").click();

		Select status_dd1 = new Select(WebdriverWait.findElement("id", "statusId"));
		status_dd1.selectByVisibleText("Picked");
		Thread.sleep(1000);
		WebdriverWait.findElement("id", "submitForm").click();
		Thread.sleep(1000);

		Select status_dd2 = new Select(WebdriverWait.findElement("id", "statusId"));
		status_dd2.selectByVisibleText("Packed");
		Thread.sleep(1000);
		WebdriverWait.findElement("id", "submitForm").click();
		Thread.sleep(1000);

		Select status_dd3 = new Select(WebdriverWait.findElement("id", "statusId"));
		status_dd3.selectByVisibleText("Shipped");
		Thread.sleep(1000);
		WebdriverWait.findElement("id", "submitForm").click();
		Thread.sleep(1000);

		Facilities_Shipments_View.Shpmnt_Status_Val();

		WebdriverWait.findElement("xpath", "//*[@id='primaryReturnIdRow']/td[2]/a").click();
		log.info("All Qty Shipped successfully");
	}

	public static void Capture_ReturnShipID(String sheetname) {

		String shipdID = WebdriverWait.findElement("id", "shipmentIdDisplay").getText();
		ExcelWriter.writeExcelFile(sheetname, 15, 5, shipdID);
	}

	//////////////////// Purchase Shipment Negative
	//////////////////// Flow/////////////////////////////////


	public static void StatusVal_WithoutaddingLineitems(List<HashMap<String, String>> data) {

		// Status in Select
		Select status_dd = new Select(WebdriverWait.findElement("id", "statusId"));
		status_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("id", "submitForm").click();

		String select = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		ShipmentEdit_softAssert.assertThat(select).isEqualTo(data.get(17).get("Column21"));

		// Input Status
		Select status_dd1 = new Select(WebdriverWait.findElement("id", "statusId"));
		status_dd1.selectByVisibleText("Input");

		WebdriverWait.findElement("id", "submitForm").click();

		String input = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		ShipmentEdit_softAssert.assertThat(input).isEqualTo(data.get(14).get("Column17"));

		// Picked Status
		Select status_dd2 = new Select(WebdriverWait.findElement("id", "statusId"));
		status_dd2.selectByVisibleText("Picked");

		WebdriverWait.findElement("id", "submitForm").click();

		String picked = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		ShipmentEdit_softAssert.assertThat(picked).isEqualTo(data.get(15).get("Column17"));

		// Packed Status
		Select status_dd3 = new Select(WebdriverWait.findElement("id", "statusId"));
		status_dd3.selectByVisibleText("Packed");

		WebdriverWait.findElement("id", "submitForm").click();

		String packed = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		ShipmentEdit_softAssert.assertThat(packed).isEqualTo(data.get(16).get("Column17"));

		// Shipped Status
		Select status_dd4 = new Select(WebdriverWait.findElement("id", "statusId"));
		status_dd4.selectByVisibleText("Shipped");

		WebdriverWait.findElement("id", "submitForm").click();

		String shipped = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		ShipmentEdit_softAssert.assertThat(shipped).isEqualTo(data.get(16).get("Column17"));

	}

	public static void ShipmentEdit_assertions() {
		ShipmentEdit_softAssert.assertAll();
	}
}
