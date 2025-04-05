package com.qa.pages;

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

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public void openWidgetsPane() {
        WaitForElement.waitForHomepageToSettle();
		Action.scrollDownFluentlyTillElementVisible(widgetLink);
        Action.click(widgetLink);
	}

    public void openInteractionsPane() {
        WaitForElement.waitForHomepageToSettle();
		Action.scrollDownFluentlyTillElementVisible(interactions);
        Action.click(interactions);
	}

    public void openElementsPane() {
        WaitForElement.waitForHomepageToSettle();
		Action.scrollDownFluentlyTillElementVisible(elementsLink);
		Action.click(elementsLink);
	}

    public void openAlertsFramesAndWindowsPane() {
        WaitForElement.waitForHomepageToSettle();
        Action.scrollDownFluentlyTillElementVisible(alertsFrameAndWindowsPaneLink);
		Action.click(alertsFrameAndWindowsPaneLink);
	}

}
