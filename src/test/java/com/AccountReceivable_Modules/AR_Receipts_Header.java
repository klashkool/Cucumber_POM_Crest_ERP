package com.AccountReceivable_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.support.ui.Select;

import com.Sales_Modules.Sales_SalesOrder;
import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class AR_Receipts_Header extends Base {
	public static Logger log = Logger.getLogger(Sales_SalesOrder.class);

	@Rule
	public static JUnitSoftAssertions AR_ReceiptsHeader_softAssert = new JUnitSoftAssertions();
	public static void ReceiptIDCapture(String sheetname, int reciptID_Row) {

		String PayID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/table/tbody[1]/tr[1]/td[2]").getText();
		ExcelWriter.writeExcelFile(sheetname, reciptID_Row, 10, PayID);
	}

	public static void ReceiptStatus_Val() throws InterruptedException {

		Thread.sleep(500);
		String status = WebdriverWait.findElement("xapth", "//*[@id='content-main-section']/div[2]/div[2]/form/table/tbody[1]/tr[2]/td[4]").getText();
		Assert.assertEquals("Created", status);

	}

	///////////////////////////////////////Negative flows////////////////////////////////////////////
	public static void clickUpdateBtn() {
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form/div/input").click();
	}

	public static void Validate_Cancel_button_navigating_to_SalesInvoiceSearchPage() {
		WebdriverWait.findElement("link", "Cancel").click();
		String verifySearchPageIsDisplayed = driver.getTitle();
		AR_ReceiptsHeader_softAssert.assertThat(verifySearchPageIsDisplayed).isEqualTo("Crest: Edit Receipt");
	}

	public static void Validate_by_updating_RefNo_with_255Characters(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		WebdriverWait.findElement("id", "paymentRefNum").sendKeys(data.get(6).get("Column1"));
		clickUpdateBtn();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_ReceiptsHeader_softAssert.assertThat(validationMsg).isEqualTo("Yet to add the validation message since the appln throwing exception.");
	}

	public static void Validate_by_updating_comments_with_255Characters(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		WebdriverWait.findElement("id", "comments").sendKeys(data.get(6).get("Column1"));
		clickUpdateBtn();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_ReceiptsHeader_softAssert.assertThat(validationMsg).isEqualTo("The maximum limit for Comment is 255 characters.");
	}

	public static void Validate_by_updating_Amount_with_InvalidData(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		Select paymentTypeId_dd = new Select(WebdriverWait.findElement("id", "paymentTypeId"));
		paymentTypeId_dd.selectByVisibleText("Customer Deposit");
		Select paymentMethodId_dd = new Select(WebdriverWait.findElement("id", "paymentMethodId"));
		paymentMethodId_dd.selectByIndex(1);
		WebdriverWait.findElement("id", "0_lookupId_partyIdFrom").sendKeys(data_Positive.get(0).get("Column4"));
		WebdriverWait.findElement("id", "amount").clear();
		WebdriverWait.findElement("id", "paymentRefNum").sendKeys("Test");
		for (int i = 0; i <= 6; i++) {
			if(i==4) {
				WebdriverWait.findElement("id", "amount").clear();
			}else if(i==6) {
				WebdriverWait.findElement("id", "amount").sendKeys(data.get(i+10).get("Column1"));
			}else {
			WebdriverWait.findElement("id", "amount").sendKeys(data.get(i).get("Column1"));
			}
			//Click Update button
			WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form/div/input").click();
			if(i==1||i==3){
				String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				AR_ReceiptsHeader_softAssert.assertThat(validationMsg).isEqualTo(data.get(14).get("Column12"));
			}if(i==4) {
				String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				AR_ReceiptsHeader_softAssert.assertThat(validationMsg).isEqualTo(data.get(13).get("Column12"));

			}else {
				String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				AR_ReceiptsHeader_softAssert.assertThat(validationMsg).isEqualTo(data.get(12).get("Column12"));

			}
		}
	}

	public static void Validate_by_updating_PartyID_with_InvalidData(List<HashMap<String, String>> data, String col, int msg_row,
			String colName) {
		WebdriverWait.findElement("id", "0_lookupId_partyIdFrom").clear();
		WebdriverWait.findElement("id", "paymentRefNum").sendKeys("Test");
		for (int i = 0; i <= 6; i++) {
			WebdriverWait.findElement("id", "0_lookupId_partyIdFrom").clear();
			if(i==3) {
				WebdriverWait.findElement("id", "0_lookupId_partyIdFrom").clear();
			}else {
			WebdriverWait.findElement("id", "0_lookupId_partyIdFrom").sendKeys(data.get(i).get(col));
			}
			clickUpdateBtn();
			if(i==3) {
				verify_PartyID_Validation_Msg_for_LeavingEmpty(data);
			}else {
				verify_PartyID_Validation_Msg_for_InvalidData(data, msg_row, colName);
			}
		}
	}

	public static String verify_PartyID_Validation_Msg_for_InvalidData(List<HashMap<String, String>> data, int row, String colName) {
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_ReceiptsHeader_softAssert.assertThat(validationMsg).isEqualTo(data.get(row).get(colName));
		return validationMsg;
	}

	public static String verify_PartyID_Validation_Msg_for_LeavingEmpty(List<HashMap<String, String>> data) {
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_ReceiptsHeader_softAssert.assertThat(validationMsg).isEqualTo(data.get(15).get("Column5"));
		return validationMsg;
	}

	public static void AR_ReceiptsHeader_softAssert() {
		AR_ReceiptsHeader_softAssert.assertAll();
	}
}