package com.AccountsPayable_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class AP_PurchaseInvoices_Overview extends Base {

	@Rule
	public static JUnitSoftAssertions PI_View_softAssert = new JUnitSoftAssertions();
	public static JUnitSoftAssertions PI_View_OutstandingAmt_softAssert = new JUnitSoftAssertions();

	public static void PI_Post() throws InterruptedException {

		String partyName = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody[1]/tr[2]/td[4]").getText();
		System.out.println(partyName);
		if (partyName.contains("APPL-TN")) {
			WebdriverWait.findElement("link", "Post").click();
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} else {
			WebdriverWait.findElement("link", "Post").click();

			String InvID_Status = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody[1]/tr[7]/td[2]").getText();
			Assert.assertEquals("Posted", InvID_Status);
		}
	}
	public static void PI_Inv1_SubTotal_Validations(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating SubTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String subTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (subTotal.contains("Sub Total (Item base Price)")) {

				String SubTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");

				// Assert.assertEquals(data.get(row).get(col), SubTotal);

				PI_View_softAssert.assertThat(SubTotal).isEqualTo(data.get(row).get(col));
				System.out.println("PI Sub Total validated Successfully");
			}
		}
	}

	public static void PI_Inv1_Tax_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating Total Tax");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();
			System.out.println(totalTax);

			if (totalTax.contains("Tax Amount (Consolidated)")) {

				String TotalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");
				PI_View_softAssert.assertThat(TotalTax).isEqualTo(data.get(row).get(col));
				log.info("PI Tax Amt validated Successfully");
				System.out.println("PI Tax Amt validated Successfully");
			}
		}
	}

	public static void PI_Inv1_GrdTotal_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating GrandTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String grandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (grandTotal.contains("Grand Total")) {

				String GrandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[3]")
								.getText().replace(",", "");
				PI_View_softAssert.assertThat(GrandTotal).isEqualTo(data.get(row).get(col));
				log.info("PI Grand Total validated Successfully");
				System.out.println("PI Grand Total validated Successfully");
			}
		}
	}

	public static void PI_Inv2_SubTotal_Validations(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating SubTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String subTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (subTotal.contains("Sub Total (Item base Price)")) {

				String SubTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");

				// Assert.assertEquals(data.get(row).get(col), SubTotal);

				PI_View_softAssert.assertThat(SubTotal).isEqualTo(data.get(row).get(col));
				System.out.println("PI Sub Total validated Successfully");
			}
		}
	}

	public static void PI_Inv2_Tax_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating Total Tax");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();
			System.out.println(totalTax);

			if (totalTax.contains("Tax Amount (Consolidated)")) {

				String TotalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");
				PI_View_softAssert.assertThat(TotalTax).isEqualTo(data.get(row).get(col));
				log.info("PI Tax Amt validated Successfully");
				System.out.println("PI Tax Amt validated Successfully");
			}
		}
	}

	public static void PI_Inv2_GrdTotal_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating GrandTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String grandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (grandTotal.contains("Grand Total")) {

				String GrandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[3]")
								.getText().replace(",", "");
				PI_View_softAssert.assertThat(GrandTotal).isEqualTo(data.get(row).get(col));
				log.info("PI Grand Total validated Successfully");
				System.out.println("PI Grand Total validated Successfully");
			}
		}
	}

	public static void PI_Inv3_SubTotal_Validations(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating SubTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String subTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (subTotal.contains("Sub Total (Item base Price)")) {

				String SubTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");

				// Assert.assertEquals(data.get(row).get(col), SubTotal);

				PI_View_softAssert.assertThat(SubTotal).isEqualTo(data.get(row).get(col));
				System.out.println("PI Sub Total validated Successfully");
			}
		}
	}

	public static void PI_Inv3_Tax_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating Total Tax");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();
			System.out.println(totalTax);

			if (totalTax.contains("Tax Amount (Consolidated)")) {

				String TotalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");
				PI_View_softAssert.assertThat(TotalTax).isEqualTo(data.get(row).get(col));
				log.info("PI Tax Amt validated Successfully");
				System.out.println("PI Tax Amt validated Successfully");
			}
		}
	}

	public static void PI_Inv3_GrdTotal_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating GrandTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String grandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (grandTotal.contains("Grand Total")) {

				String GrandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[3]")
								.getText().replace(",", "");
				PI_View_softAssert.assertThat(GrandTotal).isEqualTo(data.get(row).get(col));
				log.info("PI Grand Total validated Successfully");
				System.out.println("PI Grand Total validated Successfully");
			}
		}
	}

	public static void Inv1_HeaderAmt_Val(List<HashMap<String, String>> data) {

		String headerAmt = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody[1]/tr[4]/td[2]").getText().replace(",",
						"");

		PI_View_softAssert.assertThat(headerAmt).isEqualTo(data.get(13).get("Column9"));

	}

	public static void Inv2_HeaderAmt_Val(List<HashMap<String, String>> data) {

		String headerAmt = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody[1]/tr[4]/td[2]").getText().replace(",",
						"");

		PI_View_softAssert.assertThat(headerAmt).isEqualTo(data.get(14).get("Column9"));

	}

	public static void Inv3_HeaderAmt_Val(List<HashMap<String, String>> data) {

		String headerAmt = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody[1]/tr[4]/td[2]").getText().replace(",",
						"");

		PI_View_softAssert.assertThat(headerAmt).isEqualTo(data.get(15).get("Column9"));

	}

	public static void PI_Inv1_ThirdPartyTax_Table_Val(List<HashMap<String, String>> data, String col) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[1]").click();// 3rd
																												// party
																												// Taxes
																												// Button

		List<WebElement> thirdPartyTaxTable = WebdriverWait.findElements("xpath", "//*[@id='tab_tab1_tax']/table/tbody/tr");
		int b = thirdPartyTaxTable.size();

		String thirdPartyTotalTax = WebdriverWait.findElement("xpath", "//*[@id='tab_tab1_tax']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",", "");
		PI_View_softAssert.assertThat(thirdPartyTotalTax).isEqualTo(data.get(13).get(col));
		System.out.println("Third Party Tax Table validated successfully");
	}

	public static void PI_Inv2_ThirdPartyTax_Table_Val(List<HashMap<String, String>> data, String col) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[1]").click();// 3rd
																												// party
																												// Taxes
																												// Button

		List<WebElement> thirdPartyTaxTable = WebdriverWait.findElements("xpath", "//*[@id='tab_tab1_tax']/table/tbody/tr");
		int b = thirdPartyTaxTable.size();

		String thirdPartyTotalTax = WebdriverWait.findElement("xpath", "//*[@id='tab_tab1_tax']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",", "");
		PI_View_softAssert.assertThat(thirdPartyTotalTax).isEqualTo(data.get(14).get(col));
		System.out.println("Third Party Tax Table validated successfully");
	}

	public static void PI_Inv3_ThirdPartyTax_Table_Val(List<HashMap<String, String>> data, String col) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[1]").click();// 3rd
																												// party
																												// Taxes
																												// Button

		List<WebElement> thirdPartyTaxTable = WebdriverWait.findElements("xpath", "//*[@id='tab_tab1_tax']/table/tbody/tr");
		int b = thirdPartyTaxTable.size();

		String thirdPartyTotalTax = WebdriverWait.findElement("xpath", "//*[@id='tab_tab1_tax']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",", "");
		PI_View_softAssert.assertThat(thirdPartyTotalTax).isEqualTo(data.get(15).get(col));
		System.out.println("Third Party Tax Table validated successfully");
	}

	public static void PI_Inv1_ThirdPartyCharges_Table_Val(List<HashMap<String, String>> data, String col) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[2]").click();// 3rd
																												// party
																												// Charges
																												// Button

		List<WebElement> thirdPartyChargesTable = WebdriverWait.findElements("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr");
		int b = thirdPartyChargesTable.size();

		String thirdPartyTotalCharges = WebdriverWait.findElement("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",",
						"");
		PI_View_softAssert.assertThat(thirdPartyTotalCharges).isEqualTo(data.get(15).get(col));
		System.out.println("Third Party Charges Table validated successfully");

	}

	public static void PI_Inv2_ThirdPartyCharges_Table_Val(List<HashMap<String, String>> data, String col) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[2]").click();// 3rd
																												// party
																												// Charges
																												// Button

		List<WebElement> thirdPartyChargesTable = WebdriverWait.findElements("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr");
		int b = thirdPartyChargesTable.size();

		String thirdPartyTotalCharges = WebdriverWait.findElement("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",",
						"");
		PI_View_softAssert.assertThat(thirdPartyTotalCharges).isEqualTo(data.get(15).get(col));
		System.out.println("Third Party Charges Table validated successfully");

	}

	public static void PI_Inv3_ThirdPartyCharges_Table_Val(List<HashMap<String, String>> data, String col) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[2]").click();// 3rd
																												// party
																												// Charges
																												// Button

		List<WebElement> thirdPartyChargesTable = WebdriverWait.findElements("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr");
		int b = thirdPartyChargesTable.size();

		String thirdPartyTotalCharges = WebdriverWait.findElement("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",",
						"");
		PI_View_softAssert.assertThat(thirdPartyTotalCharges).isEqualTo(data.get(15).get(col));
		System.out.println("Third Party Charges Table validated successfully");

	}

	public static void PI_Inv1_TaxBreakup_Table_Val(List<HashMap<String, String>> data) {

		List<WebElement> taxBreakupTable = WebdriverWait.findElements("xpath", "//*[@id='taxContainer']/table/tbody/tr");
		int b = taxBreakupTable.size();

		String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]").getText().replace(",", "");
		PI_View_softAssert.assertThat(totalTax).isEqualTo(data.get(13).get("Column8"));

		System.out.println("Tax Breakup Table Validated Successfully");
	}

	public static void PI_Inv2_TaxBreakup_Table_Val(List<HashMap<String, String>> data) {

		List<WebElement> taxBreakupTable = WebdriverWait.findElements("xpath", "//*[@id='taxContainer']/table/tbody/tr");
		int b = taxBreakupTable.size();

		String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]").getText().replace(",", "");
		PI_View_softAssert.assertThat(totalTax).isEqualTo(data.get(14).get("Column8"));

		System.out.println("Tax Breakup Table Validated Successfully");
	}

	public static void PI_Inv3_TaxBreakup_Table_Val(List<HashMap<String, String>> data) {

		List<WebElement> taxBreakupTable = WebdriverWait.findElements("xpath", "//*[@id='taxContainer']/table/tbody/tr");
		int b = taxBreakupTable.size();

		String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]").getText().replace(",", "");
		PI_View_softAssert.assertThat(totalTax).isEqualTo(data.get(15).get("Column8"));

		System.out.println("Tax Breakup Table Validated Successfully");
	}

	public static void Inv1_OutstandingAmt_Val(List<HashMap<String, String>> data) throws InterruptedException {

		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
		WebdriverWait.findElement("link", "Purchase Invoices").click();
		WebdriverWait.findElement("id", "invoiceId").sendKeys(data.get(13).get("Column6"));
		WebdriverWait.findElement("xpath", "//*[@id='FindPurchaseInvoice']/table/tbody[1]/tr[7]/td/input").click();// Search
																													// Button
		// WebdriverWait.findElement("xpath",
		// "//*[@id='example']/tbody/tr/td[1]/a").click();
		Thread.sleep(500);

		WebElement element = WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();

		String invOutstandingAmt = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody[1]/tr[5]/td[2]").getText()
						.replace(",", "");

		PI_View_OutstandingAmt_softAssert.assertThat(invOutstandingAmt).isEqualTo(data.get(13).get("Column14"));
	}

	public static void Inv2_OutstandingAmt_Val(List<HashMap<String, String>> data) throws InterruptedException {

		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
		WebdriverWait.findElement("link", "Purchase Invoices").click();
		WebdriverWait.findElement("id", "invoiceId").sendKeys(data.get(14).get("Column6"));
		WebdriverWait.findElement("xpath", "//*[@id='FindPurchaseInvoice']/table/tbody[1]/tr[7]/td/input").click();// Search
																													// Button
		// WebdriverWait.findElement("xpath",
		// "//*[@id='example']/tbody/tr/td[1]/a").click();
		Thread.sleep(500);

		WebElement element = WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();

		String invOutstandingAmt = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody[1]/tr[5]/td[2]").getText()
						.replace(",", "");

		PI_View_OutstandingAmt_softAssert.assertThat(invOutstandingAmt).isEqualTo(data.get(14).get("Column14"));
	}

	public static void Inv3_OutstandingAmt_Val(List<HashMap<String, String>> data) throws InterruptedException {

		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
		WebdriverWait.findElement("link", "Purchase Invoices").click();
		WebdriverWait.findElement("id", "invoiceId").sendKeys(data.get(15).get("Column6"));
		WebdriverWait.findElement("xpath", "//*[@id='FindPurchaseInvoice']/table/tbody[1]/tr[7]/td/input").click();// Search
																													// Button
		// WebdriverWait.findElement("xpath",
		// "//*[@id='example']/tbody/tr/td[1]/a").click();
		Thread.sleep(500);

		WebElement element = WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();

		String invOutstandingAmt = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody[1]/tr[5]/td[2]").getText()
						.replace(",", "");

		PI_View_OutstandingAmt_softAssert.assertThat(invOutstandingAmt).isEqualTo(data.get(15).get("Column14"));
	}

	public static void PayTerm_Inv1_PaidAmt_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div/div[1]/ul/li[2]/a").click();

		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[6]").getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm1).isEqualTo(data.get(13).get("Column18"));

		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[6]").getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm2).isEqualTo(data.get(13).get("Column19"));

	}

	public static void PayTerm_Inv2_PaidAmt_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div/div[1]/ul/li[2]/a").click();

		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[6]").getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm1).isEqualTo(data.get(13).get("Column18"));

		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[6]").getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm2).isEqualTo(data.get(13).get("Column19"));

	}

	public static void PayTerm_Inv3_PaidAmt_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div/div[1]/ul/li[2]/a").click();

		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[6]").getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm1).isEqualTo(data.get(13).get("Column18"));

		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[6]").getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm2).isEqualTo(data.get(13).get("Column19"));

	}

	public static void PayTerm_Inv1_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div/div[1]/ul/li[2]/a").click();

		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[5]").getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm1).isEqualTo(data.get(13).get("Column18"));

		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[5]").getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm2).isEqualTo(data.get(13).get("Column19"));

	}

	public static void PayTerm_Inv2_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div/div[1]/ul/li[2]/a").click();

		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[5]").getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm1).isEqualTo(data.get(14).get("Column18"));

		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[5]").getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm2).isEqualTo(data.get(14).get("Column19"));

	}

	public static void PayTerm_Inv3_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div/div[1]/ul/li[2]/a").click();

		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[5]").getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm1).isEqualTo(data.get(15).get("Column18"));

		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[5]").getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm2).isEqualTo(data.get(15).get("Column19"));

	}

	public static void verifySalesReturnInvoiceDetails_Prd_N_Qty(List<HashMap<String, String>> data) {
		String getProductID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[1]/td[4]").getText();
		// Assert.assertEquals(getProductID,
		// getProductID.contains(data.get(0).get("Column25")));

		String getReturnedQty = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[1]/td[6]").getText();
		Assert.assertEquals(getReturnedQty, data.get(0).get("Column27"));
	}

	public static void verify_SalesReturn_SubTotal_Val(List<HashMap<String, String>> data) {

		log.info("Validating SubTotal");
		String subTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[2]/td[3]").getText().replace(",",
						"");
		System.out.println("SubTotal" + subTotal);
		PI_View_softAssert.assertThat(data.get(13).get("Column7")).isEqualTo(subTotal);
		System.out.println("Sub Total validated successfully");
	}

	public static void verify_SO_Tax_Val(List<HashMap<String, String>> data) {

		log.info("Validating Tax");
		String grandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[4]/td[3]").getText().replace(",",
						"");
		System.out.println("GrandTotal" + grandTotal);
		PI_View_softAssert.assertThat(data.get(17).get("Column8")).isEqualTo(grandTotal);
		log.info("Tax validated Successfully");
		System.out.println("Tax Validated Successfully");
	}

	public static void verify_SO_GrdTotal_Val(List<HashMap<String, String>> data) {

		log.info("Validating GrandTotal");
		String grandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[5]/td[3]/span").getText()
						.replace(",", "");
		System.out.println("GrandTotal" + grandTotal);
		PI_View_softAssert.assertThat(data.get(17).get("Column9")).isEqualTo(grandTotal);
		log.info("Grand Total validated Successfully");
		System.out.println("Grand Total Validated Successfully");
	}

	public static void AddtionalCostPI_GrdTotal_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating GrandTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String grandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (grandTotal.contains("Grand Total")) {

				String GrandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[3]")
								.getText().replace(",", "");
				PI_View_softAssert.assertThat(GrandTotal).isEqualTo(data.get(row).get(col));
				log.info("PI Grand Total validated Successfully");
				System.out.println("Addtional Cost PI Grand Total validated Successfully");
			}
		}
	}

	public static void AdditionalCost_InvoiceIDCapture(String sheetname) {

		WebdriverWait.findElement("link", "Overview").click();

		String inv_ID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody[1]/tr[1]/td[2]").getText();
		ExcelWriter.writeExcelFile(sheetname, 16, 6, inv_ID);
	}

	public static void PI_View_softAssert() {
		PI_View_softAssert.assertAll();
	}

	public static void PI_View_OutstandingAmt_softAssert() {
		PI_View_OutstandingAmt_softAssert.assertAll();
	}

}
