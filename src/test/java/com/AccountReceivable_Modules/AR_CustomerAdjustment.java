package com.AccountReceivable_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;

import com.Sales_Modules.Sales_SalesOrder;
import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AR_CustomerAdjustment extends Base{
	public static Logger log = Logger.getLogger(Sales_SalesOrder.class);

	@Rule
	public static JUnitSoftAssertions AR_CustAdjmt_softAssert = new JUnitSoftAssertions();
	
	//////////////////////////////////////////Negative Flow//////////////////////////////////////////////
	
	public static void click_CreateAdjmt() {
		WebdriverWait.findElement("link", "Create Customer Adjustment").click();
	}
	
	public static void Validating_CustAdjmt_date_range_with_invalid_date(List<HashMap<String, String>> data) {
		// From Date
		WebdriverWait.findElement("id", "fromDate_i18n").sendKeys(data.get(8).get("Column1"));
		// Thru Date
		WebdriverWait.findElement("id", "thruDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("id", "submit").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_CustAdjmt_softAssert.assertThat(validationMsg).isEqualTo(data.get(34).get("Column2"));
	}
	
	public static void validate_createCustAdjmt_without_any_details(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("xpath", "//*[@id='generateApReport']/table/tbody/tr[3]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		AR_CustAdjmt_softAssert.assertThat(validationMsg).isEqualTo(data.get(6).get("Column5"));
		String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
		AR_CustAdjmt_softAssert.assertThat(validationMsg1).isEqualTo(data.get(1).get("Column7"));
	}
	
	public static void createCustAdjmt_for_NegFlow(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		WebdriverWait.findElement("id", "0_lookupId_partyIdFrom").sendKeys(data_Positive.get(0).get("Column4"));
		WebdriverWait.findElement("xpath", "//*[@id='generateApReport']/table/tbody/tr[3]/td/input").click();
	}
	
	public static void validate_Compute_without_Credit_Document(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("xpath", "//*[@id='generateApReport']/table[2]/tbody/tr[1]/td[11]/input").click();
		WebdriverWait.findElement("name", "compute").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_CustAdjmt_softAssert.assertThat(validationMsg).isEqualTo(data.get(23).get("Column21"));
	}
	
	public static void validate_Compute_without_Debit_Document(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("xpath", "//*[@id='generateApReport']/table[3]/tbody/tr[1]/td[11]/input").click();
		WebdriverWait.findElement("name", "compute").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_CustAdjmt_softAssert.assertThat(validationMsg).isEqualTo(data.get(24).get("Column21"));
	}

	public static void AR_CustAdjmt_softAssert() {
		AR_CustAdjmt_softAssert.assertAll();
	}

}
