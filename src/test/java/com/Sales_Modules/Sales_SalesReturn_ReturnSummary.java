package com.Sales_Modules;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;

import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Sales_SalesReturn_ReturnSummary extends Base{

	public static void click_ReturnSummaryLink() {
		WebdriverWait.findElement("link", "Return Summary").click();
	}

	public static void capture_SalesReturnInvoiceID(String sheetname) {
		String salesReturnId = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody[2]/tr/td[2]/a").getText();
		ExcelWriter.writeExcelFile(sheetname, 15, 6, salesReturnId);
	}

	public static void capture_SalesReturnShipmentID(String sheetname) {
		String salesReturnId = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody[2]/tr/td[1]/a").getText();
		ExcelWriter.writeExcelFile(sheetname, 15, 5, salesReturnId);
	}

	public static void click_ReturnShipmentLink() {
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody[2]/tr/td[1]/a").click();
	}

	public static void verifySalesReturnShipmentStatus(String status) {
		String getStatus = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[4]").getText();
	Assert.assertEquals(getStatus, status);
	}

	public static void verifySalesReturnShipmentQty(List<HashMap<String, String>> data) {
		String getStatus = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr/td[4]").getText();
		Assert.assertEquals(getStatus, data.get(0).get("Column27"));
	}

	public static void click_SalesReturnID() {
		WebdriverWait.findElement("xpath", "//*[@id='primaryReturnIdRow']/td[2]/a").click();
	}

	public static void click_SalesReturnInvoiceID() {
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody[2]/tr/td[2]/a").click();
	}

	public static void click_SO_ID() {
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form[1]/table/tbody[2]/tr[1]/td[1]/a").click();
	}

}
