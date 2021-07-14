package com.AccountsPayable_Modules;

import org.junit.Assert;

import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class AP_Payments_Header extends Base {

	public static void PaymentIDCapture(String sheetname, int row) {

		String PayID = WebdriverWait
				.findElement("xpath", "//*[@id='content-main-section']/div/div[2]/form/table/tbody[1]/tr[1]/td[2]")
				.getText();
		ExcelWriter.writeExcelFile(sheetname, row, 10, PayID);
	}

	public static void PaymentStatus_Val() {

		String status = WebdriverWait.findElement("xapth", "//*[@id='content-main-section']/div/div[2]/form/table/tbody[1]/tr[1]/td[4]").getText();
		Assert.assertEquals("Created", status);

	}

}
