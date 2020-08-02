package com.example.selenium.configurations;

import com.example.selenium.webpages.HomePage;
import com.example.selenium.webpages.LoginPage;

public class TestNGWebPageFactory {

	protected static Object[] webPageFactory;

	public static Object[] InstentiateWebPages() {
		LoginPage loginPage = new LoginPage(BaseSuite.driver, BaseSuite.webDriverWait);
		HomePage homePage = new HomePage(BaseSuite.driver, BaseSuite.webDriverWait);
		webPageFactory = new Object[] { loginPage, homePage };
		return webPageFactory;
	}

	public static LoginPage getLoginPage() {
		return (LoginPage) webPageFactory[0];
	}

	public static HomePage getHomePage() {
		return (HomePage) webPageFactory[1];
	}

}
