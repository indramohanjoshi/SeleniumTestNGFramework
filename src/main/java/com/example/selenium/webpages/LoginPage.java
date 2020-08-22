package com.example.selenium.webpages;

import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginPage {

	@Autowired
	BasePage basePage;

	private static final By USER_NAME = By.xpath("//*[@id=\"txtUsername\"]");
	private static final By PASSWORD = By.xpath("//*[@id=\"txtPassword\"]");
	private static final By LOGIN_BUTTON = By.xpath("//*[@id=\"btnLogin\"]");

	public void LoginToOrangeHRM(String userName, String password) {
		basePage.setText(USER_NAME, userName);
		basePage.setText(PASSWORD, password);
		basePage.clickElement(LOGIN_BUTTON);
	}

	public String getCurrentUrl() {
		return basePage.getCurrentUrl();
	}

}
