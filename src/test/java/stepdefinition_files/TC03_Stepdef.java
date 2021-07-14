package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.AccountsPayable_Modules.AP_Payments;
import com.AccountsPayable_Modules.AP_PurchaseInvoices;
import com.AccountsPayable_Modules.AP_PurchaseInvoices_Overview;
import com.Crest_ERP_Login.Crest_Login;
import com.Facilities_Modules.Facilities_Shipments;
import com.Financials_Modules.Financials_AcctTransaction;
import com.Financials_Modules.Financials_AcctTransaction_GLTransactionViewEntry;
import com.Procurement_Modules.Procurement_PurchaseOrder;
import com.Procurement_Modules.Procurement_PurchaseOrder_View;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class TC03_Stepdef extends Base {

	List<HashMap<String, String>> TC03_data = CrestTestDataReader.get_TC03_Data();

	// @TC03-01
	// Create PO with Two level Approval - TC03

	@Given("^Create PO - TC_Three$")
	public void create_PO_TC_Three() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder.PO_Start_Order(TC03_data, "TC03 Test", "Normal", "-Select-");
	}

	@Then("^Add Shipping Details - TC_Three$")
	public void add_Shipping_Details_TC_Three() {
		Procurement_PurchaseOrder.PO_Shipping_Details_SingleFacility(TC03_data);
	}

	@Then("^Add Order Items - TC_Three$")
	public void add_Order_Items_TC_Three() throws InterruptedException {
		Procurement_PurchaseOrder.PO_OrderItems(TC03_data, 4);
	}

	@Then("^Add Charges Before Creating PO - TC_Three$")
	public void add_Charges_Before_Creating_PO_TC_Three() {
		Procurement_PurchaseOrder.Charges(TC03_data);
	}

	@Then("^Review & Create PO - TC_Three$")
	public void review_Create_PO_TC_Three() {
		Procurement_PurchaseOrder.PO_ReviewCreatePO("TC03");
	}

	@Then("^Edit Order with Line Item Discounts - TC_Three$")
	public void edit_Order_with_Line_Item_Discounts_TC_Three() {
		Procurement_PurchaseOrder.Edit_lineItem_Discounts(TC03_data);
	}

	@Then("^Edit Order with Overall Discounts - TC_Three$")
	public void edit_Order_with_Overall_Discounts_TC_Three() {
		Procurement_PurchaseOrder.Edit_Adjustments(TC03_data);
	}

	@Then("^Add Charges After PO - TC_Three$")
	public void add_Charges_After_PO_TC_Three() throws InterruptedException {
		Procurement_PurchaseOrder_View.AddThirdparty_Charges(TC03_data);
	}

	@Then("^Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - TC_Three$")
	public void validating_SubTotal_OtherCharges_PopupBtn_Tax_Consolidated_PopupBtn_GrandTotal_ThirdPartyTax_Table_TaxBreakup_Table_TC_Three() {
		Procurement_PurchaseOrder_View.PO_SubTotal_Val(TC03_data, "Column15");
		Procurement_PurchaseOrder_View.PO_Tax_Val(TC03_data, "Column16");
		Procurement_PurchaseOrder_View.OtherCharges_PopupBtn_Val(TC03_data);
		Procurement_PurchaseOrder_View.Tax_Consolidated_PopupBtn_Val(TC03_data);
		Procurement_PurchaseOrder_View.PO_GrdTotal_Val(TC03_data, "Column17");
		Procurement_PurchaseOrder_View.ThirdPartyCharges_Table_Val(TC03_data);
		Procurement_PurchaseOrder_View.TaxBreakup_Table_Val(TC03_data);
		Procurement_PurchaseOrder_View.PO_View_assertions();
	}

	// @TC03-02
	// Scenario: PO Approval

	@Given("^Approve PO \\(Two level Approval\\) - TC_Three$")
	public void approve_PO_Two_level_Approval_TC_Three() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder_View.PO_WFC_Approvals(TC03_data);
	}

	// @TC03-03
	// Scenario: Receive Inventory (Multiple Shipments) - TC03

	@Given("^Receive Inventory for Shipment One - TC_Three$")
	public void receive_Inventory_for_Shipment_One_TC_Three() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(TC03_data, "TC03", "Column25", "Column26", "Column27", 15, 0);
	}

	@Then("^Receive Inventory for Shipment Two - TC_Three$")
	public void receive_Inventory_for_Shipment_Two_TC_Three() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(TC03_data, "TC03", "Column37", "Column38", "Column39", 16, 0);
	}

	@Then("^Receive Inventory for Shipment Three - TC_Three$")
	public void receive_Inventory_for_Shipment_Three_TC_Three() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(TC03_data, "TC03", "Column49", "Column50", "Column51", 17, 2);
		Procurement_PurchaseOrder_View.CaptureInvID("TC03");

	}

	// @TC03-04
	// Scenario: Invoice Posting (Multiple Invoice) - TC03

	@Given("^Invoice Posting for Generated Invoices - TC_Three$")
	public void invoice_Posting_for_Generated_Invoices_TC_Three() throws Throwable {

		// Invoice1
		AP_PurchaseInvoices.Invoice_Posting(TC03_data, 13);
		AP_PurchaseInvoices_Overview.Inv1_HeaderAmt_Val(TC03_data);
		AP_PurchaseInvoices_Overview.PI_Inv1_SubTotal_Validations(TC03_data, 13, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv1_Tax_Val(TC03_data, 13, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv1_GrdTotal_Val(TC03_data, 13, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv1_ThirdPartyCharges_Table_Val(TC03_data, "Column29");
		AP_PurchaseInvoices_Overview.PI_Inv1_TaxBreakup_Table_Val(TC03_data);

		// Invoice2
		AP_PurchaseInvoices.Invoice_Posting(TC03_data, 14);
		AP_PurchaseInvoices_Overview.Inv2_HeaderAmt_Val(TC03_data);
		AP_PurchaseInvoices_Overview.PI_Inv2_SubTotal_Validations(TC03_data, 14, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv2_Tax_Val(TC03_data, 14, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv2_GrdTotal_Val(TC03_data, 14, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv2_ThirdPartyCharges_Table_Val(TC03_data, "Column41");
		AP_PurchaseInvoices_Overview.PI_Inv2_TaxBreakup_Table_Val(TC03_data);

		// Invoice3
		AP_PurchaseInvoices.Invoice_Posting(TC03_data, 15);
		AP_PurchaseInvoices_Overview.Inv3_HeaderAmt_Val(TC03_data);
		AP_PurchaseInvoices_Overview.PI_Inv3_SubTotal_Validations(TC03_data, 15, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv3_Tax_Val(TC03_data, 15, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv3_GrdTotal_Val(TC03_data, 15, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv3_ThirdPartyCharges_Table_Val(TC03_data, "Column53");
		AP_PurchaseInvoices_Overview.PI_Inv3_TaxBreakup_Table_Val(TC03_data);

	}

	@Then("^Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Three$")
	public void validating_SubTotal_Tax_GrandTotal_for_all_the_Generated_Invoices_TC_Three() {
		AP_PurchaseInvoices_Overview.PI_View_softAssert();
	}

	// @TC03-05
	// Scenario: Payments - TC03

	@Given("^Payments \\(Partial Payment for One Invoice and Full Payment for Remaining Invoices\\) - TC_Three$")
	public void payments_Partial_Payment_for_One_Invoice_and_Full_Payment_for_Remaining_Invoices_TC_Three() throws Throwable {
		AP_Payments.Payments(TC03_data, "TC03", "PartialPayment", 15);
		AP_PurchaseInvoices_Overview.Inv1_OutstandingAmt_Val(TC03_data);
		AP_PurchaseInvoices_Overview.Inv2_OutstandingAmt_Val(TC03_data);
		AP_PurchaseInvoices_Overview.Inv3_OutstandingAmt_Val(TC03_data);
		AP_PurchaseInvoices_Overview.PI_View_OutstandingAmt_softAssert();
	}

	// @TC03-06
	// Scenario: Validate all the Inventories under Finance Module - TC03

	@Given("^Validate Accounting transactions with ShipmentID under Finance Module - TC_Three$")
	public void validate_Accounting_transactions_with_ShipmentID_under_Finance_Module_TC_Three() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_ShipID_1(TC03_data, 13, "Column25", "Column36");
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_ShipID_2(TC03_data, 14, "Column37", "Column48");
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_ShipID_3(TC03_data, 15, "Column49", "Column60");
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_ShipID_softAssert();

	}

	// @TC03-07
	// Scenario: Transactions Validations under Finance Module with Invoice ID-
	// TC03

	@Given("^Validate Accounting transactions with InvoiceID under Finance Module - TC_Three$")
	public void validate_Accounting_transactions_with_InvoiceID_under_Finance_Module_TC_Three() {

	}

	// @TC03-08
	// Scenario: Transactions Validations under Finance Module with Payment ID-
	// TC03

	@Given("^Validate Accounting transactions with PaymentID under Finance Module - TC_Three$")
	public void validate_Accounting_transactions_with_PaymentID_under_Finance_Module_TC_Three() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_PaymentID(TC03_data, 13);
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_PaymentID_softAssert();

	}
}
