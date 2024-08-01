package com.bblogautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bblogautomation.tests.BaseClass;

public class ProfilePage extends BasePage {
	WebDriver driver;

	public ProfilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, 10), this);
	}

	@FindBy(xpath = "//a[contains(text(),'My Articles') and contains(@class,'nav-link')]")
	public WebElement linkMyArticles;

	@FindBy(xpath = "//a[contains(text(),'Favorited Articles') and contains(@class,'nav-link')]")
	public WebElement linkFavArticles;

	public void navigateToFavArticles() {
		try {
			Thread.sleep(1200);
			new WebDriverWait(BaseClass.driver, 20).until(ExpectedConditions.elementToBeClickable(linkFavArticles))
					.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void navigateToMyArticles() {
		new WebDriverWait(BaseClass.driver, 20).until(ExpectedConditions.elementToBeClickable(linkMyArticles)).click();
	}

}
