package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.qa.utilities.Action;
import com.qa.utilities.TestBase;
import com.qa.utilities.Validations;

public class AlertsPage extends TestBase {

	@FindBy(how = How.XPATH, using = "//span[text()='Alerts']")
	public WebElement alertsPageLink;

	@FindBy(how = How.CSS, using = "[id='alertButton']")
	public WebElement normalAlertButton;

	@FindBy(how = How.CSS, using = "[id='timerAlertButton']")
	public WebElement delayAlertButton;

	@FindBy(how = How.CSS, using = "[id='confirmButton']")
	public WebElement confirmationAlertButton;

	@FindBy(how = How.CSS, using = "[id='promtButton']")
	public WebElement inputPromptAlertButton;

	@FindBy(how = How.CSS, using = "span[id='promptResult']")
	public WebElement textEnteredInPromptAlert;

	public AlertsPage() {
		PageFactory.initElements(driver, this);
	}

	public void openAlertsPage() {
		Action.scrollDownFluentlyTillElementVisible(alertsPageLink);
		Action.click(alertsPageLink, 1);
	}

	public void instigateNormalAlert() {
		Action.scrollDownFluentlyTillElementVisible(normalAlertButton);
		Action.click(normalAlertButton, 1);
	}

	public Boolean isAlertPresent(String alertText, int wait) {
		return Validations.validateTextOfAlertFluently(alertText, wait, 1);
	}

	
	public Boolean isAlertClosed(int wait) {
		return Validations.validateAbsenceOfAlert(wait);
	}
	
	public void instigateDelayAlert() {
		Action.click(delayAlertButton, 1);
	}

	public void instigateConformationAlert() {
		Action.click(confirmationAlertButton, 1);
	}

	public void acceptNormalAlert() {
		Action.acceptAlert();
	}

	public void acceptConformationAlert() {
		Action.acceptAlert();
	}

	public void instigatePromptAlert() {
		Action.click(inputPromptAlertButton, 1);
	}

	public void enterValueInPromptAlert(String value) {
		Action.enterTheValueInPromptAlert(value);
	}

	public void acceptDelayedAlert() {
		Action.acceptAlert();
	}

	public void acceptPromptAlert() {
		Action.acceptAlert();
	}

	public Boolean isValueEnteredInPromptAlertDisplayed(String expectedValue) {
		return Validations.validateTextOfElement(textEnteredInPromptAlert, expectedValue, 1);
	}
}
