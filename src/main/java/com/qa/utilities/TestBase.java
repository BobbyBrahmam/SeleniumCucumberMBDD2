package com.qa.utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.Scenario;

public class TestBase {
	public static final Logger log = LoggerFactory.getLogger(TestBase.class);

	public static WebDriver driver;
	public static Properties prop;
	public static File downloadsFolder;
	public static final File DOWNLOADS_FOLDER = new File(System.getProperty("user.dir") + "/downloads");
	public static String runnerLable = "";

	    public TestBase() {
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
			prop = new Properties();
			prop.load(input);
		} catch (IOException e) {
			log.error("Error occurred while doing X", e);
		}
	}

	public static WebDriver initialization() {
		downloadsFolder = new File(DOWNLOADS_FOLDER, UUID.randomUUID().toString());
		String browserName = System.getProperty("browser"); // First try to read from CLI if given
		if (browserName == null || browserName.isEmpty()) {
			browserName = prop.getProperty("browser"); // Fallback to config if CLI value is not give
		}
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.silentOutput", "true");
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("download.default_directory", downloadsFolder.getAbsolutePath());
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.setCapability("browserName", "chrome");
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.firefox.logfile", "NUL");
			FirefoxProfile profile = new FirefoxProfile();
			FirefoxOptions options = new FirefoxOptions();
			profile.setPreference("browser.download.dir", downloadsFolder.getAbsolutePath());
			profile.setPreference("browser.download.folderList", 2); // 2 = use custom folder
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf, application/octet-stream, application/vnd.ms-excel, text/csv, image/jpg, image/jpeg, image/png"); 
			options.setProfile(profile);
			options.setCapability("browserName", "firefox");
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			driver = new FirefoxDriver(options);
		} else if (browserName.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("download.default_directory", downloadsFolder.getAbsolutePath());
			options.setExperimentalOption("prefs", prefs);
			options.setCapability("browserName", "MicrosoftEdge");
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			driver = new EdgeDriver(options);
		} else {
			System.out.println("Unmatched browser name");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		return driver;
	}

	//Extent Reports
	//@Attachment(value = "Screenshot of {0}", type = "image/png")
	public static void takeScreenshot(Scenario scenario) throws IOException {
		String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String destinationPath = System.getProperty("user.dir") + prop.getProperty("cucumberReportsScreenshots") + scenario.getName()
				+ date + ".jpg";
		File screenshotFile = new File(destinationPath);
		screenshotFile.getParentFile().mkdirs();

		//Full Page Screenshot for only chrome
		if(driver instanceof ChromeDriver) {
			try {
				Map<String, Object> params = new HashMap<>();
				params.put("format", "png");
				params.put("captureBeyondViewport", true); // Ensures full-page capture
				Map<String, Object> screenshotss = ((ChromeDriver) driver).executeCdpCommand("Page.captureScreenshot", params);
				String screenshotBase64 = (String) screenshotss.get("data");
				byte[] decodedScreenshot = Base64.getDecoder().decode(screenshotBase64);
				Files.write(screenshotFile.toPath(), decodedScreenshot);
				scenario.attach(decodedScreenshot, "image/png", "Full_Screen_Screenshot_For: " + scenario.getName());
			} catch (IOException e) {
				System.out.println("Chrome full-page screenshot failed: " + e.getMessage());
			}
		}

		//Normal Screenshot for chrome, edge and firefox
		byte[] screenshots = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshots, "image/png", "Visbile_Screen_Screenshot_For: " + scenario.getName());
	}
}