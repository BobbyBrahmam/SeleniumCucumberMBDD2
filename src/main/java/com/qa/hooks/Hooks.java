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
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import java.util.logging.Logger;
import java.util.logging.Level;


public class Hooks extends TestBase {


	@BeforeAll
	public static void consoleFilterSetup() {
		    
        // Silences the Selenium DevTools Protocol (CDP) warnings
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        
        // Silences the specific CDP version finder warning
        Logger.getLogger("org.openqa.selenium.devtools.CdpVersionFinder").setLevel(Level.OFF);
        
        // Optional: Silences the WebDriver Manager logs if you use it
        System.setProperty("webdriver.chrome.silentOutput", "true");
	}

	@Before
    public void startScenario(Scenario scenario) {
        // ANSI Escape Codes for Blue and Reset
        String blue = "\u001B[34m";
        String reset = "\u001B[0m";

        // This prints directly to the console WITHOUT the [main] INFO prefix
        System.out.println(blue + "Scenario: " + scenario.getName() + reset);
    }
	
	@After
	public void endTest(Scenario scenario) throws IOException {
		try {
			if (scenario.isFailed()) {
				super.takeScreenshot(scenario);
			}
		} catch (IOException e) {
			log.error("An IOException occurred while taking screenshot for failed scenario: " + scenario.getName(), e);
		}
		catch (Exception e) {
			log.error("An Exception occurred while taking screenshot for failed scenario: " + scenario.getName(), e);
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
				if (runnerLable.equals("FailedRunTest") && Files.exists(source)) {
					Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
				}
			} catch (IOException e) {
				log.error("An error occurred while copying failed rerun file: " + e.getMessage(), e);
			}

			try {
				if (sourceDir.exists()) {
					Files.move(sourceDir.toPath(), destinationDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
					log.info("Allure report moved to: " + destinationDir.getAbsolutePath());
				} else {
					log.warn("No Allure report found to backup.");
				}
			} 
			catch(IOException e) {
				log.error("An IOException occurred while backing up Allure reports: " + e.getMessage(), e);
			}
			catch (Exception e) {
				log.error("An Exception occurred while backing up Allure reports: " + e.getMessage(), e);
			}

		}));

	}

}