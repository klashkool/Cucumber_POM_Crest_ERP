package com.CRM;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class CRM_Activities extends Base {

	@Rule
	public static JUnitSoftAssertions activity_softAssert = new JUnitSoftAssertions();

	public static void clickCreateActivity() {
		//WebdriverWait.findElement("xpath", "//*[@id='myHoverTitle_0']/a/img").click();
		WebdriverWait.findElement("link", "Create Activity").click();
	}
	
	public static void createActivity(List<HashMap<String, String>> data, String sheetName) throws InterruptedException {
		clickCreateActivity();
		WebdriverWait.findElement("id", "partyId").click();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
//		Select partyDD = new Select(WebdriverWait.findElement("id", "partyId"));
//		partyDD.selectByVisibleText(data.get(20).get("Column1"));
//		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='partyId']//option");
//        for (WebElement option : options) {
//            if (option.getText().contains(data.get(20).get("Column1"))) {
//                option.click();
//                break;
//            }
//        }
//        Thread.sleep(1000);
		WebdriverWait.findElement("id", "workEffortName").sendKeys(data.get(56).get("Column2"));
		//WebdriverWait.findElement("id", "partyId").sendKeys(data.get(20).get("Column3"));
		WebdriverWait.findElement("id", "description").sendKeys(data.get(56).get("Column4"));
//		Select typeDD = new Select(WebdriverWait.findElement("id", "workEffortTypeId"));
//		typeDD.selectByVisibleText(data.get(20).get("Column5"));
//		Select statusDD = new Select(WebdriverWait.findElement("id", "currentStatusId"));
//		statusDD.selectByVisibleText(data.get(20).get("Column6"));
//		Select priorityDD = new Select(WebdriverWait.findElement("id", "priority"));
//		priorityDD.selectByVisibleText(data.get(20).get("Column7"));
//		Select purposeDD = new Select(WebdriverWait.findElement("id", "workEffortPurposeTypeId"));
//		purposeDD.selectByVisibleText(data.get(20).get("Column8"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = (dateFormat.format(new Date()));
//		WebdriverWait.findElement("id", "estimatedStartDate_i18n").sendKeys(currentDate);
		ExcelWriter.writeExcelFile(sheetName, 58, 9, currentDate);
//		WebdriverWait.findElement("id", "estimatedCompletionDate_i18n").sendKeys(currentDate);
		ExcelWriter.writeExcelFile(sheetName, 58, 10, currentDate);
		WebdriverWait.findElement("xpath", "//*[@type='submit']").click();
		String getSuccessMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		activity_softAssert.assertThat(getSuccessMsg).isEqualTo("Activity created successfully.");
	}
	
	public static void activity_assertions() {
		activity_softAssert.assertAll();
	}
}
