package com.example.selenium.webpages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class HomePage {

	public HomePage(WebDriver driver, Wait<WebDriver> webDriverWait) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.webDriverWait = webDriverWait;
	}
	
	private WebDriver driver;
	private Wait<WebDriver> webDriverWait;

	@FindBy(xpath = "//*[@id=\"welcome\"]")
	WebElement welcomeLink;
	
	@FindBy(xpath = "//*[@id=\"aboutDisplayLink\"]" )
	WebElement aboutLink;
	
	@FindBy(xpath = "//*[@id=\"displayAbout\"]/div/a")
	WebElement dismissAlertButton;
	
	@FindBy(xpath = "//*[@id=\"welcome-menu\"]/ul/li[2]/a" )
	WebElement logoutLink;

	public void clickOnWelcomLink() {
		this.welcomeLink.click();
	}
	
	public String getWelcomLinkText() {
		return webDriverWait.until(ExpectedConditions.visibilityOf(welcomeLink)).getText();
		//return this.welcomeLink.getText();
	}
	
	public String getAboutLinkText() {
		return webDriverWait.until(ExpectedConditions.visibilityOf(aboutLink)).getText();
		//return this.welcomeLink.getText();
	}
	
	public String getLogoutLinkText() {
		return webDriverWait.until(ExpectedConditions.visibilityOf(logoutLink)).getText();
		//return this.welcomeLink.getText();
	}
	
	public void clickOnAboutLink() {
		this.aboutLink.click();	
	}
	
	public String swithToAlertAndGetText() {
		return this.driver.switchTo().alert().getText();
	}
	
	public void dismissAlert() {
		this.driver.switchTo().alert().dismiss();
	}
	
	public void clickDismissAlertButton() {
		this.dismissAlertButton.click();
	}
	
	

}
