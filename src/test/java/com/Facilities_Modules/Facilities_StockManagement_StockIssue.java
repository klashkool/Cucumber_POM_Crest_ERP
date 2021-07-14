package com.Facilities_Modules;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;
public class Facilities_StockManagement_StockIssue extends Base {

	public static void CreateStockIssue(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "FACILITIES").click();
		WebdriverWait.findElement("link", "Stock Management").click();

		for (int p = 44; p <= 48; p++) {

			WebdriverWait.findElement("link", "Stock Issue").click();
			WebdriverWait.findElement("link", "Create Stock Issue").click();

			Select docType = new Select(WebdriverWait.findElement("id", "type"));
			docType.selectByVisibleText("Unplanned");

			Select facility = new Select(WebdriverWait.findElement("id", "facilityId"));
			facility.selectByVisibleText(data.get(0).get("Column7"));

			WebdriverWait.findElement("name", "productId").sendKeys(data.get(p).get("Column2"));
			WebdriverWait.findElement("id", "searchSubmitButton").click();
			WebdriverWait.findElement("id", "transactionQty_o_0").sendKeys(data.get(p).get("Column1"));

			Select issueReason = new Select(WebdriverWait.findElement("id", "varianceReasonId"));
			issueReason.selectByVisibleText("Other Issue");

			Select dept = new Select(WebdriverWait.findElement("id", "partyId"));
			dept.selectByVisibleText(data.get(0).get("Column8"));

			WebdriverWait.findElement("id", "stockIssueReceiptIds").click();

			WebdriverWait.findElement("id", "submitButton").click();

			WebdriverWait.findElement("link", "Approve").click();

			// Alert alert = driver.switchTo().alert();
			// alert.accept();


		}

	}

}