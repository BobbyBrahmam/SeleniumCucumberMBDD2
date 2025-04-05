package com.qa.stepDefinitions;

import org.junit.Assert;

import com.qa.pages.SelectMenuPage;
import com.qa.utilities.TestBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SelectMenuSteps extends TestBase {

	SelectMenuPage select = new SelectMenuPage();
	
	@When("I open Select Menu page")
	public void i_open_select_menu_page() {
	       select.openSelectMenuPage();
	}
	
	@Then("I see the bootstrap dropdown")
	public void i_see_the_bootstrap_dropdown() {
	   	   Assert.assertTrue("Bootstrap select dropdown is not displayed", select.isBootStrapMenuDisplayed());
	}
	
	@When("I select the option {string} in the bootstrap dropdown")
	public void i_select_the_option_group_option_in_the_bootstrap_dropdown(String value) {
	       select.selectTheBootstrapOption(value);
	}
	
	@Then("I see the {string} get selected")
	public void i_see_the_group_option_get_selected(String value) {
	   	   Assert.assertTrue("Selected value is not displayed", select.isSelectedValueDisplayed(value));
	}
	
	@Then("I see the old dropdown")
	public void i_see_the_old_dropdown() {
	   	   Assert.assertTrue("Old select dropdown is not displayed", select.isOldMenuDisplayed());
	}
	
	@Then("I am able to select all the options in the old dropdown")
	public void I_am_able_to_select_all_the_options_in_the_old_dropdown(DataTable colours) {
	   	Assert.assertTrue("Old select dropdown is not displayed", select.ableToSelectAllOptions(colours));
	}
	
}