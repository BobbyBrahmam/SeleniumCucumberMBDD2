package com.qa.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.qa.utilities.Action;
import com.qa.utilities.TestBase;
import com.qa.utilities.Validations;

public class BrowserWindowsPage extends TestBase {

	@FindBy(how = How.XPATH, using = "//span[text()='Browser Windows']")
	public WebElement browserWindowsPageLink;

	@FindBy(how = How.CSS, using = "[id='tabButton']")
	public WebElement newTabButton;

	@FindBy(how = How.CSS, using = "[id='sampleHeading']")
	public WebElement newTabOrWindowText;

	@FindBy(how = How.CSS, using = "[id='windowButton']")
	public WebElement newWindowButton;

	String parentWindow;
	ArrayList<String> tabs;
	Set<String> handles;
	List<String> hList;

	public BrowserWindowsPage() {
		PageFactory.initElements(driver, this);
	}

	public void openBrowserWindowsPage() {
		Action.click(browserWindowsPageLink, 1);
	}

	public void instigateNewTab() {
		Action.scrollDownFluentlyTillElementVisible(newTabButton);
		Action.click(newTabButton, 1);
		tabs = new ArrayList<String>(driver.getWindowHandles());
	}

	public void switchToNewTab() {
		driver.switchTo().window(tabs.get(1));
	}

	public void switchBackToMainTab() {
		driver.switchTo().window(tabs.get(0));
	}

	public Boolean isTextAvailable() {
		return Validations.validateTextOfElement(newTabOrWindowText, "This is a sample page", 1);
	}

	public void instigateNewWindow() {
		parentWindow = driver.getWindowHandle();
		Action.click(newWindowButton, 1);
		handles = driver.getWindowHandles();
		hList = new ArrayList<String>(handles);
	}

	public void switchToNewWindow(String windowUrl) {
		String url = "";
		for (String e : hList) {
			url = driver.switchTo().window(e).getCurrentUrl();
			if (url.equals(windowUrl))
				break;
			else
				continue;
		}
	}

	public void switchBackToMainWindow() {
		driver.switchTo().window(parentWindow);
	}

	public Boolean validateCurrentWindow(String url) {
		return url.equals(driver.getCurrentUrl());
	}

}