package com.AccountsPayable_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AP_PurchaseInvoices_Applications extends Base {

	@Rule
	public static JUnitSoftAssertions PI_Application_softAssert = new JUnitSoftAssertions();

	public static void Inv_Adjustments(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Applications").click();

		List<WebElement> payments_toApplyTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr");

		for (int a = 1; a <= payments_toApplyTable.size(); a++) {

			String payID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr[" + a + "]/td/a").getText();

			if (payID.equals(data.get(13).get("Column10"))) {

				WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr[" + a + "]/td[6]/a").click();
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

	///////////////////////////////// Negative
	///////////////////////////////// Flow//////////////////////////////////////////////////

	public static void Application_Link() {

		WebdriverWait.findElement("link", "Applications").click();
	}

	public static void InvalidAmt_Val(List<HashMap<String, String>> data) {

		// Empty Amt
		WebdriverWait.findElement("id", "amountToApply_o_0").clear();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr[1]/td[6]/a").click();

		String amtEmpty = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Application_softAssert.assertThat(amtEmpty).isEqualTo(data.get(14).get("Column12"));

		// 0 Value
		WebdriverWait.findElement("id", "amountToApply_o_0").clear();
		WebdriverWait.findElement("id", "amountToApply_o_0").sendKeys("0");
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr[1]/td[6]/a").click();

		String zeroValue = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PI_Application_softAssert.assertThat(zeroValue).isEqualTo(data.get(15).get("Column12"));

	}

	public static void Apply_RemovePayID(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr[1]/td[6]/a").click();

		String successMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		PI_Application_softAssert.assertThat(successMsg).isEqualTo(data.get(13).get("Column17"));

		WebdriverWait.findElement("link", "Remove").click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

		String removeMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		PI_Application_softAssert.assertThat(removeMsg).isEqualTo(data.get(3).get("Column18"));

		String noRecordMsg = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr/td").getText();
		PI_Application_softAssert.assertThat(noRecordMsg).isEqualTo("No Records Found");

	}

	public static void PI_Application_assertions() {
		PI_Application_softAssert.assertAll();
	}

}
