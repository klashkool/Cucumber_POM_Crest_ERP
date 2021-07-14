package com.Procurement_Modules;

import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Procurement_PurchaseReturn_ReturnHeader extends Base {

	public static void Capture_ReturnID(String sheetname) {

		String returnId = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/table/tbody/tr[1]/td[2]").getText();
		ExcelWriter.writeExcelFile(sheetname, 15, 10, returnId);
	}

	public static void Update_ReturnHeader() {

		WebdriverWait.findElement("link", "Return Header").click();
		WebdriverWait.findElement("link", "Create return shipment").click();

		WebdriverWait.findElement("id", "createSubmit").click();

	}

}
