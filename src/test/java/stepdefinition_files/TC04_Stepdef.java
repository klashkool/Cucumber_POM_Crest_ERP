package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.AccountsPayable_Modules.AP_Payments;
import com.AccountsPayable_Modules.AP_PurchaseInvoices;
import com.AccountsPayable_Modules.AP_PurchaseInvoices_Overview;
import com.Crest_ERP_Login.Crest_Login;
import com.Facilities_Modules.Facilities_ReceiveAdHoc;
import com.Financials_Modules.Financials_AcctTransaction;
import com.Financials_Modules.Financials_AcctTransaction_GLTransactionViewEntry;
import com.Procurement_Modules.Procurement_PurchaseOrder;
import com.Procurement_Modules.Procurement_PurchaseOrder_View;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;



public class TC04_Stepdef extends Base {

	List<HashMap<String, String>> TC04_data = CrestTestDataReader.get_TC04_Data();

	// @TC04-01
	// Scenario: Create PO with Single level Approval for Non Stock Items - TC04

	@Given("^Create PO with Non Stock Items - TC_Four$")
	public void create_PO_with_Non_Stock_Items_TC_Four() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder.PO_Start_Order(TC04_data, "TC04 Test", "Normal", "-Select-");
	}

	@Then("^Add Shipping Details - TC_Four$")
	public void add_Shipping_Details_TC_Four() {
		Procurement_PurchaseOrder.PO_Shipping_Details_SingleFacility(TC04_data);
	}

	@Then("^Add Order Items - TC_Four$")
	public void add_Order_Items_TC_Four() throws InterruptedException {
		Procurement_PurchaseOrder.PO_Adhoc_OrderItems(TC04_data, 4);
		Procurement_PurchaseOrder.Continue_ChargesPage_WithoutCharges();
	}

	@Then("^Review & Create PO - TC_Four$")
	public void review_Create_PO_TC_Four() {
		Procurement_PurchaseOrder.PO_ReviewCreatePO("TC04");
	}

	@Then("^Validating SubTotal/Tax/GrandTotal/TaxConsolidated Btn/TaxBreakup Table  - TC_Four$")
	public void validating_SubTotal_Tax_GrandTotal_TaxConsolidated_Btn_TaxBreakup_Table_TC_Four() {
		Procurement_PurchaseOrder_View.PO_SubTotal_Val(TC04_data, "Column15");
		Procurement_PurchaseOrder_View.PO_Tax_Val(TC04_data, "Column16");
		Procurement_PurchaseOrder_View.Tax_Consolidated_PopupBtn_Val(TC04_data);
		Procurement_PurchaseOrder_View.PO_GrdTotal_Val(TC04_data, "Column17");
		Procurement_PurchaseOrder_View.TaxBreakup_Table_Val(TC04_data);
		Procurement_PurchaseOrder_View.PO_View_assertions();
	}

	// @TC04-02
	// Scenario: PO Approval - TC01

	@Given("^Approve PO \\(Single level Approval\\) - TC_Four$")
	public void approve_PO_Single_level_Approval_TC_Four() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder_View.PO_WFC_Approvals(TC04_data);
	}

	// @TC04-03
	// Scenario: Receive Non Stock Items - TC04

	@Given("^Receive Non Stock Items\\(Single Shipment\\) - TC_Four$")
	public void receive_Non_Stock_Items_Single_Shipment_TC_Four() throws Throwable {
		Facilities_ReceiveAdHoc.PO_Adhoc_Recv_Shpmt(TC04_data, "Column27");
		Procurement_PurchaseOrder_View.CaptureInvID("TC04");
	}

	// @TC04-04
	// Scenario: Invoice Posting - TC04

	@Given("^Invoice Posting \\(Single Invoice\\) - TC_Four$")
	public void invoice_Posting_Single_Invoice_TC_Four() throws Throwable {
		AP_PurchaseInvoices.Invoice_Posting(TC04_data, 13);
		AP_PurchaseInvoices_Overview.Inv1_HeaderAmt_Val(TC04_data);
		AP_PurchaseInvoices_Overview.PI_Inv1_SubTotal_Validations(TC04_data, 13, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv1_Tax_Val(TC04_data, 13, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv1_GrdTotal_Val(TC04_data, 13, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv1_TaxBreakup_Table_Val(TC04_data);

	}

	@Then("^Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Four$")
	public void validating_SubTotal_Tax_GrandTotal_for_all_the_Generated_Invoices_TC_Four() {
		AP_PurchaseInvoices_Overview.PI_View_softAssert();
	}

	// @TC04-05
	// Scenario: Payments - TC04

	@Given("^Payments \\(Single Payment\\) - TC_Four$")
	public void payments_Single_Payment_TC_Four() throws Throwable {
		AP_Payments.Payments(TC04_data, "TC04", "FullPayment", 15);
		AP_PurchaseInvoices_Overview.Inv1_OutstandingAmt_Val(TC04_data);
		AP_PurchaseInvoices_Overview.PI_View_OutstandingAmt_softAssert();
	}

	// @TC04-06
	// Scenario: Transactions Validations under Finance Module with Invoice ID-
	// TC04

	@Given("^Validate Accounting transactions with InvoiceID under Finance Module - TC_Four$")
	public void validate_Accounting_transactions_with_InvoiceID_under_Finance_Module_TC_Four() {

	}

	// @TC04-07
	// Scenario: Transactions Validations under Finance Module with Payment ID-
	// TC04

	@Given("^Validate Accounting transactions with PaymentID under Finance Module - TC_Four$")
	public void validate_Accounting_transactions_with_PaymentID_under_Finance_Module_TC_Four() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_PaymentID(TC04_data, 13);
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_PaymentID_softAssert();

	}
}