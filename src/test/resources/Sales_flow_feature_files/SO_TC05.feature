Feature: Crest Release - 3.4 - SO_TC05 - Domestic Sales Order for Offline Payments for Product Type = Finished Goods - Sale Order with GST (Inter) , Discount, Shipment charges, Other Charges (defined in Tax configurator module), Multiple Shipments, Customer Invoice and Customer Payment (Partial payment for 1 invoice and full payment for other Invoices )

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@SO_TC05-01	
Scenario: Create SO with Offline Payment - SO_TC05 

	Given Create SO - SO_TC_Five 
	Then Add Shipping Details - SO_TC_Five
	Then Add Order Items - SO_TC_Five
	Then Review & Create SO - SO_TC_Five
	Then Edit Order with Line Item Discounts - SO_TC_Five 
	Then Edit Order with Overall Discounts - SO_TC_Five
	Then Add Charges - SO_TC_Five
	Then Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - SO_TC_Five
	
@SO_TC05-02	
Scenario: SO Approval - SO_TC05

	Given Approve SO - SO_TC_Five
	
@SO_TC05-03 
Scenario: Create Shipment (Multiple Shipment) - SO_TC05 

	Given  Create Shipment One - SO_TC_Five
	Then Create Shipment Two - SO_TC_Five
	Then Create Shipment Three - SO_TC_Five
	
@SO_TC05-04 
Scenario: Invoice Posting (Multiple Invoice) - SO_TC05

	Given Invoice Posting for Generated Invoices - SO_TC_Five 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - SO_TC_Five
	
@SO_TC05-05
Scenario: Receipts - SO_TC05 

	Given Receipts (Partial Receipt for One Invoice and Full Receipt for Remaining Invoices) - SO_TC_Five	
	
@SO_TC05-06
Scenario: Transactions Validations under Finance Module with Invoice ID- SO_TC05

	Given Validate Accounting transactions with InvoiceID under Finance Module - SO_TC_Five
	
@SO_TC05-07
Scenario: Transactions Validations under Finance Module with Payment ID- SO_TC05

	Given Validate Accounting transactions with PaymentID under Finance Module - SO_TC_Five
	
