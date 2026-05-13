package com.qa.stepDefinitions;

import com.qa.pages.TextBoxFormPage;
import com.qa.utilities.TestBase;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TextBoxFormSteps extends TestBase {

    TextBoxFormPage fill = new TextBoxFormPage().initElements();

    @When("I go to text box page")
    public void i_go_to_text_box_page() {
        fill.openTextBoxFormPage();
    }

    @When("I enter the full name {string}")
    public void i_enter_the_full_name(String fullName) {
        fill.enterTheFullName(fullName);
    }

    @When("I enter the eamil address {string}")
    public void i_enter_the_eamil_address(String emailAddress) {
        fill.enterEmailAddress(emailAddress);
    }

    @When("I enter the current address {string}")
    public void i_enter_the_current_address(String currentAddress) {
        fill.enterCurrentAddress(currentAddress);
    }

    @When("I enter the permanent address {string}")
    public void i_enter_the_permanent_address(String permanentAddress) {
        fill.enterPermanentAddress(permanentAddress);
    }

    @When("I enter the details {string}, {string}, {string}, {string}")
    public void i_enter_the_details(String fullName, String emailAddress, String currentAddress, String permanentAddress) {
        fill.enterAllTheDetails(fullName, emailAddress, currentAddress, permanentAddress);
    }

    @When("I submit the form")
    public void i_submit_the_form() {
        fill.submitTheForm();
    }

    @Then("I see the details getting populated")
    public void i_see_the_details_getting_populated() {
        fill.areDetailsGettingPopulated();
    }
}