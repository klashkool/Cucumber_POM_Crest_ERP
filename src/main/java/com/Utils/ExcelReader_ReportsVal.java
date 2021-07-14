package com.Utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class ExcelReader_ReportsVal {

	public static HashMap<String, String> storeValues = new HashMap<String, String>();

	public static List<HashMap<String, String>> readExcelDatafromFile(String filePath, String sheetName) {

//create Java List to store Hashmaps
		List<HashMap<String, String>> excelData = new ArrayList<HashMap<String, String>>();

		try {
			FileInputStream fs = new FileInputStream(filePath);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			boolean beginStoring = false;

//catch header row, so that you can use it as Key for your hashmap
			Row HeaderRow = null;

			for (int r = 0; r <= sheet.getPhysicalNumberOfRows(); r++) {
				Row CurrentRow = sheet.getRow(r);
//each row of data is stored in new hashmap
				HashMap<String, String> currentRowMap = new HashMap<String, String>();

				if (CurrentRow != null)
					for (int c = 0; c < CurrentRow.getPhysicalNumberOfCells(); c++) {
						Cell CurrentCell = CurrentRow.getCell(c);

						if(!beginStoring) {
							if(CurrentRow.getCell(0).getStringCellValue() == "") {
								beginStoring = true;
								HeaderRow = sheet.getRow(r+1);
								break;
							}
						}

						if (CurrentCell != null && beginStoring) {
							if (CurrentCell.getCellType() == CellType.FORMULA) {
								switch (CurrentCell.getCachedFormulaResultType()) {
									case NUMERIC :
									currentRowMap.put(HeaderRow.getCell(c).getStringCellValue(),
											String.valueOf(CurrentCell.getNumericCellValue()));
									break;
									case STRING :
									currentRowMap.put(HeaderRow.getCell(c).getStringCellValue(),
											String.valueOf(CurrentCell.getRichStringCellValue()));
									break;
									default :
										break;
								}
							} else {
								if (CurrentCell.getCellType() == CellType.STRING) {
									currentRowMap.put(HeaderRow.getCell(c).getStringCellValue(),
											CurrentCell.getStringCellValue());
								} else if (CurrentCell.getCellType() == CellType.NUMERIC) {
									currentRowMap.put(HeaderRow.getCell(c).getStringCellValue(),
											String.valueOf(CurrentCell.getNumericCellValue()));
								}
							}
						}

// i.e hashmap<key, value> = <row(0)column(c), row(1)column(c)>
					}
				if(beginStoring)
					excelData.add(currentRowMap);
			}
//			((Closeable) wb).close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Excel Reader Data :" + excelData);
		return excelData;

	}

}
