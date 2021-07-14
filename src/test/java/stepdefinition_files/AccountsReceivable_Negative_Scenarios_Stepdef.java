package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.AccountReceivable_Modules.AR_ARPaymentGroups;
import com.AccountReceivable_Modules.AR_CreditMemo;
import com.AccountReceivable_Modules.AR_CustomerAdjustment;
import com.AccountReceivable_Modules.AR_Main;
import com.AccountReceivable_Modules.AR_Receipts;
import com.AccountReceivable_Modules.AR_Receipts_Applications;
import com.AccountReceivable_Modules.AR_Receipts_Header;
import com.AccountReceivable_Modules.AR_Receipts_Overview;
import com.AccountReceivable_Modules.AR_SalesInvoice;
import com.AccountReceivable_Modules.AR_SalesInvoice_Applications;
import com.AccountReceivable_Modules.AR_SalesInvoice_Content;
import com.AccountReceivable_Modules.AR_SalesInvoice_Header;
import com.AccountReceivable_Modules.AR_SalesInvoice_Items;
import com.AccountReceivable_Modules.AR_SalesInvoice_Overview;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AccountsReceivable_Negative_Scenarios_Stepdef {

	List<HashMap<String, String>> Sales_Validation_Msg_Data = CrestTestDataReader.get_Sales_Validation_Msg_Data();
	List<HashMap<String, String>> Sales_PositveData_For_NegativeFlow = CrestTestDataReader.get_Sales_PositveData_For_NegativeFlow();
	List<HashMap<String, String>> Sales_PositveData_SO_TC05_Data = CrestTestDataReader.get_SO_TC05_Data();

	// @SalesInvoice_Search
	@Given("^Navigate to SalesInvoice - AR_SalesInvoice_SearchPage$")
	public void navigate_to_SalesInvoice_AR_SalesInvoice_SearchPage() throws Throwable {
		AR_Main.clickAR();
		AR_Main.clickSalesInvoice();
	}

	@Then("^Validate with Invalid Date Range - SO_SalesInvoice_SearchPage$")
	public void validate_with_Invalid_Date_Range_SO_SalesInvoice_SearchPage() throws Throwable {
		AR_SalesInvoice.Validating_SalesInvoice_date_range_with_invalid_date(Sales_Validation_Msg_Data);
		AR_SalesInvoice.SalesInvoice_assertions();
	}

	// @SalesInvoice_Create
	@Given("^Create SalesInvoice - AR_SalesInvoice_CreatePage$")
	public void create_SalesInvoice_AR_SalesInvoice_CreatePage() throws Throwable {
		AR_Main.clickAR();
		AR_Main.clickSalesInvoice();
		AR_SalesInvoice.clickCreateSalesInvoice();
	}

	@Then("^Validate Create SalesInvoice Page with Invalid data - SO_SalesInvoice_CreatePage$")
	public void validate_Create_SalesInvoice_Page_with_Invalid_data_SO_SalesInvoice_CreatePage() throws Throwable {
		AR_SalesInvoice.Validate_Cancel_button_navigating_to_SalesInvoiceSearchPage();
		AR_SalesInvoice.Validating_without_Mandatory_details(Sales_Validation_Msg_Data);
		AR_SalesInvoice.Validate_PartyID_with_InvalidData(Sales_Validation_Msg_Data, "Column1", 5, "Column5");
		AR_SalesInvoice.Validate_description_with_more_than_255_Characters(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		AR_SalesInvoice.Validate_ReferenceNo_with_more_than_255_Characters(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		// AR_SalesInvoice.Validate_OrderAggrrNterms_without_entering_anydata(Sales_Validation_Msg_Data);
		// AR_SalesInvoice.Validate_OrderAggrrNterms_with_more_than_255_Characters(Sales_Validation_Msg_Data);
		// AR_SalesInvoice.Validate_OrderAggrrNterms_with_invalid_TermDays(Sales_Validation_Msg_Data);
		// AR_SalesInvoice.Validate_OrderAggrrNterms_with_invalid_TermValue(Sales_Validation_Msg_Data);
		AR_SalesInvoice.SalesInvoice_assertions();
	}

	// @SalesInvoice_Header
	@Given("^Create SalesInvoice - AR_SalesInvoice_HeaderPage$")
	public void create_SalesInvoice_AR_SalesInvoice_HeaderPage() throws Throwable {
		AR_SalesInvoice.CreateSalesInvoice_For_NegFlow(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
	}

	@Then("^Validate SalesInvoice Header_Page with Invalid data - SO_SalesInvoice_HeaderPage$")
	public void validate_SalesInvoice_Header_Page_with_Invalid_data_SO_SalesInvoice_HeaderPage() throws Throwable {
		AR_SalesInvoice_Header.Validate_by_updating_without_PartyID(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		AR_SalesInvoice_Header.Validate_by_updating_without_Currency(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Header.Validate_PartyID_with_InvalidData(Sales_Validation_Msg_Data, "Column1", 5, "Column5");
		AR_SalesInvoice_Header.Validate_description_with_more_than_255_Characters(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		AR_SalesInvoice_Header.Validate_ReferenceNo_with_more_than_255_Characters(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		AR_SalesInvoice_Header.SalesInvoiceHeader_softAssert();
	}

	// @SalesInvoice_Items
	@Given("^Create SalesInvoice - AR_SalesInvoice_ItemsPage$")
	public void create_SalesInvoice_AR_SalesInvoice_ItemsPage() throws Throwable {
		AR_SalesInvoice.CreateSalesInvoice_For_NegFlow(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
	}

	@Then("^Validate_SalesInvoice_Items Page with Invalid data - SO_SalesInvoice_ItemsPage$")
	public void validate_salesinvoice_items_Page_with_Invalid_data_SO_SalesInvoice_ItemsPage() throws Throwable {
		AR_SalesInvoice_Items.Validating_without_Mandatory_details(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Items.Validate_Qty_with_Spl_Characters(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Items.Validate_Qty_with_Characters(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Items.Validate_Qty_with_Neg_Value(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Items.Validate_UnitPrice_with_Spl_Characters(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Items.Validate_UnitPrice_with_Characters(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Items.Validate_UnitPrice_with_Neg_Value(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Items.Validate_without_GL_Acc(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Items.Validate_with_more_than_255_characters_in_Description(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Items.addLineItem_NegFlow(Sales_Validation_Msg_Data);
		// AR_SalesInvoice_Items.Validate_by_updating_qty_with_invalidData(Sales_Validation_Msg_Data);
		// AR_SalesInvoice_Items.Validate_by_updating_UnitPrice_with_invalidData(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Items.Validate_lineItem_isExist_by_cancelling_confirmation_Alert(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Items.Validate_lineItem_isExist_by_Accepting_confirmation_Alert(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Items.SalesInvoiceItems_softAssert();
	}

	// @SalesInvoice_Applications
	@Given("^Create SalesInvoice - AR_SalesInvoice_ApplicationsPage$")
	public void create_SalesInvoice_AR_SalesInvoice_ApplicationsPage() throws Throwable {
		AR_SalesInvoice.CreateSalesInvoice_For_NegFlow(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		AR_SalesInvoice_Items.addLineItem_NegFlow(Sales_Validation_Msg_Data);
	}

	@Then("^Validate_SalesInvoice_Application Page with Invalid data - SO_SalesInvoice_ApplicationsPage$")
	public void validate_salesinvoice_application_Page_with_Invalid_data_SO_SalesInvoice_ApplicationsPage() throws Throwable {
		AR_SalesInvoice_Applications.applyPaymentWithEmptyAmount(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Applications.SI_Appln_softAssert();
	}

	// @SalesInvoice_Content
	@Given("^Create SalesInvoice - AR_SalesInvoice_ContentPage$")
	public void create_SalesInvoice_AR_SalesInvoice_ContentPage() throws Throwable {
		AR_SalesInvoice.CreateSalesInvoice_For_NegFlow(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		AR_SalesInvoice_Items.addLineItem_NegFlow(Sales_Validation_Msg_Data);

	}

	@Then("^Validate_SalesInvoice_Content Page with Invalid data - SO_SalesInvoice_ContentPage$")
	public void validate_salesinvoice_content_Page_with_Invalid_data_SO_SalesInvoice_ContentPage() throws Throwable {
		AR_SalesInvoice_Content.validate_ContentTab_without_mandatory_fields(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Content.validate_fileUpload_with_211MB(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Content.validate_fileUpload_with_sameFile_twice(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Content.validate_fileUpload_with_valid_fileType(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Content.validate_fileUpload_with_invalid_fileType(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Content.validate_fileUpload_with_250characters_in_description(Sales_Validation_Msg_Data);
		AR_SalesInvoice.CreateSalesInvoice_For_NegFlow(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		AR_SalesInvoice_Items.addLineItem_NegFlow(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Content.Validate_lineItem_isExist_by_cancelling_confirmation_Alert(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Content.Validate_lineItem_isExist_by_Accepting_confirmation_Alert(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Content.SI_Content_softAssert();
	}

	// @SalesInvoice_Overview
	@Given("^Create SalesInvoice - AR_SalesInvoice_OverviewPage$")
	public void create_SalesInvoice_AR_SalesInvoice_OverviewPage() throws Throwable {
		AR_SalesInvoice.CreateSalesInvoice_For_NegFlow(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
	}

	@Then("^Validate_SalesInvoice_Overview Page with Invalid data - SO_SalesInvoice_OverviewPage$")
	public void validate_salesinvoice_overview_Page_with_Invalid_data_SO_SalesInvoice_OverviewPage() throws Throwable {
		AR_SalesInvoice_Overview.Validate_BackToInvoice_link_navigating_to_SalesInvoiceOverviewPage();
		AR_SalesInvoice_Overview.Validate_void_without_voidReason(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Overview.Validate_void_without_voidDate(Sales_Validation_Msg_Data);
		AR_SalesInvoice_Overview.Validate_VoidReason_255Char(Sales_Validation_Msg_Data);
		AR_SalesInvoice.CreateSalesInvoice_For_NegFlow(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		AR_SalesInvoice_Overview.Validate_ApproveOrPost_with_0_invoiceAmount(Sales_Validation_Msg_Data, "Approve", "Approved");
		AR_SalesInvoice.CreateSalesInvoice_For_NegFlow(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		AR_SalesInvoice_Overview.Validate_ApproveOrPost_with_0_invoiceAmount(Sales_Validation_Msg_Data, "Post", "Posted");
		AR_SalesInvoice_Overview.SI_Overview_softAssert();
	}

	// @Receipts_Search
	@Given("^Navigate to Receipts - AR_Receipts_SearchPage$")
	public void navigate_to_Receipts_AR_Receipts_SearchPage() throws Throwable {
		AR_Receipts.navigateToReceipts();
	}

	@Then("^Validate_Receipts_Search Page with Invalid data - SO_Receipts_SearchPage$")
	public void validate_receipts_search_Page_with_Invalid_data_SO_Receipts_SearchPage() throws Throwable {
		AR_Receipts.Validating_Receipts_date_range_with_invalid_date(Sales_Validation_Msg_Data);
		AR_Receipts.AR_Receipts_softAssert();
	}

	// @Receipts_Create
	@Given("^Create Receipts - AR_Receipts_CreatePage$")
	public void create_Receipts_AR_Receipts_CreatePage() throws Throwable {
		AR_Receipts.navigateToReceipts();
		AR_Receipts.clickCreateReceipt();
	}

	@Then("^Validate_Receipts_Create Page with Invalid data - SO_Receipts_CreatePage$")
	public void validate_receipts_create_Page_with_Invalid_data_SO_Receipts_CreatePage() throws Throwable {
		AR_Receipts.Validate_Cancel_button_navigating_to_SalesInvoiceSearchPage();
		AR_Receipts.Validating_without_Mandatory_details(Sales_Validation_Msg_Data);
		AR_Receipts.Validate_PartyID_with_InvalidData(Sales_Validation_Msg_Data, "Column1", 14, "Column5");
		AR_Receipts.Validate_RefNo_with_255Characters(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		AR_Receipts.Validate_comments_with_255Characters(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		AR_Receipts.Validate_Amount_with_InvalidData(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		AR_Receipts.AR_Receipts_softAssert();
	}

	// @Receipts_Header
	@Given("^Create Receipts - AR_Receipts_HeaderPage$")
	public void create_Receipts_AR_Receipts_HeaderPage() throws Throwable {
		AR_Receipts.create_Receipt_for_Neg_Flow(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
	}

	@Then("^Validate_Receipts_Header Page with Invalid data - SO_Receipts_HeaderPage$")
	public void validate_receipts_header_Page_with_Invalid_data_SO_Receipts_HeaderPage() throws Throwable {
		// AR_Receipts_Header.Validate_by_updating_PartyID_with_InvalidData(Sales_Validation_Msg_Data,
		// "Column1", 14, "Column5");
		AR_Receipts_Header.Validate_Cancel_button_navigating_to_SalesInvoiceSearchPage();
		// AR_Receipts_Header.Validate_by_updating_RefNo_with_255Characters(Sales_Validation_Msg_Data,
		// Sales_PositveData_For_NegativeFlow);
		AR_Receipts_Header.Validate_by_updating_comments_with_255Characters(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		AR_Receipts_Header.AR_ReceiptsHeader_softAssert();
	}

	// @Receipts_Applications
	@Given("^Create Receipts - AR_Receipts_ApplicationsPage$")
	public void create_Receipts_AR_Receipts_ApplicationsPage() throws Throwable {
		AR_Receipts.create_Receipt_for_Neg_Flow(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		AR_Receipts_Applications.clickApplnTab();
	}

	@Then("^Validate_Receipts_Applications Page with Invalid data - SO_Receipts_ApplicationsPage$")
	public void validate_receipts_applications_Page_with_Invalid_data_SO_Receipts_ApplicationsPage() throws Throwable {
		AR_Receipts_Applications.validate_Applying_Payment_without_selecting_any_checkbox(Sales_Validation_Msg_Data);
		AR_Receipts_Applications.validate_by_dismissing_PaymentConfirmationAlert(Sales_Validation_Msg_Data);
		AR_Receipts_Applications.validate_by_dismissing_RemovalConfirmationAlert(Sales_Validation_Msg_Data, true);
		AR_Receipts_Applications.validate_by_dismissing_RemovalConfirmationAlert(Sales_Validation_Msg_Data, false);
		AR_Receipts_Applications.AR_ReceiptsAppln_softAssert();
	}

	// @Receipts_Overview
	@Given("^Create Receipts - AR_Receipts_OverviewPage$")
	public void create_Receipts_AR_Receipts_OverviewPage() throws Throwable {
		AR_Receipts.create_Receipt_for_Neg_Flow(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
	}

	@Then("^Validate_Receipts_Overview Page with Invalid data - SO_Receipts_OverviewPage$")
	public void validate_receipts_overview_Page_with_Invalid_data_SO_Receipts_OverviewPage() throws Throwable {

		AR_Receipts_Overview.Validate_Cancel_without_voidReason(Sales_Validation_Msg_Data);
		AR_Receipts_Overview.Validate_Cancel_without_voidDate(Sales_Validation_Msg_Data);
		AR_Receipts_Overview.Validate_VoidReason_255Char(Sales_Validation_Msg_Data);
		AR_Receipts_Overview.Validate_BackToSearch_link_navigating_to_ReceiptOverviewPage();
		AR_Receipts_Overview.AR_ReceiptsOverview_softAssert();
	}

	// @AR_PaymentGroup_Create
	@Given("^Create Payment Group - AR_PaymentGroup_CreatePage$")
	public void create_Payment_Group_AR_PaymentGroup_CreatePage() throws Throwable {
		AR_Main.clickAR();
		AR_Main.clickAR_Payment_Groups();
		AR_ARPaymentGroups.clickCreate_Payment_Groups();
	}

	@Then("^Validate_PaymentGroup_Create Page with Invalid data - AR_PaymentGroup_CreatePage$")
	public void validate_paymentgroup_create_Page_with_Invalid_data_AR_PaymentGroup_CreatePage() throws Throwable {
		AR_ARPaymentGroups.Validate_Create_AR_Payment_Group_without_anydata(Sales_Validation_Msg_Data);
		AR_ARPaymentGroups.Validate_Cancel_AR_Payment(Sales_Validation_Msg_Data);
		AR_ARPaymentGroups.AR_PaymentGroup_softAssert();
	}
	//// @AR_PaymentGroup_GroupMembers
	@Given("^Create Payment Group - AR_PaymentGroup_GroupMembersPage$")
	public void create_Payment_Group_AR_PaymentGroup_GroupMembersPage() throws Throwable {
		AR_ARPaymentGroups.CreateARPaymentGroup();
	}

	@Then("^Validate_PaymentGroup_GroupMembers Page with Invalid data - AR_PaymentGroup_GroupMembersPage$")
	public void validate_paymentgroup_groupmembers_Page_with_Invalid_data_AR_PaymentGroup_GroupMembersPage() throws Throwable {
		AR_ARPaymentGroups.Validate_GroupMembers_without_mandatoryFields(Sales_Validation_Msg_Data);
		AR_ARPaymentGroups.Validating_GroupMembers_date_range_with_invalid_date(Sales_Validation_Msg_Data);
		AR_ARPaymentGroups.AR_PaymentGroup_softAssert();
	}

	// @AR_CreditMemo_Search
	@Given("^Search Credit Memo - AR_Credit Memo_SearchPage$")
	public void search_Credit_Memo_AR_Credit_Memo_SearchPage() throws Throwable {
		AR_Main.clickAR();
		AR_Main.clickCredit_OR_DebitMemo();
	}

	@Then("^Validate_Credit_Memo_Search Page with Invalid data - AR_Credit_Memo_SearchPage$")
	public void validate_credit_memo_search_Page_with_Invalid_data_AR_Credit_Memo_SearchPage() throws Throwable {
		AR_CreditMemo.Validating_CreditMemo_date_range_with_invalid_date(Sales_Validation_Msg_Data);
		AR_CreditMemo.AR_Credit_Or_DebitMemo_softAssert();
	}

	// @AR_CreditMemo_Create
	@Given("^Create Credit Memo - AR_Credit Memo_CreatePage$")
	public void create_Credit_Memo_AR_Credit_Memo_CreatePage() throws Throwable {
		AR_Main.clickAR();
		AR_Main.clickCredit_OR_DebitMemo();
		AR_CreditMemo.click_CreateCredit_or_DebitMemo();
	}

	@Then("^Validate_Credit_Memo_Create Page with Invalid data - AR_Credit_Memo_CreatePage$")
	public void validate_credit_memo_create_Page_with_Invalid_data_AR_Credit_Memo_CreatePage() throws Throwable {
		AR_CreditMemo.Validating_without_Mandatory_details(Sales_Validation_Msg_Data);
		AR_CreditMemo.AR_Credit_Or_DebitMemo_softAssert();
	}

	// @AR_CreditMemo_Items
	@Given("^Create Credit Memo - AR_Credit Memo_ItemsPage$")
	public void create_Credit_Memo_AR_Credit_Memo_ItemsPage() throws Throwable {
		AR_Main.clickAR();
		AR_Main.clickCredit_OR_DebitMemo();
		AR_CreditMemo.click_CreateCredit_or_DebitMemo();
		AR_CreditMemo.createCreditMemo_for_NegFlow(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		AR_CreditMemo.click_Items();
	}

	@Then("^Validate_Credit_Memo_Items Page with Invalid data - AR_Credit_Memo_ItemsPage$")
	public void validate_credit_memo_items_Page_with_Invalid_data_AR_Credit_Memo_ItemsPage() throws Throwable {
		AR_CreditMemo.Validating_CreditMemo_Items_page_without_Mandatory_details(Sales_Validation_Msg_Data);
		AR_CreditMemo.add_LineItem_for_NegFlow(Sales_Validation_Msg_Data);
		AR_CreditMemo.Validate_by_updating_Amount_with_SPL_Characters(Sales_Validation_Msg_Data);
		AR_CreditMemo.Validate_by_removing_line_item(Sales_Validation_Msg_Data);
		AR_CreditMemo.Validate_void_CreditMemo_without_any_details(Sales_Validation_Msg_Data);
		AR_CreditMemo.AR_Credit_Or_DebitMemo_softAssert();
	}

	// @AR_CustAdjmt_Search
	@Given("^Search CustAdjmt - AR_CustAdjmt_SearchPage$")
	public void search_CustAdjmt_AR_CustAdjmt_SearchPage() throws Throwable {
		AR_Main.clickAR();
		AR_Main.clickCustomerAdjustment();
	}

	@Then("^Validate_CustAdjmt_Search Page with Invalid data - AR_CustAdjmt_SearchPage$")
	public void validate_custadjmt_search_Page_with_Invalid_data_AR_CustAdjmt_SearchPage() throws Throwable {
		AR_CustomerAdjustment.Validating_CustAdjmt_date_range_with_invalid_date(Sales_Validation_Msg_Data);
		AR_CustomerAdjustment.AR_CustAdjmt_softAssert();
	}

	// @AR_CustAdjmt_Create
	@Given("^Create CustAdjmt - AR_CustAdjmt_CreatePage$")
	public void create_CustAdjmt_AR_CustAdjmt_CreatePage() throws Throwable {
		AR_Main.clickAR();
		AR_Main.clickCustomerAdjustment();
		AR_CustomerAdjustment.click_CreateAdjmt();
	}

	@Then("^Validate_CustAdjmt_Create Page with Invalid data - AR_CustAdjmt_CreatePage$")
	public void validate_custadjmt_create_Page_with_Invalid_data_AR_CustAdjmt_CreatePage() throws Throwable {
		AR_CustomerAdjustment.validate_createCustAdjmt_without_any_details(Sales_Validation_Msg_Data);
		AR_CustomerAdjustment.createCustAdjmt_for_NegFlow(Sales_Validation_Msg_Data, Sales_PositveData_For_NegativeFlow);
		AR_CustomerAdjustment.validate_Compute_without_Debit_Document(Sales_Validation_Msg_Data);
		AR_CustomerAdjustment.validate_Compute_without_Credit_Document(Sales_Validation_Msg_Data);
		AR_CustomerAdjustment.AR_CustAdjmt_softAssert();
	}

}
