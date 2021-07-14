package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.Facilities_Modules.Facilities_Facilities_StockManagement;
import com.Sales_Modules.Sales_SalesOrder_View;
import com.Sales_Modules.Sales_SalesReturn;
import com.Sales_Modules.Sales_SalesReturn_ReturnHeader;
import com.Sales_Modules.Sales_SalesReturn_ReturnItems;
import com.Sales_Modules.Sales_SalesReturn_ReturnSummary;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;



public class SO_TC04_Stepdef extends Base {
//Return Replacement is executed on SO_TC_05
	List<HashMap<String, String>> SO_ReturnReplacement_data = CrestTestDataReader.get_SO_ReturnReplacement_Data();

	@Given("^Create SO Return - SO Return Replacement Flow$")
	public void create_SO_Return_SO_Return_Flow() throws Throwable {
		Sales_SalesReturn.CreateSalesReturn(SO_ReturnReplacement_data, "SO_TC04");
	}

	@Then("^Capture SO Return ID - SO Return Replacement Flow$")
	public void capture_SO_Return_ID_SO_Return_Flow() throws Throwable {
		Sales_SalesReturn_ReturnHeader.Capture_SalesReturnID("SO_TC04");
	}

	@Then("^Return Items - SO Return Replacement Flow$")
	public void return_Items_SO_Return_Flow() throws Throwable {
		Sales_SalesReturn_ReturnItems.SalesReturnReplacement_items_with_Tax(SO_ReturnReplacement_data);
	}

	@Then("^Receive returned items - SO Return Replacement Flow$")
	public void receive_returned_items_SO_Return_Flow() throws Throwable {
		Sales_SalesReturn_ReturnHeader.click_ReceiveSalesReturnLink();
		Facilities_Facilities_StockManagement.receiveSalesReturnProducts();
	}

	@Then("^Verify SO Return status - SO Return Replacement Flow$")
	public void verify_SO_Return_status_SO_Return_Flow() throws Throwable {
		Facilities_Facilities_StockManagement.click_ReturnNoLink();
		Sales_SalesReturn_ReturnHeader.verifySalesReturnStatus("Completed");
	}

	@Then("^Navigate to Sales Order view details page - SO Return Replacement Flow$")
	public void navigate_to_Sales_Order_view_details_page_SO_Return_Replacement_Flow() throws Throwable {
		Sales_SalesReturn_ReturnSummary.click_ReturnSummaryLink();
		Sales_SalesReturn_ReturnSummary.click_SO_ID();
		Sales_SalesOrder_View.click_firstLineItem_ViewDetails();
	}

	@Then("^Capture Replacement SO ID - SO Return Replacement Flow$")
	public void capture_Replacement_SO_ID_SO_Return_Replacement_Flow() throws Throwable {
		Sales_SalesOrder_View.capture_ReturnReplacementSO_ID("SO_TC04");
	}

	@Then("^Open Replacement SO and Verify details - SO Return Replacement Flow$")
	public void open_Replacement_SO_and_Verify_details_SO_Return_Replacement_Flow() throws Throwable {
		Sales_SalesOrder_View.click_ReturnReplacementSO_ID();
		Sales_SalesOrder_View.switchTab(1);
	//	Sales_SalesOrder_View.verifySalesReturnInvoiceDetails_Prd_N_Qty(SO_ReturnReplacement_data);
		Sales_SalesOrder_View.verify_ReturnReplacementSO_GrdTotal_Value(SO_ReturnReplacement_data);
		Sales_SalesOrder_View.SO_View_assertions();
	}

}
