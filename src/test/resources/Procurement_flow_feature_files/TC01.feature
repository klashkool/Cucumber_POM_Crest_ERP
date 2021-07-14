Feature: Crest Release - 3.4 - TC01 - Local Purchase Order (for Stock Items ) - Purchase Order with Single level approval , GST ( Intra ), Single Shipment, Supplier Invoice & Single Payment



Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@TC01-01
Scenario: Create PO - TC01

	Given Create PO - TC_One 
	Then Add Shipping Details - TC_One
	Then Add Order Items - TC_One
	Then Review & Create PO - TC_One
	Then Validating SubTotal/Tax/GrandTotal/TaxConsolidated Btn/TaxBreakup Table  - TC_One

@TC01-02	
Scenario: PO Approval - TC01

	Given Approve PO (Single level Approval) - TC_One
	
@TC01-03 
Scenario: Receive Inventory - TC01 

	Given  Receive Inventory (Single Shipment) - TC_One 
	
@TC01-04 
Scenario: Invoice Posting - TC01 

	Given Invoice Posting (Single Invoice) - TC_One 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_One
	
@TC01-05
Scenario: Payments - TC01 

	Given Payments (Single Payment) - TC_One	
	
@TC01-06
Scenario: Transactions Validations under Finance Module with Shipment ID- TC01

	Given Validate Accounting transactions with ShipmentID under Finance Module - TC_One	
	
@TC01-07
Scenario: Transactions Validations under Finance Module with Invoice ID- TC01

	Given Validate Accounting transactions with InvoiceID under Finance Module - TC_One
	
@TC01-08
Scenario: Transactions Validations under Finance Module with Payment ID- TC01

	Given Validate Accounting transactions with PaymentID under Finance Module - TC_One

#@TC01-07	
#Scenario: Download and validate reports - TC01
#
#Given Validate Stock Receipt Report 
	
	
