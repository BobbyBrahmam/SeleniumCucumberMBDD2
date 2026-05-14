package com.qa.reporter;

import com.qa.utilities.TestBase;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;

public class CustomCucumberFormatter extends TestBase implements EventListener {
    
    private static final String BLUE = "\u001B[34m";  // ANSI Blue
    private static final String RESET = "\u001B[0m";  // Reset color

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
    }

    private void onTestCaseStarted(TestCaseStarted event) {
        log.info("Starting scenario: " + BLUE + event.getTestCase().getName() + RESET);
    }
}