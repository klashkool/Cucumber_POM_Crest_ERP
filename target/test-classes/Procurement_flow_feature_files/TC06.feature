Feature: Crest Release - 3.4 - TC06 - Import purhcase Order (for Stock Items) - Purchase Order with  Discount, Shipment charges, Other charges (defined in Tax configurator module) - All TAXES in BASE Currency.
Create 2 different shipments, Receive 2 different Invoices, Make one payment at Exchange rate of X , and the next payment at Exchange rate of X+(X*5%)


Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
	
@TC06-01
Scenario: Create a Exchange Rate - TC06

	Given Configure Exchange Rate - TC_Six
	
@TC06-02
Scenario: Create PO with Two level Approval - TC06

	Given Create PO - TC_Six
	Then Add Shipping Details - TC_Six
	Then Add Order Items - TC_Six
	Then Review & Create PO - TC_Six
	Then Edit Order with Line Item Discounts - TC_Six 
	Then Edit Order with Overall Discounts - TC_Six
	Then Add Charges - TC_Six
	Then Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - TC_Six
	
@TC06-03	
Scenario: PO Approval - TC06
	Given Approve PO (Two level Approval) - TC_Six
	
	
@TC06-04
Scenario: Receive Inventory (Multiple Shipments) - TC06

	Given  Receive Inventory for Shipment One - TC_Six
	Then  Receive Inventory for Shipment Two - TC_Six
	Then Receive Inventory for Shipment Three - TC_Six
	
@TC06-05 
Scenario: Invoice Posting (Multiple Invoice) - TC06

	Given Invoice Posting for Generated Invoices - TC_Six 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Six

	
@TC06-06
Scenario: Payments - TC06

	Given Payments (One Invoice with Exchange Rate of X ) - TC_Six		
	
@TC06-07
Scenario: Changing Exchange Rate plus Five percent - TC06

	Given Reconfigure Exchange Rate - TC_Six
	
@TC06-08
Scenario: Payments - TC06

	Given Payments (Remaining Invoice with Exchange Rate of X plus Five Percent) - TC_Six	

@TC06-09
Scenario: Transactions Validations under Finance Module with Shipment ID- TC06

	Given Validate Accounting transactions with ShipmentID under Finance Module - TC_Six	
	
@TC06-10
Scenario: Transactions Validations under Finance Module with Invoice ID- TC06

	Given Validate Accounting transactions with InvoiceID under Finance Module - TC_Six
	
@TC06-11
Scenario: Transactions Validations under Finance Module with Payment ID- TC06

	Given Validate Accounting transactions with PaymentID under Finance Module - TC_Six
	
	

	
	
	

	