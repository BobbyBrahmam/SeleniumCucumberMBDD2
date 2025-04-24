package com.qa.myrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/com/qa/features/Widgets.feature"}, 
                     glue = {"com.qa.stepDefinitions", "com.qa.hooks"}, 
                     tags = "@Draft", 
        stepNotifications = true, 
                   plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "rerun:src/test/resources/failedrerun.txt"},
               monochrome = true, 
                   dryRun = false)
                    
public class SuiteRunner {
}