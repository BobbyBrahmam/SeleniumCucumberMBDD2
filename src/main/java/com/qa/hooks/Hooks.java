package com.qa.hooks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import com.qa.utilities.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;


public class Hooks extends TestBase {
	
	@After
	public void endTest(Scenario scenario) throws IOException {
		try {
			if (scenario.isFailed()) {
				super.takeScreenshot(scenario);
			}
		} catch (Exception e) {
			System.out.println("Scenario may be broken. Taking screenshot as precaution.");
			super.takeScreenshot(scenario);
		}
		
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	@AfterAll
	public static void backupReportsAndFailedFeatures() {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			downloadsFolder.delete();
			Path source = Paths.get(System.getProperty("user.dir")+prop.getProperty("failedrerunbackup.file.path"));
			Path destination = Paths.get(System.getProperty("user.dir")+prop.getProperty("failedrerun.file.path"));
			String allureReportsPath = System.getProperty("user.dir")+prop.getProperty("allureReports.folder.path");
			String backupBasePath = System.getProperty("user.dir")+prop.getProperty("backupBase.folder.path");
			String timestamp = java.time.LocalDateTime.now().toString().replace(":", "-").replace("T", "_");
			File sourceDir = new File(allureReportsPath);
			File destinationDir = new File(backupBasePath + "/AllureReports-" + timestamp);
			try {
				if (runnerLable == "FailedRunTest" && Files.exists(source)) {
					Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
				}
			} catch (IOException e) {
				System.out.println("An error occurred: " + e.getMessage());
			}

			try {
				if (sourceDir.exists()) {
					Files.move(sourceDir.toPath(), destinationDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
					System.out.println("Allure report moved to: " + destinationDir.getAbsolutePath());
				} else {
					System.out.println("No Allure report found to backup.");
				}
			} catch (Exception e) {
				System.out.println("An error occurred: " + e.getMessage());
			}

		}));

	}

}