package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.Procurement_Modules.Procurement_PurchaseOrder_View;
import com.Sales_Modules.Sales_SalesOrder;
import com.Sales_Modules.Sales_SalesOrder_View;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;



public class SO_TC08_Stepdef extends Base {

	List<HashMap<String, String>> SO_DropShip_Data = CrestTestDataReader.get_SO_DropShip_Data();

	@Given("^Create SO with Drop Ship YES - SO_DropShip$")
	public void create_SO_with_Drop_Ship_YES_SO_DropShip() throws Throwable {
		Sales_SalesOrder.SO_StartOrder(SO_DropShip_Data, "SO_TC08", "-Select-");
		Sales_SalesOrder.OfflinePayment_Chkbox();
	}

	@Then("^Add Shipping Details - SO_DropShip$")
	public void add_Shipping_Details_SO_DropShip() throws Throwable {
		Sales_SalesOrder.SO_Shipping_Details_SingleFacility(SO_DropShip_Data);
	}

	@Then("^Add Order Items - SO_DropShip$")
	public void add_Order_Items_SO_DropShip() throws Throwable {
		Sales_SalesOrder.SO_OrderItems_DropShip(SO_DropShip_Data, 4);
	}

	@Then("^Review & Create SO - SO_DropShip$")
	public void review_Create_SO_SO_DropShip() throws Throwable {
		Sales_SalesOrder.SO_ReviewCreateSO("SO_TC08");
	}

	@Then("^Validating SubTotal/Tax/GrandTotal - SO_DropShip$")
	public void validating_SubTotal_Tax_GrandTotal_SO_DropShip() throws Throwable {
		Sales_SalesOrder_View.SO_SubTotal_Val(SO_DropShip_Data, "Column15");
		Sales_SalesOrder_View.SO_Tax_Val(SO_DropShip_Data, "Column16");
		Sales_SalesOrder_View.SO_GrdTotal_Val(SO_DropShip_Data, "Column17");

	}

	@Then("^Validating Drop Ship PO got generated - SO_DropShip$")
	public void validating_Drop_Ship_PO_got_generated_SO_DropShip() throws Throwable {
		Sales_SalesOrder_View.click_firstLineItem_ViewDetails();
		Sales_SalesOrder_View.verify_and_capture_DropShipPO_ID("SO_TC08");
		Sales_SalesOrder_View.SO_View_DropShip_assertions();
	}

	@Then("^Validating SubTotal/Tax/GrandTotalDrop Ship PO - SO_DropShip$")
	public void validating_SubTotal_Tax_GrandTotalDrop_Ship_PO_SO_DropShip() throws Throwable {
		Sales_SalesOrder_View.click_DropShipPO_ID("SO_TC08");
		Procurement_PurchaseOrder_View.switchTab(1);
		Procurement_PurchaseOrder_View.dropShip_PO_SubTotal_Val(SO_DropShip_Data);
		Procurement_PurchaseOrder_View.dropShip_PO_Tax_Val(SO_DropShip_Data);
		Procurement_PurchaseOrder_View.dropShip_PO_GrdTotal_Val(SO_DropShip_Data);
		Procurement_PurchaseOrder_View.PO_View_assertions();
	}

}
