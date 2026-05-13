package com.qa.stepDefinitions;

import org.junit.Assert;

import com.qa.pages.ToolTipsPage;
import com.qa.utilities.TestBase;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ToolTipSteps extends TestBase {

    ToolTipsPage tip = new ToolTipsPage().initElements();

    @When("I open Tool Tips page")
    public void i_open_tool_tips_page() {
        tip.goToToolTipsPage();
    }

    @When("I hover on the given button")
    public void i_hover_on_the_given_button() {
        tip.hoverOnHoverMeButton();
    }

    @Then("I see the tool tip with the tip message {string}")
    public void i_see_the_tool_tip_with_the_tip_message(String expectedMessageText) {
        Assert.assertTrue("Tool Tip message is not as expected", tip.isToolTipMessageAccurate(expectedMessageText));
    }
}
