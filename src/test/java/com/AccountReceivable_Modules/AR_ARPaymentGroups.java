package com.AccountReceivable_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AR_ARPaymentGroups<softAssertions> extends Base {
	// public static Logger log = Logger.getLogger(Sales_SalesOrder.class);

	public static JUnitSoftAssertions AR_PaymentGroup_softAssert = new JUnitSoftAssertions();

	//////////////////////////////////////////Negative Flow//////////////////////////////////////////////

	public static void clickCreate_Payment_Groups() {
		WebdriverWait.findElement("link", "Create Payment Group").click();
	}

	public static void clickCreateButton() {
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/table/tbody[1]/tr[2]/td[2]/input").click();
	}

	public static void CreateARPaymentGroup() {
		AR_Main.clickAR();
		AR_Main.clickAR_Payment_Groups();
		clickCreate_Payment_Groups();
		WebdriverWait.findElement("id", "paymentGroupName").sendKeys("Test Payment Group");
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/table/tbody[1]/tr[2]/td[2]/input").click();
	}

	public static void Validate_Create_AR_Payment_Group_without_anydata(List<HashMap<String, String>> data) {
		clickCreateButton();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_PaymentGroup_softAssert.assertThat(validationMsg).isEqualTo(data.get(21).get("Column21"));
		}

	public static void Validate_Cancel_AR_Payment(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("link", "Cancel").click();
		String getPageTitle = driver.getTitle();
		AR_PaymentGroup_softAssert.assertThat(getPageTitle).isEqualTo("Crest: Find AR Payment Groups");
		}

	public static void Validate_GroupMembers_without_mandatoryFields(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("link", "Group Members").click();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/div/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		AR_PaymentGroup_softAssert.assertThat(validationMsg).isEqualTo(data.get(22).get("Column21"));
		String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
		AR_PaymentGroup_softAssert.assertThat(validationMsg1).isEqualTo(data.get(32).get("Column2"));
		}

	public static void Validating_GroupMembers_date_range_with_invalid_date(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("id", "0_lookupId_button").click();
		WebdriverWait.findElement("id", "lookupSubmitButton").click();
		WebdriverWait.findElement("xpath", "//*[@id='export_lookup_table']/tbody/tr[1]/td[1]/a").click();
		// From Date
		WebdriverWait.findElement("id", "fromDate_i18n").sendKeys(data.get(8).get("Column1"));
		// Thru Date
		WebdriverWait.findElement("id", "thruDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/div/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_PaymentGroup_softAssert.assertThat(validationMsg).isEqualTo(data.get(19).get("Column2"));
	}

	public static void AR_PaymentGroup_softAssert() {
		AR_PaymentGroup_softAssert.assertAll();
	}
}
