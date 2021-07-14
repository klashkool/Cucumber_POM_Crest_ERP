package stepdefinition_files;

import java.text.ParseException;
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
import com.Utils.testexcelreport_val;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class TC01_Stepdef extends Base {


	List<HashMap<String, String>> TC01_data = CrestTestDataReader.get_TC01_Data();

	// @TC01-01
	// Scenario: Create PO - TC01

	@Given("^Create PO - TC_One$")
	public void create_PO_TC_One() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder.PO_Start_Order(TC01_data, "TC01 Test", "Normal", "-Select-");
	}

	@Then("^Add Shipping Details - TC_One$")
	public void add_Shipping_Details_TC_One() {
		Procurement_PurchaseOrder.PO_Shipping_Details_SingleFacility(TC01_data);
	}

	@Then("^Add Order Items - TC_One$")
	public void add_Order_Items_TC_One() throws InterruptedException {
		Procurement_PurchaseOrder.PO_OrderItems(TC01_data, 4);
		Procurement_PurchaseOrder.Continue_ChargesPage_WithoutCharges();
	}

	@Then("^Review & Create PO - TC_One$")
	public void review_Create_PO_TC_One() {
		Procurement_PurchaseOrder.PO_ReviewCreatePO("TC01");
	}

	@Then("^Validating SubTotal/Tax/GrandTotal/TaxConsolidated Btn/TaxBreakup Table  - TC_One$")
	public void validating_SubTotal_Tax_GrandTotal_TaxConsolidated_Btn_TaxBreakup_Table_TC_One() {
		Procurement_PurchaseOrder_View.PO_SubTotal_Val(TC01_data, "Column15");
		Procurement_PurchaseOrder_View.PO_Tax_Val(TC01_data, "Column16");
		Procurement_PurchaseOrder_View.Tax_Consolidated_PopupBtn_Val(TC01_data);
		Procurement_PurchaseOrder_View.PO_GrdTotal_Val(TC01_data, "Column17");
		Procurement_PurchaseOrder_View.TaxBreakup_Table_Val(TC01_data);
		Procurement_PurchaseOrder_View.PO_View_assertions();
	}

	// @TC01-02
	// Scenario: PO Approval - TC01

	@Given("^Approve PO \\(Single level Approval\\) - TC_One$")
	public void approve_PO_Single_level_Approval_TC_One() throws ParseException {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder_View.PO_WFC_Approvals(TC01_data);
	}

	// @TC01-03
	// Scenario: Receive Inventory (Single Shipment) - TC01

	@Given("^Receive Inventory \\(Single Shipment\\) - TC_One$")
	public void receive_Inventory_Single_Shipment_TC_One() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(TC01_data, "TC01", "Column25", "Column26", "Column27", 15, 0);
		Procurement_PurchaseOrder_View.CaptureInvID("TC01");
	}

	// @TC01-04
	// Scenario: Invoice Posting - TC01

	@Given("^Invoice Posting \\(Single Invoice\\) - TC_One$")
	public void invoice_Posting_Single_Invoice_TC_One() throws InterruptedException {
		AP_PurchaseInvoices.Invoice_Posting(TC01_data, 13);
		AP_PurchaseInvoices_Overview.Inv1_HeaderAmt_Val(TC01_data);
		AP_PurchaseInvoices_Overview.PI_Inv1_SubTotal_Validations(TC01_data, 13, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv1_Tax_Val(TC01_data, 13, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv1_GrdTotal_Val(TC01_data, 13, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv1_TaxBreakup_Table_Val(TC01_data);
	}

	@Then("^Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_One$")
	public void validating_SubTotal_Tax_GrandTotal_for_all_the_Generated_Invoices_TC_One() {
		AP_PurchaseInvoices_Overview.PI_View_softAssert();
	}

	// @TC01-05
	// Scenario: Payments - TC01

	@Given("^Payments \\(Single Payment\\) - TC_One$")
	public void payments_Single_Payment_TC_One() throws InterruptedException {
		AP_Payments.Payments(TC01_data, "TC01", "FullPayment", 15);
		AP_PurchaseInvoices_Overview.Inv1_OutstandingAmt_Val(TC01_data);
		AP_PurchaseInvoices_Overview.PI_View_OutstandingAmt_softAssert();
	}

	// @TC01-06
	// Scenario: Transactions Validations under Finance Module with Shipment
	// ID's- TC01

	@Given("^Validate Accounting transactions with ShipmentID under Finance Module - TC_One$")
	public void validate_Accounting_transactions_with_ShipmentID_under_Finance_Module_TC_One() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_ShipID_1(TC01_data, 13, "Column25", "Column36");
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_ShipID_softAssert();
	}

	@Then("^Validate Accounting transactions with InvoiceID under Finance Module - TC_One$")
	public void validate_Accounting_transactions_with_InvoiceID_under_Finance_Module_TC_One() {

	}

	// @TC01-08
	// Scenario: Transactions Validations under Finance Module with Payment ID-
	// TC01

	@Given("^Validate Accounting transactions with PaymentID under Finance Module - TC_One$")
	public void validate_Accounting_transactions_with_PaymentID_under_Finance_Module_TC_One() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_PaymentID(TC01_data, 13);
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_PaymentID_softAssert();
	}

	@Given("^Validate Stock Receipt Report$")
	public void validate_Stock_Receipt_Report() throws InterruptedException {
		testexcelreport_val.testexcel(TC01_data);
	}

}
