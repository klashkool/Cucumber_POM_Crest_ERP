package com.Sales_Modules;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Sales_SalesReturn_ReturnHeader extends Base {
	@Rule
	public static JUnitSoftAssertions Sales_ReturnHeader_softAssert = new JUnitSoftAssertions();
	public static void Capture_ReturnID(String sheetname) {

		String returnId = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/table/tbody/tr[1]/td[2]").getText();
		ExcelWriter.writeExcelFile(sheetname, 15, 8, returnId);
	}
	
	public static void Capture_SalesReturnID(String sheetname) {
		String salesReturnId = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/table/tbody/tr[1]/td[2]").getText();
		ExcelWriter.writeExcelFile(sheetname, 15, 10, salesReturnId);
	}
	
	public static void click_ReceiveSalesReturnLink() {
		WebdriverWait.findElement("link", "Receive Sales Return").click();
	}
	
	public static void verifySalesReturnStatus(String status) {
	String getStatus =	WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr[2]/td[4]").getText();
	Assert.assertEquals(getStatus, status);
	}

	public static void Update_ReturnHeader() {

		Select status_dd = new Select(WebdriverWait.findElement("name", "statusId"));
		status_dd.selectByVisibleText("Received");
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/div/input").click();

		Select status1_dd = new Select(WebdriverWait.findElement("name", "statusId"));
		status1_dd.selectByVisibleText("Completed");
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/div/input").click();

		String returnStatus = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr[2]/td[4]").getText();
		Assert.assertEquals("Completed", returnStatus);

	}
	
	public static void Sales_ReturnHeader_softAssert() {
		Sales_ReturnHeader_softAssert.assertAll();
	}

}
