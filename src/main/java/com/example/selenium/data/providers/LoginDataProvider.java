package com.example.selenium.data.providers;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.example.selenium.constants.LoginTestConstant;
import com.example.selenium.utils.ExcelReaderUtil;

public class LoginDataProvider {

	@DataProvider(name = "LoginValidDataProvider")
	public static Object[][] getDataValidLoginData() {
		Object[][] data = null;
		try {
			data = ExcelReaderUtil.readExcel(getFilePath(), "ValidLoginData.xlsx", null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	@DataProvider(name = "LoginInValidDataProvider")
	public static Object[][] getDataInValidLoginData() {
		Object[][] data = null;
		try {
			data = ExcelReaderUtil.readExcel(getFilePath(), "InValidLoginData.xlsx", null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	private static String getFilePath() {
		return System.getProperty("user.dir") + LoginTestConstant.TEST_DATA_PATH;
	}
}