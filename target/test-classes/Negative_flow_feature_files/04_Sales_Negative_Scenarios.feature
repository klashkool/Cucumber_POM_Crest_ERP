Feature: Sales Negative Scenarios

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@Quotes_Search
Scenario: Sales - Quotes - Search page
	Given Navigate to Quotes - SO_Quotes_SearchPage
	Then Validate with Invalid Date Range - SO_Quotes_SearchPage

@Quotes_Create
Scenario: Sales - Quotes - Create page
	Given Create Quote - SO_Quotes_CreatePage
	Then Validate Create Quote Page with Invalid data - SO_Quotes_CreatePage
	
@Quotes_Edit
Scenario: Sales - Quotes - Edit page
	Given Create Quote - SO_Quotes_EditPage
	Then Validate Quote Edit_Page with Invalid data - SO_Quotes_EditPage
	
@Quotes_QuoteItems
Scenario: Sales - Quotes - Items page
	Given Create Quote - SO_Quotes_ItemsPage
	Then Validate_Quote_Items Page with Invalid data - SO_Quotes_ItemsPage
	
@Quotes_Create_and_Accept
Scenario: Sales - Quotes - Edit page (Positive Flow)
	Given Create and Accept the Quote - SO_Quotes_EditPage (Positive Flow)
	Then Verify the success message - SO_Quotes_EditPage (Positive Flow)

@SO_Search
Scenario: Sales - Sales Order - Search page
	Given Navigate to Sales Order - SO_SearchPage
	Then Validate with Invalid Date Range - SO_SearchPage
	
@SO_StartOrder
Scenario: Sales - Sales Order - Start Order page
	Given Navigate to Sales Order - SO_StartOrder_Page
	Then Validate SO Start Order Page with Invalid data - SO_StartOrder_Page

@SO_ShipDetails
Scenario: Sales - Sales Order - Shipping Details page
	Given Navigate to Sales Order - SO_ShippingDetails_Page
	Then Validate SO Shipping Details Page with Invalid data - SO_ShippingDetails_Page

@SO_OrderItems
Scenario: Sales - Sales Order - Order Items page
	Given Navigate to Sales Order - SO_OrderItems_Page
	Then Validate SO Order Items Page with Invalid data - SO_OrderItems_Page

@SO_View
Scenario: Sales - Sales Order - View page
	Given Navigate to Sales Order - View_Page
	Then Validate Sales Order View Page with Invalid Data - SO_View_Page
	
@SO_Edit
Scenario: Sales - Sales Order - Edit page
	Given Navigate to Sales Order - Edit_Page
	Then Validate Sales Order Edit Page with Invalid Data - SO_Edit_Page

@SO_CreateAsNewOrder
Scenario: Sales - Create Sales Order - Create As New Order
	Given Navigate to Sales Order - Create As New Order
	Then Validate Sales order - SO_Create As New Order

@SO_CancelOrder
Scenario: Sales - Create Sales Order - Cancel Order
	Given Navigate to Sales Order - Create Order
	Then Validate Cancel Order - SO_Cancel Order

@ProformaInvoice_Search
Scenario: Sales - Proforma Invoice - Search page
	Given Navigate to Proforma Invoice - SO_ProformaInvoice_SearchPage
	Then Validate with Invalid Date Range - SO_ProformaInvoice_SearchPage
	
@ProformaInvoice_Create
Scenario: Sales - Proforma Invoice - Create page
	Given Navigate to Create Proforma Invoice - SO_ProformaInvoice_CreatePage
	Then Validate Create Proforma Invoice Page with Invalid data - SO_ProformaInvoice_CreatePage
	
@ProformaInvoice_Header
Scenario: Sales - Proforma Invoice - Header page
	Given Navigate to Proforma Invoice Header - SO_ProformaInvoice_HeaderPage
	Then Validate Proforma Invoice Header Page with Invalid data - SO_ProformaInvoice_HeaderPage

@ProformaInvoice_Items
Scenario: Sales - Proforma Invoice - Items page
	Given Navigate to Proforma Invoice Items - SO_ProformaInvoice_ItemsPage
	Then Validate Proforma Invoice Items Page with Invalid data - SO_ProformaInvoice_ItemsPage
	
@ProformaInvoice_Summary
Scenario: Sales - Proforma Invoice - Summary page
	Given Navigate to Proforma Invoice Summary - SO_ProformaInvoice_SummaryPage
	Then Validate Proforma Invoice Summary Page with Invalid data - SO_ProformaInvoice_SummaryPage

@Sales_Return
Scenario: Sales - Sales Return - Search page
	Given Navigate to Sales Return - SalesReturn_SearchPage
	Then Validate Sales Return Page with Invalid data - SalesReturn_SearchPage
	
@Sales_Return_Create
Scenario: Sales - Sales Return - Create page
	Given Create Sales Return - SalesReturn_CreatePage
	Then Validate Create Sales Return Page with Invalid data - SalesReturn_CreatePage

@Sales_Return_ReturnItems
Scenario: Sales - Sales Return - ReturnItems page
	Given Navigate to Sales Return - SalesReturn_ReturnItemsPage
	Then Validate Sales Return Page with Invalid data - SalesReturn_ReturnItemsPage
	