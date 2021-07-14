Feature: Shipments Negative Scenarios

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 

@Shipments_Search
Scenario: Sales - Shipments - Search page
	Given Navigate to Shipments - Sales_Shipments_SearchPage
	Then Validate with Invalid Date Range - Sales_Shipments_SearchPage

@Shipments_Create
Scenario: Sales - Shipments - Create page
	Given Navigate to Shipments - Sales_Shipments_CreatePage
	Then Validate with Invalid Data - Sales_Shipments_CreatePage
	
@Shipments_Edit
Scenario: Sales - Shipments - Edit page
	Given Navigate to Shipments - Sales_Shipments_EditPage
	Then Validate with Invalid Data - Sales_Shipments_EditPage

@Shipments_ShipmentPlan
Scenario: Sales - Shipments - Shipment Plan page
	Given Navigate to Shipments Shipment Plan - Sales_Shipments_ShipmentPlanPage
	Then Validate with Invalid Data - Sales_Shipments_ShipmentPlanPage
	
@Shipments_OrderItems
Scenario: Sales - Shipments - Order Items page
	Given Navigate to Shipments Order Items - Sales_Shipments_OrderItemsPage
	Then Validate with Invalid Data - Sales_Shipments_OrderItemsPage

@SalesReturnShipment_Create
Scenario: Sales - Return Shipments - Create page
	Given Navigate to Return Shipments - Sales_ReturnShipments_CreatePage
	Then Validate with Invalid Data - Sales_ReturnShipments_CreatePage
