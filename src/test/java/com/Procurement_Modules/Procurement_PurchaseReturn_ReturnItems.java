package com.Procurement_Modules;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Procurement_PurchaseReturn_ReturnItems extends Base {

	public static void Update_ReturnItems(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Return Items").click();

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div/div[2]/form/table/tbody/tr[1]/td[2]/select/option");

		for (WebElement option : options) {
			if (option.getText().contains(data.get(13).get("Column4"))) {
		        option.click();
		        break;
		    }
		}

		WebdriverWait.findElement("xpath", "//*[@id= 'content-main-section']/div[1]/div[2]/form/table/tbody/tr[2]/td[2]/input").click();

		List<WebElement> Returntble = WebdriverWait.findElements("xpath",
						"//*[@id= 'content-main-section']/form/div[1]/div[2]/table/tbody/tr");

		for (int a = 2; a <= Returntble.size(); a++) {

			String prdID = WebdriverWait.findElement("xpath", "//*[@id= 'content-main-section']/form/div[1]/div[2]/table/tbody/tr[" + a + "]/td[1]")
							.getText();

			if (prdID.contains(data.get(0).get("Column2"))) {

				WebdriverWait.findElement("xpath", "//*[@id= 'content-main-section']/form/div[1]/div[2]/table/tbody/tr[" + a + "]/td[5]/input").clear();
				WebdriverWait.findElement("xpath", "//*[@id= 'content-main-section']/form/div[1]/div[2]/table/tbody/tr[" + a + "]/td[5]/input")
								.sendKeys(data.get(0).get("Column27"));

				Select reason_DD = new Select(WebdriverWait
								.findElement("xpath", "//*[@id= 'content-main-section']/form/div[1]/div[2]/table/tbody/tr[" + a + "]/td[8]/select"));
				reason_DD.selectByVisibleText("Defective Item");

				Select type_DD = new Select(WebdriverWait.findElement("xpath",
								"//*[@id= 'content-main-section']/form/div[1]/div[2]/table/tbody/tr[" + a + "]/td[9]/select"));
				type_DD.selectByVisibleText("Refund");

				Select itemStatus_DD = new Select(WebdriverWait.findElement("xpath",
								"//*[@id= 'content-main-section']/form/div[1]/div[2]/table/tbody/tr[" + a + "]/td[10]/select"));
				itemStatus_DD.selectByVisibleText("Returned");

				WebdriverWait.findElement("xpath", "//*[@id= 'content-main-section']/form/div[1]/div[2]/table/tbody/tr[" + a + "]/td[11]/input").click();
				break;
			}
		}

		// int j = 19;
		//
		// for (int b = 0; b <= 3; b++) {
		//
		// WebdriverWait.findElement("name", "_rowSubmit_o_" + j).click();
		// j++;
		// }
		WebdriverWait.findElement("id", "sub1").click();
		AcceptReturnBtn();
	}

	///////////////////// Negative Flow/////////////////////

	public static void AcceptReturnBtn() {

		// WebdriverWait.findElement("xpath", "//input[@value='Accept
		// Return']").click();
		WebdriverWait.findElement("link", "Accept Return").click();
	}

	public static void Capture_ReturnID(String sheetname) {

		String return_ID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form[2]/div/div[2]/table/tbody/tr[1]/td[2]").getText();
		ExcelWriter.writeExcelFile(sheetname, 15, 10, return_ID);
	}

}

