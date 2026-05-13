package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.qa.utilities.Action;
import com.qa.utilities.TestBase;
import com.qa.utilities.WaitForElement;

public class HomePage extends TestBase {

    @FindBy(how = How.CSS, using = "div[class='category-cards'] a:nth-child(1) div div div:nth-child(2)")
	public WebElement elementsLink;

    @FindBy(how = How.CSS, using = "div[class='category-cards'] a:nth-child(3) div div div:nth-child(2)")
	public WebElement alertsFrameAndWindowsPaneLink;

    @FindBy(how = How.CSS, using = "div[class='category-cards'] a:nth-child(4) div div div:nth-child(2)")
	public WebElement widgetLink;

    @FindBy(how = How.CSS, using = "div[class='category-cards'] a:nth-child(5) div div div:nth-child(2)")
	public WebElement interactions;

    @FindBy(how = How.CSS, using = "div[class='category-cards'] a:nth-child(6) div div div:nth-child(2)")
	public WebElement bookStoreLink;

    @FindBy(how = How.CSS, using = "div[class='category-cards'] a")
	public WebElement cardSet;

    public HomePage() {
    }

    public HomePage initElements() {
        PageFactory.initElements(driver, this);
        return this;
    }

    public void openWidgetsPane() {
        WaitForElement.waitBrieflyUntilCardsSetVisible(cardSet, 12);
		Action.scrollDownFluentlyTillElementVisible(widgetLink);
        Action.click(widgetLink);
	}

    public void openInteractionsPane() {
        WaitForElement.waitBrieflyUntilCardsSetVisible(cardSet, 12);
		Action.scrollDownFluentlyTillElementVisible(interactions);
        Action.click(interactions);
	}

    public void openElementsPane() {
        WaitForElement.waitBrieflyUntilCardsSetVisible(cardSet, 12);
		Action.scrollDownFluentlyTillElementVisible(elementsLink);
		Action.click(elementsLink);
	}

    public void openAlertsFramesAndWindowsPane() {
        WaitForElement.waitBrieflyUntilCardsSetVisible(cardSet, 12);
        Action.scrollDownFluentlyTillElementVisible(alertsFrameAndWindowsPaneLink);
		Action.click(alertsFrameAndWindowsPaneLink);
	}

    public void openBookStorePane() {
        WaitForElement.waitBrieflyUntilCardsSetVisible(cardSet, 12);
        Action.scrollDownFluentlyTillElementVisible(bookStoreLink);
		Action.click(bookStoreLink);
	}

}
