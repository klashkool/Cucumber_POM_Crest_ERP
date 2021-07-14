package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.Facilities_Modules.Facilities_Shipments_Sales;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Shipments_Negative_Scenarios_Stepdef extends Base {

	List<HashMap<String, String>> Sales_Validation_Msg_Data = CrestTestDataReader.get_Sales_Validation_Msg_Data();
	List<HashMap<String, String>> Sales_PositveData_For_NegativeFlow = CrestTestDataReader.get_Sales_PositveData_For_NegativeFlow();
	List<HashMap<String, String>> Sales_PositveData_SO_TC05_Data = CrestTestDataReader.get_SO_TC05_Data();

	@Given("^Navigate to Shipments - Sales_Shipments_SearchPage$")
	public void navigate_to_Shipments_Sales_Shipments_SearchPage() throws Throwable {
		Facilities_Shipments_Sales.navigateToShipments();
	}

	@Then("^Validate with Invalid Date Range - Sales_Shipments_SearchPage$")
	public void validate_with_Invalid_Date_Range_Sales_Shipments_SearchPage() throws Throwable {
		Facilities_Shipments_Sales.Validating_Shipment_date_range_with_invalid_date(Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.SalesShipment_assertions();
	}

	@Given("^Navigate to Shipments - Sales_Shipments_CreatePage$")
	public void navigate_to_Shipments_Sales_Shipments_CreatePage() throws Throwable {
		Facilities_Shipments_Sales.navigateToShipments();
		Facilities_Shipments_Sales.clickCreateShipmentLink();
		Facilities_Shipments_Sales.selectingShipmentType("Sales Shipment");
	}

	@Then("^Validate with Invalid Data - Sales_Shipments_CreatePage$")
	public void validate_with_Invalid_Data_Sales_Shipments_CreatePage() throws Throwable {
		Facilities_Shipments_Sales.Validating_without_Mandatory_details(Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_BackToSearch_button_navigating_to_ShipmentsSearchPage();
		Facilities_Shipments_Sales.Validate_Cancel_button_navigating_to_ShipmentsSearchPage();
		Facilities_Shipments_Sales.Validate_PrimaryOrderID_with_InvalidData(Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_PrimaryOrderID_with_PO_ID(Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_PrimaryOrderID_with_SO_ID_in_CreatedStatus(Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_PrimaryOrderID_with_SO_ID_in_HeldStatus(Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_PrimaryOrderID_with_SO_ID_in_CompletedStatus(Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_PrimaryOrderID_with_SO_ID_in_CancelledStatus(Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_SalesShipment_EstimatedReadyDate_with_PastDate(Sales_PositveData_SO_TC05_Data, Sales_PositveData_SO_TC05_Data,
						Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_SalesShipment_EstimatedShipDate_with_PastDate(Sales_PositveData_SO_TC05_Data, Sales_PositveData_SO_TC05_Data,
						Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_SalesShipment_ShipmentDate_with_PastDate(Sales_PositveData_SO_TC05_Data, Sales_PositveData_SO_TC05_Data,
						Sales_Validation_Msg_Data);
//		Facilities_Shipments_Sales.Validate_SalesShipment_ERD_ESD_ShipDate_with_PastDate(Sales_PositveData_SO_TC05_Data, Sales_PositveData_SO_TC05_Data, Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_CreateSalesShipment_with_all_Status(Sales_PositveData_SO_TC05_Data, Sales_PositveData_SO_TC05_Data, Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.SalesShipment_assertions();
	}

	@Given("^Navigate to Shipments - Sales_Shipments_EditPage$")
	public void navigate_to_Shipments_Sales_Shipments_EditPage() throws Throwable {
//		Sales_SalesOrder.SO_StartOrder(Sales_PositveData_SO_TC05_Data, "SO Offline Payment Flow");
//		Sales_SalesOrder.OfflinePayment_Chkbox();
//		Sales_SalesOrder.SO_Shipping_Details_SingleFacility(Sales_PositveData_SO_TC05_Data);
//		Sales_SalesOrder.SO_OrderItems(Sales_PositveData_SO_TC05_Data, 4);
//		Sales_SalesOrder.SO_ReviewCreateSO("SO_TC05");
//		Sales_SalesOrder_View.Approve_SO(Sales_PositveData_SO_TC05_Data);
		Facilities_Shipments_Sales.navigateToShipments();
		Facilities_Shipments_Sales.clickCreateShipmentLink();
		Facilities_Shipments_Sales.selectingShipmentType("Sales Shipment");
	}

	@Then("^Validate with Invalid Data - Sales_Shipments_EditPage$")
	public void validate_with_Invalid_Data_Sales_Shipments_EditPage() throws Throwable {
		Facilities_Shipments_Sales.Validate_SalesShipment_UpdateStatus_without_issuing_items(Sales_PositveData_SO_TC05_Data, Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_SalesShipment_by_issuing_items_and_UpdateStatus_except_Packed(Sales_PositveData_For_NegativeFlow,
						Sales_PositveData_SO_TC05_Data, Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_SalesShipment_by_Updating_Status_Packed_to_Delivered(Sales_PositveData_SO_TC05_Data, Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_SalesShipment_ViewPage_BackToSearch_button_navigating_to_SearchPage();
		Facilities_Shipments_Sales.SalesShipment_assertions();
	}

	@Given("^Navigate to Shipments Shipment Plan - Sales_Shipments_ShipmentPlanPage$")
	public void navigate_to_Shipments_Shipment_Plan_Sales_Shipments_ShipmentPlanPage() throws Throwable {
//		Sales_SalesOrder.SO_StartOrder(Sales_PositveData_SO_TC05_Data, "SO Offline Payment Flow");
//		Sales_SalesOrder.OfflinePayment_Chkbox();
//		Sales_SalesOrder.SO_Shipping_Details_SingleFacility(Sales_PositveData_SO_TC05_Data);
//		Sales_SalesOrder.SO_OrderItems(Sales_PositveData_SO_TC05_Data, 4);
//		Sales_SalesOrder.SO_ReviewCreateSO("SO_TC05");
//		Sales_SalesOrder_View.Approve_SO(Sales_PositveData_SO_TC05_Data);
		Facilities_Shipments_Sales.navigateToShipments();
		Facilities_Shipments_Sales.clickCreateShipmentLink();
		Facilities_Shipments_Sales.selectingShipmentType("Sales Shipment");
	}

	@Then("^Validate with Invalid Data - Sales_Shipments_ShipmentPlanPage$")
	public void validate_with_Invalid_Data_Sales_Shipments_ShipmentPlanPage() throws Throwable {
		Facilities_Shipments_Sales.Validate_SalesShipmentPlan_Qty_with_InvalidData(Sales_PositveData_SO_TC05_Data, Sales_PositveData_SO_TC05_Data, Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_SalesShipmentPlan_by_removing_added_lineItem(Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_SalesShipmentPlan_lineItem_is_exist_after_cancelling_the_alert();
		Facilities_Shipments_Sales.SalesShipment_assertions();
	}

	@Given("^Navigate to Shipments Order Items - Sales_Shipments_OrderItemsPage$")
	public void navigate_to_Shipments_Order_Items_Sales_Shipments_OrderItemsPage() throws Throwable {
//		Sales_SalesOrder.SO_StartOrder(Sales_PositveData_SO_TC05_Data, "SO Offline Payment Flow");
//		Sales_SalesOrder.OfflinePayment_Chkbox();
//		Sales_SalesOrder.SO_Shipping_Details_SingleFacility(Sales_PositveData_SO_TC05_Data);
//		Sales_SalesOrder.SO_OrderItems(Sales_PositveData_SO_TC05_Data, 4);
//		Sales_SalesOrder.SO_ReviewCreateSO("SO_TC05");
//		Sales_SalesOrder_View.Approve_SO(Sales_PositveData_SO_TC05_Data);
		Facilities_Shipments_Sales.navigateToShipments();
		Facilities_Shipments_Sales.clickCreateShipmentLink();
		Facilities_Shipments_Sales.selectingShipmentType("Sales Shipment");
	}

	@Then("^Validate with Invalid Data - Sales_Shipments_OrderItemsPage$")
	public void validate_with_Invalid_Data_Sales_Shipments_OrderItemsPage() throws Throwable {
		Facilities_Shipments_Sales.Validate_SalesShipment_OrderItems_with_Invalid_Data(Sales_PositveData_SO_TC05_Data, Sales_PositveData_SO_TC05_Data, Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.SalesShipment_assertions();
	}

	@Given("^Navigate to Return Shipments - Sales_ReturnShipments_CreatePage$")
	public void navigate_to_Return_Shipments_Sales_ReturnShipments_CreatePage() throws Throwable {
		Facilities_Shipments_Sales.navigateToShipments();
		Facilities_Shipments_Sales.clickCreateShipmentLink();
		Facilities_Shipments_Sales.selectingShipmentType("Sales Return");
	}

	@Then("^Validate with Invalid Data - Sales_ReturnShipments_CreatePage$")
	public void validate_with_Invalid_Data_Sales_ReturnShipments_CreatePage() throws Throwable {
		Facilities_Shipments_Sales.Validating_SalesReturn_without_Mandatory_details(Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_BackToSearch_button_navigating_to_ShipmentsSearchPage();
		Facilities_Shipments_Sales.Validate_PrimaryReturnID_with_PO_ID(Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_PrimaryReturnID_with_InvalidData(Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_SalesReturn_with_Shipped_to_Shipped_Status(Sales_Validation_Msg_Data);
		Facilities_Shipments_Sales.Validate_SalesReturn_Cancel_button_navigating_to_ShipmentsSearchPage();
		Facilities_Shipments_Sales.SalesShipment_assertions();
	}
}
