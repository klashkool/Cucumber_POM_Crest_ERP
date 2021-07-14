package com.Procurement_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;



public class Procurement_PurchaseReturn extends Base {

	@Rule
	public static JUnitSoftAssertions purchaseReturn_softAssert = new JUnitSoftAssertions();

	public static void PO_Return(List<HashMap<String, String>> data, String sheetname) throws InterruptedException {

		WebdriverWait.findElement("link", "PROCUREMENT").click();
		WebdriverWait.findElement("link", "Purchase Returns").click();
		WebdriverWait.findElement("link", "Create Purchase Return Order").click();

		// Organization DD
		Select OrgDD = new Select(WebdriverWait.findElement("id", "fromPartyId"));
		OrgDD.selectByVisibleText(data.get(0).get("Column3"));

		// PartyID
		WebdriverWait.findElement("id", "0_lookupId_toPartyId").sendKeys(data.get(0).get("Column4"));// Nagarjuna
																					// Fertilizers
		// Facility DD
		Select FacDD = new Select(WebdriverWait.findElement("id", "destinationFacilityId"));
		FacDD.selectByVisibleText(data.get(0).get("Column7"));

		// Currency DD
		Select CurrencyDD = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		CurrencyDD.selectByVisibleText(data.get(0).get("Column6"));

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/div/input").click();

		Procurement_PurchaseReturn_ReturnHeader.Capture_ReturnID(sheetname);
		Procurement_PurchaseReturn_ReturnItems.Update_ReturnItems(data);
		// Procurement_PurchaseReturn_ReturnSummary.PO_ReturnValue_Val(data);
		Procurement_PurchaseReturn_ReturnHeader.Update_ReturnHeader();
	}

	///////////////////////////////// Negative Flow////////////////////////////

	public static void ProcurementLink() {

		WebdriverWait.findElement("link", "PROCUREMENT").click();
	}

	public static void PurchaseReturnLink() {

		WebdriverWait.findElement("link", "Purchase Returns").click();
	}

	public static void CreatePurchaseReturnLink() {

		WebdriverWait.findElement("link", "Create Purchase Return Order").click();
	}

	public static void InvalidDates_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "fromDate_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("id", "thruDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='searchCriteria']/table/tbody[1]/tr[5]/td/input").click();

		String returnDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseReturn_softAssert.assertThat(returnDate).isEqualTo("Return Thru Date must be greater than or equal to Return From Date.");

	}

	public static void MandatoryFields_Val(List<HashMap<String, String>> data) {

		Select docType_dd = new Select(WebdriverWait.findElement("id", "fromPartyId"));
		docType_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("name", "entryDate_i18n").clear();

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/div/input").click();

		String entryDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String fac = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String fromParty = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();
		String toparty = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[4]").getText();

		purchaseReturn_softAssert.assertThat(entryDate).isEqualTo(data.get(31).get("Column2"));
		purchaseReturn_softAssert.assertThat(fac).isEqualTo(data.get(9).get("Column8"));
		purchaseReturn_softAssert.assertThat(fromParty).isEqualTo(data.get(15).get("Column5"));
		purchaseReturn_softAssert.assertThat(toparty).isEqualTo(data.get(16).get("Column5"));

	}

	public static void PurchaseReturnPage_CancelBtn() {

		WebdriverWait.findElement("link", "Cancel").click();

		WebElement createlink = WebdriverWait.findElement("link", "Create Purchase Return Order");
		Assert.assertTrue(createlink.isDisplayed());
	}

	public static void Invalid_ReturnQty(List<HashMap<String, String>> data) {

		// Negative Qty
		WebdriverWait.findElement("name", "returnQuantity_o_0").clear();
		WebdriverWait.findElement("name", "returnQuantity_o_0").sendKeys(data.get(1).get("Column1"));
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("id", "sub1").click();

		String qty_grt0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseReturn_softAssert.assertThat(qty_grt0).isEqualTo(data.get(13).get("Column10"));

		// 0 Value
		WebdriverWait.findElement("name", "returnQuantity_o_0").clear();
		WebdriverWait.findElement("name", "returnQuantity_o_0").sendKeys(data.get(3).get("Column1"));
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("id", "sub1").click();

		String qty_grt = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseReturn_softAssert.assertThat(qty_grt).isEqualTo(data.get(13).get("Column10"));

		// Empty Value
		WebdriverWait.findElement("name", "returnQuantity_o_0").clear();
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("id", "sub1").click();

		String qty_empty = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseReturn_softAssert.assertThat(qty_empty).isEqualTo(data.get(14).get("Column10"));

		// Spl Characters
		WebdriverWait.findElement("name", "returnQuantity_o_0").clear();
		WebdriverWait.findElement("name", "returnQuantity_o_0").sendKeys(data.get(5).get("Column1"));
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("id", "sub1").click();

		String splchar = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseReturn_softAssert.assertThat(splchar).isEqualTo(data.get(15).get("Column10"));

		// More than Received Qty
		WebdriverWait.findElement("name", "returnQuantity_o_0").clear();
		WebdriverWait.findElement("name", "returnQuantity_o_0").sendKeys("10000");
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("id", "sub1").click();

		String moreQty = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseReturn_softAssert.assertThat(moreQty).isEqualTo(data.get(16).get("Column10"));
	}

	public static void Invalid_ReturnPrice(List<HashMap<String, String>> data) {

		// Negative Price
		WebdriverWait.findElement("name", "returnPrice_o_0").clear();
		WebdriverWait.findElement("name", "returnPrice_o_0").sendKeys(data.get(1).get("Column1"));
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("id", "sub1").click();

		String qty_grt0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseReturn_softAssert.assertThat(qty_grt0).isEqualTo(data.get(20).get("Column12"));

		// 0 Price
		WebdriverWait.findElement("name", "returnPrice_o_0").clear();
		WebdriverWait.findElement("name", "returnPrice_o_0").sendKeys(data.get(3).get("Column1"));
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("id", "sub1").click();

		String qty_grt = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseReturn_softAssert.assertThat(qty_grt).isEqualTo(data.get(20).get("Column12"));

		// Empty Price
		WebdriverWait.findElement("name", "returnPrice_o_0").clear();
		WebdriverWait.findElement("name", "returnPrice_o_0").click();
		WebdriverWait.findElement("id", "sub1").click();

		String qty_empty = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseReturn_softAssert.assertThat(qty_empty).isEqualTo(data.get(21).get("Column12"));

		// Spl Characters
		WebdriverWait.findElement("name", "returnPrice_o_0").clear();
		WebdriverWait.findElement("name", "returnPrice_o_0").sendKeys(data.get(5).get("Column1"));
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("id", "sub1").click();

		String splchar = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseReturn_softAssert.assertThat(splchar).isEqualTo(data.get(22).get("Column12"));

	}

	public static void ReturnSelecteditem() {

		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("id", "sub1").click();
	}


	public static void PurchaseReturn_softAssert() {
		purchaseReturn_softAssert.assertAll();
	}
}
