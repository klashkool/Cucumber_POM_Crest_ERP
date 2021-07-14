package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.Crest_ERP_Login.Crest_Login;
import com.Facilities_Modules.Facilities_Shipments;
import com.Procurement_Modules.Procurement_PurchaseOrder;
import com.Procurement_Modules.Procurement_PurchaseOrder_View;
import com.Procurement_Modules.Procurement_PurchaseReturn;
import com.Procurement_Modules.Procurement_PurchaseReturn_ReturnItems;
import com.Procurement_Modules.Procurement_Quotes;
import com.Procurement_Modules.Procurement_Quotes_UpdateQuoteStatus;
import com.Procurement_Modules.Procurement_RFQ;
import com.Procurement_Modules.Procurement_Requirements_Requirements;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Procurement_Negative_Scenarios_Stepdef extends Base {

	List<HashMap<String, String>> Proc_Val_Msg = CrestTestDataReader.get_Proc_Val_Msg();
	List<HashMap<String, String>> Proc_Positive_Data = CrestTestDataReader.get_Proc_Positive_Data();
	List<HashMap<String, String>> TC01_2 = CrestTestDataReader.get_TC01_2_Data();

	// @Proc_Neg01
	// Scenario: Procurements - Requirements Search page

	@Given("^Navigate to Requirements - PO_Req_SearchPage$")
	public void navigate_to_Requirements_PO_Req_SearchPage() {
		Procurement_Requirements_Requirements.Req_Navigation();
	}

	@Then("^Validate with Invalid Date Range - PO_Req_SearchPage$")
	public void validate_with_Invalid_Date_Range_PO_Req_SearchPage() {
		Procurement_Requirements_Requirements.Req_SearchPage_InvalidDate_Val(Proc_Val_Msg);
		Procurement_Requirements_Requirements.PO_Req_assertions();
	}

	// @Proc_Neg02
	// Scenario: Procurements - Requirements Create Page

	@Given("^Create Requirement - PO_Req_CreatePage$")
	public void create_Requirement_PO_Req_CreatePage() {
		Procurement_Requirements_Requirements.Req_Navigation();
		Procurement_Requirements_Requirements.Create_Req_link();
	}

	@Then("^Validate Create Requirement Page with Invalid data - PO_Req_CreatePage$")
	public void validate_Create_Requirement_Page_with_Invalid_data_PO_Req_CreatePage() {

		Procurement_Requirements_Requirements.Req_Mandatory_Fields_CreateReqTable(Proc_Val_Msg);
		Procurement_Requirements_Requirements.Invalid_reqByDate_CreateReqTable(Proc_Val_Msg);
		Procurement_Requirements_Requirements.Create_WithoutAddinglineItem_ReqItemTable(Proc_Val_Msg);
		Procurement_Requirements_Requirements.Req_Mandatory_Fields_ReqItemTable(Proc_Val_Msg);
		Procurement_Requirements_Requirements.Invalid_PrdID(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_Requirements_Requirements.InvalidQty(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_Requirements_Requirements.Invalid_reqByDate_ReqItemTable(Proc_Val_Msg);
		// Procurement_Requirements_Requirements.Val_255char_Comments_ReqItemTable(Proc_Val_Msg);
		Procurement_Requirements_Requirements.Removing_AddedLineItem(Proc_Val_Msg);
		Procurement_Requirements_Requirements.Val_255Charc_Desc_Reason_CreateReqTable(Proc_Val_Msg);
		Procurement_Requirements_Requirements.PO_Req_assertions();

	}

	// @Proc_Neg03
	// Scenario: Procurements - Requirements Edit Page

	@Given("^Create Requirement - PO_ReqEditPage$")
	public void create_Requirement_PO_ReqEditPage() throws InterruptedException {
		Procurement_Requirements_Requirements.PO_Req(Proc_Positive_Data, "Proc_Valid_Data_NegFlow", "Inv");
	}

	@Then("^Validate Edit Requirement Page with Invalid data - PO_ReqEditPage$")
	public void validate_Edit_Requirement_Page_with_Invalid_data_PO_ReqEditPage() {

		Procurement_Requirements_Requirements.Req_Mandatory_Fields_EditReqTable(Proc_Val_Msg);
		Procurement_Requirements_Requirements.Invalid_reqByDate_EditReqTable(Proc_Val_Msg);
		Procurement_Requirements_Requirements.Req_Mandatory_Fields_EditReqItemTable(Proc_Val_Msg);
		Procurement_Requirements_Requirements.EditReq_Invalid_PrdID(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_Requirements_Requirements.EditReq_InvalidQty(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_Requirements_Requirements.EditReq_Invalid_reqByDate_ReqItemTable(Proc_Val_Msg);//
		Procurement_Requirements_Requirements.EditReqQty_InvalidData(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_Requirements_Requirements.Removing_AddedLineItem_EditReq(Proc_Val_Msg);
		Procurement_Requirements_Requirements.Reject_Req(Proc_Val_Msg);
		Procurement_Requirements_Requirements.PO_EditReq_assertions();

	}

	// @Proc_Neg04
	// Scenario: Procurements - Requirements (Postive Flow)

	@Given("^Create and Approve Requirement - PO_ReqEditPage$")
	public void create_and_Approve_Requirement_PO_ReqEditPage() throws InterruptedException {
		Procurement_Requirements_Requirements.PO_Req(Proc_Positive_Data, "Proc_Valid_Data_NegFlow", "Inv");
		Procurement_Requirements_Requirements.Req_Approve();
	}

	// @Proc_Neg05
	// Scenario: Procurements - RFQ Search Page

	@Given("^Navigate to RFQ - PO_RFQ_SearchPage$")
	public void navigate_to_RFQ_PO_RFQ_SearchPage() {
		Procurement_RFQ.RFQ_Navigation();
	}

	@Then("^Validate with Invalid Date Range - PO_RFQ_SearchPage$")
	public void validate_with_Invalid_Date_Range_PO_RFQ_SearchPage() {
		Procurement_RFQ.RFQ_SearchPage_InvalidDate_Val(Proc_Val_Msg);
		Procurement_RFQ.PO_RFQ_assertions();
	}

	// @Proc_Neg06
	// Scenario: Procurements - RFQ Create Page

	@Given("^Create RFQ - PO_RFQ_CreatePage$")
	public void create_RFQ_PO_RFQ_CreatePage() {
		Procurement_RFQ.RFQ_Navigation();
		Procurement_RFQ.Create_RFQ_Link();
	}

	@Then("^Validate RFQ Create Page with Invalid data - PO_RFQ_CreatePage$")
	public void validate_RFQ_Create_Page_with_Invalid_data_PO_RFQ_CreatePage() throws InterruptedException {

		Procurement_RFQ.RFQ_Mandatory_Fields_CreateRFQTable(Proc_Val_Msg);
		Procurement_RFQ.Invalid_ResponseReqDate_CreateRFQTable(Proc_Val_Msg);
		Procurement_RFQ.RFQ_Mandatory_Fields_SupplierTable(Proc_Val_Msg);
		Procurement_RFQ.RFQ_AddSupplier(Proc_Val_Msg); // Bug ID - 14810
		Procurement_RFQ.RFQ_AddSameSupplierTwice(Proc_Val_Msg);
		Procurement_RFQ.RFQ_RemoveSupplier(Proc_Val_Msg);
		Procurement_RFQ.RFQ_Mandatory_Fields_RFQNotesTable(Proc_Val_Msg);
		Procurement_RFQ.AddingNotes_255Char(Proc_Val_Msg);
		Procurement_RFQ.RemovingNotes(Proc_Val_Msg);
		Procurement_RFQ.PO_RFQ_assertions();
	}

	// @Proc_Neg07
	// Scenario: Procurements - RFQ Item Page (Manual RFQ)

	@Given("^Create Manual RFQ - PO_RFQ_CreatePage$")
	public void create_Manual_RFQ_PO_RFQ_CreatePage() throws InterruptedException {
		Procurement_RFQ.RFQ_Navigation();
		Procurement_RFQ.Create_RFQ_Link();
		Procurement_RFQ.PO_Manual_RFQ(Proc_Positive_Data, "Manual");
	}

	@Then("^Validate RFQ Items Page with Invalid Data - PO_RFQ_ItemsPage$")
	public void validate_RFQ_Items_Page_with_Invalid_Data_PO_RFQ_ItemsPage() {

	}

	// @Proc_Neg08
	// Scenario: Procurements - Quotes Search Page

	@Given("^Navigate to Quotes - PO_Quotes_SearchPage$")
	public void navigate_to_Quotes_PO_Quotes_SearchPage() {
		Procurement_Quotes.PO_Quotes_Navigation();
	}

	@Then("^Validate with Invalid Date Range - PO_Quotes_SearchPage$")
	public void validate_with_Invalid_Date_Range_PO_Quotes_SearchPage() {
		Procurement_Quotes.PO_Quotes_SearchPage_InvalidDate_Val(Proc_Val_Msg);
		Procurement_Quotes.PO_Quotes_assertions();
	}

	// @Proc_Neg09
	// Scenario: Procurements - Quotes Create Page

	@Given("^Create Quotes - PO_Quotes_CreatePage$")
	public void create_Quotes_PO_Quotes_CreatePage() {
		Procurement_Quotes.PO_Quotes_Navigation();
		Procurement_Quotes.Create_Quote_Link();
	}

	@Then("^Validate Quotes Create Page with Invalid data - PO_Quotes_CreatePage$")
	public void validate_Quotes_Create_Page_with_Invalid_data_PO_Quotes_CreatePage() {
		Procurement_Quotes.Quotes_Mandatory_Fields_CreateQuote(Proc_Val_Msg);
		Procurement_Quotes.Invalid_date_CreateQuote(Proc_Val_Msg);
		Procurement_Quotes.Val_CreateQuotes_Desc_255Char(Proc_Val_Msg);
		Procurement_Quotes.PO_Quotes_assertions();
	}

	// @Proc_Neg10
	// Scenario: Procurements - Quotes Update Quote Status Page

	@Given("^Create Quotes - PO_Quotes_UpdateQuoteStatusPage$")
	public void create_Quotes_PO_Quotes_UpdateQuoteStatusPage() {
		Procurement_Quotes.PO_Quotes_Navigation();
		Procurement_Quotes.Create_Quote_Link();
		Procurement_Quotes.Create_PO_Quotes(Proc_Val_Msg);
	}

	@Then("^Validate Update Quote Status Page with Invalid data - PO_Quotes_UpdateQuoteStatusPage$")
	public void validate_Update_Quote_Status_Page_with_Invalid_data_PO_Quotes_UpdateQuoteStatusPage() {
		Procurement_Quotes_UpdateQuoteStatus.UpdateQuoteStatus_Mandatory_Fields_OuoteItemListTable(Proc_Val_Msg);
		Procurement_Quotes_UpdateQuoteStatus.UpdateQuoteStatus_InvalidProductID_OuoteItemListTable(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_Quotes_UpdateQuoteStatus.UpdateQuoteStatus_InvalidQty_OuoteItemListTable(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_Quotes_UpdateQuoteStatus.UpdateQuoteStatus_InvalidDates_OuoteItemListTable(Proc_Val_Msg);
		Procurement_Quotes_UpdateQuoteStatus.UpdateQuoteStatus_AddingComments_255Char(Proc_Val_Msg);//
		Procurement_Quotes_UpdateQuoteStatus.UpdateQuoteStatus_Addinglineitems(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_Quotes_UpdateQuoteStatus.UpdateQuoteStatus_Editlineitems_InvalidQty(Proc_Val_Msg);
		Procurement_Quotes_UpdateQuoteStatus.QuoteNotes_Mandatory_Fields(Proc_Val_Msg);
		Procurement_Quotes_UpdateQuoteStatus.AddingQuotesNotes_255Char(Proc_Val_Msg);//
		Procurement_Quotes_UpdateQuoteStatus.Remove_QuoteNotes();//
		Procurement_Quotes_UpdateQuoteStatus.Val_RejectedQuoteLineitem_CreateOrder(Proc_Val_Msg);
		Procurement_Quotes_UpdateQuoteStatus.PO_UpdateQuoteStatus_assertions();

	}

	// @Proc_Neg11
	// Scenario: Procurements - Purchase Order Search Page

	@Given("^Navigate to Purchase Order - PO_PurchaseOrder_SearchPage$")
	public void navigate_to_Purchase_Order_PO_PurchaseOrder_SearchPage() {
		Procurement_PurchaseOrder.PurchaseOrder_Navigation();

	}

	@Then("^Validate with Invalid Date Range - PO_PurchaseOrder_SearchPage$")
	public void validate_with_Invalid_Date_Range_PO_PurchaseOrder_SearchPage() {
		Procurement_PurchaseOrder.PurchaseOrder_SearchPage_InvalidDate_Val(Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_PurchaseOrder_assertions();
	}

	// @Proc_Neg12
	// Scenario: Procurements - Purchase Order StartOrder Page

	@Given("^Navigate to Purchase Order - PO_PurchaseOrder_StartOrderPage$")
	public void navigate_to_Purchase_Order_PO_PurchaseOrder_StartOrderPage() {
		Procurement_PurchaseOrder.PurchaseOrder_Navigation();
	}

	@Then("^Validate StartOrder Page with Invalid data - PO_PurchaseOrder_StartOrderPage$")
	public void validate_StartOrder_Page_with_Invalid_data_PO_PurchaseOrder_StartOrderPage() {
		Procurement_PurchaseOrder.PO_StartOrder_MandatoryFields_POtable(Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_StartOrder_CancelBtn();
		Procurement_PurchaseOrder.PO_PurchaseOrder_assertions();
	}

	// @Proc_Neg13
	// Scenario: Procurements - Purchase Order ShippingDetails Page

	@Given("^Navigate to Purchase Order - PO_PurchaseOrder_ShippingDetailsPage$")
	public void navigate_to_Purchase_Order_PO_PurchaseOrder_ShippingDetailsPage() {
		Procurement_PurchaseOrder.PurchaseOrder_Navigation();
		Procurement_PurchaseOrder.PO_Start_Order(Proc_Positive_Data, "NegativeFlow", "Normal", "-Select-");

	}

	@Then("^Validate Shipping Details Page with Invalid data - PO_PurchaseOrder_ShippingDetailsPage$")
	public void validate_Shipping_Details_Page_with_Invalid_data_PO_PurchaseOrder_ShippingDetailsPage() {
		Procurement_PurchaseOrder.PO_ShpDetails_MandatoryFields(Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_ShpDetails_AddRemoveShpGrp();

		// Notes

		Procurement_PurchaseOrder.PO_PurchaseOrder_assertions();
	}

	// @Proc_Neg14
	// Scenario: Procurements - Purchase Order OrderItems Page

	@Given("^Navigate to Purchase Order - PO_PurchaseOrder_OrderItemsPage$")
	public void navigate_to_Purchase_Order_PO_PurchaseOrder_OrderItemsPage() {
		Procurement_PurchaseOrder.PurchaseOrder_Navigation();
		Procurement_PurchaseOrder.PO_Start_Order(Proc_Positive_Data, "NegativeFlow", "Normal", "-Select-");
		Procurement_PurchaseOrder.PO_Shipping_Details_SingleFacility(Proc_Positive_Data);

	}

	@Then("^Validate Order Items Page with Invalid data - PO_PurchaseOrder_OrderItemsPage$")
	public void validate_Order_Items_Page_with_Invalid_data_PO_PurchaseOrder_OrderItemsPage() {
		Procurement_PurchaseOrder.PO_OrderItems_MandatoryFields(Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_OrderItems_Invalid_PrdID(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_OrderItems_InvalidQty(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_OrderItems_DiscMsg_Val(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_OrderItems_AddComments_255Charac(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_OrderItems_Backbtn();
		Procurement_PurchaseOrder.PO_OrderItems_RemoveLineItems();
		Procurement_PurchaseOrder.PO_PurchaseOrder_assertions();
	}

	// @Proc_Neg15
	// Scenario: Procurements - Purchase Order Review&CreatePO Page

	@Given("^Navigate to Purchase Order - PO_PurchaseOrder_Review&CreatePOPage$")
	public void navigate_to_Purchase_Order_PO_PurchaseOrder_Review_CreatePOPage() throws InterruptedException {
		Procurement_PurchaseOrder.PurchaseOrder_Navigation();
		Procurement_PurchaseOrder.PO_Start_Order(Proc_Positive_Data, "NegativeFlow", "Normal", "-Select-");
		Procurement_PurchaseOrder.PO_Shipping_Details_SingleFacility(Proc_Positive_Data);
		Procurement_PurchaseOrder.PO_OrderItems(Proc_Positive_Data, 0);
		Procurement_PurchaseOrder.Continue_ChargesPage_WithoutCharges();
	}

	@Then("^Validate Review&CreatePO Page with Invalid data - PO_PurchaseOrder_Review&CreatePOPage$")
	public void validate_Review_CreatePO_Page_with_Invalid_data_PO_PurchaseOrder_Review_CreatePOPage() {
		Procurement_PurchaseOrder.PO_ReviewCreatePO_Notes_Val();
		Procurement_PurchaseOrder.PO_PurchaseOrder_assertions();
	}

	// @Proc_Neg16
	// Scenario: Procurements - Purchase Order PO_View Page

	@Given("^Navigate to Purchase Order - PO_PurchaseOrder_ViewPage$")
	public void navigate_to_Purchase_Order_PO_PurchaseOrder_ViewPage() throws InterruptedException {
		Procurement_PurchaseOrder.PurchaseOrder_Navigation();
		Procurement_PurchaseOrder.PO_Start_Order(Proc_Positive_Data, "NegativeFlow", "Normal", "-Select-");
		Procurement_PurchaseOrder.PO_Shipping_Details_SingleFacility(Proc_Positive_Data);
		Procurement_PurchaseOrder.PO_OrderItems(Proc_Positive_Data, 0);
		Procurement_PurchaseOrder.Continue_ChargesPage_WithoutCharges();
		Procurement_PurchaseOrder.PO_ReviewCreatePO("Proc_Valid_Data_NegFlow");
	}

	@Then("^Validate PO_View Page with Invalid data - PO_PurchaseOrder_ViewPage$")
	public void validate_PO_View_Page_with_Invalid_data_PO_PurchaseOrder_ViewPage() {
		Procurement_PurchaseOrder_View.AddCharges_MandatoryFields(Proc_Val_Msg);
		Procurement_PurchaseOrder_View.AddCharges_InvalidChargeID(Proc_Val_Msg);
		Procurement_PurchaseOrder_View.AddCharges_InvalidValue(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_PurchaseOrder_View.AddCharges_AddingSameChargeIDTwice(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_PurchaseOrder_View.AddCharges_DeleteCharges();
		Procurement_PurchaseOrder_View.CancelOrder();
		Procurement_PurchaseOrder_View.PO_View_assertions();

	}

	// @Proc_Neg17
	// Scenario: Procurements - Purchase Order PO_Edit Page

	@Given("^Navigate to Purchase Order - PO_PurchaseOrder_EditPage$")
	public void navigate_to_Purchase_Order_PO_PurchaseOrder_EditPage() throws InterruptedException {
		Procurement_PurchaseOrder.PurchaseOrder_Navigation();
		Procurement_PurchaseOrder.PO_Start_Order(Proc_Positive_Data, "PO_Edit NegativeFlow", "Normal", "-Select-");
		Procurement_PurchaseOrder.PO_Shipping_Details_SingleFacility(Proc_Positive_Data);
		Procurement_PurchaseOrder.PO_OrderItems(Proc_Positive_Data, 2);
		Procurement_PurchaseOrder.Continue_ChargesPage_WithoutCharges();
		Procurement_PurchaseOrder.PO_ReviewCreatePO("Proc_Valid_Data_NegFlow");
		Procurement_PurchaseOrder_View.EditOrderLink();
	}

	@Then("^Validate PO_View Page with Invalid data - PO_PurchaseOrder_EditPage$")
	public void validate_PO_View_Page_with_Invalid_data_PO_PurchaseOrder_EditPage() throws InterruptedException {

		// Ship before date and after date
		Procurement_PurchaseOrder.PO_Edit_OrderItems_InvalidQty(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_Edit_OrderItems_Discounts(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_Edit_OrderItems_Adjustments_MandatoryFields(Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_Edit_OrderItems_Adjustments_InvalidValue(Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_Edit_OrderItems_Adjustments_Desc255Charc(Proc_Val_Msg);//
		Procurement_PurchaseOrder.PO_Edit_OrderItems_AdjLineitem_InvalidValue(Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_Edit_OrderItems_DeleteAdjustments(Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_Edit_AddOrderItems_MandatoryFields(Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_Edit_AddOrderItems_Invalid_PrdID(Proc_Positive_Data, Proc_Val_Msg);//
		Procurement_PurchaseOrder.PO_Edit_AddOrderItems_InvalidQty(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_Edit_AddOrderItems_DiscMsg_Val(Proc_Positive_Data, Proc_Val_Msg);//
		Procurement_PurchaseOrder.PO_Edit_AddOrderItems_AddComments_255Charac(Proc_Positive_Data, Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_Edit_OtherDetails_Notes(Proc_Val_Msg);
		Procurement_PurchaseOrder.PO_PurchaseOrder_assertions();
	}

	// @Proc_Neg18
	// Scenario: Procurements - Purchase Order - WFC Approval -
	// PO_PurchaseOrder_ViewPage

	@Given("^Create Purchase Order with WFC configurations - PO_PurchaseOrder_ViewPage$")
	public void create_Purchase_Order_with_WFC_configurations_PO_PurchaseOrder_ViewPage() throws InterruptedException {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder.PO_Start_Order(TC01_2, "WFC Test", "Normal", "-Select-");
		Procurement_PurchaseOrder.PO_Shipping_Details_SingleFacility(TC01_2);
		Procurement_PurchaseOrder.PO_OrderItems(TC01_2, 4);
		Procurement_PurchaseOrder.Continue_ChargesPage_WithoutCharges();
		Procurement_PurchaseOrder.PO_ReviewCreatePO("Proc_Valid_Data_NegFlow");
	}

	@Then("^Approve Validation by Purchase VP - PO_PurchaseOrder_ViewPage$")
	public void approve_Validation_by_Purchase_VP_PO_PurchaseOrder_ViewPage() {
		Crest_Login.PurchaseVP_login();
		Procurement_PurchaseOrder_View.PO_Multilevel_Approval(Proc_Positive_Data, "Approve", "Approved");
	}

	@Then("^Return Validation by Pur CEO - PO_PurchaseOrder_ViewPage$")
	public void return_Validation_by_Pur_CEO_PO_PurchaseOrder_ViewPage() {
		Crest_Login.PurchaseCEO_login();
		Procurement_PurchaseOrder_View.PO_Multilevel_Approval(Proc_Positive_Data, "Return", "Returned");
	}

	@Then("^Reject Validation by Pur VP - PO_PurchaseOrder_ViewPage$")
	public void reject_Validation_by_Pur_VP_PO_PurchaseOrder_ViewPage() {
		Crest_Login.PurchaseVP_login();
		Procurement_PurchaseOrder_View.PO_Multilevel_Approval(Proc_Positive_Data, "Reject", "Rejected");
		Procurement_PurchaseOrder_View.WFC_History_Validations();
	}

	// @Proc_Neg19
	// Scenario: Procurements - Purchase Order - Cancel line items and
	// Validating all the values -PO_PurchaseOrder_EditPage

	@Given("^Create Purchase Order - PO_PurchaseOrder_EditPage$")
	public void create_Purchase_Order_PO_PurchaseOrder_EditPage() throws InterruptedException {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder.PurchaseOrder_Navigation();
		Procurement_PurchaseOrder.PO_Start_Order(Proc_Positive_Data, "Proc_Neg18", "Normal", "-Select-");
		Procurement_PurchaseOrder.PO_Shipping_Details_SingleFacility(Proc_Positive_Data);
		Procurement_PurchaseOrder.PO_OrderItems(Proc_Positive_Data, 4);
		Procurement_PurchaseOrder.Continue_ChargesPage_WithoutCharges();
		Procurement_PurchaseOrder.PO_ReviewCreatePO("Proc_Valid_Data_NegFlow");
		Procurement_PurchaseOrder.Edit_lineItem_Discounts(Proc_Positive_Data);
		Procurement_PurchaseOrder.Edit_Adjustments(Proc_Positive_Data);
		Procurement_PurchaseOrder_View.Add_Charges(Proc_Positive_Data);
		Procurement_PurchaseOrder_View.EditOrderLink();
	}

	@Then("^Validate Values after cancelling the Line Items - PO_PurchaseOrder_EditPage$")
	public void validate_Values_after_cancelling_and_Adding_the_Line_Items_PO_PurchaseOrder_EditPage() {
		Procurement_PurchaseOrder.PO_Edit_OrderItems_Cancel_lineitems(Proc_Val_Msg);
		Procurement_PurchaseOrder_View.PO_SubTotal_Val(Proc_Positive_Data, "Column15");
		Procurement_PurchaseOrder_View.PO_Tax_Val(Proc_Positive_Data, "Column16");
		Procurement_PurchaseOrder_View.OtherCharges_PopupBtn_Val(Proc_Positive_Data);
		Procurement_PurchaseOrder_View.Tax_Consolidated_PopupBtn_Val(Proc_Positive_Data);
		Procurement_PurchaseOrder_View.PO_GrdTotal_Val(Proc_Positive_Data, "Column17");
		Procurement_PurchaseOrder_View.ThirdPartyCharges_Table_Val(Proc_Positive_Data);
		Procurement_PurchaseOrder_View.TaxBreakup_Table_Val(Proc_Positive_Data);
	}

	@Then("^Validate Values after Adding a line Item - PO_PurchaseOrder_EditPage$")
	public void validate_Values_after_Adding_a_line_Item_PO_PurchaseOrder_EditPage() {

		// Adding Products
		Procurement_PurchaseOrder_View.EditOrderLink();
		Procurement_PurchaseOrder.PO_Edit_AddOrderItems_AddProducts(TC01_2);
		Procurement_PurchaseOrder_View.PO_SubTotal_Val(TC01_2, "Column15");
		Procurement_PurchaseOrder_View.PO_Tax_Val(TC01_2, "Column16");
		Procurement_PurchaseOrder_View.OtherCharges_PopupBtn_Val(TC01_2);
		Procurement_PurchaseOrder_View.Tax_Consolidated_PopupBtn_Val(TC01_2);
		Procurement_PurchaseOrder_View.PO_GrdTotal_Val(TC01_2, "Column17");
		Procurement_PurchaseOrder_View.ThirdPartyCharges_Table_Val(TC01_2);
		Procurement_PurchaseOrder_View.TaxBreakup_Table_Val(TC01_2);
		Procurement_PurchaseOrder_View.PO_View_assertions();
	}

	// @Proc_Neg20
	// Scenario: Procurement - Create As New Order

	@Given("^Navigate to Created purchase Order - PO_ViewPage$")
	public void navigate_to_Created_purchase_Order_PO_ViewPage() {
		Procurement_PurchaseOrder.Search_PO(Proc_Positive_Data);
	}

	@Then("^Create as New Order - PO_ViewPage$")
	public void create_as_New_Order_PO_ViewPage() {
		Procurement_PurchaseOrder.CreateAsNewOrder();
		Procurement_PurchaseOrder.PO_ReviewCreatePO("Proc_Valid_Data_NegFlow");
	}

	@Then("^Validate Values after Creating a New Order - PO ViewPage$")
	public void validate_Values_after_Creating_a_New_Order_PO_ViewPage() {
		Procurement_PurchaseOrder_View.PO_SubTotal_Val(TC01_2, "Column15");
		Procurement_PurchaseOrder_View.PO_Tax_Val(TC01_2, "Column16");
		Procurement_PurchaseOrder_View.OtherCharges_PopupBtn_Val(TC01_2);
		Procurement_PurchaseOrder_View.Tax_Consolidated_PopupBtn_Val(TC01_2);
		Procurement_PurchaseOrder_View.PO_GrdTotal_Val(TC01_2, "Column17");
		Procurement_PurchaseOrder_View.ThirdPartyCharges_Table_Val(TC01_2);
		Procurement_PurchaseOrder_View.TaxBreakup_Table_Val(TC01_2);
		Procurement_PurchaseOrder_View.PO_View_assertions();
	}

	// @Proc_Neg21
	// Scenario: Procurements - Purchase Order - Approve PO

	@Given("^Approve the Order - PO_PurchaseOrder_ViewPage$")
	public void approve_the_Order_PO_PurchaseOrder_ViewPage() {
		Crest_Login.PurchaseExc_login();
		Crest_Login.PurchaseVP_login();
		Procurement_PurchaseOrder_View.PO_Multilevel_Approval(Proc_Positive_Data, "Approve", "Approved");
		Crest_Login.PurchaseCEO_login();
		Procurement_PurchaseOrder_View.PO_Multilevel_Approval(Proc_Positive_Data, "Approve", "Approved");
	}

	// @Proc_Neg22
	// Scenario: Procurement - Purchase Return - PR_SearchPage

	@Given("^Navigate to Purchase Return Search Page - PR_SearchPage$")
	public void navigate_to_Purchase_Return_Search_Page_PR_SearchPage() {
		Procurement_PurchaseReturn.ProcurementLink();
		Procurement_PurchaseReturn.PurchaseReturnLink();
	}

	@Then("^Validate with Invalid date - PR_SearchPage$")
	public void validate_with_Invalid_date_PR_SearchPage() {
		Procurement_PurchaseReturn.InvalidDates_Val(Proc_Val_Msg);
		Procurement_PurchaseReturn.PurchaseReturn_softAssert();
	}

	// @Proc_Neg23
	// Scenario: Procurement - Purchase Return - PR_CreatePage

	@Given("^Navigate to Purchase Return Create Page - PR_CreatePage$")
	public void navigate_to_Purchase_Return_Create_Page_PR_CreatePage() {
		Procurement_PurchaseReturn.ProcurementLink();
		Procurement_PurchaseReturn.PurchaseReturnLink();
		Procurement_PurchaseReturn.CreatePurchaseReturnLink();
	}

	@Then("^Validate with Invalid date - PR_CreatePage$")
	public void validate_with_Invalid_date_PR_CreatePage() {
		Procurement_PurchaseReturn.MandatoryFields_Val(Proc_Val_Msg);
		Procurement_PurchaseReturn.PurchaseReturnPage_CancelBtn();
		Procurement_PurchaseReturn.PurchaseReturn_softAssert();
	}

	// @Proc_Neg24
	// Scenario: Procurement - Purchase Return - PurchaseReturnPage

	@Given("^Receive Inventory to Create Return - PurchaseReturnPage$")
	public void receive_Inventory_to_Create_Return_PurchaseReturnPage() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(Proc_Positive_Data, "Proc_Valid_Data_NegFlow", "Column25", "Column26", "Column27", 15, 0);
		Procurement_PurchaseOrder_View.CaptureInvID("Proc_Valid_Data_NegFlow");
	}

	@Then("^Create Return - PurchaseReturnPage$")
	public void create_Return_PurchaseReturnPage() {
		Procurement_PurchaseOrder.Search_PO(Proc_Positive_Data);
		Procurement_PurchaseOrder_View.CreateReturnLink();
	}

	@Then("^Validate with Invalid data - PurchaseReturnPage$")
	public void validate_with_Invalid_data_PurchaseReturnPage() {

		Procurement_PurchaseReturn.Invalid_ReturnQty(Proc_Val_Msg);
		Procurement_PurchaseReturn.Invalid_ReturnPrice(Proc_Val_Msg);
		Procurement_PurchaseReturn.ReturnSelecteditem();
		Procurement_PurchaseReturn_ReturnItems.AcceptReturnBtn();
		Procurement_PurchaseReturn_ReturnItems.Capture_ReturnID("Proc_Valid_Data_NegFlow");
		Procurement_PurchaseReturn.PurchaseReturn_softAssert();
	}

}
