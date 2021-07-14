package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.CRM.CRM_Activities;
import com.CRM.CRM_Leads;
import com.CRM.CRM_Main;
import com.CRM.CRM_Opportunities;
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



public class SO_TC09_Stepdef extends Base {

	List<HashMap<String, String>> SO_TC09_Data = CrestTestDataReader.get_SO_TC09_Data();

	@Given("^Navigate to CRM>>Lead>>Create Lead$")
	public void navigate_to_CRM_Lead_Create_Lead() throws Throwable {
		CRM_Main.clickCRM();
		CRM_Main.clickLeads();
		CRM_Leads.clickCreateLead();
	}

	@Then("^Create Lead and capture details$")
	public void create_Lead_and_capture_details() throws Throwable {
		CRM_Leads.CreateLead(SO_TC09_Data, "SO_TC09");
		CRM_Leads.captureLeadDetails("SO_TC09");
	}

	@Then("^Convert to Account and capture details$")
	public void convert_to_Account_and_capture_details() throws Throwable {
		CRM_Leads.convertToAccount(SO_TC09_Data);
	}

	@Then("^Create the sales opportunity$")
	public void create_the_sales_opportunity() throws Throwable {
		CRM_Opportunities.clickOpportunityAndCaptureAccountNo("SO_TC09");
		CRM_Opportunities.createOpportunity(SO_TC09_Data, "SO_TC09");
		CRM_Opportunities.captureOpportunityID("SO_TC09");
	}

	@Then("^Create the sales activity$")
	public void create_the_sales_activity() throws Throwable {
		CRM_Activities.createActivity(SO_TC09_Data, "SO_TC09");
	}

	@Then("^Create Quote_add line items$")
	public void create_Quote_add_line_items() throws Throwable {
		Sales_Quotes.createQuoteFromCRM(SO_TC09_Data, "SO_TC09");
		Sales_Quotes_QuoteItems.Add_Products(SO_TC09_Data);
	}

	@Then("^AcceptQuote and validate details$")
	public void acceptquote_and_validate_details() throws Throwable {
		Sales_Quotes_Edit.SO_Accept_Quote();
		Sales_Quotes_View.Quote_Header_Val();
		Sales_Quotes_View.SO_Quotes_GrndTotal_Val(SO_TC09_Data, "Column15");
		Sales_Quotes_View.SO_View_assertions();
	}

	@Then("^Covert to Order and Create Order$")
	public void covert_to_Order_and_Create_Order() throws Throwable {
		Sales_Quotes_View.SO_Quotes_CreateOrderLink();
		WebdriverWait.findElement("link", "Continue").click();
		WebdriverWait.findElement("link", "Continue").click();
		WebdriverWait.findElement("link", "Continue").click();
		Sales_SalesOrder.SO_ReviewCreateSO("SO_TC09");
	}

	@Then("^Validate Order details$")
	public void validate_Order_details() throws Throwable {
		Sales_SalesOrder_View.SO_SubTotal_Val(SO_TC09_Data, "Column15");
		Sales_SalesOrder_View.SO_Tax_Val(SO_TC09_Data, "Column16");
		Sales_SalesOrder_View.Tax_Consolidated_PopupBtn_Val(SO_TC09_Data);
		Sales_SalesOrder_View.SO_GrdTotal_Val(SO_TC09_Data, "Column17");
		Sales_SalesOrder_View.TaxBreakup_Table_Val(SO_TC09_Data);
		Sales_SalesOrder_View.SO_View_assertions();
	}

}
