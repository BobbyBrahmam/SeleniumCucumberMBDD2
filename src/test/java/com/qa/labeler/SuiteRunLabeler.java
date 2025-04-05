package com.qa.labeler;

import com.qa.utilities.TestBase;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunStarted;

public class SuiteRunLabeler extends TestBase implements ConcurrentEventListener{

     @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, event -> {
            runnerLable = "SuiteRunnerTest";  // Set flag before tests run
        });
    }

}