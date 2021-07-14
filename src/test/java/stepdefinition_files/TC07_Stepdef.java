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
import com.Procurement_Modules.Procurement_Quotes;
import com.Procurement_Modules.Procurement_RFQ;
import com.Procurement_Modules.Procurement_Requirements_Requirements;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;
import com.Utils.WebdriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;



public class TC07_Stepdef extends Base {

	List<HashMap<String, String>> TC07_data = CrestTestDataReader.get_TC07_Data();

	@Given("^Create PO Requirements - TC_Seven$")
	public void create_PO_Requirements_TC_Seven() throws InterruptedException {
		Crest_Login.PurchaseExc_login();
		Procurement_Requirements_Requirements.PO_Req(TC07_data, "TC07", "Inv");
		Procurement_Requirements_Requirements.Req_Approve();
		Procurement_Requirements_Requirements.Create_RFQ();
	}

	@Then("^Create PO RFQ - TC_Seven$")
	public void create_PO_RFQ_TC_Seven() throws InterruptedException {
		Procurement_RFQ.PO_RFQ(TC07_data, "TC07", "Inv");
	}

	@Then("^Create PO Quote - TC_Seven$")
	public void create_PO_Quote_TC_Seven() {
		Procurement_Quotes.PO_Quotes(TC07_data);

		WebdriverWait.findElement("link", "Continue").click();
		WebdriverWait.findElement("link", "Continue").click();
		WebdriverWait.findElement("link", "Continue").click();
	}

	@Then("^Add Charges Before Creating PO - TC_Seven$")
	public void add_Charges_Before_Creating_PO_TC_Seven() {
		Procurement_PurchaseOrder.Charges(TC07_data);
	}

	@Then("^Review & Create PO - TC_Seven$")
	public void review_Create_PO_TC_Seven() {
		Procurement_PurchaseOrder.PO_ReviewCreatePO("TC07");
	}

	@Then("^Edit Order with Line Item Discounts - TC_Seven$")
	public void edit_Order_with_Line_Item_Discounts_TC_Seven() {
		Procurement_PurchaseOrder.Edit_lineItem_Discounts(TC07_data);
	}

	@Then("^Edit Order with Overall Discounts - TC_Seven$")
	public void edit_Order_with_Overall_Discounts_TC_Seven() {
		Procurement_PurchaseOrder.Edit_Adjustments(TC07_data);
	}

	@Then("^Add Charges After PO - TC_Seven$")
	public void add_Charges_After_PO_TC_Seven() {
		Procurement_PurchaseOrder_View.AddThirdparty_Charges(TC07_data);
	}

	@Then("^Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - TC_Seven$")
	public void validating_SubTotal_OtherCharges_PopupBtn_Tax_Consolidated_PopupBtn_GrandTotal_ThirdPartyTax_Table_TaxBreakup_Table_TC_Seven() {
		Procurement_PurchaseOrder_View.PO_SubTotal_Val(TC07_data, "Column15");
		Procurement_PurchaseOrder_View.PO_Tax_Val(TC07_data, "Column16");
		Procurement_PurchaseOrder_View.OtherCharges_PopupBtn_Val(TC07_data);
		Procurement_PurchaseOrder_View.Tax_Consolidated_PopupBtn_Val(TC07_data);
		Procurement_PurchaseOrder_View.PO_GrdTotal_Val(TC07_data, "Column17");
		Procurement_PurchaseOrder_View.ThirdPartyCharges_Table_Val(TC07_data);
		Procurement_PurchaseOrder_View.TaxBreakup_Table_Val(TC07_data);
		Procurement_PurchaseOrder_View.PO_View_assertions();
	}

	// @TC07-02
	// Scenario: PO Approval- TC07

	@Given("^Approve PO \\(Two level Approval\\) - TC_Seven$")
	public void approve_PO_Two_level_Approval_TC_Seven() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder_View.PO_WFC_Approvals(TC07_data);
	}

	// @TC07-03
	// Scenario: Receive Inventory (Multiple Shipments) - TC07

	@Given("^Receive Inventory for Shipment One - TC_Seven$")
	public void receive_Inventory_for_Shipment_One_TC_Seven() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(TC07_data, "TC07", "Column25", "Column26", "Column27", 15, 0);
	}

	@Then("^Receive Inventory for Shipment Two - TC_Seven$")
	public void receive_Inventory_for_Shipment_Two_TC_Seven() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(TC07_data, "TC07", "Column37", "Column38", "Column39", 16, 0);
	}

	@Then("^Receive Inventory for Shipment Three - TC_Seven$")
	public void receive_Inventory_for_Shipment_Three_TC_Seven() throws InterruptedException {
		Facilities_Shipments.PO_ReceiveInventory(TC07_data, "TC07", "Column49", "Column50", "Column51", 17, 2);
		Procurement_PurchaseOrder_View.CaptureInvID("TC07");
	}

	// @TC07-04
	// Scenario: Invoice Posting (Multiple Invoice) - TC07

	@Given("^Invoice Posting for Generated Invoices - TC_Seven$")
	public void invoice_Posting_for_Generated_Invoices_TC_Seven() throws Throwable {

		// Invoice1
		AP_PurchaseInvoices.Invoice_Posting(TC07_data, 13);
		AP_PurchaseInvoices_Overview.Inv1_HeaderAmt_Val(TC07_data);
		AP_PurchaseInvoices_Overview.PI_Inv1_SubTotal_Validations(TC07_data, 13, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv1_Tax_Val(TC07_data, 13, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv1_GrdTotal_Val(TC07_data, 13, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv1_ThirdPartyCharges_Table_Val(TC07_data, "Column29");
		AP_PurchaseInvoices_Overview.PI_Inv1_TaxBreakup_Table_Val(TC07_data);

		// Invoice2
		AP_PurchaseInvoices.Invoice_Posting(TC07_data, 14);
		AP_PurchaseInvoices_Overview.Inv2_HeaderAmt_Val(TC07_data);
		AP_PurchaseInvoices_Overview.PI_Inv2_SubTotal_Validations(TC07_data, 14, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv2_Tax_Val(TC07_data, 14, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv2_GrdTotal_Val(TC07_data, 14, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv2_ThirdPartyCharges_Table_Val(TC07_data, "Column41");
		AP_PurchaseInvoices_Overview.PI_Inv2_TaxBreakup_Table_Val(TC07_data);

		// Invoice3
		AP_PurchaseInvoices.Invoice_Posting(TC07_data, 15);
		AP_PurchaseInvoices_Overview.Inv3_HeaderAmt_Val(TC07_data);
		AP_PurchaseInvoices_Overview.PI_Inv3_SubTotal_Validations(TC07_data, 15, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv3_Tax_Val(TC07_data, 15, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv3_GrdTotal_Val(TC07_data, 15, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv3_ThirdPartyCharges_Table_Val(TC07_data, "Column53");
		AP_PurchaseInvoices_Overview.PI_Inv3_TaxBreakup_Table_Val(TC07_data);

	}

	@Then("^Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Seven$")
	public void validating_SubTotal_Tax_GrandTotal_for_all_the_Generated_Invoices_TC_Seven() {
		AP_PurchaseInvoices_Overview.PI_View_softAssert();
	}
	// @TC07-05
	// Scenario: Payments - TC07

	@Given("^Payments \\(Single Payment\\) - TC_Seven$")
	public void payments_Single_Payment_TC_Seven() throws InterruptedException {
		AP_Payments.Payments(TC07_data, "TC07", "FullPayment", 15);
		AP_PurchaseInvoices_Overview.Inv1_OutstandingAmt_Val(TC07_data);
		AP_PurchaseInvoices_Overview.Inv2_OutstandingAmt_Val(TC07_data);
		AP_PurchaseInvoices_Overview.Inv3_OutstandingAmt_Val(TC07_data);
		AP_PurchaseInvoices_Overview.PI_View_OutstandingAmt_softAssert();
	}

	// @TC07-06
	// Scenario: Validate all the Inventories under Finance Module - TC07

	@Given("^Validate Accounting transactions with ShipmentID under Finance Module - TC_Seven$")
	public void validate_Accounting_transactions_with_ShipmentID_under_Finance_Module_TC_Seven() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_ShipID_1(TC07_data, 13, "Column25", "Column36");
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_ShipID_2(TC07_data, 14, "Column37", "Column48");
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_ShipID_3(TC07_data, 15, "Column49", "Column60");
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_ShipID_softAssert();

	}

	// @TC07-07
	// Scenario: Transactions Validations under Finance Module with Invoice ID-
	// TC07

	@Given("^Validate Accounting transactions with InvoiceID under Finance Module - TC_Seven$")
	public void validate_Accounting_transactions_with_InvoiceID_under_Finance_Module_TC_Seven() {

	}

	// @TC07-08
	// Scenario: Transactions Validations under Finance Module with Payment ID-
	// TC07

	@Given("^Validate Accounting transactions with PaymentID under Finance Module - TC_Seven$")
	public void validate_Accounting_transactions_with_PaymentID_under_Finance_Module_TC_Seven() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_PaymentID(TC07_data, 13);
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_PaymentID_softAssert();

	}

}
