package com.example.selenium.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.selenium.configurations.TestNGWebPageFactory;
import com.example.selenium.constants.LoginTestConstant;
import com.example.selenium.webpages.LoginPage;

public class LoginTest extends BaseTest {

	protected LoginPage loginPage;

	@BeforeClass()
	public void launchLoginWindow() {
		loginPage = TestNGWebPageFactory.getLoginPage();
	}

	@Test(dataProvider = "LoginInValidDataProvider", dataProviderClass = com.example.selenium.data.providers.LoginDataProvider.class, priority = 1)
	public void testAuthenticationWhenEmptyUsername(String userName, String password, String dashboardUrl) {
		loginPage.reset();
		loginPage.setPassword(password);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getSpanMessage(), LoginTestConstant.USER_NAME_REQUIRED);
		Assert.assertNotEquals(driver.getCurrentUrl(), dashboardUrl);
	}

	@Test(dataProvider = "LoginInValidDataProvider", dataProviderClass = com.example.selenium.data.providers.LoginDataProvider.class, priority = 2)
	public void testAuthenticationWhenEmptyPassword(String userName, String password, String dashboardUrl) {
		loginPage.reset();
		loginPage.setUserName(userName);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getSpanMessage(), LoginTestConstant.PASSWORD_REQUIRED);
		Assert.assertNotEquals(driver.getCurrentUrl(), dashboardUrl);
	}

	@Test(dataProvider = "LoginInValidDataProvider", dataProviderClass = com.example.selenium.data.providers.LoginDataProvider.class, priority = 3)
	public void testAuthenticationWhenInValidCredential(String userName, String password, String dashboardUrl) {
		loginPage.reset();
		loginPage.LoginToOrangeHRM(userName, password);
		Assert.assertEquals(loginPage.getSpanMessage(), LoginTestConstant.INVALID_CREDENTIAL);
		Assert.assertNotEquals(driver.getCurrentUrl(), dashboardUrl);
	}

	@Test(dataProvider = "LoginValidDataProvider", dataProviderClass = com.example.selenium.data.providers.LoginDataProvider.class, priority = 4)
	public void testAuthenticationWhenValidCredential(String userName, String password, String dashboardUrl) {
		loginPage.reset();
		loginPage.LoginToOrangeHRM(userName, password);
		Assert.assertEquals(driver.getCurrentUrl(), dashboardUrl);
	}

}
