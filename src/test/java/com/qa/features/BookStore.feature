@allure.label.owner=Bobby
@allure.label.epic:Epic-Book_Store_Page
@allure.label.feature:Feature-Book_Store
Feature: Book Store

Background:
  Given I navigate to ToolsQA HomePage
  When I opened the BookStore pane

  @Draft
  @allure.label.story:Verify_that_the_user_is_able_to_use_the_slider
  Scenario: Verify that the user is able to use the slider
    And I open BookStore Login page
    Then I see the username and password fields
    When I enter the username "BobyBrahmam" in Bookstore login page
    And I enter the password "TestBoby@108" in Bookstore login page
    And I click Login button
    Then I am able to login to the Bookstore search page
