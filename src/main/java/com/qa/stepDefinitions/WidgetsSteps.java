package com.qa.stepDefinitions;

import com.qa.utilities.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;

import com.qa.pages.WidgetsPage;

public class WidgetsSteps extends TestBase{
	
	WidgetsPage widget = new WidgetsPage();

	@When("I open Slider page")
	public void i_open_slider_page() {
		Allure.link("Website", "https://dev.example.com/");
        Allure.issue("AUTH-123", "https://example.com/issues/AUTH-123");
        Allure.tms("TMS-456", "https://example.com/tms/TMS-456");
		widget.openSliderPage();
	}

	@Then("I see the slider")
	public void i_see_the_slider() {
		widget.isUserOnSliderPage();
	}

	@When("I drag the slider to the value {int}")
	public void I_drag_the_slider_to_the_value_n(int value) throws InterruptedException{
		widget.slideTheSliderDynamicallyToSomeValue(value);
	}

	@Then("I see the value {int} in input box")
	public void i_see_value_n_in_input_box(int value){
		widget.isValueSameAsSlided(value);
	}
	
}