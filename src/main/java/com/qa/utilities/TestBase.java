package com.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.cucumber.java.Scenario;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static File folder;
	public static final File DOWNLOADS_FOLDER = new File("E:\\Programming\\workspace\\SeleniumCucumberMBDD2\\downloads");
	public static String runnerLable = "";

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream file = new FileInputStream(
					"E:\\Programming\\workspace\\SeleniumCucumberMBDD2\\src\\main\\java\\com\\qa\\configuration\\config.properties");
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver initialization() {
		folder = new File(DOWNLOADS_FOLDER, UUID.randomUUID().toString());
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.silentOutput", "true");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", folder.getAbsolutePath());
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.setCapability("browserName", "chrome");
			driver = new ChromeDriver(options);
		}

		else if (browserName.equals("FireFox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
		}

		else if (browserName.equals("Internet Explorer")) {
			System.setProperty("webdriver.ie.driver", "C:\\Drivers\\ie.exe");
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
		String destinationPath = System.getProperty("user.dir") + "/cucumber-reports/screenshots/" + scenario.getName() + date + ".jpg";
		// For Full Page Screenshot and normal screenshot
		File screenshotFile = new File(destinationPath);
		screenshotFile.getParentFile().mkdirs();
		Map<String, Object> params = new HashMap<>();
		params.put("format", "png");
		params.put("captureBeyondViewport", true); // Ensures full-page capture
		Map<String, Object> screenshotss = ((ChromeDriver) driver).executeCdpCommand("Page.captureScreenshot", params);
		String screenshotBase64 = (String) screenshotss.get("data");
		byte[] decodedScreenshot = Base64.getDecoder().decode(screenshotBase64);
		Files.write(screenshotFile.toPath(), decodedScreenshot);
		scenario.attach(decodedScreenshot, "image/png", "Full_Screen_Screenshot_For: " + scenario.getName());
		byte[] screenshots = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshots, "image/png", "Visbile_Screen_Screenshot_For: " + scenario.getName());
	}
}