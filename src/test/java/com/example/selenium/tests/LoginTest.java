package com.example.selenium.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


public class LoginTest extends AbstractTest {
	
	@Test(dataProvider = "LoginValidDataProvider", dataProviderClass = com.example.selenium.data.providers.LoginDataProvider.class, priority = 1)
	@Description("Login to Orange HRM using userName and Password")
	@Severity(value = SeverityLevel.BLOCKER)
	public void testAuthenticationWhenValidCredential(String userName, String password, String dashboardUrl) {
		loginPage.LoginToOrangeHRM(userName, password);
		Assert.assertEquals(loginPage.getCurrentUrl(), dashboardUrl);
	}

}
