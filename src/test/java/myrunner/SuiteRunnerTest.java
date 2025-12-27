package myrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/com/qa/features/"}, 
                     glue = {"com.qa.stepDefinitions", "com.qa.hooks"}, 
                     tags = "@TestOne", 
        stepNotifications = true, 
                   plugin = {
                             "pretty", 
                             "com.qa.labeler.SuiteRunLabeler",
                             "com.qa.reporter.CustomCucumberFormatter", 
                             "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", 
                             "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", 
                             "rerun:src/test/resources/failedrerun.txt"
                            },
               monochrome = false, 
                   dryRun = false)

public class SuiteRunnerTest {
}