package com.Party_Modules;

import com.Utils.WebdriverWait;

public class Party_Parties {

	public static void PartySearch(String partyId) {

		WebdriverWait.findElement("link", "PARTY").click();
		WebdriverWait.findElement("link", "Parties").click();
		WebdriverWait.findElement("name", "partyId").sendKeys(partyId);
		WebdriverWait.findElement("xpath", "//input[@type='submit']").click();
		WebdriverWait.findElement("xpath", "//*[@id='example']//a").click();

	}

}
