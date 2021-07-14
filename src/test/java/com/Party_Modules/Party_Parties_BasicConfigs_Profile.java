package com.Party_Modules;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;

import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Party_Parties_BasicConfigs_Profile {

	public static String billingAcct_bal_beforeSO;
	public static String billingAcct_bal_AfterSO;

	public static void Capture_BillingAcct_Bal_BeforeSO(String sheetname) {

		WebdriverWait.findElement("xpath", "//*[@id='partyPaymentMethod']//td//a").click();

		String availBal = WebdriverWait.findElement("xpath", "//*[@id='updateBillingAccount']//tr[6]//td[2]").getText().replace("₹", "").replace(",", "");
		ExcelWriter.writeExcelFile(sheetname, 15, 1, availBal);
	}

	public static void Val_BillingAcct_Bal_AfterSO(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='partyPaymentMethod']//td//a").click();

		String availBal = WebdriverWait.findElement("xpath", "//*[@id='updateBillingAccount']//tr[6]//td[2]").getText().replace("₹", "").replace(",", "");
		Assert.assertEquals(data.get(13).get("Column1"), availBal);
	}
}
