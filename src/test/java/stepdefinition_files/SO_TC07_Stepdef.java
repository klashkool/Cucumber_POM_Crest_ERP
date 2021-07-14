package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.AccountReceivable_Modules.AR_Receipts;
import com.AccountReceivable_Modules.AR_SalesInvoice;
import com.AccountReceivable_Modules.AR_SalesInvoice_Overview;
import com.Facilities_Modules.Facilities_Shipments;
import com.Financials_Modules.Financials_AcctTransaction;
import com.Financials_Modules.Financials_AcctTransaction_GLTransactionViewEntry;
import com.Financials_Modules.Financials_GlobalGLSettings_ForeignExchangeRates;
import com.Sales_Modules.Sales_SalesOrder;
import com.Sales_Modules.Sales_SalesOrder_View;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class SO_TC07_Stepdef extends Base {

	List<HashMap<String, String>> SO_TC07_data = CrestTestDataReader.get_SO_TC07_Data();

	// @SO_TC07-01
	// Scenario: Create an Exchange Rate - SO_TC07

	@Given("^Configure Exchange Rate - SO_TC_Seven$")
	public void configure_Exchange_Rate_SO_TC_Seven() {
		Financials_GlobalGLSettings_ForeignExchangeRates.ConfigureXchangeRates(SO_TC07_data, 13, "Column12");
	}

	// @SO_TC07-02
	// Scenario: Create SO with Offline Payment - SO_TC07

	@Given("^Create SO - SO_TC_Seven$")
	public void create_SO_SO_TC_Seven() {
		Sales_SalesOrder.SO_StartOrder(SO_TC07_data, "Export SO with Offline Payments", "-Select-");
		Sales_SalesOrder.OfflinePayment_Chkbox();
	}

	@Then("^Add Shipping Details - SO_TC_Seven$")
	public void add_Shipping_Details_SO_TC_Seven() {
		Sales_SalesOrder.SO_Shipping_Details_SingleFacility(SO_TC07_data);
	}

	@Then("^Add Order Items - SO_TC_Seven$")
	public void add_Order_Items_SO_TC_Seven() throws InterruptedException {
		Sales_SalesOrder.SO_OrderItems(SO_TC07_data, 4);
	}

	@Then("^Review & Create SO - SO_TC_Seven$")
	public void review_Create_SO_SO_TC_Seven() throws InterruptedException {
		Sales_SalesOrder.SO_ReviewCreateSO_WithoutAlert("SO_TC07");
	}

	@Then("^Edit Order with Line Item Discounts - SO_TC_Seven$")
	public void edit_Order_with_Line_Item_Discounts_SO_TC_Seven() {
		Sales_SalesOrder.Edit_lineItem_Discounts_WithoutAlert(SO_TC07_data);
	}

	@Then("^Edit Order with Overall Discounts - SO_TC_Seven$")
	public void edit_Order_with_Overall_Discounts_SO_TC_Seven() {
		Sales_SalesOrder.Edit_Adjustments(SO_TC07_data);
	}

	@Then("^Add Charges - SO_TC_Seven$")
	public void add_Charges_SO_TC_Seven() throws InterruptedException {
		Sales_SalesOrder_View.Add_Charges(SO_TC07_data);
	}

	@Then("^Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - SO_TC_Seven$")
	public void validating_SubTotal_OtherCharges_PopupBtn_Tax_Consolidated_PopupBtn_GrandTotal_ThirdPartyTax_Table_TaxBreakup_Table_SO_TC_Seven() {
		Sales_SalesOrder_View.SO_SubTotal_Val(SO_TC07_data, "Column15");
		Sales_SalesOrder_View.SO_Tax_Val(SO_TC07_data, "Column16");
		Sales_SalesOrder_View.Tax_Consolidated_PopupBtn_Val(SO_TC07_data);
		Sales_SalesOrder_View.SO_GrdTotal_Val(SO_TC07_data, "Column17");
		Sales_SalesOrder_View.ThirdPartyCharges_Table_Val(SO_TC07_data);
		Sales_SalesOrder_View.SO_View_assertions();
	}

	// @SO_TC07-03
	// Scenario: SO Approval - SO_TC07

	@Given("^Approve SO - SO_TC_Seven$")
	public void approve_SO_SO_TC_Seven() {
		Sales_SalesOrder_View.Approve_SO(SO_TC07_data);
	}

	// @SO_TC07-04
	// Scenario: Create Shipment (Multiple Shipment) - SO_TC07

	@Given("^Create Shipment One - SO_TC_Seven$")
	public void create_Shipment_One_SO_TC_Seven() throws InterruptedException {
		Facilities_Shipments.SO_CreateShipment(SO_TC07_data, "SO_TC07", "Column25", "Column26", "Column27", 15, 0);
	}

	@Then("^Create Shipment Two - SO_TC_Seven$")
	public void create_Shipment_Two_SO_TC_Seven() throws InterruptedException {
		Facilities_Shipments.SO_CreateShipment(SO_TC07_data, "SO_TC07", "Column37", "Column38", "Column39", 16, 0);
	}

	@Then("^Create Shipment Three - SO_TC_Seven$")
	public void create_Shipment_Three_SO_TC_Seven() throws InterruptedException {
		Facilities_Shipments.SO_CreateShipment(SO_TC07_data, "SO_TC07", "Column49", "Column50", "Column51", 17, 2);
		Sales_SalesOrder_View.SO_CaptureInvID("SO_TC07");
	}

	// @SO_TC07-05
	// Scenario: Invoice Posting (Multiple Invoice) - SO_TC07

	@Given("^Invoice Posting for Generated Invoices - SO_TC_Seven$")
	public void invoice_Posting_for_Generated_Invoices_SO_TC_Seven() {

		// Invoice 1
		AR_SalesInvoice.SO_InvoicePosting(SO_TC07_data, 13);
		AR_SalesInvoice_Overview.Inv1_HeaderAmt_Val(SO_TC07_data);
		AR_SalesInvoice_Overview.SI_Inv1_SubTotal_Validations(SO_TC07_data, 13, "Column7");
		AR_SalesInvoice_Overview.SI_Inv1_Tax_Val(SO_TC07_data, 13, "Column8");
		AR_SalesInvoice_Overview.SI_Inv1_GrdTotal_Val(SO_TC07_data, 13, "Column9");
		AR_SalesInvoice_Overview.SI_Inv1_ThirdPartyCharges_Table_Val(SO_TC07_data, "Column29");

		// Invoice 2
		AR_SalesInvoice.SO_InvoicePosting(SO_TC07_data, 14);
		AR_SalesInvoice_Overview.Inv2_HeaderAmt_Val(SO_TC07_data);
		AR_SalesInvoice_Overview.SI_Inv2_SubTotal_Validations(SO_TC07_data, 14, "Column7");
		AR_SalesInvoice_Overview.SI_Inv2_Tax_Val(SO_TC07_data, 14, "Column8");
		AR_SalesInvoice_Overview.SI_Inv2_GrdTotal_Val(SO_TC07_data, 14, "Column9");
		AR_SalesInvoice_Overview.SI_Inv2_ThirdPartyCharges_Table_Val(SO_TC07_data, "Column41");

		// Invoice 3
		AR_SalesInvoice.SO_InvoicePosting(SO_TC07_data, 15);
		AR_SalesInvoice_Overview.Inv3_HeaderAmt_Val(SO_TC07_data);
		AR_SalesInvoice_Overview.SI_Inv3_SubTotal_Validations(SO_TC07_data, 15, "Column7");
		AR_SalesInvoice_Overview.SI_Inv3_Tax_Val(SO_TC07_data, 15, "Column8");
		AR_SalesInvoice_Overview.SI_Inv3_GrdTotal_Val(SO_TC07_data, 15, "Column9");
		AR_SalesInvoice_Overview.SI_Inv3_ThirdPartyCharges_Table_Val(SO_TC07_data, "Column53");
	}

	@Then("^Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - SO_TC_Seven$")
	public void validating_SubTotal_Tax_GrandTotal_for_all_the_Generated_Invoices_SO_TC_Seven() {
		AR_SalesInvoice_Overview.SI_View_softAssert();
	}

	// @SO_TC07-06
	// Scenario: Receipts - SO_TC07

	@Given("^Receipts \\(One Invoice with Exchange Rate of X\\) - SO_TC_Seven$")
	public void receipts_One_Invoice_with_Exchange_Rate_of_X_SO_TC_Seven() throws InterruptedException {
		AR_Receipts.Receipts(SO_TC07_data, "SO_TC07", "ExchangeRate", 15, "USD");
		AR_SalesInvoice_Overview.Inv1_OutstandingAmt_Val(SO_TC07_data);
	}

	// @SO_TC07-07
	// Scenario: Changing Exchange Rate plus Five percent - SO_TC07

	@Given("^Reconfigure Exchange Rate - SO_TC_Seven$")
	public void reconfigure_Exchange_Rate_SO_TC_Seven() {
		Financials_GlobalGLSettings_ForeignExchangeRates.ConfigureXchangeRates(SO_TC07_data, 14, "Column12");
	}

	// @SO_TC07-08
	// Scenario: Receipts - SO_TC07

	@Given("^Receipts \\(Remaining Invoice with Exchange Rate of X plus Five Percent\\) - SO_TC_Seven$")
	public void receipts_Remaining_Invoice_with_Exchange_Rate_of_X_plus_Five_Percent_SO_TC_Seven() throws InterruptedException {
		AR_Receipts.Receipts(SO_TC07_data, "SO_TC07", "FullPayment", 16, "USD");
		AR_SalesInvoice_Overview.Inv2_OutstandingAmt_Val(SO_TC07_data);
		AR_SalesInvoice_Overview.Inv3_OutstandingAmt_Val(SO_TC07_data);
		AR_SalesInvoice_Overview.SI_View_OutstandingAmt_softAssert();
	}

	// @SO_TC07-09
	// Scenario: Transactions Validations under Finance Module with Invoice ID-
	// SO_TC07

	@Given("^Validate Accounting transactions with InvoiceID under Finance Module - SO_TC_Seven$")
	public void validate_Accounting_transactions_with_InvoiceID_under_Finance_Module_SO_TC_Seven() {

	}

	// @SO_TC07-10
	// Scenario: Transactions Validations under Finance Module with Payment ID-
	// SO_TC07

	@Given("^Validate Accounting transactions with PaymentID under Finance Module - SO_TC_Seven$")
	public void validate_Accounting_transactions_with_PaymentID_under_Finance_Module_SO_TC_Seven() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_PaymentID_1_Imp(SO_TC07_data, 13, 13);
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_PaymentID_2_Imp(SO_TC07_data, 16, 16);
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_PaymentID_softAssert();
	}

}
