package com.Procurement_Modules;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;

import com.Utils.Base;
import com.Utils.CrestTestDataReader;
import com.Utils.ExcelReader_ReportsVal;
import com.Utils.FileRename;

public class Procurement_Reports extends Base {
	
	static List<HashMap<String, String>> PO_Inv_data = CrestTestDataReader.getPOInvData();
	

	public static void PO_Report_Val() throws InterruptedException {

		log.info("Creating a New PO");
		driver.findElement(By.linkText("PROCUREMENT")).click();
		driver.findElement(By.linkText("Purchase Order")).click();
		driver.findElement(By.linkText("Reports")).click();
		driver.findElement(By.linkText("Purchase Order Excel")).click();
		
		// Adding Current Date(From Date)
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String Fromdate = (dateFormat.format(new Date()));
				driver.findElement(By.name("minDate_i18n")).sendKeys(Fromdate);		
				driver.findElement(By.name("maxDate_i18n")).sendKeys(Fromdate);
		
		
		driver.findElement(By.name("supplierPartyId")).sendKeys("10020");//Metal Shop
		
		Thread.sleep(2000);		
		driver.findElement(By.xpath("//*[@id='purchaseOrderSubmit']/td/input")).click();
		
		Thread.sleep(5000);
		FileRename.test();

		List<HashMap<String, String>> PO_Report = ExcelReader_ReportsVal
				.readExcelDatafromFile("C:\\Users\\KailashR\\Downloads\\report.xls", "purchase_order_report");

		
		System.out.println(PO_Inv_data.get(14).get("Supplier"));
		
		System.out.println(PO_Report.get(2).get("P.O. No.")); 
		System.out.println(PO_Report.size());

	}

}
