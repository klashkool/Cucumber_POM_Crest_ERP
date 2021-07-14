package com.AccountsPayable_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AP_PurchaseInvoices_Items extends Base {

	@Rule
	public static JUnitSoftAssertions PI_Items_softAssert = new JUnitSoftAssertions();

	public static void RevisedUnitCost_Validation(List<HashMap<String, String>> data, List<HashMap<String, String>> data1) {

		int j = 44;
		for (int a = 1; a <= 5; a++) {

			String ShpQty = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/table/tbody/tr[" + a + "]/td[4]").getText();
			PI_Items_softAssert.assertThat(ShpQty).isEqualTo(data.get(j).get("Column4"));

			String BaseDocumentUnitCost = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/table/tbody/tr[" + a + "]/td[5]")
							.getText();

			PI_Items_softAssert.assertThat(BaseDocumentUnitCost).isEqualTo(data1.get(j).get("Column5"));

			String BaseDocumentValue = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/table/tbody/tr[" + a + "]/td[6]")
							.getText().replace("₹", "").replace(",", "");
			PI_Items_softAssert.assertThat(BaseDocumentValue).isEqualTo(data.get(j).get("Column6"));

			String AllocUnitPerunit = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/table/tbody/tr[" + a + "]/td[7]")
							.getText();
			PI_Items_softAssert.assertThat(AllocUnitPerunit).isEqualTo(data1.get(j).get("Column7"));

			String RevisedUnitCost = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/table/tbody/tr[" + a + "]/td[8]").getText();
			PI_Items_softAssert.assertThat(RevisedUnitCost).isEqualTo(data1.get(j).get("Column8"));

			String TotalAllocCost = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/table/tbody/tr[" + a + "]/td[9]").getText()
							.replace("₹", "").replace(",", "");
			PI_Items_softAssert.assertThat(TotalAllocCost).isEqualTo(data.get(j).get("Column9"));

			String CurrentQOH = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/table/tbody/tr[" + a + "]/td[10]").getText();
			PI_Items_softAssert.assertThat(CurrentQOH).isEqualTo(data.get(j).get("Column1"));
			j++;
		}
	}



	//////////////////////////////////// Negative
	//////////////////////////////////// Flow////////////////////////////////

	public static void Items_link() {

		WebdriverWait.findElement("link", "Items").click();
	}

	public static void MandatoryFields_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "submitTerm").click();

		String invItemType = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String qtyReq = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String unitPriceReq = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();
		String deptReq = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[4]").getText();

		PI_Items_softAssert.assertThat(invItemType).isEqualTo(data.get(19).get("Column21"));
		PI_Items_softAssert.assertThat(qtyReq).isEqualTo(data.get(0).get("Column10"));
		PI_Items_softAssert.assertThat(unitPriceReq).isEqualTo(data.get(11).get("Column12"));
		PI_Items_softAssert.assertThat(deptReq).isEqualTo(data.get(2).get("Column9"));
	}

	public static void desc_255Charc_Val(List<HashMap<String, String>> data) {

		Select invType = new Select(WebdriverWait.findElement("id", "invoiceItemTypeId"));
		invType.selectByVisibleText("Invoice Product Item(Purchase)");

		WebdriverWait.findElement("id", "description").sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("id", "quantity").sendKeys(data.get(4).get("Column1"));

		Select dept = new Select(WebdriverWait.findElement("id", "partyRole"));
		dept.selectByVisibleText("UIDAI - Purchase Dept");

		Select taxCatg = new Select(WebdriverWait.findElement("id", "gstCategoryId"));
		taxCatg.selectByVisibleText("GST 5%");

		WebdriverWait.findElement("id", "amount").sendKeys("5000");

		Select GL_Acct = new Select(WebdriverWait.findElement("id", "overrideGlAccountId"));
		GL_Acct.selectByVisibleText("115001 - Accounts Receivable (Debtors)INR [115001]");

		WebdriverWait.findElement("name", "submitTerm").click();

		String charcLimit = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Items_softAssert.assertThat(charcLimit).isEqualTo(data.get(5).get("Column13"));

		WebdriverWait.findElement("id", "description").clear();
	}

	public static void InvalidQty(List<HashMap<String, String>> data) {

		//Negative Qty
		WebdriverWait.findElement("id", "quantity").clear();
		WebdriverWait.findElement("id", "quantity").sendKeys(data.get(1).get("Column1"));
		WebdriverWait.findElement("name", "submitTerm").click();
		String qty_grt0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Items_softAssert.assertThat(qty_grt0).isEqualTo(data.get(1).get("Column10"));

		// 0 Value
		WebdriverWait.findElement("id", "quantity").clear();
		WebdriverWait.findElement("id", "quantity").sendKeys(data.get(3).get("Column1"));
		WebdriverWait.findElement("name", "submitTerm").click();
		String qty_grt = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Items_softAssert.assertThat(qty_grt).isEqualTo(data.get(1).get("Column10"));

		// Spl Characters
		WebdriverWait.findElement("id", "quantity").clear();
		WebdriverWait.findElement("id", "quantity").sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "submitTerm").click();
		String qty_num1 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Items_softAssert.assertThat(qty_num1).isEqualTo(data.get(2).get("Column10"));

		// Enter valid Qty
		WebdriverWait.findElement("id", "quantity").clear();
		WebdriverWait.findElement("id", "quantity").sendKeys(data.get(4).get("Column1"));
	}

	public static void Invalid_UnitPrice(List<HashMap<String, String>> data) {

		// Negative Value
		WebdriverWait.findElement("id", "amount").clear();
		WebdriverWait.findElement("id", "amount").sendKeys(data.get(1).get("Column1"));
		WebdriverWait.findElement("name", "submitTerm").click();
		String UnitPrice_grt0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Items_softAssert.assertThat(UnitPrice_grt0).isEqualTo(data.get(12).get("Column12"));

		// 0 Value
		WebdriverWait.findElement("id", "amount").clear();
		WebdriverWait.findElement("id", "amount").sendKeys(data.get(3).get("Column1"));
		WebdriverWait.findElement("name", "submitTerm").click();
		String UnitPrice_grt = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Items_softAssert.assertThat(UnitPrice_grt).isEqualTo(data.get(12).get("Column12"));

		// Spl Characters
		WebdriverWait.findElement("id", "amount").clear();
		WebdriverWait.findElement("id", "amount").sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "submitTerm").click();
		String UnitPrice_num = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Items_softAssert.assertThat(UnitPrice_num).isEqualTo(data.get(13).get("Column12"));

		// Enter valid Qty
		WebdriverWait.findElement("id", "amount").clear();
		WebdriverWait.findElement("id", "amount").sendKeys(data.get(4).get("Column1"));
	}

	public static void InvalidQty_LineItem(List<HashMap<String, String>> data) {

		// Negative Qty
		WebdriverWait.findElement("id", "quantity_o_1").clear();
		WebdriverWait.findElement("id", "quantity_o_1").sendKeys(data.get(1).get("Column1"));
		WebdriverWait.findElement("name", "update").click();
		String qty_grt0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Items_softAssert.assertThat(qty_grt0).isEqualTo(data.get(1).get("Column10"));

		// 0 Value
		WebdriverWait.findElement("id", "quantity_o_1").clear();
		WebdriverWait.findElement("id", "quantity_o_1").sendKeys(data.get(3).get("Column1"));
		WebdriverWait.findElement("name", "update").click();
		String qty_grt = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Items_softAssert.assertThat(qty_grt).isEqualTo(data.get(1).get("Column10"));

		// Spl Characters
		WebdriverWait.findElement("id", "quantity_o_1").clear();
		WebdriverWait.findElement("id", "quantity_o_1").sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "update").click();
		String qty_num1 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Items_softAssert.assertThat(qty_num1).isEqualTo(data.get(2).get("Column10"));

		// Empty Qty
		WebdriverWait.findElement("id", "quantity_o_1").clear();
		WebdriverWait.findElement("name", "update").click();
		String qtyReq = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Items_softAssert.assertThat(qtyReq).isEqualTo(data.get(0).get("Column10"));

		// Enter valid Qty
		WebdriverWait.findElement("id", "quantity_o_1").clear();
		WebdriverWait.findElement("id", "quantity_o_1").sendKeys(data.get(4).get("Column1"));
	}

	public static void InvalidUnitPrice_LineItem(List<HashMap<String, String>> data) {

		// Negative Qty
		WebdriverWait.findElement("id", "amount_o_1").clear();
		WebdriverWait.findElement("id", "amount_o_1").sendKeys(data.get(1).get("Column1"));
		WebdriverWait.findElement("name", "update").click();
		String qty_grt0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Items_softAssert.assertThat(qty_grt0).isEqualTo(data.get(12).get("Column12"));

		// 0 Value
		WebdriverWait.findElement("id", "amount_o_1").clear();
		WebdriverWait.findElement("id", "amount_o_1").sendKeys(data.get(3).get("Column1"));
		WebdriverWait.findElement("name", "update").click();
		String qty_grt = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Items_softAssert.assertThat(qty_grt).isEqualTo(data.get(12).get("Column12"));

		// Spl Characters
		WebdriverWait.findElement("id", "amount_o_1").clear();
		WebdriverWait.findElement("id", "amount_o_1").sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "update").click();
		String qty_num1 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Items_softAssert.assertThat(qty_num1).isEqualTo(data.get(13).get("Column12"));

		// Empty Qty
		WebdriverWait.findElement("id", "amount_o_1").clear();
		WebdriverWait.findElement("name", "update").click();
		String qtyReq = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Items_softAssert.assertThat(qtyReq).isEqualTo(data.get(12).get("Column12"));

		// Enter valid Qty
		WebdriverWait.findElement("id", "amount_o_1").clear();
		WebdriverWait.findElement("id", "quantity_o_1").sendKeys(data.get(4).get("Column1"));
	}

	public static void Add_LineItems() {

		WebdriverWait.findElement("name", "submitTerm").click();
	}

	public static void Remove_LineItems() {

		List<WebElement> rows1 = WebdriverWait.findElements("xpath", "//*[@id='updateInvoiceItem']/table/tbody/tr");
		rows1.size();

		WebdriverWait.findElement("xpath", "//*[@id='updateInvoiceItem']/table/tbody/tr[1]/td[11]/a").click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

		List<WebElement> rows2 = WebdriverWait.findElements("xpath", "//*[@id='updateInvoiceItem']/table/tbody/tr");
		rows2.size();

		PI_Items_softAssert.assertThat(rows2.size()).isEqualTo(rows1.size() - 1);
	}

	public static void PI_Items_assertions() {
		PI_Items_softAssert.assertAll();
	}
}
