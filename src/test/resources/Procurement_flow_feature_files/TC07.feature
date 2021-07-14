Feature: Crest Release - 3.4 - TC07 - PR to PO (for Stock Items) Local - Purchase Order with Two level Approval, GST (Inter) , Discount, Shipment charges, Other charges (defined in Tax configurator module).Multiple shipments, Single Payment / Invoice 


Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@TC07-01 
Scenario: Create PO from Requirements - TC07 

	Given Create PO Requirements - TC_Seven
	Then Create PO RFQ - TC_Seven 
	Then Create PO Quote - TC_Seven
	Then Add Charges Before Creating PO - TC_Seven 
	Then Review & Create PO - TC_Seven 
	Then Edit Order with Line Item Discounts - TC_Seven 
	Then Edit Order with Overall Discounts - TC_Seven
	Then Add Charges After PO - TC_Seven
	Then Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - TC_Seven
	
@TC07-02	
Scenario: PO Approval- TC07

	Given Approve PO (Two level Approval) - TC_Seven	
	
@TC07-03
Scenario: Receive Inventory (Multiple Shipments) - TC07 

	Given  Receive Inventory for Shipment One - TC_Seven
	Then  Receive Inventory for Shipment Two - TC_Seven
	Then Receive Inventory for Shipment Three - TC_Seven
	
@TC07-04 
Scenario: Invoice Posting (Multiple Invoice) - TC07

	Given Invoice Posting for Generated Invoices - TC_Seven 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Seven

	
@TC07-05
Scenario: Payments - TC07

	Given Payments (Single Payment) - TC_Seven		
	
@TC07-06
Scenario: Transactions Validations under Finance Module with Shipment ID- TC07

	Given Validate Accounting transactions with ShipmentID under Finance Module - TC_Seven	
	
@TC07-07
Scenario: Transactions Validations under Finance Module with Invoice ID- TC07

	Given Validate Accounting transactions with InvoiceID under Finance Module - TC_Seven
	
@TC07-08
Scenario: Transactions Validations under Finance Module with Payment ID- TC07

	Given Validate Accounting transactions with PaymentID under Finance Module - TC_Seven

		