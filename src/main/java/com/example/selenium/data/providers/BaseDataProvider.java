package com.example.selenium.data.providers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.selenium.utils.ExcelReaderUtil;

public class BaseDataProvider {

	private static final Logger LOG = LoggerFactory.getLogger(BaseDataProvider.class);
	private static final String USER_DIR = System.getProperty("user.dir");

	protected static Object[][] constructObjectArray(String testDataFileRelativePath, String fileName, String sheetName) {
		Object[][] data = null;
		try {
			String fileAbsolutePath = getTestDataFileAbsolutePath(testDataFileRelativePath);
			LOG.info("Reading sheet :: {} of file :: {}, with absolute path :: {}", sheetName, fileName, fileAbsolutePath);
			data = ExcelReaderUtil.readExcel(fileAbsolutePath, fileName, sheetName);
		} catch (IOException e) {
			LOG.error("Unable to read data from fileName :: {} error details :: {}", fileName, e);
		}
		return data;
	}

	private static String getTestDataFileAbsolutePath(String testDataFileRelativePath) {
		return USER_DIR + testDataFileRelativePath;
	}

}
