package com.AccountReceivable_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.support.ui.Select;

import com.Sales_Modules.Sales_SalesOrder;
import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AR_SalesInvoice_Content extends Base {
	public static Logger log = Logger.getLogger(Sales_SalesOrder.class);

	@Rule
	public static JUnitSoftAssertions SI_Content_softAssert = new JUnitSoftAssertions();
	
	////////////////////////////////////////Negative flow//////////////////////////////////////////////////
	
	public static void clickContentTab() {
		WebdriverWait.findElement("link", "Content").click();
	}
	
	public static void validate_ContentTab_without_mandatory_fields(List<HashMap<String, String>> data) {
		clickContentTab();
		WebdriverWait.findElement("xpath", "//*[@id='createInvoiceContent']/table/tbody[1]/tr[5]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		SI_Content_softAssert.assertThat(validationMsg).isEqualTo(data.get(17).get("Column21"));
		String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
		SI_Content_softAssert.assertThat(validationMsg1).isEqualTo(data.get(12).get("Column6"));
	}
	
	public static void validate_fileUpload_with_sameFile_twice(List<HashMap<String, String>> data) {
		clickContentTab();
		for(int i=0; i<=1; i++) {
			Select contentType = new Select(WebdriverWait.findElement("id", "invoiceContentTypeId"));
			contentType.selectByVisibleText("comments");
			WebdriverWait.findElement("id", "dataResourceName").sendKeys("D:\\Projects\\CrestCucumber\\src\\main\\java\\com\\Utils\\TestFileUpload_Word.docx");
			WebdriverWait.findElement("xpath", "//*[@id='createInvoiceContent']/table/tbody[1]/tr[5]/td/input").click();
		}
		boolean isSuccessMsgDisplayed = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").isDisplayed();
		SI_Content_softAssert.assertThat(isSuccessMsgDisplayed).isEqualTo(false);
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SI_Content_softAssert.assertThat(validationMsg).isEqualTo("yet to add validation msg since appln is accepting same file twice");
	}
	
	public static void validate_fileUpload_with_211MB(List<HashMap<String, String>> data) {
		clickContentTab();
			Select contentType = new Select(WebdriverWait.findElement("id", "invoiceContentTypeId"));
			contentType.selectByVisibleText("comments");
			WebdriverWait.findElement("id", "dataResourceName").sendKeys("D:\\Projects\\CrestCucumber\\src\\main\\java\\com\\Utils\\TestFileUpload_JDK_211MB.exe");
			WebdriverWait.findElement("xpath", "//*[@id='createInvoiceContent']/table/tbody[1]/tr[5]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SI_Content_softAssert.assertThat(validationMsg).isEqualTo("yet to add validation msg since appln is throwing exception");
	}
	
	public static void validate_fileUpload_with_valid_fileType(List<HashMap<String, String>> data) {
		clickContentTab();
		for(int i=31; i<=5; i++) {
		Select contentType = new Select(WebdriverWait.findElement("id", "invoiceContentTypeId"));
		contentType.selectByVisibleText("comments");
		WebdriverWait.findElement("id", "dataResourceName").sendKeys("D:\\Projects\\CrestCucumber\\src\\main\\java\\com\\Utils\\"+data.get(i).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='createInvoiceContent']/table/tbody[1]/tr[5]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		SI_Content_softAssert.assertThat(validationMsg).isEqualTo(data.get(20).get("Column17"));
	}}
	
	public static void validate_fileUpload_with_invalid_fileType(List<HashMap<String, String>> data) {
		clickContentTab();
		for(int i=36; i<=3; i++) {
		Select contentType = new Select(WebdriverWait.findElement("id", "invoiceContentTypeId"));
		contentType.selectByVisibleText("comments");
		WebdriverWait.findElement("id", "dataResourceName").sendKeys("D:\\Projects\\CrestCucumber\\src\\main\\java\\com\\Utils\\"+data.get(i).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='createInvoiceContent']/table/tbody[1]/tr[5]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SI_Content_softAssert.assertThat(validationMsg).isEqualTo("yet to add validation msg since appln is accepting the file format "+data.get(i).get("Column1"));
		}
		}
	
	public static void validate_fileUpload_with_250characters_in_description(List<HashMap<String, String>> data) {
		clickContentTab();
			Select contentType = new Select(WebdriverWait.findElement("id", "invoiceContentTypeId"));
			contentType.selectByVisibleText("comments");
			WebdriverWait.findElement("id", "description").sendKeys(data.get(6).get("Column1"));
			WebdriverWait.findElement("id", "dataResourceName").sendKeys("D:\\Projects\\CrestCucumber\\src\\main\\java\\com\\Utils\\TestFileUpload_Word.docx");
			WebdriverWait.findElement("xpath", "//*[@id='createInvoiceContent']/table/tbody[1]/tr[5]/td/input").click();
			String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
			SI_Content_softAssert.assertThat(validationMsg).isEqualTo(data.get(20).get("Column17"));
			String getDescriptionCount = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr/td[2]").getText();
			SI_Content_softAssert.assertThat(getDescriptionCount.length()).isEqualTo("250");
			
	}
	
	public static void Validate_lineItem_isExist_by_cancelling_confirmation_Alert(List<HashMap<String, String>> data) {
		clickContentTab();
		WebdriverWait.findElement("link", "Remove").click();
		driver.switchTo().alert().dismiss();
			boolean isLineItemDisplayed = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr/td[1]").isDisplayed();
			SI_Content_softAssert.assertThat(isLineItemDisplayed).isEqualTo(true);
	}

	public static void Validate_lineItem_isExist_by_Accepting_confirmation_Alert(List<HashMap<String, String>> data) {
		clickContentTab();
		WebdriverWait.findElement("link", "Remove").click();
		driver.switchTo().alert().accept();
			String getNoRecordsFound = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr/td").getText();
			SI_Content_softAssert.assertThat(getNoRecordsFound).isEqualTo("No Records Found");
	}
	public static void SI_Content_softAssert() {
		SI_Content_softAssert.assertAll();
	}
}
