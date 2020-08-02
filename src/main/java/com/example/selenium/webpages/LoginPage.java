package com.example.selenium.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

public class LoginPage {

	public LoginPage(WebDriver driver, Wait<WebDriver> webDriverWait) {
		PageFactory.initElements(driver, this);
		this.webDriverWait = webDriverWait;
		this.driver = driver;
	}

	//In case fluent wait need to be used for any webElement use webDriverWait.
	private Wait<WebDriver> webDriverWait;
	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"txtUsername\"]")
	WebElement userName;

	@FindBy(xpath = "//*[@id=\"txtPassword\"]")
	WebElement password;

	@FindBy(xpath = "//*[@id=\"btnLogin\"]")
	WebElement loginButton;

	@FindBy(xpath = "//*[@id=\"spanMessage\"]")
	WebElement loginMessage;

	public void setUserName(String userName1) {
		// webDriverWait.until(ExpectedConditions.visibilityOf(userName)).sendKeys(userName1);
		this.userName.sendKeys(userName1);
	}

	public void setPassword(String password1) {
		// webDriverWait.until(ExpectedConditions.visibilityOf(password)).sendKeys(password1);
		this.password.sendKeys(password1);
	}

	public void clickLoginButton() {
		// webDriverWait.until(ExpectedConditions.visibilityOf(loginButton)).click();
		loginButton.click();
	}

	public String getSpanMessage() {
		// return
		// webDriverWait.until(ExpectedConditions.visibilityOf(loginMessage)).getText();
		return loginMessage.getText();
	}

	public void LoginToOrangeHRM(String userName1, String password1) {
		this.userName.sendKeys(userName1);
		this.password.sendKeys(password1);
		loginButton.click();
		/*
		 * webDriverWait.until(ExpectedConditions.visibilityOf(userName)).sendKeys(
		 * userName1);
		 * webDriverWait.until(ExpectedConditions.visibilityOf(password)).sendKeys(
		 * password1);
		 * webDriverWait.until(ExpectedConditions.visibilityOf(loginButton)).click();
		 */
	}

	public void reset() {
		this.userName.clear();
		this.password.clear();
		// webDriverWait.until(ExpectedConditions.visibilityOf(userName)).clear();
		// webDriverWait.until(ExpectedConditions.visibilityOf(password)).clear();
	}

}
