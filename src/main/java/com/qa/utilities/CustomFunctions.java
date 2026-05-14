package com.qa.utilities;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.WebElement;

public class CustomFunctions extends TestBase {
    public static void fillFormSimultaneously(Map<WebElement, String> fieldElements) {
        ExecutorService executorService = Executors.newFixedThreadPool(fieldElements.size());
        for (Map.Entry<WebElement, String> entry : fieldElements.entrySet()) {
            executorService.execute(() -> {
                try {
                    entry.getKey().sendKeys(entry.getValue());
                } catch (Exception e) {
                    log.error("Error occurred while filling the form field using 'fillFormSimultaneously(Map<WebElement, String> fieldElements)' : " + entry.getKey(), e);
                }
            });
        }
        executorService.shutdown();
    }

}