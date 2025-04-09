package myrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"@src/test/resources/failedrerun.txt"}, 
                     glue = {"com.qa.stepDefinitions", "com.qa.hooks" }, 
        stepNotifications = true, 
                   plugin = {"pretty",
                             "com.qa.labeler.FailedRunLabeler",
                             "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                             "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", 
                             "rerun:src/test/resources/failedrerunbackup.txt"},
               monochrome = false, 
                   dryRun = false)

public class FailedRun {
}