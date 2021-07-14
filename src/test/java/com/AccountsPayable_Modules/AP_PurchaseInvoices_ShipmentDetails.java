package com.AccountsPayable_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;



public class AP_PurchaseInvoices_ShipmentDetails extends Base {

	@Rule
	public static JUnitSoftAssertions PurchaseInvoiceShipDetails_softAssert = new JUnitSoftAssertions();


	public static void EnterShpID(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "shipmentId").sendKeys(data.get(13).get("Column5"));
		WebdriverWait.findElement("xpath", "//*[@id='ui-id-1']").click();
		WebdriverWait.findElement("name", "submitShipment").click();
	}

	public static void ShipmentValue_Validation(List<HashMap<String, String>> data) {

		String shipValue = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/table[3]/tbody[1]/tr[2]/td[2]").getText()
						.replace("₹", "").replace(",", "");
		PurchaseInvoiceShipDetails_softAssert.assertThat(shipValue).isEqualTo(data.get(5).get("Column36"));
	}

	public static void Add_AdditionalCharges(List<HashMap<String, String>> data) {

		Select charge = new Select(WebdriverWait.findElement("id", "chargeId_o_0"));
		charge.selectByVisibleText(data.get(39).get("Column1"));

		Select dept = new Select(WebdriverWait.findElement("id", "deptId_o_0"));
		dept.selectByVisibleText(data.get(0).get("Column8"));

		WebdriverWait.findElement("name", "mainSubmit").click();

		String shipValue = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		Assert.assertEquals("Shipment(s) & Charge(s) added to invoice successfully.", shipValue);

	}



	public static void PurchaseInvoiceShipDetails_softAssert() {
		PurchaseInvoiceShipDetails_softAssert.assertAll();
	}

}
