package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.qa.utilities.Action;
import com.qa.utilities.TestBase;
import com.qa.utilities.Validations;

public class AutoCompletePage extends TestBase {
	@FindBy(how = How.CSS, using = "div[class='category-cards'] div.card:nth-child(4)")
	public WebElement widgetsLink;

	@FindBy(how = How.XPATH, using = "//span[text()='Auto Complete']")
	public WebElement autoCompletePageLink;

	@FindBy(how = How.CSS, using = "[id='autoCompleteContainer'] [class='row'] [id='autoCompleteMultiple'] input[id='autoCompleteMultipleInput']")
	public WebElement autoCompleteInputBox;
	
	@FindBy(how = How.CSS, using = ".auto-complete__menu div div")
	public List<WebElement> autoCompleteSuggetionsList;

	@FindBy(how = How.CSS, using = ".auto-complete__menu div div[id='react-select-2-option-0']")
	public WebElement autoCompleteFirstSuggetion;
	
	@FindBy(how = How.CSS, using = ".auto-complete__multi-value .auto-complete__multi-value__label")
	public WebElement enteredValue;

	public AutoCompletePage() {
		PageFactory.initElements(driver, this);
	}

	public void openAutoCompletePage() {
		Action.scrollDownFluentlyTillElementVisible(autoCompletePageLink);
		Action.click(autoCompletePageLink, 1);
	}

	public void enterInitialLetterOFWord() {
		Action.scrollDownFluentlyTillElementVisible(autoCompleteInputBox);
		Action.sendKeys(autoCompleteInputBox, "a", 3);
	}

	public Boolean areRelatedSuggestionsDisplayed() {
		return Validations.validateVisibilityOfElements(autoCompleteSuggetionsList, 3);
	}

	public void selectFirstSuggestion() {
		Action.click(autoCompleteFirstSuggetion, 1);
	}

	public Boolean isSelectedSuggestionGotEntered(String input) {
		return Validations.validateTextOfElement(enteredValue, input, 1);
	}
}