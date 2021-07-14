package com.Facilities_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.WebElement;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Facilities_Shipments_ShipmentPlan extends Base {

	@Rule
	public static JUnitSoftAssertions SalesShipmentPlan_softAssert = new JUnitSoftAssertions();

	public static void Add_ShipmentPlan(List<HashMap<String, String>> data, String plndQty_colnum) throws InterruptedException {

		WebdriverWait.findElement("link", "Shipment Plan").click();
		WebdriverWait.findElement("xpath", "//*[@id='FindOrderItems']/table/tbody/tr[3]/td/input").click();

		List<WebElement> Shpqty_List = WebdriverWait.findElements("xpath", "//*[@id='AddOrderItems']/table/tbody/tr");
		System.out.println(Shpqty_List.size());
		for (int a = 0; a <= Shpqty_List.size() - 2; a++) {

			WebdriverWait.findElement("name", "quantity_o_" + (a)).clear();
			WebdriverWait.findElement("name", "quantity_o_" + (a)).sendKeys(data.get(a).get(plndQty_colnum));
		}
		WebdriverWait.findElement("xpath", "//*[@id='AddOrderItems']/div/input").click();
	}

	public static void ShipmentQty_Val(List<HashMap<String, String>> data, String prdID, String ordQty_colnum, String plndQty_colnum) {

		List<WebElement> Shpqty_List = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr");
		System.out.println("Shipmment plan table size" + Shpqty_List.size());
		int j = 0;
		for (int a = 2; a <= Shpqty_List.size(); a++) {
			String PrdId = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr[" + a + "]/td[4]").getText();

			System.out.println(PrdId);

			if (PrdId.equals(data.get(j).get(prdID))) {

				String totalOrdQty = WebdriverWait.findElement("xpath", "//*[@id='selectAllForm']/div[3]/table/tbody/tr[" + a + "]/td[8]")
								.getText();
				String totalPlannedQty = WebdriverWait.findElement("xpath", "//*[@id='selectAllForm']/div[3]/table/tbody/tr[" + a + "]/td[9]")
								.getText();
				Assert.assertEquals(data.get(j).get(ordQty_colnum), totalOrdQty);
				Assert.assertEquals(data.get(j).get(plndQty_colnum), totalPlannedQty);
				j++;
			}
		}

}

	//////////////////////////////////// Sales Shipment Negative Flow ///////////////////////////////////////

	public static void AddShipmentPlan_NegFlow() {
		WebdriverWait.findElement("link", "Shipment Plan").click();
		WebdriverWait.findElement("xpath", "//*[@value='Submit']").click();
		WebdriverWait.findElement("xpath", "//*[@value='Add']").click();
	}

	public static void SalesShipmentPlan_assertions() {
		SalesShipmentPlan_softAssert.assertAll();
	}
}