package com.Sales_Modules;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.openqa.selenium.support.ui.Select;

import com.Utils.WebdriverWait;

public class Sales_SalesReturn {


	public static JUnitSoftAssertions Sales_Return_softAssert = new JUnitSoftAssertions();

	public static void Sales_Return(List<HashMap<String, String>> data, String sheetname) {

		WebdriverWait.findElement("link", "SALES").click();
		WebdriverWait.findElement("link", "Sales Return").click();
		WebdriverWait.findElement("link", "Create Sales Return Order").click();

		WebdriverWait.findElement("id", "0_lookupId_fromPartyId").sendKeys(data.get(0).get("Column4"));

		// Adding Current Date(From Date)

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String Fromdate = (dateFormat.format(new Date()));
		WebdriverWait.findElement("name", "entryDate_i18n").sendKeys(Fromdate);

		Select fac_dd = new Select(WebdriverWait.findElement("id", "destinationFacilityId"));
		fac_dd.selectByVisibleText(data.get(1).get("Facility-ShipGroup"));

		Select curr_dd = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		curr_dd.selectByVisibleText(data.get(0).get("Currency"));

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/div/input").click();
		Sales_SalesReturn_ReturnHeader.Capture_ReturnID(sheetname);
		Sales_SalesReturn_ReturnItems.Return_items(data);
		Sales_SalesReturn_ReturnHeader.Update_ReturnHeader();
	}

	public static void CreateSalesReturn(List<HashMap<String, String>> data, String sheetname) throws InterruptedException {

		WebdriverWait.findElement("link", "SALES").click();

		WebdriverWait.findElement("link", "Sales Return").click();

		WebdriverWait.findElement("link", "Create Sales Return Order").click();

		WebdriverWait.findElement("id", "0_lookupId_fromPartyId").sendKeys(data.get(0).get("Column4"));

		Select facility_dd = new Select(WebdriverWait.findElement("id", "destinationFacilityId"));
		facility_dd.selectByVisibleText(data.get(1).get("Column7"));
		//Click Create btn
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/div/div[2]/div/input").click();
	}


	////////////////////////////////////// Negative
	////////////////////////////////////// Scenarios/////////////////////////////////////////////////

	public static void Navigate_To_Create_SalesReturn() {
		WebdriverWait.findElement("link", "SALES").click();
		WebdriverWait.findElement("link", "Sales Return").click();
		WebdriverWait.findElement("link", "Create Sales Return Order").click();
	}

	public static void Navigate_To_SalesReturn() {
		WebdriverWait.findElement("link", "SALES").click();
		WebdriverWait.findElement("link", "Sales Return").click();
	}

	public static void Validating_SalesReturn_date_range_with_invalid_date(List<HashMap<String, String>> data) {
		// SalesReturn Date - From
		WebdriverWait.findElement("id", "minDate_i18n").sendKeys(data.get(8).get("Column1"));
		// SalesReturn Date - Thru
		WebdriverWait.findElement("id", "maxDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Search']").click();
		String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_Return_softAssert.assertThat(validationMsg1).isEqualTo("Thru Date must be greater than or equal to From Date.");
	}

	public static void Validate_Create_SalesReturn_without_Mandatory_Fields(List<HashMap<String, String>> data) {
		Navigate_To_Create_SalesReturn();
		WebdriverWait.findElement("id", "entryDate_i18n").clear();

		Select ordType_dd = new Select(WebdriverWait.findElement("id", "toPartyId"));
		ordType_dd.selectByVisibleText("-Select-");

		Select currency_dd = new Select(WebdriverWait.findElement("id", "destinationFacilityId"));
		currency_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();

		String path1 = "//*[@id='content-messages']/div/ul/li[";
		String path2 = "]";
		for (int i = 1; i <= 4; i++) {
			String all_ValidationMsg = WebdriverWait.findElement("xpath", path1 + i + path2).getText();
			if (i == 1) {
				Sales_Return_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(18).get("Column2"));
			} else if (i == 2) {
				Sales_Return_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(11).get("Column8"));
			} else if (i == 3) {
				Sales_Return_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(8).get("Column5"));
			} else if (i == 4) {
				Sales_Return_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(9).get("Column5"));
			}
		}
	}

	public static void Validate_FromParty_with_InvalidData(List<HashMap<String, String>> data) {
		Navigate_To_Create_SalesReturn();
		WebdriverWait.findElement("id", "0_lookupId_fromPartyId").sendKeys(data.get(5).get("Column1"));
		Select facility_dd = new Select(WebdriverWait.findElement("id", "destinationFacilityId"));
		facility_dd.selectByIndex(1);
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_Return_softAssert.assertThat(actMsg).isEqualTo(data.get(10).get("Column5"));
		WebdriverWait.findElement("id", "0_lookupId_fromPartyId").clear();
	}

	public static void Validate_Cancel_button_navigating_to_SalesReturnSearchPage() {
		WebdriverWait.findElement("link", "Cancel").click();
		boolean verifySearchPageIsDisplayed = WebdriverWait.findElement("link", "Create Sales Return Order")
				.isDisplayed();
		Sales_Return_softAssert.assertThat(verifySearchPageIsDisplayed).isEqualTo(true);
	}

	public static void create_SalesReturn_Neg_flow(List<HashMap<String, String>> data_Positive) {
		Navigate_To_Create_SalesReturn();
		WebdriverWait.findElement("id", "0_lookupId_fromPartyId").sendKeys(data_Positive.get(0).get("Column4"));
		Select Fac_dd = new Select(WebdriverWait.findElement("id", "destinationFacilityId"));
		Fac_dd.selectByVisibleText(data_Positive.get(0).get("Column7"));
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
	}

	public static void Sales_Return_assertions() {
		Sales_Return_softAssert.assertAll();
	}
}
