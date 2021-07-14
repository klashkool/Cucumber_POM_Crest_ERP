package com.Financials_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Financials_AcctTransaction_GLTransactionViewEntry extends Base {

	@Rule
	public static JUnitSoftAssertions AcctTrans_ShipID_softAssert = new JUnitSoftAssertions();
	public static JUnitSoftAssertions AcctTrans_PaymentID_softAssert = new JUnitSoftAssertions();
	public static JUnitSoftAssertions AcctTrans_InvID_softAssert = new JUnitSoftAssertions();

	public static void Acct_Trans_Val_ShipID_1(List<HashMap<String, String>> data, int ShipIDRow, String PrdID, String CostValue) {

		WebdriverWait.findElement("link", "GL Transaction View/Entry").click();
		WebdriverWait.findElement("xpath", "//*[@id='toggleLink']").click();
		WebdriverWait.findElement("name", "shipmentId").sendKeys(data.get(ShipIDRow).get("Column5"));
		WebdriverWait.findElement("xpath", "//*[@id='findAcctgTrans']/div/table/tbody[1]/tr[13]/td/input").click();

		List<WebElement> glTransTable = WebdriverWait.findElements("xpath", "//*[@id='example']/tbody/tr");
		for (int t = 1; t <= glTransTable.size(); t++) {
			WebdriverWait.findElement("xpath", "//*[@id='example']/thead/tr/td[1]").click();
			String Posted_Status = driver.findElement(By.xpath("//*[@id='example']/tbody/tr[" + t + "]/td[5]")).getText();
			Assert.assertEquals("Y", Posted_Status);
			WebdriverWait.findElement("xpath", "//*[@id= 'example']/tbody/tr[" + t + "]/td[1]/a").click();

			List<WebElement> acctTransEntryTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr");
			System.out.println(acctTransEntryTable.size());
			for (int g = 1; g <= acctTransEntryTable.size(); g++) {

				String prdID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[3]").getText();

				String glAcctName = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[2]")
								.getText();

				String flag = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[5]").getText();

				if (g == 1) {

					if (prdID.contains(data.get(t - 1).get(PrdID))) {

						Assert.assertEquals(data.get(30).get("Column1"), glAcctName);
						Assert.assertEquals(data.get(30).get("Column2"), flag);
						String value = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[1]/td[8]").getText()
										.replace("₹", "").replace(",", "");

						AcctTrans_ShipID_softAssert.assertThat(value).isEqualTo(data.get(t - 1).get(CostValue));
						System.out.println("1st line value " + value);
					}
				}
				if (g == 2) {

					if (prdID.contains(data.get(t - 1).get(PrdID))) {
						Assert.assertEquals(data.get(31).get("Column1"), glAcctName);
						Assert.assertEquals(data.get(31).get("Column2"), flag);
						String value = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[1]/td[8]").getText()
										.replace("₹", "").replace(",", "");
						AcctTrans_ShipID_softAssert.assertThat(value).isEqualTo(data.get(t - 1).get(CostValue));
						System.out.println("2nd line value " + value);
					}
				}
			}
			WebdriverWait.findElement("link", "Back to Search").click();
		}
	}

	public static void Acct_Trans_Val_ShipID_2(List<HashMap<String, String>> data, int ShipIDRow, String PrdID, String CostValue) {

		WebdriverWait.findElement("link", "GL Transaction View/Entry").click();
		WebdriverWait.findElement("xpath", "//*[@id='toggleLink']").click();
		WebdriverWait.findElement("name", "shipmentId").sendKeys(data.get(ShipIDRow).get("Column5"));
		WebdriverWait.findElement("xpath", "//*[@id='findAcctgTrans']/div/table/tbody[1]/tr[13]/td/input").click();

		List<WebElement> glTransTable = WebdriverWait.findElements("xpath", "//*[@id='example']/tbody/tr");
		for (int t = 1; t <= glTransTable.size(); t++) {
			WebdriverWait.findElement("xpath", "//*[@id='example']/thead/tr/td[1]").click();
			String Posted_Status = driver.findElement(By.xpath("//*[@id='example']/tbody/tr[" + t + "]/td[5]")).getText();
			Assert.assertEquals("Y", Posted_Status);
			WebdriverWait.findElement("xpath", "//*[@id= 'example']/tbody/tr[" + t + "]/td[1]/a").click();

			List<WebElement> acctTransEntryTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr");
			System.out.println(acctTransEntryTable.size());
			for (int g = 1; g <= acctTransEntryTable.size(); g++) {

				String prdID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[3]").getText();

				String glAcctName = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[2]")
								.getText();

				String flag = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[5]").getText();

				if (g == 1) {

					if (prdID.contains(data.get(t - 1).get(PrdID))) {

						Assert.assertEquals(data.get(30).get("Column1"), glAcctName);
						Assert.assertEquals(data.get(30).get("Column2"), flag);
						String value = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[1]/td[8]").getText()
										.replace("₹", "").replace(",", "");

						AcctTrans_ShipID_softAssert.assertThat(value).isEqualTo(data.get(t - 1).get(CostValue));
						System.out.println("1st line value " + value);
					}
				}
				if (g == 2) {

					if (prdID.contains(data.get(t - 1).get(PrdID))) {
						Assert.assertEquals(data.get(31).get("Column1"), glAcctName);
						Assert.assertEquals(data.get(31).get("Column2"), flag);
						String value = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[1]/td[8]").getText()
										.replace("₹", "").replace(",", "");
						AcctTrans_ShipID_softAssert.assertThat(value).isEqualTo(data.get(t - 1).get(CostValue));
						System.out.println("2nd line value " + value);
					}
				}
			}
			WebdriverWait.findElement("link", "Back to Search").click();
		}
	}

	public static void Acct_Trans_Val_ShipID_3(List<HashMap<String, String>> data, int ShipIDRow, String PrdID, String CostValue) {

		WebdriverWait.findElement("link", "GL Transaction View/Entry").click();
		WebdriverWait.findElement("xpath", "//*[@id='toggleLink']").click();
		WebdriverWait.findElement("name", "shipmentId").sendKeys(data.get(ShipIDRow).get("Column5"));
		WebdriverWait.findElement("xpath", "//*[@id='findAcctgTrans']/div/table/tbody[1]/tr[13]/td/input").click();

		List<WebElement> glTransTable = WebdriverWait.findElements("xpath", "//*[@id='example']/tbody/tr");
		for (int t = 1; t <= glTransTable.size(); t++) {
			WebdriverWait.findElement("xpath", "//*[@id='example']/thead/tr/td[1]").click();
			String Posted_Status = driver.findElement(By.xpath("//*[@id='example']/tbody/tr[" + t + "]/td[5]")).getText();
			Assert.assertEquals("Y", Posted_Status);
			WebdriverWait.findElement("xpath", "//*[@id= 'example']/tbody/tr[" + t + "]/td[1]/a").click();

			List<WebElement> acctTransEntryTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr");
			System.out.println(acctTransEntryTable.size());
			for (int g = 1; g <= acctTransEntryTable.size(); g++) {

				String prdID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[3]").getText();

				String glAcctName = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[2]")
								.getText();

				String flag = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[5]").getText();

				if (g == 1) {

					if (prdID.contains(data.get(t - 1).get(PrdID))) {

						Assert.assertEquals(data.get(30).get("Column1"), glAcctName);
						Assert.assertEquals(data.get(30).get("Column2"), flag);
						String value = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[1]/td[8]").getText()
										.replace("₹", "").replace(",", "");

						AcctTrans_ShipID_softAssert.assertThat(value).isEqualTo(data.get(t - 1).get(CostValue));
						System.out.println("1st line value " + value);
					}
				}
				if (g == 2) {

					if (prdID.contains(data.get(t - 1).get(PrdID))) {
						Assert.assertEquals(data.get(31).get("Column1"), glAcctName);
						Assert.assertEquals(data.get(31).get("Column2"), flag);
						String value = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[1]/td[8]").getText()
										.replace("₹", "").replace(",", "");
						AcctTrans_ShipID_softAssert.assertThat(value).isEqualTo(data.get(t - 1).get(CostValue));
						System.out.println("2nd line value " + value);
					}
				}
			}
			WebdriverWait.findElement("link", "Back to Search").click();
		}
	}

	public static void Acct_Trans_Val_ShipID_4(List<HashMap<String, String>> data, int ShipIDRow, String PrdID, String CostValue) {

		WebdriverWait.findElement("link", "GL Transaction View/Entry").click();
		WebdriverWait.findElement("xpath", "//*[@id='toggleLink']").click();
		WebdriverWait.findElement("name", "shipmentId").sendKeys(data.get(ShipIDRow).get("Column5"));
		WebdriverWait.findElement("xpath", "//*[@id='findAcctgTrans']/div/table/tbody[1]/tr[13]/td/input").click();

		List<WebElement> glTransTable = WebdriverWait.findElements("xpath", "//*[@id='example']/tbody/tr");
		for (int t = 1; t <= glTransTable.size(); t++) {
			WebdriverWait.findElement("xpath", "//*[@id='example']/thead/tr/td[1]").click();
			String Posted_Status = driver.findElement(By.xpath("//*[@id='example']/tbody/tr[" + t + "]/td[5]")).getText();
			Assert.assertEquals("Y", Posted_Status);
			WebdriverWait.findElement("xpath", "//*[@id= 'example']/tbody/tr[" + t + "]/td[1]/a").click();

			List<WebElement> acctTransEntryTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr");
			System.out.println(acctTransEntryTable.size());
			for (int g = 1; g <= acctTransEntryTable.size(); g++) {

				String prdID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[3]").getText();

				String glAcctName = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[2]")
								.getText();

				String flag = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[5]").getText();

				if (g == 1) {

					if (prdID.contains(data.get(t - 1).get(PrdID))) {

						Assert.assertEquals(data.get(30).get("Column1"), glAcctName);
						Assert.assertEquals(data.get(30).get("Column2"), flag);
						String value = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[1]/td[8]").getText()
										.replace("₹", "").replace(",", "");

						AcctTrans_ShipID_softAssert.assertThat(value).isEqualTo(data.get(t - 1).get(CostValue));
						System.out.println("1st line value " + value);
					}
				}
				if (g == 2) {

					if (prdID.contains(data.get(t - 1).get(PrdID))) {
						Assert.assertEquals(data.get(31).get("Column1"), glAcctName);
						Assert.assertEquals(data.get(31).get("Column2"), flag);
						String value = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[1]/td[8]").getText()
										.replace("₹", "").replace(",", "");
						AcctTrans_ShipID_softAssert.assertThat(value).isEqualTo(data.get(t - 1).get(CostValue));
						System.out.println("2nd line value " + value);
					}
				}
			}
			WebdriverWait.findElement("link", "Back to Search").click();
		}
	}

	public static void Acct_Trans_Val_AddtnlCostInv(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "GL Transaction View/Entry").click();
		WebdriverWait.findElement("xpath", "//*[@id='toggleLink']").click();
		WebdriverWait.findElement("name", "invoiceId").sendKeys(data.get(14).get("Column6"));
		WebdriverWait.findElement("xpath", "//*[@id='findAcctgTrans']/div/table/tbody[1]/tr[13]/td/input").click();

		String Posted_Status = driver.findElement(By.xpath("//*[@id='example']/tbody/tr/td[5]")).getText();
		Assert.assertEquals("Y", Posted_Status);
		WebdriverWait.findElement("xpath", "//*[@id= 'example']/tbody/tr/td[1]/a").click();

		String glAcctName = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[6]/td[2]").getText();
		AcctTrans_InvID_softAssert.assertThat(glAcctName).isEqualTo("210 - Accounts Payable");

		String flag = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[6]/td[5]").getText();
		AcctTrans_InvID_softAssert.assertThat(flag).isEqualTo("C");

		String amt = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[6]/td[8]").getText();
		AcctTrans_InvID_softAssert.assertThat(amt).isEqualTo(data.get(40).get("Column9").replace("₹", "").replace(",", ""));

	}

	// ***** Acct Transaction Validations with Invoice ID********

	public static void Acct_Trans_Val_InvoiceID(List<HashMap<String, String>> data, String acctName1, String acctName2, String acctName3, String acctName4,
					String flag1, String flag2) {

		int a = 13;
		for (int inv = 1; inv <= 4; inv++) {
			WebdriverWait.findElement("link", "GL Transaction View/Entry").click();
			WebdriverWait.findElement("xpath", "//*[@id='toggleLink']").click();
			WebdriverWait.findElement("name", "invoiceId").sendKeys(data.get(a).get("Currency"));
			WebdriverWait.findElement("xpath", "//*[@id='findAcctgTrans']/div/table/tbody[1]/tr[13]/td/input").click();

			String Posted_Status = driver.findElement(By.xpath("//*[@id='example']/tbody/tr/td[5]")).getText();
			Assert.assertEquals("Y", Posted_Status);
			WebdriverWait.findElement("xpath", "//*[@id= 'example']/tbody/tr/td[1]/a").click();

			List<WebElement> acctTransEntries = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr");

			for (int g = 1; g <= acctTransEntries.size(); g++) {
				String glAcctName = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[2]")
								.getText();

				if (glAcctName.equalsIgnoreCase(acctName1)) {

					String flag = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[5]").getText();
					Assert.assertEquals(flag1, flag);

				} else if (glAcctName.equalsIgnoreCase(acctName2)) {

					String flag = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[5]").getText();
					Assert.assertEquals(flag1, flag);

				} else if (glAcctName.equalsIgnoreCase(acctName3)) {

					String flag = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[5]").getText();
					Assert.assertEquals(flag1, flag);

				} else if (glAcctName.equalsIgnoreCase(acctName4)) {

					String flag = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[5]").getText();
					Assert.assertEquals(flag2, flag);

					String acctPay_Recv = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[7]")
									.getText().replace("₹", "").replace(",", "");
					Assert.assertEquals(data.get(a).get("Facility-ShipGroup"), acctPay_Recv);

				}
			}
			a++;
		}
	}

	public static void Acct_Trans_Val_PaymentID(List<HashMap<String, String>> data, int PayID_row) {

		WebdriverWait.findElement("link", "GL Transaction View/Entry").click();
		WebdriverWait.findElement("xpath", "//*[@id='toggleLink']").click();
		WebdriverWait.findElement("name", "paymentId").sendKeys(data.get(PayID_row).get("Column10"));
		WebdriverWait.findElement("xpath", "//*[@id='findAcctgTrans']/div/table/tbody[1]/tr[13]/td/input").click();

		String Posted_Status = driver.findElement(By.xpath("//*[@id='example']/tbody/tr/td[5]")).getText();
		Assert.assertEquals("Y", Posted_Status);
		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a").click();

		List<WebElement> acctTransEntryTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr");
		System.out.println(acctTransEntryTable.size());
		for (int g = 1; g <= acctTransEntryTable.size(); g++) {

			String glAcctName = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[2]").getText();

			String flag = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[5]").getText();

			if (g == 1) {

				Assert.assertEquals(data.get(30).get("Column5"), glAcctName);
				Assert.assertEquals(data.get(30).get("Column6"), flag);
				String value = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[1]/td[8]").getText()
								.replace(",", "");

				AcctTrans_PaymentID_softAssert.assertThat(value).isEqualTo(data.get(17).get("Column13"));
				System.out.println("1st line value " + value);
			}

			if (g == 2) {

				Assert.assertEquals(data.get(31).get("Column5"), glAcctName);
				Assert.assertEquals(data.get(31).get("Column6"), flag);
				String value = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[1]/td[8]").getText()
								.replace(",", "");
				AcctTrans_PaymentID_softAssert.assertThat(value).isEqualTo(data.get(17).get("Column13"));
				System.out.println("2nd line value " + value);

			}
		}
	}

	public static void Acct_Trans_Val_PaymentID_1_Imp(List<HashMap<String, String>> data, int row1, int row2) {

		WebdriverWait.findElement("link", "GL Transaction View/Entry").click();
		WebdriverWait.findElement("xpath", "//*[@id='toggleLink']").click();
		WebdriverWait.findElement("name", "paymentId").sendKeys(data.get(13).get("Column10"));
		WebdriverWait.findElement("xpath", "//*[@id='findAcctgTrans']/div/table/tbody[1]/tr[13]/td/input").click();

		String Posted_Status = driver.findElement(By.xpath("//*[@id='example']/tbody/tr/td[5]")).getText();
		Assert.assertEquals("Y", Posted_Status);
		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a").click();

		List<WebElement> acctTransEntryTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr");
		System.out.println(acctTransEntryTable.size());
		for (int g = 1; g <= acctTransEntryTable.size(); g++) {

			String glAcctName = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[2]").getText();

			String flag = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[5]").getText();

			if (g == 1) {

				Assert.assertEquals(data.get(30).get("Column5"), glAcctName);
				Assert.assertEquals(data.get(30).get("Column6"), flag);
				String value = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[1]/td[8]").getText()
								.replace(",", "");

				AcctTrans_PaymentID_softAssert.assertThat(value).isEqualTo(data.get(row1).get("Column13"));
				System.out.println("1st line value " + value);
			}

			if (g == 2) {

				Assert.assertEquals(data.get(31).get("Column5"), glAcctName);
				Assert.assertEquals(data.get(31).get("Column6"), flag);
				String value = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[1]/td[8]").getText()
								.replace(",", "");
				AcctTrans_PaymentID_softAssert.assertThat(value).isEqualTo(data.get(row2).get("Column13"));
				System.out.println("2nd line value " + value);

			}
		}
	}

	public static void Acct_Trans_Val_PaymentID_2_Imp(List<HashMap<String, String>> data, int row1, int row2) {

		WebdriverWait.findElement("link", "GL Transaction View/Entry").click();
		WebdriverWait.findElement("xpath", "//*[@id='toggleLink']").click();
		WebdriverWait.findElement("name", "paymentId").sendKeys(data.get(14).get("Column10"));
		WebdriverWait.findElement("xpath", "//*[@id='findAcctgTrans']/div/table/tbody[1]/tr[13]/td/input").click();

		String Posted_Status = driver.findElement(By.xpath("//*[@id='example']/tbody/tr/td[5]")).getText();
		Assert.assertEquals("Y", Posted_Status);
		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a").click();

		List<WebElement> acctTransEntryTable = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr");
		System.out.println(acctTransEntryTable.size());
		for (int g = 1; g <= acctTransEntryTable.size(); g++) {

			String glAcctName = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[2]").getText();

			String flag = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[" + g + "]/td[5]").getText();

			if (g == 1) {

				Assert.assertEquals(data.get(30).get("Column5"), glAcctName);
				Assert.assertEquals(data.get(30).get("Column6"), flag);
				String value = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[1]/td[8]").getText()
								.replace(",", "");

				AcctTrans_PaymentID_softAssert.assertThat(value).isEqualTo(data.get(row1).get("Column13"));
				System.out.println("1st line value " + value);
			}

			if (g == 2) {

				Assert.assertEquals(data.get(31).get("Column5"), glAcctName);
				Assert.assertEquals(data.get(31).get("Column6"), flag);
				String value = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[1]/td[8]").getText()
								.replace(",", "");
				AcctTrans_PaymentID_softAssert.assertThat(value).isEqualTo(data.get(row2).get("Column13"));
				System.out.println("2nd line value " + value);

			}
		}
	}

	public static void AcctTrans_InvID_softAssert() {
		AcctTrans_InvID_softAssert.assertAll();
	}

	public static void AcctTrans_ShipID_softAssert() {
		AcctTrans_ShipID_softAssert.assertAll();
	}

	public static void AcctTrans_PaymentID_softAssert() {
		AcctTrans_PaymentID_softAssert.assertAll();
	}
}
