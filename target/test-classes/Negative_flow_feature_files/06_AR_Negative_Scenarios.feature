Feature: Account Receivables Negative Scenarios

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 

@SalesInvoice_Search
Scenario: AR - SalesInvoice - Search page
	Given Navigate to SalesInvoice - AR_SalesInvoice_SearchPage
	Then Validate with Invalid Date Range - SO_SalesInvoice_SearchPage

@SalesInvoice_Create
Scenario: AR - SalesInvoice - Create page
	Given Create SalesInvoice - AR_SalesInvoice_CreatePage
	Then Validate Create SalesInvoice Page with Invalid data - SO_SalesInvoice_CreatePage
	
@SalesInvoice_Header
Scenario: AR - SalesInvoice - Header page
	Given Create SalesInvoice - AR_SalesInvoice_HeaderPage
	Then Validate SalesInvoice Header_Page with Invalid data - SO_SalesInvoice_HeaderPage
	
@SalesInvoice_Items
Scenario: AR - SalesInvoice - Items page
	Given Create SalesInvoice - AR_SalesInvoice_ItemsPage
	Then Validate_SalesInvoice_Items Page with Invalid data - SO_SalesInvoice_ItemsPage
	
@SalesInvoice_Applications
Scenario: AR - SalesInvoice - Applications page
	Given Create SalesInvoice - AR_SalesInvoice_ApplicationsPage
	Then Validate_SalesInvoice_Application Page with Invalid data - SO_SalesInvoice_ApplicationsPage
	
@SalesInvoice_Content
Scenario: AR - SalesInvoice - Content page
	Given Create SalesInvoice - AR_SalesInvoice_ContentPage
	Then Validate_SalesInvoice_Content Page with Invalid data - SO_SalesInvoice_ContentPage
	
@SalesInvoice_Overview
Scenario: AR - SalesInvoice - Overview page
	Given Create SalesInvoice - AR_SalesInvoice_OverviewPage
	Then Validate_SalesInvoice_Overview Page with Invalid data - SO_SalesInvoice_OverviewPage
	
@Receipts_Search
Scenario: AR - Receipts - Search page
	Given Navigate to Receipts - AR_Receipts_SearchPage
	Then Validate_Receipts_Search Page with Invalid data - SO_Receipts_SearchPage
	
@Receipts_Create
Scenario: AR - Receipts - Create page
	Given Create Receipts - AR_Receipts_CreatePage
	Then Validate_Receipts_Create Page with Invalid data - SO_Receipts_CreatePage
	
@Receipts_Header
Scenario: AR - Receipts - Header page
	Given Create Receipts - AR_Receipts_HeaderPage
	Then Validate_Receipts_Header Page with Invalid data - SO_Receipts_HeaderPage
	
@Receipts_Applications
Scenario: AR - Receipts - Applications page
	Given Create Receipts - AR_Receipts_ApplicationsPage
	Then Validate_Receipts_Applications Page with Invalid data - SO_Receipts_ApplicationsPage
	
@Receipts_Overview
Scenario: AR - Receipts - Overview page
	Given Create Receipts - AR_Receipts_OverviewPage
	Then Validate_Receipts_Overview Page with Invalid data - SO_Receipts_OverviewPage
	
@AR_PaymentGroup_Create
Scenario: AR - Payment Group - Create page
	Given Create Payment Group - AR_PaymentGroup_CreatePage
	Then Validate_PaymentGroup_Create Page with Invalid data - AR_PaymentGroup_CreatePage
	
@AR_PaymentGroup_GroupMembers
Scenario: AR - Payment Group - GroupMembers page
	Given Create Payment Group - AR_PaymentGroup_GroupMembersPage
	Then Validate_PaymentGroup_GroupMembers Page with Invalid data - AR_PaymentGroup_GroupMembersPage
	
@AR_CreditMemo_Search
Scenario: AR - Credit Memo - Search page
	Given Search Credit Memo - AR_Credit Memo_SearchPage
	Then Validate_Credit_Memo_Search Page with Invalid data - AR_Credit_Memo_SearchPage
	
@AR_CreditMemo_Create
Scenario: AR - Credit Memo - Create page
	Given Create Credit Memo - AR_Credit Memo_CreatePage
	Then Validate_Credit_Memo_Create Page with Invalid data - AR_Credit_Memo_CreatePage
	
@AR_CreditMemo_Items
Scenario: AR - Credit Memo - Items page
	Given Create Credit Memo - AR_Credit Memo_ItemsPage
	Then Validate_Credit_Memo_Items Page with Invalid data - AR_Credit_Memo_ItemsPage
	
@AR_CustAdjmt_Search
Scenario: AR - CustAdjmt - Search page
	Given Search CustAdjmt - AR_CustAdjmt_SearchPage
	Then Validate_CustAdjmt_Search Page with Invalid data - AR_CustAdjmt_SearchPage
	
@AR_CustAdjmt_Create
Scenario: AR - CustAdjmt - Create page
	Given Create CustAdjmt - AR_CustAdjmt_CreatePage
	Then Validate_CustAdjmt_Create Page with Invalid data - AR_CustAdjmt_CreatePage