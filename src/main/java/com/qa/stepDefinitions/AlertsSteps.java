package com.qa.stepDefinitions;

import org.junit.Assert;
import com.qa.pages.AlertsPage;
import com.qa.utilities.TestBase;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AlertsSteps extends TestBase {
   
	AlertsPage trigger = new AlertsPage().initElements();
	
	@When("I open Alerts page")
	public void i_open_alerts_page() { 
		trigger.openAlertsPage();
	}
	
	@When("I click on normal alert button")
	public void i_click_on_normal_alert_button() {    
		trigger.instigateNormalAlert();
	}
	
	@Then("I see the normal alert")
	public void i_see_the_normal_alert() {  
		Assert.assertTrue("Normal alert is not displayed", trigger.isAlertPresent("You clicked a button", 5));
	}
	
	@When("I accept the normal alert")
	public void i_accept_the_normal_alert() {  
		trigger.acceptNormalAlert();
	}
	
	@Then("I see the normal alert closed")
	public void i_see_the_normal_alert_closed() {  
		Assert.assertTrue("Alert remains open", trigger.isAlertClosed(3));
	}	
	
	@When("I click on delayed alert button")
	public void i_click_on_delayed_alert_button() {  
		trigger.instigateDelayAlert();
	}
	
	@Then("I see the delayed alert after {int} seconds")
	public void i_see_the_delayed_alert_button_after_seconds(int seconds) {  
		Assert.assertTrue("Alert is not displayed after 5 seconds", trigger.isAlertPresent("This alert appeared after 5 seconds", seconds));
	}
	
	@When("I click accept the delayed alert")
	public void i_click_accept_the_delayed_alert() {  
		trigger.acceptDelayedAlert();
	}
	
	@Then("I see the delayed alert closed")
	public void i_see_the_delayed_alert_closed() {  
		Assert.assertTrue("Alert is not closed after accepting it", trigger.isAlertClosed(2));
	}
	
	@When("I click on confirmation alert button")
	public void i_click_on_confirmation_alert_button() {	   
		trigger.instigateConformationAlert();
	}
	
	@Then("I see the confirmation alert")
	public void i_see_the_confirmation_alert_button() {   
		Assert.assertTrue("Alert not opened", trigger.isAlertPresent("Do you confirm action?", 3));
	}
	
	@When("I click ok on confirmation alert")
	public void i_click_ok_on_confirmation_alert() {   
		trigger.acceptConformationAlert();
	}
	
	@Then("I see that the confirmation alert get closed")
	public void i_see_that_the_confirmation_alert_get_closed() {
		Assert.assertTrue("Alert not closed",trigger.isAlertClosed(3));
	}
	
	@When("I click on prompt alert button")
	public void i_click_on_prompt_alert_button() {   
		trigger.instigatePromptAlert();
	}
	
	@Then("I see the prompt alert")
	public void i_see_the_prompt_alert() {    
		Assert.assertTrue("Prompt alert is not displayed", trigger.isAlertPresent("Please enter your name", 3));
	}
	
	@When("I enter some value {string} and click ok on prompt alert")
	public void i_enter_some_text_and_click_ok_on_prompt_alert(String value) { 
		trigger.enterValueInPromptAlert(value);	
		trigger.acceptPromptAlert();
	}
	
	@Then("I see the prompt alert dissapear and the text {string} diplayed")
	public void i_see_the_prompt_alert_dissapear_and_the_text_diplayed(String value) {
		Assert.assertTrue("Alert not closed",trigger.isAlertClosed(2));
		Assert.assertTrue("Text entered in prompt is not displayed in element", trigger.isValueEnteredInPromptAlertDisplayed("You entered "+value));
	}
}