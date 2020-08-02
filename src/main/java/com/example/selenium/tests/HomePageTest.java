package com.example.selenium.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.example.selenium.configurations.TestNGWebPageFactory;
import com.example.selenium.constants.HomePageTestConstant;
import com.example.selenium.webpages.HomePage;

public class HomePageTest extends BaseTest {

	protected HomePage homePage;

	@BeforeClass()
	public void launchLoginWindow() {
		homePage = TestNGWebPageFactory.getHomePage();
	}

	@Test(priority = 1)
	@Parameters({ "paramDashboardUrl" })
	public void testHomePageWelcomeLinkAndLogOutLink(final String dashboardUrl) {
		Assert.assertEquals(homePage.getWelcomLinkText(), HomePageTestConstant.WELCOME_LINK_TEXT);
		homePage.clickOnWelcomLink();
		Assert.assertEquals(homePage.getAboutLinkText(), HomePageTestConstant.ABOUT_TEXT);
		Assert.assertEquals(homePage.getLogoutLinkText(), HomePageTestConstant.LOGOUT_TEXT);
		homePage.clickOnAboutLink();
		homePage.clickDismissAlertButton();
		Assert.assertNotEquals(driver.getCurrentUrl(), dashboardUrl);
	}

}
