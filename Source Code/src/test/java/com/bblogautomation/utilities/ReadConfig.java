package com.bblogautomation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties prop;

	public ReadConfig() {
		try {
			File configSrc = new File("./Configuration/config.properties");
			FileInputStream fis = new FileInputStream(configSrc);
			prop = new Properties();
			prop.load(fis);

		} catch (Exception e) {
			System.out.println("Exception occured in Read Config: " + e.getMessage());
		}

	}
	
	public String getConfigData(String fieldName)
	{
		String fieldValue = prop.getProperty(fieldName, "");
		return fieldValue;
	}
}
