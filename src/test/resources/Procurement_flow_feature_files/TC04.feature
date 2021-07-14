Feature: Crest Release - 3.4 - TC04 - Local Purchase order (for Non-Stock Items) - Purchase Order with Single level approval , GST ( Intra ), Single Shipment, Supplier Invoice & One single Payment



Background: Login to the Application
Given Launch the Application
Then Login with valid Credentials


@TC04-01
Scenario: Create PO with Single level Approval for Non Stock Items - TC04

	Given Create PO with Non Stock Items - TC_Four 
	Then Add Shipping Details - TC_Four
	Then Add Order Items - TC_Four
	Then Review & Create PO - TC_Four
	Then Validating SubTotal/Tax/GrandTotal/TaxConsolidated Btn/TaxBreakup Table  - TC_Four	
	
@TC04-02	
Scenario: PO Approval - TC01

	Given Approve PO (Single level Approval) - TC_Four
	
@TC04-03
Scenario: Receive Non Stock Items - TC04	
	
	Given Receive Non Stock Items(Single Shipment) - TC_Four
	
@TC04-04
Scenario: Invoice Posting - TC04 

	Given Invoice Posting (Single Invoice) - TC_Four 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Four
	
@TC04-05
Scenario: Payments - TC04 

	Given Payments (Single Payment) - TC_Four
	
@TC04-06
Scenario: Transactions Validations under Finance Module with Invoice ID- TC04

	Given Validate Accounting transactions with InvoiceID under Finance Module - TC_Four
	
@TC04-07
Scenario: Transactions Validations under Finance Module with Payment ID- TC04

	Given Validate Accounting transactions with PaymentID under Finance Module - TC_Four

	
	