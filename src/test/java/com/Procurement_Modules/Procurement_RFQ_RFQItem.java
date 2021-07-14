package com.Procurement_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Procurement_RFQ_RFQItem extends Base {

	@Rule
	public static JUnitSoftAssertions PO_RFQItems_softAssert = new JUnitSoftAssertions();

	public static void RFQ_Status_Val() {

		String rfq_status = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		Assert.assertEquals("RFQ Created Successfully", rfq_status);
		System.out.println("RFQ Created Successfully");
	}

	public static void RFQ_LoadItems(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("name", "selectAll").click();
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form[1]/div[2]/input").click(); // Add Items

		String additems_valmsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		Assert.assertEquals("Item(s) Added Successfully to the RFQ", additems_valmsg);
	}

	////////////////////////////////////////// Negative
	////////////////////////////////////////// Flow//////////////////////////////////////////////////

	public static void LoadItemsWithout_ReqID(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/form/table/tbody/tr/td[3]/input").click();
		String reqID = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();

		PO_RFQItems_softAssert.assertThat(reqID).isEqualTo(data.get(9).get("Column21"));
	}

	public static void PO_RFQ_assertions() {
		PO_RFQItems_softAssert.assertAll();
	}
}
