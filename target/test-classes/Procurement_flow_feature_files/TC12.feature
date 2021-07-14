Feature: Crest Release - 3.4 - TC12 - Domestic Purchase Order with Line level discount (for Stock Items ) - Additional Cost Loading after partial Sales shipment of the items 

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@TC12-01
Scenario: Create PO with Two level Approval - TC12 

	Given Create PO - TC_Twelve 
	Then Add Shipping Details - TC_Twelve
	Then Add Order Items - TC_Twelve
	Then Review & Create PO - TC_Twelve
	Then Edit Order with Line Item Discounts - TC_Twelve 
	Then Edit Order with Overall Discounts - TC_Twelve	
	Then Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - TC_Twelve
	

@TC12-02	
Scenario: PO Approval - TC12

	Given Approve PO (Single level Approval) - TC_Twelve
	
@TC12-03 
Scenario: Receive Inventory - TC12 

	Given  Receive Inventory (Single Shipment) - TC_Twelve 
	
@TC12-04 
Scenario: Invoice Posting - TC12 

	Given Invoice Posting (Single Invoice) - TC_Twelve 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Twelve
	
@TC12-05
Scenario: Payments - TC12 

	Given Payments (Single Payment) - TC_Twelve	
	
@SO_TC12-06
Scenario: Create SO with Offline Payment - SO_TC12 

	Given Create SO - SO_TC_Twelve 
	Then Add Shipping Details - SO_TC_Twelve
	Then Add Order Items - SO_TC_Twelve
	Then Review & Create SO - SO_TC_Twelve
	Then Edit Order with Line Item Discounts - SO_TC_Twelve 
	Then Edit Order with Overall Discounts - SO_TC_Twelve
	Then Add Charges - SO_TC_Twelve
	Then Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - SO_TC_Twelve
	
@SO_TC12-07
Scenario: SO Approval - SO_TC12

	Given Approve SO - SO_TC_Twelve
	
@SO_TC12-08
Scenario: Create Shipment (Partial Qty) - SO_TC12 

	Given  Create Shipment (Single Shipment) - SO_TC_Twelve 

@AC_TC12_01
Scenario: Create & Post Additional Cost Invoice - AC_TC12

	Given Create and Post Additional Cost Invoice - AC_TC_Twelve
	Then Validate Revised unit Cost - AC_TC_Twelve
	
@AC_TC12_02
Scenario: Additional Cost Invoice Validation under Finance Module - AC_TC12

	Given Validate Additional Cost Invoice under Financials - AC_TC_Twelve
	
@StockIssue_01
Scenario: Create Stock Issue for the balance Qty - AC_TC12

	Given Create Stock Issue - AC_TC_Twelve
	
	