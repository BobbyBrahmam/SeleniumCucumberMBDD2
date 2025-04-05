package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.qa.utilities.Action;
import com.qa.utilities.TestBase;
import com.qa.utilities.Validations;
import com.qa.utilities.WaitForElement;

public class ToolTipsPage extends TestBase {

    @FindBy(how = How.XPATH, using = "//span[text()='Tool Tips']")
    public WebElement toolTipsLink;

    @FindBy(how = How.CSS, using = "[id='toopTipContainer'] [id='buttonToolTopContainer'] [id='toolTipButton']")
    public WebElement hoverMeButton;

    @FindBy(how = How.CSS, using = "div[id='buttonToolTip'] div[class='tooltip-inner']")
    public WebElement toolTip;

    public ToolTipsPage() {
        PageFactory.initElements(driver, this);
    }

    public void goToToolTipsPage() {
        Action.scrollDownFluentlyTillElementVisible(toolTipsLink);
        Action.click(toolTipsLink);
    }

    public void hoverOnHoverMeButton() {
        WaitForElement.waitForAutoScrollToFinish(driver, 3);
        Action.scrollDownFluentlyTillElementVisible(hoverMeButton);
        Action.hoverOnElement(hoverMeButton);
    }

    public Boolean isToolTipMessageAccurate(String expected) {
        return Validations.validateTextOfElement(toolTip, expected, 1);
    }
}
