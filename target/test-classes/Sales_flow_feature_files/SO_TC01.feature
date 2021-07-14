Feature: Crest Release - 3.3 - SO_TC01 - Domestic Sales Order for Offline Payments from Quotes for Product Type = Finished Goods - Sales Quotes >  Converstion to Sales Order > GST (Intra), Single Shipment, Customer Invoice & One single Payment

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@SO_TC01-01 
Scenario: Create Sales Order From Quotes - SO_TC01

	Given Create New Sales Quote - SO_TC_One
	Then Add Products with Discounts - SO_TC_One
	Then Validate Quotes SubTotal & GrandTotal - SO_TC_One
	Then Convert Sales Quotes into Sales Order - SO_TC_One
	Then Review and Create SO - SO_TC_One
	Then Validating SubTotal/Tax/GrandTotal/TaxConsolidated Btn/TaxBreakup Table - SO_TC_One
	
@SO_TC01-02	
Scenario: SO Approval - SO_TC01

	Given Approve SO - SO_TC_One
	
@SO_TC01-03 
Scenario: Create Shipment - SO_TC01 

	Given  Create Shipment (Single Shipment) - SO_TC_One 
	
@SO_TC01-04 
Scenario: Invoice Posting - SO_TC01 

	Given Invoice Posting (Single Invoice) - SO_TC_One 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - SO_TC_One
	
@SO_TC01-05
Scenario: Receipts - SO_TC01 

	Given Receipts (Single Receipt) - SO_TC_One	
	
@SO_TC01-06
Scenario: Transactions Validations under Finance Module with Payment ID- SO_TC01

	Given Validate Accounting transactions with PaymentID under Finance Module - SO_TC_One
	
