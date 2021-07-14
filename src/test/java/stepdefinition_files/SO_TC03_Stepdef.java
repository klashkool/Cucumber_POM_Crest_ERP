package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.AccountsPayable_Modules.AP_PurchaseInvoices_Overview;
import com.Facilities_Modules.Facilities_Facilities_StockManagement;
import com.Sales_Modules.Sales_SalesReturn;
import com.Sales_Modules.Sales_SalesReturn_ReturnHeader;
import com.Sales_Modules.Sales_SalesReturn_ReturnItems;
import com.Sales_Modules.Sales_SalesReturn_ReturnSummary;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class SO_TC03_Stepdef extends Base {
//Return is executed on SO_TC_05
	List<HashMap<String, String>> SO_Return_data = CrestTestDataReader.get_SO_Return_Data();

	@Given("^Create SO Return - SO Return Flow$")
	public void create_SO_Return_SO_Return_Flow() throws Throwable {
		Sales_SalesReturn.CreateSalesReturn(SO_Return_data, "SO_TC03");
	}
	
	@Then("^Capture SO Return ID - SO Return Flow$")
	public void capture_SO_Return_ID_SO_Return_Flow() throws Throwable {
		Sales_SalesReturn_ReturnHeader.Capture_SalesReturnID("SO_TC03");
	}

	@Then("^Return Items - SO Return Flow$")
	public void return_Items_SO_Return_Flow() throws Throwable {
		Sales_SalesReturn_ReturnItems.SalesReturn_items_with_Tax(SO_Return_data);
	}

	@Then("^Receive returned items - SO Return Flow$")
	public void receive_returned_items_SO_Return_Flow() throws Throwable {
		Sales_SalesReturn_ReturnHeader.click_ReceiveSalesReturnLink();
		Facilities_Facilities_StockManagement.receiveSalesReturnProducts();	
	}

	@Then("^Verify SO Return status - SO Return Flow$")
	public void verify_SO_Return_status_SO_Return_Flow() throws Throwable {
		Facilities_Facilities_StockManagement.click_ReturnNoLink();
		Sales_SalesReturn_ReturnHeader.verifySalesReturnStatus("Completed");
	}

	@Then("^Capture Return Shipment and Invoice ID - SO Return Flow$")
	public void capture_Return_Shipment_and_Invoice_ID_SO_Return_Flow() throws Throwable {
		Sales_SalesReturn_ReturnSummary.click_ReturnSummaryLink();
		Sales_SalesReturn_ReturnSummary.capture_SalesReturnInvoiceID("SO_TC03");
		Sales_SalesReturn_ReturnSummary.capture_SalesReturnShipmentID("SO_TC03");
	}

	@Then("^Open Return Shipment and Verify details - SO Return Flow$")
	public void open_Return_Shipment_and_Verify_details_SO_Return_Flow() throws Throwable {
		Sales_SalesReturn_ReturnSummary.click_ReturnShipmentLink();
		Sales_SalesReturn_ReturnSummary.verifySalesReturnShipmentStatus("Received");
		Sales_SalesReturn_ReturnSummary.verifySalesReturnShipmentQty(SO_Return_data);
	}

	@Then("^Open Return Invoice and Verify details - SO Return Flow$")
	public void open_Return_Invoice_and_Verify_details_SO_Return_Flow() throws Throwable {
		Sales_SalesReturn_ReturnSummary.click_SalesReturnID();
		Sales_SalesReturn_ReturnSummary.click_ReturnSummaryLink();
		Sales_SalesReturn_ReturnSummary.click_SalesReturnInvoiceID();
		AP_PurchaseInvoices_Overview.verifySalesReturnInvoiceDetails_Prd_N_Qty(SO_Return_data);
		AP_PurchaseInvoices_Overview.verify_SalesReturn_SubTotal_Val(SO_Return_data);
		AP_PurchaseInvoices_Overview.verify_SO_Tax_Val(SO_Return_data);
		AP_PurchaseInvoices_Overview.verify_SO_GrdTotal_Val(SO_Return_data);
		AP_PurchaseInvoices_Overview.PI_View_softAssert();
	}

}
