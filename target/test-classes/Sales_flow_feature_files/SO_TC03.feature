Feature: Crest Release - 3.4 - SO TC03 - Sales Return (For order SO_TC05)

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 

@SalesReturn 
Scenario: Create Sales Return - SO Return Flow 

	Given Create SO Return - SO Return Flow
	Then Capture SO Return ID - SO Return Flow
	Then Return Items - SO Return Flow
	Then Receive returned items - SO Return Flow
	Then Verify SO Return status - SO Return Flow
	Then Capture Return Shipment and Invoice ID - SO Return Flow
	Then Open Return Shipment and Verify details - SO Return Flow
	Then Open Return Invoice and Verify details - SO Return Flow