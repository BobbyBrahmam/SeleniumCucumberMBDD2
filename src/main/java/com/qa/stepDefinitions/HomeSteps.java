package com.qa.stepDefinitions;

import com.qa.pages.HomePage;
import com.qa.utilities.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class HomeSteps extends TestBase {

    HomeSteps home;
    HomePage homePage;

    @Given("I navigate to ToolsQA HomePage")
    public void i_navigate_to_toolsqa_homepage() {
        TestBase.initialization();
        home = new HomeSteps();
        homePage = new HomePage();
    }

    @When("I open widgets pane")
    public void i_open_widgets_pane() {
        homePage.openWidgetsPane();
    }

    @When("I open interactions pane")
    public void i_open_interactions_pane() {
        homePage.openInteractionsPane();
    }

    @When("I open elements pane")
    public void i_open_elements_pane() {
        homePage.openElementsPane();
    }

    @When("I open AlertsFramesAndWindows pane")
    public void i_open_alerts_frames_and_windows_pane() {
        homePage.openAlertsFramesAndWindowsPane();
    }

    @When("I opened the BookStore pane")
    public void i_opened_the_book_store_pane() {
        homePage.openBookStorePane();
    }
}