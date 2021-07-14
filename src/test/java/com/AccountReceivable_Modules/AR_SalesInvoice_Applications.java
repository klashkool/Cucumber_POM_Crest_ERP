package com.AccountReceivable_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.WebElement;

import com.Sales_Modules.Sales_SalesOrder;
import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AR_SalesInvoice_Applications extends Base {
	public static Logger log = Logger.getLogger(Sales_SalesOrder.class);

	@Rule
	public static JUnitSoftAssertions SI_Appln_softAssert = new JUnitSoftAssertions();

	public static void Inv_Adjustments(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Applications").click();

		List<WebElement> payments_toApplyTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr");

		for (int a = 1; a <= payments_toApplyTable.size(); a++) {

			String payID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr[" + a + "]/td/a").getText();

			if (payID.equals(data.get(13).get("Column10"))) {

				WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr[" + a + "]/td[6]/a").click();
			}

		}
	}

	public static void Val_AdjustedInv_Amt(List<HashMap<String, String>> data) {

		String payID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr/td[1]/a").getText();

		if (payID.equals(data.get(14).get("Column10"))) {

			String invAmt = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr/td[2]").getText();
			String appliedAmt = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr/td[3]").getText();

			Assert.assertEquals(data.get(13).get("Column9"), invAmt);
			Assert.assertEquals(data.get(13).get("Column9"), appliedAmt);

		}

	}

	////////////////////////////////////////////Negative flow/////////////////////////////////////////

	public static void clickApplnsTab() {
		WebdriverWait.findElement("link", "Applications").click();
	}

	public static void applyPaymentWithEmptyAmount(List<HashMap<String, String>> data) {
		clickApplnsTab();
		WebdriverWait.findElement("id", "amountToApply_o_0").clear();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr[1]/td[6]/a").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SI_Appln_softAssert.assertThat(validationMsg).isEqualTo("Amount Cannot be Empty.");
	}

	public static void SI_Appln_softAssert() {
		SI_Appln_softAssert.assertAll();
	}
}
