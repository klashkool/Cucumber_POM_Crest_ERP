package com.Sales_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Utils.WebdriverWait;

public class Sales_ProformaInvoice {

	@Rule
	public static JUnitSoftAssertions Sales_ProformaInvoice_softAssert = new JUnitSoftAssertions();

	///////////////////////////////////////////////Negative Scenario///////////////////////////////////////

	public static void Navigate_To_Create_ProformaInvoice() {
		WebdriverWait.findElement("link", "SALES").click();
		WebdriverWait.findElement("link", "Proforma Invoice").click();
		WebdriverWait.findElement("link", "Create Proforma Invoice").click();
	}

	public static void createProformaInvoice(List<HashMap<String, String>> data_Positive) {
		WebElement custDD = WebdriverWait.findElement("id", "0_lookupId_partyId");
		custDD.clear();
		custDD.sendKeys(data_Positive.get(0).get("Column4"));
		click_Create_btn();
	}

	public static void Navigate_To_ProformaInvoice() {
		WebdriverWait.findElement("link", "SALES").click();
		WebdriverWait.findElement("link", "Proforma Invoice").click();
	}

	public static void Click_ProformaInvoiceHeaderLink() {
		WebdriverWait.findElement("link", "Proforma Invoice Header").click();
	}

	public static void Click_ProformaInvoiceItemsLink() {
		WebdriverWait.findElement("link", "Proforma Invoice Items").click();
	}

	public static void Click_ProformaInvoiceSummaryLink() {
		WebdriverWait.findElement("link", "Proforma Invoice Summary").click();
	}

	public static void click_Create_btn() {
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/div/input").click();
	}

	public static void Validating_ProformaInvoice_date_range_with_invalid_date(List<HashMap<String, String>> data) {
		//Proforma Invoice Date - From
		 WebdriverWait.findElement("id", "fromDate_i18n").sendKeys(data.get(8).get("Column1"));
		//Proforma Invoice Date - Thru
		 WebdriverWait.findElement("id", "thruDate_i18n").sendKeys(data.get(7).get("Column1"));
		 WebdriverWait.findElement("xpath", "//*[@value='Search']").click();
		 String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		 Sales_ProformaInvoice_softAssert.assertThat(validationMsg1).isEqualTo(data.get(20).get("Column2"));
	}

	public static void Validating_without_Mandatory_details(List<HashMap<String, String>> data) {
		Select orgDD = new Select(WebdriverWait.findElement("id", "partyIdFrom"));
		orgDD.selectByVisibleText("-Select-");
		WebdriverWait.findElement("id", "proformaInvoiceDate_i18n").clear();
		click_Create_btn();
		String path1 = "//*[@id='content-messages']/div/ul/li[";
		String path2 = "]";
		for(int i=1; i<=4; i++) {
			String all_ValidationMsg = WebdriverWait.findElement("xpath", path1+i+path2).getText();
			if(i==1) {
				Sales_ProformaInvoice_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(3).get("Column4"));
			}
			else if (i == 2) {
				Sales_ProformaInvoice_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(6).get("Column5"));
			}else if (i == 3) {
				Sales_ProformaInvoice_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(15).get("Column2"));
			} else if (i == 4) {
				Sales_ProformaInvoice_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(7).get("Column6"));
			}
		}
	}

	public static void Validate_PartyID_with_InvalidData(List<HashMap<String, String>> data) {

		for(int i=0; i<=5; i++) {
			WebdriverWait.findElement("id", "0_lookupId_partyId").sendKeys(data.get(i).get("Column1"));
			click_Create_btn();
			String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
			Sales_ProformaInvoice_softAssert.assertThat(validationMsg1).isEqualTo(data.get(7).get("Column5"));
			// String validationMsg2 = WebdriverWait.findElement("xpath",
			// "//*[@id='content-messages']/div/ul/li[2]").getText();
			// Sales_ProformaInvoice_softAssert.assertThat(validationMsg2).isEqualTo(data.get(0).get("Column7"));
			WebdriverWait.findElement("id", "0_lookupId_partyId").clear();
		}
	}

	public static void Validate_ProformaInvoiceDate_with_InvalidData(List<HashMap<String, String>> data_positive, List<HashMap<String, String>> data) {
		WebdriverWait.findElement("id", "0_lookupId_partyId").sendKeys(data_positive.get(0).get("Column4"));
		for(int i=0; i<=5; i++) {
		WebdriverWait.findElement("id", "proformaInvoiceDate_i18n").sendKeys(data.get(i).get("Column1"));
		click_Create_btn();
		String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ProformaInvoice_softAssert.assertThat(validationMsg1).isEqualTo(data.get(15).get("Column2"));
		}
	}

	public static void Validate_ProformaInvoice_description_with_255_Characters(List<HashMap<String, String>> data_positive, List<HashMap<String, String>> data) {
		WebdriverWait.findElement("id", "0_lookupId_partyId").clear();
		WebdriverWait.findElement("id", "0_lookupId_partyId").sendKeys(data_positive.get(0).get("Column4"));
		WebElement description = WebdriverWait.findElement("id", "description");
		description.sendKeys(data.get(6).get("Column1"));
		click_Create_btn();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ProformaInvoice_softAssert.assertThat(validationMsg).isEqualTo("The maximum limit for Description is 255 characters");
	}

	public static void Validate_Cancel_button_navigating_to_SearchPage() {
		Sales_ProformaInvoice.Navigate_To_Create_ProformaInvoice();
		WebdriverWait.findElement("link", "Cancel").click();
		boolean verifySearchPageIsDisplayed = WebdriverWait.findElement("link", "Create Proforma Invoice").isDisplayed();
		Sales_ProformaInvoice_softAssert.assertThat(verifySearchPageIsDisplayed).isEqualTo(true);
	}

	public static void Validate_Update_ProformaInvoice_description_with_255_Characters(List<HashMap<String, String>> data_positive, List<HashMap<String, String>> data) {
		WebElement description = WebdriverWait.findElement("id", "description");
		description.sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Update']").click();
		try {
			if(WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").isDisplayed()) {
				String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				Sales_ProformaInvoice_softAssert.assertThat(validationMsg).isEqualTo("The maximum limit for Description is 255 characters");
			}else if(WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").isDisplayed()) {
				String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
				if(validationMsg.equals(data.get(17).get("Column17"))) {
					Sales_ProformaInvoice_softAssert.fail("Appln is accepting 255 characters and giving success message");
				}
			}
		}catch(Exception e) {
			Sales_ProformaInvoice_softAssert.fail("Appln is accepting 255 characters and giving success message");
		}


	}

	public static void Validate_ProformaInvoiceItems_Without_OrderID(List<HashMap<String, String>> data) {
		boolean isValidationMsgDisplayed = WebdriverWait.findElement("xpath", "//*[@value='Load Items']").isEnabled();
		Sales_ProformaInvoice_softAssert.assertThat(isValidationMsgDisplayed).isEqualTo(false);
	}

	public static void Validate_Cancel_ProformaInvoice() {
		WebdriverWait.findElement("link", "Cancel").click();
		String getStatus = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr[1]/td[4]").getText();
		Sales_ProformaInvoice_softAssert.assertThat(getStatus).isEqualTo("Cancelled");
	}

	public static void Validate_OrderID_with_InvalidData(List<HashMap<String, String>> data) {
			WebElement orderID = WebdriverWait.findElement("name", "orderId");
			orderID.sendKeys(data.get(9).get("Column1"));
			WebdriverWait.findElement("xpath", "//*[@value='Load Items']").click();
			String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
			Sales_ProformaInvoice_softAssert.assertThat(validationMsg).isEqualTo(data.get(11).get("Column21"));
			orderID.clear();
	}

	public static void Validate_OrderID_with_SO_other_than_Approved_Status(List<HashMap<String, String>> data) {
		WebElement orderID = WebdriverWait.findElement("name", "orderId");
		for(int i=10; i<=12; i++) {
		orderID.sendKeys(data.get(i).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Load Items']").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		Sales_ProformaInvoice_softAssert.assertThat(validationMsg).isEqualTo(data.get(12).get("Column21"));
		orderID.clear();
		}
	}

	public static void Validate_OrderID_with_SO_Approved_Status(List<HashMap<String, String>> data) {
		WebElement orderID = WebdriverWait.findElement("name", "orderId");
		orderID.sendKeys(data.get(13).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Load Items']").click();
		boolean verifyItemsIsDisplayed = WebdriverWait.findElement("xpath", "//*[@id='returnItemId_tableRow_0']/td[1]").isDisplayed();
		Sales_ProformaInvoice_softAssert.assertThat(verifyItemsIsDisplayed).isEqualTo(true);
		orderID.clear();
	}

	public static void Validate_AddSelectedItems_btn_isDisabled_when_no_line_item_selected(List<HashMap<String, String>> data) {
		WebElement orderID = WebdriverWait.findElement("name", "orderId");
		orderID.sendKeys(data.get(13).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Load Items']").click();
		boolean verifyItemsIsDisplayed = WebdriverWait.findElement("xpath", "//*[@value='Add Selected Items']").isEnabled();
		Sales_ProformaInvoice_softAssert.assertThat(verifyItemsIsDisplayed).isEqualTo(false);
	}

	public static void Validate_AddSelectedItems_btn_isEnabled_when_line_item_selected(List<HashMap<String, String>> data) {
		WebElement orderID = WebdriverWait.findElement("name", "orderId");
		orderID.sendKeys(data.get(13).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Load Items']").click();
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		boolean verifyItemsIsDisplayed = WebdriverWait.findElement("xpath", "//*[@value='Add Selected Items']").isEnabled();
		Sales_ProformaInvoice_softAssert.assertThat(verifyItemsIsDisplayed).isEqualTo(true);
	}

	public static void Validate_AddSelectedItems_with_0_qty(List<HashMap<String, String>> data) {
		WebElement orderID = WebdriverWait.findElement("name", "orderId");
		orderID.sendKeys(data.get(13).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Load Items']").click();
		WebdriverWait.findElement("name", "invoicableQuantity_o_0").sendKeys("0");
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("xpath", "//*[@value='Add Selected Items']").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		Sales_ProformaInvoice_softAssert.assertThat(validationMsg).isEqualTo(data.get(16).get("Column10"));
	}

	public static void Validate_AddSelectedItems_with_empty_qty(List<HashMap<String, String>> data) {
		WebElement orderID = WebdriverWait.findElement("name", "orderId");
		orderID.sendKeys(data.get(13).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Load Items']").click();
		WebdriverWait.findElement("name", "invoicableQuantity_o_0").clear();
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("xpath", "//*[@value='Add Selected Items']").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		Sales_ProformaInvoice_softAssert.assertThat(validationMsg).isEqualTo(data.get(14).get("Column10"));
	}

	public static void Validate_AddSelectedItems_with_ProformaQty_greaterThan_OrderQty(List<HashMap<String, String>> data) {
		WebElement orderID = WebdriverWait.findElement("name", "orderId");
		orderID.sendKeys(data.get(13).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Load Items']").click();
		WebElement Qty = WebdriverWait.findElement("name", "invoicableQuantity_o_0");
		Qty.clear();
		Qty.sendKeys(data.get(14).get("Column1"));
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("xpath", "//*[@value='Add Selected Items']").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		Sales_ProformaInvoice_softAssert.assertThat(validationMsg).isEqualTo(data.get(8).get("Column10"));
	}

	public static void Validate_AddSelectedItems_with_SPL_characters_in_ProformaQty(List<HashMap<String, String>> data) {
		WebElement orderID = WebdriverWait.findElement("name", "orderId");
		orderID.sendKeys(data.get(13).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Load Items']").click();
		WebElement Qty = WebdriverWait.findElement("name", "invoicableQuantity_o_0");
		Qty.clear();
		Qty.sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("xpath", "//*[@value='Add Selected Items']").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		Sales_ProformaInvoice_softAssert.assertThat(validationMsg).isEqualTo(data.get(15).get("Column10"));
	}

	public static void Validate_by_Removing_line_Item(List<HashMap<String, String>> data) {
		WebElement orderID = WebdriverWait.findElement("name", "orderId");
		orderID.sendKeys(data.get(13).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Load Items']").click();
		WebElement Qty = WebdriverWait.findElement("name", "invoicableQuantity_o_0");
		Qty.clear();
		Qty.sendKeys("1");
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("xpath", "//*[@value='Add Selected Items']").click();
		WebdriverWait.findElement("link", "Remove").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		Sales_ProformaInvoice_softAssert.assertThat(validationMsg).contains(data.get(16).get("Column17"));
	}

	public static void ProformaInvoice_assertions() {
		Sales_ProformaInvoice_softAssert.assertAll();
	}
}
