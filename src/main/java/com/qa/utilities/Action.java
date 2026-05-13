package com.qa.utilities;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Action extends TestBase {

    static Actions actions = null;
    static final int DEFAULT_POLLING = 1;

    public static void click(WebElement element) {
        element.click();
    }

    public static void click(WebElement element, int wait) {
        try {
            WaitForElement.waitForClickability(element, wait).click();
        } catch (NoSuchElementException e) {
            System.out.println("The problem is: " + e);
            log.error("Error occurred while doing X", e);
        }
    }

    public static void fluentClick(WebElement element, int maxTime) {
        try {
            WaitForElement.fluentWaitForClickabilityOf(element, maxTime, DEFAULT_POLLING).click();
        } catch (NoSuchElementException | ElementClickInterceptedException e) {
            System.out.println("The problem is: " + e);
            log.error("Error occurred while doing X", e);
        }
    }

    public static void sendKeys(WebElement element, String input, int wait) {
        try {
            WaitForElement.waitForVisibilityOf(element, wait).sendKeys(input);
        } catch (NoSuchElementException e) {
            System.out.println("The problem is: " + e);
            log.error("Error occurred while doing X", e);
        }
    }

    public static void selectDropdownValue(WebElement element, String option, int wait) {
        Select select = new Select(element);
        select.selectByVisibleText(option);
    }

    public static void performTasks(WebElement element, int wait, CharSequence... input) {
        try {
            WaitForElement.waitForVisibilityOf(element, wait).sendKeys(input);
        } catch (NoSuchElementException e) {
            System.out.println("The problem is: " + e);
            log.error("Error occurred while doing X", e);
        }
    }

    public static void mouseHoverOnElement(WebElement element) {
        Actions act = new Actions(driver);
        act.moveToElement(element).build().perform();
    }

    public static void dragAndDrop(WebElement from, WebElement to) {
        Actions act = new Actions(driver);
        //act.clickAndHold(from).moveToElement(to).release().build().perform();
        act.dragAndDrop(from, to).build().perform();
    }

    public static void dragAndDropByHoldAndMove(WebElement from, WebElement to, int offsetX, int offsetY) {
        Actions act = new Actions(driver);
        act.click(from).clickAndHold().moveToElement(to).moveByOffset(offsetX, offsetY).release().build().perform();
    }

    public static void dragAndDropBySize(WebElement element, int offsetX, int offsetY) {
        Actions act = new Actions(driver);
        act.clickAndHold(element).moveByOffset(offsetX, offsetY).release().build().perform();
    }

    public static void hoverOnElement(WebElement element) {
        Actions act = new Actions(driver);
        act.moveToElement(element).build().perform();
    }

    public static void waitAndHoverOnElement(WebElement element, int wait) {
        try {
            Actions act = new Actions(driver);
            act.moveToElement(WaitForElement.waitForVisibilityOf(element, wait)).build().perform();
        } catch (NoSuchElementException e) {
            log.error("Error occurred while doing X", e);
        }
    }

    public static void waitFluentlyAndHoverOnElement(WebElement element, int wait) {
        try {
            Actions act = new Actions(driver);
            act.moveToElement(WaitForElement.fluentWaitForVisibilityOf(element, wait, DEFAULT_POLLING)).build()
                    .perform();
        } catch (NoSuchElementException e) {
            log.error("Error occurred while doing X", e);
        }
    }

    public static void clickByExecutor(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static void scrollDown(int pixels) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0, pixels)");
    }

    public static void scrollDownTillElementVisible(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void scrollDownFluentlyTillElementVisible(WebElement conditional_element, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void scrollDownFluentlyTillElementVisible(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(element));

        js.executeScript("""

        const element = arguments[0];

        const rect = element.getBoundingClientRect();

        const absoluteElementTop = rect.top + window.pageYOffset;

        const middle = absoluteElementTop - (window.innerHeight / 2);

        window.scrollTo({
            top: middle,
            behavior: 'instant'
        });

    """, element);

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void scrollDownFluentlyTillElementVisible(WebElement element, int maxTime, int polling) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        try {
            executor.executeScript("arguments[0].scrollIntoView();",
                    WaitForElement.fluentWaitForVisibilityOf(element, maxTime, polling));
        } catch (Exception e) {
            System.out.println("The problem is: " + e);
        }
    }

    public static void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            System.out.println("escape from alert");
            actions = new Actions(driver);
            actions.keyDown(Keys.ESCAPE);
        }
    }

    public static void dismissAlert() {
        try {
            driver.switchTo().alert().dismiss();
        } catch (Exception e) {
            System.out.println("escape from alert");
            actions = new Actions(driver);
            actions.keyDown(Keys.ESCAPE);
        }
    }

    public static void enterTheValueInPromptAlert(String value) {
        driver.switchTo().alert().sendKeys(value);
    }

}
