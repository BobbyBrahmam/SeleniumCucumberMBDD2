@allure.label.owner:Bobby
@allure.label.epic:Epic-Handling_Elements
@allure.label.feature:Feature-Handling_Elements
Feature: Feature-Handling Elements

    Background:
        Given I navigate to ToolsQA HomePage

    @Regression @Test1
    @allure.label.story:Verify_that_user_is_able_to_fill_the_user_details_form
    Scenario Outline: Verify that user is able to fill the user details form
        When I open elements pane
        And I go to text box page
        And I enter the full name "<fullName>"
        And I enter the eamil address "<emailAddress>"
        And I enter the current address "<currentAddress>"
        And I enter the permanent address "<permanentAddress>"
        And I submit the form
        Then I see the details getting populated
        Examples:
            | fullName        | emailAddress             | currentAddress| permanentAddress |
            | Arjun Suravaram | arjunsuravaram@gmail.com | abc123street  | abc123street     |
            | Bobby Brahmama  | bobybrahmam@gmail.com    | qwe123street  | aksdflkstreet    |

    @Regression @Test1
    @allure.label.story:Verify_that_user_is_able_to_fill_the_user_details_parallelly_in_the_form
    Scenario Outline: Verify that user is able to fill the user details parallelly in the form
        When I open elements pane
        And I go to text box page
        And I enter the details "<fullName>", "<emailAddress>", "<currentAddress>", "<permanentAddress>"
        And I submit the form
        Then I see the details getting populated
        Examples:
            | fullName          | emailAddress             | currentAddress | permanentAddress |
            | Arjun Suravaram   | arjunsuravaram@gmail.com | abc123street   | abc123street     |
            | Bobby Brahmam     | bobybrahmam@gmail.com    | qwe123street   | aksdflkstreet    |

    @Regression @Rerun @Test2
    @allure.label.story:Verify_that_the_user_is_able_to_delete_all_existing_records_in_data_table
    Scenario: Verify that the user is able to delete all existing records in data table
        When I open elements pane
        And I open WebTables page
        Then I see the existing records
        And I delete all existing records
        Then All records are deleted

    @Regression
    @allure.label.story:Verify_that_user_is_able_to_add_a_new_records
    Scenario Outline: Verify that user is able to add a new records
        When I open elements pane
        And I open WebTables page
        And I open the registration form
        And I fill the registration form with the details "<First Name>", "<Last Name>", "<Email>", <Age>, <Salary>, "<Department>"
        And I submit the registratin form
        Then I see the record with the details "<First Name>", "<Last Name>", "<Email>", <Age>, <Salary>, "<Department>" added
            | First Name | Last Name | Email | Age | Salary | Department |
        Examples:
            | First Name | Last Name      | Email                    | Age | Salary | Department |
            | Dhoni      | Mahendra Singh | dhoni@gmail.com          | 30  | 50000  | Coaching   |
            | Kohli      | Virat          | viratkohli@yahoomail.com | 31  | 10000  | Finance    |
            | Rohit      | Sharma         | rohitsharma200@gmail.com | 29  | 70000  | HR         |

    @Regression @Draft @Test2
    @allure.label.story:Verify_that_user_is_able_to_download_and_upload_a_file
    Scenario: Verify that user is able to download and upload a file
        When I open elements pane
        And I open upload-download page
        And I download the file
        Then I see the file got downloaded
        When I upload the file
        Then I see the file got uploaded