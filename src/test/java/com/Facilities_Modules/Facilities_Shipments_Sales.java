package com.Facilities_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Sales_Modules.Sales_SalesOrder;
import com.Sales_Modules.Sales_SalesOrder_View;
import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Facilities_Shipments_Sales extends Base {

	@Rule
	public static JUnitSoftAssertions SalesShipment_softAssert = new JUnitSoftAssertions();

	public static Logger log = Logger.getLogger(Facilities_Shipments_Sales.class);

	public static void navigateToShipments() {
		WebdriverWait.findElement("link", "FACILITIES").click();
		WebdriverWait.findElement("link", "Stock Management").click();
		WebdriverWait.findElement("link", "Shipments").click();
	}

	public static void clickCreateShipmentLink() {
		WebdriverWait.findElement("link", "Create Shipment").click();
	}

	public static void selectShipmentType(String shipType) {
		Select shipTypeDD = new Select(WebdriverWait.findElement("id", "shipmentTypeId"));
		shipTypeDD.selectByVisibleText(shipType);
	}

	public static void click_Create_btn() {
		WebdriverWait.findElement("id", "createSubmit").click();
	}


//////////////////////////////////////////Negative Scenarios////////////////////////////////////////////////////
	public static void MakePartialSalesShipmentAndUpdateTheStatusToShipped(List<HashMap<String, String>> data_Positive) throws InterruptedException {
		Sales_SalesOrder_View.ApproveSalesOrder();
		Sales_SalesOrder_View.ClickCreateNewShipment();
		WebdriverWait.findElement("id", "createSubmit").click();
		Facilities_Shipments_ShipmentPlan.AddShipmentPlan_NegFlow();
		Facilities_Shipments_OrderItems_Sales.SalesShipment_Issue_Partial_Items_NegFlow(data_Positive);
		Facilities_Shipments_Edit.ShpStatus();
		Facilities_Shipments_View.Capture_ShpID("Sales_Valid_data_NegFlow", 13);
	}

	public static void Validating_Shipment_date_range_with_invalid_date(List<HashMap<String, String>> data) {
		// Estimated Ship Date - From
		WebdriverWait.findElement("id", "estimatedShipDate_fld0_value_i18n").sendKeys(data.get(8).get("Column1"));
		// Estimated Ship Date - Thru
		WebdriverWait.findElement("id", "estimatedShipDate_fld1_value_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Search']").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesShipment_softAssert.assertThat(validationMsg).isEqualTo(data.get(23).get("Column2"));
		WebdriverWait.findElement("id", "estimatedShipDate_fld0_value_i18n").clear();
		WebdriverWait.findElement("id", "estimatedShipDate_fld1_value_i18n").clear();
		// Entry Date - From
		WebdriverWait.findElement("id", "entryDate_fld0_value_i18n").sendKeys(data.get(8).get("Column1"));
		// Entry Date - Thru
		WebdriverWait.findElement("id", "entryDate_fld1_value_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Search']").click();
		String validationMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesShipment_softAssert.assertThat(validationMsg1).isEqualTo(data.get(24).get("Column2"));
	}

	public static void Validating_without_Mandatory_details(List<HashMap<String, String>> data) {
		selectShipmentType("Sales Shipment");
		Select statusIdDD = new Select(WebdriverWait.findElement("id", "statusId"));
		statusIdDD.selectByVisibleText("-Select-");
		click_Create_btn();
		String path1 = "//*[@id='content-messages']/div/ul/li[";
		String path2 = "]";
		for (int i = 1; i <= 5; i++) {
			String all_ValidationMsg = WebdriverWait.findElement("xpath", path1 + i + path2).getText();
			if (i == 1) {
				SalesShipment_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(13).get("Column21"));
			} else if (i == 2) {
				SalesShipment_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(14).get("Column21"));
			} else if (i == 3) {
				SalesShipment_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(9).get("Column8"));
			} else if (i == 4) {
				SalesShipment_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(1).get("Column7"));
			} else if (i == 5) {
				SalesShipment_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(12).get("Column8"));
			}
		}
	}

	public static void Validate_BackToSearch_button_navigating_to_ShipmentsSearchPage() {
		WebdriverWait.findElement("link", "Back to search").click();
		String verifySearchPageIsDisplayed = driver.getTitle();
		SalesShipment_softAssert.assertThat(verifySearchPageIsDisplayed).isEqualTo("Crest: Find Shipment(s)");
	}

	public static void Validate_Cancel_button_navigating_to_ShipmentsSearchPage() {
		Facilities_Shipments_Sales.clickCreateShipmentLink();
		Facilities_Shipments_Sales.selectingShipmentType("Sales Shipment");
		WebdriverWait.findElement("link", "Cancel").click();
		String verifySearchPageIsDisplayed = driver.getTitle();
		SalesShipment_softAssert.assertThat(verifySearchPageIsDisplayed).isEqualTo("Crest: Find Shipment(s)");
	}

	public static void Validate_PrimaryOrderID_with_InvalidData(List<HashMap<String, String>> data) {
		Facilities_Shipments_Sales.clickCreateShipmentLink();
		Facilities_Shipments_Sales.selectingShipmentType("Sales Shipment");
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data.get(5).get("Column1"));
		Select currency_dd = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		currency_dd.selectByIndex(2);
		Select originFacility_dd = new Select(WebdriverWait.findElement("id", "originFacilityId"));
		originFacility_dd.selectByIndex(2);
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		SalesShipment_softAssert.assertThat(actMsg).isEqualTo("Entered Primary Order ID "+data.get(5).get("Column1")+" is not a Sales Order");
		String actMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
		SalesShipment_softAssert.assertThat(actMsg1).isEqualTo(data.get(9).get("Column8"));
	}

	public static void Validate_PrimaryOrderID_with_PO_ID(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data.get(15).get("Column1"));
		Select currency_dd = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		currency_dd.selectByIndex(2);
		Select originFacility_dd = new Select(WebdriverWait.findElement("id", "originFacilityId"));
		originFacility_dd.selectByIndex(2);
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesShipment_softAssert.assertThat(actMsg).isEqualTo("Entered Primary Order ID "+data.get(15).get("Column1")+" is not a Sales Order");
	}

	public static void Validate_PrimaryOrderID_with_SO_ID_in_CreatedStatus(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data.get(10).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
	//	SalesShipment_softAssert.assertThat(actMsg).isEqualTo("Primary Order ID "+data.get(10).get("Column1")+" is not in Approved Status");
	if(actMsg.contains("is not in Approved Status")) {
		SalesShipment_softAssert.wasSuccess();
	}else {
		SalesShipment_softAssert.fail("SO ID with Created Status Flow : Expected validation message is not displayed");
	}
	}

	public static void Validate_PrimaryOrderID_with_SO_ID_in_CompletedStatus(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data.get(11).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
	//	SalesShipment_softAssert.assertThat(actMsg).isEqualTo("Primary Order ID "+data.get(11).get("Column1")+" is not in Approved Status");
		if(actMsg.contains("is not in Approved Status")) {
			SalesShipment_softAssert.wasSuccess();
		}else {
			SalesShipment_softAssert.fail("SO ID with Completed Status Flow : Expected validation message is not displayed");
		}
	}

	public static void Validate_PrimaryOrderID_with_SO_ID_in_CancelledStatus(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data.get(17).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		//SalesShipment_softAssert.assertThat(actMsg).isEqualTo("Primary Order ID "+data.get(11).get("Column1")+" is not in Approved Status");
		if(actMsg.contains("is not in Approved Status")) {
			SalesShipment_softAssert.wasSuccess();
		}else {
			SalesShipment_softAssert.fail("SO ID with Cancelled Status Flow : Expected validation message is not displayed");
		}
	}

	public static void Validate_PrimaryOrderID_with_SO_ID_in_HeldStatus(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data.get(12).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
	//	SalesShipment_softAssert.assertThat(actMsg).isEqualTo("Primary Order ID "+data.get(12).get("Column1")+" is not in Approved Status");
		if(actMsg.contains("is not in Approved Status")) {
			SalesShipment_softAssert.wasSuccess();
		}else {
			SalesShipment_softAssert.fail("SO ID with Held Status Flow : Expected validation message is not displayed");
		}
	}

	public static void selectingShipmentType(String ShipType) {
		Select shipmentType_dd = new Select(WebdriverWait.findElement("id", "shipmentTypeId"));
		shipmentType_dd.selectByVisibleText(ShipType);
	}
	public static void Validate_SalesShipment_ERD_ESD_ShipDate_with_PastDate(List<HashMap<String, String>> data_positive_TC05, List<HashMap<String, String>> data_TC05_to_read_SO_ID, List<HashMap<String, String>> data) throws InterruptedException {
		Sales_SalesOrder.SO_StartOrder(data_positive_TC05, "SO Offline Payment Flow", "-Select-");
	Sales_SalesOrder.OfflinePayment_Chkbox();
	Sales_SalesOrder.SO_Shipping_Details_SingleFacility(data_positive_TC05);
	Sales_SalesOrder.SO_OrderItems(data_positive_TC05, 4);
	Sales_SalesOrder.SO_ReviewCreateSO("SO_TC05");
	Sales_SalesOrder_View.Approve_SO(data_positive_TC05);
	navigateToShipments();
	clickCreateShipmentLink();
	selectingShipmentType("Sales Shipment");
	WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
	WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data_TC05_to_read_SO_ID.get(13).get("Column4"));
//	WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data.get(13).get("Column1"));
	for(int i=1; i<=3; i++) {
		if(i==1) {
			WebdriverWait.findElement("id", "estimatedReadyDate_i18n").clear();
	WebdriverWait.findElement("id", "estimatedReadyDate_i18n").sendKeys(data.get(7).get("Column1"));
		}else if(i==2) {
			WebdriverWait.findElement("id", "estimatedShipDate_i18n").clear();
			WebdriverWait.findElement("id", "estimatedShipDate_i18n").sendKeys(data.get(7).get("Column1"));
		}else if(i==3) {
			WebdriverWait.findElement("id", "eventDate_i18n").clear();
			WebdriverWait.findElement("id", "eventDate_i18n").sendKeys(data.get(7).get("Column1"));
		}
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		try {
			if(WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").isDisplayed()) {
				String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				SalesShipment_softAssert.assertThat(actMsg).isEqualTo("Yet to add validation msg. Application is accepting the past date");
			}
		}catch(Exception e) {
			SalesShipment_softAssert.fail("Expected Validation message is not displayed");
		}
	}
	}

	public static void Validate_SalesShipment_EstimatedReadyDate_with_PastDate(List<HashMap<String, String>> data_positive_TC05, List<HashMap<String, String>> data_TC05_to_read_SO_ID, List<HashMap<String, String>> data) throws InterruptedException {
//		Sales_SalesOrder.SO_StartOrder(data_positive_TC05, "SO Offline Payment Flow");
//		Sales_SalesOrder.OfflinePayment_Chkbox();
//		Sales_SalesOrder.SO_Shipping_Details_SingleFacility(data_positive_TC05);
//		Sales_SalesOrder.SO_OrderItems(data_positive_TC05, 4);
//		Sales_SalesOrder.SO_ReviewCreateSO("SO_TC05");
//		Sales_SalesOrder_View.Approve_SO(data_positive_TC05);
		navigateToShipments();
		clickCreateShipmentLink();
		selectingShipmentType("Sales Shipment");
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data_TC05_to_read_SO_ID.get(13).get("Column4"));
		WebdriverWait.findElement("id", "estimatedReadyDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesShipment_softAssert.assertThat(actMsg).isEqualTo("Yet to add validation msg. Application is accepting the past date as delivery date");
		}

	public static void Validate_SalesShipment_EstimatedShipDate_with_PastDate(List<HashMap<String, String>> data_positive_TC05, List<HashMap<String, String>> data_TC05_to_read_SO_ID, List<HashMap<String, String>> data) throws InterruptedException {
//		Sales_SalesOrder.SO_StartOrder(data_positive_TC05, "SO Offline Payment Flow");
//		Sales_SalesOrder.OfflinePayment_Chkbox();
//		Sales_SalesOrder.SO_Shipping_Details_SingleFacility(data_positive_TC05);
//		Sales_SalesOrder.SO_OrderItems(data_positive_TC05, 4);
//		Sales_SalesOrder.SO_ReviewCreateSO("SO_TC05");
//		Sales_SalesOrder_View.Approve_SO(data_positive_TC05);
		navigateToShipments();
		clickCreateShipmentLink();
		selectingShipmentType("Sales Shipment");
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data_TC05_to_read_SO_ID.get(13).get("Column4"));
		WebdriverWait.findElement("id", "estimatedShipDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesShipment_softAssert.assertThat(actMsg).isEqualTo("Yet to add validation msg. Application is accepting the past date as delivery date");
		}

	public static void Validate_SalesShipment_ShipmentDate_with_PastDate(List<HashMap<String, String>> data_positive_TC05, List<HashMap<String, String>> data_TC05_to_read_SO_ID, List<HashMap<String, String>> data) throws InterruptedException {
//		Sales_SalesOrder.SO_StartOrder(data_positive_TC05, "SO Offline Payment Flow");
//		Sales_SalesOrder.OfflinePayment_Chkbox();
//		Sales_SalesOrder.SO_Shipping_Details_SingleFacility(data_positive_TC05);
//		Sales_SalesOrder.SO_OrderItems(data_positive_TC05, 4);
//		Sales_SalesOrder.SO_ReviewCreateSO("SO_TC05");
//		Sales_SalesOrder_View.Approve_SO(data_positive_TC05);
		navigateToShipments();
		clickCreateShipmentLink();
		selectingShipmentType("Sales Shipment");
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data_TC05_to_read_SO_ID.get(13).get("Column4"));
		WebdriverWait.findElement("id", "eventDate_i18n").sendKeys(data.get(7).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesShipment_softAssert.assertThat(actMsg).isEqualTo("Yet to add validation msg. Application is accepting the past date as delivery date");
		}

	public static void Validate_CreateSalesShipment_with_all_Status(List<HashMap<String, String>> data_positive_TC05, List<HashMap<String, String>> data_TC05_to_read_SO_ID, List<HashMap<String, String>> data) throws InterruptedException {
		// Sales_SalesOrder.SO_StartOrder(data_positive_TC05, "SO Offline
		// Payment Flow");
		// Sales_SalesOrder.OfflinePayment_Chkbox();
		// Sales_SalesOrder.SO_Shipping_Details_SingleFacility(data_positive_TC05);
		// Sales_SalesOrder.SO_OrderItems(data_positive_TC05, 4);
		// Sales_SalesOrder.SO_ReviewCreateSO("SO_TC05");
		// Sales_SalesOrder_View.Approve_SO(data_positive_TC05);
		navigateToShipments();
		clickCreateShipmentLink();
		selectingShipmentType("Sales Shipment");
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data_TC05_to_read_SO_ID.get(13).get("Column4"));
//		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data.get(13).get("Column1"));
			int j=18;
		for (int i = 1; i <= 6; i++) {
			Select status_dd = new Select(WebdriverWait.findElement("id", "statusId"));
			status_dd.selectByVisibleText(data.get(j).get("Column1"));
			// WebdriverWait.findElement("xpath",
			// "//*[@value='Create']").click();
			WebdriverWait.findElement("id", "createSubmit").click();
			if(WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").isDisplayed()) {
				String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				// SalesShipment_softAssert.assertThat(actMsg).isEqualTo("Yet to
				// add validation msg. Application is throwing exception");
			}else {
					SalesShipment_softAssert.fail("Expected Validation message is not displayed");
				}
			j++;
			}
		}


	public static void clickAddBtn() {
		WebdriverWait.findElement("xpath", "//*[@value='Add']").click();
	}
	public static void selectCheckBoxAndclickIssueBtn() {
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("id", "submitButton").click();
	}
	public static void clickShipmentPlanLink() {
		WebdriverWait.findElement("link", "Shipment Plan").click();
	}
	public static void clickOrderItemsLink() {
		WebdriverWait.findElement("link", "Order Items").click();
	}
	public static void Validate_SalesShipmentPlan_Qty_with_InvalidData(List<HashMap<String, String>> data_positive_TC05, List<HashMap<String, String>> data_TC05_to_read_SO_ID, List<HashMap<String, String>> data) throws InterruptedException {
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data_TC05_to_read_SO_ID.get(13).get("Column4"));
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		clickShipmentPlanLink();
		WebdriverWait.findElement("xpath", "//*[@value='Submit']").click();
		for(int i=0; i<=4; i++) {
			WebdriverWait.findElement("xpath", "//*[@value='Submit']").click();
			WebElement QtyTxtField = WebdriverWait.findElement("id", "quantity_o_0");
			QtyTxtField.clear();
			if(i==0) {
				QtyTxtField.sendKeys(data.get(5).get("Column1"));
				clickAddBtn();
			}else if(i==1) {
				QtyTxtField.sendKeys(data.get(0).get("Column1"));
				clickAddBtn();
			}else if(i==2) {
				QtyTxtField.sendKeys(data.get(14).get("Column1"));
				clickAddBtn();
			String actMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				SalesShipment_softAssert.assertThat(actMsg1).containsIgnoringCase("Not adding Order Item to plan for shipment");
			}else if(i==3) {
				QtyTxtField.sendKeys(data.get(1).get("Column1"));
				clickAddBtn();
			String actMsg2 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				SalesShipment_softAssert.assertThat(actMsg2).isEqualTo("Error on Row :1 Quantity cannot be less than zero.");
			}else if(i==4) {
				QtyTxtField.clear();
				clickAddBtn();
				String actMsg4 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				SalesShipment_softAssert.assertThat(actMsg4).isEqualTo(data.get(17).get("Column10"));
			}
			if(i==0||i==1) {
				String actMsg3 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				SalesShipment_softAssert.assertThat(actMsg3).isEqualTo(data.get(16).get("Column10"));
				}
			}
	}

	public static void Validate_SalesShipmentPlan_by_removing_added_lineItem(List<HashMap<String, String>> data) throws InterruptedException {
		clickShipmentPlanLink();
		WebdriverWait.findElement("xpath", "//*[@value='Submit']").click();
		clickAddBtn();
		WebdriverWait.findElement("link", "Remove").click();
		try {
			if(driver.switchTo().alert()!=null) {
				driver.switchTo().alert().accept();
				String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
				SalesShipment_softAssert.assertThat(actMsg).isEqualTo(data.get(14).get("Column8"));
			}
		}catch(Exception e) {
			SalesShipment_softAssert.fail("Expected confirmation alert is not displayed");
		}
	}

	public static void Validate_SalesShipmentPlan_lineItem_is_exist_after_cancelling_the_alert() throws InterruptedException {
		clickShipmentPlanLink();
		WebdriverWait.findElement("xpath", "//*[@value='Submit']").click();
		clickAddBtn();
		WebdriverWait.findElement("link", "Remove").click();
		try {
			if(driver.switchTo().alert()!=null) {
				driver.switchTo().alert().dismiss();
				String getFirstLineItemProduct = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[4]").getText();
				if(getFirstLineItemProduct.contains("DNPFT001")) {
					SalesShipment_softAssert.wasSuccess();
					}else {
						SalesShipment_softAssert.fail("By dismissing the confirmation alert, the line item is still getting removed");

					}
				}
		}catch(Exception e) {
			SalesShipment_softAssert.fail("Expected confirmation alert is not displayed");
		}
	}

	public static void Validate_SalesShipment_OrderItems_with_Invalid_Data(List<HashMap<String, String>> data_positive_TC05, List<HashMap<String, String>> data_TC05_to_read_SO_ID, List<HashMap<String, String>> data) throws InterruptedException {
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data_TC05_to_read_SO_ID.get(13).get("Column4"));
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		clickShipmentPlanLink();
		WebdriverWait.findElement("xpath", "//*[@value='Submit']").click();
//		WebElement QtyTxtField = WebdriverWait.findElement("id", "quantity_o_0");
//		QtyTxtField.clear();
//		QtyTxtField.sendKeys(data.get(5).get("Column1"));
		clickAddBtn();
		String actMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		SalesShipment_softAssert.assertThat(actMsg1).isEqualTo(data.get(13).get("Column8"));
		clickOrderItemsLink();
		try{
			Assert.assertTrue("Issue Button is disabled. TC Passed!!", WebdriverWait.findElement("id", "submitButton").isEnabled()==false);
		}catch(Exception e) {
			Assert.assertTrue("Issue Button is enabled. TC Failed!!", WebdriverWait.findElement("id", "submitButton").isEnabled()==true);
		}

		for(int i=0; i<=4; i++) {
			WebElement issueTxtField = WebdriverWait.findElement("name", "quantity_o_0");
			issueTxtField.clear();
			if(i==0) {
				issueTxtField.sendKeys(data.get(5).get("Column1"));
				selectCheckBoxAndclickIssueBtn();
			}else if(i==1) {
				issueTxtField.sendKeys(data.get(0).get("Column1"));
				selectCheckBoxAndclickIssueBtn();
			}else if(i==2) {
				issueTxtField.sendKeys(data.get(14).get("Column1"));
				selectCheckBoxAndclickIssueBtn();
			String actMsg_1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
			SalesShipment_softAssert.assertThat(actMsg_1).isEqualTo(data.get(10).get("Column8"));
			}else if(i==3) {
				issueTxtField.sendKeys(data.get(1).get("Column1"));
				selectCheckBoxAndclickIssueBtn();
			}else if(i==4) {
				selectCheckBoxAndclickIssueBtn();
			}
			if(i==0||i==1) {
				String actMsg_3 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
				SalesShipment_softAssert.assertThat(actMsg_3).isEqualTo("Error on Row 1: Issue Quantity should be a Number");
				}else if(i==3||i==4) {
					String actMsg_2 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
					if(actMsg_2.contains("Not issuing Order Item Ship Group Inventory Reservation to shipment")) {
						SalesShipment_softAssert.wasSuccess();
					}else {
					SalesShipment_softAssert.fail("Expected validation message is not displayed. TC failed.");
					}
				}
			}

	}

	public static void Validate_SalesShipment_UpdateStatus_without_issuing_items(List<HashMap<String, String>> data_TC05_to_read_SO_ID,
					List<HashMap<String, String>> data) throws InterruptedException {
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data_TC05_to_read_SO_ID.get(13).get("Column4"));
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		int j=21;
		for(int i=1; i<=5; i++) {
			Select status_dd = new Select(WebdriverWait.findElement("id", "statusId"));
			if(i==4) {
			status_dd.selectByVisibleText(data.get(j+i+1).get("Column1"));
			}else {
				status_dd.selectByVisibleText(data.get(j+i).get("Column1"));
			}
			//update btn
			Thread.sleep(3000);
			WebdriverWait.findElement("id", "submitForm").click();
			try {
				if(i==1) {
					String actMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
					SalesShipment_softAssert.assertThat(actMsg1).isEqualTo("At least one item must be issued in 'Order Items' screen before changing status to "+data.get(j+i).get("Column1"));
				}else if(i==2) {
						String actMsg2 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
						SalesShipment_softAssert.assertThat(actMsg2).isEqualTo("At least one item must be issued in 'Order Items' screen before changing status to "+data.get(j+i).get("Column1"));
						String actMsg3 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
						SalesShipment_softAssert.assertThat(actMsg3).isEqualTo("Changing the status from Input to "+data.get(j+i).get("Column1")+" is not allowed.");
				}else if(i==3) {
					String actMsg4 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
					SalesShipment_softAssert.assertThat(actMsg4).isEqualTo("Changing the status from Input to "+data.get(j+i).get("Column1")+" is not allowed.");
				}else if(i==4) {
					String actMsg4 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
					SalesShipment_softAssert.assertThat(actMsg4).isEqualTo("Changing the status from Input to "+data.get(j+i+1).get("Column1")+" is not allowed.");
				}else if(i==5) {
					try {
						if(driver.switchTo().alert()!=null) {
							driver.switchTo().alert().dismiss();
						}
					}catch(Exception e) {
						SalesShipment_softAssert.fail("Expected Alert is not displayed while updated the status to Cancelled");
					}
					String getStatus = WebdriverWait.findElement("id", "select2-statusId-container").getText();
					SalesShipment_softAssert.assertThat(getStatus).isEqualTo("Input");
				}else {
					SalesShipment_softAssert.fail("Expected Validation message is not displayed");
				}
			}catch(Exception e) {
				SalesShipment_softAssert.fail("Expected Validation message is not displayed");
			}
			j++;
		}
		}

	public static void Validate_SalesShipment_by_issuing_items_and_UpdateStatus_except_Packed(List<HashMap<String, String>> data_positive_TC05, List<HashMap<String, String>> data_TC05_to_read_SO_ID, List<HashMap<String, String>> data) throws InterruptedException {
//		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
//		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data_TC05_to_read_SO_ID.get(13).get("Column4"));
//		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		clickShipmentPlanLink();
		WebdriverWait.findElement("xpath", "//*[@value='Submit']").click();
		clickAddBtn();
		clickOrderItemsLink();
		WebdriverWait.findElement("name", "selectAll").click();
		WebdriverWait.findElement("id", "submitButton").click();
		WebdriverWait.findElement("link", "Edit").click();
		int j=22;
		for(int i=1; i<=5; i++) {
			Select status_dd = new Select(WebdriverWait.findElement("id", "statusId"));
			if(i==3) {
			status_dd.selectByVisibleText(data.get(j+i+1).get("Column1"));
			}else if(i==4){
				status_dd.selectByVisibleText(data.get(j+i-5).get("Column1"));
			}else {
				status_dd.selectByVisibleText(data.get(j+i).get("Column1"));
			}
			WebdriverWait.findElement("xpath", "//*[@value='Update']").click();
			try {
				if(i==1) {
					String actMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
					SalesShipment_softAssert.assertThat(actMsg1).isEqualTo("At least one item must be issued in 'Order Items' screen before changing status to "+data.get(j+i).get("Column1"));
				}else if(i==2) {
						String actMsg2 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
						SalesShipment_softAssert.assertThat(actMsg2).isEqualTo("At least one item must be issued in 'Order Items' screen before changing status to "+data.get(j+i).get("Column1"));
						String actMsg3 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
						SalesShipment_softAssert.assertThat(actMsg3).isEqualTo("Changing the status from Input to "+data.get(j+i).get("Column1")+" is not allowed.");
				}else if(i==3) {
					String actMsg4 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
					SalesShipment_softAssert.assertThat(actMsg4).isEqualTo("Changing the status from Input to "+data.get(j+i+1).get("Column1")+" is not allowed.");
				}else if(i==4) {
					String actMsg4 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
					SalesShipment_softAssert.assertThat(actMsg4).isEqualTo("Changing the status from Input to "+data.get(j+i-5).get("Column1")+" is not allowed.");
				}else if(i==5) {
					String actMsg5 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
					SalesShipment_softAssert.assertThat(actMsg5).isEqualTo("Changing the status from Input to "+data.get(j+i).get("Column1")+" is not allowed.");
				}else {
					SalesShipment_softAssert.fail("Expected Validation message is not displayed");
				}
			}catch(Exception e) {
				SalesShipment_softAssert.fail("Expected Validation message is not displayed");
			}
			j++;
		}
		}

	public static void Validate_SalesShipment_by_Updating_Status_Packed_to_Delivered(List<HashMap<String, String>> data_TC05_to_read_SO_ID, List<HashMap<String, String>> data) throws InterruptedException {
//		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").clear();
//		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data_TC05_to_read_SO_ID.get(13).get("Column4"));
//		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
//		clickShipmentPlanLink();
//		WebdriverWait.findElement("xpath", "//*[@value='Submit']").click();
//		clickAddBtn();
//		clickOrderItemsLink();
//		WebdriverWait.findElement("name", "selectAll").click();
//		WebdriverWait.findElement("id", "submitButton").click();
		WebdriverWait.findElement("link", "Edit").click();
		Select status_dd = new Select(WebdriverWait.findElement("id", "statusId"));
		status_dd.selectByVisibleText("Packed");
		WebdriverWait.findElement("xpath", "//*[@value='Update']").click();
		status_dd.selectByVisibleText("Delivered");
		WebdriverWait.findElement("xpath", "//*[@value='Update']").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesShipment_softAssert.assertThat(actMsg).isEqualTo(data.get(18).get("Column17"));
		}

	public static void Validate_SalesShipment_ViewPage_BackToSearch_button_navigating_to_SearchPage() {
		WebdriverWait.findElement("link", "View").click();
		WebdriverWait.findElement("link", "Back to search").click();
		String verifySearchPageIsDisplayed = driver.getTitle();
		SalesShipment_softAssert.assertThat(verifySearchPageIsDisplayed).isEqualTo("Crest: Find Shipment(s)");
	}

	public static void Validating_SalesReturn_without_Mandatory_details(List<HashMap<String, String>> data) {
		Select statusIdDD = new Select(WebdriverWait.findElement("id", "statusId"));
		statusIdDD.selectByVisibleText("-Select-");
		click_Create_btn();
		String path1 = "//*[@id='content-messages']/div/ul/li[";
		String path2 = "]";
		for (int i = 1; i <= 4; i++) {
			String all_ValidationMsg = WebdriverWait.findElement("xpath", path1 + i + path2).getText();
			if (i == 1) {
				SalesShipment_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(13).get("Column21"));
			} else if (i == 2) {
				SalesShipment_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(25).get("Column21"));
			} else if (i == 3) {
				SalesShipment_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(1).get("Column7"));
			} else if (i == 4) {
				SalesShipment_softAssert.assertThat(all_ValidationMsg).isEqualTo(data.get(15).get("Column8"));
			}
		}
	}

	public static void Validate_PrimaryReturnID_with_PO_ID(List<HashMap<String, String>> data) {
		navigateToShipments();
		clickCreateShipmentLink();
		selectingShipmentType("Sales Return");
		WebdriverWait.findElement("id", "2_lookupId_primaryReturnId").clear();
		WebdriverWait.findElement("id", "2_lookupId_primaryReturnId").sendKeys(data.get(39).get("Column1"));
		Select currency_dd = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		currency_dd.selectByIndex(2);
		Select originFacility_dd = new Select(WebdriverWait.findElement("id", "destinationFacilityId"));
		originFacility_dd.selectByIndex(2);
		click_Create_btn();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesShipment_softAssert.assertThat(actMsg).isEqualTo("Entered Primary Return ID "+data.get(39).get("Column1")+" is not a Sales Return Order");
	}

	public static void Validate_SalesReturn_with_Shipped_to_Shipped_Status(List<HashMap<String, String>> data) throws InterruptedException {
		navigateToShipments();
		clickCreateShipmentLink();
		selectingShipmentType("Sales Return");
		WebdriverWait.findElement("id", "2_lookupId_primaryReturnId").clear();
		WebdriverWait.findElement("id", "2_lookupId_primaryReturnId").sendKeys(data.get(40).get("Column1"));
		Select currency_dd = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		currency_dd.selectByIndex(2);
		Select destFacility_dd = new Select(WebdriverWait.findElement("id", "destinationFacilityId"));
		destFacility_dd.selectByIndex(2);
		click_Create_btn();
		Select statusIdDD = new Select(WebdriverWait.findElement("id", "statusId"));
		statusIdDD.selectByVisibleText("Shipped");
		// WebdriverWait.findElement("id", "createSubmit").click();
		Thread.sleep(1000);
		WebdriverWait.findElement("id", "createSubmit").click();
		Thread.sleep(1000);
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		SalesShipment_softAssert.assertThat(actMsg).isEqualTo(data.get(21).get("Column17"));
	}

	public static void Validate_PrimaryReturnID_with_InvalidData(List<HashMap<String, String>> data) {
		navigateToShipments();
		clickCreateShipmentLink();
		selectingShipmentType("Sales Return");
		WebdriverWait.findElement("id", "2_lookupId_primaryReturnId").clear();
		WebdriverWait.findElement("id", "2_lookupId_primaryReturnId").sendKeys(data.get(5).get("Column1"));
		Select currency_dd = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		currency_dd.selectByIndex(2);
		Select destFacility_dd = new Select(WebdriverWait.findElement("id", "destinationFacilityId"));
		destFacility_dd.selectByIndex(2);
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		String actMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[1]").getText();
		SalesShipment_softAssert.assertThat(actMsg).isEqualTo(data.get(26).get("Column21"));
		String actMsg1 = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li[2]").getText();
		SalesShipment_softAssert.assertThat(actMsg1).isEqualTo(data.get(27).get("Column21"));
	}

	public static void Validate_SalesReturn_Cancel_button_navigating_to_ShipmentsSearchPage() {
		navigateToShipments();
		clickCreateShipmentLink();
		selectingShipmentType("Sales Return");
		WebdriverWait.findElement("link", "Cancel").click();
		String verifySearchPageIsDisplayed = driver.getTitle();
		SalesShipment_softAssert.assertThat(verifySearchPageIsDisplayed).isEqualTo("Crest: Find Shipment(s)");
	}


	public static void SalesShipment_assertions() {
		SalesShipment_softAssert.assertAll();
	}
}
