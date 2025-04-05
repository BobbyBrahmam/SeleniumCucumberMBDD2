package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.qa.utilities.Action;
import com.qa.utilities.TestBase;
import com.qa.utilities.Validations;
import com.qa.utilities.WaitForElement;

public class MenuPage extends TestBase {

	@FindBy(how = How.XPATH, using = "//span[text()='Menu']")
	public WebElement menuLink;

	@FindBy(how = How.CSS, using = "div[class='nav-menu-container']")
	public WebElement menubar;

	@FindBy(how = How.CSS, using = "[class='nav-menu-container']>ul[id='nav']>li:nth-child(2)>a")
	public WebElement secondMenu;

	@FindBy(how = How.CSS, using = "[class='nav-menu-container']>ul[id='nav']>li:nth-child(2)>ul")
	public WebElement secondMenuList;

	@FindBy(how = How.CSS, using = "[class='nav-menu-container']>ul[id='nav']>li:nth-child(2)>ul>li:nth-child(3)")
	public WebElement subMenu;

	@FindBy(how = How.CSS, using = "[class='nav-menu-container']>ul[id='nav']>li:nth-child(2)>ul>li:nth-child(3)>ul>li:nth-child(2)")
	public WebElement subMenuList;

	public MenuPage() {
		PageFactory.initElements(driver, this);
	}

	public void openMenuPage() {
		Action.scrollDownFluentlyTillElementVisible(menuLink);
		Action.click(menuLink, 1);
	}

	public Boolean isMenuBarDisplayed() {
		return Validations.validateVisibilityOfElement(menubar, 1);
	}

	public void hoverOnMainMenu(String order) {
		WaitForElement.waitForAutoScrollToFinish(driver, 3);
		if (order.equalsIgnoreCase("second")) {
			Action.scrollDownFluentlyTillElementVisible(secondMenu);
			Action.hoverOnElement(secondMenu);
		}
	}

	public Boolean isMainMenuListDisplayed(String order) {
		if (order.equalsIgnoreCase("second"))
			return Validations.validateVisibilityOfElement(secondMenuList, 1);
		else
			return false;
	}

	public void hoverOnSubMenu(String order) {
		if (order.equalsIgnoreCase("second"))
			Action.waitFluentlyAndHoverOnElement(subMenu, 4);
	}

	public Boolean isSubMenuListDisplayed(String order) {
		if (order.equalsIgnoreCase("second"))
			return Validations.validateVisibilityOfElement(subMenuList, 1);
		else
			return false;
	}
}
