package com.AccountsPayable_Modules;

import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AP_PurchaseInvoices_Header extends Base {

	public static void PI_Approve() throws InterruptedException {

		WebdriverWait.findElement("link", "Header").click();
		Thread.sleep(1000);

		// WebElement element = driver.findElement(By.id("referenceNumber"));
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].click();", element);

		// WebElement elem = driver.findElement(By.id("referenceNumber"));
		// String js = "arguments[0].style.height='auto';
		// arguments[0].style.visibility='visible';";
		//
		// ((JavascriptExecutor) driver).executeScript(js, elem);
		// elem.sendKeys("1234");

		WebdriverWait.findElement("id", "referenceNumber").sendKeys("1234");
		String partyID = WebdriverWait.findElement("id", "0_lookupId_partyIdFrom_lookupDescription").getText();

		if (partyID.contains("APPL-TN")) {

			Select tds_dd = new Select(WebdriverWait.findElement("id", "tdsId"));
			tds_dd.selectByVisibleText("FEES FOR PROFESSIONAL OR TECHNICAL SERVICES - 10.00%");
		}

		Select payTerm_dd = new Select(WebdriverWait.findElement("id", "agreementId"));
		payTerm_dd.selectByVisibleText("-Select-");
		WebdriverWait.findElement("link", "Update").click();
		WebdriverWait.findElement("link", "Approve").click();
	}

	public static void Approve_Post_AddtnlCostInvoice() {

		WebdriverWait.findElement("link", "Header").click();
		WebdriverWait.findElement("link", "Update").click();
		WebdriverWait.findElement("link", "Approve").click();
		WebdriverWait.findElement("link", "Post").click();
	}

}