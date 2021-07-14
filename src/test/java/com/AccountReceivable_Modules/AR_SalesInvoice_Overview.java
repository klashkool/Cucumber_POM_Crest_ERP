package com.AccountReceivable_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.Sales_Modules.Sales_SalesOrder;
import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AR_SalesInvoice_Overview extends Base {

	public static Logger log = Logger.getLogger(Sales_SalesOrder.class);

	@Rule
	public static JUnitSoftAssertions SI_View_softAssert = new JUnitSoftAssertions();
	public static JUnitSoftAssertions SI_View_OutstandingAmt_softAssert = new JUnitSoftAssertions();
	public static JUnitSoftAssertions SI_Overview_softAssert = new JUnitSoftAssertions();

	public static void SO_Invoice_Post() {

		WebdriverWait.findElement("link", "Post").click();
		String so_invId_status = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div//tr[8]/td[2]").getText();
		Assert.assertEquals("Posted", so_invId_status);
	}

	public static void SO_Inv_AcctTrans_Val() {

		List<WebElement> transTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']//div[8]//tr");

		for (int a = 1; a <= transTable.size() - 1; a++) {

			String acctName = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div[8]//tr[" + a + "]//td[11]").getText();

			if (acctName.equalsIgnoreCase("GENERAL SALES") & acctName.equalsIgnoreCase(("Input CGST"))) {
				String flag = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div[8]//tr[" + a + "]//td[12]").getText();
				Assert.assertEquals("C", flag);
			} else if (acctName.equalsIgnoreCase("Accounts Receivable")) {
				String flag = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div[8]//tr[" + a + "]//td[12]").getText();
				Assert.assertEquals("D", flag);
			}
		}
		System.out.println("Invoice Accounting Transactions Validated Succesfully with Debit/Credit Flag");
	}

	public static void SI_Inv2_SubTotal_Validations(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating SubTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String subTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (subTotal.contains("Sub Total (Item base Price)")) {

				String SubTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");

				// Assert.assertEquals(data.get(row).get(col), SubTotal);

				SI_View_softAssert.assertThat(data.get(row).get(col)).isEqualTo(SubTotal);
				System.out.println("SI Sub Total validated Successfully");
			}
		}
	}

	public static void SI_Inv2_Tax_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating Total Tax");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();
			System.out.println(totalTax);

			if (totalTax.contains("Tax Amount (Consolidated)")) {

				String TotalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");
				SI_View_softAssert.assertThat(data.get(row).get(col)).isEqualTo(TotalTax);
				log.info("SI Tax Amt validated Successfully");
				System.out.println("SI Tax Amt validated Successfully");
			}
		}
	}

	public static void SI_Inv2_GrdTotal_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating GrandTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String grandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (grandTotal.contains("Grand Total")) {

				String GrandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]")
								.getText().replace(",", "");
				SI_View_softAssert.assertThat(data.get(row).get(col)).isEqualTo(GrandTotal);
				log.info("SI Grand Total validated Successfully");
				System.out.println("SI Grand Total validated Successfully");
			}
		}
	}

	public static void SI_Inv3_SubTotal_Validations(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating SubTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String subTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (subTotal.contains("Sub Total (Item base Price)")) {

				String SubTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");

				// Assert.assertEquals(data.get(row).get(col), SubTotal);

				SI_View_softAssert.assertThat(data.get(row).get(col)).isEqualTo(SubTotal);
				System.out.println("SI Sub Total validated Successfully");
			}
		}
	}

	public static void SI_Inv3_Tax_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating Total Tax");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();
			System.out.println(totalTax);

			if (totalTax.contains("Tax Amount (Consolidated)")) {

				String TotalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");
				SI_View_softAssert.assertThat(data.get(row).get(col)).isEqualTo(TotalTax);
				log.info("SI Tax Amt validated Successfully");
				System.out.println("SI Tax Amt validated Successfully");
			}
		}
	}

	public static void SI_Inv3_GrdTotal_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating GrandTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String grandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (grandTotal.contains("Grand Total")) {

				String GrandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]")
								.getText().replace(",", "");
				SI_View_softAssert.assertThat(data.get(row).get(col)).isEqualTo(GrandTotal);
				log.info("SI Grand Total validated Successfully");
				System.out.println("SI Grand Total validated Successfully");
			}
		}
	}

	public static void SI_Inv1_SubTotal_Validations(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating SubTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String subTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (subTotal.contains("Sub Total (Item base Price)")) {

				String SubTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");

				// Assert.assertEquals(data.get(row).get(col), SubTotal);

				SI_View_softAssert.assertThat(data.get(row).get(col)).isEqualTo(SubTotal);
				System.out.println("SI Sub Total validated Successfully");
			}
		}
	}

	public static void SI_Inv1_Tax_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating Total Tax");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();
			System.out.println(totalTax);

			if (totalTax.contains("Tax Amount (Consolidated)")) {

				String TotalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");
				SI_View_softAssert.assertThat(data.get(row).get(col)).isEqualTo(TotalTax);
				log.info("SI Tax Amt validated Successfully");
				System.out.println("SI Tax Amt validated Successfully");
			}
		}
	}

	public static void SI_Inv1_GrdTotal_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating GrandTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String grandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (grandTotal.contains("Grand Total")) {

				String GrandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]")
								.getText().replace(",", "");
				SI_View_softAssert.assertThat(data.get(row).get(col)).isEqualTo(GrandTotal);
				log.info("SI Grand Total validated Successfully");
				System.out.println("SI Grand Total validated Successfully");
			}
		}
	}

	public static void SI_Inv1_TaxBreakup_Table_Val(List<HashMap<String, String>> data, int IGSTRow, String IGSTcol, int CGSTRow, String CGSTcol, int SGSTRow,
					String SGSTcol, String totaltax) {

		String taxType = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div//table//tbody//tr[1]//td[11]").getText();

		System.out.println(taxType);

		List<WebElement> taxBreakupTable = WebdriverWait.findElements("xpath", "//*[@id='taxContainer']/table/tbody/tr");
		int b = taxBreakupTable.size();

		if (taxType.equalsIgnoreCase("INTRA")) {

			System.out.println("--------------------------------entered the loop Intra---------------------------");

			String CGST = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[2]/span").getText().replace("₹", "")
							.replace(",", "");
			SI_View_softAssert.assertThat(data.get(23).get(CGSTcol)).isEqualTo(CGST);

			String SGST = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[3]/span").getText().replace("₹", "")
							.replace(",", "");
			SI_View_softAssert.assertThat(data.get(23).get(SGSTcol)).isEqualTo(SGST);

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]/span").getText().replace("₹", "")
							.replace(",", "");
			SI_View_softAssert.assertThat(data.get(17).get(totaltax)).isEqualTo(totalTax);

		} else if (taxType.equalsIgnoreCase("INTER")) {

			String IGST = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[4]/span").getText().replace("₹", "")
							.replace(",", "");
			SI_View_softAssert.assertThat(data.get(13).get(IGSTcol)).isEqualTo(IGST);

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]/span").getText().replace("₹", "")
							.replace(",", "");
			SI_View_softAssert.assertThat(data.get(13).get(IGSTcol)).isEqualTo(totalTax);

		}

		System.out.println("Tax Breakup Table Validated Successfully");
	}

	public static void SI_Inv2_TaxBreakup_Table_Val(List<HashMap<String, String>> data, String IGSTcol, String CGSTcol, String SGSTcol, String totaltax) {

		String taxType = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div//table//tbody//tr[1]//td[11]").getText();

		System.out.println(taxType);

		List<WebElement> taxBreakupTable = WebdriverWait.findElements("xpath", "//*[@id='taxContainer']/table/tbody/tr");
		int b = taxBreakupTable.size();

		if (taxType.equalsIgnoreCase("INTRA")) {

			System.out.println("--------------------------------entered the loop Intra---------------------------");

			String CGST = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[2]/span").getText().replace("₹", "")
							.replace(",", "");
			SI_View_softAssert.assertThat(data.get(23).get(CGSTcol)).isEqualTo(CGST);

			String SGST = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[3]/span").getText().replace("₹", "")
							.replace(",", "");
			SI_View_softAssert.assertThat(data.get(23).get(SGSTcol)).isEqualTo(SGST);

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]/span").getText().replace("₹", "")
							.replace(",", "");
			SI_View_softAssert.assertThat(data.get(17).get(totaltax)).isEqualTo(totalTax);

		} else if (taxType.equalsIgnoreCase("INTER")) {

			String IGST = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[4]/span").getText().replace("₹", "")
							.replace(",", "");
			SI_View_softAssert.assertThat(data.get(13).get(IGSTcol)).isEqualTo(IGST);

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]/span").getText().replace("₹", "")
							.replace(",", "");
			SI_View_softAssert.assertThat(data.get(13).get(IGSTcol)).isEqualTo(totalTax);

		}

		System.out.println("Tax Breakup Table Validated Successfully");
	}

	public static void SI_Inv3_TaxBreakup_Table_Val(List<HashMap<String, String>> data, String IGSTcol, String CGSTcol, String SGSTcol, String totaltax) {

		String taxType = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div//table//tbody//tr[1]//td[11]").getText();

		System.out.println(taxType);

		List<WebElement> taxBreakupTable = WebdriverWait.findElements("xpath", "//*[@id='taxContainer']/table/tbody/tr");
		int b = taxBreakupTable.size();

		if (taxType.equalsIgnoreCase("INTRA")) {

			System.out.println("--------------------------------entered the loop Intra---------------------------");

			String CGST = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[2]/span").getText().replace("₹", "")
							.replace(",", "");
			SI_View_softAssert.assertThat(data.get(23).get(CGSTcol)).isEqualTo(CGST);

			String SGST = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[3]/span").getText().replace("₹", "")
							.replace(",", "");
			SI_View_softAssert.assertThat(data.get(23).get(SGSTcol)).isEqualTo(SGST);

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]/span").getText().replace("₹", "")
							.replace(",", "");
			SI_View_softAssert.assertThat(data.get(17).get(totaltax)).isEqualTo(totalTax);

		} else if (taxType.equalsIgnoreCase("INTER")) {

			String IGST = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[4]/span").getText().replace("₹", "")
							.replace(",", "");
			SI_View_softAssert.assertThat(data.get(13).get(IGSTcol)).isEqualTo(IGST);

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]/span").getText().replace("₹", "")
							.replace(",", "");
			SI_View_softAssert.assertThat(data.get(13).get(IGSTcol)).isEqualTo(totalTax);

		}

		System.out.println("Tax Breakup Table Validated Successfully");
	}

	public static void Inv1_HeaderAmt_Val(List<HashMap<String, String>> data) {

		String headerAmt = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody[1]/tr[5]/td[2]").getText().replace(",",
						"");

		SI_View_softAssert.assertThat(headerAmt).isEqualTo(data.get(13).get("Column9"));

	}

	public static void Inv2_HeaderAmt_Val(List<HashMap<String, String>> data) {

		String headerAmt = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody[1]/tr[5]/td[2]").getText().replace(",",
						"");

		SI_View_softAssert.assertThat(headerAmt).isEqualTo(data.get(14).get("Column9"));

	}

	public static void Inv3_HeaderAmt_Val(List<HashMap<String, String>> data) {

		String headerAmt = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody[1]/tr[5]/td[2]").getText().replace(",",
						"");

		SI_View_softAssert.assertThat(headerAmt).isEqualTo(data.get(15).get("Column9"));

	}

	public static void PayTerm_Inv1_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[1]/div[1]/ul/li[2]/a").click();

		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[3]").getText().replace(",", "");
		SI_View_softAssert.assertThat(payterm1).isEqualTo(data.get(13).get("Column18"));

		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[3]").getText().replace(",", "");
		SI_View_softAssert.assertThat(payterm2).isEqualTo(data.get(13).get("Column19"));

	}

	public static void PayTerm_Inv2_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[1]/div[1]/ul/li[2]/a").click();

		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[3]").getText().replace(",", "");
		SI_View_softAssert.assertThat(payterm1).isEqualTo(data.get(14).get("Column18"));

		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[3]").getText().replace(",", "");
		SI_View_softAssert.assertThat(payterm2).isEqualTo(data.get(14).get("Column19"));

	}

	public static void PayTerm_Inv3_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[1]/div[1]/ul/li[2]/a").click();

		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[3]").getText().replace(",", "");
		SI_View_softAssert.assertThat(payterm1).isEqualTo(data.get(15).get("Column18"));

		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[3]").getText().replace(",", "");
		SI_View_softAssert.assertThat(payterm2).isEqualTo(data.get(15).get("Column19"));

	}

	public static void PayTerm_Inv1_PaidAmt_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[1]/div[1]/ul/li[2]/a").click();

		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[4]").getText().replace(",", "");
		SI_View_softAssert.assertThat(payterm1).isEqualTo(data.get(13).get("Column18"));

		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[4]").getText().replace(",", "");
		SI_View_softAssert.assertThat(payterm2).isEqualTo(data.get(13).get("Column19"));

	}

	public static void PayTerm_Inv2_PaidAmt_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[1]/div[1]/ul/li[2]/a").click();

		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[4]").getText().replace(",", "");
		SI_View_softAssert.assertThat(payterm1).isEqualTo(data.get(13).get("Column18"));

		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[4]").getText().replace(",", "");
		SI_View_softAssert.assertThat(payterm2).isEqualTo(data.get(13).get("Column19"));

	}

	public static void PayTerm_Inv3_PaidAmt_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[1]/div[1]/ul/li[2]/a").click();

		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[4]").getText().replace(",", "");
		SI_View_softAssert.assertThat(payterm1).isEqualTo(data.get(13).get("Column18"));

		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[4]").getText().replace(",", "");
		SI_View_softAssert.assertThat(payterm2).isEqualTo(data.get(13).get("Column19"));

	}

	public static void SI_Inv1_TaxBreakup_Table_Val(List<HashMap<String, String>> data) {

		List<WebElement> taxBreakupTable = WebdriverWait.findElements("xpath", "//*[@id='taxContainer']/table/tbody/tr");
		int b = taxBreakupTable.size();

		String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]").getText().replace(",", "");
		SI_View_softAssert.assertThat(totalTax).isEqualTo(data.get(13).get("Column8"));

		System.out.println("Tax Breakup Table Validated Successfully");
	}

	public static void SI_Inv2_TaxBreakup_Table_Val(List<HashMap<String, String>> data) {

		List<WebElement> taxBreakupTable = WebdriverWait.findElements("xpath", "//*[@id='taxContainer']/table/tbody/tr");
		int b = taxBreakupTable.size();

		String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]").getText().replace(",", "");
		SI_View_softAssert.assertThat(totalTax).isEqualTo(data.get(14).get("Column8"));

		System.out.println("Tax Breakup Table Validated Successfully");
	}

	public static void SI_Inv3_TaxBreakup_Table_Val(List<HashMap<String, String>> data) {

		List<WebElement> taxBreakupTable = WebdriverWait.findElements("xpath", "//*[@id='taxContainer']/table/tbody/tr");
		int b = taxBreakupTable.size();

		String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]").getText().replace(",", "");
		SI_View_softAssert.assertThat(totalTax).isEqualTo(data.get(15).get("Column8"));

		System.out.println("Tax Breakup Table Validated Successfully");
	}

	public static void Inv1_OutstandingAmt_Val(List<HashMap<String, String>> data) throws InterruptedException {

		WebdriverWait.findElement("link", "ACCOUNTS RECEIVABLE").click();
		WebdriverWait.findElement("link", "Sales Invoice").click();
		WebdriverWait.findElement("id", "invoiceId").sendKeys(data.get(13).get("Column6"));
		WebdriverWait.findElement("id", "submit").click();// SearchButton
		Thread.sleep(500);

		WebElement element = WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();

		String invOutstandingAmt = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div//table//tbody[1]//tr[6]//td[2]").getText()
						.replace(",", "");

		SI_View_OutstandingAmt_softAssert.assertThat(invOutstandingAmt).isEqualTo(data.get(13).get("Column14"));
	}

	public static void Inv2_OutstandingAmt_Val(List<HashMap<String, String>> data) throws InterruptedException {

		WebdriverWait.findElement("link", "ACCOUNTS RECEIVABLE").click();
		WebdriverWait.findElement("link", "Sales Invoice").click();
		WebdriverWait.findElement("id", "invoiceId").sendKeys(data.get(14).get("Column6"));
		WebdriverWait.findElement("id", "submit").click();// SearchButton
		Thread.sleep(500);

		WebElement element = WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();

		String invOutstandingAmt = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div//table//tbody[1]//tr[6]//td[2]").getText()
						.replace(",", "");

		SI_View_OutstandingAmt_softAssert.assertThat(invOutstandingAmt).isEqualTo(data.get(14).get("Column14"));
	}

	public static void Inv3_OutstandingAmt_Val(List<HashMap<String, String>> data) throws InterruptedException {

		WebdriverWait.findElement("link", "ACCOUNTS RECEIVABLE").click();
		WebdriverWait.findElement("link", "Sales Invoice").click();
		WebdriverWait.findElement("id", "invoiceId").sendKeys(data.get(15).get("Column6"));
		WebdriverWait.findElement("id", "submit").click();// SearchButton
		Thread.sleep(500);

		WebElement element = WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();

		String invOutstandingAmt = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//div//table//tbody[1]//tr[6]//td[2]").getText()
						.replace(",", "");

		SI_View_OutstandingAmt_softAssert.assertThat(invOutstandingAmt).isEqualTo(data.get(15).get("Column14"));
	}

	public static void SI_Inv1_ThirdPartyCharges_Table_Val(List<HashMap<String, String>> data, String col) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[2]").click();// 3rd
																												// party
																												// Charges
																												// Button

		List<WebElement> thirdPartyChargesTable = WebdriverWait.findElements("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr");
		int b = thirdPartyChargesTable.size();

		String thirdPartyTotalCharges = WebdriverWait.findElement("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",",
						"");
		SI_View_softAssert.assertThat(thirdPartyTotalCharges).isEqualTo(data.get(15).get(col));
		System.out.println("Third Party Charges Table validated successfully");

	}

	public static void SI_Inv2_ThirdPartyCharges_Table_Val(List<HashMap<String, String>> data, String col) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[2]").click();// 3rd
																												// party
																												// Charges
																												// Button

		List<WebElement> thirdPartyChargesTable = WebdriverWait.findElements("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr");
		int b = thirdPartyChargesTable.size();

		String thirdPartyTotalCharges = WebdriverWait.findElement("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",",
						"");
		SI_View_softAssert.assertThat(thirdPartyTotalCharges).isEqualTo(data.get(15).get(col));
		System.out.println("Third Party Charges Table validated successfully");

	}

	public static void SI_Inv3_ThirdPartyCharges_Table_Val(List<HashMap<String, String>> data, String col) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[2]").click();// 3rd
																												// party
																												// Charges
																												// Button

		List<WebElement> thirdPartyChargesTable = WebdriverWait.findElements("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr");
		int b = thirdPartyChargesTable.size();

		String thirdPartyTotalCharges = WebdriverWait.findElement("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",",
						"");
		SI_View_softAssert.assertThat(thirdPartyTotalCharges).isEqualTo(data.get(15).get(col));
		System.out.println("Third Party Charges Table validated successfully");

	}


	public static void PR_Refund_Invoice_Subtotal_Val(List<HashMap<String, String>> data, int row, String col) {

		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String subTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (subTotal.contains("Sub Total (Item base Price)")) {

				String SubTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");

				// Assert.assertEquals(data.get(row).get(col), SubTotal);

				SI_View_softAssert.assertThat(data.get(row).get(col)).isEqualTo(SubTotal);
				System.out.println("Purchase Return Sub Total validated Successfully");
			}
		}
	}

	public static void PR_Refund_Invoice_Tax_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating Total Tax");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String totalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();
			System.out.println(totalTax);

			if (totalTax.contains("Tax Amount (Consolidated)")) {

				String TotalTax = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]").getText()
								.replace(",", "");
				SI_View_softAssert.assertThat(data.get(row).get(col)).isEqualTo(TotalTax);
				log.info("SI Tax Amt validated Successfully");
				System.out.println("Purchase Return Tax Amt validated Successfully");
			}
		}
	}

	public static void PR_Refund_Invoice_GrandTotal_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating GrandTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String grandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (grandTotal.contains("Grand Total")) {

				String GrandTotal = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]")
								.getText().replace(",", "");
				SI_View_softAssert.assertThat(data.get(row).get(col)).isEqualTo(GrandTotal);
				log.info("SI Grand Total validated Successfully");
				System.out.println("Purchase Return Grand Total validated Successfully");
			}
		}
	}

	public static void PR_Refund_Invoice_HeaderAmt_Val(List<HashMap<String, String>> data) {

		String headerAmt = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody[1]/tr[5]/td[2]").getText().replace(",",
						"");

		SI_View_softAssert.assertThat(headerAmt).isEqualTo(data.get(13).get("Column9"));

	}

	//////////////////////////////////////Negative flows///////////////////////////////////////////////
	public static void Validate_BackToInvoice_link_navigating_to_SalesInvoiceOverviewPage() {
		WebdriverWait.findElement("link", "Void").click();
		WebdriverWait.findElement("link", "Back to Invoice").click();
		String verifyInvOverviewPageIsDisplayed = driver.getTitle();
		SI_View_softAssert.assertThat(verifyInvOverviewPageIsDisplayed).isEqualTo("Crest: Invoice Overview");
	}

	public static void Validate_void_without_voidReason(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("link", "Void").click();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/table/tbody[1]/tr[2]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SI_View_softAssert.assertThat(validationMsg).isEqualTo(data.get(19).get("Column17"));
		}

	public static void Validate_void_without_voidDate(List<HashMap<String, String>> data) {
		// WebdriverWait.findElement("link", "Void").click();
		WebdriverWait.findElement("id", "voidReason").sendKeys("Test");
		WebdriverWait.findElement("id", "voidDate_i18n").clear();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/table/tbody[1]/tr[2]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SI_View_softAssert.assertThat(validationMsg).isEqualTo(data.get(29).get("Column2"));
		}

	public static void Validate_VoidReason_255Char(List<HashMap<String, String>> data) {
		// WebdriverWait.findElement("link", "Void").click();
		WebdriverWait.findElement("id", "voidReason").sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/table/tbody[1]/tr[2]/td/input").click();
		String getVoidReason = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody[1]/tr[10]/td[2]").getText();
		SI_View_softAssert.assertThat(getVoidReason.length()).isEqualTo("255");
	}

	public static void Validate_ApproveOrPost_with_0_invoiceAmount(List<HashMap<String, String>> data, String ApproveOrPostLink, String ApprovedOrPosted) {
		WebdriverWait.findElement("link", ApproveOrPostLink).click();
		String getStatus = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody[1]/tr[8]/td[2]").getText();
		if(getStatus.equals(ApprovedOrPosted)) {
			SI_View_softAssert.fail("Invoice getting "+ApprovedOrPosted+"when invoice amount is 0.");
		}
	}

	public static void SI_View_OutstandingAmt_softAssert() {
		SI_View_OutstandingAmt_softAssert.assertAll();
	}

	public static void SI_View_softAssert() {
		SI_View_softAssert.assertAll();
	}

	public static void SI_Overview_softAssert() {
		SI_Overview_softAssert.assertAll();
	}
}