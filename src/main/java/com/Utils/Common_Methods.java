package com.Utils;

public class Common_Methods extends Base {

	public static void Val_Msg(int num) {

		String Val_Msg = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[num]").getText();
	}

}
