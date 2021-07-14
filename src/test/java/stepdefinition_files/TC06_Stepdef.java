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



public class TC06_Stepdef extends Base {

	List<HashMap<String, String>> TC06_data = CrestTestDataReader.get_TC06_Data();

	// @TC06-01
	// Scenario: Create a Exchange Rate - TC06

	@Given("^Configure Exchange Rate - TC_Six$")
	public void configure_Exchange_Rate_TC_Six() {
		Financials_GlobalGLSettings_ForeignExchangeRates.ConfigureXchangeRates(TC06_data, 13, "Column12");

	}

	// @TC06-02
	// Scenario: Create PO with Two level Approval - TC06

	@Given("^Create PO - TC_Six$")
	public void create_PO_TC_Six() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder.PO_Start_Order(TC06_data, "TC06 Test", "Imports", "-Select-");
	}

	@Then("^Add Shipping Details - TC_Six$")
	public void add_Shipping_Details_TC_Six() {
		Procurement_PurchaseOrder.PO_Shipping_Details_SingleFacility(TC06_data);
	}

	@Then("^Add Order Items - TC_Six$")
	public void add_Order_Items_TC_Six() throws InterruptedException {
		Procurement_PurchaseOrder.PO_OrderItems(TC06_data, 4);
		Procurement_PurchaseOrder.Continue_ChargesPage_WithoutCharges();

	}

	@Then("^Review & Create PO - TC_Six$")
	public void review_Create_PO_TC_Six() {
		Procurement_PurchaseOrder.PO_ReviewCreatePO("TC06");
	}

	@Then("^Edit Order with Line Item Discounts - TC_Six$")
	public void edit_Order_with_Line_Item_Discounts_TC_Six() {
		Procurement_PurchaseOrder.Edit_lineItem_Discounts(TC06_data);
	}

	@Then("^Edit Order with Overall Discounts - TC_Six$")
	public void edit_Order_with_Overall_Discounts_TC_Six() {
		Procurement_PurchaseOrder.Edit_Adjustments(TC06_data);
	}

	@Then("^Add Charges - TC_Six$")
	public void add_Charges_TC_Six() throws InterruptedException {
		Procurement_PurchaseOrder_View.Add_Charges(TC06_data);
	}

	@Then("^Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - TC_Six$")
	public void validating_SubTotal_OtherCharges_PopupBtn_Tax_Consolidated_PopupBtn_GrandTotal_ThirdPartyTax_Table_TaxBreakup_Table_TC_Six() {
		Procurement_PurchaseOrder_View.PO_SubTotal_Val(TC06_data, "Column15");
		Procurement_PurchaseOrder_View.OtherCharges_PopupBtn_Val(TC06_data);
		Procurement_PurchaseOrder_View.PO_GrdTotal_Val(TC06_data, "Column17");
		Procurement_PurchaseOrder_View.ThirdPartyCharges_Table_Val(TC06_data);
		Procurement_PurchaseOrder_View.ThirdPartyTax_Table_Val(TC06_data);
		Procurement_PurchaseOrder_View.PO_View_assertions();
	}

	// @TC06-03
	// Scenario: PO Approval - TC06

	@Given("^Approve PO \\(Two level Approval\\) - TC_Six$")
	public void approve_PO_Two_level_Approval_TC_Six() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder_View.PO_WFC_Approvals_Imports(TC06_data);
	}

	// @TC06-04
	// Scenario: Receive Inventory (Multiple Shipments) - TC06

	@Given("^Receive Inventory for Shipment One - TC_Six$")
	public void receive_Inventory_for_Shipment_One_TC_Six() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(TC06_data, "TC06", "Column25", "Column26", "Column27", 15, 0);
	}

	@Then("^Receive Inventory for Shipment Two - TC_Six$")
	public void receive_Inventory_for_Shipment_Two_TC_Six() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(TC06_data, "TC06", "Column37", "Column38", "Column39", 16, 0);
	}

	@Then("^Receive Inventory for Shipment Three - TC_Six$")
	public void receive_Inventory_for_Shipment_Three_TC_Six() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(TC06_data, "TC06", "Column49", "Column50", "Column51", 17, 2);
		Procurement_PurchaseOrder_View.CaptureInvID("TC06");
	}

	// @TC06-05
	// Scenario: Invoice Posting (Multiple Invoice) - TC06

	@Given("^Invoice Posting for Generated Invoices - TC_Six$")
	public void invoice_Posting_for_Generated_Invoices_TC_Six() throws InterruptedException {

		// Invoice1
		AP_PurchaseInvoices.Invoice_Posting(TC06_data, 13);
		AP_PurchaseInvoices_Overview.Inv1_HeaderAmt_Val(TC06_data);
		AP_PurchaseInvoices_Overview.PI_Inv1_SubTotal_Validations(TC06_data, 13, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv1_GrdTotal_Val(TC06_data, 13, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv1_ThirdPartyTax_Table_Val(TC06_data, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv1_ThirdPartyCharges_Table_Val(TC06_data, "Column29");

		// Invoice2
		AP_PurchaseInvoices.Invoice_Posting(TC06_data, 14);
		AP_PurchaseInvoices_Overview.Inv2_HeaderAmt_Val(TC06_data);
		AP_PurchaseInvoices_Overview.PI_Inv2_SubTotal_Validations(TC06_data, 14, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv2_GrdTotal_Val(TC06_data, 14, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv2_ThirdPartyTax_Table_Val(TC06_data, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv2_ThirdPartyCharges_Table_Val(TC06_data, "Column41");

		// Invoice3
		AP_PurchaseInvoices.Invoice_Posting(TC06_data, 15);
		AP_PurchaseInvoices_Overview.Inv3_HeaderAmt_Val(TC06_data);
		AP_PurchaseInvoices_Overview.PI_Inv3_SubTotal_Validations(TC06_data, 15, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv3_GrdTotal_Val(TC06_data, 15, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv3_ThirdPartyTax_Table_Val(TC06_data, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv3_ThirdPartyCharges_Table_Val(TC06_data, "Column53");

	}

	@Then("^Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Six$")
	public void validating_SubTotal_Tax_GrandTotal_for_all_the_Generated_Invoices_TC_Six() {
		AP_PurchaseInvoices_Overview.PI_View_softAssert();
	}

	// @TC06-06
	// Scenario: Payments - TC06

	@Given("^Payments \\(One Invoice with Exchange Rate of X \\) - TC_Six$")
	public void payments_One_Invoice_with_Exchange_Rate_of_X_TC_Six() throws InterruptedException {
		AP_Payments.Payments(TC06_data, "TC06", "ExchangeRate", 15);
		AP_PurchaseInvoices_Overview.Inv1_OutstandingAmt_Val(TC06_data);
	}

	// @TC06-07
	// Scenario: Changing Exchange Rate plus Five percent - TC06

	@Given("^Reconfigure Exchange Rate - TC_Six$")
	public void Reconfigure_Exchange_Rate_TC_Six() {
		Financials_GlobalGLSettings_ForeignExchangeRates.ConfigureXchangeRates(TC06_data, 14, "Column12");
	}

	// @TC06-08
	// Scenario: Payments - TC06

	@Given("^Payments \\(Remaining Invoice with Exchange Rate of X plus Five Percent\\) - TC_Six$")
	public void payments_Remaining_Invoice_with_Exchange_Rate_of_X_plus_Five_Percent_TC_Six() throws InterruptedException {
		AP_Payments.Payments(TC06_data, "TC06", "FullPayment", 16);
		AP_PurchaseInvoices_Overview.Inv2_OutstandingAmt_Val(TC06_data);
		AP_PurchaseInvoices_Overview.Inv3_OutstandingAmt_Val(TC06_data);
		AP_PurchaseInvoices_Overview.PI_View_OutstandingAmt_softAssert();
	}

	// @TC06-09
	// Scenario: Transactions Validations under Finance Module with Shipment ID-
	// TC06

	@Given("^Validate Accounting transactions with ShipmentID under Finance Module - TC_Six$")
	public void validate_Accounting_transactions_with_ShipmentID_under_Finance_Module_TC_Six() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_ShipID_1(TC06_data, 13, "Column25", "Column36");
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_ShipID_2(TC06_data, 14, "Column37", "Column48");
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_ShipID_3(TC06_data, 15, "Column49", "Column60");
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_ShipID_softAssert();
	}

	// @TC06-10
	// Scenario: Transactions Validations under Finance Module with Invoice ID-
	// TC06

	@Given("^Validate Accounting transactions with InvoiceID under Finance Module - TC_Six$")
	public void validate_Accounting_transactions_with_InvoiceID_under_Finance_Module_TC_Six() {

	}

	// @TC06-11
	// Scenario: Transactions Validations under Finance Module with Payment ID-
	// TC06

	@Given("^Validate Accounting transactions with PaymentID under Finance Module - TC_Six$")
	public void validate_Accounting_transactions_with_PaymentID_1_under_Finance_Module_TC_Six() {

		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_PaymentID_1_Imp(TC06_data, 13, 13);
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_PaymentID_2_Imp(TC06_data, 16, 16);
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_PaymentID_softAssert();
	}

}
