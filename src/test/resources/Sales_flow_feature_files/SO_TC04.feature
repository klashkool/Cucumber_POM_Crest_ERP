Feature: Crest Release - 3.4 - SO TC04 - Sales Return Replacement (For order SO_TC05)

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 

@SalesReturnReplacement 
Scenario: Create Sales Return - SO Return Replacement Flow 

	Given Create SO Return - SO Return Replacement Flow
	Then Capture SO Return ID - SO Return Replacement Flow
	Then Return Items - SO Return Replacement Flow
	Then Receive returned items - SO Return Replacement Flow
	Then Verify SO Return status - SO Return Replacement Flow
	Then Navigate to Sales Order view details page - SO Return Replacement Flow
	Then Capture Replacement SO ID - SO Return Replacement Flow
	Then Open Replacement SO and Verify details - SO Return Replacement Flow