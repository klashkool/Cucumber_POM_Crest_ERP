package com.CRM;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class CRM_Main extends Base {
	public static void clickCRM() {
		WebdriverWait.findElement("link", "CRM").click();
	}
	public static void clickDashboard() {
		WebdriverWait.findElement("link", "DASHBOARD").click();
	}
	public static void clickLeads() {
		WebdriverWait.findElement("link", "Leads").click();
	}
	public static void clickAccounts() {
		WebdriverWait.findElement("link", "Accounts").click();
	}
	public static void clickContacts() {
		WebdriverWait.findElement("link", "Contacts").click();
	}
	public static void clickActivities() {
		WebdriverWait.findElement("link", "Activities").click();
	}
	public static void clickOpportunities() {
		WebdriverWait.findElement("link", "Opportunities").click();
	}
}
