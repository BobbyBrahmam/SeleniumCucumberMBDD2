package com.qa.stepDefinitions;

import org.junit.Assert;
import com.qa.pages.ResizablePage;
import com.qa.utilities.TestBase;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ResizableSteps extends TestBase {

    ResizablePage expand = new ResizablePage().initElements();

    @When("I open Resizable page")
    public void i_open_resizable_page() {
      expand.openResizablePage();
    }

    @When("I hold and drag the inner resizable equal to outer box")
    public void i_hold_and_drag_the_inner_resizable_equal_to_outer_box() {
      expand.resizeTheInnerResizable();
    }

    @Then("I see the inner box aligned with the outer box")
    public void i_see_the_inner_box_aligned_with_the_outer_box() {
      Assert.assertTrue("Box is not resized as expected",expand.isInnerBoxResized());
    }

}