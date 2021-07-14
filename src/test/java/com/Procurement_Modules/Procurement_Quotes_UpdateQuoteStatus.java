package com.Procurement_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Procurement_Quotes_UpdateQuoteStatus extends Base {


	public static JUnitSoftAssertions PO_UpdateQuoteStatus_softAssert = new JUnitSoftAssertions();

	public static void UpdateQuoteStatus() {

		WebdriverWait.findElement("link", "Update Quote Status").click();

		List<WebElement> lineItems = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr");

		for (int a = 1; a <= lineItems.size(); a++) {

			Select status_dd = new Select(WebdriverWait.findElement("xpath", "//*[starts-with(@id, 'statusId_')]"));
			status_dd.selectByVisibleText("Fully Accepted");

			WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[" + a + "]/td[13]/form/a").click();

		}

		WebdriverWait.findElement("link", "Create Order").click();
	}

	public static void Select_OrderType() {

		Select ordType = new Select(driver.findElement(By.id("orderType")));
		ordType.selectByVisibleText("Domestic");
	}

	////////////////////////////////////////// Negative
	////////////////////////////////////////// Flow////////////////////////////////////////

	public static void UpdateQuoteStatus_Mandatory_Fields_OuoteItemListTable(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='createQuoteItem']/table/tbody[1]/tr[6]/td/input").click();

		String prdID = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String quoteUnitPrice = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String qty = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();
		String fac = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[4]").getText();
		String dept = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[5]").getText();

		PO_UpdateQuoteStatus_softAssert.assertThat(prdID).isEqualTo(data.get(4).get("Column3"));
		PO_UpdateQuoteStatus_softAssert.assertThat(quoteUnitPrice).isEqualTo(data.get(1).get("Column12"));
		PO_UpdateQuoteStatus_softAssert.assertThat(qty).isEqualTo(data.get(0).get("Column10"));
		PO_UpdateQuoteStatus_softAssert.assertThat(fac).isEqualTo(data.get(0).get("Column8"));
		PO_UpdateQuoteStatus_softAssert.assertThat(dept).isEqualTo(data.get(0).get("Column9"));
	}

	public static void UpdateQuoteStatus_InvalidProductID_OuoteItemListTable(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		WebdriverWait.findElement("name", "productId").sendKeys(Inv_data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(0).get("Column9"));

		Select reqUom_dd = new Select(WebdriverWait.findElement("id", "uomId"));
		reqUom_dd.selectByVisibleText(data.get(0).get("Column10"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data.get(0).get("Column8"));

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='facilityId']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(0).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("xpath", "//*[@id='createQuoteItem']/table/tbody[1]/tr[6]/td/input").click();

		String prdIDMsg = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_UpdateQuoteStatus_softAssert.assertThat(prdIDMsg).isEqualTo(Inv_data.get(1).get("Column3"));

		WebdriverWait.findElement("name", "productId").clear();
		WebdriverWait.findElement("name", "quantity").clear();

	}

	public static void UpdateQuoteStatus_InvalidQty_OuoteItemListTable(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		WebdriverWait.findElement("name", "productId").sendKeys(data.get(0).get("Column2"));

		// Negative Value Qty
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(1).get("Column1"));

		Select reqUom_dd = new Select(WebdriverWait.findElement("id", "uomId"));
		reqUom_dd.selectByVisibleText(data.get(0).get("Column10"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data.get(0).get("Column8"));

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='facilityId']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(0).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("xpath", "//*[@id='createQuoteItem']/table/tbody[1]/tr[6]/td/input").click();

		String qty_grt0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String qty_num = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();

		PO_UpdateQuoteStatus_softAssert.assertThat(qty_num).isEqualTo(Inv_data.get(2).get("Column10"));
		PO_UpdateQuoteStatus_softAssert.assertThat(qty_grt0).isEqualTo(Inv_data.get(1).get("Column10"));

		// 0 Value Qty
		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(3).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='createQuoteItem']/table/tbody[1]/tr[6]/td/input").click();

		String qty_grt_0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_UpdateQuoteStatus_softAssert.assertThat(qty_grt_0).isEqualTo(Inv_data.get(1).get("Column10"));

		// Qty with special characters.
		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(0).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='createQuoteItem']/table/tbody[1]/tr[6]/td/input").click();

		String qty_num1 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_UpdateQuoteStatus_softAssert.assertThat(qty_num1).isEqualTo(Inv_data.get(2).get("Column10"));

		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(4).get("Column1"));

	}

	public static void UpdateQuoteStatus_InvalidDates_OuoteItemListTable(List<HashMap<String, String>> data) {

		// Invalid Start Date
		WebdriverWait.findElement("id", "reservStart_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("id", "givenDate_i18n").sendKeys(data.get(7).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='createQuoteItem']/table/tbody[1]/tr[6]/td/input").click();

		String requiredByDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();

		PO_UpdateQuoteStatus_softAssert.assertThat(requiredByDate).isEqualTo(data.get(4).get("Column2"));
		WebdriverWait.findElement("id", "reservStart_i18n").clear();
		WebdriverWait.findElement("id", "reservStart_i18n").clear();

	}

	public static void UpdateQuoteStatus_AddingComments_255Char(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "productId").sendKeys(data.get(10).get("Column1"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(4).get("Column1"));

		WebdriverWait.findElement("id", "comments").sendKeys(data.get(6).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='createQuoteItem']/table/tbody[1]/tr[6]/td/input").click();

		// String commentsName = WebdriverWait.findElement("xpath",
		// "//*[@id='comments_0']").getAttribute("value");
		// String commentsName = WebdriverWait.findElement("xpath",
		// "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[1]/td[12]/text()").getText();
		// commentsName.length();
		//
		// PO_UpdateQuoteStatus_softAssert.assertThat(commentsName.length()).isEqualTo(255);

	}

	public static void UpdateQuoteStatus_Addinglineitems(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		WebdriverWait.findElement("name", "productId").clear();
		WebdriverWait.findElement("name", "quantity").clear();

		for (int a = 0; a <= 4; a++) {
			WebdriverWait.findElement("name", "productId").sendKeys(data.get(a).get("Column2"));
			WebdriverWait.findElement("name", "quantity").sendKeys(data.get(a).get("Column9"));
			WebdriverWait.findElement("id", "reservStart_i18n").sendKeys(Inv_data.get(7).get("Column1"));
			WebdriverWait.findElement("id", "givenDate_i18n").sendKeys(Inv_data.get(8).get("Column1"));

			Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
			dept_dd.selectByVisibleText(data.get(0).get("Column8"));

			List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='facilityId']//option");
			for (WebElement option : options) {
				if (option.getText().contains(data.get(0).get("Column7"))) {
					option.click();
					break;
				}
			}

			WebdriverWait.findElement("id", "comments").sendKeys(Inv_data.get(6).get("Column1"));

			WebdriverWait.findElement("xpath", "//*[@id='createQuoteItem']/table/tbody[1]/tr[6]/td/input").click();
		}
	}

	public static void UpdateQuoteStatus_Editlineitems_InvalidQty(List<HashMap<String, String>> data) {

		// Validating with Empty value
		WebdriverWait.findElement("id", "quantity_0").clear();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[1]/td[13]/form/a").click();

		String qty_req = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_UpdateQuoteStatus_softAssert.assertThat(qty_req).isEqualTo(data.get(0).get("Column10"));

		// Negative Qty
		WebdriverWait.findElement("id", "quantity_0").clear();
		WebdriverWait.findElement("id", "quantity_0").sendKeys(data.get(1).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[1]/td[13]/form/a").click();

		String qty_num = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_UpdateQuoteStatus_softAssert.assertThat(qty_num).isEqualTo(data.get(2).get("Column10"));

		// Special Characters
		WebdriverWait.findElement("id", "quantity_0").clear();
		WebdriverWait.findElement("id", "quantity_0").sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[1]/td[13]/form/a").click();

		String qty_num1 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_UpdateQuoteStatus_softAssert.assertThat(qty_num1).isEqualTo(data.get(2).get("Column10"));

		// Decimal value Rounding off

		WebdriverWait.findElement("id", "quantity_0").clear();
		WebdriverWait.findElement("id", "quantity_0").sendKeys(data.get(2).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[1]/td[13]/form/a").click();

	}

	public static void QuoteNotes_Mandatory_Fields(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='createQuoteNote']/div/table/tbody[1]/tr[2]/td/input").click();
		String notes_req = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_UpdateQuoteStatus_softAssert.assertThat(notes_req).isEqualTo(data.get(0).get("Column13"));

	}

	public static void AddingQuotesNotes_255Char(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "noteName").sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("id", "noteInfo").sendKeys(data.get(6).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='createQuoteNote']/div/table/tbody[1]/tr[2]/td/input").click();

		String notes_name = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_UpdateQuoteStatus_softAssert.assertThat(notes_name).isEqualTo("The maximum limit for Note Name is 255 characters.");

		String notes_info = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		PO_UpdateQuoteStatus_softAssert.assertThat(notes_info).isEqualTo("The maximum limit for Note Info is 255 characters.");

		WebdriverWait.findElement("id", "noteName").clear();
		WebdriverWait.findElement("id", "noteInfo").clear();
	}

	public static void Remove_QuoteNotes() {

		WebdriverWait.findElement("id", "noteName").sendKeys("Note Name Test");
		WebdriverWait.findElement("id", "noteInfo").sendKeys("Note Info Test");

		WebdriverWait.findElement("xpath", "//*[@id='createQuoteNote']/div/table/tbody[1]/tr[2]/td/input").click();

		WebdriverWait.findElement("xpath", "//*[@id='removeQuoteNote']/input[1]").click();
		// WebElement noteContainer = WebdriverWait.findElement("xpath",
		// "//*[@id='noteContainer']/table[2]/tbody/tr/td[1]");
		// Assert.assertNull(noteContainer);
	}

	public static void Val_RejectedQuoteLineitem_CreateOrder(List<HashMap<String, String>> data) {

		for (int a = 0; a <= 4; a++) {

			if (a <= 3) {
				Select status_dd = new Select(WebdriverWait.findElement("id", "statusId_" + a + ""));
				status_dd.selectByVisibleText("Rejected");
				WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[" + (a + 1) + "]/td[13]/form/a").click();

				String successMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
				PO_UpdateQuoteStatus_softAssert.assertThat(successMsg).isEqualTo(data.get(7).get("Column17"));

			} else {
				Select status_dd = new Select(WebdriverWait.findElement("id", "statusId_" + a + ""));
				status_dd.selectByVisibleText("Fully Accepted");
				WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[" + (a + 1) + "]/td[13]/form/a").click();
				String successMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
				PO_UpdateQuoteStatus_softAssert.assertThat(successMsg).isEqualTo(data.get(7).get("Column17"));
			}
		}
		WebdriverWait.findElement("link", "Create Order").click();
		WebdriverWait.findElement("link", "Continue").click();
		WebdriverWait.findElement("link", "Continue").click();

		String prdID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/form/table/tbody/tr/td[2]").getText();
		PO_UpdateQuoteStatus_softAssert.assertThat(prdID).contains("BRUFT201");

	}

	public static void PO_UpdateQuoteStatus_assertions() {
		PO_UpdateQuoteStatus_softAssert.assertAll();
	}

}
