package com.qa.stepDefinitions;

import org.junit.Assert;
import com.qa.pages.AutoCompletePage;
import com.qa.utilities.TestBase;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AutoCompleteSteps extends TestBase {
	
	AutoCompletePage enter = new AutoCompletePage();
	
	@When("I open Auto-Complete page")
	public void i_open_auto_complete_page() {
		enter.openAutoCompletePage();
	}
	
	@When("I enter the initial letter of a word")
	public void i_enter_the_initial_letter_of_a_word() { 
	    enter.enterInitialLetterOFWord();
	}
	
	@Then("I see the related suggestions")
	public void i_see_the_related_suggestions() {    
		Assert.assertTrue("Related suggestions are not getting displayed",enter.areRelatedSuggestionsDisplayed());
	}
	
	@Then("I select the first suggestion")
	public void i_select_the_first_suggestion() {    
		enter.selectFirstSuggestion();
	}
	
	@Then("I see the selected suggestion {string} got entered")
	public void i_see_the_selected_suggestion_got_entered(String value) {
		Assert.assertTrue("Related suggestions are not getting displayed",enter.isSelectedSuggestionGotEntered(value));
	}
}