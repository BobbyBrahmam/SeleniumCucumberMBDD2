package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.qa.utilities.Action;
import com.qa.utilities.TestBase;
import com.qa.utilities.WaitForElement;

public class HomePage extends TestBase {

    @FindBy(how = How.CSS, using = "div[class='category-cards'] div.card:nth-child(1)")
	public WebElement elementsLink;

    @FindBy(how = How.CSS, using = "div[class='category-cards'] div.card:nth-child(3)")
	public WebElement alertsFrameAndWindowsPaneLink;

    @FindBy(how = How.CSS, using = "div[class='category-cards'] div.card:nth-child(4)")
	public WebElement widgetLink;

    @FindBy(how = How.CSS, using = "div[class='category-cards'] div.card:nth-child(5)")
	public WebElement interactions;

    @FindBy(how = How.CSS, using = "div[class='category-cards'] div.card:nth-child(6)")
	public WebElement bookStoreLink;

    @FindBy(how = How.CSS, using = "div#app div.body-height div.home-content div.home-body div.category-cards div.card.mt-4.top-card")
	public List<WebElement> cards;

    @FindBy(how = How.CSS, using = "div#app div.body-height div.home-content div.home-body div.category-cards")
	public WebElement cardSet;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public void openWidgetsPane() {
        WaitForElement.waitBrieflyUntilCardsSetVisible(cardSet, 10);
		Action.scrollDownFluentlyTillElementVisible(widgetLink);
        Action.click(widgetLink);
	}

    public void openInteractionsPane() {
        WaitForElement.waitBrieflyUntilCardsSetVisible(cardSet, 10);
		Action.scrollDownFluentlyTillElementVisible(interactions);
        Action.click(interactions);
	}

    public void openElementsPane() {
        WaitForElement.waitBrieflyUntilCardsSetVisible(cardSet, 10);
		Action.scrollDownFluentlyTillElementVisible(elementsLink);
		Action.click(elementsLink);
	}

    public void openAlertsFramesAndWindowsPane() {
        WaitForElement.waitBrieflyUntilCardsSetVisible(cardSet, 10);
        Action.scrollDownFluentlyTillElementVisible(alertsFrameAndWindowsPaneLink);
		Action.click(alertsFrameAndWindowsPaneLink);
	}

    public void openBookStorePane() {
        WaitForElement.waitBrieflyUntilCardsSetVisible(cardSet, 10);
        Action.scrollDownFluentlyTillElementVisible(bookStoreLink);
		Action.click(bookStoreLink);
	}

}
