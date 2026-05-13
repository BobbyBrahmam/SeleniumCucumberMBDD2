package com.qa.stepDefinitions;

import com.qa.pages.DragAndDropSortablePage;
import com.qa.utilities.TestBase;
import io.cucumber.java.en.When;

public class DragAndDropSortableSteps extends TestBase{
	
	DragAndDropSortablePage sort = new DragAndDropSortablePage().initElements();

	@When("I open sortable page")
	public void i_open_sortable_page() {
	   sort.openSortablePage();
	}
	
	@When("I re-sort the list in reverse order")
	public void i_re_sort_the_list_in_reverse_order() {
		sort.reverseSortTheSortable();
	}
	
}
