Feature: Crest Release - 3.4 - TC03 - Local Purchase Order (for Stock Items ) - Purchase Order with Two level Approval, GST (Inter) , Discount, Shipment charges, Other Tax charges (defined in Tax configurator module), Multiple Shipments, Supplier Invoice and Supplier Payment (Partial payment for one Invoice)



Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@TC03-01
Scenario: Create PO with Two level Approval - TC03 

	Given Create PO - TC_Three 
	Then Add Shipping Details - TC_Three
	Then Add Order Items - TC_Three
	Then Add Charges Before Creating PO - TC_Three
	Then Review & Create PO - TC_Three
	Then Edit Order with Line Item Discounts - TC_Three 
	Then Edit Order with Overall Discounts - TC_Three
	Then Add Charges After PO - TC_Three
	Then Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - TC_Three
	
@TC03-02	
Scenario: PO Approval
	Given Approve PO (Two level Approval) - TC_Three
	
	
@TC03-03
Scenario: Receive Inventory (Multiple Shipments) - TC03 

	Given  Receive Inventory for Shipment One - TC_Three
	Then  Receive Inventory for Shipment Two - TC_Three
	Then Receive Inventory for Shipment Three - TC_Three
	
@TC03-04 
Scenario: Invoice Posting (Multiple Invoice) - TC03

	Given Invoice Posting for Generated Invoices - TC_Three 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Three

	
@TC03-05
Scenario: Payments - TC03 

	Given Payments (Partial Payment for One Invoice and Full Payment for Remaining Invoices) - TC_Three		
	
@TC03-06
Scenario: Transactions Validations under Finance Module with Shipment ID- TC03

	Given Validate Accounting transactions with ShipmentID under Finance Module - TC_Three	
	
@TC03-07
Scenario: Transactions Validations under Finance Module with Invoice ID- TC03

	Given Validate Accounting transactions with InvoiceID under Finance Module - TC_Three
	
@TC03-08
Scenario: Transactions Validations under Finance Module with Payment ID- TC03

	Given Validate Accounting transactions with PaymentID under Finance Module - TC_Three

	
	