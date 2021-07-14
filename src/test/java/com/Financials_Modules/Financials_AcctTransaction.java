package com.Financials_Modules;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Financials_AcctTransaction extends Base {

	public static Logger log = Logger.getLogger(Financials_AcctTransaction.class);

	public static void Acct_Trans_Val() {

		driver.findElement(By.linkText("FINANCIALS")).click();
		driver.findElement(By.linkText("Accounting Transaction")).click();

		List<WebElement> companies = WebdriverWait.findElements("xpath", "//*[@id= 'screenlet_1_col']/table/tbody/tr");
		System.out.println(companies.size());

		for (int a = 1; a <= companies.size(); a++) {

			String companiesName = WebdriverWait.findElement("xpath", "//*[@id= 'screenlet_1_col']/table/tbody/tr[" + a + "]").getText();
			System.out.println(companiesName);

			if (companiesName.equalsIgnoreCase("UIDAI Accounting")) {
				WebdriverWait.findElement("xpath", "//*[@id= 'screenlet_1_col']/table/tbody/tr[" + a + "]/td[2]/a").click();
				break;
			}
		}

	}
}