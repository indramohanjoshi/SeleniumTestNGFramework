package com.example.selenium.configurations;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

public class WebDriverFactory {

	private static final Logger LOG = LoggerFactory.getLogger(WebDriverFactory.class);

	public static WebDriver getWebDriverInstance(String browser, String url, long implicitlyWaitInSecond) {
		WebDriver driver = null;
		try {
			LOG.info("Instentiating web driver for bowser type :: {}", browser);
			DriverManagerType browserType = DriverManagerType.valueOf(browser);
			WebDriverManager.getInstance(browserType).setup();
			Class<?> browserClass = Class.forName(browserType.browserClass());
			driver = (WebDriver) browserClass.newInstance();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(implicitlyWaitInSecond, TimeUnit.SECONDS);
			driver.get(url);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			LOG.error("Error occured while Instentiating web driver {}", e);
		}
		return driver;
	}
}
