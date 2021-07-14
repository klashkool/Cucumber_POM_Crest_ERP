package com.Facilities_Modules;

import org.openqa.selenium.WebElement;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Facilities_Shipments_Adjustments extends Base {

	public static void Adj() {

		WebdriverWait.findElement("link", "Adjustments").click();
		WebElement adjAvail = WebdriverWait.findElement("id", "adjustmentAvailable");

		if (adjAvail.isSelected()) {
			WebdriverWait.findElement("id", "adjustmentAvailable").click();
			WebdriverWait.findElement("xpath", "//*[@id='submitRow']/td/input").click();
			WebdriverWait.findElement("link", "Receive Inventory").click();
		} else {

			WebdriverWait.findElement("link", "Receive Inventory").click();
		}

	}

}
