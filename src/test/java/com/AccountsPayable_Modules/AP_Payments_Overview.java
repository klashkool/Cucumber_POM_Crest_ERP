package com.AccountsPayable_Modules;

import org.assertj.core.api.JUnitSoftAssertions;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AP_Payments_Overview extends Base {

	public static void ConfirmPayment() {

		JUnitSoftAssertions ConfirmPayments_softAssert = new JUnitSoftAssertions();

		WebdriverWait.findElement("link", "Confirm").click();

		String Pymt_Status = WebdriverWait
				.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody[1]/tr[1]/td[4]")
				.getText();
		// Assert.assertEquals("Confirmed", Pymt_Status);

		ConfirmPayments_softAssert.assertThat(Pymt_Status).isEqualTo("Confirmed");
		ConfirmPayments_softAssert.assertAll();

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[3]/a")
				.click();// Click POnum
	}

	public static void SentPayment() {

		JUnitSoftAssertions SendPayments_softAssert = new JUnitSoftAssertions();

		WebdriverWait.findElement("link", "Sent").click();

		String Pymt_Status = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody[1]/tr[1]/td[4]").getText();
		// Assert.assertEquals("Sent", Pymt_Status);
		SendPayments_softAssert.assertThat(Pymt_Status).isEqualTo("Sent");
		SendPayments_softAssert.assertAll();

	}


}
