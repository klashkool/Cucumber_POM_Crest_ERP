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
import com.Facilities_Modules.Facilities_StockManagement_StockIssue;
import com.Financials_Modules.Financials_AcctTransaction;
import com.Financials_Modules.Financials_AcctTransaction_GLTransactionViewEntry;
import com.Financials_Modules.Financials_GlobalGLSettings_ForeignExchangeRates;
import com.Procurement_Modules.Procurement_PurchaseOrder;
import com.Procurement_Modules.Procurement_PurchaseOrder_View;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;



public class TC11_Stepdef extends Base {

	List<HashMap<String, String>> AC_IMP_PO_data = CrestTestDataReader.get_AC_PO_TC11_Data();
	List<HashMap<String, String>> AC_IMP_PO_data1 = CrestTestDataReader.get_AC_PO_TC11_Data1();

	// @TC11-01
	// Scenario: Create an Exchange Rate - TC11

	@Given("^Configure Exchange Rate - TC_Eleven$")
	public void configure_Exchange_Rate_TC_Eleven() {
		Financials_GlobalGLSettings_ForeignExchangeRates.ConfigureXchangeRates(AC_IMP_PO_data, 13, "Column12");
	}

	// @TC11-02
	// Scenario: Create PO with Two level Approval - TC11

	@Given("^Create PO - TC_Eleven$")
	public void create_PO_TC_Eleven() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder.PO_Start_Order(AC_IMP_PO_data, "TC11 Test", "Imports", "-Select-");
	}

	@Then("^Add Shipping Details - TC_Eleven$")
	public void add_Shipping_Details_TC_Eleven() {
		Procurement_PurchaseOrder.PO_Shipping_Details_SingleFacility(AC_IMP_PO_data);
	}

	@Then("^Add Order Items - TC_Eleven$")
	public void add_Order_Items_TC_Eleven() throws InterruptedException {
		Procurement_PurchaseOrder.PO_OrderItems(AC_IMP_PO_data, 4);
		Procurement_PurchaseOrder.Continue_ChargesPage_WithoutCharges();
	}

	@Then("^Review & Create PO - TC_Eleven$")
	public void review_Create_PO_TC_Eleven() {
		Procurement_PurchaseOrder.PO_ReviewCreatePO("AC_PO_TC11");
	}

	@Then("^Edit Order with Line Item Discounts - TC_Eleven$")
	public void edit_Order_with_Line_Item_Discounts_TC_Eleven() {
		Procurement_PurchaseOrder.Edit_lineItem_Discounts(AC_IMP_PO_data);
	}

	@Then("^Edit Order with Overall Discounts - TC_Eleven$")
	public void edit_Order_with_Overall_Discounts_TC_Eleven() {
		Procurement_PurchaseOrder.Edit_Adjustments(AC_IMP_PO_data);
	}

	@Then("^Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - TC_Eleven$")
	public void validating_SubTotal_OtherCharges_PopupBtn_Tax_Consolidated_PopupBtn_GrandTotal_ThirdPartyTax_Table_TaxBreakup_Table_TC_Eleven()
	{
		Procurement_PurchaseOrder_View.PO_SubTotal_Val(AC_IMP_PO_data, "Column15");
		Procurement_PurchaseOrder_View.PO_GrdTotal_Val(AC_IMP_PO_data, "Column17");
		Procurement_PurchaseOrder_View.ThirdPartyCharges_Table_Val(AC_IMP_PO_data);
		Procurement_PurchaseOrder_View.ThirdPartyTax_Table_Val(AC_IMP_PO_data);
		Procurement_PurchaseOrder_View.PO_View_assertions();
	}

	// @TC11-03
	// Scenario: PO Approval - TC11

	@Given("^Approve PO \\(Two level Approval\\) - TC_Eleven$")
	public void approve_PO_Two_level_Approval_TC_Eleven() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder_View.PO_WFC_Approvals_Imports(AC_IMP_PO_data);
	}

	// @TC11-04
	// Scenario: Receive Inventory (Single Shipments) - TC11

	@Given("^Receive Inventory \\(Single Shipment\\) - TC_Eleven$")
	public void receive_Inventory_Single_Shipment_TC_Eleven() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(AC_IMP_PO_data, "AC_PO_TC11", "Column25", "Column26", "Column27", 15, 0);
		Procurement_PurchaseOrder_View.CaptureInvID("AC_PO_TC11");
	}

	// @TC11-05
	// Scenario: Invoice Posting (Single Invoice) - TC11

	@Given("^Invoice Posting for Generated Invoices - TC_Eleven$")
	public void invoice_Posting_for_Generated_Invoices_TC_Eleven() throws InterruptedException {
		AP_PurchaseInvoices.Invoice_Posting(AC_IMP_PO_data, 13);
		AP_PurchaseInvoices_Overview.Inv1_HeaderAmt_Val(AC_IMP_PO_data);
		AP_PurchaseInvoices_Overview.PI_Inv1_SubTotal_Validations(AC_IMP_PO_data, 13, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv1_GrdTotal_Val(AC_IMP_PO_data, 13, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv1_ThirdPartyTax_Table_Val(AC_IMP_PO_data, "Column8");
	}

	@Then("^Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Eleven$")
	public void validating_SubTotal_Tax_GrandTotal_for_all_the_Generated_Invoices_TC_Eleven() {
		AP_PurchaseInvoices_Overview.PI_View_softAssert();
	}

	// @TC11-06
	// Scenario: Payments - TC11

	@Given("^Payments \\(Single Payment\\) - TC_Eleven$")
	public void payments_Single_Payment_TC_Eleven() throws InterruptedException {
		AP_Payments.Payments(AC_IMP_PO_data, "AC_PO_TC11", "FullPayment", 15);
		AP_PurchaseInvoices_Overview.Inv1_OutstandingAmt_Val(AC_IMP_PO_data);
		AP_PurchaseInvoices_Overview.PI_View_OutstandingAmt_softAssert();
	}

	// @AC_TC11_01
	// Scenario: Create & Post Additional Cost Invoice - AC_TC11

	@Given("^Create and Post Additional Cost Invoice - AC_TC_Eleven$")
	public void create_and_Post_Additional_Cost_Invoice_AC_TC_Eleven() {
		AP_PurchaseInvoices.PurchaseInvoice_Navigation();
		AP_PurchaseInvoices.CreateAddtnlCostInvoice_Link();
		AP_PurchaseInvoices.CreateAddtnlCostInvoice(AC_IMP_PO_data);
		AP_PurchaseInvoices_ShipmentDetails.EnterShpID(AC_IMP_PO_data);
		AP_PurchaseInvoices_ShipmentDetails.ShipmentValue_Validation(AC_IMP_PO_data);
		AP_PurchaseInvoices_ShipmentDetails.Add_AdditionalCharges(AC_IMP_PO_data);
		AP_PurchaseInvoices_Header.Approve_Post_AddtnlCostInvoice();
		AP_PurchaseInvoices_Overview.AdditionalCost_InvoiceIDCapture("AC_PO_TC11");
	}

	@Then("^Validate Revised unit Cost - AC_TC_Eleven$")
	public void validate_Revised_unit_Cost_AC_TC_Eleven() {
		AP_PurchaseInvoices_Items.Items_link();
		AP_PurchaseInvoices_Items.RevisedUnitCost_Validation(AC_IMP_PO_data, AC_IMP_PO_data1);
		AP_PurchaseInvoices_Items.PI_Items_assertions();
	}

	//	@AC_TC11_02
	//	Scenario: Additional Cost Invoice Validation under Finance Module - AC_TC11

	@Given("^Validate Additional Cost Invoice under Financials - AC_TC_Eleven$")
	public void validate_Additional_Cost_Invoice_under_Financials_AC_TC_Eleven() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_AddtnlCostInv(AC_IMP_PO_data);
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_InvID_softAssert();
	}

	// @StockIssue_01
	// Scenario: Create Stock Issue for the balance Qty - AC_TC11

	@Given("^Create Stock Issue - AC_TC_Eleven$")
	public void create_Stock_Issue_AC_TC_Eleven() {
		Facilities_StockManagement_StockIssue.CreateStockIssue(AC_IMP_PO_data);// Bug
																			// ID
																			// 15680
	}
}
