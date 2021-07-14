Feature: Negative Testing for Procurement

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 	

@Proc_Neg01	
Scenario: Procurements - Requirements Search page
	
	Given Navigate to Requirements - PO_Req_SearchPage
	Then Validate with Invalid Date Range - PO_Req_SearchPage
	
@Proc_Neg02	
Scenario: Procurements - Requirements Create Page
	
	Given Create Requirement - PO_Req_CreatePage
	Then Validate Create Requirement Page with Invalid data - PO_Req_CreatePage
	
@Proc_Neg03	
Scenario: Procurements - Requirements Edit Page

	Given Create Requirement - PO_ReqEditPage
	Then Validate Edit Requirement Page with Invalid data - PO_ReqEditPage
	
@Proc_Neg04	
Scenario: Procurements - Requirements (Postive Flow)

	Given Create and Approve Requirement - PO_ReqEditPage

@Proc_Neg05
Scenario: Procurements - RFQ Search Page

	Given Navigate to RFQ - PO_RFQ_SearchPage
	Then Validate with Invalid Date Range - PO_RFQ_SearchPage
	
@Proc_Neg06
Scenario: Procurements - RFQ Create Page

	Given Create RFQ - PO_RFQ_CreatePage
	Then Validate RFQ Create Page with Invalid data - PO_RFQ_CreatePage

@Proc_Neg07
Scenario: Procurements - RFQ Item Page (Manual RFQ)

	Given Create Manual RFQ - PO_RFQ_CreatePage
	Then Validate RFQ Items Page with Invalid Data - PO_RFQ_ItemsPage


@Proc_Neg08
Scenario: Procurements - Quotes Search Page

	Given Navigate to Quotes - PO_Quotes_SearchPage
	Then Validate with Invalid Date Range - PO_Quotes_SearchPage
	
@Proc_Neg09
Scenario: Procurements - Quotes Create Page

	Given Create Quotes - PO_Quotes_CreatePage
	Then Validate Quotes Create Page with Invalid data - PO_Quotes_CreatePage
	
@Proc_Neg10
Scenario: Procurements - Quotes Update Quote Status Page

	Given Create Quotes - PO_Quotes_UpdateQuoteStatusPage
	Then Validate Update Quote Status Page with Invalid data - PO_Quotes_UpdateQuoteStatusPage
	
@Proc_Neg11
Scenario: Procurements - Purchase Order Search Page

	Given Navigate to Purchase Order - PO_PurchaseOrder_SearchPage
	Then Validate with Invalid Date Range - PO_PurchaseOrder_SearchPage
	
@Proc_Neg12
Scenario: Procurements - Purchase Order StartOrder Page

	Given Navigate to Purchase Order - PO_PurchaseOrder_StartOrderPage
	Then Validate StartOrder Page with Invalid data - PO_PurchaseOrder_StartOrderPage
	
@Proc_Neg13
Scenario: Procurements - Purchase Order ShippingDetails Page

	Given Navigate to Purchase Order - PO_PurchaseOrder_ShippingDetailsPage
	Then Validate Shipping Details Page with Invalid data - PO_PurchaseOrder_ShippingDetailsPage
	
@Proc_Neg14
Scenario: Procurements - Purchase Order OrderItems Page

	Given Navigate to Purchase Order - PO_PurchaseOrder_OrderItemsPage
	Then Validate Order Items Page with Invalid data - PO_PurchaseOrder_OrderItemsPage
	
@Proc_Neg15
Scenario: Procurements - Purchase Order Review&CreatePO Page

	Given Navigate to Purchase Order - PO_PurchaseOrder_Review&CreatePOPage
	Then Validate Review&CreatePO Page with Invalid data - PO_PurchaseOrder_Review&CreatePOPage
	
@Proc_Neg16
Scenario: Procurements - Purchase Order PO_View Page

	Given Navigate to Purchase Order - PO_PurchaseOrder_ViewPage
	Then Validate PO_View Page with Invalid data - PO_PurchaseOrder_ViewPage
	
@Proc_Neg17
Scenario: Procurements - Purchase Order PO_Edit Page

	Given Navigate to Purchase Order - PO_PurchaseOrder_EditPage
	Then Validate PO_View Page with Invalid data - PO_PurchaseOrder_EditPage
	
@Proc_Neg18
Scenario: Procurements - Purchase Order - WFC Approval - PO_PurchaseOrder_ViewPage

	Given Create Purchase Order with WFC configurations - PO_PurchaseOrder_ViewPage
	Then Approve Validation by Purchase VP - PO_PurchaseOrder_ViewPage
	Then Return Validation by Pur CEO - PO_PurchaseOrder_ViewPage
	Then Reject Validation by Pur VP - PO_PurchaseOrder_ViewPage	
	
@Proc_Neg19
Scenario: Procurements - Purchase Order - Cancel and Add line items and Validating all the values -PO_PurchaseOrder_EditPage

	Given Create Purchase Order - PO_PurchaseOrder_EditPage
	Then Validate Values after cancelling the Line Items - PO_PurchaseOrder_EditPage
	Then Validate Values after Adding a line Item - PO_PurchaseOrder_EditPage

@Proc_Neg20
Scenario: Procurement - Create As New Order

	Given Navigate to Created purchase Order - PO_ViewPage
	Then Create as New Order - PO_ViewPage
	Then Validate Values after Creating a New Order - PO ViewPage

@Proc_Neg21
Scenario: Procurements - Purchase Order - Approve PO

	Given Approve the Order - PO_PurchaseOrder_ViewPage
	
@Proc_Neg22
Scenario: Procurement - Purchase Return - PR_SearchPage

	Given Navigate to Purchase Return Search Page - PR_SearchPage
	Then Validate with Invalid date - PR_SearchPage
	
@Proc_Neg23
Scenario: Procurement - Purchase Return - PR_CreatePage

	Given Navigate to Purchase Return Create Page - PR_CreatePage
	Then Validate with Invalid date - PR_CreatePage
	
@Proc_Neg24
Scenario: Procurement - Purchase Return - PurchaseReturnPage

	Given Receive Inventory to Create Return - PurchaseReturnPage
	Then Create Return - PurchaseReturnPage
	Then Validate with Invalid data - PurchaseReturnPage

@Proc_Neg25
Scenario: Procurement - Create As New Order

	Given Navigate to Created purchase Order - PO_ViewPage
	Then Create as New Order - PO_ViewPage
	Then Validate Values after Creating a New Order - PO ViewPage

@Proc_Neg21
Scenario: Procurements - Purchase Order - Approve PO

	Given Approve the Order - PO_PurchaseOrder_ViewPage

	