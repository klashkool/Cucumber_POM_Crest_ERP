Feature: Negative Testing for AccountsPayable

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 	
	
@Proc_AP_Neg01	
Scenario: PurchaseInvoice - PI_SearchPage

	Given Navigate to Purchase Invoice Search page - PI_SearchPage
	Then Validate with Invalid Data - PI_SearchPage
	
@Proc_AP_Neg02	
Scenario: PurchaseInvoice - PI_CreatePage

	Given Navigate to Purchase Invoice Create page - PI_CreatePage
	Then Validate with Invalid Data - PI_CreatePage
	
@Proc_AP_Neg03	
Scenario: PurchaseInvoice - PI_ItemsPage

	Given Navigate to Purchase Invoice Items page - PI_ItemsPage
	Then Validate with Invalid Data - PI_ItemsPage
	
@Proc_AP_Neg04	
Scenario: PurchaseInvoice - PI_ApplicationPage

	Given Navigate to Purchase Invoice Application page - PI_ApplicationPage
	Then Validate with Invalid Data - PI_ApplicationPage
	
@Proc_AP_Neg05
Scenario: CreditMemo - CreditMemo_SearchPage

	Given Navigate to Credit Memo Search page - CreditMemo_SearchPage
	Then Validate with Invalid Data - CreditMemo_SearchPage
	
@Proc_AP_Neg06
Scenario: CreditMemo - CreditMemo_CreatePage

	Given Navigate to Credit Memo Create page - CreditMemo_CreatePage
	Then Validate with Invalid Data - CreditMemo_CreatePage

@Proc_AP_Neg07
Scenario: CreditMemo - CreditMemo_ItemsPage

	Given Navigate to Credit Memo Items page - CreditMemo_ItemsPage
	Then Validate with Invalid Data - CreditMemo_ItemsPage
	
@Proc_AP_Neg08
Scenario: SupplierAdjustment - SupplierAdjustment_SearchPage

	Given Navigate to Supplier Adjustment Search page - SupplierAdjustment_SearchPage
	Then Validate with Invalid Data - SupplierAdjustment_SearchPage
	
@Proc_AP_Neg09
Scenario: SupplierAdjustment - SupplierAdjustment_CreatePage

	Given Navigate to Supplier Adjustment Create page - SupplierAdjustment_CreatePage
	Then Validate with Invalid Data - SupplierAdjustment_CreatePage
	
@Proc_AP_Neg10
Scenario: Payments - Payments_SearchPage

	Given Navigate to Payment Search page - Payment_SearchPage
	Then Validate with Invalid Data - Payment_SearchPage
	
@Proc_AP_Neg11
Scenario: Payments - VendorPayments_CreatePage

	Given Navigate to Vendor Payment Create page - VendorPayments_CreatePage
	Then Validate with Invalid Data - VendorPayments_CreatePage
	
@Proc_AP_Neg12
Scenario: Payments - Payments_ApplicationPage

	Given Navigate to Payments Application page - Payments_ApplicationPage
	Then Validate with Invalid Data - Payments_ApplicationPage

	
