package com.Utils;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader_FourDecimals {

	public static HashMap<String, String> storeValues = new HashMap<String, String>();

	public static List<HashMap<String, String>> readExcelDatafromFile(String filePath, String sheetName) {

		// create Java List to store Hashmaps
		List<HashMap<String, String>> excelData = new ArrayList<HashMap<String, String>>();

		try {
			FileInputStream fs = new FileInputStream(filePath);
			XSSFWorkbook wb = new XSSFWorkbook(fs);
			XSSFSheet sheet = wb.getSheet(sheetName);

			// catch header row, so that you can use it as Key for your hashmap
			Row HeaderRow = sheet.getRow(0);

			for (int r = 2; r <= sheet.getPhysicalNumberOfRows(); r++) {
				Row CurrentRow = sheet.getRow(r);
				// each row of data is stored in new hashmap

				HashMap<String, String> currentRowMap = new HashMap<String, String>();

				if (CurrentRow != null)
					for (int c = 0; c < CurrentRow.getPhysicalNumberOfCells(); c++) {
						Cell CurrentCell = CurrentRow.getCell(c);

						if (CurrentCell != null) {
							if (CurrentCell.getCellType() == CellType.FORMULA) {

								switch (CurrentCell.getCachedFormulaResultType()) {

									case NUMERIC :

										DecimalFormat decimalFormat = new DecimalFormat("0.0000");

										String key = HeaderRow.getCell(c).getStringCellValue();
										String value = decimalFormat.format(CurrentCell.getNumericCellValue());
										String format = CurrentCell.getCellStyle().getDataFormatString();

										if (format.equals("[$₹-439]#,##0.00")) {

											value = format.split("")[2] + value;

										} else if (format.equals("\"$\"#,##0.00")) {

											value = format.split("")[1] + value;

										} else if (format.equals(null)) {

											value = " ";
										}
										currentRowMap.put(key, value);

										break;
									case STRING :

										currentRowMap.put(HeaderRow.getCell(c).getStringCellValue(), String.valueOf(CurrentCell.getRichStringCellValue()));

										break;
									default :
										break;

								}
							} else {
								if (CurrentCell.getCellType() == CellType.STRING) {

									currentRowMap.put(HeaderRow.getCell(c).getStringCellValue(), CurrentCell.getStringCellValue());

								} else if (CurrentCell.getCellType() == CellType.NUMERIC) {

									// currentRowMap.put(HeaderRow.getCell(c).getStringCellValue(),
									// String.valueOf(CurrentCell.getNumericCellValue()));
									DecimalFormat decimalFormat = new DecimalFormat("0.0000");

									String key = HeaderRow.getCell(c).getStringCellValue();
									String value = decimalFormat.format(CurrentCell.getNumericCellValue());
									String format = CurrentCell.getCellStyle().getDataFormatString();

									if (format.equals("[$₹-439]#,##0.00")) {

										value = format.split("")[2] + value;

									} else if (format.equals("\"$\"#,##0.00")) {

										value = format.split("")[1] + value;

									} else if (format.equals(null)) {

										value = " ";
									}
									currentRowMap.put(key, value);
								}
							}
						}

						// i.e hashmap<key, value> = <row(0)column(c),
						// row(1)column(c)>
					}
				excelData.add(currentRowMap);
			}
			// ((Closeable) wb).close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("Excel Reader Data :" + excelData);
		return excelData;

	}

}
