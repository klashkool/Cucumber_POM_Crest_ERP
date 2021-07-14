package com.AccountsPayable_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AP_SupplierAdjustment extends Base {

	@Rule
	public static JUnitSoftAssertions AP_SupAdj_softAssert = new JUnitSoftAssertions();

	/////////////////////////////////////////// Negative
	/////////////////////////////////////////// Flow///////////////////////////////////

	public static void AccountsPayableLink() {

		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
	}

	public static void SupAdjLink() {

		WebdriverWait.findElement("link", "Supplier Adjustment").click();
	}

	public static void CreateSupAdjLink() {

		WebdriverWait.findElement("link", "Create Supplier Adjustment").click();
	}

	public static void InvalidDates_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "fromDate_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("id", "thruDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='searchContainer']/div[2]/form/table/tbody/tr[4]/td/center/input").click();
		String AdjDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_SupAdj_softAssert.assertThat(AdjDate).isEqualTo("Thru Date must be greater than or equal to From Date.");

	}

	public static void MandatoryFields_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "adjustmentDate_i18n").clear();

		WebdriverWait.findElement("name", "getDoc").click();

		String supp = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String currency = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String AdjDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();

		AP_SupAdj_softAssert.assertThat(supp).isEqualTo(data.get(14).get("Column5"));
		AP_SupAdj_softAssert.assertThat(currency).isEqualTo(data.get(2).get("Column11"));
		AP_SupAdj_softAssert.assertThat(AdjDate).isEqualTo("Thru Date must be greater than or equal to From Date.");

	}

	public static void Click_Compute_WithoutLineItems(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "partyIdFrom").sendKeys("11680");
		WebdriverWait.findElement("name", "getDoc").click();
		WebdriverWait.findElement("name", "compute").click();

		String supp = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String currency = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();

		AP_SupAdj_softAssert.assertThat(supp).isEqualTo(data.get(25).get("Column21"));
		AP_SupAdj_softAssert.assertThat(currency).isEqualTo(data.get(26).get("Column21"));

	}

	public static void AP_SupAdj_softAssert() {
		AP_SupAdj_softAssert.assertAll();
	}
}
