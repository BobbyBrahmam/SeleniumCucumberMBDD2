package com.qa.stepDefinitions;

import com.qa.pages.BookStorePage;
import com.qa.utilities.TestBase;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;

public class BookStoreSteps extends TestBase {

    BookStorePage read = new BookStorePage().initElements();

    @When("I open BookStore Login page")
    public void i_open_book_store_login_page() {
        read.openLoginPage();
    }

    @Then("I see the username and password fields")
    public void i_see_the_username_and_password_fields() {
        Assert.assertTrue("Username or Password fields are not as expected", read.validateLoginPage());
    }

    @When("I enter the username {string} in Bookstore login page")
    public void i_enter_the_username_in_bookstore_login_page(String username) {
        read.enterUsername(username);
    }

    @When("I enter the password {string} in Bookstore login page")
    public void i_enter_the_password_in_bookstore_login_page(String password) {
        read.enterPassword(password);
    }

    @When("I click Login button")
    public void i_click_login_button() {
        read.clickLoginButton();
    }

    @Then("I am able to login to the Bookstore search page")
    public void i_am_able_to_login_to_the_bookstore_search_page() {
        Assert.assertTrue("User is not able to login", read.isUserAbleToLogin());
    }

    @Then("I click on Logout button")
    public void i_click_on_logout_button() {
        read.clickLogoutButton();
    }

    @Then("I am able to logout")
    public void i_am_able_to_logout() {
        Assert.assertTrue("User is not able to logout", read.isUserAbleToLogout());
    }
}
