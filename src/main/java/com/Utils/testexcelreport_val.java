package com.Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class testexcelreport_val extends Base {

	public static void testexcel(List<HashMap<String, String>> data) throws InterruptedException {

		log.info("Creating a New PO");
		driver.findElement(By.linkText("FACILITIES")).click();
		driver.findElement(By.linkText("Reports")).click();
		driver.findElement(By.linkText("Inventory Reports - Stock Receipt")).click();

		Select org_dd = new Select(WebdriverWait.findElement("id", "company"));
		org_dd.selectByVisibleText("UIDAI");

		Select date_dd = new Select(WebdriverWait.findElement("id", "dateSelection"));
		date_dd.selectByVisibleText("Date Selection");

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String Fromdate = (dateFormat.format(new Date()));
		WebdriverWait.findElement("name", "fromDate_i18n").sendKeys("14/09/2020");
		WebdriverWait.findElement("name", "thruDate_i18n").sendKeys("14/09/2020");

		// WebdriverWait.findElement("name", "minDate_i18n").sendKeys(Fromdate);
		// WebdriverWait.findElement("name", "maxDate_i18n").sendKeys(Fromdate);
		// driver.findElement(By.name("supplierPartyId")).sendKeys("125550");

		driver.findElement(By.xpath("//*[@id='generateStockReport']/table/tbody[1]/tr[7]/td/input")).click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

		// driver.findElement(By.xpath("//*[@id='generateStockReport']/table/tbody[1]/tr[7]/td/input")).click();

		Thread.sleep(2000);

		FileRename.test();

		List<HashMap<String, String>> PO_Report = ExcelReader_ReportsVal.readExcelDatafromFile("C:\\Users\\KailashR\\Downloads\\report.csv",
						"STOCK_RECEIPT");

		Thread.sleep(5000);
		int counter = 0;
		for (int a = 2; a < PO_Report.size() - 1; a++) {

			// if (PO_Report.get(a).get("P.O.
			// No.").equals(data.get(14).get("Supplier"))) {
			// counter++;
			// }
			// }
			// System.out.println(counter);
			// Assert.assertEquals(10, counter);
			//
			// if (counter == 10) {
			// Assert.assertEquals(PO_Report.get(counter).get("P.O. Date"),
			// "04/03/2020");
			// System.out.println("PO Reports - Date validated Successfully");

			System.out.println(PO_Report.get(a).get("Transaction No."));

		}

		File file = new File("C:\\Users\\KailashR\\Downloads\\report.xls");

		if (file.delete()) {
			System.out.println("file deleted");

		}

	}
}