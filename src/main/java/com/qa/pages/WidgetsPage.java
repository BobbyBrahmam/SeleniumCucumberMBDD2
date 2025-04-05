package com.qa.pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.qa.utilities.*;

public class WidgetsPage extends TestBase {

	@FindBy(how = How.XPATH, using = "//span[text()='Slider']")
	public WebElement sliderLink;

	@FindBy(how = How.CSS, using = "input[type='range'][class='range-slider range-slider--primary']")
	public WebElement slider;

	@FindBy(how = How.CSS, using = "input[type='range'][class='range-slider range-slider--primary']")
	public WebElement sliderHandle;

	@FindBy(how = How.CSS, using = "input[id='sliderValue']")
	public WebElement sliderValue;

	@FindBy(how = How.CSS, using = "div[id='sliderContainer'] div[class='range-slider__tooltip__label']")
	public WebElement slideToolTip;

	public WidgetsPage() {
		PageFactory.initElements(driver, this);
	}

	public void openSliderPage() {
		Action.scrollDownFluentlyTillElementVisible(sliderLink);
		Action.click(sliderLink, 1);
	}

	public void isUserOnSliderPage() {
		Assert.assertTrue("Slider is not present", Validations.validateVisibilityOfElement(slider, 2));
	}

	public void slideTheSliderToSomeValue(int range) {
		Action.dragAndDropBySize(sliderHandle, 25, range);
	}

	public void isValueSameAsSlided(int range) {
		Assert.assertEquals(range, Integer.parseInt(sliderValue.getDomAttribute("value")));
	}

	public void slideTheSliderDynamicallyToSomeValue(int range) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));";
		js.executeScript(script, slider, range);
	}
}