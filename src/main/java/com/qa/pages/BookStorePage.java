package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.qa.utilities.Action;
import com.qa.utilities.TestBase;
import com.qa.utilities.Validations;
import com.qa.utilities.WaitForElement;

public class BookStorePage extends TestBase {

    @FindBy(how = How.XPATH, using = "//span[text()='Login']")
    public WebElement loginPageLink;

    @FindBy(how = How.CSS, using = "input[id='userName']")
    public WebElement username;

    @FindBy(how = How.CSS, using = "input[id='password']")
    public WebElement password;

    @FindBy(how = How.CSS, using = "button#login")
    public WebElement loginButton;

    @FindBy(how = How.ID, using = "userName-value")
    public WebElement profileName;

    @FindBy(how = How.CSS, using = "input[id='searchBox']")
    public WebElement searchBox;

    @FindBy(how = How.XPATH, using = "//button[text()='Logout']")
    public WebElement logoutButton;

    public BookStorePage() {
    }

    public BookStorePage initElements() {
        PageFactory.initElements(driver, this);
        return this;
    }

    public void openLoginPage() {
        Action.scrollDownFluentlyTillElementVisible(loginPageLink);
        Action.click(loginPageLink);
        WaitForElement.waitForAutoScrollToFinish(driver, 3);
    }

    public Boolean validateLoginPage() {
        Action.scrollDownFluentlyTillElementVisible(username);
        return Validations.validateVisibilityOfElement(username, 0)
                && Validations.validateVisibilityOfElement(password, 0);
    }

    public void enterUsername(String name) {
        Action.sendKeys(username, name, 0);
    }

    public void enterPassword(String pwd) {
        Action.sendKeys(password, pwd, 0);
    }

    public void clickLoginButton() {
        Action.click(loginButton);
    }

    public Boolean isUserAbleToLogin() {
        return Validations.validateVisibilityOfElement(searchBox, 5)
                && Validations.validateTextOfElement(profileName, "BobyBrahmam", 0);
    }

    public void clickLogoutButton() {
        Action.scrollDownFluentlyTillElementVisible(logoutButton);
        Action.click(logoutButton);
    }

    public Boolean isUserAbleToLogout() {
        return Validations.validateVisibilityOfElement(username, 0)
                && Validations.validateVisibilityOfElement(password, 0)
                && Validations.validateTheCurrentPageUrl(prop.getProperty("bookStoreLoginPageURL"));
    }
}
