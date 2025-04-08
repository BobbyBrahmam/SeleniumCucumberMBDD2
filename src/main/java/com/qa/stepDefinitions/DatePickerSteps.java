package com.qa.stepDefinitions;

import com.qa.pages.DatePickerPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class DatePickerSteps {

    DatePickerPage schedule = new DatePickerPage();

    @When("I open Date Picker page")
    public void i_open_date_picker_page() {
        schedule.openDatePickerPage();
    }

    @When("I enter the date {string}")
    public void i_enter_the_date(String dateValue) {
        schedule.enterTheDate(dateValue);
    }

    @Then("I see the same year, month and date selected")
    public void i_see_the_same_year_month_and_date_selected() {
        Assert.assertTrue("Date is displayed inaccurately",schedule.isTheDateMonthYearAccurate());
    }
}
