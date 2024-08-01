package com.bblogautomation.pageobjects;

import org.openqa.selenium.WebElement;

public class BasePage {

	
	public boolean doesElementExists(WebElement element) {
		try {
			element.getText();
			System.out.println(element.getText() + " exists");
			return true;
		} catch (Exception e) {
			System.out.println(element.getText() + " doesnt exists");
			System.out.println(e.getMessage());
			return false;
		}
	}

}
