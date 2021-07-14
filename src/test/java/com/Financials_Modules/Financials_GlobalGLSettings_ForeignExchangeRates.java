package com.Financials_Modules;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Financials_GlobalGLSettings_ForeignExchangeRates extends Base {

	public static void ConfigureXchangeRates(List<HashMap<String, String>> data, int row, String exchangeRate) {

		WebdriverWait.findElement("link", "FINANCIALS").click();
		WebdriverWait.findElement("link", "Global GL Settings").click();
		WebdriverWait.findElement("link", "Foreign Exchange Rates").click();

		WebdriverWait.findElement("link", "Create Exchange Rates").click();

		Select fromcurrency_dd = new Select(WebdriverWait.findElement("id", "uomId"));
		fromcurrency_dd.selectByVisibleText("United States Dollar - USD");

		Select tocurrency_dd = new Select(WebdriverWait.findElement("id", "uomIdTo"));
		tocurrency_dd.selectByVisibleText("Indian Rupee - INR");

		WebdriverWait.findElement("id", "conversionFactor").sendKeys(data.get(row).get(exchangeRate));


		// // Adding Current Date(From Date)
		// SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy
		// HH:mm");
		// String Fromdate = (dateFormat.format(new Date()));
		// WebdriverWait.findElement("name",
		// "fromDate_i18n").sendKeys(Fromdate);

		WebdriverWait.findElement("id", "submitForm").click();

	}

}
