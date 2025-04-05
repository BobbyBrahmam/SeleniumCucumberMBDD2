package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.qa.utilities.Action;
import com.qa.utilities.TestBase;

import io.cucumber.datatable.DataTable;

public class ManageDataTablePage extends TestBase {
	@FindBy(how = How.CSS, using = "div[class='category-cards'] div.card:nth-child(1)")
	public WebElement menu;

	@FindBy(how = How.XPATH, using = "//span[text()='Web Tables']")
	public WebElement webtableLink;

	@FindBy(how = How.CSS, using = "[class='rt-table']")
	public WebElement webtable;

	@FindBy(how = How.CSS, using = "[role='rowgroup'] span[title='Delete']")
	public List<WebElement> records;

	@FindBy(how = How.CSS, using = "div[role='rowgroup']:nth-child(1) span[title='Delete'] svg path")
	public WebElement deleteRowIcon;

	@FindBy(how = How.CSS, using = "button[id='addNewRecordButton']")
	public WebElement addNewRecordButton;

	@FindBy(how = How.CSS, using = "[class='modal-content'] div.modal-body div[id='firstName-wrapper'] div input[id='firstName']")
	public WebElement firstNameInput;

	@FindBy(how = How.CSS, using = "[class='modal-content'] div.modal-body div[id='lastName-wrapper'] div input[id='lastName']")
	public WebElement lastNameInput;

	@FindBy(how = How.CSS, using = "[class='modal-content'] div.modal-body div[id='userEmail-wrapper'] div input[id='userEmail']")
	public WebElement emailInput;

	@FindBy(how = How.CSS, using = "[class='modal-content'] div.modal-body div[id='age-wrapper'] div input[id='age']")
	public WebElement ageInput;

	@FindBy(how = How.CSS, using = "[class='modal-content'] div.modal-body div[id='salary-wrapper'] div input[id='salary']")
	public WebElement salaryInput;

	@FindBy(how = How.CSS, using = "[class='modal-content'] div.modal-body div[id='department-wrapper'] div input[id='department']")
	public WebElement departmentInput;

	@FindBy(how = How.CSS, using = "[class='modal-content'] div.modal-body button[id='submit']")
	public WebElement submitButton;

	@FindBy(how = How.CSS, using = "[class='pagination-bottom']")
	public WebElement pagination;

	// @FindBy(how = How.CSS, using = "[class='modal-content'] div.modal-body div[id='firstName-wrapper'] div input[id='firstName']")
	// public WebElement firstNameInput;

	// @FindBy(how = How.CSS, using = "[class='modal-content'] div.modal-body div[id='lastName-wrapper'] div input[id='lastName']")
	// public WebElement lastNameInput;

	// @FindBy(how = How.CSS, using = "[class='modal-content'] div.modal-body div[id='userEmail-wrapper'] div input[id='userEmail']")
	// public WebElement emailInput;

	// @FindBy(how = How.CSS, using = "[class='modal-content'] div.modal-body div[id='age-wrapper'] div input[id='age']")
	// public WebElement ageInput;

	// @FindBy(how = How.CSS, using = "[class='modal-content'] div.modal-body div[id='salary-wrapper'] div input[id='salary']")
	// public WebElement salaryInput;

	// @FindBy(how = How.CSS, using = "[class='modal-content'] div.modal-body div[id='department-wrapper'] div input[id='department']")
	// public WebElement departmentInput;

private static String tableRecordSegmentOne = "(//div[@class='rt-tbody']//div[@role='row'][.//div[text()[normalize-space()]]]/div[count(//div[@class='rt-thead -header']//div[normalize-space()='";
	private static String tableRecordSegmenttwo = "']/preceding-sibling::div) + 1])[last()]";

	public static String returnElementValueHavingXpath(String headerName) {
		return driver.findElement(By.xpath(tableRecordSegmentOne + headerName + tableRecordSegmenttwo)).getText();
	}

	public ManageDataTablePage() {
		PageFactory.initElements(driver, this);
	}

	public void openWebElementsTablePage() {
		Action.scrollDownFluentlyTillElementVisible(webtableLink);
		Action.click(webtableLink, 1);
	}

	public Boolean isUserOnWebTablePage() {
		return webtable.isDisplayed();
	}

	public void deleteAllExistingRecords() {
		Action.scrollDownFluentlyTillElementVisible(pagination);
		int size = records.size();
		for (int i = 1; i <= size; i++) {
			Action.click(deleteRowIcon, 3);
		}

	}

	public Boolean isUserAbleToDeleteRecords() {
		return records.size() == 0;
	}

	public void openRegistrationForm() {
		Action.click(addNewRecordButton, 1);
	}

	public void fillRegistrationForm(String firstName, String lastName, String email, int age, int salary,
			String department) {
		Action.sendKeys(firstNameInput, firstName, 1);
		Action.sendKeys(lastNameInput, lastName, 1);
		Action.sendKeys(emailInput, email, 1);
		Action.sendKeys(ageInput, String.valueOf(age), 1);
		Action.sendKeys(salaryInput, String.valueOf(salary), 1);
		Action.sendKeys(departmentInput, department, 1);
	}

	public void submitRegistraionForm() {
		Action.click(submitButton, 0);
	}

	public Boolean areDetailsGotAdded(String firstName, String lastName, String email, int age, int salary, String department, DataTable table) {
		Action.scrollDownFluentlyTillElementVisible(pagination);
		List<List<String>> headernames = table.cells();
		return (firstName.equals(ManageDataTablePage.returnElementValueHavingXpath(headernames.get(0).get(0)))
				&& lastName.equals(ManageDataTablePage.returnElementValueHavingXpath(headernames.get(0).get(1)))
				&& email.equals(ManageDataTablePage.returnElementValueHavingXpath(headernames.get(0).get(2)))
				&& String.valueOf(age).equals(ManageDataTablePage.returnElementValueHavingXpath(headernames.get(0).get(3)))
				&& String.valueOf(salary).equals(ManageDataTablePage.returnElementValueHavingXpath(headernames.get(0).get(4)))
				&& department.equals(ManageDataTablePage.returnElementValueHavingXpath(headernames.get(0).get(5))));			
	}
}
