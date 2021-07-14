Feature: Crest Release - 3.4 - SO_TC11 - Domestic Sales Order for Offline Payments for Product Type = Service Using Inventory - Sale Order with GST (Inter) , Discount, Shipment charges, Other Charges (defined in Tax configurator module), Multiple Shipments, Customer Invoice and Customer Payment (Partial payment for 1 invoice and full payment for other Invoices )

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@SO_TC11-01	
Scenario: Create SO with Offline Payment - SO_TC11 

	Given Create SO - SO_TC_Eleven 
	Then Add Shipping Details - SO_TC_Eleven
	Then Add Order Items - SO_TC_Eleven
	Then Review & Create SO - SO_TC_Eleven	
	Then Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - SO_TC_Eleven
	
@SO_TC11-02	
Scenario: SO Approval - SO_TC11

	Given Approve SO - SO_TC_Eleven
	
@SO_TC11-03 
Scenario: Create Shipment (Multiple Shipment) - SO_TC11 

	Given  Create Shipment One - SO_TC_Eleven
	Then Create Shipment Two - SO_TC_Eleven
	Then Create Shipment Three - SO_TC_Eleven
	
@SO_TC11-04 
Scenario: Invoice Posting (Multiple Invoice) - SO_TC11

	Given Invoice Posting for Generated Invoices - SO_TC_Eleven 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - SO_TC_Eleven
	
@SO_TC11-05
Scenario: Receipts - SO_TC11 

	Given Receipts (Full Receipt) - SO_TC_Eleven	
	
@SO_TC11-06
Scenario: Transactions Validations under Finance Module with Invoice ID- SO_TC11

	Given Validate Accounting transactions with InvoiceID under Finance Module - SO_TC_Eleven
	
@SO_TC11-07
Scenario: Transactions Validations under Finance Module with Payment ID- SO_TC11

	Given Validate Accounting transactions with PaymentID under Finance Module - SO_TC_Eleven
	
