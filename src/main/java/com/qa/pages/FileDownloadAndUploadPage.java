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
	}

	public FileDownloadAndUploadPage initElements() {
		PageFactory.initElements(driver, this);
		return this;
	}

	public void openUploadAndDownloadPage() {
		Action.scrollDownFluentlyTillElementVisible(uploadAndDownloadPageLink);
		Action.click(uploadAndDownloadPageLink, 1);
	}

	public void downloadFile() {
		downloadsFolder.mkdir();
		Action.scrollDownFluentlyTillElementVisible(downloadButton);
		Action.click(downloadButton, 2);
	}

	public Boolean isFileDownloaded(int timeoutInSeconds) {
		boolean check = Validations.validateTheDownloaded(downloadsFolder, timeoutInSeconds, 1);
		if(check){
			for (File file : downloadsFolder.listFiles()) {
				file.delete();
			}
		}
		return check;
	}

	public void uploadFile() {
		String filePath = System.getProperty("user.dir")+prop.getProperty("sampleImagePath");
		filePath = filePath.replace("/", "\\").replace("\\", "\\\\");
		Action.sendKeys(uploadButton, filePath, 1);
	}

	public Boolean isFileUploaded() {
		return Validations.validateVisibilityOfElement(uploadedFilePath, 1);
	}
}