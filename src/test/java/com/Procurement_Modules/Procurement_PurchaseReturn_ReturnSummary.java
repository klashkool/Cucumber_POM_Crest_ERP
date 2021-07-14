package com.Procurement_Modules;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;

import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Procurement_PurchaseReturn_ReturnSummary extends Base {

	public static void PO_ReturnValue_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Return Summary").click();

		String returnQty = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form[1]/table/tbody[1]/tr[1]/td[5]").getText();
		Assert.assertEquals(data.get(21).get("Column9"), returnQty);

		String returnLineValue = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form[1]/table/tbody[1]/tr[1]/td[10]")
						.getText().replace("₹", "").replace(",", "");
		Assert.assertEquals(data.get(21).get("Column12"), returnLineValue);

		String returnCGSTValue = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form[1]/table/tbody[1]/tr[2]/td[9]")
						.getText().replace("₹", "").replace(",", "");
		Assert.assertEquals(data.get(21).get("Column16"), returnCGSTValue);

		String returnSGSTValue = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form[1]/table/tbody[1]/tr[3]/td[9]")
						.getText().replace("₹", "").replace(",", "");
		Assert.assertEquals(data.get(21).get("Column17"), returnSGSTValue);

		String returnTotalValue = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form[1]/table/tbody[2]/tr/td[3]").getText()
						.replace("₹", "").replace(",", "");
		Assert.assertEquals(data.get(21).get("Column19"), returnTotalValue);

		System.out.println("Return Qty & Values are Validated Successfully");
	}

	public static void CaptureReturnInvoiceID(List<HashMap<String, String>> data, String sheetname) {

		Procurement_PurchaseReturn.ProcurementLink();
		Procurement_PurchaseReturn.PurchaseReturnLink();

		WebdriverWait.findElement("id", "returnId").sendKeys(data.get(13).get("Column10"));
		WebdriverWait.findElement("xpath", "//*[@id='searchCriteria']/table/tbody[1]/tr[5]/td/input").click();

		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[2]/a").click();

		// Invoice ID link
		String invoiceID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody[2]/tr/td[2]/a").getText();
		ExcelWriter.writeExcelFile(sheetname, 15, 6, invoiceID);
	}

	public static void ReturnInvID_Link() {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody[2]/tr/td[2]/a").click();
	}


}


