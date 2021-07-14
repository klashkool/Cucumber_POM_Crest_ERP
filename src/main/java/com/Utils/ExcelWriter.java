package com.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

	public static void writeExcelFile(final String sheetName, final int rowNumber, final int cellNumber,
			final String value) {

		final String filePath = "\\src\\main\\java\\com\\Utils\\Crest_TestData.xlsx";
		try {
			FileInputStream ExcelFileToRead = new FileInputStream(System.getProperty("user.dir") + filePath);
			XSSFWorkbook workBook;
			workBook = new XSSFWorkbook(ExcelFileToRead);
			XSSFSheet sheet = workBook.getSheet(sheetName);
			XSSFRow row = sheet.getRow(rowNumber);
			XSSFCell result_cell = row.createCell(cellNumber);

			result_cell.setCellValue(value);
			ExcelFileToRead.close();


			FileOutputStream ExcelFileToWrite = new FileOutputStream(System.getProperty("user.dir") + filePath);
			workBook.write(ExcelFileToWrite);
			ExcelFileToWrite.flush();
			ExcelFileToWrite.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}