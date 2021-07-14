package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.AccountReceivable_Modules.AR_Receipts;
import com.AccountReceivable_Modules.AR_SalesInvoice;
import com.AccountReceivable_Modules.AR_SalesInvoice_Overview;
import com.Financials_Modules.Financials_AcctTransaction;
import com.Financials_Modules.Financials_AcctTransaction_GLTransactionViewEntry;
import com.Sales_Modules.Sales_SalesOrder;
import com.Sales_Modules.Sales_SalesOrder_View;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;



public class SO_TC10_Stepdef extends Base {

	List<HashMap<String, String>> SO_TC10_data = CrestTestDataReader.get_SO_TC10_Data();

	// @SO_TC10-01
	// Scenario: Create Service SO with Offline Payment - SO_TC10

	@Given("^Create SO - SO_TC_Ten$")
	public void create_SO_SO_TC_Ten() {
		Sales_SalesOrder.SO_StartOrder(SO_TC10_data, "SO Service Flow", "-Select-");
		Sales_SalesOrder.OfflinePayment_Chkbox();
	}

	@Then("^Add Shipping Details - SO_TC_Ten$")
	public void add_Shipping_Details_SO_TC_Ten() {
		Sales_SalesOrder.SO_Shipping_Details_SingleFacility(SO_TC10_data);
	}

	@Then("^Add Order Items - SO_TC_Ten$")
	public void add_Order_Items_SO_TC_Ten() throws InterruptedException {
		Sales_SalesOrder.SO_OrderItems(SO_TC10_data, 4);
	}

	@Then("^Review & Create SO - SO_TC_Ten$")
	public void review_Create_SO_SO_TC_Ten() throws InterruptedException {
		Sales_SalesOrder.SO_ReviewCreateSO("SO_TC10");
	}

	@Then("^Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - SO_TC_Ten$")
	public void validating_SubTotal_OtherCharges_PopupBtn_Tax_Consolidated_PopupBtn_GrandTotal_ThirdPartyTax_Table_TaxBreakup_Table_SO_TC_Ten() {
		Sales_SalesOrder_View.SO_SubTotal_Val(SO_TC10_data, "Column15");
		Sales_SalesOrder_View.SO_Tax_Val(SO_TC10_data, "Column16");
		Sales_SalesOrder_View.Tax_Consolidated_PopupBtn_Val(SO_TC10_data);
		Sales_SalesOrder_View.SO_GrdTotal_Val(SO_TC10_data, "Column17");
		Sales_SalesOrder_View.SO_View_assertions();
	}

	// SO_TC10-02
	// Scenario: SO Approval - SO_TC10

	@Given("^Approve SO - SO_TC_Ten$")
	public void approve_SO_SO_TC_Ten() {
		Sales_SalesOrder_View.Approve_SO(SO_TC10_data);
	}

	// @SO_TC10-03
	// Scenario: Generate Service Items Invoice

	@Given("^Generate Service Items Invoice - SO_TC_Ten$")
	public void generate_Service_Items_Invoice_SO_TC_Ten() {
		Sales_SalesOrder_View.Generate_ServiceOrder_Inv(SO_TC10_data);

		// Service Invoice 1
		Sales_SalesOrder.Create_ServiceOrder_Inv1(SO_TC10_data, "Column25", "Column27");

		// Service Invoice 2
		Sales_SalesOrder.Create_ServiceOrder_Inv2(SO_TC10_data, "Column37", "Column39");

		// Service Invoice 3
		Sales_SalesOrder.Create_ServiceOrder_Inv3(SO_TC10_data, "Column49", "Column51");
		Sales_SalesOrder.ServiceInvoicedQty_Val(SO_TC10_data, "Column2", "Column9", "Column9");
		Sales_SalesOrder_View.SO_CaptureInvID("SO_TC10");
	}

	// @SO_TC10-04
	// Scenario: Invoice Posting (Multiple Invoice) - SO_TC10

	@Given("^Invoice Posting for Generated Invoices - SO_TC_Ten$")
	public void invoice_Posting_for_Generated_Invoices_SO_TC_Ten() {

		// Invoice 1
		AR_SalesInvoice.SO_InvoicePosting(SO_TC10_data, 13);
		AR_SalesInvoice_Overview.Inv1_HeaderAmt_Val(SO_TC10_data);
		AR_SalesInvoice_Overview.SI_Inv1_SubTotal_Validations(SO_TC10_data, 13, "Column7");
		AR_SalesInvoice_Overview.SI_Inv1_Tax_Val(SO_TC10_data, 13, "Column8");
		AR_SalesInvoice_Overview.SI_Inv1_GrdTotal_Val(SO_TC10_data, 13, "Column9");

		// Invoice 2
		AR_SalesInvoice.SO_InvoicePosting(SO_TC10_data, 14);
		AR_SalesInvoice_Overview.Inv2_HeaderAmt_Val(SO_TC10_data);
		AR_SalesInvoice_Overview.SI_Inv2_SubTotal_Validations(SO_TC10_data, 14, "Column7");
		AR_SalesInvoice_Overview.SI_Inv2_Tax_Val(SO_TC10_data, 14, "Column8");
		AR_SalesInvoice_Overview.SI_Inv2_GrdTotal_Val(SO_TC10_data, 14, "Column9");

		// Invoice 3
		AR_SalesInvoice.SO_InvoicePosting(SO_TC10_data, 15);
		AR_SalesInvoice_Overview.Inv3_HeaderAmt_Val(SO_TC10_data);
		AR_SalesInvoice_Overview.SI_Inv3_SubTotal_Validations(SO_TC10_data, 15, "Column7");
		AR_SalesInvoice_Overview.SI_Inv3_Tax_Val(SO_TC10_data, 15, "Column8");
		AR_SalesInvoice_Overview.SI_Inv3_GrdTotal_Val(SO_TC10_data, 15, "Column9");
	}

	@Then("^Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - SO_TC_Ten$")
	public void validating_SubTotal_Tax_GrandTotal_for_all_the_Generated_Invoices_SO_TC_Ten() {
		AR_SalesInvoice_Overview.SI_View_softAssert();
	}

	// @SO_TC10-05
	// Scenario: Receipts - SO_TC10

	@Given("^Receipts \\(Partial Receipt for One Invoice and Full Receipt for Remaining Invoices\\) - SO_TC_Ten$")
	public void receipts_Partial_Receipt_for_One_Invoice_and_Full_Receipt_for_Remaining_Invoices_SO_TC_Ten() throws InterruptedException {
		AR_Receipts.Receipts(SO_TC10_data, "SO_TC10", "PartialPayment", 15, "INR");
		AR_SalesInvoice_Overview.Inv1_OutstandingAmt_Val(SO_TC10_data);
		AR_SalesInvoice_Overview.Inv2_OutstandingAmt_Val(SO_TC10_data);
		AR_SalesInvoice_Overview.Inv3_OutstandingAmt_Val(SO_TC10_data);
		AR_SalesInvoice_Overview.SI_View_OutstandingAmt_softAssert();
	}


	// @SO_TC10-06
	// Scenario: Transactions Validations under Finance Module with Invoice ID-
	// SO_TC10

	@Given("^Validate Accounting transactions with InvoiceID under Finance Module - SO_TC_Ten$")
	public void validate_Accounting_transactions_with_InvoiceID_under_Finance_Module_SO_TC_Ten() {

	}

	// @SO_TC10-07
	// Scenario: Transactions Validations under Finance Module with Payment ID-
	// SO_TC10

	@Given("^Validate Accounting transactions with PaymentID under Finance Module - SO_TC_Ten$")
	public void validate_Accounting_transactions_with_PaymentID_under_Finance_Module_SO_TC_Ten() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_PaymentID(SO_TC10_data, 13);
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_PaymentID_softAssert();
	}

}
