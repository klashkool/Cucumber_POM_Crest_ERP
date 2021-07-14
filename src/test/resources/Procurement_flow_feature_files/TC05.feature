Feature: Crest Release - 3.3 - TC05 - Import purhcase Order (for Stock Items) - Purchase Order with  Discount, Shipment charges, Other Charges (defined in Tax configurator module) - All TAXES in Foreign Currency. Advance payment for 50% and on receipt of the final invoice clearing the balance amount



Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
	
@TC05-01
Scenario: Create an Exchange Rate - TC05

	Given Configure Exchange Rate - TC_Five
	
	
@TC05-02 
Scenario: Creating Vendor Pre Payment(50% of Invoice Value) - TC05

	Given Create Vendor Prepayment - TC_Five
	
@TC05-03
Scenario: Create PO with Two level Approval - TC05

	Given Create PO - TC_Five 
	Then Add Shipping Details - TC_Five
	Then Add Order Items - TC_Five
	Then Add Charges Before Creating PO - TC_Five
	Then Review & Create PO - TC_Five
	Then Edit Order with Line Item Discounts - TC_Five 
	Then Edit Order with Overall Discounts - TC_Five
	Then Add Charges After PO - TC_Five
	Then Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - TC_Five
	
@TC05-04	
Scenario: PO Approval - TC05
	Given Approve PO (Two level Approval) - TC_Five
	
	
@TC05-05
Scenario: Receive Inventory (Multiple Shipments) - TC05

	Given  Receive Inventory for Shipment One - TC_Five
	Then  Receive Inventory for Shipment Two - TC_Five
	Then Receive Inventory for Shipment Three - TC_Five
	
@TC05-06 
Scenario: Invoice Posting (Multiple Invoice) - TC05

	Given Invoice Posting for Generated Invoices - TC_Five 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Five

	
@TC05-07
Scenario: Payments - TC05 

	Given Payments (Full Payment for Remaining Invoices after Adjustments) - TC_Five		
	
@TC05-08
Scenario: Transactions Validations under Finance Module with Shipment ID- TC05

	Given Validate Accounting transactions with ShipmentID under Finance Module - TC_Five	
	
@TC05-09
Scenario: Transactions Validations under Finance Module with Invoice ID- TC05

	Given Validate Accounting transactions with InvoiceID under Finance Module - TC_Five
	
@TC05-10
Scenario: Transactions Validations under Finance Module with Payment ID- TC05

	Given Validate Accounting transactions with PaymentID under Finance Module - TC_Five

	
	
	

	