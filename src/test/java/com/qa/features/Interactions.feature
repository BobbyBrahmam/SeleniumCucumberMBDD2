@allure.label.owner:Bobby
@allure.label.epic:Epic-Interactions_Page
@allure.label.feature:Feature-Handling_Interactions
Feature: Feature-Handling Interactions

    Background:
        Given I navigate to ToolsQA HomePage
        When I open interactions pane

    @Regression @Draft
    @allure.label.story:Verify_that_user_is_able_to_sort_the_number_list
    Scenario: Verify that user is able to sort the number list
        And I open sortable page
        And I re-sort the list in reverse order

    @ReRun @TestOne @NotAutomatable
    @allure.label.story:Verify_that_the_user_is_able_to_drag_and_drop
    Scenario: Verify that the user is able to drag and drop
        And I open Droppable page
        Then I see the drag and drop elements
        And I drag and drop the element
        Then I see element got dropped displaying the message "Dropped!"

    @Regression
    @allure.label.story:Verify_that_the_user_is_able_to_resize_the_inner_box_equal_to_the_outer_box
    Scenario: Verify that the user is able to resize the inner box equal to the outer box
        And I open Resizable page
        And I hold and drag the inner resizable equal to outer box
        Then I see the inner box aligned with the outer box