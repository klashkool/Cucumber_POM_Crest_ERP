package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.Sales_Modules.Sales_ProformaInvoice;
import com.Sales_Modules.Sales_Quotes;
import com.Sales_Modules.Sales_Quotes_Edit;
import com.Sales_Modules.Sales_Quotes_QuoteItems;
import com.Sales_Modules.Sales_SalesOrder;
import com.Sales_Modules.Sales_SalesOrder_View;
import com.Sales_Modules.Sales_SalesReturn;
import com.Sales_Modules.Sales_SalesReturn_ReturnItems;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Sales_Negative_Scenarios_Stepdef extends Base {

	List<HashMap<String, String>> Sales_Validation_Msg_Data = CrestTestDataReader.get_Sales_Validation_Msg_Data();
	List<HashMap<String, String>> Sales_PositveData_For_NegativeFlow = CrestTestDataReader.get_Sales_PositveData_For_NegativeFlow();
	List<HashMap<String, String>> Sales_PositveData_SO_TC05_Data = CrestTestDataReader.get_SO_TC05_Data();

	@Given("^Navigate to Quotes - SO_Quotes_SearchPage$")
	public void navigate_to_Quotes_SO_Quotes_SearchPage() throws Throwable {
		Sales_Quotes.Navigate_To_Quotes();
	}

	@Then("^Validate with Invalid Date Range - SO_Quotes_SearchPage$")
	public void validate_with_Invalid_Date_Range_SO_Quotes_SearchPage() throws Throwable {
		Sales_Quotes.Validating_Quotes_date_range_with_invalid_date(Sales_Validation_Msg_Data);
		Sales_Quotes.Sales_Quote_assertions();
	}

	@Given("^Create Quote - SO_Quotes_CreatePage$")
	public void create_Quote_SO_Quotes_CreatePage() throws Throwable {
		Sales_Quotes.Navigate_To_Create_SO_Quote();
	}

	@Then("^Validate Create Quote Page with Invalid data - SO_Quotes_CreatePage$")
	public void validate_Create_Quote_Page_with_Invalid_data_SO_Quotes_CreatePage() throws Throwable {
		Sales_Quotes.Validating_without_Mandatory_details(Sales_Validation_Msg_Data);
		Sales_Quotes.Validate_PartyID_with_InvalidData(Sales_Validation_Msg_Data, "Column1", 5, "Column5");
		Sales_Quotes.Validate_ValidThruDate_with_InvalidData(Sales_PositveData_For_NegativeFlow, Sales_Validation_Msg_Data, 7, "Column1", 10, "Column2");
		Sales_Quotes.Sales_Quote_assertions();
	}

	@Given("^Create Quote - SO_Quotes_EditPage$")
	public void create_Quote_SO_Quotes_EditPage() throws Throwable {
		Sales_Quotes.Create_SO_Quotes_without_Items_NegativeFlow(Sales_PositveData_For_NegativeFlow);
	}

	@Then("^Validate Quote Edit_Page with Invalid data - SO_Quotes_EditPage$")
	public void validate_Quote_Edit_Page_with_Invalid_data_SO_Quotes_EditPage() throws Throwable {
		Sales_Quotes_Edit.AcceptQuote_without_adding_lineItem_and_Validate(Sales_Validation_Msg_Data, 3, "Column21");
		Sales_Quotes_Edit.Sales_Quote_Edit_assertions();
	}

	@Given("^Create Quote - SO_Quotes_ItemsPage$")
	public void create_Quote_SO_Quotes_ItemsPage() throws Throwable {
		Sales_Quotes.Create_SO_Quotes_without_Items_NegativeFlow(Sales_PositveData_For_NegativeFlow);
	}

	@Then("^Validate_Quote_Items Page with Invalid data - SO_Quotes_ItemsPage$")
	public void validate_quote_items_Page_with_Invalid_data_SO_Quotes_ItemsPage() throws Throwable {
		Sales_Quotes_QuoteItems.Validating_QuoteItems_without_adding_mandatory_details(Sales_Validation_Msg_Data);
		Sales_Quotes_QuoteItems.Validate_ProductID_with_InvalidData(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_Quotes_QuoteItems.Validate_Qty_with_Spl_Characters(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_Quotes_QuoteItems.Validate_Qty_with_Neg_Value(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_Quotes_QuoteItems.Validate_Discount_with_Spl_Characters(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_Quotes_QuoteItems.Validate_with_DiscountType_as_Percentage_and_Discount_as_Empty(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_Quotes_QuoteItems.Validate_with_Discount_Value_and_without_DiscountType(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_Quotes_QuoteItems.Add_Products(Sales_PositveData_For_NegativeFlow);
		Sales_Quotes_QuoteItems.Validate_with_negativeValue_and_spl_characters_as_Qty_in_added_lineItem(Sales_Validation_Msg_Data);
		Sales_Quotes_QuoteItems.Validate_by_making_empty_Qty_in_added_lineItem(Sales_Validation_Msg_Data);
		// Sales_Quotes_QuoteItems.Validate_by_updating_Comments_in_added_lineItem(Sales_Validation_Msg_Data);
		Sales_Quotes_QuoteItems.Validate_by_updating_Quote_lineItem_without_discount_value(Sales_Validation_Msg_Data);
		Sales_Quotes_QuoteItems.Validate_by_updating_Quote_lineItem_without_discountType(Sales_Validation_Msg_Data);
		Sales_Quotes_QuoteItems.Validate_alert_message_while_removing_lineItem(Sales_Validation_Msg_Data);
		// Sales_Quotes_QuoteItems.Validate_by_updating_Quote_Note(Sales_Validation_Msg_Data);
		//Sales_Quotes_QuoteItems.Validate_alert_message_while_removing_QuoteNote(Sales_Validation_Msg_Data);
		Sales_Quotes_QuoteItems.Sales_Quote_QuoteItems_assertions();
	}

	@Given("^Create and Accept the Quote - SO_Quotes_EditPage \\(Positive Flow\\)$")
	public void create_and_Accept_the_Quote_SO_Quotes_EditPage_Positive_Flow() throws Throwable {
		Sales_Quotes.Create_SO_Quotes_NegativeFlow(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow, "Sales_Valid_data_NegFlow");
		Sales_Quotes_Edit.SO_Accept_Quote();
	}

	@Then("^Verify the success message - SO_Quotes_EditPage \\(Positive Flow\\)$")
	public void verify_the_success_message_SO_Quotes_EditPage_Positive_Flow() throws Throwable {
		Sales_Quotes_Edit.Validate_AcceptQuote_successMsg(Sales_Validation_Msg_Data);
		Sales_Quotes.Sales_Quote_assertions();
		Sales_Quotes_Edit.Sales_Quote_Edit_assertions();
	}

	@Given("^Navigate to Sales Order - SO_SearchPage$")
	public void navigate_to_Sales_Order_SO_SearchPage() throws Throwable {
		Sales_SalesOrder.NavigateToSO();
	}

	@Then("^Validate with Invalid Date Range - SO_SearchPage$")
	public void validate_with_Invalid_Date_Range_SO_SearchPage() throws Throwable {
		Sales_SalesOrder.Validating_SO_Search_date_range_with_invalid_date(Sales_Validation_Msg_Data);
		Sales_SalesOrder.SO_View_assertions();
	}

	@Given("^Navigate to Sales Order - SO_StartOrder_Page$")
	public void navigate_to_Sales_Order_SO_StartOrder_Page() throws Throwable {
		Sales_SalesOrder.NavigateToCreateSO();
	}

	@Then("^Validate SO Start Order Page with Invalid data - SO_StartOrder_Page$")
	public void validate_SO_Start_Order_Page_with_Invalid_data_SO_StartOrder_Page() throws Throwable {
		Sales_SalesOrder.Validate_SO_StartOrder_without_Mandatory_Fields(Sales_Validation_Msg_Data);
		Sales_SalesOrder.Validate_SO_StartOrder_with_invalid_Date(Sales_PositveData_For_NegativeFlow, Sales_Validation_Msg_Data);
		Sales_SalesOrder.SO_View_assertions();
	}

	@Given("^Navigate to Sales Order - SO_ShippingDetails_Page$")
	public void navigate_to_Sales_Order_SO_ShippingDetails_Page() throws Throwable {
		Sales_SalesOrder.navigateToShippingDetailsPage(Sales_PositveData_For_NegativeFlow, "-Select-");
	}

	@Then("^Validate SO Shipping Details Page with Invalid data - SO_ShippingDetails_Page$")
	public void validate_SO_Shipping_Details_Page_with_Invalid_data_SO_ShippingDetails_Page() throws Throwable {
		// Sales_SalesOrder.Validate_SO_Shipping_address_isDisplayed(Sales_Validation_Msg_Data);
		Sales_SalesOrder.Validate_SO_Shipping_Details_without_Mandatory_Fields(Sales_Validation_Msg_Data);
		Sales_SalesOrder.Validate_SO_Shipping_Details_by_removing_ShipGroup(Sales_Validation_Msg_Data);
		Sales_SalesOrder.Validate_SO_Shipping_Details_with_invalid_date(Sales_Validation_Msg_Data);
		Sales_SalesOrder.Validate_SO_Shipping_Details_SameFacility_more_than_one_time(Sales_Validation_Msg_Data);
		Sales_SalesOrder.SO_View_assertions();
	}

	@Given("^Navigate to Sales Order - SO_OrderItems_Page$")
	public void navigate_to_Sales_Order_SO_OrderItems_Page() throws Throwable {
		Sales_SalesOrder.navigateToShippingDetailsPage(Sales_PositveData_For_NegativeFlow, "-Select-");
		Sales_SalesOrder.SO_Shipping_Details_SingleFacility(Sales_PositveData_For_NegativeFlow);
	}

	@Then("^Validate SO Order Items Page with Invalid data - SO_OrderItems_Page$")
	public void validate_SO_Order_Items_Page_with_Invalid_data_SO_OrderItems_Page() throws Throwable {
		Sales_SalesOrder.Validate_OrderItems_Back_button(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_SalesOrder.navigateToShippingDetailsPage(Sales_PositveData_For_NegativeFlow, "-Select-");
		Sales_SalesOrder.SO_Shipping_Details_SingleFacility(Sales_PositveData_For_NegativeFlow);
		Sales_SalesOrder.Validate_SO_OrderItems_without_Mandatory_Fields(Sales_Validation_Msg_Data);
		Sales_SalesOrder.Validate_ProductID_with_InvalidData(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_SalesOrder.Validate_Qty_with_SPL_characters(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_SalesOrder.Validate_Qty_with_Negative_Value(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_SalesOrder.Validate_Discount_with_SPL_characters(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_SalesOrder.Validate_Discount_without_DiscountType(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_SalesOrder.Validate_DiscountType_without_Discount(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_SalesOrder.SO_OrderItems_AddLineItems_NegativeFlow(Sales_PositveData_For_NegativeFlow, 1);
		Sales_SalesOrder.Validate_RemoveSelected_without_selecting_lineItem(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_SalesOrder.Validate_without_DropShip(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_SalesOrder.SO_View_assertions();
	}

	@Given("^Navigate to Sales Order - View_Page$")
	public void navigate_to_Sales_Order_View_Page() throws Throwable {
		//Sales_SalesOrder.Create_SO_For_NegativeFlow(Sales_PositveData_For_NegativeFlow, "Sales_Valid_data_NegFlow");
		Sales_SalesOrder.SO_StartOrder(Sales_PositveData_SO_TC05_Data, "SO Offline Payment Flow", "-Select-");
		Sales_SalesOrder.OfflinePayment_Chkbox();
		Sales_SalesOrder.SO_Shipping_Details_SingleFacility(Sales_PositveData_SO_TC05_Data);
		Sales_SalesOrder.SO_OrderItems(Sales_PositveData_SO_TC05_Data, 4);
		Sales_SalesOrder.SO_ReviewCreateSO("SO_TC05");
	}

	@Then("^Validate Sales Order View Page with Invalid Data - SO_View_Page$")
	public void validate_Sales_Order_View_Page_with_Invalid_Data_SO_View_Page() throws Throwable {
		Sales_SalesOrder_View.Validate_AddCharges_without_Charges(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_Add_Charges_with_huge_value(Sales_PositveData_SO_TC05_Data, Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Add_Charges(Sales_PositveData_For_NegativeFlow);
		Sales_SalesOrder_View.Validate_AddCharges_DeleteBtn_without_selecting_checkbox(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_SalesOrder_View.Validate_AddCharges_DeleteBtn_by_selecting_lineItem_checkbox(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_SalesOrder_View.SO_View_assertions();
	}

	@Given("^Navigate to Sales Order - Edit_Page$")
	public void navigate_to_Sales_Order_Edit_Page() throws Throwable {
	//	Sales_SalesOrder.Create_SO_For_NegativeFlow(Sales_PositveData_For_NegativeFlow, "Sales_Valid_data_NegFlow");
		Sales_SalesOrder.SO_StartOrder(Sales_PositveData_SO_TC05_Data, "SO Offline Payment Flow", "-Select-");
		Sales_SalesOrder.OfflinePayment_Chkbox();
		Sales_SalesOrder.SO_Shipping_Details_SingleFacility(Sales_PositveData_SO_TC05_Data);
		Sales_SalesOrder.SO_OrderItems(Sales_PositveData_SO_TC05_Data, 4);
		Sales_SalesOrder.SO_ReviewCreateSO("SO_TC05");
		Sales_SalesOrder.Edit_lineItem_Discounts(Sales_PositveData_SO_TC05_Data);
		Sales_SalesOrder.Edit_Adjustments(Sales_PositveData_SO_TC05_Data);
		Sales_SalesOrder_View.Add_Charges(Sales_PositveData_SO_TC05_Data);
		Sales_SalesOrder_View.EditOrderLink();
	}

	@Then("^Validate Sales Order Edit Page with Invalid Data - SO_Edit_Page$")
	public void validate_Sales_Order_Edit_Page_with_Invalid_Data_SO_Edit_Page() throws Throwable {
		Sales_SalesOrder_View.Validate_Ship_before_and_after_date_with_past_date(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_RecalculateSelected_without_selecting_lineItem(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_RecalculateSelected_lineItem_Qty_with_negativeValue(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_RecalculateSelected_lineItem_Qty_with_SPL_characters(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_RecalculateSelected_lineItem_Discount_with_emptyValue(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_RecalculateSelected_lineItem_without_DiscountType_with_DiscountValue(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_CancelSelected_without_selecting_lineItem(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_Select_And_CancelSelected_lineItem(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_AddOrderItemsSection_without_mandatory_fields(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_by_updating_OtherDetails_Section_without_NoteDescription(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_successMsg_by_updating_OtherDetails_Section(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_Delete_OtherDetails(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_by_Adding_OtherDetails_Section_without_NoteDescription(Sales_Validation_Msg_Data);

		Sales_SalesOrder_View.Validate_edit_SO_by_removing_2_lineItems(Sales_PositveData_For_NegativeFlow);
		Sales_SalesOrder_View.Validate_edit_SO_by_adding_2_lineItems(Sales_PositveData_SO_TC05_Data);
		Sales_SalesOrder_View.Validate_edit_SO_OrderItems_Add_Button_without_mandatory_fields(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_Edit_SO_Adj_description_with_more_than_255_Characters(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_Edit_SO_with_Adj_Value_with_SPL_Characters(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_edit_SO_under_AddOrderItem_section_without_mandatory_fields(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_edit_SO_under_AddOrderItem_section_productID_with_SPL_characters(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_edit_SO_under_AddOrderItem_section_Qty_with_SPL_characters(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_edit_SO_under_AddOrderItem_section_Comments_with_moreThan_250_characters(Sales_PositveData_For_NegativeFlow, Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.Validate_edit_SO_under_AddOrderItem_section_with_Invalid_Date_Range(Sales_PositveData_For_NegativeFlow, Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.SO_View_assertions();
	}

	@Given("^Navigate to Sales Order - Create As New Order$")
	public void navigate_to_Sales_Order_Create_As_New_Order() throws Throwable {
		//Sales_SalesOrder.Create_SO_For_NegativeFlow(Sales_PositveData_For_NegativeFlow, "Sales_Valid_data_NegFlow");
		Sales_SalesOrder.SO_StartOrder(Sales_PositveData_SO_TC05_Data, "SO Offline Payment Flow", "-Select-");
		Sales_SalesOrder.OfflinePayment_Chkbox();
		Sales_SalesOrder.SO_Shipping_Details_SingleFacility(Sales_PositveData_SO_TC05_Data);
		Sales_SalesOrder.SO_OrderItems(Sales_PositveData_SO_TC05_Data, 4);
		Sales_SalesOrder.SO_ReviewCreateSO("SO_TC05");
		Sales_SalesOrder.Edit_lineItem_Discounts(Sales_PositveData_SO_TC05_Data);
		Sales_SalesOrder.Edit_Adjustments(Sales_PositveData_SO_TC05_Data);
		Sales_SalesOrder_View.Add_Charges(Sales_PositveData_SO_TC05_Data);
		Sales_SalesOrder_View.CreateAsNewOrder();
	}

	@Then("^Validate Sales order - SO_Create As New Order$")
	public void validate_Sales_order_SO_Create_As_New_Order() throws Throwable {
		//Sales_SalesOrder_View.Validate_Order_Values(Sales_PositveData_For_NegativeFlow);
		Sales_SalesOrder_View.SO_SubTotal_Val(Sales_PositveData_SO_TC05_Data, "Column15");
		Sales_SalesOrder_View.SO_Tax_Val(Sales_PositveData_SO_TC05_Data, "Column16");
		Sales_SalesOrder_View.SO_GrdTotal_Val(Sales_PositveData_SO_TC05_Data, "Column17");
		Sales_SalesOrder_View.Tax_Consolidated_PopupBtn_Val(Sales_PositveData_SO_TC05_Data);
		Sales_SalesOrder_View.TaxBreakup_Table_Val(Sales_PositveData_SO_TC05_Data);
		Sales_SalesOrder_View.Validate_Cancelled_Item_Status(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		Sales_SalesOrder_View.SO_View_assertions();
	}

	@Given("^Navigate to Sales Order - Create Order$")
	public void navigate_to_Sales_Order_Create_Order() throws Throwable {
		Sales_SalesOrder.Create_SO_For_NegativeFlow(Sales_PositveData_For_NegativeFlow, "Sales_Valid_data_NegFlow", "-Select-");
	}

	@Then("^Validate Cancel Order - SO_Cancel Order$")
	public void validate_Cancel_Order_SO_Cancel_Order() throws Throwable {
		Sales_SalesOrder_View.Validate_Cancel_Order_Without_Note(Sales_Validation_Msg_Data);
		Sales_SalesOrder_View.SO_View_assertions();
	}

	@Given("^Navigate to Proforma Invoice - SO_ProformaInvoice_SearchPage$")
	public void navigate_to_Proforma_Invoice_SO_ProformaInvoice_SearchPage() throws Throwable {
		Sales_ProformaInvoice.Navigate_To_ProformaInvoice();
	}

	@Then("^Validate with Invalid Date Range - SO_ProformaInvoice_SearchPage$")
	public void validate_with_Invalid_Date_Range_SO_ProformaInvoice_SearchPage() throws Throwable {
		Sales_ProformaInvoice.Validating_ProformaInvoice_date_range_with_invalid_date(Sales_Validation_Msg_Data);
		Sales_ProformaInvoice.ProformaInvoice_assertions();
	}

	@Given("^Navigate to Create Proforma Invoice - SO_ProformaInvoice_CreatePage$")
	public void navigate_to_Create_Proforma_Invoice_SO_ProformaInvoice_CreatePage() throws Throwable {
		Sales_ProformaInvoice.Navigate_To_Create_ProformaInvoice();
	}

	@Then("^Validate Create Proforma Invoice Page with Invalid data - SO_ProformaInvoice_CreatePage$")
	public void validate_Create_Proforma_Invoice_Page_with_Invalid_data_SO_ProformaInvoice_CreatePage() throws Throwable {
		Sales_ProformaInvoice.Validating_without_Mandatory_details(Sales_Validation_Msg_Data);
		Sales_ProformaInvoice.Validate_PartyID_with_InvalidData(Sales_Validation_Msg_Data);
		Sales_ProformaInvoice.Validate_ProformaInvoiceDate_with_InvalidData(Sales_PositveData_For_NegativeFlow, Sales_Validation_Msg_Data);
		Sales_ProformaInvoice.Validate_ProformaInvoice_description_with_255_Characters(Sales_PositveData_For_NegativeFlow, Sales_Validation_Msg_Data);
		Sales_ProformaInvoice.Validate_Cancel_button_navigating_to_SearchPage();
		Sales_ProformaInvoice.ProformaInvoice_assertions();
	}

	@Given("^Navigate to Proforma Invoice Header - SO_ProformaInvoice_HeaderPage$")
	public void navigate_to_Proforma_Invoice_Header_SO_ProformaInvoice_HeaderPage() throws Throwable {
		Sales_ProformaInvoice.Navigate_To_Create_ProformaInvoice();
		Sales_ProformaInvoice.createProformaInvoice(Sales_PositveData_For_NegativeFlow);
		Sales_ProformaInvoice.Click_ProformaInvoiceHeaderLink();
	}

	@Then("^Validate Proforma Invoice Header Page with Invalid data - SO_ProformaInvoice_HeaderPage$")
	public void validate_Proforma_Invoice_Header_Page_with_Invalid_data_SO_ProformaInvoice_HeaderPage() throws Throwable {
		Sales_ProformaInvoice.Validate_Update_ProformaInvoice_description_with_255_Characters(Sales_PositveData_For_NegativeFlow, Sales_Validation_Msg_Data);
		Sales_ProformaInvoice.Validate_Cancel_button_navigating_to_SearchPage();
		Sales_ProformaInvoice.ProformaInvoice_assertions();
	}

	@Given("^Navigate to Proforma Invoice Items - SO_ProformaInvoice_ItemsPage$")
	public void navigate_to_Proforma_Invoice_Items_SO_ProformaInvoice_ItemsPage() throws Throwable {
		Sales_ProformaInvoice.Navigate_To_Create_ProformaInvoice();
		Sales_ProformaInvoice.createProformaInvoice(Sales_PositveData_For_NegativeFlow);
		Sales_ProformaInvoice.Click_ProformaInvoiceItemsLink();
	}

	@Then("^Validate Proforma Invoice Items Page with Invalid data - SO_ProformaInvoice_ItemsPage$")
	public void validate_Proforma_Invoice_Items_Page_with_Invalid_data_SO_ProformaInvoice_ItemsPage() throws Throwable {
		Sales_ProformaInvoice.Validate_ProformaInvoiceItems_Without_OrderID(Sales_Validation_Msg_Data);
		Sales_ProformaInvoice.Validate_OrderID_with_InvalidData(Sales_Validation_Msg_Data);//raised bug. After bug fix need to update the alert msg
		Sales_ProformaInvoice.Validate_OrderID_with_SO_other_than_Approved_Status(Sales_Validation_Msg_Data);
		Sales_ProformaInvoice.Validate_OrderID_with_SO_Approved_Status(Sales_Validation_Msg_Data);
		Sales_ProformaInvoice.Validate_AddSelectedItems_btn_isDisabled_when_no_line_item_selected(Sales_Validation_Msg_Data);
		Sales_ProformaInvoice.Validate_AddSelectedItems_btn_isEnabled_when_line_item_selected(Sales_Validation_Msg_Data);
		Sales_ProformaInvoice.Validate_AddSelectedItems_with_0_qty(Sales_Validation_Msg_Data);//validation msg shows diff label name. bug did not raised
		Sales_ProformaInvoice.Validate_AddSelectedItems_with_empty_qty(Sales_Validation_Msg_Data); //validation msg shows diff label name. bug raised
		Sales_ProformaInvoice.Validate_AddSelectedItems_with_ProformaQty_greaterThan_OrderQty(Sales_Validation_Msg_Data);
		Sales_ProformaInvoice.Validate_AddSelectedItems_with_SPL_characters_in_ProformaQty(Sales_Validation_Msg_Data);
		Sales_ProformaInvoice.Validate_by_Removing_line_Item(Sales_Validation_Msg_Data);
		Sales_ProformaInvoice.ProformaInvoice_assertions();
	}

	@Given("^Navigate to Proforma Invoice Summary - SO_ProformaInvoice_SummaryPage$")
	public void navigate_to_Proforma_Invoice_Summary_SO_ProformaInvoice_SummaryPage() throws Throwable {
		Sales_ProformaInvoice.Navigate_To_Create_ProformaInvoice();
		Sales_ProformaInvoice.createProformaInvoice(Sales_PositveData_For_NegativeFlow);
	}

	@Then("^Validate Proforma Invoice Summary Page with Invalid data - SO_ProformaInvoice_SummaryPage$")
	public void validate_Proforma_Invoice_Summary_Page_with_Invalid_data_SO_ProformaInvoice_SummaryPage() throws Throwable {
		Sales_ProformaInvoice.Validate_Cancel_ProformaInvoice();
		Sales_ProformaInvoice.ProformaInvoice_assertions();
	}

	@Given("^Navigate to Sales Return - SalesReturn_SearchPage$")
	public void navigate_to_Sales_Return_SalesReturn_SearchPage() throws Throwable {
		Sales_SalesReturn.Navigate_To_SalesReturn();
	}

	@Then("^Validate Sales Return Page with Invalid data - SalesReturn_SearchPage$")
	public void validate_Sales_Return_Page_with_Invalid_data_SalesReturn_SearchPage() throws Throwable {
		Sales_SalesReturn.Validating_SalesReturn_date_range_with_invalid_date(Sales_Validation_Msg_Data);
		Sales_SalesReturn.Sales_Return_assertions();
	}

	@Given("^Create Sales Return - SalesReturn_CreatePage$")
	public void create_Sales_Return_SalesReturn_CreatePage() throws Throwable {
		Sales_SalesReturn.Navigate_To_Create_SalesReturn();
	}

	@Then("^Validate Create Sales Return Page with Invalid data - SalesReturn_CreatePage$")
	public void validate_Create_Sales_Return_Page_with_Invalid_data_SalesReturn_CreatePage() throws Throwable {
		Sales_SalesReturn.Validate_Cancel_button_navigating_to_SalesReturnSearchPage();
		Sales_SalesReturn.Validate_Create_SalesReturn_without_Mandatory_Fields(Sales_Validation_Msg_Data);
		Sales_SalesReturn.Validate_FromParty_with_InvalidData(Sales_Validation_Msg_Data);
		Sales_SalesReturn.Sales_Return_assertions();
	}

	@Given("^Navigate to Sales Return - SalesReturn_ReturnItemsPage$")
	public void navigate_to_Sales_Return_SalesReturn_ReturnItemsPage() throws Throwable {
		Sales_SalesReturn.create_SalesReturn_Neg_flow(Sales_PositveData_For_NegativeFlow);
		Sales_SalesReturn_ReturnItems.clickReturnItems(Sales_PositveData_SO_TC05_Data);

	}

	@Then("^Validate Sales Return Page with Invalid data - SalesReturn_ReturnItemsPage$")
	public void validate_Sales_Return_Page_with_Invalid_data_SalesReturn_ReturnItemsPage() throws Throwable {
		Sales_SalesReturn_ReturnItems.validate_ReturnQty_by_leaving_empty(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_ReturnQty_with_more_than_OrderedQty(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_ReturnQty_with_negativeValue(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_ReturnQty_with_SPL_characters(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_ReturnPrice_by_leaving_empty(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_ReturnPrice_with_less_than_Sales_Price(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_ReturnPrice_with_more_than_Sales_Price(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_ReturnPrice_with_Neg_Value(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_ReturnPrice_with_SPL_Characters(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_by_updating_ReturnQty_by_leaving_empty(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_by_updating_ReturnQty_more_than_issuedQty(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_by_updating_ReturnQty_with_Neg_Value(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_by_updating_ReturnQty_with_SPL_Characters(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_by_updating_ReturnPrice_by_leaving_empty(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_by_updating_ReturnPrice_with_Neg_Value(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_by_updating_ReturnPrice_with_SPL_Characters(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_by_updating_ReturnPrice_more_than_SalesPrice(Sales_Validation_Msg_Data);
		Sales_SalesReturn_ReturnItems.validate_by_updating_ReturnPrice_less_than_SalesPrice(Sales_Validation_Msg_Data);
		Sales_SalesReturn.Sales_Return_assertions();
	}
}
