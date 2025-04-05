package com.qa.utilities;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitForElement extends TestBase {

	static final int MAX_TIME = 10;
	static final int POLLING = 1;

	public static void waitForHomepageToSettle() {
		new FluentWait<>(driver)
			.withTimeout(Duration.ofSeconds(10))
			.pollingEvery(Duration.ofMillis(500))
			.ignoring(Exception.class)
			.until(d -> {
				String state = ((JavascriptExecutor) d).executeScript("return document.readyState").toString();
				return state.equals("interactive") || state.equals("complete");
			});
	}

	public static void waitUntilPageReadyAndElementVisible(WebElement element, int timeoutInSeconds) {
		new FluentWait<>(driver)
			.withTimeout(Duration.ofSeconds(timeoutInSeconds))
			.pollingEvery(Duration.ofMillis(500))
			.ignoring(Exception.class)
			.until(driver -> {
				String state = ((JavascriptExecutor) driver).executeScript("return document.readyState").toString();
				boolean domReady = state.equals("interactive") || state.equals("complete");
				boolean elementVisible = element.isDisplayed();
				return domReady && elementVisible;
			});
	}

	public static WebElement waitForPresence(By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_TIME));
		return wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}

	public static WebElement waitForClickability(WebElement element, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static Boolean textPresence(WebElement element, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_TIME));
		return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	public static WebElement waitForVisibilityOf(WebElement element, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static Alert waitForVisibilityOfAlert(int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public static Alert fluentWaitForVisibilityOfAlert(int maxWaitDuration, int polling) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(maxWaitDuration))
		.pollingEvery(Duration.ofSeconds(polling)).ignoring(NoAlertPresentException.class);
         return wait.until(ExpectedConditions.alertIsPresent());
	}

	public static List<WebElement> waitForVisibilityOfWebElements(List<WebElement> element, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		return wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public static WebElement fluentWaitForVisibilityOf(WebElement element, int max, int polling) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(max))
				.pollingEvery(Duration.ofSeconds(polling)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static WebElement fluentWaitForClickabilityOf(WebElement element, int max, int polling) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(max))
				.pollingEvery(Duration.ofSeconds(polling)).ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static Boolean fluentWaitForDowloadOf(File folder, int max, int polling) {
		if (folder.exists() && folder.isDirectory()) {
			FluentWait<File> wait = new FluentWait<>(folder).withTimeout(Duration.ofSeconds(max))
					.pollingEvery(Duration.ofSeconds(polling));
			return wait.until(dir -> {
				File[] files = dir.listFiles();
				return files != null && files.length > 0;
			});

		} else
			return false;
	}

	public static void waitForAutoScrollToFinish(WebDriver driver, int timeoutInSeconds) {
    new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds), Duration.ofMillis(100))  // Polling every 100ms
        .until(new ExpectedCondition<Boolean>() {
            private long lastScrollY = -1;

            @Override
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                long currentScrollY = ((Number) js.executeScript("return window.scrollY;")).longValue();

                // If scrollY hasn't changed since last check, scrolling has stopped
                boolean scrollStopped = (currentScrollY == lastScrollY);
                lastScrollY = currentScrollY;
                return scrollStopped;
            }
        });
}
}