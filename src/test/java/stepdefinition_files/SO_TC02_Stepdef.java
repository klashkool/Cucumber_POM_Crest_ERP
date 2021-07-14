package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.AccountReceivable_Modules.AR_Receipts;
import com.AccountReceivable_Modules.AR_SalesInvoice;
import com.AccountReceivable_Modules.AR_SalesInvoice_Overview;
import com.Facilities_Modules.Facilities_Shipments;
import com.Financials_Modules.Financials_AcctTransaction;
import com.Financials_Modules.Financials_AcctTransaction_GLTransactionViewEntry;
import com.Party_Modules.Party_Parties;
import com.Party_Modules.Party_Parties_BasicConfigs_Profile;
import com.Sales_Modules.Sales_Quotes;
import com.Sales_Modules.Sales_Quotes_Edit;
import com.Sales_Modules.Sales_Quotes_QuoteItems;
import com.Sales_Modules.Sales_Quotes_View;
import com.Sales_Modules.Sales_SalesOrder;
import com.Sales_Modules.Sales_SalesOrder_View;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;
import com.Utils.WebdriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SO_TC02_Stepdef extends Base {

	List<HashMap<String, String>> SO_TC02_data = CrestTestDataReader.get_SO_TC02_Data();

	// @SO_TC02-01
	// Scenario: Capture Billing Acct Balance - SO_TC_Two

	@Given("^Capture Billing Acct Balance - SO_TC_Two$")
	public void capture_Billing_Acct_Balance_SO_TC_Two() {
		Party_Parties.PartySearch("11651");// Workphilia
		Party_Parties_BasicConfigs_Profile.Capture_BillingAcct_Bal_BeforeSO("SO_TC02");
	}

	// @TestSO_TC02-02
	// Scenario: Create Quotes - SO_TC02

	@Given("^Create New Sales Quote - SO_TC_Two$")
	public void create_New_Sales_Quote_SO_TC_Two() throws InterruptedException {
		Sales_Quotes.Create_SO_Quotes(SO_TC02_data, "SO_TC02");
	}

	@Then("^Add Products with Discounts - SO_TC_Two$")
	public void add_Products_with_Discounts_SO_TC_Two() throws InterruptedException {
		Sales_Quotes_QuoteItems.Add_Products(SO_TC02_data);
		Sales_Quotes_Edit.SO_Accept_Quote();
		Sales_Quotes_View.Quote_Header_Val();
	}

	@Then("^Validate Quotes SubTotal & GrandTotal - SO_TC_Two$")
	public void validate_Quotes_SubTotal_GrandTotal_SO_TC_Two() {
		Sales_Quotes_View.SO_Quotes_GrndTotal_Val(SO_TC02_data, "Column15");
		Sales_Quotes_View.SO_View_assertions();
	}

	@Then("^Convert Sales Quotes into Sales Order - SO_TC_Two$")
	public void convert_Sales_Quotes_into_Sales_Order_SO_TC_Two() {
		Sales_Quotes_View.SO_Quotes_CreateOrderLink();
		Sales_SalesOrder.BillingAcct_Chkbox();
		Sales_SalesOrder.BillingAcct_value_Val(SO_TC02_data);
		WebdriverWait.findElement("link", "Continue").click();
		WebdriverWait.findElement("link", "Continue").click();
		WebdriverWait.findElement("link", "Continue").click();
	}

	@Then("^Review and Create SO - SO_TC_Two$")
	public void review_and_Create_SO_SO_TC_Two() throws InterruptedException {
		Sales_SalesOrder.SO_ReviewCreateSO("SO_TC02");
	}

	@Then("^Validating SubTotal/Tax/GrandTotal/TaxConsolidated Btn/TaxBreakup Table - SO_TC_Two$")
	public void validating_SubTotal_Tax_GrandTotal_TaxConsolidated_Btn_TaxBreakup_Table_SO_TC_Two() {
		Sales_SalesOrder_View.SO_SubTotal_Val(SO_TC02_data, "Column15");
		Sales_SalesOrder_View.SO_Tax_Val(SO_TC02_data, "Column16");
		Sales_SalesOrder_View.Tax_Consolidated_PopupBtn_Val(SO_TC02_data);
		Sales_SalesOrder_View.SO_GrdTotal_Val(SO_TC02_data, "Column17");
		Sales_SalesOrder_View.TaxBreakup_Table_Val(SO_TC02_data);
		Sales_SalesOrder_View.SO_View_assertions();
	}

	// @SO_TC02-03
	// Scenario: SO Approval - SO_TC02

	@Given("^Approve SO - SO_TC_Two$")
	public void approve_SO_SO_TC_Two() {
		Sales_SalesOrder_View.Approve_SO(SO_TC02_data);
	}

	// @SO_TC02-04
	// Scenario: Create Shipment (Multiple Shipments) - SO_TC02

	@Given("^Create Shipment One - SO_TC_Two$")
	public void create_Shipment_One_SO_TC_Two() throws InterruptedException {
		Facilities_Shipments.SO_CreateShipment(SO_TC02_data, "SO_TC02", "Column25", "Column26", "Column27", 15, 0);
	}

	@Then("^Create Shipment Two - SO_TC_Two$")
	public void create_Shipment_Two_SO_TC_Two() throws InterruptedException {
		Facilities_Shipments.SO_CreateShipment(SO_TC02_data, "SO_TC02", "Column37", "Column38", "Column39", 16, 0);
	}

	@Then("^Create Shipment Three - SO_TC_Two$")
	public void create_Shipment_Three_SO_TC_Two() throws InterruptedException {
		Facilities_Shipments.SO_CreateShipment(SO_TC02_data, "SO_TC02", "Column49", "Column50", "Column51", 17, 2);
		Sales_SalesOrder_View.SO_CaptureInvID("SO_TC02");
	}

	// @SO_TC02-05
	// Scenario: Invoice Posting (Multiple Invoice) - SO_TC02

	@Given("^Invoice Posting for Generated Invoices - SO_TC_Two$")
	public void invoice_Posting_for_Generated_Invoices_SO_TC_Two() {

		// Invoice 1
		AR_SalesInvoice.SO_InvoicePosting(SO_TC02_data, 13);
		AR_SalesInvoice_Overview.Inv1_HeaderAmt_Val(SO_TC02_data);
		AR_SalesInvoice_Overview.SI_Inv1_SubTotal_Validations(SO_TC02_data, 13, "Column7");
		AR_SalesInvoice_Overview.SI_Inv1_Tax_Val(SO_TC02_data, 13, "Column8");
		AR_SalesInvoice_Overview.SI_Inv1_GrdTotal_Val(SO_TC02_data, 13, "Column9");
		AR_SalesInvoice_Overview.SI_Inv1_TaxBreakup_Table_Val(SO_TC02_data);

		// Invoice 2
		AR_SalesInvoice.SO_InvoicePosting(SO_TC02_data, 14);
		AR_SalesInvoice_Overview.Inv2_HeaderAmt_Val(SO_TC02_data);
		AR_SalesInvoice_Overview.SI_Inv2_SubTotal_Validations(SO_TC02_data, 14, "Column7");
		AR_SalesInvoice_Overview.SI_Inv2_Tax_Val(SO_TC02_data, 14, "Column8");
		AR_SalesInvoice_Overview.SI_Inv2_GrdTotal_Val(SO_TC02_data, 14, "Column9");
		AR_SalesInvoice_Overview.SI_Inv2_TaxBreakup_Table_Val(SO_TC02_data);

		// Invoice 3
		AR_SalesInvoice.SO_InvoicePosting(SO_TC02_data, 15);
		AR_SalesInvoice_Overview.Inv3_HeaderAmt_Val(SO_TC02_data);
		AR_SalesInvoice_Overview.SI_Inv3_SubTotal_Validations(SO_TC02_data, 15, "Column7");
		AR_SalesInvoice_Overview.SI_Inv3_Tax_Val(SO_TC02_data, 15, "Column8");
		AR_SalesInvoice_Overview.SI_Inv3_GrdTotal_Val(SO_TC02_data, 15, "Column9");
		AR_SalesInvoice_Overview.SI_Inv3_TaxBreakup_Table_Val(SO_TC02_data);
	}

	@Then("^Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - SO_TC_Two$")
	public void validating_SubTotal_Tax_GrandTotal_for_all_the_Generated_Invoices_SO_TC_Two() {
		AR_SalesInvoice_Overview.SI_View_softAssert();
	}

	// @SO_TC02-06
	// Scenario: Receipts - SO_TC02

	@Given("^Receipts \\(Partial Receipt for One Invoice and Full Receipt for Remaining Invoices\\) - SO_TC_Two$")
	public void receipts_Partial_Receipt_for_One_Invoice_and_Full_Receipt_for_Remaining_Invoices_SO_TC_Two() throws InterruptedException {
		AR_Receipts.Receipts(SO_TC02_data, "SO_TC02", "FullPayment", 15, "INR");
		AR_SalesInvoice_Overview.Inv1_OutstandingAmt_Val(SO_TC02_data);
		AR_SalesInvoice_Overview.Inv2_OutstandingAmt_Val(SO_TC02_data);
		AR_SalesInvoice_Overview.Inv3_OutstandingAmt_Val(SO_TC02_data);
		AR_SalesInvoice_Overview.SI_View_OutstandingAmt_softAssert();
	}

	// @SO_TC02-07
	// Scenario: Validate Billing Acct Balance - SO_TC_Two

	@Given("^Validate Billing Acct Balance - SO_TC_Two$")
	public void validate_Billing_Acct_Balance_SO_TC_Two() {
		Party_Parties.PartySearch("11651");// Workphilia Pvt Ltd
		Party_Parties_BasicConfigs_Profile.Val_BillingAcct_Bal_AfterSO(SO_TC02_data);
	}

	// @SO_TC02-08
	// Scenario: Transactions Validations under Finance Module with Invoice ID-
	// SO_TC02

	@Given("^Validate Accounting transactions with InvoiceID under Finance Module - SO_TC_Two$")
	public void validate_Accounting_transactions_with_InvoiceID_under_Finance_Module_SO_TC_Two() {

	}

	// @SO_TC02-09
	// Scenario: Transactions Validations under Finance Module with Payment ID-
	// SO_TC02

	@Given("^Validate Accounting transactions with PaymentID under Finance Module - SO_TC_Two$")
	public void validate_Accounting_transactions_with_PaymentID_under_Finance_Module_SO_TC_Two() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_PaymentID(SO_TC02_data, 13);
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_PaymentID_softAssert();
	}
}
