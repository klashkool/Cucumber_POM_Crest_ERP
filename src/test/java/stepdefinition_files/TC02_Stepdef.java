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



public class TC02_Stepdef extends Base {

	List<HashMap<String, String>> TC02_data = CrestTestDataReader.get_TC02_Data();

	// @TC02-01
	// Scenario: Create & Approve PO with Single Level Approval - TC02

	@Given("^Create PO - TC_Two$")
	public void create_PO_TC_Two() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder.PO_Start_Order(TC02_data, "PO TDS Flow", "Normal", "60 Days Credit");
		// Procurement_PurchaseOrder.PO_Start_Order(TC02_data, "PO TDS Flow",
		// "Normal", "-Select-");
	}

	@Then("^Add Shipping Details - TC_Two$")
	public void add_Shipping_Details_TC_Two() {
		Procurement_PurchaseOrder.PO_Shipping_Details_MultipleFacility(TC02_data, 1, 0, 2, 2);
	}

	@Then("^Add Order Items - TC_Two$")
	public void add_Order_Items_TC_Two() throws InterruptedException {
		Procurement_PurchaseOrder.PO_OrderItems(TC02_data, 4);
		Procurement_PurchaseOrder.Continue_ChargesPage_WithoutCharges();
	}

	@Then("^Review & Create PO - TC_Two$")
	public void review_Create_PO_TC_Two() {
		Procurement_PurchaseOrder.PO_ReviewCreatePO_TC02("TC02");
	}

	@Then("^Add Charges - TC_Two$")
	public void add_Charges_TC_Two() throws Throwable {
		Procurement_PurchaseOrder_View.Add_Charges(TC02_data);
	}

	@Then("^Validating SubTotal/OtherCharges_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - TC_Two$")
	public void validating_SubTotal_OtherCharges_PopupBtn_GrandTotal_ThirdPartyTax_Table_TaxBreakup_Table_TC_Two() {
		Procurement_PurchaseOrder_View.PayTerm_Val(TC02_data);
		Procurement_PurchaseOrder_View.PO_SubTotal_Val(TC02_data, "Column15");
		Procurement_PurchaseOrder_View.OtherCharges_PopupBtn_Val(TC02_data);
		Procurement_PurchaseOrder_View.PO_GrdTotal_Val(TC02_data, "Column17");
		Procurement_PurchaseOrder_View.ThirdPartyTax_Table_Val(TC02_data);
		Procurement_PurchaseOrder_View.ThirdPartyCharges_Table_Val(TC02_data);
		Procurement_PurchaseOrder_View.TaxBreakup_Table_Val(TC02_data);
		Procurement_PurchaseOrder_View.PO_View_assertions();

	}

	// @TC02-02
	// Scenario: PO Approval - TC02

	@Given("^Approve PO \\(Single level Approval\\) - TC_Two$")
	public void approve_PO_Single_level_Approval_TC_Two() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder_View.PO_WFC_Approvals(TC02_data);
	}

	// @TC02-03
	// Scenario: Receive Inventory (Multiple Shipment) - TC02

	@Given("^Receive Inventory for Shipment One - TC_Two$")
	public void receive_Inventory_for_Shipment_One_TC_Two() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(TC02_data, "TC02", "Column25", "Column26", "Column27", 15, 0);

	}

	@Then("^Receive Inventory for Shipment Two - TC_Two$")
	public void receive_Inventory_for_Shipment_Two_TC_Two() throws Throwable {
		Facilities_Shipments.PO_ReceiveInventory(TC02_data, "TC02", "Column37", "Column38", "Column39", 16, 0);
	}

	@Then("^Receive Inventory for Shipment Three - TC_Two$")
	public void receive_Inventory_for_Shipment_Three_TC_Two() throws Throwable {
		Facilities_Shipments.PO_ReceiveInventory(TC02_data, "TC02", "Column49", "Column50", "Column51", 17, 2);
		Procurement_PurchaseOrder_View.CaptureInvID("TC02");
	}

	// @TC02-04
	// Scenario: Invoice Posting (Multiple Invoice) - TC02

	@Given("^Invoice Posting for Generated Invoices - TC_Two$")
	public void invoice_Posting_for_Generated_Invoices_TC_Two() throws Throwable {

		// Invoice1
		AP_PurchaseInvoices.Invoice_Posting(TC02_data, 13);
		AP_PurchaseInvoices_Overview.Inv1_HeaderAmt_Val(TC02_data);
		AP_PurchaseInvoices_Overview.PayTerm_Inv1_Val(TC02_data);
		AP_PurchaseInvoices_Overview.PI_Inv1_SubTotal_Validations(TC02_data, 13, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv1_GrdTotal_Val(TC02_data, 13, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv1_ThirdPartyTax_Table_Val(TC02_data, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv1_ThirdPartyCharges_Table_Val(TC02_data, "Column29");
		AP_PurchaseInvoices_Overview.PI_Inv1_TaxBreakup_Table_Val(TC02_data);

		// Invoice2
		AP_PurchaseInvoices.Invoice_Posting(TC02_data, 14);
		AP_PurchaseInvoices_Overview.Inv2_HeaderAmt_Val(TC02_data);
		AP_PurchaseInvoices_Overview.PayTerm_Inv2_Val(TC02_data);
		AP_PurchaseInvoices_Overview.PI_Inv2_SubTotal_Validations(TC02_data, 14, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv2_GrdTotal_Val(TC02_data, 14, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv2_ThirdPartyTax_Table_Val(TC02_data, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv2_ThirdPartyCharges_Table_Val(TC02_data, "Column41");
		AP_PurchaseInvoices_Overview.PI_Inv2_TaxBreakup_Table_Val(TC02_data);

		// Invoice3
		AP_PurchaseInvoices.Invoice_Posting(TC02_data, 15);
		AP_PurchaseInvoices_Overview.Inv3_HeaderAmt_Val(TC02_data);
		AP_PurchaseInvoices_Overview.PayTerm_Inv3_Val(TC02_data);
		AP_PurchaseInvoices_Overview.PI_Inv3_SubTotal_Validations(TC02_data, 15, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv3_GrdTotal_Val(TC02_data, 15, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv3_ThirdPartyTax_Table_Val(TC02_data, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv3_ThirdPartyCharges_Table_Val(TC02_data, "Column53");
		AP_PurchaseInvoices_Overview.PI_Inv3_TaxBreakup_Table_Val(TC02_data);

	}

	@Then("^Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Two$")
	public void validating_SubTotal_Tax_GrandTotal_for_all_the_Generated_Invoices_TC_Two() {
		AP_PurchaseInvoices_Overview.PI_View_softAssert();
	}

	// @TC02-05
	// Scenario: Make Payments - Single Payment for all the Invoices - TC02

	@Given("^Payments \\(Single Payment\\) - TC_Two$")
	public void payments_Single_Payment_TC_Two() throws InterruptedException {
		// AP_Payments.Payments(TC02_data, "TC02", "FullPayment", 15);
		AP_Payments.PayTerm_Payments(TC02_data, "TC02", 15);
		AP_PurchaseInvoices_Overview.Inv1_OutstandingAmt_Val(TC02_data);
		AP_PurchaseInvoices_Overview.Inv2_OutstandingAmt_Val(TC02_data);
		AP_PurchaseInvoices_Overview.Inv3_OutstandingAmt_Val(TC02_data);
		AP_PurchaseInvoices_Overview.PayTerm_Inv1_PaidAmt_Val(TC02_data);
		AP_PurchaseInvoices_Overview.PayTerm_Inv2_PaidAmt_Val(TC02_data);
		AP_PurchaseInvoices_Overview.PayTerm_Inv3_PaidAmt_Val(TC02_data);
		AP_PurchaseInvoices_Overview.PI_View_OutstandingAmt_softAssert();
	}

	// @TC02-06
	// Scenario: Validate all the Inventories under Finance Module - TC02

	@Given("^Validate Accounting transactions with ShipmentID under Finance Module - TC_Two$")
	public void validate_Accounting_transactions_with_ShipmentID_under_Finance_Module_TC_Two() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_ShipID_1(TC02_data, 13, "Column25", "Column36");
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_ShipID_2(TC02_data, 14, "Column37", "Column48");
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_ShipID_3(TC02_data, 15, "Column49", "Column60");
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_ShipID_softAssert();

	}

	// @TC02-07
	// Scenario: Transactions Validations under Finance Module with Invoice ID-
	// TC02

	@Given("^Validate Accounting transactions with InvoiceID under Finance Module - TC_Two$")
	public void validate_Accounting_transactions_with_InvoiceID_under_Finance_Module_TC_Two() {

	}

	// @TC02-08
	// Scenario: Transactions Validations under Finance Module with Payment ID-
	// TC02

	@Given("^Validate Accounting transactions with PaymentID under Finance Module - TC_Two$")
	public void validate_Accounting_transactions_with_PaymentID_under_Finance_Module_TC_Two() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_PaymentID(TC02_data, 13);
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_PaymentID_softAssert();

	}

}
