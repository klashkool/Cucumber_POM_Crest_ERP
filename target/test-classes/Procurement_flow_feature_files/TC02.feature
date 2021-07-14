Feature: Crest Release - 3.4 - TC02 - Local Purchase Order with Pay Term(for Stock Items ) - Purchase Order with Single Level Approval,  GST (Intra) , Charges (As defined in Tax configurator module), Multiple Facility, Multiple Shipment, Supplier Invoice with TDS, One full payment / Invoice
 


Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@TC02-01
Scenario: Create & Approve PO with Single Level Approval - TC02 

	Given Create PO - TC_Two
	Then Add Shipping Details - TC_Two
	Then Add Order Items - TC_Two	
	Then Review & Create PO - TC_Two
	Then Add Charges - TC_Two
	Then Validating SubTotal/OtherCharges_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - TC_Two
	
@TC02-02	
Scenario: PO Approval - TC02

	Given Approve PO (Single level Approval) - TC_Two		
	
@TC02-03
Scenario: Receive Inventory (Multiple Shipments) - TC02 

	Given  Receive Inventory for Shipment One - TC_Two
	Then  Receive Inventory for Shipment Two - TC_Two
	Then Receive Inventory for Shipment Three - TC_Two
	
@TC02-04 
Scenario: Invoice Posting (Multiple Invoice) - TC02 

	Given Invoice Posting for Generated Invoices - TC_Two 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Two
		
@TC02-05
Scenario: Payments - TC02 

	Given Payments (Single Payment) - TC_Two		

@TC02-06
Scenario: Transactions Validations under Finance Module with Shipment ID- TC02

	Given Validate Accounting transactions with ShipmentID under Finance Module - TC_Two	
	
@TC02-07
Scenario: Transactions Validations under Finance Module with Invoice ID- TC02

	Given Validate Accounting transactions with InvoiceID under Finance Module - TC_Two
	
@TC02-08
Scenario: Transactions Validations under Finance Module with Payment ID- TC02

	Given Validate Accounting transactions with PaymentID under Finance Module - TC_Two

	
