package com.Facilities_Modules;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.WebElement;

import com.Sales_Modules.Sales_SalesOrder;
import com.Utils.Base;
import com.Utils.WebdriverWait;
public class Facilities_Shipments_OrderItems_Sales extends Base{
	@Rule
	public static JUnitSoftAssertions SalesShipmentOrderItems_softAssert = new JUnitSoftAssertions();

	public static Logger log = Logger.getLogger(Sales_SalesOrder.class);

	//////////////////////////////////////////////Negative Flows///////////////////////////////////////////////
	public static void SalesShipment_Issue_Partial_Items_NegFlow(List<HashMap<String, String>> data_Positive) {
		WebdriverWait.findElement("link", "Order Items").click();
		WebElement lineItem1Qty = WebdriverWait.findElement("name", "quantity_o_0");
		lineItem1Qty.clear();
		lineItem1Qty.sendKeys(data_Positive.get(0).get("Column28"));
		WebElement lineItem2Qty = WebdriverWait.findElement("name", "quantity_o_1");
		lineItem2Qty.clear();
		lineItem2Qty.sendKeys(data_Positive.get(1).get("Column28"));
		WebdriverWait.findElement("name", "selectAll").click();
		WebdriverWait.findElement("id", "submitButton").click();
	}
	
	public static void SalesShipmentOrderItems_assertions() {
		SalesShipmentOrderItems_softAssert.assertAll();
	}
}
