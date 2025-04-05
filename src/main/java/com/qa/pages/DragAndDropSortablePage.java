package com.qa.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.qa.utilities.Action;
import com.qa.utilities.TestBase;
import com.qa.utilities.WaitForElement;

public class DragAndDropSortablePage extends TestBase {

	@FindBy(how = How.XPATH, using = "//span[text()='Sortable']")
	public WebElement sortableLink;

	@FindBy(how = How.CSS, using = "[aria-labelledby='demo-tab-list'] [class='list-group-item list-group-item-action']")
	public List<WebElement> sortableList;

	@FindBy(how = How.CSS, using = "[aria-labelledby='demo-tab-list'] [class='list-group-item list-group-item-action']:nth-child(1)")
	public WebElement topItem;

	@FindBy(how = How.XPATH, using = "//div[@aria-labelledby='demo-tab-list']//div[contains(text(),'Six') and @class='list-group-item list-group-item-action']")
	public WebElement lastNumberedItem;

	@FindBy(how = How.CSS, using = "[id='demo-tabpane-list'] > div > div:last-child")
	public WebElement lastItem;

	public DragAndDropSortablePage() {
		PageFactory.initElements(driver, this);
	}

	public void openSortablePage() {
		WaitForElement.waitForAutoScrollToFinish(driver, 3);
		Action.scrollDownFluentlyTillElementVisible(sortableLink);
		Action.click(sortableLink, 1);
	}

	public void reverseSortTheSortable() {
		Action.scrollDownFluentlyTillElementVisible(lastItem, 3, 1);
		int size = sortableList.size();
		for (int i = 1; i < size; i++) {
			Action.dragAndDropByHoldAndMove(topItem, lastNumberedItem, 0, 10);
		}
	}

}
