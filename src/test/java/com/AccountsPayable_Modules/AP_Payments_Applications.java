package com.AccountsPayable_Modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AP_Payments_Applications extends Base {

	@Rule
	public static JUnitSoftAssertions AP_PaymentsApplication_softAssert = new JUnitSoftAssertions();

	public static void SelectInvoiceID(List<HashMap<String, String>> data, String PaymentType) throws InterruptedException {

		WebdriverWait.findElement("link", "Applications").click();// Application
		// Link
		ArrayList<String> Invlist = new ArrayList<String>();

		for (int j = 13; j <= 16; j++) {
			Invlist.add(data.get(j).get("Column6"));
		}
		System.out.println(Invlist);

		List<WebElement> Inv_Pay_tble = WebdriverWait.findElements("xpath", "//*[@id='example5']/tbody/tr");
		String totalTabletext = null;
		for (int y = 2; y <= Inv_Pay_tble.size(); y++) {
			String before_xpath = "//*[@id='example5']/tbody/tr[";
			String after_xpath = "]/td";

			totalTabletext = WebdriverWait.findElement("xpath", before_xpath + y + after_xpath).getText();

			if (Invlist.contains(totalTabletext)) {

				String before_xpath1 = "//*[@id='example5']/tbody/tr[";
				String after_xpath1 = "]/td[8]/input";

				if (PaymentType.contains("FullPayment")) {

					WebdriverWait.findElement("xpath", before_xpath1 + y + after_xpath1).click();

				} else if (PaymentType.contains("PartialPayment")) {

					WebdriverWait.findElement("xpath", before_xpath1 + y + after_xpath1).click();

					if (totalTabletext.contains(data.get(13).get("Column6"))) {

						WebdriverWait.findElement("xpath", "//*[@id='example5']/tbody/tr[" + y + "]/td[7]/input").clear();
						WebdriverWait.findElement("xpath", "//*[@id='example5']/tbody/tr[" + y + "]/td[7]/input").sendKeys("10000");

					}
				} else if (PaymentType.contains("ExchangeRate")) {

					if (totalTabletext.contains(data.get(13).get("Column6"))) {
						WebdriverWait.findElement("xpath", before_xpath1 + y + after_xpath1).click();
					}
				}
				Thread.sleep(2000);
			}
		}

		WebdriverWait.findElement("xpath", "//*[@id='createPaymentApplication']/div/input").click();
		WebdriverWait.findElement("link", "Sent").click();
	}

	public static void SelectInvoiceID_PayTerm(List<HashMap<String, String>> data) throws InterruptedException {

		WebdriverWait.findElement("link", "Applications").click();// Application
		// Link
		ArrayList<String> Invlist = new ArrayList<String>();

		for (int j = 13; j <= 16; j++) {
			Invlist.add(data.get(j).get("Column6"));
		}
		System.out.println(Invlist);

		List<WebElement> Inv_Pay_tble = WebdriverWait.findElements("xpath", "//*[@id='example5']/tbody/tr");
		String totalTabletext = null;
		String payTermText = null;

		for (int y = 2; y <= Inv_Pay_tble.size(); y++) {
			String before_xpath = "//*[@id='example5']/tbody/tr[";
			String after_xpath = "]/td";

			totalTabletext = WebdriverWait.findElement("xpath", before_xpath + y + after_xpath).getText();

			payTermText = WebdriverWait.findElement("xpath", "//*[@id='example5']/tbody/tr[" + (y - 1) + "]/td[4]").getText();

			if (Invlist.contains(totalTabletext) && (payTermText.contains("Payment net days, part 1"))) {

				String before_xpath1 = "//*[@id='example5']/tbody/tr[";
				String after_xpath1 = "]/td[8]/input";

					WebdriverWait.findElement("xpath", before_xpath1 + y + after_xpath1).click();

			} else if (Invlist.contains(totalTabletext) && (payTermText.contains("Payment net days, part 2"))) {
				String before_xpath1 = "//*[@id='example5']/tbody/tr[";
				String after_xpath1 = "]/td[8]/input";

						WebdriverWait.findElement("xpath", before_xpath1 + y + after_xpath1).click();
				}
				Thread.sleep(2000);
			}


		WebdriverWait.findElement("xpath", "//*[@id='createPaymentApplication']/div/input").click();
		WebdriverWait.findElement("link", "Sent").click();
	}

	////////////////////////////////// Negative
	////////////////////////////////// Flow//////////////////////////////

	public static void ApplicationLink() {

		WebdriverWait.findElement("link", "Applications").click();
	}

	public static void Invalid_AmtVal(List<HashMap<String, String>> data) throws InterruptedException {

		// Empty Value

		WebdriverWait.findElement("xpath", "//*[@id='example5']/tbody/tr[1]/td[7]").clear();
		WebdriverWait.findElement("xpath", "//*[@id='example5']/tbody/tr[1]/td[8]").click();
		WebdriverWait.findElement("xpath", "//*[@id='createPaymentApplication']/div[2]/input").click();

		String amtReq = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_PaymentsApplication_softAssert.assertThat(amtReq).isEqualTo(data.get(19).get("Column12"));

		// Zero Value

		WebdriverWait.findElement("xpath", "//*[@id='example5']/tbody/tr[1]/td[7]").clear();
		WebdriverWait.findElement("xpath", "//*[@id='example5']/tbody/tr[1]/td[7]").sendKeys("0");
		WebdriverWait.findElement("xpath", "//*[@id='example5']/tbody/tr[1]/td[8]").click();
		WebdriverWait.findElement("xpath", "//*[@id='createPaymentApplication']/div[2]/input").click();

		String zeroValue = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_PaymentsApplication_softAssert.assertThat(zeroValue).isEqualTo(data.get(17).get("Column12"));

	}

	public static void apply_withoutSelectingLineIems(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='createPaymentApplication']/div[2]/input").click();
		String zeroValue = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_PaymentsApplication_softAssert.assertThat(zeroValue).isEqualTo(data.get(27).get("Column21"));
	}

	public static void removeAddedLineItem() {

		WebdriverWait.findElement("xpath", "//*[@id='example5']/tbody/tr[1]/td[8]/input").click();
		WebdriverWait.findElement("xpath", "//*[@id='createPaymentApplication']/div[2]/input").click();

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr[2]/td[6]/a").click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

		String noRecords = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr[2]/td").getText();
		AP_PaymentsApplication_softAssert.assertThat(noRecords).isEqualTo("No Records Found");

	}

	public static void Cancel_Payments(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Cancel").click();

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/table/tbody[1]/tr[2]/td/input").click();

		String voidmsg = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_PaymentsApplication_softAssert.assertThat(voidmsg).isEqualTo(data.get(21).get("Column21"));
	}

	public static void AP_PaymentsApplication_softAssert() {
		AP_PaymentsApplication_softAssert.assertAll();
	}

}
