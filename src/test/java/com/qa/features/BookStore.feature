@allure.label.owner=Bobby
@allure.label.epic:Epic-Book_Store_Page
@allure.label.feature:Feature-Book_Store
Feature: Feature-Book Store

Background:
  Given I navigate to ToolsQA HomePage
  When I opened the BookStore pane

  @Regression @TestOne
  @allure.label.story:Verify_that_the_user_is_able_to_login_to_the_Bookstore_search_page
  Scenario: Verify that the user is able to login to the Bookstore search page
    And I open BookStore Login page
    Then I see the username and password fields
    When I enter the username "BobyBrahmam" in Bookstore login page
    And I enter the password "TestBoby@108" in Bookstore login page
    And I click Login button
    Then I am able to login to the Bookstore search page

  @Regression @Draft
  @allure.label.story:Verify_that_the_user_is_able_to_logout_of_the_Bookstore_search_page
  Scenario: Verify that the user is able to logout of the Bookstore search page
    And I open BookStore Login page
    Then I see the username and password fields
    When I enter the username "BobyBrahma" in Bookstore login page
    And I enter the password "TestBoby@108" in Bookstore login page
    And I click Login button
    Then I am able to login to the Bookstore search page
    And I click on Logout button
    Then I am able to logout