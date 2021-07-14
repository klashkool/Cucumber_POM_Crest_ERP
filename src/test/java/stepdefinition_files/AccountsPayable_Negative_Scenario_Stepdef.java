package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.AccountsPayable_Modules.AP_CreditMemo;
import com.AccountsPayable_Modules.AP_CreditMemo_Items;
import com.AccountsPayable_Modules.AP_Payments;
import com.AccountsPayable_Modules.AP_Payments_Applications;
import com.AccountsPayable_Modules.AP_PurchaseInvoices;
import com.AccountsPayable_Modules.AP_PurchaseInvoices_Applications;
import com.AccountsPayable_Modules.AP_PurchaseInvoices_Items;
import com.AccountsPayable_Modules.AP_SupplierAdjustment;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AccountsPayable_Negative_Scenario_Stepdef extends Base {

	List<HashMap<String, String>> Proc_Val_Msg = CrestTestDataReader.get_Proc_Val_Msg();
	List<HashMap<String, String>> Proc_Positive_Data = CrestTestDataReader.get_Proc_Positive_Data();
	List<HashMap<String, String>> TC01_2 = CrestTestDataReader.get_TC01_2_Data();

	// @Proc_AP_Neg01
	// Scenario: PurchaseInvoice - PI_SearchPage
	@Given("^Navigate to Purchase Invoice Search page - PI_SearchPage$")
	public void navigate_to_Purchase_Invoice_Search_page_PI_SearchPage() {
		AP_PurchaseInvoices.PurchaseInvoice_Navigation();
	}

	@Then("^Validate with Invalid Data - PI_SearchPage$")
	public void validate_with_Invalid_Data_PI_SearchPage() {
		AP_PurchaseInvoices.Invalid_InvoiceDate_DueDate_Val(Proc_Val_Msg);
	}

	// @Proc_AP_Neg02
	// Scenario: PurchaseInvoice - PI_CreatePage
	@Given("^Navigate to Purchase Invoice Create page - PI_CreatePage$")
	public void navigate_to_Purchase_Invoice_Create_page_PI_CreatePage() {
		AP_PurchaseInvoices.PurchaseInvoice_Navigation();
		AP_PurchaseInvoices.CreatePI_Link();
	}

	@Then("^Validate with Invalid Data - PI_CreatePage$")
	public void validate_with_Invalid_Data_PI_CreatePage() throws InterruptedException {

		// AP_PurchaseInvoices.MandatoryFields_Val_CreatePIPage(Proc_Val_Msg);
		AP_PurchaseInvoices.Invalid_DueDate_Val(Proc_Val_Msg);
		AP_PurchaseInvoices.Desc_255CharacVal_PICreatePage(Proc_Val_Msg);
		AP_PurchaseInvoices.CancelBtnVal_CreatePIPage();
		AP_PurchaseInvoices.InvoiceSearch(Proc_Positive_Data);
		AP_PurchaseInvoices.ApproveInv_WithoutRefNo_Duedate(Proc_Val_Msg);
		// AP_PurchaseInvoices.VoidInv_WithoutReason(Proc_Val_Msg);
		// AP_PurchaseInvoices.VoidInv_WithoutVoidDate(Proc_Val_Msg);
		// AP_PurchaseInvoices.BackToInvoiceLink_Val();
		AP_PurchaseInvoices.CopyLink_Val();
		AP_PurchaseInvoices.PurchaseInvoice_softAssert();

	}

	// @Proc_AP_Neg03
	// Scenario: PurchaseInvoice - PI_ItemsPage

	@Given("^Navigate to Purchase Invoice Items page - PI_ItemsPage$")
	public void navigate_to_Purchase_Invoice_Items_page_PI_ItemsPage() throws InterruptedException {
		AP_PurchaseInvoices.InvoiceSearch(Proc_Positive_Data);
		AP_PurchaseInvoices_Items.Items_link();
	}

	@Then("^Validate with Invalid Data - PI_ItemsPage$")
	public void validate_with_Invalid_Data_PI_ItemsPage() {
		AP_PurchaseInvoices_Items.MandatoryFields_Val(Proc_Val_Msg);
		AP_PurchaseInvoices_Items.desc_255Charc_Val(Proc_Val_Msg);
		AP_PurchaseInvoices_Items.InvalidQty(Proc_Val_Msg);
		AP_PurchaseInvoices_Items.Invalid_UnitPrice(Proc_Val_Msg);
		AP_PurchaseInvoices_Items.Add_LineItems();
		AP_PurchaseInvoices_Items.InvalidQty_LineItem(Proc_Val_Msg);
		AP_PurchaseInvoices_Items.InvalidUnitPrice_LineItem(Proc_Val_Msg);
		AP_PurchaseInvoices_Items.Remove_LineItems();
		AP_PurchaseInvoices_Items.PI_Items_assertions();

	}

	// @Proc_AP_Neg04
	// Scenario: PurchaseInvoice - PI_ApplicationPage

	@Given("^Navigate to Purchase Invoice Application page - PI_ApplicationPage$")
	public void navigate_to_Purchase_Invoice_Application_page_PI_ApplicationPage() throws InterruptedException {
		AP_PurchaseInvoices.InvoiceSearch(Proc_Positive_Data);
		AP_PurchaseInvoices_Applications.Application_Link();
	}

	@Then("^Validate with Invalid Data - PI_ApplicationPage$")
	public void validate_with_Invalid_Data_PI_ApplicationPage() {

		AP_PurchaseInvoices_Applications.InvalidAmt_Val(Proc_Val_Msg);
		AP_PurchaseInvoices_Applications.Apply_RemovePayID(Proc_Val_Msg);
		AP_PurchaseInvoices_Applications.PI_Application_assertions();
	}

	// @Proc_AP_Neg05
	// Scenario: CreditMemo - CreditMemo_SearchPage

	@Given("^Navigate to Credit Memo Search page - CreditMemo_SearchPage$")
	public void navigate_to_Credit_Memo_Search_page_CreditMemo_SearchPage() {

		AP_CreditMemo.AccountsPayableLink();
		AP_CreditMemo.CreditMemoLink();
	}

	@Then("^Validate with Invalid Data - CreditMemo_SearchPage$")
	public void validate_with_Invalid_Data_CreditMemo_SearchPage() {

		AP_CreditMemo.InvalidDates_Val(Proc_Val_Msg);
		AP_CreditMemo.AP_CreditMemoSearch_softAssert();
	}

	// @Proc_AP_Neg06
	// Scenario: CreditMemo - CreditMemo_CreatePage

	@Given("^Navigate to Credit Memo Create page - CreditMemo_CreatePage$")
	public void navigate_to_Credit_Memo_Create_page_CreditMemo_CreatePage() {

		AP_CreditMemo.AccountsPayableLink();
		AP_CreditMemo.CreditMemoLink();
		AP_CreditMemo.CreateCreditMemoLink();
	}

	@Then("^Validate with Invalid Data - CreditMemo_CreatePage$")
	public void validate_with_Invalid_Data_CreditMemo_CreatePage() {

		AP_CreditMemo.MandatoryFields_Val(Proc_Val_Msg);
		AP_CreditMemo.Refno_255CharacVal(Proc_Val_Msg);
		AP_CreditMemo.desc_255CharcVal(Proc_Val_Msg);
		AP_CreditMemo.AP_CreditMemo_softAssert();
	}

	// @Proc_AP_Neg07
	// Scenario: CreditMemo - CreditMemo_ItemsPage

	@Given("^Navigate to Credit Memo Items page - CreditMemo_ItemsPage$")
	public void navigate_to_Credit_Memo_Items_page_CreditMemo_ItemsPage() {

		AP_CreditMemo.AccountsPayableLink();
		AP_CreditMemo.CreditMemoLink();
		AP_CreditMemo.CreateCreditMemoLink();
		AP_CreditMemo.Create_CreditMemo();
	}

	@Then("^Validate with Invalid Data - CreditMemo_ItemsPage$")
	public void validate_with_Invalid_Data_CreditMemo_ItemsPage() {

		AP_CreditMemo_Items.CreditMemo_ItemsLink();
		AP_CreditMemo_Items.MandatoryFields_Val(Proc_Val_Msg);
		AP_CreditMemo_Items.GL_Account_Val(Proc_Val_Msg);
		AP_CreditMemo_Items.InvalidAmt(Proc_Val_Msg);
		AP_CreditMemo_Items.desc_255CharacVal(Proc_Val_Msg);
		AP_CreditMemo_Items.InvalidAmt_CreditMemoItemDetails(Proc_Val_Msg);
		AP_CreditMemo_Items.remove_CreditMemoItemDetails();
		AP_CreditMemo_Items.Void_CreditMemo(Proc_Val_Msg);
		AP_CreditMemo_Items.AP_CreditMemoItems_softAssert();
	}

	// @Proc_AP_Neg08
	// Scenario: SupplierAdjustment - SupplierAdjustment_SearchPage

	@Given("^Navigate to Supplier Adjustment Search page - SupplierAdjustment_SearchPage$")
	public void navigate_to_Supplier_Adjustment_Search_page_SupplierAdjustment_SearchPage() {
		AP_SupplierAdjustment.AccountsPayableLink();
		AP_SupplierAdjustment.SupAdjLink();
	}

	@Then("^Validate with Invalid Data - SupplierAdjustment_SearchPage$")
	public void validate_with_Invalid_Data_SupplierAdjustment_SearchPage() {
		AP_SupplierAdjustment.InvalidDates_Val(Proc_Val_Msg);//
		AP_SupplierAdjustment.AP_SupAdj_softAssert();//
	}

	// @Proc_AP_Neg09
	// Scenario: SupplierAdjustment - SupplierAdjustment_CreatePage

	@Given("^Navigate to Supplier Adjustment Create page - SupplierAdjustment_CreatePage$")
	public void navigate_to_Supplier_Adjustment_Create_page_SupplierAdjustment_CreatePage() {
		AP_SupplierAdjustment.AccountsPayableLink();
		AP_SupplierAdjustment.SupAdjLink();
		AP_SupplierAdjustment.CreateSupAdjLink();
	}

	@Then("^Validate with Invalid Data - SupplierAdjustment_CreatePage$")
	public void validate_with_Invalid_Data_SupplierAdjustment_CreatePage() {
		AP_SupplierAdjustment.MandatoryFields_Val(Proc_Val_Msg);
		AP_SupplierAdjustment.Click_Compute_WithoutLineItems(Proc_Val_Msg);
		AP_SupplierAdjustment.AP_SupAdj_softAssert();
	}

	// @Proc_AP_Neg10
	// Scenario: Payments - Payments_SearchPage

	@Given("^Navigate to Payment Search page - Payment_SearchPage$")
	public void navigate_to_Payment_Search_page_Payment_SearchPage() {
		AP_Payments.AccountsPayableLink();
		AP_Payments.PaymentsLink();
	}

	@Then("^Validate with Invalid Data - Payment_SearchPage$")
	public void validate_with_Invalid_Data_Payment_SearchPage() {
		AP_Payments.InvalidDates_Val(Proc_Val_Msg);
		AP_Payments.AP_Payments_softAssert();
	}

	// @Proc_AP_Neg11
	// Scenario: Payments - VendorPayments_CreatePage

	@Given("^Navigate to Vendor Payment Create page - VendorPayments_CreatePage$")
	public void navigate_to_Vendor_Payment_Create_page_VendorPayments_CreatePage() {
		AP_Payments.AccountsPayableLink();
		AP_Payments.PaymentsLink();
		AP_Payments.CreatePaymentLink();
	}

	@Then("^Validate with Invalid Data - VendorPayments_CreatePage$")
	public void validate_with_Invalid_Data_VendorPayments_CreatePage() {

		AP_Payments.CreatePayment_MandatoryFields_Val(Proc_Val_Msg);
		AP_Payments.RefNo_255CharacVal(Proc_Val_Msg);
		AP_Payments.Comments_255CharacVal(Proc_Val_Msg);
		AP_Payments.AP_Payments_softAssert();
	}

	// @Proc_AP_Neg12
	// Scenario: Payments - Payments_ApplicationPage

	@Given("^Navigate to Payments Application page - Payments_ApplicationPage$")
	public void navigate_to_Payments_Application_page_Payments_ApplicationPage() {
		AP_Payments.AccountsPayableLink();
		AP_Payments.PaymentsLink();
		AP_Payments.CreatePaymentLink();
		AP_Payments.CreatePayments();
	}

	@Then("^Validate with Invalid Data - Payments_ApplicationPage$")
	public void validate_with_Invalid_Data_Payments_ApplicationPage() throws InterruptedException {

		AP_Payments_Applications.ApplicationLink();
		// AP_Payments_Applications.Invalid_AmtVal(Proc_Val_Msg);
		AP_Payments_Applications.apply_withoutSelectingLineIems(Proc_Val_Msg);
		AP_Payments_Applications.removeAddedLineItem();
		AP_Payments_Applications.Cancel_Payments(Proc_Val_Msg);
		AP_Payments_Applications.AP_PaymentsApplication_softAssert();
	}

}
