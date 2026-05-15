@allure.label.owner:Bobby
@allure.label.epic:Epic-Alerts_and_Frames
@allure.label.feature:Feature-Handle_Alerts_and_Frames_and_Windows
Feature: Feature-Handle Alerts and Frames and Windows

    Background:
        Given I navigate to ToolsQA HomePage

    @Regression @ReRun
    @allure.label.story:User_is_able_to_handle_all_kinds_of_alerts
    Scenario Outline: User is able to handle all kinds of alerts
        When I open AlertsFramesAndWindows pane
        And I open Alerts page
        And I click on normal alert button
        Then I see the normal alert
        When I accept the normal alert
        Then I see the normal alert closed
        When I click on delayed alert button
        Then I see the delayed alert after 5 seconds
        When I click accept the delayed alert
        Then I see the delayed alert closed
        When I click on confirmation alert button
        Then I see the confirmation alert
        When I click ok on confirmation alert
        Then I see that the confirmation alert get closed
        When I click on prompt alert button
        Then I see the prompt alert
        When I enter some value "<value>" and click ok on prompt alert
        Then I see the prompt alert dissapear and the text "<value>" diplayed
        Examples:
          | value |
          | Bobby |


    @Regression @TestOne
    @allure.label.story:User_is_able_to_do_window_handling
    Scenario: User is able to do window handling
        When I open AlertsFramesAndWindows pane
        And I open Browser Windows page
        And I click on new tab button
        And I try switching to new tab
        Then I am able to switch to new tab
        And I try switching to main tab
        Then I am able to switch to main tab
        When I click on new window button
        And I try switching to new window
        Then I am able to switch to new window
        When I try switching back to main window
        Then I am able to switch to main window