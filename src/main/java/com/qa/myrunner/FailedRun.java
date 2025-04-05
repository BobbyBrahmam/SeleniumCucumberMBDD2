package com.qa.myrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "@src/test/resources/failedrerun.txt" }, 
                     glue = {"com.qa.stepDefinitions", "com.qa.hooks" }, 
        stepNotifications = true, 
                   plugin = { "pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","rerun:src/test/resources/failedrerunbackup.txt"}, 
               monochrome = false, 
                   dryRun = false)

public class FailedRun {
}