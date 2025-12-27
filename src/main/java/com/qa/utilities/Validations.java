package com.qa.utilities;

import java.io.File;
import java.util.List;
import java.util.Objects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Validations extends TestBase {

	static Actions actions = null;

	public static Boolean validateTextOfElement(WebElement element, String expectedValue, int wait) {
		try {
			return WaitForElement.waitForVisibilityOf(element, wait).getText().equals(expectedValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Boolean validateAttributeValueOfElement(WebElement element, String attribute, String expectedValue,
			int wait) {
		try { 
			String actualValue = WaitForElement
                .waitForVisibilityOf(element, wait)
                .getDomAttribute(attribute);
				return Objects.equals(actualValue, expectedValue);
			//return WaitForElement.waitForVisibilityOf(element, wait).getDomAttribute(attribute).equals(expectedValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Boolean validateTextOfAlertFluently(String expectedValue, int maxWait, int polling) {
		boolean check = false;
		try {
			check = WaitForElement.fluentWaitForVisibilityOfAlert(maxWait, polling).getText().trim()
					.equals(expectedValue);
			return check;
		} catch (NoAlertPresentException e) {
			actions = new Actions(driver);
			actions.keyDown(Keys.ESCAPE);
			return check;
		}
	}

	public static Boolean validateAbsenceOfAlert(int wait) {
		try {
			WaitForElement.waitForVisibilityOfAlert(wait);
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	public static Boolean validateVisibilityOfElement(WebElement element, int wait) {
		try {
			return WaitForElement.waitForVisibilityOf(element, wait).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Boolean validateVisibilityOfElements(List<WebElement> element, int wait) {
		try {
			return WaitForElement.waitForVisibilityOfWebElements(element, wait).size() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Boolean validateTheDownloaded(File folder, int max, int polling) {
		try {
			return WaitForElement.fluentWaitForDowloadOf(folder, max, polling);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean validateTheCurrentPageUrl(String expectedURL) {
		return Objects.equals(driver.getCurrentUrl(), expectedURL);
	}

}