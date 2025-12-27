@allure.label.owner=Bobby
@allure.label.epic:Epic-Widgets_Page
@allure.label.feature:Feature-Handle_Widgets
Feature: Feature-Handle Widgets

  This test attempts to create a label with specified title

  Background:
    Given I navigate to ToolsQA HomePage
    When I open widgets pane

  @NotAuotomatable
  @allure.label.story:Verify_that_the_user_is_able_to_use_the_slider
  Scenario: Verify that the user is able to use the slider
    And I open Slider page
    Then I see the slider
    When I drag the slider to the value 65
    Then I see the value 65 in input box

  @Regression
  @allure.label.story:Verify_that_the_user_is_able_to_select_the_sub-menu_item
  Scenario: Verify that the user is able to select the sub-menu item
    And I open Menu page
    Then I see the menu bar
    When I hover on the "second" main menu
    Then I see the "second" main menu list
    When I hover on sub menu of "second" main menu
    Then I see the sub menu list of "second" main menu

  @Regression @TestOne
  @allure.label.story:Verify_that_the_user_is_able_to_select_the_auto_complete_suggestions
  Scenario: Verify that the user is able to select the auto complete suggestions
    And I open Auto-Complete page
    When I enter the initial letter of a word
    Then I see the related suggestions
    And I select the first suggestion
    Then I see the selected suggestion "Black" got entered

  @Regression @Retest
  @allure.label.story:Verify_that_the_user_is_able_to_select_the_values_from_bootstrap_dropdrown
  Scenario Outline: Verify that the user is able to select the values from bootstrap dropdrown
    And I open Select Menu page
    Then I see the bootstrap dropdown
    When I select the option <value> in the bootstrap dropdown
    Then I see the <value> get selected
    Examples:
      | value                 |
      | "Group 1, option 1"   |
      | "Group 1, option 2"   |
      | "Group 2, option 1"   |
      | "Group 2, option 2"   |
      | "A root option"       |
      | "Another root option" |

  @Regression
  @allure.label.story:Verify_that_the_user_is_able_to_select_the_values_from_old_dropdrown
  Scenario: Verify that the user is able to select the values from old dropdrown
    And I open Select Menu page
    Then I see the old dropdown
    And I am able to select all the options in the old dropdown
      | value   |
      | Red     |
      | Blue    |
      | Green   |
      | Yellow  |
      | Purple  |
      | Black   |
      | White   |
      | Voilet  |
      | Indigo  |
      | Magenta |
      | Aqua    |

  @Regression
  @allure.label.story:Verify_that_the_user_is_able_to_see_the_tooltip_after_hovering_on_the_button
  Scenario: Verify that the user is able to see the tooltip after hovering on the button
    And I open Tool Tips page
    And I hover on the given button
    Then I see the tool tip with the tip message "You hovered over the Button"

  @Regression
  @allure.label.story:Verify_that_the_user_is_able_to_select_the_date_and_time
  Scenario: Verify that the user is able to select the date and time
    And I open Date Picker page
    And I enter the date "02/28/2014"
    Then I see the same year, month and date selected

  @Regression @Draft
  @allure.label.story:Verify_that_the_user_is_able_to_select_the_date_and_time_by_using_dropdowns
  Scenario: Verify that the user is able to select the date and time by using dropdowns
    And I open Date Picker page
    And I select the month "February"
    And I select the year 2023
    And I select the date 1 
    Then I see the same year, month and date populated