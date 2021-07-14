Feature: Crest Release - 3.4 - SO TC08 - Sales Drop Ship

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@SO_DropShip
Scenario: Create SO with Drop Ship YES - SO_DropShip

	Given Create SO with Drop Ship YES - SO_DropShip
	Then Add Shipping Details - SO_DropShip
	Then Add Order Items - SO_DropShip
	Then Review & Create SO - SO_DropShip
	Then Validating SubTotal/Tax/GrandTotal - SO_DropShip
	Then Validating Drop Ship PO got generated - SO_DropShip
	Then Validating SubTotal/Tax/GrandTotalDrop Ship PO - SO_DropShip
