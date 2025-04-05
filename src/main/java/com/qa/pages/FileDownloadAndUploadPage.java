package com.qa.pages;

import java.io.File;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.qa.utilities.Action;
import com.qa.utilities.TestBase;
import com.qa.utilities.Validations;

public class FileDownloadAndUploadPage extends TestBase {

	@FindBy(how = How.XPATH, using = "//span[text()='Upload and Download']")
	public WebElement uploadAndDownloadPageLink;

	@FindBy(how = How.CSS, using = "[id='downloadButton']")
	public WebElement downloadButton;

	@FindBy(how = How.CSS, using = "[id='uploadFile']")
	public WebElement uploadButton;

	@FindBy(how = How.CSS, using = "[id='uploadedFilePath']")
	public WebElement uploadedFilePath;

	@FindBy(how = How.CSS, using = "[aria-labelledby='demo-tab-list'] [class='list-group-item list-group-item-action']")
	public List<WebElement> sortableList;

	@FindBy(how = How.CSS, using = "[aria-labelledby='demo-tab-list'] [class='list-group-item list-group-item-action']:nth-child(1)")
	public WebElement topItem;

	public FileDownloadAndUploadPage() {
		PageFactory.initElements(driver, this);
	}

	public void openUploadAndDownloadPage() {
		Action.scrollDownFluentlyTillElementVisible(uploadAndDownloadPageLink);
		Action.click(uploadAndDownloadPageLink, 1);
	}

	public void downloadFile() {
		folder.mkdir();
		Action.click(downloadButton, 1);
	}

	public Boolean isFileDownloaded(int timeoutInSeconds) {
		boolean check = Validations.validateTheDownloaded(folder, timeoutInSeconds, 1);
		if(check){
			for (File file : folder.listFiles()) {
				file.delete();
			}
			folder.delete();
		}
		return check;
	}

	public void uploadFile() {
		Action.sendKeys(uploadButton, "E:\\Programming\\sampleFile.jpeg", 1);
	}

	public Boolean isFileUploaded() {
		return Validations.validateVisibilityOfElement(uploadedFilePath, 1);
	}
}