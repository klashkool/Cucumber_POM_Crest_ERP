package com.Utils;

import java.util.List;

import org.openqa.selenium.WebElement;


public class Click extends Base {

	public static void CaptureErrorMsg(String locator, String path) {

		WebdriverWait.findElement(locator, path).click();

		boolean error = WebdriverWait.findElement("xpath", "//*[@id='content-messages'][@class='content-messages errorMessage']").isDisplayed();

		if (error == true) {

			List<WebElement> errorMsgList = WebdriverWait.findElements("xpath", "//*[@id='content-messages']/div/ul/li");

			for (int a = 1; a <= errorMsgList.size(); a++) {

				String errorMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[" + a + "]").getText();
				System.out.println("--------Error Message Displayed -------- " + errorMsg);
			}
		}
	}
}
