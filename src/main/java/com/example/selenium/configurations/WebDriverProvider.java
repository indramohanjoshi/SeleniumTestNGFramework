package com.example.selenium.configurations;

import org.openqa.selenium.WebDriver;

public class WebDriverProvider {

	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return webDriver.get();
	}

	public static void setWebDriver(WebDriver driver) {
		webDriver.set(driver);
	}

}