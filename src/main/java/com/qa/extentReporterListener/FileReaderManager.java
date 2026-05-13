package com.qa.extentReporterListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.qa.utilities.TestBase;

public class FileReaderManager extends TestBase {
	private static final FileReaderManager fileReaderManager = new FileReaderManager();
	private static ConfigFileReader configFileReader;

	private FileReaderManager() {
	}

	public static FileReaderManager getInstance() {
		return fileReaderManager;
	}

	public ConfigFileReader getConfigReader() {
		return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
	}

	public static boolean containsVisibleCharacter(Path filePath) {
		try (Stream<String> lines = Files.lines(filePath)) {
			return lines.anyMatch(line -> line.trim().length() > 0); // At least one non-whitespace character
		} catch (IOException e) {
			log.error("Error occurred while doing X", e);
			return false;
		} catch (Exception e) {
			log.error("Error occurred while doing X", e);
			return false;
		}
	}
}