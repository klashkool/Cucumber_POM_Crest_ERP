package com.Procurement_Modules;

import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Procurement_RFQ_RFQHeader extends Base {

	public static void RFQ_ID_Capture(String sheetname) {

		WebdriverWait.findElement("link", "RFQ Header").click();
		String rfqNum = WebdriverWait.findElement("xpath",
				"//*[@id='content-main-section']/form[1]/div[1]/div[2]/table/tbody[1]/tr[1]/td[2]").getText();
		System.out.println("RFQ ID = " + rfqNum);
		ExcelWriter.writeExcelFile(sheetname, 15, 2, rfqNum);
	}

	public static void Update_QuoteInfo() {

		WebdriverWait.findElement("link", "Update Quote Info").click();
	}
}