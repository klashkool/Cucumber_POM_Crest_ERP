Feature: Crest Release - 3.4 - SO_TC10 - Domestic Sales Order for Offline Payments with Product Type = Service - Sale Order with GST (Intra) , Discount, Shipment charges, Other Charges (defined in Tax configurator module), Multiple Customer Invoice and Customer Payment (Single Payment)

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@SO_TC10-01	
Scenario: Create Service SO with Offline Payment - SO_TC10 

	Given Create SO - SO_TC_Ten 
	Then Add Shipping Details - SO_TC_Ten
	Then Add Order Items - SO_TC_Ten
	Then Review & Create SO - SO_TC_Ten	
	Then Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - SO_TC_Ten
	
@SO_TC10-02	
Scenario: SO Approval - SO_TC10

	Given Approve SO - SO_TC_Ten
	
@SO_TC10-03
Scenario: Generate Service Items Invoice

	Given Generate Service Items Invoice - SO_TC_Ten	
	
@SO_TC10-04 
Scenario: Invoice Posting (Multiple Invoice) - SO_TC10

	Given Invoice Posting for Generated Invoices - SO_TC_Ten 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - SO_TC_Ten
	
@SO_TC10-05
Scenario: Receipts - SO_TC10 

	Given Receipts (Partial Receipt for One Invoice and Full Receipt for Remaining Invoices) - SO_TC_Ten	
	
@SO_TC10-06
Scenario: Transactions Validations under Finance Module with Invoice ID- SO_TC10

	Given Validate Accounting transactions with InvoiceID under Finance Module - SO_TC_Ten
	
@SO_TC10-07
Scenario: Transactions Validations under Finance Module with Payment ID- SO_TC10

	Given Validate Accounting transactions with PaymentID under Finance Module - SO_TC_Ten
	
	