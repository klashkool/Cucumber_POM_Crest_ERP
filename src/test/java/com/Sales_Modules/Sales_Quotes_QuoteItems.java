package com.Sales_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Sales_Quotes_QuoteItems extends Base {

	@Rule
	public static JUnitSoftAssertions Sales_QuotesItems_softAssert = new JUnitSoftAssertions();

	public static void Add_Products(List<HashMap<String, String>> data) throws InterruptedException {

		WebdriverWait.findElement("link", "Quote Items").click();

		for (int p = 0; p <= 4; p++) {

			Thread.sleep(1000);
			WebdriverWait.findElement("name", "productId").sendKeys(data.get(p).get("Column2"));

			Select uom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
			uom_dd.selectByVisibleText(data.get(p).get("Column10"));

			WebdriverWait.findElement("name", "quantity").sendKeys(data.get(p).get("Column9"));

			Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
			dept_dd.selectByVisibleText(data.get(p).get("Column8"));

			Select fac_dd = new Select(WebdriverWait.findElement("id", "facilityId"));
			fac_dd.selectByVisibleText(data.get(p).get("Column7"));
			WebElement ATP = WebdriverWait.findElement("id", "ATP");
			WebElement QOH = WebdriverWait.findElement("id", "QOH");
			Assert.assertEquals(true, ATP.isDisplayed());
			Assert.assertEquals(true, QOH.isDisplayed());

			WebElement price = WebdriverWait.findElement("id", "quoteUnitPrice");
			Assert.assertEquals(true, price.isDisplayed());

			Select disc_dd = new Select(WebdriverWait.findElement("id", "discountType"));
			disc_dd.selectByVisibleText(data.get(p).get("Column16"));

			WebdriverWait.findElement("id", "discount").sendKeys(data.get(p).get("Column17"));

			WebdriverWait.findElement("name", "comments").sendKeys("SO QuoteTest");
			WebdriverWait.findElement("xpath", "//*[@id='createQuoteItem']/table/tbody[1]/tr[7]/td[1]/input").click();

		}

	}

	/////////////////////////////////////////////Negative Scenarios///////////////////////////////////////////

	public static void click_AddItem_Btn() {
		WebdriverWait.findElement("xpath", ".//*[@value='Add Item']").click();
	}

	public static void click_Update_Btn() {
		WebdriverWait.findElement("xpath", ".//*[@value='Update']").click();
	}

	public static void Validating_QuoteItems_without_adding_mandatory_details(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("link", "Quote Items").click();
		click_AddItem_Btn();
		String path1 = "//*[@id='content-messages']/div/ul/li[";
		String path2 = "]";
		for(int i=1; i<=7; i++) {
			String all_ValidationMsg = WebdriverWait.findElement("xpath", path1+i+path2).getText();
			if(i==1) {
				Sales_QuotesItems_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(4).get("Column3"));
			}
			else if (i == 2) {
				Sales_QuotesItems_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(1).get("Column11"));
			} else if (i == 3) {
				Sales_QuotesItems_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(1).get("Column12"));
			} else if (i == 4) {
				Sales_QuotesItems_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(0).get("Column10"));
			} else if (i == 5) {
				Sales_QuotesItems_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(1).get("Column10"));
			} else if (i == 6) {
				Sales_QuotesItems_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(0).get("Column8"));
			} else if (i == 7) {
				Sales_QuotesItems_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(0).get("Column9"));
			}
	}
	}

	public static String verify_Validation_Msg(List<HashMap<String, String>> data, int row, String colName) {
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_QuotesItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(row).get(colName));
		return validationMsg;
	}

	public static void clearQuoteItemsFields() {
			WebdriverWait.findElement("name", "productId").clear();
			WebdriverWait.findElement("name", "quantity").clear();
			WebdriverWait.findElement("id", "discount").clear();
			WebdriverWait.findElement("name", "comments").clear();
		}

	public static void Validate_ProductID_with_InvalidData(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {

		for(int i=0; i<=5; i++) {
			WebdriverWait.findElement("name", "productId").sendKeys(data.get(i).get("Column1"));
			Select uom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
			uom_dd.selectByVisibleText(data_Positive.get(0).get("Column10"));

			WebdriverWait.findElement("name", "quantity").sendKeys(data_Positive.get(0).get("Column9"));

			Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
			dept_dd.selectByVisibleText(data_Positive.get(0).get("Column8"));

			Select fac_dd = new Select(WebdriverWait.findElement("id", "facilityId"));
			fac_dd.selectByVisibleText(data_Positive.get(0).get("Column7"));
			WebElement ATP = WebdriverWait.findElement("id", "ATP");
			WebElement QOH = WebdriverWait.findElement("id", "QOH");
			Assert.assertEquals(true, ATP.isDisplayed());
			Assert.assertEquals(true, QOH.isDisplayed());

			WebElement price = WebdriverWait.findElement("id", "quoteUnitPrice");
			Assert.assertEquals(true, price.isDisplayed());

			Select disc_dd = new Select(WebdriverWait.findElement("id", "discountType"));
			disc_dd.selectByVisibleText(data_Positive.get(0).get("Column16"));

			WebdriverWait.findElement("id", "discount").sendKeys(data_Positive.get(0).get("Column17"));

			WebdriverWait.findElement("name", "comments").sendKeys("SO QuoteTest");
			click_AddItem_Btn();
			// verify_Validation_Msg(data, 5, "Column3");
			//WebdriverWait.findElement("name", "productId").clear();
			WebdriverWait.findElement("link", "Quote Items").click();
		}
	}

	public static void QuoteItems_fillDetails_without_Product_UOM_Qty(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data_Positive.get(0).get("Column8"));

		Select fac_dd = new Select(WebdriverWait.findElement("id", "facilityId"));
		fac_dd.selectByVisibleText(data_Positive.get(0).get("Column7"));
		WebElement ATP = WebdriverWait.findElement("id", "ATP");
		WebElement QOH = WebdriverWait.findElement("id", "QOH");
		Assert.assertEquals(true, ATP.isDisplayed());
		Assert.assertEquals(true, QOH.isDisplayed());

		WebElement price = WebdriverWait.findElement("id", "quoteUnitPrice");
		Assert.assertEquals(true, price.isDisplayed());

		Select disc_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		disc_dd.selectByVisibleText(data_Positive.get(0).get("Column16"));

		WebdriverWait.findElement("id", "discount").sendKeys(data_Positive.get(0).get("Column17"));

		WebdriverWait.findElement("name", "comments").sendKeys("SO QuoteTest");
		click_AddItem_Btn();
	}
	public static void Validate_Qty_with_Spl_Characters(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
			clearQuoteItemsFields();
			WebdriverWait.findElement("name", "productId").sendKeys(data_Positive.get(0).get("Column2"));
			Select uom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
			uom_dd.selectByVisibleText(data_Positive.get(0).get("Column10"));
			WebdriverWait.findElement("name", "quantity").sendKeys(data.get(0).get("Column1"));
			QuoteItems_fillDetails_without_Product_UOM_Qty(data, data_Positive);
			String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
			Sales_QuotesItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(11).get("Column10"));
			String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
			Sales_QuotesItems_softAssert.assertThat(validationMsg1).isEqualTo(data.get(1).get("Column10"));
			//WebdriverWait.findElement("name", "quantity").clear();
			WebdriverWait.findElement("link", "Quote Items").click();
	}

	public static void Validate_Qty_with_Neg_Value(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		clearQuoteItemsFields();
		WebdriverWait.findElement("name", "productId").sendKeys(data_Positive.get(0).get("Column2"));
		Select uom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
		uom_dd.selectByVisibleText(data_Positive.get(0).get("Column10"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(1).get("Column1"));
		QuoteItems_fillDetails_without_Product_UOM_Qty(data, data_Positive);
		String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		Sales_QuotesItems_softAssert.assertThat(validationMsg1).isEqualTo(data.get(11).get("Column10"));
		String validationMsg2 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
		Sales_QuotesItems_softAssert.assertThat(validationMsg2).isEqualTo(data.get(1).get("Column10"));
	//	WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("link", "Quote Items").click();
	}
	public static void Validate_Discount_with_Spl_Characters(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		WebdriverWait.findElement("link", "Quote Items").click();
		clearQuoteItemsFields();
		WebdriverWait.findElement("name", "productId").sendKeys(data_Positive.get(0).get("Column2"));
		Select uom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
		uom_dd.selectByVisibleText(data_Positive.get(0).get("Column10"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data_Positive.get(0).get("Column9"));
		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data_Positive.get(0).get("Column8"));

		Select fac_dd = new Select(WebdriverWait.findElement("id", "facilityId"));
		fac_dd.selectByVisibleText(data_Positive.get(0).get("Column7"));
		WebElement ATP = WebdriverWait.findElement("id", "ATP");
		WebElement QOH = WebdriverWait.findElement("id", "QOH");
		Assert.assertEquals(true, ATP.isDisplayed());
		Assert.assertEquals(true, QOH.isDisplayed());

		WebElement price = WebdriverWait.findElement("id", "quoteUnitPrice");
		Assert.assertEquals(true, price.isDisplayed());

		Select disc_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		disc_dd.selectByVisibleText(data_Positive.get(0).get("Column16"));

		WebdriverWait.findElement("id", "discount").sendKeys(data.get(0).get("Column1"));

		WebdriverWait.findElement("name", "comments").sendKeys("SO QuoteTest");
		click_AddItem_Btn();
		String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_QuotesItems_softAssert.assertThat(validationMsg1).isEqualTo(data.get(0).get("Column14"));
		//WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("link", "Quote Items").click();
	}

	public static void Validate_with_DiscountType_as_Percentage_and_Discount_as_Empty(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		WebdriverWait.findElement("link", "Quote Items").click();
		clearQuoteItemsFields();
		WebdriverWait.findElement("name", "productId").sendKeys(data_Positive.get(0).get("Column2"));
		Select uom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
		uom_dd.selectByVisibleText(data_Positive.get(0).get("Column10"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data_Positive.get(0).get("Column9"));
		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data_Positive.get(0).get("Column8"));

		Select fac_dd = new Select(WebdriverWait.findElement("id", "facilityId"));
		fac_dd.selectByVisibleText(data_Positive.get(0).get("Column7"));
		WebElement ATP = WebdriverWait.findElement("id", "ATP");
		WebElement QOH = WebdriverWait.findElement("id", "QOH");
		Assert.assertEquals(true, ATP.isDisplayed());
		Assert.assertEquals(true, QOH.isDisplayed());

		WebElement price = WebdriverWait.findElement("id", "quoteUnitPrice");
		Assert.assertEquals(true, price.isDisplayed());

		Select disc_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		disc_dd.selectByVisibleText(data_Positive.get(0).get("Column16"));

		//WebdriverWait.findElement("id", "discount").sendKeys(data.get(0).get("Column1"));

		WebdriverWait.findElement("name", "comments").sendKeys("SO QuoteTest");
		click_AddItem_Btn();
		try {
			if(driver.findElement(By.xpath("//*[@id='content-messages']/div/ul/li")).isDisplayed()) {
				String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
			Sales_QuotesItems_softAssert.assertThat(validationMsg1).isEqualTo(data.get(1).get("Column14"));
			}
		}catch(Exception e) {
			Sales_QuotesItems_softAssert.fail("Application is adding line item when discount type is selected and discount field is empty");
		}
		//need to get confirmation on validation msg present in test data since appln is giving success msg for this scenario.
		//WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("link", "Quote Items").click();
	}

	public static void Validate_with_Discount_Value_and_without_DiscountType(List<HashMap<String, String>> data, List<HashMap<String, String>> data_Positive) {
		WebdriverWait.findElement("link", "Quote Items").click();
		clearQuoteItemsFields();
		WebdriverWait.findElement("name", "productId").sendKeys(data_Positive.get(0).get("Column2"));
		Select uom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
		uom_dd.selectByVisibleText(data_Positive.get(0).get("Column10"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data_Positive.get(0).get("Column9"));
		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data_Positive.get(0).get("Column8"));

		Select fac_dd = new Select(WebdriverWait.findElement("id", "facilityId"));
		fac_dd.selectByVisibleText(data_Positive.get(0).get("Column7"));
		WebElement ATP = WebdriverWait.findElement("id", "ATP");
		WebElement QOH = WebdriverWait.findElement("id", "QOH");
		Assert.assertEquals(true, ATP.isDisplayed());
		Assert.assertEquals(true, QOH.isDisplayed());

		WebElement price = WebdriverWait.findElement("id", "quoteUnitPrice");
		Assert.assertEquals(true, price.isDisplayed());
		Select disc_dd = new Select(WebdriverWait.findElement("id", "discountType"));
		disc_dd.selectByVisibleText("-Select-");
		WebdriverWait.findElement("id", "discount").sendKeys(data_Positive.get(0).get("Column17"));
		WebdriverWait.findElement("name", "comments").sendKeys("SO QuoteTest");
		click_AddItem_Btn();
		try {
			if(driver.findElement(By.xpath("//*[@id='content-messages']/div/ul/li")).isDisplayed()) {
				String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				Sales_QuotesItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(4).get("Column14"));
			}
		}catch(Exception e) {
			Sales_QuotesItems_softAssert.fail("Application is adding line item when discount type is not selected and discount value is entered");
		}
		WebdriverWait.findElement("link", "Quote Items").click();

	}

	public static void Validate_with_negativeValue_and_spl_characters_as_Qty_in_added_lineItem(List<HashMap<String, String>> data) {
		for(int i=0; i<=1; i++) {
		WebElement editQty = driver.findElement(By.id("quantity_0"));
		editQty.clear();
		editQty.sendKeys(data.get(i).get("Column1"));
		click_Update_Btn();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_QuotesItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(6).get("Column10"));
		}
	}

	public static void Validate_by_making_empty_Qty_in_added_lineItem(List<HashMap<String, String>> data) {
		WebElement editQty = driver.findElement(By.id("quantity_0"));
		editQty.clear();
		click_Update_Btn();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_QuotesItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(7).get("Column10"));
	}

	public static void Validate_by_updating_Comments_in_added_lineItem(List<HashMap<String, String>> data) {
		WebElement editQty = driver.findElement(By.id("comments_0"));
		editQty.clear();
		editQty.sendKeys("Comments updated");
		click_Update_Btn();
		//isAlertPresent();
		//driver.switchTo().alert().accept();
		String successMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		Sales_QuotesItems_softAssert.assertThat(successMsg).isEqualTo(data.get(13).get("Column17"));
	}

	public static void Validate_by_updating_Quote_lineItem_without_discount_value(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("id", "discount_0").clear();
		click_Update_Btn();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_QuotesItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(5).get("Column14"));
	}

	public static void Validate_by_updating_Quote_lineItem_without_discountType(List<HashMap<String, String>> data) {
		Select disc_dd = new Select(WebdriverWait.findElement("id", "discountType_0"));
		disc_dd.selectByVisibleText("-Select-");
		click_Update_Btn();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_QuotesItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(6).get("Column14"));
	}

	public static boolean isAlertPresent() {
	    try {
	        driver.switchTo().alert().accept();
	        return true;
	    } // try
	    catch (Exception e) {
	        return false;
	    } // catch
	}

	public static void Validate_alert_message_while_removing_lineItem(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("xpath", "//*[@id='updateQuoteItem']/table/tbody/tr/td[14]/a").click();
		// WebdriverWait.findElement("xpath",
		// "//*[@id='updateQuoteItem']//table//tbody//tr//td//a").click();
		//isAlertPresent();
		String validationMsg = driver.switchTo().alert().getText();
		Sales_QuotesItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(4).get("Column16"));
		driver.switchTo().alert().dismiss();
	}

	public static void Validate_Quote_Note_without_description(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("xpath", "//*[@value='Add Note']").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_QuotesItems_softAssert.assertThat(validationMsg).isEqualTo(data.get(0).get("Column13"));
	}

	public static void Validate_by_updating_Quote_Note(List<HashMap<String, String>> data) throws InterruptedException {
		WebdriverWait.findElement("id", "noteName").sendKeys("Test Note Name");
		WebdriverWait.findElement("id", "noteInfo").sendKeys("Test Note Information");
		Thread.sleep(2000);
		WebdriverWait.findElement("xpath", "//*[@value='Add Note']").click();
		Thread.sleep(2000);
		WebdriverWait.findElement("xpath", "//*[@id='EditQuoteNote']/input[1]").click();//update
		Thread.sleep(2000);
		WebdriverWait.findElement("id", "noteInfo").sendKeys("Test Note Information updated");
		WebdriverWait.findElement("xpath", "//*[@id='updateQuoteNote']/div/table/tbody[1]/tr[4]/td/input").click();//update
		String successMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		Sales_QuotesItems_softAssert.assertThat(successMsg).isEqualTo(data.get(10).get("Column17"));
	}

	public static void Validate_alert_message_while_removing_QuoteNote(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("xpath", "//*[@value='Remove']").click();
		//isAlertPresent();
		String validationMsg = driver.switchTo().alert().getText();
		Sales_QuotesItems_softAssert.assertThat(validationMsg).isEqualTo("alert msg yet to be added");
		driver.switchTo().alert().dismiss();
	}

	public static void Sales_Quote_QuoteItems_assertions() {
		Sales_QuotesItems_softAssert.assertAll();
	}
}
