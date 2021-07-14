Feature: Negative Testing for Facilities

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@Proc_Shp_Neg01
Scenario: Procurement - Create an Order for Shipments

	Given Navigate to Created purchase Order - PO_shipments
	Then Create as New Order - PO_shipments	

@Proc_Shp_Neg02
Scenario: Procurements - Approve PO for Shipments

	Given Approve the Order - PO_shipments	

@Proc_Shp_Neg03	
Scenario: Shipments - PO_CreateShipmentPage

	Given Navigate to Create Purchase Shipment Page - PO_CreateShipmentPage
	Then Validate with Invalid Data - PO_CreateShipmentPage
	
@Proc_Shp_Neg04
Scenario: Shipments - PO_ShipmentOrderItemsPage

	Given Navigate to Shipment Order Items Page - PO_ShipmentOrderItemsPage
	Then Validate with Invalid Data - PO_ShipmentOrderItemsPage
	
@Proc_Shp_Neg05
Scenario: Shipments - Receive Inventory
	
	Given Receive Inventory
	
@Proc_Shp_Neg06
Scenario: Purchase Return Shipments - PO_PurchaseReturnCreatePage

	Given Navigate to Create Purchase Return Page - PO_PurchaseReturnCreatePage
	Then Validate with Invalid Data - PO_PurchaseReturnCreatePage
	
@Proc_Shp_Neg07
Scenario: Purchase Return Shipments - PO_PurchaseReturnEditPage

	Given Navigate to Purchase Return Edit Page - PO_PurchaseReturnEditPage
	Then Validate with Invalid Data - PO_PurchaseReturnEditPage
	
@Proc_Shp_Neg08
Scenario: Purchase Return Shipments - PO_PurchaseReturn_OrderItemsPage

	Given Navigate to Purchase Return Order Items Page - PO_PurchaseReturn_OrderItemsPage
	Then Validate with Invalid Data - PO_PurchaseReturn_OrderItemsPage
	
@Proc_Shp_Neg09
Scenario: Create Adhoc PO - PO_ViewPage

	Given Create New Adhoc PO - PO_ViewPage
	
@Proc_Shp_Neg10
Scenario: Receive Adhoc - ReceiveAdhocPage
	
	Given Approve the Created PO - PO_ViewPage
	Then Navigate to Receive Adhoc Page - ReceiveAdhocPage
	Then Validate with Invalid date - ReceiveAdhocPage
	
	