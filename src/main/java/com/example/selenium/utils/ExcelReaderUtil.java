package com.example.selenium.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil {

	private static final String XLS = ".xls";
	private static final String XLSX = ".xlsx";

	public static Object[][] readExcel(String filePath, String fileName, String sheetName) throws IOException {

		Object[][] objectArray = null;
		Workbook workbook = null;
		Sheet sheet = null;
		
		FileInputStream inputStream = new FileInputStream(new File(filePath + "\\" + fileName));
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		if (fileExtensionName.equals(XLSX)) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(XLS)) {
			workbook = new HSSFWorkbook(inputStream);
		}

		if (StringUtils.isNotBlank(sheetName)) {
			sheet = workbook.getSheet(sheetName);
		} else {
			sheet = workbook.getSheetAt(0);
		}

		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();

		objectArray = new Object[rowCount][columnCount];
		for (int rowIndex = 1; rowIndex < rowCount + 1; rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			for (int columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
				objectArray[rowIndex-1][columnIndex] = row.getCell(columnIndex).getStringCellValue();
			}
		}
		return objectArray;
	}
}
