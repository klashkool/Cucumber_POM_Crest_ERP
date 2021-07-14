package com.AccountReceivable_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.support.ui.Select;

import com.Sales_Modules.Sales_SalesOrder;
import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AR_CreditMemo extends Base{
	public static Logger log = Logger.getLogger(Sales_SalesOrder.class);

	@Rule
	public static JUnitSoftAssertions AR_Credit_Or_DebitMemo_softAssert = new JUnitSoftAssertions();

	//////////////////////////////////////////Negative Flow//////////////////////////////////////////////

	public static void click_CreateCredit_or_DebitMemo() {
		WebdriverWait.findElement("link", "Create Credit/Debit Memo").click();
	}

	public static void click_Items() {
		WebdriverWait.findElement("link", "Items").click();
	}

	public static void Validating_CreditMemo_date_range_with_invalid_date(List<HashMap<String, String>> data) {
		// From Date
		WebdriverWait.findElement("id", "fromInvoiceDate_i18n").sendKeys(data.get(8).get("Column1"));
		// Thru Date
		WebdriverWait.findElement("id", "thruInvoiceDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("id", "submit").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_Credit_Or_DebitMemo_softAssert.assertThat(validationMsg).isEqualTo("Thru Date must be greater than or equal to From Date.");
	}

	public static void createCreditMemo_for_NegFlow(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		WebdriverWait.findElement("id", "0_lookupId_partyIdFrom").sendKeys(data_Positive.get(0).get("Column4"));
		Select numberingFormatIdDD = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		numberingFormatIdDD.selectByIndex(2);
		WebdriverWait.findElement("xpath", "//*[@id='createCreditMemo']/center/input").click();
		}


	public static void Validating_without_Mandatory_details(List<HashMap<String, String>> data) {
		Select invoiceTypeIdDD = new Select(WebdriverWait.findElement("id", "invoiceTypeId"));
		invoiceTypeIdDD.selectByVisibleText("-Select-");
		Select organizationPartyIdDD = new Select(WebdriverWait.findElement("id", "organizationPartyId"));
		organizationPartyIdDD.selectByVisibleText("-Select-");
		WebdriverWait.findElement("id", "invoiceDate_i18n").clear();
		WebdriverWait.findElement("xpath", "//*[@id='createCreditMemo']/center/input").click();

		String path1 = "//*[@id='content-messages']/div/ul/li[";
		String path2 = "]";
		for (int i = 1; i <= 6; i++) {
			String all_ValidationMsg = WebdriverWait.findElement("xpath", path1 + i + path2).getText();
			if (i == 1) {
				AR_Credit_Or_DebitMemo_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(14).get("Column6"));
			} else if (i == 2) {
				AR_Credit_Or_DebitMemo_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(4).get("Column4"));
			} else if (i == 3) {
				AR_Credit_Or_DebitMemo_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(12).get("Column5"));
			} else if (i == 4) {
				AR_Credit_Or_DebitMemo_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(33).get("Column2"));
			} else if (i == 5) {
				AR_Credit_Or_DebitMemo_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(1).get("Column7"));
			}else if (i == 6) {
				AR_Credit_Or_DebitMemo_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(7).get("Column6"));
			}
		}
	}

	public static void Validating_CreditMemo_Items_page_without_Mandatory_details(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("name", "submitTerm").click();
		String path1 = "//*[@id='content-messages']/div/ul/li[";
		String path2 = "]";
		for (int i = 1; i <= 3; i++) {
			String all_ValidationMsg = WebdriverWait.findElement("xpath", path1 + i + path2).getText();
			if (i == 1) {
				AR_Credit_Or_DebitMemo_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(15).get("Column6"));
			} else if (i == 2) {
				AR_Credit_Or_DebitMemo_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(13).get("Column12"));
			} else if (i == 3) {
				AR_Credit_Or_DebitMemo_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(2).get("Column9"));
			}
		}
	}

	public static void add_LineItem_for_NegFlow(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("id", "amount").sendKeys("500");
		Select partyRoleDD = new Select(WebdriverWait.findElement("id", "partyRole"));
		partyRoleDD.selectByVisibleText("UIDAI - Sales Dept");
		Select overrideGlAccountIdDD = new Select(WebdriverWait.findElement("id", "overrideGlAccountId"));
		overrideGlAccountIdDD.selectByVisibleText("110 - Checking Account [110]");
		WebdriverWait.findElement("name", "submitTerm").click();
	}

	public static void Validate_by_updating_Amount_with_SPL_Characters(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("id", "amount_o_1").clear();
		WebdriverWait.findElement("id", "amount_o_1").sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "update").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_Credit_Or_DebitMemo_softAssert.assertThat(validationMsg).isEqualTo(data.get(12).get("Column12"));
	}

	public static void Validate_by_removing_line_item(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("link", "Remove").click();
		driver.switchTo().alert().accept();
		String successMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		AR_Credit_Or_DebitMemo_softAssert.assertThat(successMsg).isEqualTo("Credit/Debit Memo Item Removed Successfully.");

		}

	public static void Validate_void_CreditMemo_without_any_details(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("link", "Void").click();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/table/tbody[1]/tr[2]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_Credit_Or_DebitMemo_softAssert.assertThat(validationMsg).isEqualTo(data.get(19).get("Column17"));

	}


	public static void AR_Credit_Or_DebitMemo_softAssert() {
		AR_Credit_Or_DebitMemo_softAssert.assertAll();
	}

}
