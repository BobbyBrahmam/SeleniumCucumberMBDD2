package com.qa.reporter;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;

public class CustomCucumberFormatter implements EventListener {
    
    private static final String BLUE = "\u001B[34m";  // ANSI Blue
    private static final String RESET = "\u001B[0m";  // Reset color

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
    }

    private void onTestCaseStarted(TestCaseStarted event) {
        //String tags = String.join(", ", event.getTestCase().getTags());
        //System.out.println("\033[33m" + tags + "\033[0m");  
        System.out.println(BLUE + event.getTestCase().getName() + RESET);
    }
}