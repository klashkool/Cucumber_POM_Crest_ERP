package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.Crest_ERP_Login.Crest_Login;
import com.Facilities_Modules.Facilities_Facilities_StockManagement;
import com.Facilities_Modules.Facilities_ReceiveAdHoc;
import com.Facilities_Modules.Facilities_Shipments;
import com.Facilities_Modules.Facilities_Shipments_Edit;
import com.Facilities_Modules.Facilities_Shipments_OrderItems;
import com.Facilities_Modules.Facilities_Shipments_View;
import com.Procurement_Modules.Procurement_PurchaseOrder;
import com.Procurement_Modules.Procurement_PurchaseOrder_View;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Facilities_Negative_Scenarios_Stepdef extends Base {

	List<HashMap<String, String>> Proc_Val_Msg = CrestTestDataReader.get_Proc_Val_Msg();
	List<HashMap<String, String>> Proc_Positive_Data = CrestTestDataReader.get_Proc_Positive_Data();
	List<HashMap<String, String>> TC03_data = CrestTestDataReader.get_TC03_Data();
	List<HashMap<String, String>> TC04_data = CrestTestDataReader.get_TC04_Data();

	// @Proc_Shp_Neg01
	// Scenario: Procurement - Create an Order for Shipments

	@Given("^Navigate to Created purchase Order - PO_shipments$")
	public void navigate_to_Created_purchase_Order_PO_shipments() {
		Procurement_PurchaseOrder.Search_PO(TC03_data);
	}

	@Then("^Create as New Order - PO_shipments$")
	public void create_as_New_Order_PO_shipments() {
		Procurement_PurchaseOrder.CreateAsNewOrder();
		Procurement_PurchaseOrder.PO_ReviewCreatePO("Proc_Valid_Data_NegFlow");
	}

	// @Proc_Shp_Neg02
	// Scenario: Procurements - Approve PO for Shipments

	@Given("^Approve the Order - PO_shipments$")
	public void approve_the_Order_PO_shipments() {
		Crest_Login.PurchaseExc_login();
		Crest_Login.PurchaseVP_login();
		Procurement_PurchaseOrder_View.PO_Multilevel_Approval(Proc_Positive_Data, "Approve", "Approved");
		Crest_Login.PurchaseCEO_login();
		Procurement_PurchaseOrder_View.PO_Multilevel_Approval(Proc_Positive_Data, "Approve", "Approved");
	}

	// @Proc_Shp_Neg03
	// Scenario: Shipments - PO_CreateShipmentPage

	@Given("^Navigate to Create Purchase Shipment Page - PO_CreateShipmentPage$")
	public void navigate_to_Create_Purchase_Shipment_Page_PO_CreateShipmentPage() {
		Facilities_Shipments.PurchaseShipment_Navigation();
	}

	@Then("^Validate with Invalid Data - PO_CreateShipmentPage$")
	public void validate_with_Invalid_Data_PO_CreateShipmentPage() {
		Facilities_Shipments.MandatoryFieldsVal_CreatePurchaseShipment(Proc_Val_Msg);
		Facilities_Shipments.InvalidOrderID_Val(Proc_Val_Msg, Proc_Positive_Data);
		Facilities_Shipments.AddtlShippingChargeDesc_255charac_Val(Proc_Val_Msg, Proc_Positive_Data);
		Facilities_Shipments.EstimatedArrival_Val(Proc_Val_Msg, Proc_Positive_Data);
		Facilities_Shipments.PurchaseShipment_assertions();
	}

	// @Proc_Shp_Neg04
	// Scenario: Shipments - PO_ShipmentOrderItemsPage

	@Given("^Navigate to Shipment Order Items Page - PO_ShipmentOrderItemsPage$")
	public void navigate_to_Shipment_Order_Items_Page_PO_ShipmentOrderItemsPage() {
		Facilities_Shipments.PurchaseShipment_Navigation();
		Facilities_Shipments.PO_CreateShipment(Proc_Positive_Data);
		Facilities_Shipments_View.Capture_ShpID("Proc_Valid_Data_NegFlow", 15);
	}

	@Then("^Validate with Invalid Data - PO_ShipmentOrderItemsPage$")
	public void validate_with_Invalid_Data_PO_ShipmentOrderItemsPage() {
		Facilities_Shipments_OrderItems.InvalidQtyVal_AddToShipmentPlan_Table(Proc_Val_Msg);
		Facilities_Shipments_OrderItems.AddingComments_255Charac_Val(Proc_Val_Msg);
		Facilities_Shipments_OrderItems.RemoveItemsIncluded();
		Facilities_Shipments_OrderItems.PurchaseShipmentOrderItems_assertions();
	}

	// @Proc_Shp_Neg05
	// Scenario: Shipments - Receive Inventory

	@Given("^Receive Inventory$")
	public void receive_Inventory() throws InterruptedException {
		Facilities_Shipments.PO_ShipID_Search(Proc_Positive_Data);
		Facilities_Shipments_OrderItems.PO_Recv_Shpmnt_Negative();
		Facilities_Facilities_StockManagement.PO_Receive_Shpmnt_Loc_DD();
		Facilities_Shipments_View.PO_Shpmnt_Status_Val();
		Procurement_PurchaseOrder_View.CaptureInvID("Proc_Valid_Data_NegFlow");
	}

	///////////////// Purchase Return/////////////////

	// @Proc_Shp_Neg06
	// Scenario: Return Shipments - PO_PurchaseReturnCreatePage

	@Given("^Navigate to Create Purchase Return Page - PO_PurchaseReturnCreatePage$")
	public void navigate_to_Create_Purchase_Return_Page_PO_PurchaseReturnCreatePage() {
		Facilities_Shipments.PurchaseReturnShipment_Navigation();
	}

	@Then("^Validate with Invalid Data - PO_PurchaseReturnCreatePage$")
	public void validate_with_Invalid_Data_PO_PurchaseReturnCreatePage() {
		Facilities_Shipments.MandatoryFieldsVal_CreatePurchaseReturn(Proc_Val_Msg);
		Facilities_Shipments.InvalidReturnID(Proc_Val_Msg);
		Facilities_Shipments.AddShipCharge_Val(Proc_Positive_Data, Proc_Val_Msg);
		Facilities_Shipments.AddShipChargeDesc_Val(Proc_Positive_Data, Proc_Val_Msg);
		// Need to check and implement - Estimated Ship Date:
		Facilities_Shipments.PurchaseRetrunCancelBtn_Val();
		Facilities_Shipments.PurchaseShipment_assertions();
	}

	// @Proc_Shp_Neg07
	// Scenario: Purchase Return Shipments - PO_PurchaseReturnEditPage

	@Given("^Navigate to Purchase Return Edit Page - PO_PurchaseReturnEditPage$")
	public void navigate_to_Purchase_Return_Edit_Page_PO_PurchaseReturnEditPage() {
		Facilities_Shipments.PurchaseReturnShipment_Navigation();
		Facilities_Shipments.CreatePurchaseReturn(Proc_Positive_Data);
	}

	@Then("^Validate with Invalid Data - PO_PurchaseReturnEditPage$")
	public void validate_with_Invalid_Data_PO_PurchaseReturnEditPage() {
		Facilities_Shipments_Edit.StatusVal_WithoutaddingLineitems(Proc_Val_Msg);
		Facilities_Shipments_Edit.ShipmentEdit_assertions();
	}

	// @Proc_Shp_Neg08
	// Scenario: Purchase Return Shipments - PO_PurchaseReturn_OrderItemsPage

	@Given("^Navigate to Purchase Return Order Items Page - PO_PurchaseReturn_OrderItemsPage$")
	public void navigate_to_Purchase_Return_Order_Items_Page_PO_PurchaseReturn_OrderItemsPage() {
		Facilities_Shipments.PurchaseReturnShipment_Navigation();
		Facilities_Shipments.CreatePurchaseReturn(Proc_Positive_Data);
		Facilities_Shipments_OrderItems.OrderItemsLink();
	}

	@Then("^Validate with Invalid Data - PO_PurchaseReturn_OrderItemsPage$")
	public void validate_with_Invalid_Data_PO_PurchaseReturn_OrderItemsPage() {

		Facilities_Shipments_OrderItems.MandatoryFields_Val(Proc_Val_Msg);
		Facilities_Shipments_OrderItems.Invalid_Qty(Proc_Val_Msg);
		Facilities_Shipments_OrderItems.PurchaseShipmentOrderItems_assertions();
	}

	// @Proc_Shp_Neg09
	// Scenario: Create Adhoc PO - PO_ViewPage

	@Given("^Create New Adhoc PO - PO_ViewPage$")
	public void create_New_Adhoc_PO_PO_ViewPage() {
		Procurement_PurchaseOrder.Search_PO(TC04_data);
		Procurement_PurchaseOrder.CreateAsNewOrder();
		Procurement_PurchaseOrder.PO_ReviewCreatePO("Proc_Valid_Data_NegFlow");
	}

	// @Proc_Shp_Neg10
	// Scenario: Receive Adhoc - ReceiveAdhocPage

	@Given("^Approve the Created PO - PO_ViewPage$")
	public void approve_the_Created_PO_PO_ViewPage() {
		Crest_Login.PurchaseExc_login();
		Crest_Login.PurchaseVP_login();
		Procurement_PurchaseOrder_View.PO_Multilevel_Approval(Proc_Positive_Data, "Approve", "Approved");
	}

	@Then("^Navigate to Receive Adhoc Page - ReceiveAdhocPage$")
	public void navigate_to_Receive_Adhoc_Page_ReceiveAdhocPage() {
		Facilities_ReceiveAdHoc.FacilitiesLink();
		Facilities_ReceiveAdHoc.StockManagementLink();
		Facilities_ReceiveAdHoc.ReceiveAdhocLink();
	}

	@Then("^Validate with Invalid date - ReceiveAdhocPage$")
	public void validate_with_Invalid_date_ReceiveAdhocPage() throws InterruptedException {
		// Facilities_ReceiveAdHoc.InvalidOrderID_Val(Proc_Val_Msg);
		Facilities_ReceiveAdHoc.ReceiveAdhoc_WithoutSelectingLineItems(Proc_Positive_Data);
		Facilities_ReceiveAdHoc.InvalidQty_Val(Proc_Val_Msg);
		Facilities_ReceiveAdHoc.Cancel_ReceiveAdhoc();
		Facilities_ReceiveAdHoc.ReceiveAdhoc_softAssert_assertions();

	}

}
