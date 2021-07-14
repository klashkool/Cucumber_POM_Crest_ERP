package com.AccountReceivable_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;

import com.Sales_Modules.Sales_SalesOrder;
import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AR_Receipts_Overview extends Base {
	public static Logger log = Logger.getLogger(Sales_SalesOrder.class);

	@Rule
	public static JUnitSoftAssertions AR_ReceiptsOverview_softAssert = new JUnitSoftAssertions();
	public static void ConfirmPayment() throws InterruptedException {

		WebdriverWait.findElement("link", "Confirm").click();

		String Pymt_Status = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody[1]/tr[2]/td[4]").getText();
		Assert.assertEquals("Confirmed", Pymt_Status);
		Thread.sleep(1000);
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr[2]/td[3]/a").click();// Click
																																// POnum
																																// SOnum
	}

	////////////////////////////////////Negative Flows////////////////////////////////////////////

	public static void Validate_BackToPayment_link_navigating_to_ReceiptOverviewPage() {

		WebdriverWait.findElement("link", "Cancel").click();
		WebdriverWait.findElement("link", "Back to Payment").click();
		String verifyReceiptOverviewPageIsDisplayed = driver.getTitle();
		AR_ReceiptsOverview_softAssert.assertThat(verifyReceiptOverviewPageIsDisplayed).isEqualTo("Crest: Payment Overview");
	}

	public static void Validate_Cancel_without_voidReason(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("link", "Cancel").click();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/table/tbody[1]/tr[2]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_ReceiptsOverview_softAssert.assertThat(validationMsg).isEqualTo(data.get(19).get("Column17"));
		}

	public static void Validate_Cancel_without_voidDate(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "voidReason").sendKeys("Test");
		WebdriverWait.findElement("id", "voidDate_i18n").clear();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/table/tbody[1]/tr[2]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_ReceiptsOverview_softAssert.assertThat(validationMsg).isEqualTo(data.get(29).get("Column2"));
		}

	public static void Validate_VoidReason_255Char(List<HashMap<String, String>> data) {
	//	WebdriverWait.findElement("link", "Cancel").click();
		WebdriverWait.findElement("id", "voidReason").sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/table/tbody[1]/tr[2]/td/input").click();
		String getVoidReason = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody[1]/tr[2]/td[4]").getText();
		AR_ReceiptsOverview_softAssert.assertThat(getVoidReason).isEqualTo("Cancelled");
	}

	public static void Validate_BackToSearch_link_navigating_to_ReceiptOverviewPage() {
		WebdriverWait.findElement("link", "Back to Search").click();
		String verifyReceiptOverviewPageIsDisplayed = driver.getTitle();
		AR_ReceiptsOverview_softAssert.assertThat(verifyReceiptOverviewPageIsDisplayed).isEqualTo("Crest: Receipts");
	}

	public static void AR_ReceiptsOverview_softAssert() {
		AR_ReceiptsOverview_softAssert.assertAll();
	}
}
