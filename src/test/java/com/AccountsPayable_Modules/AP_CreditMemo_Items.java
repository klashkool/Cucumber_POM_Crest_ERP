package com.AccountsPayable_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AP_CreditMemo_Items extends Base {

	@Rule
	public static JUnitSoftAssertions AP_CreditMemoItems_softAssert = new JUnitSoftAssertions();

	////////////////////////////////////// Negative
	////////////////////////////////////// Flow///////////////////////////////////////

	public static void CreditMemo_ItemsLink() {

		WebdriverWait.findElement("link", "Items").click();
	}

	public static void MandatoryFields_Val(List<HashMap<String, String>> data) {

		Select invType_dd = new Select(WebdriverWait.findElement("id", "invoiceItemTypeId"));
		invType_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("name", "submitTerm").click();

		String invType = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String amt = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String dept = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();

		AP_CreditMemoItems_softAssert.assertThat(invType).isEqualTo(data.get(19).get("Column21"));
		AP_CreditMemoItems_softAssert.assertThat(amt).isEqualTo(data.get(16).get("Column12"));
		AP_CreditMemoItems_softAssert.assertThat(dept).isEqualTo(data.get(2).get("Column9"));
	}

	public static void GL_Account_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "amount").sendKeys("5000");

		Select dept_dd = new Select(WebdriverWait.findElement("id", "partyRole"));
		dept_dd.selectByVisibleText("UIDAI - Purchase Dept");

		WebdriverWait.findElement("name", "submitTerm").click();

		String GL = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_CreditMemoItems_softAssert.assertThat(GL).isEqualTo(data.get(24).get("Column21"));
	}

	public static void InvalidAmt(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "amount").clear();

		WebdriverWait.findElement("id", "amount").sendKeys(data.get(1).get("Column1"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "partyRole"));
		dept_dd.selectByVisibleText("UIDAI - Purchase Dept");

		Select GL_dd = new Select(WebdriverWait.findElement("id", "overrideGlAccountId"));
		GL_dd.selectByVisibleText("212000 - ACCOUNTS PAYABLE - OPERATING [212000]");

		WebdriverWait.findElement("name", "submitTerm").click();

		String negamt = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_CreditMemoItems_softAssert.assertThat(negamt).isEqualTo(data.get(17).get("Column12"));

		// spl charc
		WebdriverWait.findElement("id", "amount").clear();
		WebdriverWait.findElement("id", "amount").sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "submitTerm").click();
		String splchar = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_CreditMemoItems_softAssert.assertThat(splchar).isEqualTo(data.get(18).get("Column12"));

		// 0 value
		WebdriverWait.findElement("id", "amount").clear();
		WebdriverWait.findElement("id", "amount").sendKeys(data.get(3).get("Column1"));
		WebdriverWait.findElement("name", "submitTerm").click();
		String zeroval = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_CreditMemoItems_softAssert.assertThat(zeroval).isEqualTo(data.get(17).get("Column12"));
	}

	public static void desc_255CharacVal(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "amount").clear();
		WebdriverWait.findElement("id", "amount").sendKeys(data.get(4).get("Column1"));

		WebdriverWait.findElement("id", "description").sendKeys(data.get(6).get("Column1"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "partyRole"));
		dept_dd.selectByVisibleText("UIDAI - Purchase Dept");

		Select GL_dd = new Select(WebdriverWait.findElement("id", "overrideGlAccountId"));
		GL_dd.selectByVisibleText("212000 - ACCOUNTS PAYABLE - OPERATING [212000]");

		WebdriverWait.findElement("name", "submitTerm").click();

		String desc = WebdriverWait.findElement("xpath", "//*[@id='updateInvoiceItem']/table/tbody/tr[1]/td[3]").getText();
		desc.length();

		AP_CreditMemoItems_softAssert.assertThat(desc.length()).isEqualTo(255);
	}

	public static void InvalidAmt_CreditMemoItemDetails(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "amount_o_1").clear();
		WebdriverWait.findElement("id", "amount_o_1").sendKeys(data.get(1).get("Column1"));
		WebdriverWait.findElement("name", "update").click();
		String negamt = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_CreditMemoItems_softAssert.assertThat(negamt).isEqualTo(data.get(17).get("Column12"));

		// spl charc
		WebdriverWait.findElement("id", "amount_o_1").clear();
		WebdriverWait.findElement("id", "amount_o_1").sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "update").click();
		String splchar = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_CreditMemoItems_softAssert.assertThat(splchar).isEqualTo(data.get(18).get("Column12"));

		// 0 value
		WebdriverWait.findElement("id", "amount_o_1").clear();
		WebdriverWait.findElement("id", "amount_o_1").sendKeys(data.get(3).get("Column1"));
		WebdriverWait.findElement("name", "update").click();
		String zeroval = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_CreditMemoItems_softAssert.assertThat(zeroval).isEqualTo(data.get(17).get("Column12"));

		// empty value
		WebdriverWait.findElement("id", "amount_o_1").clear();
		WebdriverWait.findElement("name", "update").click();
		String emptyval = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_CreditMemoItems_softAssert.assertThat(emptyval).isEqualTo(data.get(16).get("Column12"));

	}

	public static void remove_CreditMemoItemDetails() {

		WebdriverWait.findElement("link", "Remove").click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

	public static void Void_CreditMemo(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Void").click();

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/table/tbody[1]/tr[2]/td/input").click();

		String voidReason = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		AP_CreditMemoItems_softAssert.assertThat(voidReason).isEqualTo(data.get(21).get("Column21"));

	}

	public static void AP_CreditMemoItems_softAssert() {
		AP_CreditMemoItems_softAssert.assertAll();
	}

}
