package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.qa.utilities.*;

public class DragAndDropPage extends TestBase {
	
	@FindBy(how = How.XPATH, using = "//span[text()='Droppable']")
	public WebElement droppableMenuOption;

	@FindBy(how = How.CSS, using = "[id='draggable']")
	public WebElement draggable;

	@FindBy(how = How.CSS, using = "[id='droppable']")
	public WebElement droppable;

	@FindBy(how = How.CSS, using = "[class='drop-box ui-droppable ui-state-highlight'] p")
	public WebElement dropped;

	@FindBy(how = How.CSS, using = ".nav.nav-tabs[role='tablist']")
	public WebElement menu;

	public DragAndDropPage() {
		PageFactory.initElements(driver, this);
	}

	public void openDroppablePage() {
		Action.scrollDownFluentlyTillElementVisible(droppableMenuOption);
		Action.click(droppableMenuOption, 2);
	}

	public Boolean isUserOnDragAndDropPage() {
		return (draggable.isDisplayed() && droppable.isDisplayed());
	}

	public void dragAndDrop() {
		Action.scrollDownFluentlyTillElementVisible(menu);
		Action.dragAndDrop(draggable, droppable);
	}

	public Boolean validateDragAndDrop(String expectedValue) {
		return Validations.validateTextOfElement(dropped, expectedValue, 7);
	}
}