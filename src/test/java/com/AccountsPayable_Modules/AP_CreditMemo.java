package com.AccountsPayable_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AP_CreditMemo extends Base {

	@Rule
	public static JUnitSoftAssertions AP_CreditMemo_softAssert = new JUnitSoftAssertions();

	@Rule
	public static JUnitSoftAssertions AP_CreditMemoSearch_softAssert = new JUnitSoftAssertions();

	//////////////////////////////// Negative Flow/////////////////////////////

	public static void AccountsPayableLink() {

		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
	}

	public static void CreditMemoLink() {

		WebdriverWait.findElement("link", "Credit/Debit Memo").click();
	}

	public static void CreateCreditMemoLink() {

		WebdriverWait.findElement("link", "Create Credit/Debit Memo").click();
	}

	public static void InvalidDates_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "fromInvoiceDate_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("id", "thruInvoiceDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='searchCriteria']/table/tbody/tr[6]/td/input").click();
		String memoDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_CreditMemoSearch_softAssert.assertThat(memoDate).isEqualTo(data.get(27).get("Column2"));

	}

	public static void MandatoryFields_Val(List<HashMap<String, String>> data) {

		Select docType_dd = new Select(WebdriverWait.findElement("id", "invoiceTypeId"));
		docType_dd.selectByVisibleText("-Select-");

		Select org_dd = new Select(WebdriverWait.findElement("id", "organizationPartyId"));
		org_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("id", "invoiceDate_i18n").clear();

		WebdriverWait.findElement("xpath", "//*[@id= 'createCreditMemo']/center/input").click();

		String docType = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String org = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String partyID = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();
		String cmDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[4]").getText();
		String refNo = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[5]").getText();
		String currency = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[6]").getText();
		String numType = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[7]").getText();

		AP_CreditMemo_softAssert.assertThat(docType).isEqualTo(data.get(22).get("Column21"));
		AP_CreditMemo_softAssert.assertThat(org).isEqualTo(data.get(2).get("Column4"));
		AP_CreditMemo_softAssert.assertThat(cmDate).isEqualTo(data.get(28).get("Column2"));
		AP_CreditMemo_softAssert.assertThat(partyID).isEqualTo(data.get(6).get("Column5"));
		AP_CreditMemo_softAssert.assertThat(refNo).isEqualTo(data.get(23).get("Column21"));
		AP_CreditMemo_softAssert.assertThat(currency).isEqualTo(data.get(2).get("Column11"));
		AP_CreditMemo_softAssert.assertThat(numType).isEqualTo(data.get(6).get("Column6"));
	}

	public static void Refno_255CharacVal(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "partyIdTo").sendKeys("11680");

		WebdriverWait.findElement("id", "referenceNumber").sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id= 'createCreditMemo']/center/input").click();

		String charc = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_CreditMemo_softAssert.assertThat(charc).isEqualTo(data.get(7).get("Column13"));

		WebdriverWait.findElement("id", "referenceNumber").clear();

	}

	public static void desc_255CharcVal(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "referenceNumber").sendKeys("1234");

		WebdriverWait.findElement("id", "description").sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id= 'createCreditMemo']/center/input").click();

		String desc = WebdriverWait.findElement("id", "description").getText();
		desc.length();

		AP_CreditMemo_softAssert.assertThat(desc.length()).isEqualTo(255);
	}

	public static void Create_CreditMemo() {

		WebdriverWait.findElement("name", "partyIdTo").sendKeys("11680");
		WebdriverWait.findElement("id", "referenceNumber").sendKeys("1234");
		WebdriverWait.findElement("xpath", "//*[@id='createCreditMemo']/center/input").click();
	}

	public static void AP_CreditMemo_softAssert() {
		AP_CreditMemo_softAssert.assertAll();
	}

	public static void AP_CreditMemoSearch_softAssert() {
		AP_CreditMemoSearch_softAssert.assertAll();
	}
}
