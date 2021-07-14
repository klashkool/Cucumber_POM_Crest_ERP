package com.Sales_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.WebElement;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Sales_Quotes_View extends Base {

	@Rule
	public static JUnitSoftAssertions SO_QuotesView_softAssert = new JUnitSoftAssertions();

	public static void Quote_Header_Val() {

		String quotesHeader = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/p").getText();
		Assert.assertEquals("Quote updated successfully", quotesHeader);
		System.out.println("Quote Accepted Successfully");
	}

	public static void SO_Quotes_CreateOrderLink() {

		WebdriverWait.findElement("link", "Create Order").click();

		// WebdriverWait.findElement("xpath",
		// "//*[@id='content-main-section']/div[1]/div[1]/ul/li[4]/a").click();

	}

	public static void SO_Quotes_GrndTotal_Val(List<HashMap<String, String>> data, String Header) {

		log.info("Validating SO Quotes GrandTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath",
				"//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String grandTotal = WebdriverWait.findElement("xpath",
					"//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr[" + a + "]/td[1]").getText();

			if (grandTotal.contains("Grand Total")) {

				String GrandTotal = WebdriverWait
						.findElement("xpath",
								"//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr[" + a + "]/td[2]")
						.getText().replace(",", "");
				System.out.println("GrandTotal" + GrandTotal);
				SO_QuotesView_softAssert.assertThat(GrandTotal).isEqualTo(data.get(17).get(Header));
				log.info("Grand Total validated Successfully");
				System.out.println("Grand Total Validated Successfully");
			}
		}

	}

	public static void SO_View_assertions() {
		SO_QuotesView_softAssert.assertAll();
	}

}
