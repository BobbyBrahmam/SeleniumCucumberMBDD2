package com.qa.stepDefinitions;

import org.junit.Assert;

import com.qa.pages.BrowserWindowsPage;
import com.qa.utilities.TestBase;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BrowserWindowsSteps extends TestBase {

	BrowserWindowsPage window = new BrowserWindowsPage().initElements();
	
	@When("I open Browser Windows page")
	public void i_open_browser_windows_page() {
		window.openBrowserWindowsPage();
	}
	
	@When("I click on new tab button")
	public void i_click_on_new_tab_windows() {
	    window.instigateNewTab();  
	}
	
	@When("I try switching to new tab")
	public void i_switch_to_new_tab() {
	    window.switchToNewTab();
	}
	
	@Then("I am able to switch to new tab")
	public void i_am_able_to_switch_to_new_tab() {
		Assert.assertTrue("Not able get the text",window.isTextAvailable());
	}
	
	@When("I try switching to main tab")
	public void i_switch_to_main_tab() {
	    window.switchBackToMainTab();
	}
		
	@Then("I am able to switch to main tab")
	public void i_am_able_to_switch_to_main_tab() {
		Assert.assertTrue("Couldn't switch back to main tab", window.validateCurrentWindow(prop.getProperty("browserWindowsPageURL")));
	}
	
	@When("I click on new window button")
	public void i_click_on_new_windows_button() {
		window.instigateNewWindow();
	}
	
	@When("I try switching to new window")
	public void i_try_switching_to_new_window() {
		window.switchToNewWindow(prop.getProperty("browserWindowsSamplePageURL"));
	}
	
	@Then("I am able to switch to new window")
	public void i_am_able_to_switch_to_new_window() {
	Assert.assertTrue("Couldn't switch to new window",window.isTextAvailable());
	}
	
	@When("I try switching back to main window")
	public void i_try_switching_back_to_main_window() {
		window.switchBackToMainWindow();
	}
	
	@Then("I am able to switch to main window")
	public void i_am_able_to_switch_to_main_window() {
	   Assert.assertTrue("Couldn't switch back to main window", window.validateCurrentWindow(prop.getProperty("browserWindowsPageURL")));
	}
}