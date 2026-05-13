package com.qa.stepDefinitions;

import com.qa.utilities.*;
import com.qa.pages.DragAndDropPage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class DragAndDropSteps extends TestBase {

	DragAndDropPage drop = new DragAndDropPage().initElements();

	@When("I open Droppable page")
	public void i_open_droppable_page() {
		drop.openDroppablePage();
	}

	@Then("I see the drag and drop elements")
	public void i_see_the_drag_and_drop_elements() {
		Assert.assertTrue("There are no elements to drag and drop", drop.isUserOnDragAndDropPage());
	}
	
	@When("I drag and drop the element")
	public void i_drag_and_drop_the_element() {
		drop.dragAndDrop();
	}

	@Then("I see element got dropped displaying the message {string}")
	public void i_see_element_got_dropped(String expectedValue) {
		Assert.assertTrue("", drop.validateDragAndDrop(expectedValue));
	}

}
