package com.qa.stepDefinitions;

import org.junit.Assert;
import com.qa.pages.FileDownloadAndUploadPage;
import com.qa.utilities.TestBase;
import io.cucumber.java.en.*;

public class FileDownloadAndUploadSteps extends TestBase {

	FileDownloadAndUploadPage handler = new FileDownloadAndUploadPage().initElements();

	@When("I open upload-download page")
	public void i_open_upload_download_page() {
		handler.openUploadAndDownloadPage();
	}

	@When("I download the file")
	public void i_download_the_file() {
		handler.downloadFile();
	}

	@Then("I see the file got downloaded")
	public void i_see_the_file_got_downloaded() {
		Assert.assertTrue("File not downloaded", handler.isFileDownloaded(4));
	}

	@When("I upload the file")
	public void i_upload_the_file() {
		handler.uploadFile();
	}

	@Then("I see the file got uploaded")
	public void i_see_the_file_got_uploaded() {
		Assert.assertTrue("File not uploaded", handler.isFileUploaded());
	}
}
