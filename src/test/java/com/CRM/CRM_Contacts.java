package com.CRM;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class CRM_Contacts extends Base {
	
	public static void clickCreateContact() {
		WebdriverWait.findElement("link", "Create Contact").click();
	}
	
	public static void enterContactInfo(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("id", "address1").sendKeys(data.get(41).get("Column1"));
		WebdriverWait.findElement("id", "address2").sendKeys(data.get(41).get("Column2"));
		Select countryDD = new Select(WebdriverWait.findElement("id", "createLead_countryGeoId"));
		countryDD.selectByVisibleText(data.get(41).get("Column3"));
		Select stateDD = new Select(WebdriverWait.findElement("id", "createLead_stateProvinceGeoId"));
		stateDD.selectByVisibleText(data.get(41).get("Column4"));
		WebdriverWait.findElement("id", "city").sendKeys(data.get(41).get("Column5"));
		WebdriverWait.findElement("id", "postalCode").sendKeys(data.get(41).get("Column6"));
		WebdriverWait.findElement("id", "countryCode").sendKeys(data.get(41).get("Column7"));
		WebdriverWait.findElement("id", "contactNumber").sendKeys(data.get(41).get("Column8"));
		WebdriverWait.findElement("id", "emailAddress").sendKeys(data.get(41).get("Column9"));
	}
}
