package com.qa.stepDefinitions;

import com.qa.pages.DatePickerPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class DatePickerSteps {

    DatePickerPage schedule = new DatePickerPage().initElements();

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
        Assert.assertTrue("Date is displayed inaccurately", schedule.isTheDateMonthYearAccurate());
    }

    @When("I select the month {string}")
    public void i_select_the_month(String monthName) {
        schedule.selectTheMonth(monthName);
    }

    @When("I select the year {int}")
    public void i_select_the_year(int year) {
        schedule.selectTheYear(year);
    }

    @When("I select the date {int}")
    public void i_select_the_date(int date) {
        schedule.selectTheDate(date);
    }

    @Then("I see the same year, month and date populated")
    public void i_see_the_same_year_month_and_date_populated() {
        Assert.assertTrue("Date is not displayed accurately as expected", schedule.isDatePopulatedAccurate());
    }
}
