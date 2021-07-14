Feature: SO_TC09 - Crest Release 3.4 - CRM to Sales Order

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials
	
@SO_TC09
Scenario: Create Lead - Convert to Account - Create Opportunity - Create Activity - Create Quote - Convert Quote into Order
Given Navigate to CRM>>Lead>>Create Lead
Then Create Lead and capture details
Then Convert to Account and capture details
Then Create the sales opportunity
Then Create the sales activity
Then Create Quote_add line items
Then AcceptQuote and validate details
Then Covert to Order and Create Order
Then Validate Order details
	
