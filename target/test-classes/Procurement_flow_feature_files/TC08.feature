Feature: Crest Release - 3.4 - TC08 - PR to PO (for Non-Stock Items) Local - Purchase Order with Two level Approval, GST (Inter) , Discount, Shipment charges, Other charges (defined in Tax configurator module).Multiple shipments, Advance Payment / Invoice 


Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
	
@TC08-01 
Scenario: Creating Vendor Pre Payment(50% of Invoice Value) - TC08

	Given Create Vendor Prepayment - TC_Eight
	
@TC08-02 
Scenario: Create PO from Requirements with Multi level Approval for Non Stock Items - TC08

	Given Create PO Requirements - TC_Eight
	Then Create PO RFQ - TC_Eight 
	Then Create PO Quote - TC_Eight 
	Then Review & Create PO - TC_Eight 
	Then Edit Order with Line Item Discounts - TC_Eight
	Then Edit Order with Overall Discounts - TC_Eight
	Then Add Charges - TC_Eight
	Then Validating SubTotal/OtherCharges_PopupBtn/Tax_Consolidated_PopupBtn/GrandTotal/ThirdPartyTax Table/TaxBreakup Table - TC_Eight
	
@TC08-03	
Scenario: PO Approval- TC08

	Given Approve PO (Two level Approval) - TC_Eight	
	
@TC08-04
Scenario: Receive Adhoc (Multiple Shipments) - TC08

	Given  Receive Adhoc for Shipment One - TC_Eight
	Then  Receive Adhoc for Shipment Two - TC_Eight
	Then Receive Adhoc for Shipment Three - TC_Eight
	
@TC08-05
Scenario: Invoice Posting (Multiple Invoice) - TC08

	Given Invoice Posting for Generated Invoices - TC_Eight 
	Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - TC_Eight	
	
@TC08-06
Scenario: Payments - TC08

	Given Payments (Advance adjusted against the first invoice (part) and the balance need to be booked as one payment. Receive full payment against the second invoice) - TC_Eight	
	
@TC08-07
Scenario: Transactions Validations under Finance Module with Invoice ID- TC08

	Given Validate Accounting transactions with InvoiceID under Finance Module - TC_Eight
	
@TC08-08
Scenario: Transactions Validations under Finance Module with Payment ID- TC08

	Given Validate Accounting transactions with PaymentID under Finance Module - TC_Eight
	