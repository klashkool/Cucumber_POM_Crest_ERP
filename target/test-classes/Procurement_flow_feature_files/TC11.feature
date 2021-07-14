Feature: Crest Release - 3.4 - TC11 - Import purhcase Order (for Stock Items) with Additional Cost Loading - Purchase Order with line level discounts and document level discounts - All TAXES in Foreign Currency. 


Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
	
@TC11-01
Scenario: Create an Exchange Rate - TC11

	Given Configure Exchange Rate - TC_Eleven
	
@TC11-02
Scenario: Create PO with Two level Approval - TC11

	Given Create PO - TC_Eleven 
	Then Add Shipping Details - TC_Eleven
	Then Add Order Items - TC_Eleven
	Then Review & Create PO - TC_Eleven
	Then Edit Order with Line Item Discounts - TC_Eleven 
	Then Edit Order with Overall Discounts - TC_Eleven
	Then Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - TC_Eleven
	
@TC11-03	
Scenario: PO Approval - TC11
	Given Approve PO (Two level Approval) - TC_Eleven
	
	
@TC11-04
Scenario: Receive Inventory (Single Shipments) - TC11

	Given  Receive Inventory (Single Shipment) - TC_Eleven 
	
@TC11-05 
Scenario: Invoice Posting (Single Invoice) - TC11

	Given Invoice Posting for Generated Invoices - TC_Eleven 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Eleven

	
@TC11-06
Scenario: Payments - TC11 

	Given Payments (Single Payment) - TC_Eleven		
	
@AC_TC11_01
Scenario: Create & Post Additional Cost Invoice - AC_TC11

	Given Create and Post Additional Cost Invoice - AC_TC_Eleven
	Then Validate Revised unit Cost - AC_TC_Eleven
	
@AC_TC11_02
Scenario: Additional Cost Invoice Validation under Finance Module - AC_TC11

	Given Validate Additional Cost Invoice under Financials - AC_TC_Eleven
	
@StockIssue_01
Scenario: Create Stock Issue for the balance Qty - AC_TC11

	Given Create Stock Issue - AC_TC_Eleven
	