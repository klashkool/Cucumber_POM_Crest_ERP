package com.Sales_Modules;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Sales_SalesReturn_ReturnItems extends Base {


	public static JUnitSoftAssertions Sales_ReturnItems_softAssert = new JUnitSoftAssertions();
	public static void Return_items(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Return Items").click();

		// Adding Current Date(From Date)
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String Fromdate = (dateFormat.format(new Date()));

		System.out.println(data.get(13).get("Column4") + " - " + Fromdate);

		Select orderId_DD = new Select(WebdriverWait.findElement("name", "orderId"));
		orderId_DD.selectByVisibleText(data.get(14).get("Column4") + " - " + Fromdate);

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/table/tbody/tr[2]/td/input").click();

		List<WebElement> Returntble = WebdriverWait.findElements("xpath", "//*[@id= 'content-main-section']/form/div[1]/div[2]/table/tbody/tr");
		System.out.println(Returntble.size());

		for (int a = 0; a <= Returntble.size() - 2; a++) {

			String prdID = WebdriverWait.findElement("xpath", "//*[@id='returnItemId_tableRow_" + a + "']/td[1]").getText();
			System.out.println(prdID);

			if (prdID.equalsIgnoreCase(data.get(0).get("Column2"))) {

				WebdriverWait.findElement("name", "returnQuantity_o_" + a).clear();
				WebdriverWait.findElement("name", "returnQuantity_o_" + a).sendKeys(data.get(22).get("Sales Price"));

				Select reason_DD = new Select(WebdriverWait.findElement("name", "returnReasonId_o_0"));
				reason_DD.selectByVisibleText(data.get(22).get("3ShpQty"));

				Select type_DD = new Select(WebdriverWait.findElement("name", "returnTypeId_o_0"));
				type_DD.selectByVisibleText(data.get(22).get("4ShpQty"));

				Select itemStatus_DD = new Select(WebdriverWait.findElement("name", "expectedItemStatus_o_0"));
				itemStatus_DD.selectByVisibleText(data.get(22).get("1ShpVal"));

				WebdriverWait.findElement("name", "_rowSubmit_o_" + a).click();
			}
		}
		WebdriverWait.findElement("id", "sub1").click();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form[2]/div/input").click();
	}


	public static void SalesReturn_items_with_Tax(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Return Items").click();

		// Adding Current Date(From Date)
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String Fromdate = (dateFormat.format(new Date()));

		System.out.println(data.get(13).get("Column4") + " - " + Fromdate);

		// Select orderId_DD = new Select(WebdriverWait.findElement("name",
		// "orderId"));
		// orderId_DD.selectByVisibleText(data.get(13).get("Column4") + " - " +
		// Fromdate);

		List<WebElement> options = driver.findElements(By.xpath("//*[@name='orderId']//option"));

		for (WebElement option : options) {
			if (option.getText().contains(data.get(13).get("Column4"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/table/tbody/tr[2]/td/input").click();

		WebElement lineItem1_ReturnQty = WebdriverWait.findElement("name", "returnQuantity_o_0");
		lineItem1_ReturnQty.clear();
		lineItem1_ReturnQty.sendKeys(data.get(0).get("Column27"));

		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("name", "_rowSubmit_o_1").click();

		WebdriverWait.findElement("id", "sub1").click();
		//Accept Return
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form[2]/div/input").click();
	}

	public static void SalesReturnReplacement_items_with_Tax(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Return Items").click();

		// Adding Current Date(From Date)
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String Fromdate = (dateFormat.format(new Date()));

		System.out.println(data.get(13).get("Column4") + " - " + Fromdate);

		List<WebElement> options = driver.findElements(By.xpath("//*[@name='orderId']//option"));

		for (WebElement option : options) {
			if (option.getText().contains(data.get(13).get("Column4"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/table/tbody/tr[2]/td/input").click();

		WebElement lineItem1_ReturnQty = WebdriverWait.findElement("name", "returnQuantity_o_0");
		lineItem1_ReturnQty.clear();
		lineItem1_ReturnQty.sendKeys(data.get(0).get("Column27"));

		Select returnType_DD = new Select(WebdriverWait.findElement("name", "returnTypeId_o_0"));
		returnType_DD.selectByVisibleText("Wait Replacement");

//		Select returnType1_DD = new Select(WebdriverWait.findElement("name", "returnTypeId_o_1"));
//		returnType1_DD.selectByVisibleText("Wait Replacement");

		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
//		WebdriverWait.findElement("name", "_rowSubmit_o_1").click();

		WebdriverWait.findElement("id", "sub1").click();
		//Accept Return
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/form[2]/div/input").click();
	}

	////////////////////////////////////////////Negative flow/////////////////////////////////////////////////

	public static void clickReturnItems(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("link", "Return Items").click();

		List<WebElement> options = driver.findElements(By.xpath("//*[@name='orderId']//option"));

		for (WebElement option : options) {
			if (option.getText().contains(data.get(13).get("Column4"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/table/tbody/tr[2]/td/input").click();
	}

	public static void validate_ReturnQty_with_SPL_characters(List<HashMap<String, String>> data) {
		//Load Items
		List<WebElement> options = driver.findElements(By.xpath("//*[@name='orderId']//option"));

		for (WebElement option : options) {
			if (option.getText().contains(data.get(13).get("Column4"))) {
				option.click();
				break;
			}
		}
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/table/tbody/tr[2]/td/input").click();
		WebElement returnQty = WebdriverWait.findElement("name", "returnQuantity_o_0");
		returnQty.clear();
		returnQty.sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();//Checkbox
		WebdriverWait.findElement("id", "sub1").click();//Return selected items
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo(data.get(19).get("Column10"));
	}

	public static void validate_ReturnQty_with_negativeValue(List<HashMap<String, String>> data) {
		//Load Items
		List<WebElement> options = driver.findElements(By.xpath("//*[@name='orderId']//option"));

		for (WebElement option : options) {
			if (option.getText().contains(data.get(13).get("Column4"))) {
				option.click();
				break;
			}
		}
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/table/tbody/tr[2]/td/input").click();

		WebElement returnQty = WebdriverWait.findElement("name", "returnQuantity_o_0");
		returnQty.clear();
		returnQty.sendKeys(data.get(1).get("Column1"));
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();//Checkbox
		WebdriverWait.findElement("id", "sub1").click();//Return selected items
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo(data.get(20).get("Column10"));
	}

	public static void validate_ReturnQty_by_leaving_empty(List<HashMap<String, String>> data) {
		//Load Items
		List<WebElement> options = driver.findElements(By.xpath("//*[@name='orderId']//option"));

		for (WebElement option : options) {
			if (option.getText().contains(data.get(13).get("Column4"))) {
				option.click();
				break;
			}
		}
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/table/tbody/tr[2]/td/input").click();
		WebElement returnQty = WebdriverWait.findElement("name", "returnQuantity_o_0");
		returnQty.clear();
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();//Checkbox
		WebdriverWait.findElement("id", "sub1").click();//Return selected items
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo(data.get(21).get("Column10"));
	}

	public static void validate_ReturnQty_with_more_than_OrderedQty(List<HashMap<String, String>> data) {
		//Load Items
		List<WebElement> options = driver.findElements(By.xpath("//*[@name='orderId']//option"));

		for (WebElement option : options) {
			if (option.getText().contains(data.get(13).get("Column4"))) {
				option.click();
				break;
			}
		}
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/table/tbody/tr[2]/td/input").click();
		WebElement returnQty = WebdriverWait.findElement("name", "returnQuantity_o_0");
		String returnQtyvalue = returnQty.getText();
		returnQty.clear();
		returnQty.sendKeys(data.get(14).get("Column1"));
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();//Checkbox
		WebdriverWait.findElement("id", "sub1").click();//Return selected items
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo("Return quantity [ "+data.get(14).get("Column1")+" ] cannot exceed the issued quantity [ "+returnQtyvalue+" ].");
	}

	public static void validate_ReturnPrice_by_leaving_empty(List<HashMap<String, String>> data) {
		//Load Items
		List<WebElement> options = driver.findElements(By.xpath("//*[@name='orderId']//option"));

		for (WebElement option : options) {
			if (option.getText().contains(data.get(13).get("Column4"))) {
				option.click();
				break;
			}
		}
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/table/tbody/tr[2]/td/input").click();
		WebElement returnPrice = WebdriverWait.findElement("name", "returnPrice_o_0");
		returnPrice.clear();
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();//Checkbox
		WebdriverWait.findElement("id", "sub1").click();//Return selected items
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo(data.get(15).get("Column12"));
	}

	public static void validate_ReturnPrice_with_Neg_Value(List<HashMap<String, String>> data) {
		//Load Items
		List<WebElement> options = driver.findElements(By.xpath("//*[@name='orderId']//option"));

		for (WebElement option : options) {
			if (option.getText().contains(data.get(13).get("Column4"))) {
				option.click();
				break;
			}
		}
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/table/tbody/tr[2]/td/input").click();

		WebElement returnPrice = WebdriverWait.findElement("name", "returnPrice_o_0");
		returnPrice.clear();
		returnPrice.sendKeys(data.get(1).get("Column1"));
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();//Checkbox
		WebdriverWait.findElement("id", "sub1").click();//Return selected items
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo(data.get(16).get("Column12"));
	}

	public static void validate_ReturnPrice_with_SPL_Characters(List<HashMap<String, String>> data) {
		//Load Items
		List<WebElement> options = driver.findElements(By.xpath("//*[@name='orderId']//option"));

		for (WebElement option : options) {
			if (option.getText().contains(data.get(13).get("Column4"))) {
				option.click();
				break;
			}
		}
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/table/tbody/tr[2]/td/input").click();
		WebElement returnPrice = WebdriverWait.findElement("name", "returnPrice_o_0");
		returnPrice.clear();
		returnPrice.sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();//Checkbox
		WebdriverWait.findElement("id", "sub1").click();//Return selected items
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo(data.get(17).get("Column12"));

		// Entering Valid data
		List<WebElement> options1 = driver.findElements(By.xpath("//*[@name='orderId']//option"));

		for (WebElement option1 : options1) {
			if (option1.getText().contains(data.get(13).get("Column4"))) {
				option1.click();
				break;
			}
		}
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/table/tbody/tr[2]/td/input").click();

		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();// Checkbox
		WebdriverWait.findElement("id", "sub1").click();// Return selected items
	}

	public static void validate_ReturnPrice_with_more_than_Sales_Price(List<HashMap<String, String>> data) {
		//Load Items
		List<WebElement> options = driver.findElements(By.xpath("//*[@name='orderId']//option"));

		for (WebElement option : options) {
			if (option.getText().contains(data.get(13).get("Column4"))) {
				option.click();
				break;
			}
		}
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/table/tbody/tr[2]/td/input").click();
		WebElement returnPrice = WebdriverWait.findElement("name", "returnPrice_o_0");
		String returnPriceValue = returnPrice.getText();
		returnPrice.clear();
		returnPrice.sendKeys(data.get(14).get("Column1"));
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();//Checkbox
		WebdriverWait.findElement("id", "sub1").click();//Return selected items
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo("Return price [ "+data.get(14).get("Column1")+" ] cannot exceed the sales price [ "+returnPriceValue+" ].");
	}

	public static void validate_ReturnPrice_with_less_than_Sales_Price(List<HashMap<String, String>> data) {
		//Load Items
		List<WebElement> options = driver.findElements(By.xpath("//*[@name='orderId']//option"));

		for (WebElement option : options) {
			if (option.getText().contains(data.get(13).get("Column4"))) {
				option.click();
				break;
			}
		}
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/table/tbody/tr[2]/td/input").click();
		WebdriverWait.findElement("name", "returnQuantity_o_0").clear();
		WebdriverWait.findElement("name", "returnQuantity_o_0").sendKeys("1");
		WebElement returnPrice = WebdriverWait.findElement("name", "returnPrice_o_0");
		returnPrice.clear();
		returnPrice.sendKeys("1");
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();//Checkbox
		WebdriverWait.findElement("id", "sub1").click();//Return selected items
		boolean isAcceptReturnDisplayed = WebdriverWait.findElement("xpath", "//*[@value='Accept Return']").isDisplayed();
		Sales_ReturnItems_softAssert.assertThat(isAcceptReturnDisplayed).isEqualTo(true);
		}

	public static void validate_by_updating_ReturnQty_by_leaving_empty(List<HashMap<String, String>> data) {

		WebElement returnQty = WebdriverWait.findElement("name", "returnQuantity_o_0");
		returnQty.clear();
		WebdriverWait.findElement("xpath", "//*[@value='Update']").click();//Update btn
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo("Yet to add validation msg since the appln is accepting empty value.");
	}

	public static void validate_by_updating_ReturnQty_more_than_issuedQty(List<HashMap<String, String>> data) {

		WebElement returnQty = WebdriverWait.findElement("name", "returnQuantity_o_0");
		returnQty.clear();
		returnQty.sendKeys("99999999999999999");
		WebdriverWait.findElement("xpath", "//*[@value='Update']").click();//Update btn
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo("Yet to add validation msg since the appln is throwing exception.");
	}

	public static void validate_by_updating_ReturnQty_with_Neg_Value(List<HashMap<String, String>> data) {

		WebElement returnQty = WebdriverWait.findElement("name", "returnQuantity_o_0");
		returnQty.clear();
		returnQty.sendKeys("-1");
		WebdriverWait.findElement("xpath", "//*[@value='Update']").click();//Update btn
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo("Yet to add validation msg since the appln is accepting negative value.");
	}

	public static void validate_by_updating_ReturnQty_with_SPL_Characters(List<HashMap<String, String>> data) {

		WebElement returnQty = WebdriverWait.findElement("name", "returnQuantity_o_0");
		returnQty.clear();
		returnQty.sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Update']").click();//Update btn
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo("Yet to add validation msg since the appln is throwing exception.");
	}

	public static void validate_by_updating_ReturnPrice_by_leaving_empty(List<HashMap<String, String>> data) {

		WebElement returnQty = WebdriverWait.findElement("name", "returnQuantity_o_0");
		returnQty.clear();
		returnQty.sendKeys("1");
		WebElement returnPrice = WebdriverWait.findElement("name", "returnPrice_o_0");
		returnPrice.clear();
		WebdriverWait.findElement("xpath", "//*[@value='Update']").click();//Update btn
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo("Yet to add validation msg since the appln is accepting empty value.");
	}

	public static void validate_by_updating_ReturnPrice_more_than_SalesPrice(List<HashMap<String, String>> data) {

		WebElement returnQty = WebdriverWait.findElement("name", "returnQuantity_o_0");
		returnQty.clear();
		returnQty.sendKeys("1");
		WebElement returnPrice = WebdriverWait.findElement("name", "returnPrice_o_0");
		returnPrice.clear();
		returnPrice.sendKeys("99999999999999999999999999999");
		WebdriverWait.findElement("xpath", "//*[@value='Update']").click();//Update btn
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo("Yet to add validation msg since the appln is throwing exception.");
	}

	public static void validate_by_updating_ReturnPrice_with_Neg_Value(List<HashMap<String, String>> data) {
		//Load Items

		WebElement returnQty = WebdriverWait.findElement("name", "returnQuantity_o_0");
		returnQty.clear();
		returnQty.sendKeys("1");
		WebElement returnPrice = WebdriverWait.findElement("name", "returnPrice_o_0");
		returnPrice.clear();
		returnPrice.sendKeys("-1");
		WebdriverWait.findElement("xpath", "//*[@value='Update']").click();//Update btn
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo("Yet to add validation msg since the appln is accepting negative value.");
	}

	public static void validate_by_updating_ReturnPrice_with_SPL_Characters(List<HashMap<String, String>> data) {

		WebElement returnQty = WebdriverWait.findElement("name", "returnQuantity_o_0");
		returnQty.clear();
		returnQty.sendKeys("1");
		WebElement returnPrice = WebdriverWait.findElement("name", "returnPrice_o_0");
		returnPrice.clear();
		returnPrice.sendKeys(data.get(0).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Update']").click();//Update btn
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo("Yet to add validation msg since the appln is throwing exception.");
	}

	public static void validate_by_updating_ReturnPrice_less_than_SalesPrice(List<HashMap<String, String>> data) {

		WebElement returnQty = WebdriverWait.findElement("name", "returnQuantity_o_0");
		returnQty.clear();
		returnQty.sendKeys("1");
		WebElement returnPrice = WebdriverWait.findElement("name", "returnPrice_o_0");
		returnPrice.clear();
		returnPrice.sendKeys("0.5");
		WebdriverWait.findElement("xpath", "//*[@value='Update']").click();//Update btn
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_ReturnItems_softAssert.assertThat(actMsg).isEqualTo("Yet to add validation msg since the appln is throwing exception.");
	}

	public static void Sales_ReturnItems_softAssert() {
		Sales_ReturnItems_softAssert.assertAll();
	}
	}


