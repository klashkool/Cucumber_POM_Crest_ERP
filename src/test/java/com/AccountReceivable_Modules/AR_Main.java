package com.AccountReceivable_Modules;

import com.Utils.WebdriverWait;

public class AR_Main {
	public static void clickAR() {
		WebdriverWait.findElement("link", "ACCOUNTS RECEIVABLE").click();
	}
	
	public static void clickSalesInvoice() {
		WebdriverWait.findElement("link", "Sales Invoice").click();
	}
	
	public static void clickAR_Payment_Groups() {
		WebdriverWait.findElement("link", "AR Payment Groups").click();
	}
	
	public static void clickCredit_OR_DebitMemo() {
		WebdriverWait.findElement("link", "Credit/Debit Memo").click();
	}
	
	public static void clickCustomerAdjustment() {
		WebdriverWait.findElement("link", "Customer Adjustment").click();
	}
}
