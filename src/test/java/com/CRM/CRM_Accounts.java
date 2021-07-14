package com.CRM;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class CRM_Accounts extends Base {
	public static void clickCreateAccount() {
		WebdriverWait.findElement("link", "Create Account").click();
	}
}
