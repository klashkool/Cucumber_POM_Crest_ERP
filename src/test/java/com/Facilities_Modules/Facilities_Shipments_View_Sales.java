package com.Facilities_Modules;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Facilities_Shipments_View_Sales extends Base {

	@Rule
	public static JUnitSoftAssertions SalesShipmentView_softAssert = new JUnitSoftAssertions();

///////////////////////////////////Sales Shipment negative flow//////////////////////////////////////////////
	
	public static void ClickSOLinkFromSalesShipmentViewScreen() {
		WebdriverWait.findElement("xpath", "//*[@id='primaryOrderIdRowAndPrimaryShipGroupSeqId']/td[2]/a").click();
	}

	public static void SalesShipmentView_assertions() {
		SalesShipmentView_softAssert.assertAll();
	}
}
