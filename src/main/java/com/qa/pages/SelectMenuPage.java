package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.utilities.Action;
import com.qa.utilities.TestBase;
import com.qa.utilities.Validations;
import com.qa.utilities.WaitForElement;

import io.cucumber.datatable.DataTable;

public class SelectMenuPage extends TestBase {

	@FindBy(how = How.XPATH, using = "//span[text()='Select Menu']")
	public WebElement selectMenuPageLink;

	@FindBy(how = How.CSS, using = "[id='withOptGroup']")
	public WebElement bootstrapMenu;

	@FindBy(how = How.CSS, using = "[class=' css-1uccc91-singleValue']")
	public WebElement selectedValueOfBootstrapMenu;

	@FindBy(how = How.CSS, using = "[id='oldSelectMenu']")
	public WebElement oldSelectMenu;

	@FindBy(how = How.CSS, using = "[id='cars']")
	public WebElement lastSelectMenu;

	public SelectMenuPage() {
		PageFactory.initElements(driver, this);
	}

	public void openSelectMenuPage() {
		Action.scrollDownFluentlyTillElementVisible(selectMenuPageLink);
		Action.click(selectMenuPageLink, 1);
	}

	public Boolean isBootStrapMenuDisplayed() {
		WaitForElement.waitForAutoScrollToFinish(driver, 3);
		Action.scrollDownFluentlyTillElementVisible(bootstrapMenu);
		return Validations.validateVisibilityOfElement(bootstrapMenu, 1);
	}

	public void selectTheBootstrapOption(String value) {
		Action.scrollDownFluentlyTillElementVisible(bootstrapMenu);
		Action.click(bootstrapMenu, 1);
		Action.mouseHoverOnElement(
				driver.findElement(By.xpath("//*[@class!=' css-1uccc91-singleValue' and text()='" + value + "']")));
		Action.fluentClick(driver.findElement(By.xpath("//*[@class!=' css-1uccc91-singleValue' and text()='" + value + "']")),5);
	}

	public Boolean isSelectedValueDisplayed(String value) {
		return Validations.validateTextOfElement(selectedValueOfBootstrapMenu, value, 1);
	}

	public Boolean isOldMenuDisplayed() {
		return Validations.validateVisibilityOfElement(oldSelectMenu, 1);
	}

	public Boolean ableToSelectAllOptions(DataTable colours) {

		boolean check = false;

		List<List<String>> options = colours.cells();

		Select option = new Select(oldSelectMenu);
		
		for (int i = 1; i < options.size(); i++) {
			option.selectByVisibleText(options.get(i).get(0));
			if (options.get(i).get(0).equals(option.getFirstSelectedOption().getText())) {
				check = true;
				continue;
			}
			else {
				check = false;
				break;
			}
		}
		return check;
	}
}