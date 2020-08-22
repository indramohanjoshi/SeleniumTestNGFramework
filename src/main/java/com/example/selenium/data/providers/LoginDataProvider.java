package com.example.selenium.data.providers;

import org.testng.annotations.DataProvider;

import com.example.selenium.constants.TestDataConstant;

public class LoginDataProvider extends BaseDataProvider {
	
	private static final String IN_VALID_LOGIN_DATA_FILE = "InValidLoginData.xlsx";
	private static final String VALID_LOGIN_DATA_FILE = "ValidLoginData.xlsx";
	
	@DataProvider(name = "LoginValidDataProvider")
	public static Object[][] getDataValidLoginData() {
		return constructObjectArray(TestDataConstant.LOGIN_TEST_DATA_PATH, VALID_LOGIN_DATA_FILE, null);
	}

	@DataProvider(name = "LoginInValidDataProvider")
	public static Object[][] getDataInValidLoginData() {
		return constructObjectArray(TestDataConstant.LOGIN_TEST_DATA_PATH, IN_VALID_LOGIN_DATA_FILE, null);
	}

}