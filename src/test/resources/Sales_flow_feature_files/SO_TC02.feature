Feature: Crest Release - 3.4 -SO_TC02 Domestic Sales Order with Billing Accounts from Quotes for Product Type = Finished Goods - Sales Quotes >  Converstion to Sales Order > GST (Intra), Multiple Shipment, Customer Invoice & Partial Payment, Verifying the Outstanding amount.

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	

@SO_TC02-01	
Scenario:  Capture Billing Acct Balance - SO_TC_Two

	Given Capture Billing Acct Balance - SO_TC_Two
	
@SO_TC02-02
Scenario: Create Sales Order From Quotes - SO_TC02

	Given Create New Sales Quote - SO_TC_Two
	Then Add Products with Discounts - SO_TC_Two
	Then Validate Quotes SubTotal & GrandTotal - SO_TC_Two
	Then Convert Sales Quotes into Sales Order - SO_TC_Two
	Then Review and Create SO - SO_TC_Two
	Then Validating SubTotal/Tax/GrandTotal/TaxConsolidated Btn/TaxBreakup Table - SO_TC_Two
	
@SO_TC02-03	
Scenario: SO Approval - SO_TC02

	Given Approve SO - SO_TC_Two
	
@SO_TC02-04
Scenario: Create Shipment (Multiple Shipment) - SO_TC02 

	Given  Create Shipment One - SO_TC_Two
	Then Create Shipment Two - SO_TC_Two
	Then Create Shipment Three - SO_TC_Two
	
@SO_TC02-05
Scenario: Invoice Posting (Multiple Invoice) - SO_TC02

	Given Invoice Posting for Generated Invoices - SO_TC_Two 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - SO_TC_Two
	
@SO_TC02-06
Scenario: Receipts - SO_TC02 

	Given Receipts (Partial Receipt for One Invoice and Full Receipt for Remaining Invoices) - SO_TC_Two	
	
@SO_TC02-07	
Scenario: Validate Billing Acct Balance - SO_TC_Two

	Given Validate Billing Acct Balance - SO_TC_Two
	
@SO_TC02-08
Scenario: Transactions Validations under Finance Module with Invoice ID- SO_TC02

	Given Validate Accounting transactions with InvoiceID under Finance Module - SO_TC_Two
	
@SO_TC02-9
Scenario: Transactions Validations under Finance Module with Payment ID- SO_TC02

	Given Validate Accounting transactions with PaymentID under Finance Module - SO_TC_Two
	
