package com.example.selenium.listners;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.example.selenium.configurations.WebDriverFactory;
import com.example.selenium.configurations.WebDriverProvider;

import io.qameta.allure.Attachment;

public class WebDriverListner implements IInvokedMethodListener {
	private static final int THREAD_SLEEP_MILI = 2000;
	private static final Logger LOG = LoggerFactory.getLogger(WebDriverListner.class);

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("paramBrowserType");
			String url = method.getTestMethod().getXmlTest().getLocalParameters().get("paramLoginUrl");
			int timeout = Integer.parseInt(method.getTestMethod().getXmlTest().getLocalParameters().get("timeout"));
			WebDriver driver = WebDriverFactory.getWebDriverInstance(browserName, url, timeout);
			WebDriverProvider.setWebDriver(driver);
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			try {
				WebDriver driver = WebDriverProvider.getDriver();
				if (driver != null) {
					Thread.sleep(THREAD_SLEEP_MILI);
					if (!testResult.isSuccess()) {
						saveScreenshotPNG(driver);
					}
					driver.close();
				}
			} catch (InterruptedException | NoSuchSessionException e) {
				LOG.error("Error occured while closing driver :: {}", e);
			}
		}
	}

	@Attachment(value = "page Screenshot", type = "image/png")
	public static byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
}