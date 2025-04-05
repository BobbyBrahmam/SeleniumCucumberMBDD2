package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.qa.utilities.Action;
import com.qa.utilities.TestBase;
import com.qa.utilities.Validations;

public class ResizablePage extends TestBase {

    @FindBy(how = How.XPATH, using = "//span[text()='Resizable']")
    public WebElement resizableLink;

    @FindBy(how = How.ID, using = "resizableBoxWithRestriction")
    public WebElement innerResizable;

    @FindBy(how = How.CSS, using = "[id='resizableBoxWithRestriction'] span")
    public WebElement innerResizableHolder;

    @FindBy(how = How.CSS, using = "div[class='resizable-container'] [class='constraint-area']")
    public WebElement outerBox;

    public ResizablePage() {
        PageFactory.initElements(driver, this);
    }

    public void openResizablePage() {
        Action.scrollDownFluentlyTillElementVisible(resizableLink);
        Action.click(resizableLink);
    }

    public void resizeTheInnerResizable() {
        Action.scrollDownFluentlyTillElementVisible(outerBox);
        int widthDiff = outerBox.getSize().getWidth() - innerResizable.getSize().getWidth();
        int heightDiff = outerBox.getSize().getHeight() - innerResizable.getSize().getHeight();
        Action.dragAndDropBySize(innerResizableHolder, widthDiff, heightDiff);
    }

    public Boolean isInnerBoxResized() {
        return (innerResizable.getSize().getWidth() == outerBox.getSize().getWidth()
                && innerResizable.getSize().getHeight() == outerBox.getSize().getHeight()
                && Validations.validateAttributeValueOfElement(innerResizable, "style", "width: "+outerBox.getSize().getWidth()+"px; height: "+outerBox.getSize().getHeight()+"px;", 0));
    }
}
