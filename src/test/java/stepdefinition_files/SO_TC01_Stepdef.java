package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.AccountReceivable_Modules.AR_Receipts;
import com.AccountReceivable_Modules.AR_SalesInvoice;
import com.AccountReceivable_Modules.AR_SalesInvoice_Overview;
import com.Facilities_Modules.Facilities_Shipments;
import com.Financials_Modules.Financials_AcctTransaction;
import com.Financials_Modules.Financials_AcctTransaction_GLTransactionViewEntry;
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

public class SO_TC01_Stepdef extends Base {

	List<HashMap<String, String>> SO_TC01_data = CrestTestDataReader.get_SO_TC01_Data();

	// @SO_TC01-01
	// Scenario: Create Quotes - SO_TC01

	@Given("^Create New Sales Quote - SO_TC_One$")
	public void create_New_Sales_Quote_SO_TC_One() throws InterruptedException {
		Sales_Quotes.Create_SO_Quotes(SO_TC01_data, "SO_TC01");
	}

	@Then("^Add Products with Discounts - SO_TC_One$")
	public void add_Products_with_Discounts_SO_TC_One() throws InterruptedException {
		Sales_Quotes_QuoteItems.Add_Products(SO_TC01_data);
		Sales_Quotes_Edit.SO_Accept_Quote();
		Sales_Quotes_View.Quote_Header_Val();
	}

	@Then("^Validate Quotes SubTotal & GrandTotal - SO_TC_One$")
	public void validate_Quotes_SubTotal_GrandTotal_SO_TC_One() {
		Sales_Quotes_View.SO_Quotes_GrndTotal_Val(SO_TC01_data, "Column15");
		Sales_Quotes_View.SO_View_assertions();
	}

	@Then("^Convert Sales Quotes into Sales Order - SO_TC_One$")
	public void convert_Sales_Quotes_into_Sales_Order_SO_TC_One() {
		Sales_Quotes_View.SO_Quotes_CreateOrderLink();
		WebdriverWait.findElement("link", "Continue").click();
		WebdriverWait.findElement("link", "Continue").click();
		WebdriverWait.findElement("link", "Continue").click();
	}

	@Then("^Review and Create SO - SO_TC_One$")
	public void review_and_Create_SO_SO_TC_One() throws InterruptedException {
		Sales_SalesOrder.SO_ReviewCreateSO("SO_TC01");
	}

	@Then("^Validating SubTotal/Tax/GrandTotal/TaxConsolidated Btn/TaxBreakup Table - SO_TC_One$")
	public void validating_SubTotal_Tax_GrandTotal_TaxConsolidated_Btn_TaxBreakup_Table_SO_TC_One() {
		Sales_SalesOrder_View.SO_SubTotal_Val(SO_TC01_data, "Column15");
		Sales_SalesOrder_View.SO_Tax_Val(SO_TC01_data, "Column16");
		Sales_SalesOrder_View.Tax_Consolidated_PopupBtn_Val(SO_TC01_data);
		Sales_SalesOrder_View.SO_GrdTotal_Val(SO_TC01_data, "Column17");
		Sales_SalesOrder_View.TaxBreakup_Table_Val(SO_TC01_data);
		Sales_SalesOrder_View.SO_View_assertions();
	}

	// @SO_TC01-02
	// Scenario: SO Approval - SO_TC01

	@Given("^Approve SO - SO_TC_One$")
	public void approve_SO_SO_TC_One() {
		Sales_SalesOrder_View.Approve_SO(SO_TC01_data);
	}

	// @SO_TC01-03
	// Scenario: Create Shipment - SO_TC01

	@Given("^Create Shipment \\(Single Shipment\\) - SO_TC_One$")
	public void create_Shipment_Single_Shipment_SO_TC_One() throws InterruptedException {
		Facilities_Shipments.SO_CreateShipment(SO_TC01_data, "SO_TC01", "Column25", "Column26", "Column27", 15, 0);
		Sales_SalesOrder_View.SO_CaptureInvID("SO_TC01");
	}

	// @SO_TC01-04
	// Scenario: Invoice Posting - SO_TC01

	@Given("^Invoice Posting \\(Single Invoice\\) - SO_TC_One$")
	public void invoice_Posting_Single_Invoice_SO_TC_One() {

		AR_SalesInvoice.SO_InvoicePosting(SO_TC01_data, 13);
		AR_SalesInvoice_Overview.Inv1_HeaderAmt_Val(SO_TC01_data);
		AR_SalesInvoice_Overview.SI_Inv1_SubTotal_Validations(SO_TC01_data, 13, "Column7");
		AR_SalesInvoice_Overview.SI_Inv1_Tax_Val(SO_TC01_data, 13, "Column8");
		AR_SalesInvoice_Overview.SI_Inv1_GrdTotal_Val(SO_TC01_data, 13, "Column9");
		AR_SalesInvoice_Overview.SI_Inv1_TaxBreakup_Table_Val(SO_TC01_data);

	}

	@Then("^Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - SO_TC_One$")
	public void validating_SubTotal_Tax_GrandTotal_for_all_the_Generated_Invoices_SO_TC_One() {
		AR_SalesInvoice_Overview.SI_View_softAssert();
	}

	// @SO_TC01-05
	// Scenario: Payments - SO_TC01

	@Given("^Receipts \\(Single Receipt\\) - SO_TC_One$")
	public void receipts_Single_Receipt_SO_TC_One() throws InterruptedException {
		AR_Receipts.Receipts(SO_TC01_data, "SO_TC01", "FullPayment", 15, "INR");
		AR_SalesInvoice_Overview.Inv1_OutstandingAmt_Val(SO_TC01_data);
		AR_SalesInvoice_Overview.SI_View_OutstandingAmt_softAssert();
	}

	// @SO_TC01-06
	// Scenario: Transactions Validations under Finance Module with Payment ID-
	// SO_TC01

	@Given("^Validate Accounting transactions with PaymentID under Finance Module - SO_TC_One$")
	public void validate_Accounting_transactions_with_PaymentID_under_Finance_Module_SO_TC_One() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_PaymentID(SO_TC01_data, 13);
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_PaymentID_softAssert();
	}

}
