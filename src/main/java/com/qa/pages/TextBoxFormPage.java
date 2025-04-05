package com.qa.pages;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.qa.utilities.Action;
import com.qa.utilities.CustomFunctions;
import com.qa.utilities.TestBase;

public class TextBoxFormPage extends TestBase {

    @FindBy(how = How.XPATH, using = "//span[text()='Text Box']")
    public WebElement textBoxFormPageLink;

    @FindBy(how = How.ID, using = "userName")
    public WebElement userFullName;

    @FindBy(how = How.ID, using = "userEmail")
    public WebElement userEmail;

    @FindBy(how = How.ID, using = "currentAddress")
    public WebElement currentAddress;

    @FindBy(how = How.ID, using = "permanentAddress")
    public WebElement permanentAddress;

    @FindBy(how = How.ID, using = "submit")
    public WebElement submitButton;

    @FindBy(how = How.CSS, using = "[id='output'] [id='name']")
    public WebElement nameInOutput;

    @FindBy(how = How.CSS, using = "[id='output'] [id='email']")
    public WebElement emailAddressInOutput;

    @FindBy(how = How.CSS, using = "[id='output'] [id='currentAddress']")
    public WebElement currentAddressInOutput;

    @FindBy(how = How.CSS, using = "[id='output'] [id='permanentAddress']")
    public WebElement permanentAddressInOutput;

    String fullNameEntered;
    String emailAddressEntered;
    String currentAddressEntered;
    String permanentAddressEntered;

    public TextBoxFormPage() {
        PageFactory.initElements(driver, this);
    }

    public void openTextBoxFormPage() {
        Action.scrollDownFluentlyTillElementVisible(textBoxFormPageLink);
        Action.click(textBoxFormPageLink, 1);
    }

    public void enterTheFullName(String fullName) {
        fullNameEntered = fullName;
        Action.sendKeys(userFullName, fullName, 0);
    }

    public void enterEmailAddress(String emailAddress) {
        emailAddressEntered = emailAddress;
        Action.sendKeys(userEmail, emailAddress, 0);
    }

    public void enterCurrentAddress(String presentAddress) {
        currentAddressEntered = presentAddress;
        Action.sendKeys(currentAddress, presentAddress, 0);
    }

    public void enterPermanentAddress(String permanentLocation) {
        permanentAddressEntered = permanentLocation;
        Action.sendKeys(permanentAddress, permanentLocation, 0);
    }

    public void enterAllTheDetails(String fullName, String emailAddress, String presentAddress, String permanentLocation) {
        fullNameEntered = fullName;
        emailAddressEntered = emailAddress;
        currentAddressEntered = presentAddress;
        permanentAddressEntered = permanentLocation;
        Map<WebElement, String> formData = new HashMap<>();
        formData.put(userFullName, fullName);
        formData.put(userEmail, emailAddress);
        formData.put(currentAddress, presentAddress);
        formData.put(permanentAddress, permanentLocation);
        CustomFunctions.fillFormSimultaneously(formData);
    }

    public void submitTheForm() {
        Action.scrollDownFluentlyTillElementVisible(submitButton);
        Action.click(submitButton, 0);
    }

    public void areDetailsGettingPopulated() {
        Action.scrollDownFluentlyTillElementVisible(permanentAddressInOutput);
        Assert.assertEquals("Name:" + fullNameEntered, nameInOutput.getText());
        Assert.assertEquals("Email:" + emailAddressEntered, emailAddressInOutput.getText());
        Assert.assertEquals("Current Address :" + currentAddressEntered, currentAddressInOutput.getText());
        Assert.assertEquals("Permananet Address :" + permanentAddressEntered, permanentAddressInOutput.getText());
    }
}