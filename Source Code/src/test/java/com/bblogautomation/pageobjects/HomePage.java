package com.bblogautomation.pageobjects;

import java.util.List;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bblogautomation.tests.BaseClass;

public class HomePage extends BasePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, 10), this);
	}

	@FindBy(xpath = "//a[@href='#/' and contains(@class,'nav-link')]")
	public WebElement linkHome;

	@FindBy(xpath = "//a[@href='#/login' and contains(@class,'nav-link')]")
	public WebElement linkSignIn;

	@FindBy(xpath = "//a[contains(@href,'#/profile') and contains(@class,'nav-link')]")
	public WebElement linkProfile;

	@FindBy(xpath = "//a[@href='#/editor' and contains(@class,'nav-link')]")
	public WebElement linkNewPost;

	@FindBy(xpath = "//a[contains(text(),'Your Feed') and contains(@class,'nav-link')]")
	public WebElement linkYourFeed;

	@FindBy(xpath = "//a[contains(text(),'Global Feed') and contains(@class,'nav-link')]")
	public WebElement linkGlobalFeed;

	@FindBy(xpath = "//div[@class='tag-list']")
	public WebElement divTags;

	public void navigateToProfile() {

		try {
			new WebDriverWait(BaseClass.driver, 20).until(ExpectedConditions.elementToBeClickable(linkProfile)).click();
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WebElement getTagElement(String tagName) {
		return driver
				.findElement(By.xpath("//a[text()='" + tagName + "' and contains(@class,'tag-pill tag-default')]"));
	}

	public String getTagName(WebElement divOfTags) {
		String tagName = null;
		try {
			List<WebElement> allTags = divOfTags.findElements(By.xpath("//a[contains(@class,'tag-pill tag-default')]"));
			int counter = 0;
			Thread.sleep(1000);
			for (WebElement eachTag : allTags) {
				if (eachTag.getText().equals("pixel")) {
					tagName = "pixel";
					break;
				} else if (counter > 16) {
					tagName = eachTag.getText();
					break;
				}
				counter++;
			}
		} catch (Exception e) {

		}

		return tagName;
	}

	public WebElement getNavElement(String navItemName) {

		String navXpath = "//a[text()=' " + navItemName + " ' and contains(@class,'nav-link')]";
		By navItemXpath = new By.ByXPath(navXpath);
		if (!driver.findElements(navItemXpath).isEmpty()) {
			return driver.findElement(navItemXpath);
		} else
			return null;
	}

	public boolean doesSignInExists() {
		System.out.println("Came to check sign in button");
		try {
			linkSignIn.getText();
			System.out.println("Sign in button exists");
			return true;
		} catch (Exception e) {
			System.out.println("Sign in button doesnt exists");
			System.out.println(e.getMessage());
			return false;
		}
	}

	public WebElement articleItems(WebElement article, String item) throws InterruptedException {
		WebElement articleItem = null;
		item = item.toUpperCase();
		Thread.sleep(1000);
		switch (item) {
		case "AUTHOR":
			articleItem = article.findElement(By.xpath(".//a[@class='author']"));
			break;

		case "FAVBTN":
			// when current user havent marked article as favorite we return false else true
			articleItem = article.findElement(By.tagName("button"));
			break;

		case "TITLE":
			By titlePath = new ByChained(By.xpath(".//a[@class='preview-link']"), By.tagName("h1"));
			articleItem = article.findElement(titlePath);
			break;

		case "FAVCOUNT":
			By favCountPath = new ByChained(By.tagName("button"), By.tagName("div"));
			articleItem = article.findElement(favCountPath);
			break;

		}
		return articleItem;
	}

	public void clickSignIn() {
		linkSignIn.click();
	}

}
