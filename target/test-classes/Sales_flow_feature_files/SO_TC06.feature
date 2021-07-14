Feature: Crest Release - 3.4 - SO_TC06 - Export Sales Order for Offline Payments for Product Type = Finished Goods - Sale Order with Discount, Shipment charges, Other Charges (defined in Tax configurator module), Multiple Shipments, Customer Invoice and Customer Payment / All TAXES in Foreign Currency. Create a Customer Deposit for 50% and on receipt of the final invoice clear the balance amount

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	

@SO_TC06-01
Scenario: Create an Exchange Rate - SO_TC06

	Given Configure Exchange Rate - SO_TC_Six
	
	
@SO_TC06-02 
Scenario: Creating Customer Deposit (50% of Invoice Value) - SO_TC06

	Given Create Customer Deposit - SO_TC_Six


@SO_TC06-03
Scenario: Create SO with Offline Payment - SO_TC06 

	Given Create SO - SO_TC_Six 
	Then Add Shipping Details - SO_TC_Six
	Then Add Order Items - SO_TC_Six
	Then Review & Create SO - SO_TC_Six
	Then Edit Order with Line Item Discounts - SO_TC_Six 
	Then Edit Order with Overall Discounts - SO_TC_Six
	Then Add Charges - SO_TC_Six
	Then Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - SO_TC_Six
	
@SO_TC06-04
Scenario: SO Approval - SO_TC06

	Given Approve SO - SO_TC_Six
	
@SO_TC06-05
Scenario: Create Shipment (Multiple Shipment) - SO_TC06 

	Given  Create Shipment One - SO_TC_Six
	Then Create Shipment Two - SO_TC_Six
	Then Create Shipment Three - SO_TC_Six
	
@SO_TC06-06
Scenario: Invoice Posting (Multiple Invoice) - SO_TC06

	Given Invoice Posting for Generated Invoices - SO_TC_Six 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - SO_TC_Six
	
@SO_TC06-07
Scenario: Receipts - SO_TC06 

	Given Receipts (Full Receipt for Remaining Invoices after Adjustments) - SO_TC_Six	
	
@SO_TC06-08
Scenario: Transactions Validations under Finance Module with Invoice ID- SO_TC06

	Given Validate Accounting transactions with InvoiceID under Finance Module - SO_TC_Six
	
@SO_TC06-09
Scenario: Transactions Validations under Finance Module with Payment ID- SO_TC06

	Given Validate Accounting transactions with PaymentID under Finance Module - SO_TC_Six
	
