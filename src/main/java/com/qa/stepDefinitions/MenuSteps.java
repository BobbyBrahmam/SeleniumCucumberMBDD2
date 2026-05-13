package com.qa.stepDefinitions;

import org.junit.Assert;
import com.qa.pages.MenuPage;
import com.qa.utilities.TestBase;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MenuSteps extends TestBase {

	MenuPage menu = new MenuPage().initElements();
	
	@When("I open Menu page")
	public void i_open_menu_page() {
		menu.openMenuPage();
	}
	
	@Then("I see the menu bar")
	public void i_see_the_menu_bar() {  
	   Assert.assertTrue("Menu bar is not displayed", menu.isMenuBarDisplayed());
	}
	
	@When("I hover on the {string} main menu")
	public void i_hover_on_the_main_menu_bar(String order) {
		menu.hoverOnMainMenu(order);
	}
	
	@Then("I see the {string} main menu list")
	public void i_see_the_menu_list(String order) {			
		Assert.assertTrue(order+" main menu list is not displayed", menu.isMainMenuListDisplayed(order));
	}
	
	@When("I hover on sub menu of {string} main menu")
	public void i_hover_on_the_sub_menu(String order) {    
		menu.hoverOnSubMenu(order);
	}
	
	@Then("I see the sub menu list of {string} main menu")
	public void i_see_the_sub_menu(String order) {
	    Assert.assertTrue(order+" sub menu list is not displayed", menu.isSubMenuListDisplayed(order));
	}
}
