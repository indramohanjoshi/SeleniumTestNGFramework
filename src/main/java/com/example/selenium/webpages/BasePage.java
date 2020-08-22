package com.example.selenium.webpages;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.selenium.configurations.WebDriverProvider;

import io.qameta.allure.Step;

@Component
public class BasePage {
	private static final int TIME_OUT_IN_SECONDS = 15;
	private static final Logger LOG = LoggerFactory.getLogger(BasePage.class);

	@Step("Finding element by locator")
	protected WebElement findElementWithWait(By locator) {
		waitUntilPresenceOfElementLocated(locator, TIME_OUT_IN_SECONDS);
		return WebDriverProvider.getDriver().findElement(locator);
	}

	@Step("Finding elements by locator")
	protected List<WebElement> findElementsWithWait(By locator) {
		waitUntilPresenceOfElementLocated(locator, TIME_OUT_IN_SECONDS);
		return WebDriverProvider.getDriver().findElements(locator);
	}

	@Step("Clicking element By locator")
	protected void clickElement(By locator) {
		waitUntilElementToBeClickable(locator, 15);
		WebDriverProvider.getDriver().findElement(locator).click();

	}

	@Step("Check if element is displayed on UI")
	protected boolean isElementDisplayed(By locator) {
		return findElementWithWait(locator).isDisplayed();
	}

	@Step("Check if elements are present in dom")
	protected boolean areElementsPresent(By locator) {
		return CollectionUtils.isNotEmpty(findElementsWithWait(locator));
	}

	@Step("Get element text")
	protected String getText(By locator) {
		waitUntilVisibilityOfElementLocated(locator, TIME_OUT_IN_SECONDS);
		return WebDriverProvider.getDriver().findElement(locator).getText();
	}

	@Step("selecting dropdown value by Value: {1}")
	protected void selectDropDownByValue(By locater, String value) {
		buildSelect(locater).selectByValue(value);
	}

	@Step("selecting dropdown value with visibleText: {1}")
	protected void selectDropDownVisibleText(By locater, String visibleText) {
		buildSelect(locater).selectByVisibleText(visibleText);
	}

	private Select buildSelect(By locater) {
		waitUntilPresenceOfElementLocated(locater, TIME_OUT_IN_SECONDS);
		return new Select(WebDriverProvider.getDriver().findElement(locater));
	}

	@Step("set text: {1} in input field after clearing the input field")
	protected void setText(By locator, String text) {
		findElementWithWait(locator).clear();
		findElementWithWait(locator).sendKeys(text);
	}

	@Step("Clicking on element using mouse actions")
	protected void clickUsingAction(By locator) {
		waitUntilElementToBeClickable(locator, TIME_OUT_IN_SECONDS);
		Actions action = new Actions(WebDriverProvider.getDriver());
		WebElement element = WebDriverProvider.getDriver().findElement(locator);
		action.moveToElement(element);
		((JavascriptExecutor) WebDriverProvider.getDriver()).executeScript("arguments[0].scrollIntoView(true);",
				element);
		action.click().build().perform();
	}

	@Step("switching to Iframe with id: {0}")
	protected void switchToFrame(String id) {
		WebDriverWait wait = getWebDriverWait(TIME_OUT_IN_SECONDS);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(id));

	}

	@Step("waiting for url to be equal to given url: {0}")
	public void waitForUrl(String url) {
		WebDriverWait wait = getWebDriverWait(TIME_OUT_IN_SECONDS);
		ExpectedCondition<Boolean> urlIsCorrect = arg0 -> WebDriverProvider.getDriver().getCurrentUrl().equals(url);
		wait.until(urlIsCorrect);
	}

	@Step("waiting for url to ends with text: {0}.")
	public void waitForUrlContains(String text) {
		WebDriverWait wait = getWebDriverWait(TIME_OUT_IN_SECONDS);
		ExpectedCondition<Boolean> urlIsCorrect = arg0 -> WebDriverProvider.getDriver().getCurrentUrl().contains(text);
		wait.until(urlIsCorrect);
	}

	@Step("Get current Url")
	public String getCurrentUrl() {
		return WebDriverProvider.getDriver().getCurrentUrl();
	}

	private void waitUntilElementToBeClickable(By locator, long timeOutInSeconds) {
		WebDriverWait wait = getWebDriverWait(timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	private void waitUntilPresenceOfElementLocated(By locator, long timeOutInSeconds) {
		WebDriverWait wait = getWebDriverWait(timeOutInSeconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	private void waitUntilVisibilityOfElementLocated(By locator, long timeOutInSeconds) {
		WebDriverWait wait = getWebDriverWait(timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	private WebDriverWait getWebDriverWait(long timeOutInSeconds) {
		return new WebDriverWait(WebDriverProvider.getDriver(), timeOutInSeconds);
	}

}