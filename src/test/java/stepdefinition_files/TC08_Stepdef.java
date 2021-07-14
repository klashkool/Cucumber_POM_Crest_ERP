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
import com.Procurement_Modules.Procurement_Quotes;
import com.Procurement_Modules.Procurement_Quotes_UpdateQuoteStatus;
import com.Procurement_Modules.Procurement_RFQ;
import com.Procurement_Modules.Procurement_Requirements_Requirements;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;
import com.Utils.WebdriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;



public class TC08_Stepdef extends Base {

	List<HashMap<String, String>> TC08_data = CrestTestDataReader.get_TC08_Data();

	// @TC08-01
	// Scenario: Creating Vendor Pre Payment(50% of Invoice Value) - TC08

	@Given("^Create Vendor Prepayment - TC_Eight$")
	public void create_Vendor_Prepayment_TC_Eight() {
		AP_Payments.VendorPrePayments(TC08_data, "TC08", "INR", 15);
	}

	// @TC08-02
	// Scenario: Create PO from Requirements with Multi level Approval for Non
	// Stock Items - TC08

	@Given("^Create PO Requirements - TC_Eight$")
	public void create_PO_Requirements_TC_Eight() throws InterruptedException {
		Crest_Login.PurchaseExc_login();
		Procurement_Requirements_Requirements.PO_Req(TC08_data, "TC08", "PO Supplies to Exp");
		Procurement_Requirements_Requirements.Req_Approve();
		Procurement_Requirements_Requirements.Create_RFQ();
	}

	@Then("^Create PO RFQ - TC_Eight$")
	public void create_PO_RFQ_TC_Eight() throws InterruptedException {
		Procurement_RFQ.PO_RFQ(TC08_data, "TC08", "PO Supplies to Exp");
	}

	@Then("^Create PO Quote - TC_Eight$")
	public void create_PO_Quote_TC_Eight() {
		Procurement_Quotes.PO_Quotes(TC08_data);
		Procurement_Quotes_UpdateQuoteStatus.Select_OrderType();
		WebdriverWait.findElement("link", "Continue").click();
		WebdriverWait.findElement("link", "Continue").click();
		WebdriverWait.findElement("link", "Continue").click();
		WebdriverWait.findElement("link", "Continue").click();
	}

	@Then("^Review & Create PO - TC_Eight$")
	public void review_Create_PO_TC_Eight() {
		Procurement_PurchaseOrder.PO_ReviewCreatePO("TC08");
	}

	@Then("^Edit Order with Line Item Discounts - TC_Eight$")
	public void edit_Order_with_Line_Item_Discounts_TC_Eight() {
		Procurement_PurchaseOrder.Edit_lineItem_Discounts(TC08_data);
	}

	@Then("^Edit Order with Overall Discounts - TC_Eight$")
	public void edit_Order_with_Overall_Discounts_TC_Eight() {
		Procurement_PurchaseOrder.Edit_Adjustments(TC08_data);
	}

	@Then("^Add Charges - TC_Eight$")
	public void add_Charges_TC_Eight() throws InterruptedException {
		Procurement_PurchaseOrder_View.Add_Charges(TC08_data);
	}

	@Then("^Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - TC_Eight$")
	public void validating_SubTotal_OtherCharges_PopupBtn_Tax_Consolidated_PopupBtn_GrandTotal_ThirdPartyTax_Table_TaxBreakup_Table_TC_Eight() {
		Procurement_PurchaseOrder_View.PO_SubTotal_Val(TC08_data, "Column15");
		Procurement_PurchaseOrder_View.PO_Tax_Val(TC08_data, "Column16");
		Procurement_PurchaseOrder_View.OtherCharges_PopupBtn_Val(TC08_data);
		Procurement_PurchaseOrder_View.Tax_Consolidated_PopupBtn_Val(TC08_data);
		Procurement_PurchaseOrder_View.PO_GrdTotal_Val(TC08_data, "Column17");
		Procurement_PurchaseOrder_View.ThirdPartyCharges_Table_Val(TC08_data);
		Procurement_PurchaseOrder_View.TaxBreakup_Table_Val(TC08_data);
		Procurement_PurchaseOrder_View.PO_View_assertions();
	}

	// @TC08-03
	// Scenario: PO Approval- TC08

	@Given("^Approve PO \\(Two level Approval\\) - TC_Eight$")
	public void approve_PO_Two_level_Approval_TC_Eight() {
		Crest_Login.PurchaseExc_login();
		Procurement_PurchaseOrder_View.PO_WFC_Approvals(TC08_data);
	}

	// @TC08-04
	// Scenario: Receive Adhoc (Multiple Shipments) - TC08

	@Given("^Receive Adhoc for Shipment One - TC_Eight$")
	public void receive_Adhoc_for_Shipment_One_TC_Eight() throws InterruptedException {
		Facilities_ReceiveAdHoc.PO_Adhoc_Recv_Shpmt(TC08_data, "Column27");
	}

	@Then("^Receive Adhoc for Shipment Two - TC_Eight$")
	public void receive_Adhoc_for_Shipment_Two_TC_Eight() throws InterruptedException {
		Facilities_ReceiveAdHoc.PO_Adhoc_Recv_Shpmt(TC08_data, "Column39");
	}

	@Then("^Receive Adhoc for Shipment Three - TC_Eight$")
	public void receive_Adhoc_for_Shipment_Three_TC_Eight() throws InterruptedException {
		Facilities_ReceiveAdHoc.PO_Adhoc_Recv_Shpmt(TC08_data, "Column51");
		Procurement_PurchaseOrder_View.CaptureInvID("TC08");
	}

	// @TC08-05
	// Scenario: Invoice Posting (Multiple Invoice) - TC08

	@Given("^Invoice Posting for Generated Invoices - TC_Eight$")
	public void invoice_Posting_for_Generated_Invoices_TC_Eight() throws Throwable {

		// Invoice1
		AP_PurchaseInvoices.Invoice_Adj_Posting(TC08_data, 13);
		AP_PurchaseInvoices_Overview.Inv1_HeaderAmt_Val(TC08_data);
		AP_PurchaseInvoices_Overview.PI_Inv1_SubTotal_Validations(TC08_data, 13, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv1_Tax_Val(TC08_data, 13, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv1_GrdTotal_Val(TC08_data, 13, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv1_ThirdPartyCharges_Table_Val(TC08_data, "Column29");
		AP_PurchaseInvoices_Overview.PI_Inv1_TaxBreakup_Table_Val(TC08_data);

		// Invoice2
		AP_PurchaseInvoices.Invoice_Posting(TC08_data, 14);
		AP_PurchaseInvoices_Overview.Inv2_HeaderAmt_Val(TC08_data);
		AP_PurchaseInvoices_Overview.PI_Inv2_SubTotal_Validations(TC08_data, 14, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv2_Tax_Val(TC08_data, 14, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv2_GrdTotal_Val(TC08_data, 14, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv2_ThirdPartyCharges_Table_Val(TC08_data, "Column41");
		AP_PurchaseInvoices_Overview.PI_Inv2_TaxBreakup_Table_Val(TC08_data);

		// Invoice3
		AP_PurchaseInvoices.Invoice_Posting(TC08_data, 15);
		AP_PurchaseInvoices_Overview.Inv3_HeaderAmt_Val(TC08_data);
		AP_PurchaseInvoices_Overview.PI_Inv3_SubTotal_Validations(TC08_data, 15, "Column7");
		AP_PurchaseInvoices_Overview.PI_Inv3_Tax_Val(TC08_data, 15, "Column8");
		AP_PurchaseInvoices_Overview.PI_Inv3_GrdTotal_Val(TC08_data, 15, "Column9");
		AP_PurchaseInvoices_Overview.PI_Inv3_ThirdPartyCharges_Table_Val(TC08_data, "Column53");
		AP_PurchaseInvoices_Overview.PI_Inv3_TaxBreakup_Table_Val(TC08_data);

	}

	@Then("^Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Eight$")
	public void validating_SubTotal_Tax_GrandTotal_for_all_the_Generated_Invoices_TC_Eight() {
		AP_PurchaseInvoices_Overview.PI_View_softAssert();
	}

	// @TC08-06
	// Scenario: Payments - TC08

	@Given("^Payments \\(Advance adjusted against the first invoice \\(part\\) and the balance need to be booked as one payment\\. Receive full payment against the second invoice\\) - TC_Eight$")
	public void payments_Advance_adjusted_against_the_first_invoice_part_and_the_balance_need_to_be_booked_as_one_payment_Receive_full_payment_against_the_second_invoice_TC_Eight()
					throws InterruptedException {
		AP_Payments.Confirm_VendorPrePayment(TC08_data);
		AP_Payments.Payments(TC08_data, "TC08", "FullPayment", 16);
		AP_PurchaseInvoices_Overview.Inv1_OutstandingAmt_Val(TC08_data);
		AP_PurchaseInvoices_Overview.Inv2_OutstandingAmt_Val(TC08_data);
		AP_PurchaseInvoices_Overview.Inv3_OutstandingAmt_Val(TC08_data);
		AP_PurchaseInvoices_Overview.PI_View_OutstandingAmt_softAssert();
	}

	// @TC08-07
	// Scenario: Transactions Validations under Finance Module with Invoice ID-
	// TC08

	@Given("^Validate Accounting transactions with InvoiceID under Finance Module - TC_Eight$")
	public void validate_Accounting_transactions_with_InvoiceID_under_Finance_Module_TC_Eight() {

	}

	// @TC08-08
	// Scenario: Transactions Validations under Finance Module with Payment ID-
	// TC08

	@Given("^Validate Accounting transactions with PaymentID under Finance Module - TC_Eight$")
	public void validate_Accounting_transactions_with_PaymentID_under_Finance_Module_TC_Eight() {
		Financials_AcctTransaction.Acct_Trans_Val();
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_PaymentID_1_Imp(TC08_data, 13, 13);
		Financials_AcctTransaction_GLTransactionViewEntry.Acct_Trans_Val_PaymentID_2_Imp(TC08_data, 16, 16);
		Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_PaymentID_softAssert();
	}

}
