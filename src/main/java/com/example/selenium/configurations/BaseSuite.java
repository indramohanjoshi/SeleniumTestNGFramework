package com.example.selenium.configurations;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.testng.listener.ExtentIReporterSuiteClassListenerAdapter;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

@Listeners(ExtentIReporterSuiteClassListenerAdapter.class)
public class BaseSuite {
	public static WebDriver driver;
	public static Wait<WebDriver> webDriverWait;
	public static String baseUrl;

	@BeforeSuite()
	@Parameters({ "paramBrowserType", "paramLoginUrl" })
	public void InstentiateWebDriver(String paramBrowser, String paramBaseUrl) {
		try {
			DriverManagerType browserType = DriverManagerType.valueOf(paramBrowser);
			WebDriverManager.getInstance(browserType).setup();
			Class<?> browserClass = Class.forName(browserType.browserClass());
			driver = (WebDriver) browserClass.newInstance();

			webDriverWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5))
					.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

			baseUrl = paramBaseUrl;
			TestNGWebPageFactory.InstentiateWebPages();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(baseUrl);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
