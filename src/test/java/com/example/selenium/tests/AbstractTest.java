package com.example.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.example.selenium.configurations.ApplicationConfigContext;
import com.example.selenium.configurations.WebDriverProvider;
import com.example.selenium.webpages.LoginPage;

@ContextConfiguration(classes = { ApplicationConfigContext.class })
public class AbstractTest extends AbstractTestNGSpringContextTests {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractTest.class);

	@Autowired
	protected LoginPage loginPage;

	protected String userName;
	protected String password;

	@Parameters({ "userName", "password" })
	@BeforeSuite
	public void beforeTest(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	protected void performLogin() {
		loginPage.LoginToOrangeHRM(userName, password);
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		LOG.info("After suit quit driver");
		WebDriver driver = WebDriverProvider.getDriver();
		if (driver != null) {
			driver.quit();
		}
	}
}