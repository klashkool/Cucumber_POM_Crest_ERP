package com.AccountReceivable_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AR_SalesInvoice_Items extends Base{
	@Rule
	public static JUnitSoftAssertions SalesInvoiceItems_softAssert = new JUnitSoftAssertions();

	/////////////////////////////////////Negative Flows////////////////////////////////////////////////
	public static void Validating_without_Mandatory_details(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("link", "Items").click();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form/table/tbody[1]/tr[6]/td/input").click();
		String path1 = "//*[@id='content-messages']/div/ul/li[";
		String path2 = "]";
		for (int i = 1; i <= 4; i++) {
			String all_ValidationMsg = WebdriverWait.findElement("xpath", path1 + i + path2).getText();
			if (i == 1) {
				SalesInvoiceItems_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(11).get("Column6"));
			} else if (i == 2) {
				SalesInvoiceItems_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(12).get("Column10"));
			} else if (i == 3) {
				SalesInvoiceItems_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(9).get("Column12"));
			} else if (i == 4) {
				SalesInvoiceItems_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(2).get("Column9"));
			}
		}
	}

	public static void ClickItemsTab() {
		WebdriverWait.findElement("link", "Items").click();
	}

	public static void Validate_Qty_with_Spl_Characters(List<HashMap<String, String>> data) {
		ClickItemsTab();
		Select invoiceType_dd = new Select(WebdriverWait.findElement("id", "invoiceItemTypeId"));
		invoiceType_dd.selectByVisibleText(data.get(27).get("Column1"));
		Select dept_dd = new Select(WebdriverWait.findElement("id", "partyRole"));
		dept_dd.selectByVisibleText(data.get(28).get("Column1"));
		WebdriverWait.findElement("name", "amount").sendKeys(data.get(4).get("Column1"));
		Select glAcc_dd = new Select(WebdriverWait.findElement("id", "overrideGlAccountId"));
		glAcc_dd.selectByVisibleText(data.get(29).get("Column1"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form/table/tbody[1]/tr[6]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoiceItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(15).get("Column10"));
}

	public static void Validate_Qty_with_Characters(List<HashMap<String, String>> data) {
		ClickItemsTab();
		Select invoiceType_dd = new Select(WebdriverWait.findElement("id", "invoiceItemTypeId"));
		invoiceType_dd.selectByVisibleText(data.get(27).get("Column1"));
		Select dept_dd = new Select(WebdriverWait.findElement("id", "partyRole"));
		dept_dd.selectByVisibleText(data.get(28).get("Column1"));
		WebdriverWait.findElement("name", "amount").sendKeys(data.get(4).get("Column1"));
		Select glAcc_dd = new Select(WebdriverWait.findElement("id", "overrideGlAccountId"));
		glAcc_dd.selectByVisibleText(data.get(29).get("Column1"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(5).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form/table/tbody[1]/tr[6]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoiceItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(15).get("Column10"));
}

public static void Validate_Qty_with_Neg_Value(List<HashMap<String, String>> data) {
	ClickItemsTab();
	Select invoiceType_dd = new Select(WebdriverWait.findElement("id", "invoiceItemTypeId"));
	invoiceType_dd.selectByVisibleText(data.get(27).get("Column1"));
	Select dept_dd = new Select(WebdriverWait.findElement("id", "partyRole"));
	dept_dd.selectByVisibleText(data.get(28).get("Column1"));
	WebdriverWait.findElement("name", "amount").sendKeys(data.get(4).get("Column1"));
	Select glAcc_dd = new Select(WebdriverWait.findElement("id", "overrideGlAccountId"));
	glAcc_dd.selectByVisibleText(data.get(29).get("Column1"));
	WebdriverWait.findElement("name", "quantity").sendKeys(data.get(1).get("Column1"));
	WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form/table/tbody[1]/tr[6]/td/input").click();
	String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
	SalesInvoiceItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(18).get("Column10"));
}
public static void Validate_UnitPrice_with_Spl_Characters(List<HashMap<String, String>> data) {
	ClickItemsTab();
	Select invoiceType_dd = new Select(WebdriverWait.findElement("id", "invoiceItemTypeId"));
	invoiceType_dd.selectByVisibleText(data.get(27).get("Column1"));
	Select dept_dd = new Select(WebdriverWait.findElement("id", "partyRole"));
	dept_dd.selectByVisibleText(data.get(28).get("Column1"));
	WebdriverWait.findElement("name", "amount").sendKeys(data.get(0).get("Column1"));
	Select glAcc_dd = new Select(WebdriverWait.findElement("id", "overrideGlAccountId"));
	glAcc_dd.selectByVisibleText(data.get(29).get("Column1"));
	WebdriverWait.findElement("name", "quantity").sendKeys(data.get(4).get("Column1"));
	WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form/table/tbody[1]/tr[6]/td/input").click();
	String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
	SalesInvoiceItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(11).get("Column12"));
}

public static void Validate_UnitPrice_with_Characters(List<HashMap<String, String>> data) {
	ClickItemsTab();
	Select invoiceType_dd = new Select(WebdriverWait.findElement("id", "invoiceItemTypeId"));
	invoiceType_dd.selectByVisibleText(data.get(27).get("Column1"));
	Select dept_dd = new Select(WebdriverWait.findElement("id", "partyRole"));
	dept_dd.selectByVisibleText(data.get(28).get("Column1"));
	WebdriverWait.findElement("name", "amount").sendKeys(data.get(5).get("Column1"));
	Select glAcc_dd = new Select(WebdriverWait.findElement("id", "overrideGlAccountId"));
	glAcc_dd.selectByVisibleText(data.get(29).get("Column1"));
	WebdriverWait.findElement("name", "quantity").sendKeys(data.get(4).get("Column1"));
	WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form/table/tbody[1]/tr[6]/td/input").click();
	String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
	SalesInvoiceItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(11).get("Column12"));
}

public static void Validate_UnitPrice_with_Neg_Value(List<HashMap<String, String>> data) {
	ClickItemsTab();
	Select invoiceType_dd = new Select(WebdriverWait.findElement("id", "invoiceItemTypeId"));
	invoiceType_dd.selectByVisibleText(data.get(27).get("Column1"));
	Select dept_dd = new Select(WebdriverWait.findElement("id", "partyRole"));
	dept_dd.selectByVisibleText(data.get(28).get("Column1"));
	WebdriverWait.findElement("name", "amount").sendKeys(data.get(1).get("Column1"));
	Select glAcc_dd = new Select(WebdriverWait.findElement("id", "overrideGlAccountId"));
	glAcc_dd.selectByVisibleText(data.get(29).get("Column1"));
	WebdriverWait.findElement("name", "quantity").sendKeys(data.get(4).get("Column1"));
	WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form/table/tbody[1]/tr[6]/td/input").click();
	String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
	SalesInvoiceItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(10).get("Column12"));
}

public static void Validate_without_GL_Acc(List<HashMap<String, String>> data) {
	ClickItemsTab();
	Select invoiceType_dd = new Select(WebdriverWait.findElement("id", "invoiceItemTypeId"));
	invoiceType_dd.selectByVisibleText(data.get(27).get("Column1"));
	Select dept_dd = new Select(WebdriverWait.findElement("id", "partyRole"));
	dept_dd.selectByVisibleText(data.get(28).get("Column1"));
	WebdriverWait.findElement("name", "amount").sendKeys(data.get(1).get("Column1"));
	Select glAcc_dd = new Select(WebdriverWait.findElement("id", "overrideGlAccountId"));
	glAcc_dd.selectByVisibleText("-Select-");
	WebdriverWait.findElement("name", "quantity").sendKeys(data.get(1).get("Column1"));
	WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form/table/tbody[1]/tr[6]/td/input").click();
	String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
	SalesInvoiceItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(16).get("Column21"));
}

public static void addLineItem_NegFlow(List<HashMap<String, String>> data) {
	ClickItemsTab();
	Select invoiceType_dd = new Select(WebdriverWait.findElement("id", "invoiceItemTypeId"));
	invoiceType_dd.selectByVisibleText(data.get(27).get("Column1"));
	Select dept_dd = new Select(WebdriverWait.findElement("id", "partyRole"));
	dept_dd.selectByVisibleText(data.get(28).get("Column1"));
		WebdriverWait.findElement("name", "amount").sendKeys("25");
	Select glAcc_dd = new Select(WebdriverWait.findElement("id", "overrideGlAccountId"));
	glAcc_dd.selectByVisibleText(data.get(29).get("Column1"));
		WebdriverWait.findElement("name", "quantity").sendKeys("25");
	WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form/table/tbody[1]/tr[6]/td/input").click();

}

public static void Validate_with_more_than_255_characters_in_Description(List<HashMap<String, String>> data) {
	ClickItemsTab();
	Select invoiceType_dd = new Select(WebdriverWait.findElement("id", "invoiceItemTypeId"));
	invoiceType_dd.selectByVisibleText(data.get(27).get("Column1"));
	Select dept_dd = new Select(WebdriverWait.findElement("id", "partyRole"));
	dept_dd.selectByVisibleText(data.get(28).get("Column1"));
	WebdriverWait.findElement("name", "amount").sendKeys(data.get(1).get("Column1"));
	Select glAcc_dd = new Select(WebdriverWait.findElement("id", "overrideGlAccountId"));
	glAcc_dd.selectByVisibleText(data.get(29).get("Column1"));
	WebdriverWait.findElement("name", "quantity").sendKeys(data.get(1).get("Column1"));
	WebdriverWait.findElement("id", "description").sendKeys(data.get(6).get("Column1"));
	WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form/table/tbody[1]/tr[6]/td/input").click();
	String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesInvoiceItems_softAssert.assertThat(validationMsg).isEqualTo("The maximum limit for Description is 255 characters.");
}

public static void Validate_by_updating_qty_with_invalidData(List<HashMap<String, String>> data) {

	WebElement qty = WebdriverWait.findElement("id", "quantity_o_1");
	for(int i=0; i<=2; i++) {
		qty.clear();
		if(i==0)
			qty.sendKeys(data.get(0).get("Column1"));
		else if(i==1)
			qty.sendKeys(data.get(1).get("Column1"));
		else if(i==2)
		qty.sendKeys(data.get(5).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='updateInvoiceItem']/div/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		if(i==0||i==2)
		SalesInvoiceItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(15).get("Column10"));
		else
		SalesInvoiceItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(18).get("Column10"));
	}
}

public static void Validate_by_updating_UnitPrice_with_invalidData(List<HashMap<String, String>> data) {
		// ClickItemsTab();
		// WebdriverWait.findElement("id", "quantity_o_1").sendKeys("10");
	WebElement amt = WebdriverWait.findElement("id", "amount_o_1");
	for(int i=0; i<=2; i++) {
		amt.clear();
		if(i==0)
			amt.sendKeys(data.get(0).get("Column1"));
		else if(i==1)
			amt.sendKeys(data.get(1).get("Column1"));
		else if(i==2)
			amt.sendKeys(data.get(5).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='updateInvoiceItem']/div/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		if(i==0||i==2)
		SalesInvoiceItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(11).get("Column12"));
		else
		SalesInvoiceItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(10).get("Column12"));
	}
}

public static void Validate_lineItem_isExist_by_cancelling_confirmation_Alert(List<HashMap<String, String>> data) {
		// ClickItemsTab();
	WebdriverWait.findElement("link", "Remove").click();
	driver.switchTo().alert().dismiss();
		boolean isLineItemDisplayed = WebdriverWait.findElement("xpath", "//*[@id='updateInvoiceItem']/table/tbody/tr/td[1]").isDisplayed();
		SalesInvoiceItems_softAssert.assertThat(isLineItemDisplayed).isEqualTo(true);
}

public static void Validate_lineItem_isExist_by_Accepting_confirmation_Alert(List<HashMap<String, String>> data) {
		// ClickItemsTab();
	WebdriverWait.findElement("link", "Remove").click();
	driver.switchTo().alert().accept();
		String successMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		// boolean isLineItemDisplayed = WebdriverWait.findElement("xpath",
		// "//*[@id='updateInvoiceItem']/table/tbody/tr/td[1]").isDisplayed();
		SalesInvoiceItems_softAssert.assertThat(successMsg).isEqualTo("Sales Invoice Item Removed Successfully.");
}


	public static void SalesInvoiceItems_softAssert() {
		SalesInvoiceItems_softAssert.assertAll();
	}

}
