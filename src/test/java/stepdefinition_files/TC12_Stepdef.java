package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.AccountsPayable_Modules.AP_Payments;
import com.AccountsPayable_Modules.AP_PurchaseInvoices;
import com.AccountsPayable_Modules.AP_PurchaseInvoices_Header;
import com.AccountsPayable_Modules.AP_PurchaseInvoices_Items;
import com.AccountsPayable_Modules.AP_PurchaseInvoices_Overview;
import com.AccountsPayable_Modules.AP_PurchaseInvoices_ShipmentDetails;
import com.Crest_ERP_Login.Crest_Login;
import com.Facilities_Modules.Facilities_Shipments;
import com.Financials_Modules.Financials_AcctTransaction;
import com.Financials_Modules.Financials_AcctTransaction_GLTransactionViewEntry;
import com.Procurement_Modules.Procurement_PurchaseOrder;
import com.Procurement_Modules.Procurement_PurchaseOrder_View;
import com.Sales_Modules.Sales_SalesOrder;
import com.Sales_Modules.Sales_SalesOrder_View;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;



public class TC12_Stepdef extends Base {

	List<HashMap<String, String>> AC_PO_data = CrestTestDataReader.get_AC_PO_TC12_Data();
	List<HashMap<String, String>> AC_SO_data = CrestTestDataReader.get_AC_SO_TC12_Data();
	List<HashMap<String, String>> AC_PO_data1 = CrestTestDataReader.get_AC_PO_TC12_Data1();

	// @TC12-01
	// Scenario: Create PO - TC12

	@Given("^Create PO - TC_Twelve$")
	public void create_PO_TC_Twelve() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder.PO_Start_Order(AC_PO_data, "Addtional Cost Test", "Normal", "-Select-");
	}

	@Then("^Add Shipping Details - TC_Twelve$")
	public void add_Shipping_Details_TC_Twelve() {
		Procurement_PurchaseOrder.PO_Shipping_Details_SingleFacility(AC_PO_data);
	}

	@Then("^Add Order Items - TC_Twelve$")
	public void add_Order_Items_TC_Twelve() throws InterruptedException {
		Procurement_PurchaseOrder.PO_OrderItems(AC_PO_data, 4);
		Procurement_PurchaseOrder.Continue_ChargesPage_WithoutCharges();
	}

	@Then("^Review & Create PO - TC_Twelve$")
	public void review_Create_PO_TC_Twelve() {
		Procurement_PurchaseOrder.PO_ReviewCreatePO("AC_PO_TC12");
	}

	@Then("^Edit Order with Line Item Discounts - TC_Twelve$")
	public void edit_Order_with_Line_Item_Discounts_TC_Twelve() {
		Procurement_PurchaseOrder.Edit_lineItem_Discounts(AC_PO_data);
	}

	@Then("^Edit Order with Overall Discounts - TC_Twelve$")
	public void edit_Order_with_Overall_Discounts_TC_Twelve() {
		Procurement_PurchaseOrder.Edit_Adjustments(AC_PO_data);
	}

	@Then("^Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - TC_Twelve$")
	public void validating_SubTotal_OtherCharges_PopupBtn_Tax_Consolidated_PopupBtn_GrandTotal_ThirdPartyTax_Table_TaxBreakup_Table_TC_Twelve()
	{
		Procurement_PurchaseOrder_View.PO_SubTotal_Val(AC_PO_data, "Column15");
		Procurement_PurchaseOrder_View.PO_Tax_Val(AC_PO_data, "Column16");
		Procurement_PurchaseOrder_View.Tax_Consolidated_PopupBtn_Val(AC_PO_data);
		Procurement_PurchaseOrder_View.PO_GrdTotal_Val(AC_PO_data, "Column17");
		Procurement_PurchaseOrder_View.ThirdPartyCharges_Table_Val(AC_PO_data);
		Procurement_PurchaseOrder_View.TaxBreakup_Table_Val(AC_PO_data);
		Procurement_PurchaseOrder_View.PO_View_assertions();
	}

	// @TC12-02
	// Scenario: PO Approval - TC12

	@Given("^Approve PO \\(Single level Approval\\) - TC_Twelve$")
	public void approve_PO_Single_level_Approval_TC_Twelve() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder_View.PO_WFC_Approvals(AC_PO_data);
	}

	// @TC12-03
	// Scenario: Receive Inventory - TC12

	@Given("^Receive Inventory \\(Single Shipment\\) - TC_Twelve$")
	public void receive_Inventory_Single_Shipment_TC_Twelve() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(AC_PO_data, "AC_PO_TC12", "Column25", "Column26", "Column27", 15, 0);
		Procurement_PurchaseOrder_View.CaptureInvID("AC_PO_TC12");
	}

	// @TC12-04
	// Scenario: Invoice Posting - TC12

	@Given("^Invoice Posting \\(Single Invoice\\) - TC_Twelve$")
	public void invoice_Posting_Single_Invoice_TC_Twelve() throws InterruptedException {
		AP_PurchaseInvoices.Invoice_Posting(AC_PO_data, 13);
		AP_PurchaseInvoices_Overview.Inv1_HeaderAmt_Val(AC_PO_data);
		AP_PurchaseInvoices_Overview.PI_Inv1_SubTotal_Validations(AC_PO_data, 13, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv1_Tax_Val(AC_PO_data, 13, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv1_GrdTotal_Val(AC_PO_data, 13, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv1_TaxBreakup_Table_Val(AC_PO_data);
	}

	@Then("^Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Twelve$")
	public void validating_SubTotal_Tax_GrandTotal_for_all_the_Generated_Invoices_TC_Twelve() {
		AP_PurchaseInvoices_Overview.PI_View_softAssert();
	}

	// @TC12-05
	// Scenario: Payments - TC12

	@Given("^Payments \\(Single Payment\\) - TC_Twelve$")
	public void payments_Single_Payment_TC_Twelve() throws InterruptedException {
		AP_Payments.Payments(AC_PO_data, "AC_PO_TC12", "FullPayment", 15);
		AP_PurchaseInvoices_Overview.Inv1_OutstandingAmt_Val(AC_PO_data);
		AP_PurchaseInvoices_Overview.PI_View_OutstandingAmt_softAssert();
	}

	// @SO_TC12-06
	// Scenario: Create SO with Offline Payment - SO_TC12

	@Given("^Create SO - SO_TC_Twelve$")
	public void create_SO_SO_TC_Twelve() {
		Sales_SalesOrder.SO_StartOrder(AC_SO_data, "Additional Cost Sales Flow", "-Select-");
		Sales_SalesOrder.OfflinePayment_Chkbox();
	}

	@Then("^Add Shipping Details - SO_TC_Twelve$")
	public void add_Shipping_Details_SO_TC_Twelve() {
		Sales_SalesOrder.SO_Shipping_Details_SingleFacility(AC_SO_data);
	}

	@Then("^Add Order Items - SO_TC_Twelve$")
	public void add_Order_Items_SO_TC_Twelve() throws InterruptedException {
		Sales_SalesOrder.SO_OrderItems(AC_SO_data, 4);
	}

	@Then("^Review & Create SO - SO_TC_Twelve$")
	public void review_Create_SO_SO_TC_Twelve() throws InterruptedException {
		Sales_SalesOrder.SO_ReviewCreateSO("AC_SO_TC12");
	}

	@Then("^Edit Order with Line Item Discounts - SO_TC_Twelve$")
	public void edit_Order_with_Line_Item_Discounts_SO_TC_Twelve() {
		Sales_SalesOrder.Edit_lineItem_Discounts(AC_SO_data);
	}

	@Then("^Edit Order with Overall Discounts - SO_TC_Twelve$")
	public void edit_Order_with_Overall_Discounts_SO_TC_Twelve() {
		Sales_SalesOrder.Edit_Adjustments(AC_SO_data);
	}

	@Then("^Add Charges - SO_TC_Twelve$")
	public void add_Charges_SO_TC_Twelve() throws InterruptedException {
		Sales_SalesOrder_View.Add_Charges(AC_SO_data);
	}

	@Then("^Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - SO_TC_Twelve$")
	public void validating_SubTotal_OtherCharges_PopupBtn_Tax_Consolidated_PopupBtn_GrandTotal_ThirdPartyTax_Table_TaxBreakup_Table_SO_TC_Twelve()
	{
		Sales_SalesOrder_View.SO_SubTotal_Val(AC_SO_data, "Column15");
		Sales_SalesOrder_View.SO_Tax_Val(AC_SO_data, "Column16");
		Sales_SalesOrder_View.Tax_Consolidated_PopupBtn_Val(AC_SO_data);
		Sales_SalesOrder_View.SO_GrdTotal_Val(AC_SO_data, "Column17");
		Sales_SalesOrder_View.ThirdPartyCharges_Table_Val(AC_SO_data);
		Sales_SalesOrder_View.TaxBreakup_Table_Val(AC_SO_data);
		Sales_SalesOrder_View.SO_View_assertions();
	}

	// @SO_TC12-07
	// Scenario: SO Approval - SO_TC12

	@Given("^Approve SO - SO_TC_Twelve$")
	public void approve_SO_SO_TC_Twelve() {
		Sales_SalesOrder_View.Approve_SO(AC_SO_data);
	}

	// @SO_TC12-08
	// Scenario: Create Shipment (Partial Qty) - SO_TC12

	@Given("^Create Shipment \\(Single Shipment\\) - SO_TC_Twelve$")
	public void create_Shipment_Single_Shipment_SO_TC_Twelve() throws InterruptedException {
		Facilities_Shipments.SO_CreateShipment(AC_SO_data, "AC_SO_TC12", "Column25", "Column26", "Column27", 15, 0);
	Sales_SalesOrder_View.SO_CaptureInvID("SO_TC01");

	}

	// @AC_TC12_01
	// Scenario: Create & Post Additional Cost Invoice - AC_TC12

	@Given("^Create and Post Additional Cost Invoice - AC_TC_Twelve$")
	public void create_and_Post_Additional_Cost_Invoice_AC_TC_Twelve() {
		AP_PurchaseInvoices.PurchaseInvoice_Navigation();
		AP_PurchaseInvoices.CreateAddtnlCostInvoice_Link();
		AP_PurchaseInvoices.CreateAddtnlCostInvoice(AC_PO_data);
		AP_PurchaseInvoices_ShipmentDetails.EnterShpID(AC_PO_data);
		AP_PurchaseInvoices_ShipmentDetails.ShipmentValue_Validation(AC_PO_data);
		AP_PurchaseInvoices_ShipmentDetails.Add_AdditionalCharges(AC_PO_data);
		AP_PurchaseInvoices_Header.Approve_Post_AddtnlCostInvoice();
		AP_PurchaseInvoices_Overview.AdditionalCost_InvoiceIDCapture("AC_PO_TC12");
	}

	@Then("^Validate Revised unit Cost - AC_TC_Twelve$")
	public void validate_Revised_unit_Cost_AC_TC_Twelve() {
		AP_PurchaseInvoices_Items.Items_link();
		AP_PurchaseInvoices_Items.RevisedUnitCost_Validation(AC_PO_data, AC_PO_data1);
		AP_PurchaseInvoices_Items.PI_Items_assertions();
	}

	// @AC_TC12_02
	// Scenario: Additional Cost Invoice Validation under Finance Module -
	// AC_TC12

	@Given("^Validate Additional Cost Invoice under Financials - AC_TC_Twelve$")
	public void validate_Additional_Cost_Invoice_under_Financials_AC_TC_Twelve() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_AddtnlCostInv(AC_PO_data);
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_InvID_softAssert();
	}

	// @StockIssue_01
	// Scenario: Create Stock Issue for the balance Qty - AC_TC12

	@Given("^Create Stock Issue - AC_TC_Twelve$")
	public void create_Stock_Issue_AC_TC_Twelve() {
		// Facilities_StockManagement_StockIssue.CreateStockIssue(AC_PO_data);//
		// Bug
																			// ID
																			// 15680
	}
}
