package com.qa.extentReporterListener;

import com.qa.utilities.TestBase;

public class ConfigFileReader extends TestBase {
	public String getReportConfigPath() {
		String reportConfigPath = prop.getProperty(System.getProperty("user.dir")+"reportConfigPath");
		if (reportConfigPath != null)
			return reportConfigPath;
		else
			throw new RuntimeException(
					"Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
	}
}