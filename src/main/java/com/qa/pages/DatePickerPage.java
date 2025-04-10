package com.qa.pages;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.utilities.Action;
import com.qa.utilities.TestBase;
import com.qa.utilities.WaitForElement;

public class DatePickerPage extends TestBase {

    private static String dateInput;
    private static int yearNumber;
    private static int dateNumber;
    private static String nameOfMonth;

    @FindBy(how = How.XPATH, using = "//span[text()='Date Picker']")
    public WebElement datePickerLink;

    @FindBy(how = How.ID, using = "datePickerMonthYearInput")
    public WebElement dateInputField;

    @FindBy(how = How.CSS, using = "select[class='react-datepicker__month-select']")
    public WebElement monthDropdownElement;

    @FindBy(how = How.CSS, using = "select[class='react-datepicker__year-select']")
    public WebElement yearDropdownElement;

    public DatePickerPage() {
        PageFactory.initElements(driver, this);
    }

    public void openDatePickerPage() {
        Action.scrollDownFluentlyTillElementVisible(datePickerLink);
        Action.click(datePickerLink);
    }

    public void enterTheDate(String dateValue) {
        WaitForElement.waitForAutoScrollToFinish(driver, 4);
        dateInput = dateValue;
        Action.scrollDownFluentlyTillElementVisible(dateInputField);
        dateNumber = Integer.parseInt(dateValue.split("/")[1]);
        yearNumber = Integer.parseInt(dateValue.split("/")[2]);
        Action.click(dateInputField);
        Action.performTasks(dateInputField, 0, Keys.chord(Keys.CONTROL, "a"));
        Action.performTasks(dateInputField, 0, Keys.BACK_SPACE);
        Action.sendKeys(dateInputField, dateValue, 0);
    }

    public Boolean isTheDateMonthYearAccurate() {

        // get expected month name from given date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date;
        try {
            date = LocalDate.parse(dateInput, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return false; // Invalid date
        }
        String expectedMonthName = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        // get month name got selected
        Select monthDropdown = new Select(monthDropdownElement);
        String selectedMonthOption = monthDropdown.getFirstSelectedOption().getText();

        // get selected date
        String xpathOfDate = "//div[contains(@class, 'react-datepicker__day') and text()='" + dateNumber + "']";
        int actualDateDisplayed = 0;
        List<WebElement> elements = driver.findElements(By.xpath(xpathOfDate));

        if (elements.size() > 1) {
            if (dateNumber >= 1 && dateNumber <= 14) {
                actualDateDisplayed = Integer.parseInt(elements.get(0).getText());
            } else if (dateNumber >= 22 && dateNumber <= 31) {
                actualDateDisplayed = Integer.parseInt(elements.get(1).getText());
            } else if (dateNumber >= 15 && dateNumber <= 21) {
                actualDateDisplayed = Integer.parseInt(elements.get(0).getText());
            } else {
            }
        } else if (elements.size() == 1) {
            actualDateDisplayed = Integer.parseInt(elements.get(0).getText());
        }

        // get selected year
        Select yearDropdown = new Select(yearDropdownElement);
        int selectedYearOption = Integer.parseInt(yearDropdown.getFirstSelectedOption().getText());

        // validate the actual displayed values with given expected values
        return (expectedMonthName.equals(selectedMonthOption) && dateNumber == actualDateDisplayed
                && yearNumber == selectedYearOption);
    }

    public void selectTheMonth(String monthName) {
        nameOfMonth = monthName;
        WaitForElement.waitForAutoScrollToFinish(driver, 4);
        Action.scrollDownFluentlyTillElementVisible(dateInputField, 3, 1);
        Action.click(dateInputField);
        Action.selectDropdownValue(monthDropdownElement, monthName, 1);
    }

    public void selectTheYear(int year) {
        yearNumber = year;
        Action.selectDropdownValue(yearDropdownElement, String.valueOf(year), 0);
    }

    public void selectTheDate(int dateValue) {
        dateNumber = dateValue;
        String xpathOfDate = "//div[contains(@class, 'react-datepicker__day') and text()='" + dateValue + "']";
        List<WebElement> elements = driver.findElements(By.xpath(xpathOfDate));
        if (elements.size() > 1) {
            if (dateValue >= 1 && dateValue <= 14) {
                Action.click(elements.get(0));
            } else if (dateValue >= 22 && dateValue <= 31) {
                Action.click(elements.get(1));
            } else if (dateNumber >= 15 && dateNumber <= 21) {
                Action.click(elements.get(0));
            } else {

            }
        } else if (elements.size() == 1) {
            Action.click(elements.get(0));
        }
    }

    public Boolean isDatePopulatedAccurate() {
        int monthValue = Month.valueOf(nameOfMonth.toUpperCase()).getValue(); // converting month name into its
                                                                              // numerical value;
        String expectedMonthValue = (monthValue > 0 && monthValue < 10) ? String.format("0%d", monthValue)
                : String.valueOf(monthValue); // adding 0 as prefix if it is a single digit number
        String expectedDateValue = (dateNumber > 0 && dateNumber < 10) ? String.format("0%d", dateNumber)
                : String.valueOf(dateNumber); // adding 0 as prefix if it is a single digit number
        String expectedDateFormatValue = expectedMonthValue + "/" + expectedDateValue + "/" + yearNumber;
        String actualDateFormatValue = dateInputField.getDomAttribute("value");
        return expectedDateFormatValue.equals(actualDateFormatValue);
    }

}