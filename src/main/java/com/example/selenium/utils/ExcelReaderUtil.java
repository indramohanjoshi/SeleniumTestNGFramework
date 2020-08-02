package com.example.selenium.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil {

	public static Object[][] readExcel(String filePath, String fileName, String sheetName) throws IOException {

		Object[][] object = null;
		Workbook workbook = null;
		Sheet sheet = null;
		
		FileInputStream inputStream = new FileInputStream(new File(filePath + "\\" + fileName));
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			workbook = new HSSFWorkbook(inputStream);
		}

		if (null != sheetName) {
			sheet = workbook.getSheet(sheetName);
		} else {
			sheet = workbook.getSheetAt(0);
		}

		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();

		object = new Object[rowCount][columnCount];
		for (int rowIndex = 1; rowIndex < rowCount + 1; rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			for (int columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
				object[rowIndex-1][columnIndex] = row.getCell(columnIndex).getStringCellValue();
			}
		}
		return object;
	}
}
