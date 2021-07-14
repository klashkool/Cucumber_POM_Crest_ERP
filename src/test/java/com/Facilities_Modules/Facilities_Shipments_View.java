package com.Facilities_Modules;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;

import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Facilities_Shipments_View extends Base {

	@Rule
	public static JUnitSoftAssertions SalesShipmentView_softAssert = new JUnitSoftAssertions();

	public static void Shpmnt_Status_Val() {

		String shipStatus = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//tr//td[4]").getText();
		Assert.assertEquals("Shipped", shipStatus);
	}

	public static void Capture_ShpID(String sheetname, int row) {

		WebdriverWait.findElement("link", "View").click();

		String shipID = WebdriverWait
						.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[1]/td[2]")
				.getText();
		ExcelWriter.writeExcelFile(sheetname, row, 5, shipID);

	}

	public static void PO_Shpmnt_Status_Val() {

		String shipStatus = WebdriverWait
						.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[4]")
				.getText();
		Assert.assertEquals("Received", shipStatus);

	}

	///////////////////////////////////Sales Shipment negative flow//////////////////////////////////////////////



	public static void SalesShipmentView_assertions() {
		SalesShipmentView_softAssert.assertAll();
	}
}
