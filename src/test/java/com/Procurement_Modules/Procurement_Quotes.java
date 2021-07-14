package com.Procurement_Modules;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Procurement_Quotes extends Base {

	public static Logger log = Logger.getLogger(Procurement_Quotes.class);

	public static JUnitSoftAssertions PO_Quotes_softAssert = new JUnitSoftAssertions();

	public static void PO_Quotes(List<HashMap<String, String>> data) {

		List<WebElement> itemDetails = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/form/div[2]/div[2]/table/tbody/tr");
		System.out.println(itemDetails.size());

		int b = 0;
		for (int a = 2; a <= itemDetails.size(); a++) {

			WebElement unitprice = WebdriverWait.findElement("id", "unitPrice_o_0");
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", unitprice);

			String unitPrice = data.get(b).get("Column11");
			String price = unitPrice.replace("₹", "").replace("$", "").replace(",", "");

			WebdriverWait.findElement("id", "unitPrice_o_" + b).sendKeys(price);

			// Adding 10days extra from Current Date(ToDate)
			// Estimated Delivery date
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, 10);
			String newDate = dateFormat1.format(cal.getTime());
			WebdriverWait.findElement("name", "givenDate_o_" + b + "_i18n").sendKeys(newDate);
			b++;
		}

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div[4]/input").click();

		Procurement_Quotes_UpdateQuoteStatus.UpdateQuoteStatus();

	}

	////////////////////////////////////////// Negative
	////////////////////////////////////////// Flow/////////////////////////////////////

	public static void PO_Quotes_Navigation() {

		WebdriverWait.findElement("link", "PROCUREMENT").click();
		WebdriverWait.findElement("link", "Quotes").click();
	}

	public static void Create_Quote_Link() {
		WebdriverWait.findElement("link", "Create Quote").click();
	}

	public static void PO_Quotes_SearchPage_InvalidDate_Val(List<HashMap<String, String>> data) {

		// Valid date with past date as Thru date
		WebdriverWait.findElement("id", "validFromDate_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("id", "validThruDate_i18n").sendKeys(data.get(7).get("Column1"));

		// Issue Date Range with past date as Thru date
		WebdriverWait.findElement("id", "issueFromDate_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("id", "issueThruDate_i18n").sendKeys(data.get(7).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='searchCriteria']/table/tbody[1]/tr[6]/td/input").click();

		String valid_ThruDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String issue_ThruDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();

		PO_Quotes_softAssert.assertThat(valid_ThruDate).isEqualTo(data.get(19).get("Column2"));
		PO_Quotes_softAssert.assertThat(issue_ThruDate).isEqualTo(data.get(20).get("Column2"));

	}

	public static void Quotes_Mandatory_Fields_CreateQuote(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "issueDate_i18n").clear();

		Select org_dd = new Select(WebdriverWait.findElement("id", "orgPartyId"));
		org_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("xpath", "//*[@id='createQuote']/div/table/tbody/tr[7]/td/input").click();

		String quote_Name = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String org = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String quoteSupplier = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();
		String quoteRecvdDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[4]").getText();
		String numType = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[5]").getText();

		PO_Quotes_softAssert.assertThat(quote_Name).isEqualTo(data.get(10).get("Column21"));
		PO_Quotes_softAssert.assertThat(org).isEqualTo(data.get(2).get("Column4"));
		PO_Quotes_softAssert.assertThat(quoteSupplier).isEqualTo(data.get(10).get("Column5"));
		PO_Quotes_softAssert.assertThat(quoteRecvdDate).isEqualTo(data.get(15).get("Column2"));
		PO_Quotes_softAssert.assertThat(numType).isEqualTo(data.get(2).get("Column6"));
	}

	public static void Invalid_date_CreateQuote(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "quoteName").sendKeys("Test");
		WebdriverWait.findElement("name", "partyId").sendKeys(data.get(11).get("Column5"));

		WebdriverWait.findElement("id", "validFromDate_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("id", "validThruDate_i18n").sendKeys(data.get(7).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='createQuote']/div/table/tbody/tr[7]/td/input").click();

		String date = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_Quotes_softAssert.assertThat(date).isEqualTo(data.get(10).get("Column2"));

		WebdriverWait.findElement("id", "validFromDate_i18n").clear();
		WebdriverWait.findElement("id", "validThruDate_i18n").clear();

	}

	public static void Val_CreateQuotes_Desc_255Char(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "description").sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='createQuote']/div/table/tbody/tr[7]/td/input").click();

		String date = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_Quotes_softAssert.assertThat(date).isEqualTo(data.get(5).get("Column13"));

		WebdriverWait.findElement("id", "description").clear();
	}

	public static void Create_PO_Quotes(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "quoteName").sendKeys("Test");
		WebdriverWait.findElement("name", "partyId").sendKeys(data.get(11).get("Column5"));
		WebdriverWait.findElement("xpath", "//*[@id='createQuote']/div/table/tbody/tr[7]/td/input").click();
	}

	public static void PO_Quotes_assertions() {
		PO_Quotes_softAssert.assertAll();
	}

}
