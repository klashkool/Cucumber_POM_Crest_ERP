package com.Procurement_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Procurement_Requirements_Requirements extends Base {

	public static Logger log = Logger.getLogger(Procurement_Requirements_Requirements.class);
	@Rule
	public static JUnitSoftAssertions PO_Req_softAssert = new JUnitSoftAssertions();
	public static JUnitSoftAssertions PO_EditReq_softAssert = new JUnitSoftAssertions();

	public static void PO_Req_Inv_Adhoc(List<HashMap<String, String>> data, String sheetname) throws InterruptedException {

		log.info("Creating PO Requirements");
		WebdriverWait.findElement("link", "PROCUREMENT").click();
		WebdriverWait.findElement("link", "Requirements").click();
		WebdriverWait.findElement("link", "Create Requirement").click();
		// Requirement Type DD

		Select reqType_dd = new Select(WebdriverWait.findElement("id", "requirementTypeId"));
		reqType_dd.selectByVisibleText("Product Requirement");
		// Organization DD
		Select reqOrg_dd = new Select(WebdriverWait.findElement("id", "fromPartyId"));
		reqOrg_dd.selectByVisibleText(data.get(0).get("Column3"));

		log.info("Adding products for Requirements");
		for (int p = 0; p <= 9; p++) {

			if (p <= 4) {
				Select ordType_dd = new Select(WebdriverWait.findElement("id", "addItemType"));
				ordType_dd.selectByVisibleText(data.get(p).get("Column5"));

				Thread.sleep(1000);
				WebdriverWait.findElement("name", "productId").sendKeys(data.get(p).get("Column2"));
				WebdriverWait.findElement("name", "quantity").sendKeys(data.get(p).get("Column9"));

				Select reqUom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
				reqUom_dd.selectByVisibleText(data.get(p).get("Column10"));

				Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
				dept_dd.selectByVisibleText(data.get(p).get("Column8"));

				WebdriverWait.findElement("name", "comments").sendKeys("Automation PO Req Test");

				Select fac_dd = new Select(WebdriverWait.findElement("id", "facilityId"));
				fac_dd.selectByVisibleText(data.get(1).get("Column7"));

				WebdriverWait.findElement("name", "submitProduct").click();

			} else if (p >= 5 && p <= 7) {

				Select ordType_dd = new Select(WebdriverWait.findElement("id", "addItemType"));
				ordType_dd.selectByVisibleText(data.get(p).get("Column5"));

				Thread.sleep(2000);
				WebdriverWait.findElement("name", "quantity").sendKeys(data.get(p).get("Column9"));

				Select uom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
				uom_dd.selectByVisibleText(data.get(p).get("Column10"));

				Select taxCat_dd = new Select(WebdriverWait.findElement("id", "gstCategory"));
				taxCat_dd.selectByVisibleText("GST 5%");

				Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
				dept_dd.selectByVisibleText(data.get(p).get("Column8"));

				Select fac_dd = new Select(WebdriverWait.findElement("xpath",
								"/html/body/div[2]/div[4]/div[2]/div[1]/form[1]/div[2]/div[2]/table[1]/tbody[1]/tr[5]/td[2]/select"));
				fac_dd.selectByVisibleText(data.get(6).get("Column7"));

				WebdriverWait.findElement("name", "comments").sendKeys(data.get(p).get("Column2"));

				WebdriverWait.findElement("name", "submitProduct").click();

			} else if (p >= 8 && p <= 9) {

				Select ordType_dd = new Select(WebdriverWait.findElement("id", "addItemType"));
				ordType_dd.selectByVisibleText(data.get(p).get("Column5"));

				Thread.sleep(2000);
				WebdriverWait.findElement("name", "quantity").sendKeys(data.get(p).get("Column9"));

				Select uom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
				uom_dd.selectByVisibleText(data.get(p).get("Column10"));

				Select taxCat_dd = new Select(WebdriverWait.findElement("id", "gstCategory"));
				taxCat_dd.selectByVisibleText("GST 18%");

				Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
				dept_dd.selectByVisibleText(data.get(p).get("Column8"));

				Select fac_dd = new Select(WebdriverWait.findElement("xpath",
								"/html/body/div[2]/div[4]/div[2]/div[1]/form[1]/div[2]/div[2]/table[1]/tbody[1]/tr[5]/td[2]/select"));
				fac_dd.selectByVisibleText(data.get(9).get("Column7"));

				WebdriverWait.findElement("name", "comments").sendKeys(data.get(p).get("Column2"));

				WebdriverWait.findElement("name", "submitProduct").click();
			}

		}

		WebdriverWait.findElement("xpath", "//*[@id='createRequirements']/div[3]/input").click();

		String reqPoNum = WebdriverWait.findElement("xpath", "//*[@id='editRequirements']/table/tbody[1]/tr[1]/td[2]").getText();
		System.out.println("Req ID = " + reqPoNum);
		ExcelWriter.writeExcelFile(sheetname, 15, 0, reqPoNum);

		WebdriverWait.findElement("link", "Approve").click();

		String reqPoStatus = WebdriverWait.findElement("xpath", "//*[@id='editRequirements']/table/tbody[1]/tr[2]/td[4]").getText();
		Assert.assertEquals("Approved", reqPoStatus);

		System.out.println("PO Requirements Created Successfully");
		log.info("PO Requirements Created Successfully");

		WebdriverWait.findElement("link", "Create RFQ").click();

	}

	public static void PO_Req(List<HashMap<String, String>> data, String sheetname, String orderType) throws InterruptedException {

		log.info("Creating PO Requirements");
		WebdriverWait.findElement("link", "PROCUREMENT").click();
		WebdriverWait.findElement("link", "Requirements").click();
		WebdriverWait.findElement("link", "Create Requirement").click();

		// Requirement Type DD
		Select reqType_dd = new Select(WebdriverWait.findElement("id", "requirementTypeId"));
		reqType_dd.selectByVisibleText("Product Requirement");

		// Organization DD
		Select reqOrg_dd = new Select(WebdriverWait.findElement("id", "fromPartyId"));
		reqOrg_dd.selectByVisibleText(data.get(0).get("Column3"));

		log.info("Adding products for Requirements");
		for (int p = 0; p <= 4; p++) {

			Select ordType_dd = new Select(WebdriverWait.findElement("id", "addItemType"));
			ordType_dd.selectByVisibleText(data.get(p).get("Column5"));

			if (orderType.contains("PO Supplies to Exp")) {
				Select taxCat_dd = new Select(WebdriverWait.findElement("id", "gstCategory"));
				taxCat_dd.selectByVisibleText("GST 5%");

				WebdriverWait.findElement("name", "comments").sendKeys(data.get(p).get("Column2"));
			} else {

				Thread.sleep(1000);
				WebdriverWait.findElement("name", "productId").sendKeys(data.get(p).get("Column2"));
				WebdriverWait.findElement("name", "comments").sendKeys("Automation PO Req Test");
			}

			WebdriverWait.findElement("name", "quantity").sendKeys(data.get(p).get("Column9"));

			Select reqUom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
			reqUom_dd.selectByVisibleText(data.get(p).get("Column10"));

			Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
			dept_dd.selectByVisibleText(data.get(p).get("Column8"));

			List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='facilityId']//option");
			for (WebElement option : options) {
				if (option.getText().contains(data.get(p).get("Column7"))) {
					option.click();
					break;
				}
			}

			WebdriverWait.findElement("name", "submitProduct").click();
		}

		WebdriverWait.findElement("name", "createProduct").click();

		String reqPoNum = WebdriverWait.findElement("xpath", "//*[@id='editRequirements']/table/tbody[1]/tr[1]/td[2]").getText();
		System.out.println("Req ID = " + reqPoNum);
		ExcelWriter.writeExcelFile(sheetname, 15, 1, reqPoNum);

	}

	public static void Req_Approve() {

		WebdriverWait.findElement("link", "Approve").click();

		String reqPoStatus = WebdriverWait.findElement("xpath", "//*[@id='editRequirements']/table/tbody[1]/tr[2]/td[4]").getText();
		Assert.assertEquals("Approved", reqPoStatus);

		System.out.println("PO Requirements Created Successfully");
		log.info("PO Requirements Created Successfully");

	}

	public static void Create_RFQ() {
		WebdriverWait.findElement("link", "Create RFQ").click();
	}

	// ----------------------------------Negative
	// Flows----------------------------------------------------------------------------------------
	//
	//
	//
	//

	public static void Req_Navigation() {

		WebdriverWait.findElement("link", "PROCUREMENT").click();
		WebdriverWait.findElement("link", "Requirements").click();
	}

	public static void Req_SearchPage_InvalidDate_Val(List<HashMap<String, String>> data) {

		// Requirement Date Range with past date as Thru date
		WebdriverWait.findElement("id", "requirementStartFromDate_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("id", "requirementStartThruDate_i18n").sendKeys(data.get(7).get("Column1"));

		// Required By Date Range with past date as Thru date
		WebdriverWait.findElement("id", "requiredByFromDate_i18n").sendKeys(data.get(8).get("Column1"));
		WebdriverWait.findElement("id", "requiredByThruDate_i18n").sendKeys(data.get(7).get("Column1"));

		WebdriverWait.findElement("id", "submit").click();

		String req_ThruDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String req_FromDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String reqBy_ThruDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();
		String reqBy_FromDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[4]").getText();

		PO_Req_softAssert.assertThat(req_ThruDate).isEqualTo(data.get(0).get("Column2"));
		PO_Req_softAssert.assertThat(req_FromDate).isEqualTo(data.get(1).get("Column2"));
		PO_Req_softAssert.assertThat(reqBy_ThruDate).isEqualTo(data.get(2).get("Column2"));
		PO_Req_softAssert.assertThat(reqBy_FromDate).isEqualTo(data.get(3).get("Column2"));

	}

	public static void Create_Req_link() {
		WebdriverWait.findElement("link", "Create Requirement").click();
	}

	public static void Req_Mandatory_Fields_CreateReqTable(List<HashMap<String, String>> data) {

		// Requirement Type DD
		Select reqType_dd = new Select(WebdriverWait.findElement("id", "requirementTypeId"));
		reqType_dd.selectByVisibleText("-Select-");

		// Organization DD
		Select reqOrg_dd = new Select(WebdriverWait.findElement("id", "fromPartyId"));
		reqOrg_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("name", "createProduct").click();

		String reqType = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String orgType = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();

		PO_Req_softAssert.assertThat(reqType).isEqualTo(data.get(0).get("Column6"));
		PO_Req_softAssert.assertThat(orgType).isEqualTo(data.get(0).get("Column4"));

	}

	public static void Invalid_reqByDate_CreateReqTable(List<HashMap<String, String>> data) {

		// Invalid Required By Date
		WebdriverWait.findElement("id", "requiredByDate_i18n").sendKeys(data.get(7).get("Column1"));

		WebdriverWait.findElement("name", "createProduct").click();

		String requiredByDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();

		PO_Req_softAssert.assertThat(requiredByDate).isEqualTo(data.get(13).get("Column2"));

		WebdriverWait.findElement("id", "requiredByDate_i18n").clear();

	}

	public static void Create_WithoutAddinglineItem_ReqItemTable(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "createProduct").click();

		String reqItemMsg = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();

		PO_Req_softAssert.assertThat(reqItemMsg).isEqualTo(data.get(0).get("Column21"));

	}

	public static void Req_Mandatory_Fields_ReqItemTable(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "submitProduct").click();

		String qty_Req = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String qty_moreThan0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String fac = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();
		String dept = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[4]").getText();
		String orderItem_Type = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[5]").getText();

		PO_Req_softAssert.assertThat(qty_Req).isEqualTo(data.get(0).get("Column10"));
		PO_Req_softAssert.assertThat(qty_moreThan0).isEqualTo(data.get(1).get("Column10"));
		PO_Req_softAssert.assertThat(fac).isEqualTo(data.get(0).get("Column8"));
		PO_Req_softAssert.assertThat(dept).isEqualTo(data.get(0).get("Column9"));
		PO_Req_softAssert.assertThat(orderItem_Type).isEqualTo(data.get(1).get("Column6"));

	}

	public static void Invalid_PrdID(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		Select ordType_dd = new Select(WebdriverWait.findElement("id", "addItemType"));
		ordType_dd.selectByVisibleText(data.get(0).get("Column5"));

		WebdriverWait.findElement("name", "productId").sendKeys(Inv_data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(0).get("Column9"));

		Select reqUom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
		reqUom_dd.selectByVisibleText(data.get(0).get("Column10"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data.get(0).get("Column8"));

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='facilityId']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(0).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("name", "submitProduct").click();

		String prdIDMsg = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_Req_softAssert.assertThat(prdIDMsg).isEqualTo(Inv_data.get(1).get("Column3"));

		WebdriverWait.findElement("name", "productId").clear();
		WebdriverWait.findElement("name", "quantity").clear();

	}

	public static void InvalidQty(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		Select ordType_dd = new Select(WebdriverWait.findElement("id", "addItemType"));
		ordType_dd.selectByVisibleText(data.get(0).get("Column5"));

		WebdriverWait.findElement("name", "productId").sendKeys(data.get(0).get("Column2"));

		// Negative Value Qty
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(1).get("Column1"));

		Select reqUom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
		reqUom_dd.selectByVisibleText(data.get(0).get("Column10"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data.get(0).get("Column8"));

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='facilityId']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(0).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("name", "submitProduct").click();

		String qty_num = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String qty_grt0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();

		PO_Req_softAssert.assertThat(qty_num).isEqualTo(Inv_data.get(2).get("Column10"));
		PO_Req_softAssert.assertThat(qty_grt0).isEqualTo(Inv_data.get(1).get("Column10"));

		// 0 Value Qty
		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(3).get("Column1"));
		WebdriverWait.findElement("name", "submitProduct").click();

		String qty_grt_0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_Req_softAssert.assertThat(qty_grt_0).isEqualTo(Inv_data.get(1).get("Column10"));

		// Qty with special characters.
		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "submitProduct").click();

		String qty_num1 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_Req_softAssert.assertThat(qty_num1).isEqualTo(Inv_data.get(2).get("Column10"));

		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(4).get("Column1"));

		Select ordType1_dd = new Select(WebdriverWait.findElement("id", "addItemType"));
		ordType1_dd.selectByVisibleText(data.get(0).get("Column5"));

	}

	public static void Invalid_reqByDate_ReqItemTable(List<HashMap<String, String>> data) {

		// Invalid Required By Date
		WebdriverWait.findElement("id", "itemRequiredByDate_i18n").sendKeys(data.get(7).get("Column1"));

		WebdriverWait.findElement("name", "submitProduct").click();

		String requiredByDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();

		PO_Req_softAssert.assertThat(requiredByDate).isEqualTo(data.get(4).get("Column2"));
		WebdriverWait.findElement("id", "itemRequiredByDate_i18n").clear();

	}

	public static void Val_255char_Comments_ReqItemTable(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "comments").sendKeys("Test");
		WebdriverWait.findElement("name", "submitProduct").click();

		// String comments = WebdriverWait.findElement("xpath",
		// "//*[@id='createRequirements']/div[2]/div[2]/table[2]/tbody/tr/td[12]").getText();
		// comments.length();
		//
		// PO_Req_softAssert.assertThat(comments.length()).isEqualTo(255);
	}

	public static void Removing_AddedLineItem(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "submitProduct").click();
		WebdriverWait.findElement("link", "Remove").click();

		String removedMsg = WebdriverWait.findElement("xpath", "//*[@id='createRequirements']/div[2]/div[2]/table[2]/tbody/tr/td").getText();

		PO_Req_softAssert.assertThat(removedMsg).isEqualTo(data.get(8).get("Column21"));
	}

	public static void Val_255Charc_Desc_Reason_CreateReqTable(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "description").sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("id", "reason").sendKeys(data.get(6).get("Column1"));

		// WebdriverWait.findElement("name", "submitProduct").click();
		WebdriverWait.findElement("name", "createProduct").click();

		String desc_charclimit = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_Req_softAssert.assertThat(desc_charclimit).isEqualTo(data.get(5).get("Column13"));

		String reason_charclimit = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		PO_Req_softAssert.assertThat(reason_charclimit).isEqualTo(data.get(6).get("Column13"));

	}

	public static void te(List<HashMap<String, String>> data, String sheetname, String orderType) throws InterruptedException {
		log.info("Adding products for Requirements");
		for (int p = 0; p <= 4; p++) {

			Select ordType_dd = new Select(WebdriverWait.findElement("id", "addItemType"));
			ordType_dd.selectByVisibleText(data.get(p).get("Column5"));

			if (orderType.contains("PO Supplies to Exp")) {
				Select taxCat_dd = new Select(WebdriverWait.findElement("id", "gstCategory"));
				taxCat_dd.selectByVisibleText("GST 5%");

				WebdriverWait.findElement("name", "comments").sendKeys(data.get(p).get("Column2"));
			} else {

				Thread.sleep(1000);
				WebdriverWait.findElement("name", "productId").sendKeys(data.get(p).get("Column2"));
				WebdriverWait.findElement("name", "comments").sendKeys("Automation PO Req Test");
			}

			WebdriverWait.findElement("name", "quantity").sendKeys(data.get(p).get("Column9"));

			Select reqUom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
			reqUom_dd.selectByVisibleText(data.get(p).get("Column10"));

			Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
			dept_dd.selectByVisibleText(data.get(p).get("Column8"));

			List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='facilityId']//option");
			for (WebElement option : options) {
				if (option.getText().contains(data.get(p).get("Column7"))) {
					option.click();
					break;
				}
			}

			WebdriverWait.findElement("name", "submitProduct").click();
		}

		WebdriverWait.findElement("name", "createProduct").click();

		String reqPoNum = WebdriverWait.findElement("xpath", "//*[@id='editRequirements']/table/tbody[1]/tr[1]/td[2]").getText();
		System.out.println("Req ID = " + reqPoNum);
		ExcelWriter.writeExcelFile(sheetname, 15, 1, reqPoNum);

		WebdriverWait.findElement("link", "Approve").click();

		String reqPoStatus = WebdriverWait.findElement("xpath", "//*[@id='editRequirements']/table/tbody[1]/tr[2]/td[4]").getText();
		Assert.assertEquals("Approved", reqPoStatus);

		System.out.println("PO Requirements Created Successfully");
		log.info("PO Requirements Created Successfully");

		WebdriverWait.findElement("link", "Create RFQ").click();

	}

	//////////////////// Requirement Edit/////////////////////////

	public static void Req_Mandatory_Fields_EditReqTable(List<HashMap<String, String>> data) {

		// Requirement Type DD
		Select reqType_dd = new Select(WebdriverWait.findElement("id", "requirementTypeId"));
		reqType_dd.selectByVisibleText("-Select-");

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/input").click();

		String reqType = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_EditReq_softAssert.assertThat(reqType).isEqualTo(data.get(0).get("Column6"));

		Select reqType1_dd = new Select(WebdriverWait.findElement("id", "requirementTypeId"));
		reqType1_dd.selectByVisibleText("Product Requirement");
	}

	public static void Invalid_reqByDate_EditReqTable(List<HashMap<String, String>> data) {

		// Invalid Required By Date
		WebdriverWait.findElement("id", "requiredByDate_i18n").sendKeys(data.get(7).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/input").click();

		String requiredByDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();

		PO_EditReq_softAssert.assertThat(requiredByDate).isEqualTo(data.get(13).get("Column2"));

		WebdriverWait.findElement("id", "requiredByDate_i18n").clear();

	}

	public static void Req_Mandatory_Fields_EditReqItemTable(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='createRequirementItem']/table/tbody[1]/tr[8]/td/input").click();

		String qty_Req = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String qty_moreThan0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();
		String fac = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[3]").getText();
		String dept = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[4]").getText();
		String orderItem_Type = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[5]").getText();

		PO_EditReq_softAssert.assertThat(qty_Req).isEqualTo(data.get(0).get("Column10"));
		PO_EditReq_softAssert.assertThat(qty_moreThan0).isEqualTo(data.get(1).get("Column10"));
		PO_EditReq_softAssert.assertThat(fac).isEqualTo(data.get(0).get("Column8"));
		PO_EditReq_softAssert.assertThat(dept).isEqualTo(data.get(0).get("Column9"));
		PO_EditReq_softAssert.assertThat(orderItem_Type).isEqualTo(data.get(1).get("Column6"));

	}

	public static void EditReq_Invalid_PrdID(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		Select ordType_dd = new Select(WebdriverWait.findElement("id", "addItemType"));
		ordType_dd.selectByVisibleText(data.get(0).get("Column5"));

		WebdriverWait.findElement("name", "productId").sendKeys(Inv_data.get(0).get("Column1"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(0).get("Column9"));

		Select reqUom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
		reqUom_dd.selectByVisibleText(data.get(0).get("Column10"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data.get(0).get("Column8"));

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='facilityId']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(0).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("xpath", "//*[@id='createRequirementItem']/table/tbody[1]/tr[8]/td/input").click();

		String prdIDMsg = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_EditReq_softAssert.assertThat(prdIDMsg).isEqualTo(Inv_data.get(1).get("Column3"));

		WebdriverWait.findElement("name", "productId").clear();
		WebdriverWait.findElement("name", "quantity").clear();

	}

	public static void EditReq_InvalidQty(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		Select ordType_dd = new Select(WebdriverWait.findElement("id", "addItemType"));
		ordType_dd.selectByVisibleText(data.get(0).get("Column5"));

		WebdriverWait.findElement("name", "productId").sendKeys(data.get(0).get("Column2"));

		// Negative Value Qty
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(1).get("Column1"));

		Select reqUom_dd = new Select(WebdriverWait.findElement("id", "quantityUomId"));
		reqUom_dd.selectByVisibleText(data.get(0).get("Column10"));

		Select dept_dd = new Select(WebdriverWait.findElement("id", "departmentName"));
		dept_dd.selectByVisibleText(data.get(0).get("Column8"));

		List<WebElement> options = WebdriverWait.findElements("xpath", "//*[@id='facilityId']//option");
		for (WebElement option : options) {
			if (option.getText().contains(data.get(0).get("Column7"))) {
				option.click();
				break;
			}
		}

		WebdriverWait.findElement("xpath", "//*[@id='createRequirementItem']/table/tbody[1]/tr[8]/td/input").click();

		String qty_grt0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String qty_num = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();

		PO_EditReq_softAssert.assertThat(qty_num).isEqualTo(Inv_data.get(2).get("Column10"));
		PO_EditReq_softAssert.assertThat(qty_grt0).isEqualTo(Inv_data.get(1).get("Column10"));

		// 0 Value Qty
		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(3).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='createRequirementItem']/table/tbody[1]/tr[8]/td/input").click();

		String qty_grt_0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_EditReq_softAssert.assertThat(qty_grt_0).isEqualTo(Inv_data.get(1).get("Column10"));

		// Qty with special characters.
		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(0).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='createRequirementItem']/table/tbody[1]/tr[8]/td/input").click();

		String qty_num1 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_EditReq_softAssert.assertThat(qty_num1).isEqualTo(Inv_data.get(2).get("Column10"));

		WebdriverWait.findElement("name", "quantity").clear();
		WebdriverWait.findElement("name", "quantity").sendKeys(Inv_data.get(4).get("Column1"));

	}

	public static void EditReq_Invalid_reqByDate_ReqItemTable(List<HashMap<String, String>> data) {

		// Invalid Required By Date
		WebdriverWait.findElement("id", "itemRequiredByDate_i18n").sendKeys(data.get(7).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='createRequirementItem']/table/tbody[1]/tr[8]/td/input").click();

		String requiredByDate = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();

		PO_EditReq_softAssert.assertThat(requiredByDate).isEqualTo(data.get(4).get("Column2"));
		WebdriverWait.findElement("id", "itemRequiredByDate_i18n").clear();
	}

	public static void EditReqQty_InvalidData(List<HashMap<String, String>> data, List<HashMap<String, String>> Inv_data) {

		// Edit the Qty as empty
		WebdriverWait.findElement("id", "quantity_0").clear();
		WebdriverWait.findElement("xpath", "//*[@id='removeRequirementItem']/a[1]").click();
		String qty_req = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String qty_grt0 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();

		PO_EditReq_softAssert.assertThat(qty_req).isEqualTo(Inv_data.get(0).get("Column10"));
		PO_EditReq_softAssert.assertThat(qty_grt0).isEqualTo(Inv_data.get(1).get("Column10"));

		// Edit the Qty to 0
		WebdriverWait.findElement("id", "quantity_0").clear();
		WebdriverWait.findElement("id", "quantity_0").sendKeys(Inv_data.get(3).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='removeRequirementItem']/a[1]").click();
		String qty_grt01 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		PO_EditReq_softAssert.assertThat(qty_grt01).isEqualTo(Inv_data.get(1).get("Column10"));

		// Edit the Qty to Negative
		WebdriverWait.findElement("id", "quantity_0").clear();
		WebdriverWait.findElement("id", "quantity_0").sendKeys(Inv_data.get(1).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='removeRequirementItem']/a[1]").click();
		String qty_num1 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String qty_grt02 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();

		PO_EditReq_softAssert.assertThat(qty_num1).isEqualTo(Inv_data.get(2).get("Column10"));
		PO_EditReq_softAssert.assertThat(qty_grt02).isEqualTo(Inv_data.get(1).get("Column10"));

		// Edit the Qty to decimal
		WebdriverWait.findElement("id", "quantity_0").clear();
		WebdriverWait.findElement("id", "quantity_0").sendKeys(Inv_data.get(2).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='removeRequirementItem']/a[1]").click();

		String Qty = WebdriverWait.findElement("id", "quantity_0").getText();
		PO_EditReq_softAssert.assertThat(Qty).isEqualTo(Inv_data.get(9).get("Column1"));

	}

	public static void Removing_AddedLineItem_EditReq(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='removeRequirementItem']/a[2]").click();

		String removeMsg = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/p").getText();
		PO_EditReq_softAssert.assertThat(removeMsg).isEqualTo(data.get(0).get("Column18"));

	}

	public static void Reject_Req(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Reject").click();
		String rejectMsg = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/p").getText();
		PO_EditReq_softAssert.assertThat(rejectMsg).isEqualTo(data.get(0).get("Column20"));

		String rejectStatus = WebdriverWait.findElement("xpath", "//*[@id='editRequirements']/table/tbody[1]/tr[2]/td[4]").getText();
		PO_EditReq_softAssert.assertThat(rejectStatus).isEqualTo("Rejected");

	}

	public static void PO_Req_assertions() {
		PO_Req_softAssert.assertAll();
	}

	public static void PO_EditReq_assertions() {
		PO_EditReq_softAssert.assertAll();
	}

}
