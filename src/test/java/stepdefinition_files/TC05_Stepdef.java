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
import com.Financials_Modules.Financials_GlobalGLSettings_ForeignExchangeRates;
import com.Procurement_Modules.Procurement_PurchaseOrder;
import com.Procurement_Modules.Procurement_PurchaseOrder_View;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;



public class TC05_Stepdef extends Base {

	List<HashMap<String, String>> TC05_data = CrestTestDataReader.get_TC05_Data();


	// @TC05-01
	// Scenario: Create a Exchange Rate - TC05

	@Given("^Configure Exchange Rate - TC_Five$")
	public void configure_Exchange_Rate_TC_Five() {
		Financials_GlobalGLSettings_ForeignExchangeRates.ConfigureXchangeRates(TC05_data, 13, "Column12");
	}

	// @TC05-02
	// Scenario: Creating Vendor Pre Payment(50% of Invoice Value) - TC05

	@Given("^Create Vendor Prepayment - TC_Five$")
	public void create_Vendor_Prepayment_TC_Five() {
		AP_Payments.VendorPrePayments(TC05_data, "TC05", "USD", 15);
	}

	// @TC05-03
	// Scenario: Create PO with Two level Approval - TC05

	@Given("^Create PO - TC_Five$")
	public void create_PO_TC_Five() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder.PO_Start_Order(TC05_data, "TC05 Test", "Imports", "-Select-");
	}

	@Then("^Add Shipping Details - TC_Five$")
	public void add_Shipping_Details_TC_Five() {
		Procurement_PurchaseOrder.PO_Shipping_Details_SingleFacility(TC05_data);
	}

	@Then("^Add Order Items - TC_Five$")
	public void add_Order_Items_TC_Five() throws InterruptedException {
		Procurement_PurchaseOrder.PO_OrderItems(TC05_data, 4);
	}

	@Then("^Add Charges Before Creating PO - TC_Five$")
	public void add_Charges_Before_Creating_PO_TC_Five() {
		Procurement_PurchaseOrder.Charges(TC05_data);
	}

	@Then("^Review & Create PO - TC_Five$")
	public void review_Create_PO_TC_Five() {
		Procurement_PurchaseOrder.PO_ReviewCreatePO("TC05");
	}

	@Then("^Edit Order with Line Item Discounts - TC_Five$")
	public void edit_Order_with_Line_Item_Discounts_TC_Five() {
		Procurement_PurchaseOrder.Edit_lineItem_Discounts(TC05_data);
	}

	@Then("^Edit Order with Overall Discounts - TC_Five$")
	public void edit_Order_with_Overall_Discounts_TC_Five() {
		Procurement_PurchaseOrder.Edit_Adjustments(TC05_data);
	}

	@Then("^Add Charges After PO - TC_Five$")
	public void add_Charges_After_PO_TC_Five() throws InterruptedException {
		Procurement_PurchaseOrder_View.AddThirdparty_Charges(TC05_data);
	}

	@Then("^Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - TC_Five$")
	public void validating_SubTotal_OtherCharges_PopupBtn_Tax_Consolidated_PopupBtn_GrandTotal_ThirdPartyTax_Table_TaxBreakup_Table_TC_Five() {
		Procurement_PurchaseOrder_View.PO_SubTotal_Val(TC05_data, "Column15");
		Procurement_PurchaseOrder_View.OtherCharges_PopupBtn_Val(TC05_data);
		Procurement_PurchaseOrder_View.PO_GrdTotal_Val(TC05_data, "Column17");
		Procurement_PurchaseOrder_View.ThirdPartyCharges_Table_Val(TC05_data);
		Procurement_PurchaseOrder_View.ThirdPartyTax_Table_Val(TC05_data);
		Procurement_PurchaseOrder_View.PO_View_assertions();
	}

	// @TC05-04
	// Scenario: PO Approval - TC05

	@Given("^Approve PO \\(Two level Approval\\) - TC_Five$")
	public void approve_PO_Two_level_Approval_TC_Five() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder_View.PO_WFC_Approvals_Imports(TC05_data);
	}

	// @TC05-05
	// Scenario: Receive Inventory (Multiple Shipments) - TC05

	@Given("^Receive Inventory for Shipment One - TC_Five$")
	public void receive_Inventory_for_Shipment_One_TC_Five() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(TC05_data, "TC05", "Column25", "Column26", "Column27", 15, 0);
	}

	@Then("^Receive Inventory for Shipment Two - TC_Five$")
	public void receive_Inventory_for_Shipment_Two_TC_Five() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(TC05_data, "TC05", "Column37", "Column38", "Column39", 16, 0);
	}

	@Then("^Receive Inventory for Shipment Three - TC_Five$")
	public void receive_Inventory_for_Shipment_Three_TC_Five() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(TC05_data, "TC05", "Column49", "Column50", "Column51", 17, 2);
		Procurement_PurchaseOrder_View.CaptureInvID("TC05");
	}

	// @TC05-06
	// Scenario: Invoice Posting (Multiple Invoice) - TC05

	@Given("^Invoice Posting for Generated Invoices - TC_Five$")
	public void invoice_Posting_for_Generated_Invoices_TC_Five() throws InterruptedException {

		// Invoice1
		AP_PurchaseInvoices.Invoice_Adj_Posting(TC05_data, 13);
		AP_PurchaseInvoices_Overview.Inv1_HeaderAmt_Val(TC05_data);
		AP_PurchaseInvoices_Overview.PI_Inv1_SubTotal_Validations(TC05_data, 13, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv1_GrdTotal_Val(TC05_data, 13, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv1_ThirdPartyTax_Table_Val(TC05_data, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv1_ThirdPartyCharges_Table_Val(TC05_data, "Column29");

		// Invoice2
		AP_PurchaseInvoices.Invoice_Posting(TC05_data, 14);
		AP_PurchaseInvoices_Overview.Inv2_HeaderAmt_Val(TC05_data);
		AP_PurchaseInvoices_Overview.PI_Inv2_SubTotal_Validations(TC05_data, 14, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv2_GrdTotal_Val(TC05_data, 14, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv2_ThirdPartyTax_Table_Val(TC05_data, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv2_ThirdPartyCharges_Table_Val(TC05_data, "Column41");

		// Invoice3
		AP_PurchaseInvoices.Invoice_Posting(TC05_data, 15);
		AP_PurchaseInvoices_Overview.Inv3_HeaderAmt_Val(TC05_data);
		AP_PurchaseInvoices_Overview.PI_Inv3_SubTotal_Validations(TC05_data, 15, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv3_GrdTotal_Val(TC05_data, 15, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv3_ThirdPartyTax_Table_Val(TC05_data, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv3_ThirdPartyCharges_Table_Val(TC05_data, "Column53");

	}

	@Then("^Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Five$")
	public void validating_SubTotal_Tax_GrandTotal_for_all_the_Generated_Invoices_TC_Five() {
		AP_PurchaseInvoices_Overview.PI_View_softAssert();
	}

	// @TC05-07
	// Scenario: Payments - TC05

	@Given("^Payments \\(Full Payment for Remaining Invoices after Adjustments\\) - TC_Five$")
	public void payments_Full_Payment_for_Remaining_Invoices_after_Adjustments_TC_Five() throws InterruptedException {
		AP_Payments.Confirm_VendorPrePayment(TC05_data);
		AP_Payments.Payments(TC05_data, "TC05", "FullPayment", 16);
		AP_PurchaseInvoices_Overview.Inv1_OutstandingAmt_Val(TC05_data);
		AP_PurchaseInvoices_Overview.Inv2_OutstandingAmt_Val(TC05_data);
		AP_PurchaseInvoices_Overview.Inv3_OutstandingAmt_Val(TC05_data);
		AP_PurchaseInvoices_Overview.PI_View_OutstandingAmt_softAssert();
	}

	// @TC05-08
	// Scenario: Transactions Validations under Finance Module with Shipment ID-
	// TC05

	@Given("^Validate Accounting transactions with ShipmentID under Finance Module - TC_Five$")
	public void validate_Accounting_transactions_with_ShipmentID_under_Finance_Module_TC_Five() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_ShipID_1(TC05_data, 13, "Column25", "Column36");
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_ShipID_2(TC05_data, 14, "Column37", "Column48");
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_ShipID_3(TC05_data, 15, "Column49", "Column60");
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_ShipID_softAssert();
	}

	// @TC05-09
	// Scenario: Transactions Validations under Finance Module with Invoice ID-
	// TC05

	@Given("^Validate Accounting transactions with InvoiceID under Finance Module - TC_Five$")
	public void validate_Accounting_transactions_with_InvoiceID_under_Finance_Module_TC_Five() {

	}

	// @TC05-10
	// Scenario: Transactions Validations under Finance Module with Payment ID-
	// TC05

	@Given("^Validate Accounting transactions with PaymentID under Finance Module - TC_Five$")
	public void validate_Accounting_transactions_with_PaymentID_under_Finance_Module_TC_Five() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_PaymentID_1_Imp(TC05_data, 13, 13);
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_PaymentID_2_Imp(TC05_data, 16, 16);
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_PaymentID_softAssert();
	}


}
