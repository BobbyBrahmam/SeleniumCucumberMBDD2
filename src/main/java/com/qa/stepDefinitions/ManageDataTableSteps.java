package com.qa.stepDefinitions;

import org.junit.Assert;
import com.qa.pages.ManageDataTablePage;
import com.qa.utilities.TestBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class ManageDataTableSteps extends TestBase {

	ManageDataTablePage manage = new ManageDataTablePage().initElements();

	@When("I open WebTables page")
	public void i_open_webtables_page() {
		manage.openWebElementsTablePage();
	}

	@Then("I see the existing records")
	public void i_see_the_existing_records() {
		Assert.assertTrue("User couldn't navigate to WebTables Page", manage.isUserOnWebTablePage());
	}

	@When("I delete all existing records")
	public void i_delete_all_existing_records() {
		manage.deleteAllExistingRecords();
	}

	@Then("All records are deleted")
	public void all_records_are_deleted() {
		Assert.assertTrue("User couldn't delete all records", manage.isUserAbleToDeleteRecords());
	}

	@When("I open the registration form")
	public void i_open_the_registration_form() {
		manage.openRegistrationForm();
	}

	@When("I fill the registration form with the details {string}, {string}, {string}, {int}, {int}, {string}")
	public void I_fill_the_registration_form_with_the_details(String firstName, String lastName, String email, int age,
			int salary, String department) {
		manage.fillRegistrationForm(firstName, lastName, email, age, salary, department);
	}

	@When("I submit the registratin form")
	public void i_submit_the_registratin_form() {
		manage.submitRegistraionForm();
	}

	@Then("I see the record with the details {string}, {string}, {string}, {int}, {int}, {string} added")
	public void i_see_the_record_with_the_details_added(String firstName, String lastName, String email, int age, int salary, String department, DataTable table) {
		Assert.assertTrue("Details are not added as expected", manage.areDetailsGotAdded(firstName, lastName, email, age, salary, department, table));
	}
}