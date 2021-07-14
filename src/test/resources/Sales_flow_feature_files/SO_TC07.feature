Feature: Crest Release - 3.4 - SO_TC07 - Export Sales Order for Offline Payments for Product Type = Finished Goods - Sales Order with  Discount, Shipment charges, Other charges (defined in Tax configurator module) - All TAXES in BASE Currency.
Create two different shipments,  2 different Invoices, Make one payment at Exchange rate of X , and the next payment at Exchange rate of X+(X*5%)

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	

@SO_TC07-01
Scenario: Create an Exchange Rate - SO_TC07

	Given Configure Exchange Rate - SO_TC_Seven	


@SO_TC07-02
Scenario: Create SO with Offline Payment - SO_TC07 

	Given Create SO - SO_TC_Seven 
	Then Add Shipping Details - SO_TC_Seven
	Then Add Order Items - SO_TC_Seven
	Then Review & Create SO - SO_TC_Seven
	Then Edit Order with Line Item Discounts - SO_TC_Seven 
	Then Edit Order with Overall Discounts - SO_TC_Seven
	Then Add Charges - SO_TC_Seven
	Then Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - SO_TC_Seven
	
@SO_TC07-03
Scenario: SO Approval - SO_TC07

	Given Approve SO - SO_TC_Seven
	
@SO_TC07-04
Scenario: Create Shipment (Multiple Shipment) - SO_TC07 

	Given  Create Shipment One - SO_TC_Seven
	Then Create Shipment Two - SO_TC_Seven
	Then Create Shipment Three - SO_TC_Seven
	
@SO_TC07-05
Scenario: Invoice Posting (Multiple Invoice) - SO_TC07

	Given Invoice Posting for Generated Invoices - SO_TC_Seven 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - SO_TC_Seven
	
@SO_TC07-06
Scenario: Receipts - SO_TC07 

	Given Receipts (One Invoice with Exchange Rate of X) - SO_TC_Seven	
	
@SO_TC07-07
Scenario: Changing Exchange Rate plus Five percent - SO_TC07

	Given Reconfigure Exchange Rate - SO_TC_Seven
	
@SO_TC07-08
Scenario: Receipts - SO_TC07

	Given Receipts (Remaining Invoice with Exchange Rate of X plus Five Percent) - SO_TC_Seven
	
@SO_TC07-09
Scenario: Transactions Validations under Finance Module with Invoice ID- SO_TC07

	Given Validate Accounting transactions with InvoiceID under Finance Module - SO_TC_Seven
	
@SO_TC07-10
Scenario: Transactions Validations under Finance Module with Payment ID- SO_TC07

	Given Validate Accounting transactions with PaymentID under Finance Module - SO_TC_Seven
	
